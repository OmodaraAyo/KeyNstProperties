package keynst.com.keynstProperty.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobTitle {
    CEO("Chief Executive Officer"),
    PRESIDENT("President"),
    MANAGER("Manager"),
    DIRECTOR("Director"),
    ENGINEER("ENGINEER"),
    ANALYST("Analyst"),
    ASSOCIATE("Associate"),
    COORDINATOR("Coordinator"),
    ADMINISTRATOR("Administrator"),
    SPECIALIST("Specialist"),
    CONSULTANT("Consultant"),
    ASSISTANT("Assistant"),
    EXECUTIVE("Executive"),
    SUPERVISION("Supervisor"),
    CLERK("Clerk"),
    TECHNICIAN("Technician"),
    MARKETER("Marketer"),
    SALES_REPRESENTATIVE("Sales Representative"),
    CUSTOMER_SERVICE("Customer Service");

    private final String title;
}
