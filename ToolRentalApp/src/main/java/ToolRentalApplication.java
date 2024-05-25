import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Requirements:
 * - Written in Java
 * - When a customer checks out, a rental agreement is produced
 * - Store charges daily rental fees per tool, and fees differ per tool
 *    - Some tools are free of charge on weekends and/or holidays
 * - Clerks may give a customer a discount that is applied to the total
 *   daily charge
 * - Tool:
 *    - Code
 *    - Type
 *    - Brand
 *    - Daily charge
 *    - Weekday charge (boolean)
 *    - Weekend charge (boolean)
 *    - Holiday charge (boolean)
 * - Holidays:
 *    - Independence Day July 4
 *    - Labor Day First Monday in September
 * - Checkout Requirements:
 *    - Tool code
 *    - Rental day count (days customer wants to rent tool)
 *       - Throw user-friendly exception if not greater than 1
 *    - Discount percentage (whole number)
 *       - Throw user-friendly exception if not in the range 0 to 100
 *    - Checkout date
 *    - Generates a Rental Agreement
 *       - Details covered in RentalAgreement.java class
 *
 *  Out of Scope:
 *     - Graphical User Interface
 *     - Persistent database of tools/inventory system
 *     - Retry system for invalid clerk input
 *
 */

public class ToolRentalApplication {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            Tool.Code toolCode = null;
            LocalDate checkoutDate = null;
            int rentalDays = -1;
            int discountPercentage = -1;
            toolCode = UserInputUtil.getToolCode(scanner);
            checkoutDate = UserInputUtil.getCheckoutDate(scanner);
            rentalDays = UserInputUtil.getNumberOfRentalDays(scanner);
            discountPercentage = UserInputUtil.getDiscountPercentage(scanner);

            if (toolCode != null && rentalDays != -1 && discountPercentage != -1) {
                RentalAgreement rentalAgreement = checkout(toolCode, checkoutDate, rentalDays, discountPercentage);
                System.out.println("\nRental Agreement:\n");
                System.out.println(rentalAgreement);
            } else {
                System.out.println("Invalid input for Rental Agreement");
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static RentalAgreement checkout(Tool.Code code, LocalDate checkoutDate, int rentalDayCount, int discountPercentage) throws Exception {
        Tool tool = ToolRepository.getToolByCode(code);
        LocalDate dueDate;

        if (rentalDayCount < 1) {
            throw new Exception("Rental days must be 1 or greater.");
        }

        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new Exception("Discount must be between 0 and 100.");
        }

        // Calculate due date
        dueDate = checkoutDate.plusDays(rentalDayCount);

        // Calculate chargeable days
        int chargeableDays = Tool.calculateChargeableDays(tool, checkoutDate, dueDate);

        double preDiscountCharge = chargeableDays * tool.getDailyCharge();

        double discountAmount = (preDiscountCharge * discountPercentage) / 100;
        BigDecimal roundedDiscountAmount = BigDecimal.valueOf(discountAmount).setScale(2, RoundingMode.HALF_UP);

        BigDecimal finalCharge = BigDecimal.valueOf(preDiscountCharge).subtract(roundedDiscountAmount);

        return new RentalAgreement(
                tool,
                rentalDayCount,
                checkoutDate,
                dueDate,
                chargeableDays,
                preDiscountCharge,
                discountPercentage,
                roundedDiscountAmount,
                finalCharge
        );
    }
}
