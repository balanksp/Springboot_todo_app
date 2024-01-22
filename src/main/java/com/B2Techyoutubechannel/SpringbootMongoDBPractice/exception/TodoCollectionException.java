package com.B2Techyoutubechannel.SpringbootMongoDBPractice.exception;

public class TodoCollectionException extends Exception {
    private static final long serialVersionUID = 1l;

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "todo String is " + id + "not found";

    }

    public static String TodoAlreadyExist() {
        return "already given string exist";
    }

}
