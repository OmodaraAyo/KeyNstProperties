package keynst.com.keynstProperty.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    GUEST("guest"),
    USERS("user");

    private final String role;

    @Override
    public String toString() {
        return role;
    }
}
