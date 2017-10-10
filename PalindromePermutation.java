/**
 * Given a string write a function to check if it is a permutation of a palindrome.
 * A palindrome is a word or phrase that is the same forwards and backwards.
 * A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.
 */

import java.util.Arrays;

public class PalindromePermutation {
    // My first solution
    static boolean palindromePermutation(String str) {
        int[] letters = new int[128]; // Assuming ASCII

        // First loop counts letters
        for (int i = 0; i < str.length(); i++) {
            letters[str.charAt(i)]++;
        }

        // Second loop checks for palindrome
        // If there's more than one odd count the word is not a palindrome
        int oddCounts = 0;
        for (int i = 0; i < str.length(); i++) {
            if (letters[str.charAt(i)] % 2 != 0) {
                oddCounts++;
                if (oddCounts > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // Solution 1
    static boolean solution1(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    // Check that no more than one character has an odd count
    static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    // Map each character to a number.
    // This is case insensitive. Non-letter characters map to -1
    static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    // Count how many times each character appears
    static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

    // Solution 2
    static boolean solution2(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];

        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }
    
    // Solution 3
    static boolean solution3(String phrase) {
        int bitVector = createBitVector(phrase);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    // Create a bit vector for the string. For each letter with value i, toggle the ith bit.
    static int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    // Toggle the ith bit in the integer
    static int toggle(int bitVector, int index) {
        if (index < 0) return bitVector;

        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    static boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }


    public static void main(String[] args) {
        System.out.println(solution3(args[0]));
    }
}
