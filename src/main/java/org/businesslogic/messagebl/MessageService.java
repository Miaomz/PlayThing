package org.businesslogic.messagebl;

import org.util.ResultMessage;
import org.vo.CommentVO;
import org.vo.MessageVO;
import org.vo.PrivateMessageVO;
import org.vo.TagVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public interface MessageService {

    ResultMessage addMessage(MessageVO message);

    Boolean checkMessage(long messageId);

    ResultMessage modifyMessage(MessageVO message);

    MessageVO findMessageById(long mid);

    List<MessageVO> findMessageByTag(TagVO tag);

    ResultMessage sendPrivateMes(PrivateMessageVO pm);

    List<PrivateMessageVO> findSentMes(long senderId);

    List<PrivateMessageVO> findReceivedMes(long receiverId);

    ResultMessage addComment(CommentVO commentVO);

    CommentVO findCommentById(long commentId);

    List<CommentVO> findCommentsByMessage(long messageId);
}
