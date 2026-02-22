import java.util.*;

public class DatabasePersistance implements Persistance {
    private final List<StudentRecord> rows = new ArrayList<>();

    public void save(StudentRecord record) {
      rows.add(record);
    }

    public int count() {
      return rows.size();
    }

    public List<StudentRecord> all() {
      return Collections.unmodifiableList(rows);
    }
}
