import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestThread {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Geek","Freak","Preak", "GGeassy","Monte Payton", "Freeeak");

        Predicate<String> check = (s) -> s.startsWith("F");

        for(String name: names) {
            if(check.test(name)) System.out.println(name);
        }


    }
}
