package io.p4r53c.telran.utils.emums;

public enum ErrorString {

    UPPERCASE_REQUIRED("At least one uppercase letter required"),
    LOWERCASE_REQUIRED("At least one lowercase letter required"),
    DIGIT_REQUIRED("At least one digit required"),
    DOT_REQUIRED("At least one dot required"),
    SPACES_NOT_ALLOWED("Spaces are not allowed");

    private final String errorMessage;

    ErrorString(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
