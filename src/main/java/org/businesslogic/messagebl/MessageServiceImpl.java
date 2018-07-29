package org.businesslogic.messagebl;

import org.data.messagedata.MessageDAO;
import org.po.MessagePO;
import org.po.PrivateMessagePO;
import org.po.TagPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.MessageVO;
import org.vo.PrivateMessageVO;
import org.vo.TagVO;

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
        return new MessageVO(messageDAO.findMessageById(mid));
    }

    @Override
    public List<MessageVO> findMessageByTag(TagVO tag) {
        List<MessagePO> messagePOS = messageDAO.findMessageByTag((TagPO) tag.toPO());
        List<MessageVO> messageVOS = new ArrayList<>(messagePOS.size());
        messagePOS.forEach(messagePO -> messageVOS.add(new MessageVO(messagePO)));
        return messageVOS;
    }

    @Override
    public ResultMessage sendPrivateMes(PrivateMessageVO pm) {
        return messageDAO.sendPrivateMes((PrivateMessagePO) pm.toPO());
    }

    @Override
    public List<PrivateMessageVO> findSentMes(long senderId) {
        List<PrivateMessagePO> privateMessagePOS = messageDAO.findSentMes(senderId);
        List<PrivateMessageVO> privateMessageVOS = new ArrayList<>(privateMessagePOS.size());
        privateMessagePOS.forEach(privateMessagePO -> privateMessageVOS.add(new PrivateMessageVO(privateMessagePO)));
        return privateMessageVOS;
    }

    @Override
    public List<PrivateMessageVO> findReceivedMes(long receiverId) {
        List<PrivateMessagePO> privateMessagePOS = messageDAO.findReceivedMes(receiverId);
        List<PrivateMessageVO> privateMessageVOS = new ArrayList<>(privateMessagePOS.size());
        privateMessagePOS.forEach(privateMessagePO -> privateMessageVOS.add(new PrivateMessageVO(privateMessagePO)));
        return privateMessageVOS;
    }
}