package Sercurity;

public class PhoneNumberValidator {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Remove any whitespace or special characters from the phone number
        String cleanPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");

        // Check if the cleaned phone number meets the length requirement
        int phoneNumberLength = cleanPhoneNumber.length();
        if (phoneNumberLength < 9 || phoneNumberLength > 11) {
            return false;
        }

        // Check if the cleaned phone number starts with "84" or "0"
        return cleanPhoneNumber.startsWith("84") || cleanPhoneNumber.startsWith("0");
    }
}
