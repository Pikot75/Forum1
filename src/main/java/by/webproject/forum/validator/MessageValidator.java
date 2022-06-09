package by.webproject.forum.validator;

public interface MessageValidator {
    boolean validateMessageDataByFromIdAndText(long fromId, long toId, String text);
}
