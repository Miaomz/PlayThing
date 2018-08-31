package org.application.businesslogic.messagebl;

import org.application.data.messagedata.MessageDAO;
import org.application.po.CommentPO;
import org.application.po.MessagePO;
import org.application.po.PrivateMessagePO;
import org.application.po.TagPO;
import org.application.util.ClassType;
import org.application.util.ResultMessage;
import org.application.util.TransUtil;
import org.application.vo.CommentVO;
import org.application.vo.MessageVO;
import org.application.vo.PrivateMessageVO;
import org.application.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class MessageServiceImpl implements MessageService {

    private MessageDAO messageDAO;

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Override
    public ResultMessage addMessage(MessageVO message) {
        return messageDAO.addMessage((MessagePO) message.toPO());
    }

    @Override
    public Boolean checkMessage(long messageId) {
        return messageDAO.checkMessage(messageId);
    }

    @Override
    public ResultMessage modifyMessage(MessageVO message) {
        return messageDAO.modifyMessage((MessagePO) message.toPO());
    }

    @Override
    public MessageVO findMessageById(long mid) {
        MessagePO messagePO = messageDAO.findMessageById(mid);
        if (messagePO != null && !messagePO.isDeleted()){
            return new MessageVO(messageDAO.findMessageById(mid));
        } else {
            return null;
        }
    }

    @Override
    public List<MessageVO> findAllMessages() {
        List<MessagePO> messagePOS = messageDAO.findAllMessages();
        List<MessageVO> messageVOS = new ArrayList<>(messagePOS.size());
        messagePOS.forEach(messagePO -> messageVOS.add(new MessageVO(messagePO)));
        return messageVOS;
    }

    @Override
    public List<MessageVO> findMessageByTag(TagVO tag) {
        List<MessagePO> messagePOS = messageDAO.findAllMessages();
        TransUtil.removeDeleted(messagePOS);

        messagePOS.removeIf(messagePO -> {
            for (TagPO tagPO : messagePO.getTags()) {
                if (tagPO.getContent().equals(tag.getContent())){
                    return false;
                }
            }
            return true;
        });

        List<MessageVO> messageVOS = new ArrayList<>(messagePOS.size());
        messagePOS.forEach(messagePO -> messageVOS.add(new MessageVO(messagePO)));
        return messageVOS;
    }

    @Override
    public ResultMessage sendPrivateMes(PrivateMessageVO pm) {
        return messageDAO.sendPrivateMes((PrivateMessagePO) pm.toPO());
    }

    @Override
    public ResultMessage checkPrivateMes(long pmId) {
        return messageDAO.checkPrivateMes(pmId);
    }

    @Override
    public List<PrivateMessageVO> findSentMes(long senderId) {
        List<PrivateMessagePO> privateMessagePOS = messageDAO.findSentMes(senderId);
        TransUtil.removeDeleted(privateMessagePOS);

        List<PrivateMessageVO> privateMessageVOS = new ArrayList<>(privateMessagePOS.size());
        privateMessagePOS.forEach(privateMessagePO -> privateMessageVOS.add(new PrivateMessageVO(privateMessagePO)));
        return privateMessageVOS;
    }

    @Override
    public List<PrivateMessageVO> findReceivedMes(long receiverId) {
        List<PrivateMessagePO> privateMessagePOS = messageDAO.findReceivedMes(receiverId);
        TransUtil.removeDeleted(privateMessagePOS);

        List<PrivateMessageVO> privateMessageVOS = new ArrayList<>(privateMessagePOS.size());
        privateMessagePOS.forEach(privateMessagePO -> privateMessageVOS.add(new PrivateMessageVO(privateMessagePO)));
        return privateMessageVOS;
    }

    @Override
    public ResultMessage addComment(CommentVO commentVO) {
        return messageDAO.addComment((CommentPO) commentVO.toPO());
    }

    @Override
    public CommentVO findCommentById(long commentId) {
        return new CommentVO(messageDAO.getCommentById(commentId));
    }

    @Override
    public List<CommentVO> findCommentsByMessage(long messageId) {
        List<CommentPO> commentPOS = messageDAO.getCommentByPostId(messageId);
        commentPOS.removeIf(commentPO -> commentPO.getType() != ClassType.SHARE);

        List<CommentVO> commentVOS = new ArrayList<>(commentPOS.size());
        commentPOS.forEach(commentPO -> commentVOS.add(new CommentVO(commentPO)));
        return commentVOS;
    }

    @Override
    public List<CommentVO> findCommentByCommodity(long commodityId) {
        List<CommentPO> commentPOS = messageDAO.getCommentByPostId(commodityId);
        commentPOS.removeIf(commentPO -> commentPO.getType() != ClassType.SELL);

        List<CommentVO> commentVOS = new ArrayList<>(commentPOS.size());
        commentPOS.forEach(commentPO -> commentVOS.add(new CommentVO(commentPO)));
        return commentVOS;
    }
}
