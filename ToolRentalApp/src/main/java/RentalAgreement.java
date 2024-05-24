import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
  Generates a RentalAgreement based on Tool type and duration of tool rental.
 */
public class RentalAgreement {
    private final Tool tool;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final int chargedDays;
    private final double preDiscountCharge;
    private final int discountPercentage;
    private final BigDecimal discountAmount;
    private final BigDecimal finalCharge;

    public RentalAgreement(
            Tool tool,
            int rentalDays,
            LocalDate checkoutDate,
            LocalDate dueDate,
            int chargedDays,
            double preDiscountCharge,
            int discountPercentage,
            BigDecimal discountAmount,
            BigDecimal finalCharge
    ) {
        this.tool = tool;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.chargedDays = chargedDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercentage = discountPercentage;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public Tool getTool() {
        return tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public int getChargedDays() {
        return chargedDays;
    }

    public double getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    @Override
    public String toString() {
        DecimalFormat currencyFormatter = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormatter = new DecimalFormat("#%");
        String finalPercentageAmountFormatted = percentFormatter.format((double) discountPercentage / 100);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        StringBuilder rentalAgreementBuilder = new StringBuilder();
        rentalAgreementBuilder.append("Tool code: ").append(tool.getCode()).append("\n");
        rentalAgreementBuilder.append("Tool type: ").append(tool.getToolType()).append("\n");
        rentalAgreementBuilder.append("Tool brand: ").append(tool.getBrand()).append("\n");
        rentalAgreementBuilder.append("Rental days: ").append(rentalDays).append("\n");
        rentalAgreementBuilder.append("Check out date: ").append(checkoutDate.format(dateTimeFormatter)).append("\n");
        rentalAgreementBuilder.append("Due date: ").append(dueDate.format(dateTimeFormatter)).append("\n");
        rentalAgreementBuilder.append("Daily rental charge: ").append(currencyFormatter.format(tool.getDailyCharge())).append("\n");
        rentalAgreementBuilder.append("Charge days: ").append(chargedDays).append("\n");
        rentalAgreementBuilder.append("Pre-discount charge: ").append(currencyFormatter.format(preDiscountCharge)).append("\n");
        rentalAgreementBuilder.append("Discount percent: ").append(finalPercentageAmountFormatted).append("\n");
        rentalAgreementBuilder.append("Discount amount: ").append(currencyFormatter.format(discountAmount)).append("\n");
        rentalAgreementBuilder.append("Final charge: ").append(currencyFormatter.format(finalCharge)).append("\n");

        return rentalAgreementBuilder.toString();
    }
}
