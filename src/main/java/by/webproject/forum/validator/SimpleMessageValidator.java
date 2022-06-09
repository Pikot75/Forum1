package by.webproject.forum.validator;

import javax.jms.Message;
import java.util.regex.Pattern;

public class SimpleMessageValidator implements MessageValidator {
    @Override
    public boolean validateMessageDataByFromIdAndText(long fromId, long toId, String text) {
        final String TEXT_REGEX = "</?script>";//почему не дает сделать privet and static?
        String[] check = text.split(" ");
        int ber = 0;
        for (String temp : check) {
            if (temp.equals(TEXT_REGEX)) {
                ber++;
            }
        }
        if (fromId >0 && toId >0 && ber == 0) {
            return true;
        }
        return false;
    }
}
