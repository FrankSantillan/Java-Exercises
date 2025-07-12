package main.java;

import java.lang.reflect.Array;
import java.net.StandardSocketOptions;
import java.util.*;
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


        //Non-unique characters
        Set<Character> nonUnique = findNonUniqueCharacters(original);
        System.out.println("Non-unique characters: " + nonUnique);

        System.out.println(isAnagramBySort("Listen", "Silent"));          // true
        System.out.println(isAnagramBySort("Dormitory", "Dirty room"));   // true
        System.out.println(isAnagramBySort("Hello", "Olelh "));           // true
        System.out.println(isAnagramBySort("Hello", "World"));            // false


        int[] numArr = {0, 1, 0, 3, 12};
        moveZerosToEnd(numArr);
        System.out.println(Arrays.toString(numArr)); // [1, 3, 12, 0, 0]


        identifyRepeatWord();
        deleteRepetedWord();

        int num = 3; // You can change this input
        System.out.println(bracketCombinations(num)); // Output: 5

        findMaxValue();
        findMaxValueStream();

        String[] names = {"Jay", "Ray", "Jay", "May", "Kay","Nay","Nay"};
        System.out.println("Unique Names in Array"+Arrays.toString(Arrays.stream(names).distinct().toArray(String[]::new)));


        System.out.println("Delete Repeated word from Array"+Arrays.toString(Arrays.stream(names).filter(word -> Arrays.stream(names).filter(word::equals).count() == 1).toArray(String[]::new)));

        int[] nums = {3, 7, 0, 0, 2, 9, 5, 8, 0, 7};
        System.out.println("Move Zeros to End"+Arrays.toString(
                Arrays.stream(nums)
                        .boxed()  // Convert to Integer stream for better sorting control
                        .sorted((a, b) -> {
                            if (a == 0 && b != 0) return 1;    // Move zero to end
                            else if (a != 0 && b == 0) return -1; // Keep non-zero first
                            else return 0;                     // Maintain original order
                        })
                        .mapToInt(Integer::intValue)  // Convert back to primitive
                        .toArray()
        ));
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
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
        static void moveZerosToEnd(int[] nums) {
            int index = 0; // pointer for non-zero elements

            // First, move all non-zero elements to the front
            for (int num : nums) {
                if (num != 0) {
                    nums[index++] = num;
                }
            }

            // Fill the remaining positions with zeros
            while (index < nums.length) {
                nums[index++] = 0;
            }
        }

    static void identifyRepeatWord() {
        String[] names = {"Jay", "Ray", "Jay", "May", "Kay","Nay","Nay"};
        Set<String> seen = new HashSet<>();
        Set<String> set = Arrays.stream(names).filter(val -> !seen.add(val)).collect(Collectors.toSet());
        System.out.println(Arrays.toString(names));
        System.out.println(seen);
        System.out.println(set);

        Map<String, Long> nameCounts = Arrays.stream(names)
                .collect(Collectors.groupingBy(
                        name -> name,
                        Collectors.counting()
                ));
        List<String> duplicates = Arrays.stream(names)
                .filter(name -> nameCounts.get(name) > 1)
                .collect(Collectors.toList());

        List<String> uniques = Arrays.stream(names)
                .filter(name -> nameCounts.get(name) == 1)
                .collect(Collectors.toList());

        List<String> finalResult = new ArrayList<>();
        finalResult.addAll(duplicates);
        finalResult.addAll(uniques);

        System.out.println("Duplicated Names First"+Arrays.toString(finalResult.toString().toCharArray()));

    }

    static void deleteRepetedWord(){
        String[] names = {"Jay", "Ray", "Jay", "May", "Kay", "Nay"};

        Map<String, Integer> countMap = new HashMap<>();
        for (String name : names) {
            countMap.put(name, countMap.getOrDefault(name, 0) + 1);
        }

        List<String> uniqueNames = new ArrayList<>();
        for (String name : names) {
            if (countMap.get(name) == 1) {
                uniqueNames.add(name);
            }
        }

        System.out.println(uniqueNames);
    }

        public static boolean isAnagramBySort(String s1, String s2) {
            // Remove spaces; make case‑insensitive
            String a = s1.replaceAll("\\s+", "").toLowerCase();
            String b = s2.replaceAll("\\s+", "").toLowerCase();

            if (a.length() != b.length()) return false;

            char[] arr1 = a.toCharArray();
            char[] arr2 = b.toCharArray();
            Arrays.sort(arr1);
            Arrays.sort(arr2);

            return Arrays.equals(arr1, arr2);
        }

    public static int bracketCombinations(int num) {
        return catalan(num);
    }
    public static int catalan(int n) {
        return factorial(2 * n) / (factorial(n) * factorial(n + 1));
    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void  findMaxValue(){
        int[] arr = {3, 7, 2, 9, 5};
        int maxValue = arr[0];
        for(int i=0; i<=arr.length-1; i++){
            if (arr[i] > maxValue){
                maxValue = arr[i];
            }
        }
        System.out.println("The max value is: "+maxValue);
    }

    public static void  findMaxValueStream() {
        int[] arr = {3, 7, 2, 9, 5};
        int max = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
        int min = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
        System.out.println("Array Natural Ordered :"+Arrays.stream(arr).boxed().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));
        System.out.println("Array Reverse Ordered :"+Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
        System.out.println("The min value is: " + min);
        System.out.println("The max value is: " + max);
    }
}
