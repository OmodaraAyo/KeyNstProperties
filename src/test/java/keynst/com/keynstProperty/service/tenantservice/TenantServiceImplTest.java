package keynst.com.keynstProperty.service.tenantservice;

import io.github.cdimascio.dotenv.Dotenv;
import keynst.com.keynstProperty.KeynstPropertyApplication;
import keynst.com.keynstProperty.dtos.TenantDtos.TenantRequest;
import keynst.com.keynstProperty.dtos.TenantDtos.TenantResponse;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersRequest;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersResponse;
import keynst.com.keynstProperty.models.enums.Gender;
import keynst.com.keynstProperty.models.enums.JobTitle;
import keynst.com.keynstProperty.models.enums.Salary;
import keynst.com.keynstProperty.repository.TenantRepo;
import keynst.com.keynstProperty.repository.UserRepo;
import keynst.com.keynstProperty.service.userservice.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = KeynstPropertyApplication.class)
@ActiveProfiles("test")
public class TenantServiceImplTest {

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    private UsersResponse usersResponse;

    @BeforeEach
    public void startAllWithThis() {
//        Dotenv dotenv = Dotenv.load();
        tenantRepo.deleteAll();
        userRepo.deleteAll();

        UsersRequest user = new UsersRequest();
        user.setUsername("test_username");
        user.setPassword("test_password");
        user.setEmail("test_email@gmail.com");
        user.setFirstName("test_first_name");
        user.setLastName("test_last_name");
        user.setGender(Gender.MALE);
        user.setDob("2001-01-04");
        user.setPhone(List.of("123456789", "3249485950"));
        usersResponse = userService.register(user);
        assertNotNull(usersResponse);
    }

    @Test
    public void testToCreateANewTenant() {
        TenantRequest tenantRequest = new TenantRequest();
        tenantRequest.setUserId(usersResponse.getUserId());
        tenantRequest.setFirstName("John");
        tenantRequest.setLastName("Doe");
        tenantRequest.setEmail("john@doe.com");
        tenantRequest.setGender(Gender.MALE);
        tenantRequest.setNationalId("12345678911");
        tenantRequest.setIncome(Salary.MONTHLY);
        tenantRequest.setCurrentAddress("2B, Akinsola Street");
        tenantRequest.setPhoneNumber(List.of("09012345678","08012345678"));
        tenantRequest.setPreferredContact("09012345678");
        tenantRequest.setSearchingForHome(true);
        tenantRequest.setCurrentEmployer("Water Co-operation Limited");
        tenantRequest.setEmploymentLength(5);
        tenantRequest.setJobTitle(JobTitle.MANAGER);
        tenantRequest.setHasPet(true);
        tenantRequest.setAcknowledged(true);

        TenantResponse savedTenant = tenantService.createTenant(tenantRequest);
        assertEquals(1, tenantRepo.count());
        assertEquals("John", savedTenant.getFirstName());
        assertEquals("male", savedTenant.getGender().toString());


    }


}