
import java.util.Scanner;

public class SubstringFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the main string: ");
        String mainString = scanner.nextLine();

        System.out.print("Enter the substring to find: ");
        String substring = scanner.nextLine();

        int frequency = countSubstringFrequency(mainString, substring);
        System.out.println("The substring \"" + substring + "\" appears " + frequency + " times in the main string.");
    }

    public static int countSubstringFrequency(String mainString, String substring) {
        int count = 0;
        int index = 0;

        while ((index = mainString.indexOf(substring, index)) != -1) {
            count++;
            index += substring.length();
        }

        return count;
    }
}
