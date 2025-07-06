package main.java;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java in IntelliJ!");
        System.out.println("Success! Working at " + System.getProperty("java.version"));
        reverseMyName("Hello Java in IntelliJ!");

        String original1 = "Hello Java in IntelliJ!";
        String original = "Hello";

        //Java Stream 8
        String reversed_stream = IntStream.rangeClosed(1, original.length())
                .mapToObj(i -> String.valueOf(original.charAt(original.length() - i)))
                .collect(Collectors.joining());

        System.out.println("Reversed Stream: " + reversed_stream);

        //Reversed String Builder
        String reversed = new StringBuilder(original).reverse().toString();
        System.out.println("Reversed string builder: " + reversed);

        Set<Character> nonUnique = findNonUniqueCharacters(original);
        System.out.println("Non-unique characters: " + nonUnique);
    }

    public static void reverseMyName(String inputName){
        String reversedLegacy = "";
        for (int i = inputName.length() - 1; i >= 0; i--) {
            reversedLegacy += inputName.charAt(i);
        }
        System.out.println(reversedLegacy);

    }

    public static Set<Character> findNonUniqueCharacters(String input) {
        return input.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}