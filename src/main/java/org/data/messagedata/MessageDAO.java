package org.data.messagedata;

import org.po.MessagePO;
import org.po.PrivateMessagePO;
import org.po.TagPO;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface MessageDAO {

    ResultMessage addMessage(MessagePO message);

    Boolean checkMessage(long messageId);

    ResultMessage modifyMessage(MessagePO message);

    MessagePO findMessageById(long mid);

    List<MessagePO> findMessageByTag(TagPO tag);

    ResultMessage sendPrivateMes(PrivateMessagePO pm);

    List<PrivateMessagePO> findSentMes(long senderId);

    List<PrivateMessagePO> findReceivedMes(long receiverId);
}
