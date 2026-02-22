import java.util.*;

public class OnboardingService {
    private final DatabasePersistance db;

    public OnboardingService(DatabasePersistance db) {
      this.db = db;
    }

    public void registerFromRawInput(String raw) {
        StudentSerializer serializer = new StudentSerializer();

        StudentRecord record = serializer.serialize(raw);
        record.id = IdUtil.generateStudentId(db.count());

        db.save(record);

        System.out.println("OK: created student " + record.id);
        System.out.println("Saved. Total students: " + db.count());
        System.out.println("CONFIRMATION:");
        System.out.println(record);
    }
}
