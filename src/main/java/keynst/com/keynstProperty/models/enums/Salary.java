package keynst.com.keynstProperty.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Salary {
    MONTHLY("monthly"),
    ANNUAL("annual");
    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
