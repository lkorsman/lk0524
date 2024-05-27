import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import static junit.framework.TestCase.assertEquals;

public class RentalAgreementTest {

    @Test
    public void testRentalAgreementToString() {
        // Given
        Tool tool = ToolRepository.getToolByCode(Tool.Code.JAKR);
        LocalDate checkoutDate = LocalDate.of(2020, 1, 1);
        LocalDate dueDate = LocalDate.of(2020, 1, 2);
        BigDecimal discountAmount = BigDecimal.valueOf(0);
        BigDecimal finalAmount = BigDecimal.valueOf(0);
        String expectedRentalAgreement = "Tool code: JAKR\n" +
                "Tool type: Jackhammer\n" +
                "Tool brand: Ridgid\n" +
                "Rental days: 1\n" +
                "Check out date: 01/01/20\n" +
                "Due date: 01/02/20\n" +
                "Daily rental charge: $2.99\n" +
                "Charge days: 0\n" +
                "Pre-discount charge: $2.99\n" +
                "Discount percent: 0%\n" +
                "Discount amount: $0.00\n" +
                "Final charge: $0.00\n";

        RentalAgreement rentalAgreement = new RentalAgreement(
                                            tool,
                                            1,
                                            checkoutDate,
                                            dueDate,
                                            0,
                                            BigDecimal.valueOf(2.99),
                                            0,
                                            discountAmount,
                                            finalAmount
                                          );

        // When
        String rentalAgreementResult = rentalAgreement.toString();

        // Then
        assertEquals(expectedRentalAgreement, rentalAgreementResult);
    }
}
