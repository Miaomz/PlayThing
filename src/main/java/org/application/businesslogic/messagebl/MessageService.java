package org.application.businesslogic.messagebl;

import org.application.util.ResultMessage;
import org.application.vo.CommentVO;
import org.application.vo.MessageVO;
import org.application.vo.PrivateMessageVO;
import org.application.vo.TagVO;

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

    List<MessageVO> findAllMessages();

    List<MessageVO> findMessageByTag(TagVO tag);

    ResultMessage sendPrivateMes(PrivateMessageVO pm);

    List<PrivateMessageVO> findSentMes(long senderId);

    List<PrivateMessageVO> findReceivedMes(long receiverId);

    ResultMessage addComment(CommentVO commentVO);

    CommentVO findCommentById(long commentId);

    List<CommentVO> findCommentsByMessage(long messageId);

    List<CommentVO> findCommentByCommodity(long commodityId);
}
