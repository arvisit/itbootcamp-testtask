package handler;

import java.util.List;

public class ValidationViolationResponse {
    private final List<Violation> violations;

    public ValidationViolationResponse(List<Violation> violations) {
        super();
        this.violations = violations;
    }

    public List<Violation> getViolations() {
        return violations;
    }

    public static class Violation {
        private final String fieldName;
        private final String message;

        public Violation(String fieldName, String message) {
            super();
            this.fieldName = fieldName;
            this.message = message;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getMessage() {
            return message;
        }
    }
}
