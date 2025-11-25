import util.exceptions.SmartCalException;

public class Validator {
    public static void requireNonEmpty(String s, String field) throws SmartCalException {
        if (s == null || s.trim().isEmpty()) {
            throw new SmartCalException(field + " cannot be empty");
        }
    }

    public static void requirePositive(double val, String field) throws SmartCalException {
        if (val <= 0) throw new SmartCalException(field + " must be positive");
    }
}
