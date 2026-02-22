import java.util.*;

public interface Validator<T> {
  List<String> validate(T record);
}

