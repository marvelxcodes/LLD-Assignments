import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Student Onboarding ===");
        DatabasePersistance db = new DatabasePersistance();
        OnboardingService svc = new OnboardingService(db);

        String raw = "name=Riya;email=riya@sst.edu;phone=9876543210;program=CSE";
        svc.registerFromRawInput(raw);

        System.out.println();
        System.out.println("-- DB DUMP --");
        
        List<StudentRecord> studentRecords = db.all();
        System.out.print(Formatter.renderStudents(studentRecords));
    }
}
