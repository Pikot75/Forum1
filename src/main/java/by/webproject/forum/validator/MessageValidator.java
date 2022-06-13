package by.webproject.forum.validator;

public interface MessageValidator {
    boolean validateMessageDataFromIdAndText(long fromId, long toId, String text);
}//
