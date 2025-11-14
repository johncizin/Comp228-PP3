package apps;
import adts.*;

public class DLLTest {

    public static void main(String[] args) {
        ReverseOrderDLL<Integer> dll = new ReverseOrderDLL<>();
        dll.add(5);
        dll.add(3);
        dll.add(8);
        dll.add(1);
        System.out.println(dll); // Should print [8, 5, 3, 1]
    }

}
