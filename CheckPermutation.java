/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 */

// Hints
// #84 There is one solution that is O(N log N) time. Another solution uses some space, but is O(N) time.
// #122 Could a hash table be useful?

import java.util.Arrays;

public class CheckPermutation {
    // My first solution
    static boolean checkPermutation0(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        System.out.println(chars1);
        System.out.println(chars2);
        if (Arrays.equals(chars1, chars2)) {
            return true;
        } else {
            return false;
        }
    }

    // Solution 1
    static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    static boolean permutation1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        return sort(s).equals(sort(t));
    }

    // Solution 2 - check if arrays have identical character counts
    static boolean permutation2(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] letters = new int[128]; // Assuming ASCII
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }

        for (int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)]--;
            if (letters[t.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(permutation2(args[0], args[1]));
    }
}
