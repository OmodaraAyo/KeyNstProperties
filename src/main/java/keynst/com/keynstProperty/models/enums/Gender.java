package keynst.com.keynstProperty.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String gender;

    @Override
    public String toString() {
        return gender;
    }
}

