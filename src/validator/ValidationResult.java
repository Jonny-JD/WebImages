package validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private final List<ValidationError> errors = new ArrayList<>();

    public void addError(ValidationError error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
