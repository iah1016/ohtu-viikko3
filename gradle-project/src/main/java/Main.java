import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        final int three = 3;
        Scanner scanner = new Scanner(System.in);
        Multiplier threeMultiplier = new Multiplier(three);

        System.out.println("Gi' 's a number:");
        int number = scanner.nextInt();
        System.out.println(number + " times three equals "  + threeMultiplier.multipliedBy(number));

        /*for( int i=0; i<1; i++ ) {
            for( int j=0; i<j; j++ ) {
                System.out.println("virhe");
            }
        }*/
    }
}
