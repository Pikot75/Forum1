package by.webproject.forum.service;

import by.webproject.forum.dao.MessageDao;
import by.webproject.forum.entity.Message;
import by.webproject.forum.entity.User;
import by.webproject.forum.exception.DaoException;
import by.webproject.forum.validator.MessageValidator;

import java.sql.Date;
import java.util.List;

public class SimpleMessageService implements MessageService {
    private final MessageDao messageDao;
    private final MessageValidator messageValidator;

    public SimpleMessageService(MessageDao messageDao, MessageValidator messageValidator) {
        this.messageDao = messageDao;
        this.messageValidator = messageValidator;
    }

    @Override
    public void addMessage(long fromId, long toId, String text) {
       if (!messageValidator.validateMessageDataByFromIdAndText(fromId,toId,text))
        try {
            messageDao.addMessage(fromId,toId,text,new Date(new java.util.Date().getTime()));
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Message> findToId(long userId) {
        messageDao.findToId(userId);
        return null;
    }

    @Override
    public List<Message> findFromId(long userId) {
        messageDao.findFromId(userId);
        return null;
    }

}
