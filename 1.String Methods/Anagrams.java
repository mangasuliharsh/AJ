
import java.util.*;
class Anagrams{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word1 = sc.nextLine();
        String word2 = sc.nextLine();

        word1.toLowerCase();
        word2.toLowerCase();

        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        if(Arrays.equals(arr1, arr2))
            System.out.println("Is a Anagram");
        else
            System.out.println("Not a Anagram");

    }
}
