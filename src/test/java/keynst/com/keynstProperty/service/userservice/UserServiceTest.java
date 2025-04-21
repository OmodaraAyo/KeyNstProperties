package keynst.com.keynstProperty.service.userservice;


import keynst.com.keynstProperty.KeynstPropertyApplication;
import keynst.com.keynstProperty.dtos.LoginDtos.LoginRequest;
import keynst.com.keynstProperty.dtos.LoginDtos.LoginResponse;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersRequest;
import keynst.com.keynstProperty.dtos.UsersDtos.UsersResponse;
import keynst.com.keynstProperty.models.enums.Gender;
import keynst.com.keynstProperty.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = KeynstPropertyApplication.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserServiceImpl userService;

    private UsersResponse savedUser;

    @BeforeEach
    public void startAllWithThis() {
        userRepo.deleteAll();
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeJohnny");
        usersRequest.setEmail("john.doe@gmail.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("123456789", "3249485950"));
        savedUser = userService.register(usersRequest);
        assertEquals( "DoeJohnny", savedUser.getUsername());
    }

    @Test
    public void testToRegisterANewUser() {
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfFirstNameFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeJohnny");
        usersRequest.setEmail("john.doe@gmail.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("123456789"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "First name is required");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfLastNameFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("");
        usersRequest.setUsername("DoeJohnny");
        usersRequest.setEmail("john.doe@gmail.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("1234567", "987657"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Last name is required");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfUsernameFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("");
        usersRequest.setEmail("john.doe@gmail.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("123456789", "98765705959"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Username is required");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfEmailFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeDeo");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setEmail("");
        usersRequest.setPassword("password");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("123456789", "98765705959"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Email is required");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfEmailIsNotValidAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeDeo");
        usersRequest.setEmail("john.doe.com");
        usersRequest.setPassword("password");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("123478494", "98765705959"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Invalid email format");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfPasswordFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeDeo");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setEmail("johndoe@yahoo.com");
        usersRequest.setPassword("");
        usersRequest.setDob("2001-01-01");
        usersRequest.setPhone(List.of("123478494", "98765705959"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Password is required");
        assertEquals(1, userRepo.count());
    }
    @Test
    public void testThatIfPhoneNumberFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeDeo");
        usersRequest.setEmail("johndoe@yahoo.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setPhone(new ArrayList<>());
        usersRequest.setDob("2001-01-01");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "At least one phone number must be provided");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfDobFieldIsEmptyAnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeDeo");
        usersRequest.setEmail("john.doe@yahoo.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setDob("");
        usersRequest.setPhone(List.of("123478494", "98765705959"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Date of birth is required");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatIfDobFieldDoesNotMatchPattern_YYYY_MM_DD_AnExceptionIsThrown() {
        assertEquals(1, userRepo.count());
        UsersRequest usersRequest = new UsersRequest();
        usersRequest.setFirstName("John");
        usersRequest.setLastName("Doe");
        usersRequest.setUsername("DoeDeo");
        usersRequest.setEmail("john.doe@yahoo.com");
        usersRequest.setGender(Gender.MALE);
        usersRequest.setPassword("password");
        usersRequest.setDob("21-05-2000");
        usersRequest.setPhone(List.of("123478494", "98765705959"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.register(usersRequest));
        assertEquals(exception.getMessage(), "Invalid date of birth");
        assertEquals(1, userRepo.count());
    }

    @Test
    public void testThatARegisteredUserCanSignInUsingUserNameAndPassword(){
        LoginRequest user = new LoginRequest();
        user.setUsername("DoeJohnny");
        user.setPassword("password");

        LoginResponse currentUser = userService.verifyLogin(user);
        assertEquals("Login Successful", currentUser.getResponse());
        assertTrue(currentUser.getIsLoggedIn());
//        System.out.println(currentUser.getToken());
    }

    @Test
    public void testThatAnErrorResponseIsReturnedIfUserNameAndPasswordDoesNotMatch(){
        LoginRequest user = new LoginRequest();
        user.setUsername("DoeJohnny");
        user.setPassword("password2345");
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> userService.verifyLogin(user));
        assertEquals("Bad credentials: Invalid username or password", exception.getMessage());
    }

    @Test
    public void testToFindAnExistingUserById(){
        UsersResponse foundId = userService.findById(savedUser.getUserId());
        assertEquals("John", foundId.getFirstName());
        assertEquals("Doe", foundId.getLastName());
        assertEquals("john.doe@gmail.com", foundId.getEmail());
    }
    @Test
    public void testThatAnExceptionIsThrownIfIdDoesNotExist(){
        RuntimeException exception = assertThrows(RuntimeException.class, ()-> userService.findById(0L));
        assertEquals(exception.getMessage(), "User not found");
    }
}