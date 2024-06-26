import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

public class ToolTest {

    @Test
    public void testLaborDayShouldNotBeCharged() {
        // Given
        LocalDate dayBeforeLaborDay = LocalDate.of(2024, 9, 1);
        LocalDate laborDay = LocalDate.of(2024, 9, 2);
        Tool tool = ToolRepository.getToolByCode(Tool.Code.LADW);

        // When
        int isChargeableDays = Tool.calculateChargeableDays(tool, dayBeforeLaborDay, laborDay);

        // Then
        assertEquals(0, isChargeableDays);
    }

    @Test
    public void testFourthOfJulyOnWeekdayShouldBeCharged() {
        // Given
        LocalDate dayBeforeFourthOfJuly = LocalDate.of(2014, 7, 3);
        LocalDate fourthOfJuly = LocalDate.of(2014, 7, 4);
        Tool tool = ToolRepository.getToolByCode(Tool.Code.CHNS);

        // When
        int isChargeableDays = Tool.calculateChargeableDays(tool, dayBeforeFourthOfJuly, fourthOfJuly);

        // Then
        assertEquals(1, isChargeableDays);
    }

    @Test
    public void testFourthOfJulyOnSaturdayShouldNotBeCharged() {
        // Given
        LocalDate julySecond = LocalDate.of(2015, 7, 2);
        LocalDate julyThird = LocalDate.of(2015, 7, 3);
        Tool tool = ToolRepository.getToolByCode(Tool.Code.LADW);

        // When
        int isChargeableDays = Tool.calculateChargeableDays(tool, julySecond, julyThird);

        // Then
        assertEquals(0, isChargeableDays);
    }

    @Test
    public void testFourthOfJulyOnSundayShouldNotBeCharged() {
        // Given
        LocalDate julyFourth = LocalDate.of(2021, 7, 4);
        LocalDate julyFifth = LocalDate.of(2021, 7, 5);
        Tool tool = ToolRepository.getToolByCode(Tool.Code.LADW);

        // When
        int isChargeableDays = Tool.calculateChargeableDays(tool, julyFourth, julyFifth);

        // Then
        assertEquals(0, isChargeableDays);
    }

    @Test
    public void testWeekendsShouldNotBeCharged() {
        // Given
        LocalDate weekdayFriday = LocalDate.of(2015, 9, 4);
        LocalDate weekendSunday = LocalDate.of(2015, 9, 6);
        Tool tool = ToolRepository.getToolByCode(Tool.Code.CHNS);

        // When
        int isChargeableDays = Tool.calculateChargeableDays(tool, weekdayFriday, weekendSunday);

        // Then
        assertEquals(0, isChargeableDays);
    }
}
