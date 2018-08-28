package org.application.data.messagedata;

import org.application.po.CommentPO;
import org.application.po.MessagePO;
import org.application.po.PrivateMessagePO;
import org.application.util.ResultMessage;

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

    List<MessagePO> findAllMessages();

    ResultMessage addComment(CommentPO commentPO);

    CommentPO getCommentById(long commentId);

    List<CommentPO> getCommentByMessageId(long messageId);

    ResultMessage sendPrivateMes(PrivateMessagePO pm);

    ResultMessage modifyPrivateMes(PrivateMessagePO pm);

    ResultMessage checkPrivateMes(long pmId);

    List<PrivateMessagePO> findSentMes(long senderId);

    List<PrivateMessagePO> findReceivedMes(long receiverId);
}
