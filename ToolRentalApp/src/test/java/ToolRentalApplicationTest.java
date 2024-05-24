import org.junit.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ToolRentalApplicationTest {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");

    @Test(expected = Exception.class)
    public void testCheckoutWithInvalidRentalDays() throws Exception {
        // When
        ToolRentalApplication.checkout(Tool.Code.JAKR, LocalDate.of(2015, 9, 3), -5, 10);
    }

    @Test(expected = Exception.class)
    public void testCheckoutWithInvalidDiscountPercent() throws Exception {
        // When
        ToolRentalApplication.checkout(Tool.Code.JAKR, LocalDate.of(2015, 9, 3), 5, 101);
    }

    @Test(expected = Exception.class)
    public void testCheckoutWithInvalidTool() throws Exception {
        // When
        ToolRentalApplication.checkout(null, LocalDate.of(2015, 9, 3), 5, 101);
    }

    @Test(expected = Exception.class)
    public void testInvalidCheckoutWithInvalidDiscountAboveOneHundredPercent() throws Exception {
        // When
        RentalAgreement agreement = ToolRentalApplication.checkout(Tool.Code.JAKR, LocalDate.of(2015, 9, 3), 5, 101);

        // Then
        assertNotNull(agreement);
        assertEquals(Tool.Code.JAKR, agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getToolType());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals("09/03/15", agreement.getCheckoutDate().format(dateTimeFormatter));
        assertEquals("09/08/15", agreement.getDueDate().format(dateTimeFormatter));
        assertEquals(2.99, agreement.getTool().getDailyCharge(), 0);
        assertEquals(2, agreement.getChargedDays());
        assertEquals(5.98, agreement.getPreDiscountCharge(), 0);
        assertEquals(0, agreement.getDiscountPercentage());
        assertEquals(0, agreement.getDiscountAmount().doubleValue(), 0);
        assertEquals(5.98, agreement.getFinalCharge().doubleValue(), 0);
    }

    @Test
    public void testValidCheckoutWithLadderOverFourthOfJuly() throws Exception {
        // When
        RentalAgreement agreement = ToolRentalApplication.checkout(Tool.Code.LADW, LocalDate.of(2020, 7, 2), 3, 10);

        // Then
        assertNotNull(agreement);
        assertEquals(Tool.Code.LADW, agreement.getTool().getCode());
        assertEquals("Ladder", agreement.getTool().getToolType());
        assertEquals("Werner", agreement.getTool().getBrand());
        assertEquals(3, agreement.getRentalDays());
        assertEquals("07/02/20", agreement.getCheckoutDate().format(dateTimeFormatter));
        assertEquals("07/05/20", agreement.getDueDate().format(dateTimeFormatter));
        assertEquals(1.99, agreement.getTool().getDailyCharge(), 0);
        assertEquals(2, agreement.getChargedDays());
        assertEquals(3.98, agreement.getPreDiscountCharge(), 0);
        assertEquals(10, agreement.getDiscountPercentage());
        assertEquals(0.40, agreement.getDiscountAmount().doubleValue(), 0);
        assertEquals(3.58, agreement.getFinalCharge().doubleValue(), 0);
    }

    @Test
    public void testValidCheckoutWithChainsawOverFourthOfJuly() throws Exception {
        // When
        RentalAgreement agreement = ToolRentalApplication.checkout(Tool.Code.CHNS, LocalDate.of(2015, 7, 2), 5, 25);

        // Then
        assertNotNull(agreement);
        assertEquals(Tool.Code.CHNS, agreement.getTool().getCode());
        assertEquals("Chainsaw", agreement.getTool().getToolType());
        assertEquals("Stihl", agreement.getTool().getBrand());
        assertEquals(5, agreement.getRentalDays());
        assertEquals("07/02/15", agreement.getCheckoutDate().format(dateTimeFormatter));
        assertEquals("07/07/15", agreement.getDueDate().format(dateTimeFormatter));
        assertEquals(1.49, agreement.getTool().getDailyCharge(), 0);
        assertEquals(3, agreement.getChargedDays());
        assertEquals(4.47, agreement.getPreDiscountCharge(), 0);
        assertEquals(25, agreement.getDiscountPercentage());
        assertEquals(1.12, agreement.getDiscountAmount().doubleValue(), 0);
        assertEquals(3.35, agreement.getFinalCharge().doubleValue(), 0);
    }

    @Test
    public void testValidCheckoutWithJackhammerOverLaborDay() throws Exception {
        // When
        RentalAgreement agreement = ToolRentalApplication.checkout(Tool.Code.JAKD, LocalDate.of(2015, 9, 3), 6, 0);

        // Then
        assertNotNull(agreement);
        assertEquals(Tool.Code.JAKD, agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getToolType());
        assertEquals("DeWalt", agreement.getTool().getBrand());
        assertEquals(6, agreement.getRentalDays());
        assertEquals("09/03/15", agreement.getCheckoutDate().format(dateTimeFormatter));
        assertEquals("09/09/15", agreement.getDueDate().format(dateTimeFormatter));
        assertEquals(2.99, agreement.getTool().getDailyCharge(), 0);
        assertEquals(3, agreement.getChargedDays());
        assertEquals(8.97, agreement.getPreDiscountCharge(), 0);
        assertEquals(0, agreement.getDiscountPercentage());
        assertEquals(0.0, agreement.getDiscountAmount().doubleValue(), 0);
        assertEquals(8.97, agreement.getFinalCharge().doubleValue(), 0);
    }

    @Test
    public void testValidCheckoutWithJackhammerOverFourthOfJuly() throws Exception {
        // When
        RentalAgreement agreement = ToolRentalApplication.checkout(Tool.Code.JAKR, LocalDate.of(2015, 7, 2), 9, 0);

        // Then
        assertNotNull(agreement);
        assertEquals(Tool.Code.JAKR, agreement.getTool().getCode());
        assertEquals("Jackhammer", agreement.getTool().getToolType());
        assertEquals("Ridgid", agreement.getTool().getBrand());
        assertEquals(9, agreement.getRentalDays());
        assertEquals("07/02/15", agreement.getCheckoutDate().format(dateTimeFormatter));
        assertEquals("07/11/15", agreement.getDueDate().format(dateTimeFormatter));
        assertEquals(2.99, agreement.getTool().getDailyCharge(), 0);
        assertEquals(6, agreement.getChargedDays());
        assertEquals(17.94, agreement.getPreDiscountCharge(), 0);
        assertEquals(0, agreement.getDiscountPercentage());
        assertEquals(0.0, agreement.getDiscountAmount().doubleValue(), 0);
        assertEquals(17.94, agreement.getFinalCharge().doubleValue(), 0);
    }
}
