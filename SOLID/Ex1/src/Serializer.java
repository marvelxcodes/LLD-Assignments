public interface Serializer<T> {
  T serialize(String data);
  String deserialize(T data);
}

