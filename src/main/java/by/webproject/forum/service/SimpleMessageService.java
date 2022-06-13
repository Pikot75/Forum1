package by.webproject.forum.service;

import by.webproject.forum.dao.MessageDao;
import by.webproject.forum.entity.Message;
import by.webproject.forum.exception.DaoException;
import by.webproject.forum.exception.ServiceError;
import by.webproject.forum.validator.MessageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class SimpleMessageService implements MessageService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleMessageService.class);
    private final MessageDao messageDao;
    private final MessageValidator messageValidator;

    public SimpleMessageService(MessageDao messageDao, MessageValidator messageValidator) {
        this.messageDao = messageDao;
        this.messageValidator = messageValidator;
    }

    @Override
    public void addMessage(long fromId, long toId, String text) {
        if (!messageValidator.validateMessageDataFromIdAndText(fromId, toId, text))
            try {
                messageDao.addMessage(fromId, toId, text, new Date(new java.util.Date().getTime()));
            } catch (DaoException e) {
                LOG.error("Cannot add message, fromId: " + fromId + "toId: " + toId + "text: " + text);
                throw new ServiceError("Cannot add message");
            }

    }

    @Override
    public List<Message> findToId(long userId) {
        try {
            return messageDao.findToId(userId);
        } catch (DaoException e) {
            LOG.error("Cannot find toId");
            throw new ServiceError("Cannot find toId");
        }

    }

    @Override
    public Optional<Message> findFromId(long userId) {
        try {
            return messageDao.findFromId(userId);
        } catch (DaoException e) {
            LOG.error("Cannot find toId");
            throw new ServiceError("Cannot find toId");
        }

    }

}
