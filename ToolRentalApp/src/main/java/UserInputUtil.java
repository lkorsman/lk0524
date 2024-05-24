import java.time.LocalDate;
import java.util.Scanner;

/*
  Helper class to get input from clerk about tool rental.
 */
public class UserInputUtil {

    volatile String test = "hello";

    /**
     * Prompts user for input and returns a Tool.Code based on user input
     * @param scanner Scanner object to get keyboard input
     * @return Tool.Code based on user's input
     * @throws Exception if user input is not an integer
     */
    public static Tool.Code getToolCode(Scanner scanner) throws Exception {
        int toolCode;

        System.out.print("""
                Enter Tool Code\s
                1 (CHNS Chainsaw)\s
                2 (JAKD Jackhammer)\s
                3 (JAKR Jackhammer)\s
                4 (LADW Ladder)\s"""
        );
        System.out.print("(enter 1 through 4 and hit enter) -> ");

        if (scanner.hasNextInt()) {
            toolCode = scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new Exception("Invalid Tool Code: " + invalidInput);
        }

        System.out.println("Tool Code entered: " + toolCode);
        return getToolCodeByInt(toolCode);
    }

    /**
     * Prompts user for year, month, and day for a date
     * @param scanner Scanner object to get keyboard input
     * @return a LocalDate representing the user's date input
     * @throws Exception if user input is not an integer for year, month, or date
     */
    public static LocalDate getCheckoutDate(Scanner scanner) throws Exception {
        int year;
        int month;
        int day;
        LocalDate date;

        System.out.print("Enter Checkout Year (YYYY): ");
        if (scanner.hasNextInt()) {
            year = scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new Exception("Invalid Year: " + invalidInput);
        }

        System.out.print("Enter Checkout Month (1-12): ");
        if (scanner.hasNextInt()) {
            month = scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new Exception("Invalid Month: " + invalidInput);
        }

        System.out.print("Enter Checkout Day (1-31): ");
        if (scanner.hasNextInt()) {
            day = scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new Exception("Invalid Day: " + invalidInput);
        }

        date = LocalDate.of(year, month, day);
        System.out.println("Date entered: " + date);
        return date;
    }

    /**
     * Prompts a user for the number of rental days
     * @param scanner Scanner object to get keyboard input
     * @return the number of rental days given by a user
     * @throws Exception if user input is not an integer
     */
    public static int getNumberOfRentalDays(Scanner scanner) throws Exception {
        int rentalDays;

        System.out.print("Enter number of rental days: ");
        if (scanner.hasNextInt()) {
            rentalDays = scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new Exception("Invalid Rental Days: " + invalidInput);
        }

        return rentalDays;
    }

    /**
     * Prompts a user for a discount percentage
     * @param scanner Scanner object to get keyboard input
     * @return the discount percent as an integer given by a user
     * @throws Exception if the user input is not an integer
     */
    public static int getDiscountPercentage(Scanner scanner) throws Exception {
        int discountPercent;

        System.out.print("Enter discount percentage (0-100): ");
        if (scanner.hasNextInt()) {
            discountPercent = scanner.nextInt();
        } else {
            String invalidInput = scanner.next();
            throw new Exception("Invalid Discount: " + invalidInput);
        }

        return discountPercent;
    }

    /**
     * Helper function to convert user input for Tool Code to actual Tool.Code enum
     * @param value integer value (1-4) to map to a Tool.Code enum
     * @return Tool.Code
     */
    private static Tool.Code getToolCodeByInt(int value) {
        switch (value) {
            case 1 -> {
                return Tool.Code.CHNS;
            }
            case 2 -> {
                return Tool.Code.JAKD;
            }
            case 3 -> {
                return Tool.Code.JAKR;
            }
            case 4 -> {
                return Tool.Code.LADW;
            }
        }
        return null;
    }
}
