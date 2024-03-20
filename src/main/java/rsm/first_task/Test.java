package rsm.first_task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        List<Integer> list = List.of(5, 11, 17);

        Set<Integer> duplicates = new HashSet<>();

        Integer duplicatedFirstInt =
                list.stream()
                        .filter(integer -> !duplicates.add(integer))
                        .findFirst().get();

        System.out.println("First duplicated occurrence is: " + duplicatedFirstInt);
    }
}
