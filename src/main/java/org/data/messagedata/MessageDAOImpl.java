package org.data.messagedata;

import org.po.MessagePO;
import org.po.PrivateMessagePO;
import org.po.TagPO;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class MessageDAOImpl implements MessageDAO {
    @Override
    public ResultMessage addMessage(MessagePO message) {
        return null;
    }

    @Override
    public Boolean checkMessage(long messageId) {
        return null;
    }

    @Override
    public ResultMessage modifyMessage(MessagePO message) {
        return null;
    }

    @Override
    public MessagePO findMessageById(long mid) {
        return null;
    }

    @Override
    public List<MessagePO> findMessageByTag(TagPO tag) {
        return null;
    }

    @Override
    public ResultMessage sendPrivateMes(PrivateMessagePO pm) {
        return null;
    }

    @Override
    public List<PrivateMessagePO> findSentMes(long senderId) {
        return null;
    }

    @Override
    public List<PrivateMessagePO> findReceivedMes(long receiverId) {
        return null;
    }
}
