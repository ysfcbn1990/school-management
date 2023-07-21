package com.schoolmanagement.payload.messages;

public class ErrorMessages {
   private ErrorMessages() {
    }

    public static final String ALREADY_SEND_A_MESSAGE_TODAY = "Error: You have already send a message with thi e-mail";

    public static final String ALREADY_REGISTER_MESSAGE_USERNAME = "Error: User with username %s is already registered";

    public static final String ALREADY_REGISTER_MESSAGE_SSN= "Error: User with ssn %s is already registered";

    public static final String ALREADY_REGISTER_MESSAGE_PHONE_NUMBER= "Error: User with Phone Number %s is already registered";

    public static final String ALREADY_REGISTER_MESSAGE_EMAIL= "Error: User with email %s is already registered";
}
