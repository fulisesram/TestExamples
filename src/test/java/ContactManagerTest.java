import lombok.Value;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public static void setupAll() {
        System.out.println("Should Print Before All Tests");
    }

    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should create contact")
    @Disabled
    public void shouldCreateContact() {
        contactManager.addContact("Fabian", "Ramirez", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
//        Assertions.assertTrue(contactManager.getAllContacts().stream()
//                .filter(contact ->
//                        contact.getFirstName().equals("Fabian") &&
//                        contact.getFirstName().equals("Ramirez") &&
//                        contact.getPhoneNumber().equals("0123456789"))
//                .findAny().isPresent());
    }

    @Test
    @DisplayName("Should Not Create Contact When First Name is Null")
    public void shouldThrowRuntimeExceptionWhenFirstNameIsNull() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact(null, "Ramirez", "9876543210");
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should Execute After Each Test");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should Be Executed at the end of the Test");
    }

    @Test
    @DisplayName("Should create contact only on MAC OS")
    @DisabledOnOs(value = OS.MAC, disabledReason = "Enable only on mAC OS")
    public void shouldCreateContactOnlyOnMac() {
        contactManager.addContact("Fabian", "Ramirez", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should Not Create Contact on Windows")
    @EnabledOnOs(value = OS.WINDOWS, disabledReason = "Enable only on Windows OS")
    public void shouldCreateContactOnlyOnWindows() {
        contactManager.addContact("Fabian", "Ramirez", "0123456789");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Test Contact Creation on Developer Machine")
    public void shouldTestContactCreationOnDev() {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Fabian", "Ramirez", "0123456789");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Nested
    class RepeatedNestedTest {
        @DisplayName("Repeat Contact Creation Test 3 Times")
        @RepeatedTest(value = 3,
                name = "Repeating Contact Creation Test {currentRepetition} of {totalRepetitions}")
        public void shouldTestContactCreationRepeatedly() {
            contactManager.addContact("Fabian", "Ramirez", "0123456789");
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }
    }

    @Nested
    class ParameterizedNestedTest {
        @DisplayName("Contact Creation Using ParameterizedTest")
        @ParameterizedTest
        @ValueSource(strings = {"0123456789", "0123456789", "0123456789"})
        public void shouldTestContactCreationUsingValueSource(String phoneNumber) {
            contactManager.addContact("Fabian", "Ramirez", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("Method Source Case - Phone Number should match the required format")
        @ParameterizedTest
        @MethodSource("phoneNumberList")
        public void shouldTestPhoneNumberFormatUsingMethodSource(String phoneNumber) {
            contactManager.addContact("Fabian", "Ramirez", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        private static List<String> phoneNumberList() {
            return Arrays.asList("0123456789", "0123456789", "0123456789");
        }

        @DisplayName("CSV Source Case - Phone Number should match the required Format")
        @ParameterizedTest
        @CsvSource({"0123456789", "0123456789", "0123456789"})
        public void shouldTestPhoneNumberFormatUsingMethodCVSSource(String phoneNumber) {
            contactManager.addContact("Fabian", "Ramirez", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

        @DisplayName("CSV Source Case - Phone Number should match the required Format")
        @ParameterizedTest
        @CsvFileSource(resources = "/data.csv")
        public void shouldTestPhoneNumberFormatUsingMethodCVSFileSource(String phoneNumber) {
            contactManager.addContact("Fabian", "Ramirez", phoneNumber);
            assertFalse(contactManager.getAllContacts().isEmpty());
            assertEquals(1, contactManager.getAllContacts().size());
        }

    }
}