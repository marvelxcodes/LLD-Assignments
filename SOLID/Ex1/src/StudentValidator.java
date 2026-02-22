import java.util.*;

public class StudentValidator implements Validator<StudentRecord> {
  public List<String> validate(StudentRecord record) {
    List<String> errors = new ArrayList<>();
    if (record.name.isBlank()) errors.add("name is required");
    if (record.email.isBlank() || !record.email.contains("@")) errors.add("email is invalid");
    if (record.phone.isBlank() || !record.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
    if (!(record.program.equals("CSE") || record.program.equals("AI") || record.program.equals("SWE"))) errors.add("program is invalid");

    if (!errors.isEmpty()) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) System.out.println("- " + e);
        return new ArrayList<>();
    }

    return errors;
  }
}
