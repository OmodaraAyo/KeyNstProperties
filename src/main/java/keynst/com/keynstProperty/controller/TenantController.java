package keynst.com.keynstProperty.controller;

import keynst.com.keynstProperty.dtos.TenantDtos.TenantRequest;
import keynst.com.keynstProperty.dtos.TenantDtos.TenantResponse;
import keynst.com.keynstProperty.service.tenantservice.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping("/tenant")
    public TenantResponse createNewTenant(@RequestBody TenantRequest request){
        return tenantService.createTenant(request);
    }
}
