package keynst.com.keynstProperty.service.tenantservice;

import keynst.com.keynstProperty.dtos.TenantDtos.TenantRequest;
import keynst.com.keynstProperty.dtos.TenantDtos.TenantResponse;
import keynst.com.keynstProperty.models.Tenant.Tenant;
import keynst.com.keynstProperty.models.usersmodel.Users;
import keynst.com.keynstProperty.repository.TenantRepo;
import keynst.com.keynstProperty.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepo tenantRepo;
    @Autowired
    private UserRepo userRepo;

    @Override
    public TenantResponse createTenant(TenantRequest request) {
        Tenant savedTenant = saveNewTenant(request);
        return getTenantResponse(savedTenant);
    }

    private Tenant saveNewTenant(TenantRequest request) {
        Tenant tenant = new Tenant();
        Users user = userRepo.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("User not found with id: "+ request.getUserId()));
        tenant.setUser(user);
        tenant.setFirstName(request.getFirstName());
        tenant.setLastName(request.getLastName());
        tenant.setGender(request.getGender());
        tenant.setNationalId(request.getNationalId());
        tenant.setEmail(request.getEmail());
        tenant.setCurrentAddress(request.getCurrentAddress());
        tenant.setPhoneNumber(request.getPhoneNumber());
        tenant.setPreferredContact(request.getPreferredContact());
        tenant.setHasPet(request.isHasPet());
        tenant.setJobTitle(request.getJobTitle());
        tenant.setCurrentEmployer(request.getCurrentEmployer());
        tenant.setEmploymentLength(request.getEmploymentLength());
        tenant.setIncome(request.getIncome());
        tenant.setSearchingForHome(request.isSearchingForHome());
        tenant.setAcknowledged(request.isAcknowledged());
        tenant.setActive(true);
        tenant.setDateCreated(getCurrentTime());
        return tenantRepo.save(tenant);
    }

    private String getCurrentTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private TenantResponse getTenantResponse(Tenant savedTenant){
        TenantResponse tenantResponse = new TenantResponse();
        tenantResponse.setTenantId(savedTenant.getTenantId());
        tenantResponse.setUserId(savedTenant.getUser().getUserId());
        tenantResponse.setFirstName(savedTenant.getFirstName());
        tenantResponse.setLastName(savedTenant.getLastName());
        tenantResponse.setGender(savedTenant.getGender());
        tenantResponse.setNationalId(savedTenant.getNationalId());
        tenantResponse.setEmail(savedTenant.getEmail());
        tenantResponse.setCurrentAddress(savedTenant.getCurrentAddress());
        tenantResponse.setPhoneNumber(savedTenant.getPhoneNumber());
        tenantResponse.setPreferredContact(savedTenant.getPreferredContact());
        tenantResponse.setHasPet(savedTenant.isHasPet());
        tenantResponse.setJobTitle(savedTenant.getJobTitle());
        tenantResponse.setCurrentEmployer(savedTenant.getCurrentEmployer());
        tenantResponse.setEmploymentLength(savedTenant.getEmploymentLength());
        tenantResponse.setIncome(savedTenant.getIncome());
        tenantResponse.setSearchingForHome(savedTenant.isSearchingForHome());
        tenantResponse.setAcknowledged(savedTenant.isAcknowledged());
        tenantResponse.setActive(savedTenant.isActive());
        tenantResponse.setDateCreated(savedTenant.getDateCreated());
        return tenantResponse;
    }

    private void setDataReqAndRes(){

    }

}
