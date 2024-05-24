import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserInputUtilTest {
    @Test
    public void testGetCheckoutDateWithValidInput() throws Exception {
        // Given
        String input = "2024\n5\n23\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        LocalDate result = UserInputUtil.getCheckoutDate(scanner);

        // Then
        assertEquals(LocalDate.of(2024, 5, 23), result);
    }

    @Test(expected = Exception.class)
    public void testGetCheckoutDateWithInvalidYear() throws Exception{
        // Given
        String input = "abcd\n5\n23\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        UserInputUtil.getCheckoutDate(scanner);
    }

    @Test(expected = Exception.class)
    public void testGetCheckoutDateWithInvalidMonth() throws Exception{
        // Given
        String input = "2024\nabcd\n23\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        UserInputUtil.getCheckoutDate(scanner);
    }

    @Test(expected = Exception.class)
    public void testGetCheckoutDateWithInvalidDay() throws Exception{
        // Given
        String input = "2024\n5\nabcd\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        UserInputUtil.getCheckoutDate(scanner);
    }

    @Test
    public void testGetNumberOfRentalDaysWithValidInput() throws Exception {
        // Given
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        int result = UserInputUtil.getNumberOfRentalDays(scanner);

        // Then
        assertEquals(5, result);
    }

    @Test(expected = Exception.class)
    public void testGetNumberOfRentalDaysWithInvalidInput() throws Exception {
        // Given
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        UserInputUtil.getNumberOfRentalDays(scanner);
    }

    @Test
    public void testGetDiscountPercentageWithValidInput() throws Exception {
        // Given
        String input = "100\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        int result = UserInputUtil.getDiscountPercentage(scanner);

        // Then
        assertEquals(100, result);
    }

    @Test(expected = Exception.class)
    public void testGetDiscountPercentageWithInvalidInput() throws Exception {
        // Given
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        UserInputUtil.getDiscountPercentage(scanner);
    }

    @Test
    public void testGetToolCodeWithValidInputOf1() throws Exception {
        // Given
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        Tool.Code result = UserInputUtil.getToolCode(scanner);

        // Then
        assertEquals(Tool.Code.CHNS, result);
    }

    @Test
    public void testGetToolCodeWithValidInputOf2() throws Exception {
        // Given
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        Tool.Code result = UserInputUtil.getToolCode(scanner);

        // Then
        assertEquals(Tool.Code.JAKD, result);
    }

    @Test
    public void testGetToolCodeWithValidInputOf3() throws Exception {
        // Given
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        Tool.Code result = UserInputUtil.getToolCode(scanner);

        // Then
        assertEquals(Tool.Code.JAKR, result);
    }

    @Test
    public void testGetToolCodeWithValidInputOf4() throws Exception {
        // Given
        String input = "4\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        Tool.Code result = UserInputUtil.getToolCode(scanner);

        // Then
        assertEquals(Tool.Code.LADW, result);
    }

    @Test()
    public void testGetToolCodeWithInvalidInputOf5() throws Exception {
        // Given
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        Tool.Code result = UserInputUtil.getToolCode(scanner);

        // Then
        assertNull(result);
    }

    @Test(expected = Exception.class)
    public void testGetToolCodeWithInvalidInput() throws Exception {
        // Given
        String input = "abc\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // When
        UserInputUtil.getToolCode(scanner);
    }
}
