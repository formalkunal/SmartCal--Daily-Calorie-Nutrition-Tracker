import java.util.List;

public class GenericPrinter {
    public static <T> void printList(List<T> items) {
        for (T i : items) {
            System.out.println(i);
        }
    }
}
