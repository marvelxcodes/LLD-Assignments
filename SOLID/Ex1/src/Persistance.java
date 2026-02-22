import java.util.*;

public interface Persistance {
    public void save(StudentRecord record);
    public int count();
    public List<StudentRecord> all();
}


