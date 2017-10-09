/**
 * Is Unique: Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */

// Hints
// #44 Try a hash table.
// #117 Could a bit vector be useful?
// #132 Can you solve it in O(N log N) time? What might a solution look like?

import java.util.ArrayList;

// My first solution
/*
public class IsUnique {
    static boolean isUnique(String testString) {
        ArrayList<Character> characters = new ArrayList<Character>();
        char currentCharacter;
        
        for (int i = 0; i < testString.length(); i++) {
            currentCharacter = testString.charAt(i);

            if (characters.contains(currentCharacter)) {
                return false;
            } else {
                characters.add(currentCharacter);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUnique("hello"));
        System.out.println(isUnique("hola"));
        System.out.println(isUnique("cheesebiscuit"));
        System.out.println(isUnique("abcdefg"));
    }
}
*/

// Solution one - assumes ASCII character set
/*
public class IsUnique {
   static boolean isUniqueChars(String str) {
        if (str.length() > 128) return false;

        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUniqueChars("hello"));
        System.out.println(isUniqueChars("hola"));
        System.out.println(isUniqueChars("cheesebiscuit"));
        System.out.println(isUniqueChars("abcdefg"));
    }
}
*/

// Solution using a bit vector to reduce space usage - assumes a-z character set
public class IsUnique {
   static boolean isUniqueChars(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUniqueChars("hello"));
        System.out.println(isUniqueChars("hola"));
        System.out.println(isUniqueChars("cheesebiscuit"));
        System.out.println(isUniqueChars("abcdefg"));
    }
}
