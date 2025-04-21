package keynst.com.keynstProperty.service.tenantservice;

import keynst.com.keynstProperty.dtos.TenantDtos.TenantRequest;
import keynst.com.keynstProperty.dtos.TenantDtos.TenantResponse;


public interface TenantService {
    TenantResponse createTenant(TenantRequest request);
}
