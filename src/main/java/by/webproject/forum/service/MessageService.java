package by.webproject.forum.service;

import by.webproject.forum.entity.Message;
import by.webproject.forum.entity.User;

import java.util.List;

public interface MessageService {
void addMessage(long fromId, long toId, String text);

List<Message> findToId(long userId);
List<Message> findFromId(long userId);
}
