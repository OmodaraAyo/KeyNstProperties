package keynst.com.keynstProperty.repository;

import keynst.com.keynstProperty.models.Tenant.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface TenantRepo extends JpaRepository<Tenant, Long> {

}
