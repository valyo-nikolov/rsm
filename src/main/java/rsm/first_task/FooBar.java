package rsm.first_task;

import java.util.*;
import java.util.stream.Collectors;

public class FooBar {

    public void fooBar(String input) {
        List<String> strings = Arrays.stream(input.split(",")).toList();
        try {
            Integer[] intsArray = strings.stream()
                    .map(Integer::valueOf)
                    .toArray(Integer[]::new);

            List<Integer> duplicatedIntsList = getDuplicatedNumbers(intsArray);

            Map<Integer, Boolean> dupFlagsMap = new HashMap<>(duplicatedIntsList.size());
            for (Integer i : duplicatedIntsList) dupFlagsMap.put(i, false);


            int[] finalI = new int[1];
            for (int i = 0; i < intsArray.length; i++) {
                finalI[0] = i;

                if (duplicatedIntsList.stream().noneMatch(integer -> integer == intsArray[finalI[0]].intValue()) ||
                        (duplicatedIntsList.stream().anyMatch(integer -> integer == intsArray[finalI[0]].intValue())
                                && !dupFlagsMap.get(intsArray[finalI[0]]))) {
                    if(intsArray[finalI[0]] % 3 == 0 && intsArray[finalI[0]] % 5 == 0 ) {
                        System.out.print("foobar");
                    } else if (intsArray[finalI[0]] % 3 == 0) {
                        System.out.print("foo");
                    } else if (intsArray[finalI[0]] % 5 == 0) {
                        System.out.print("bar");
                    } else {
                        System.out.print(intsArray[finalI[0]].intValue());
                    }
                } else {
                    if(intsArray[finalI[0]] % 3 == 0 && intsArray[finalI[0]] % 5 == 0 ) {
                        System.out.print("foobar-copy");
                    } else if (intsArray[finalI[0]] % 3 == 0) {
                        System.out.print("foo-copy");
                    } else if (intsArray[finalI[0]] % 5 == 0) {
                        System.out.print("bar-copy");
                    } else {
                        System.out.print(intsArray[finalI[0]] + "-copy");
                    }
                }
                if (finalI[0] != intsArray.length-1) {
                    System.out.print(",");
                }

                duplicatedIntsList.forEach(duplicate -> {
                    if(intsArray[finalI[0]].intValue() == duplicate && !dupFlagsMap.get(duplicate)) {
                        dupFlagsMap.put(duplicate, true);
                    }
                });
            }
            System.out.println();
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer input");
        }
    }

    private List<Integer> getDuplicatedNumbers(Integer[] intsArray) {
        List<Integer> integerList = Arrays.stream(intsArray).toList();
        Set<Integer> integerSet = new HashSet<>();
        return integerList.stream()
                .filter(integer -> !integerSet.add(integer))
                .collect(Collectors.toList());
    }
}
