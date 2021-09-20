import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ValidationClass {

    public int userInt;
    public String userString;
    public boolean isValid;


    public void validateIntInput(ValidationClass exceptionClass) throws InputMismatchException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an integer!");

        try {
            this.userInt = sc.nextInt();
            this.isValid = true;
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("You have entered an invalid input. Please Enter a valid integer!");
            this.userInt = 0;
            this.isValid = false;
        }


    }

    public int askIntInput() throws IOException, InputMismatchException {
        Scanner sc = new Scanner(System.in);
        boolean isTrue = false;
        try {
            this.userInt = sc.nextInt();
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("You have entered an invalid input. Please Enter a valid options as integer!");
            while (!isTrue) {
                this.validateIntInput(this);
                isTrue = this.isValid;
            }
        }
        return this.userInt;
    }

}
