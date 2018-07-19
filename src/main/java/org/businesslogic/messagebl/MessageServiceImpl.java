package org.businesslogic.messagebl;

import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.MessageVO;
import org.vo.PrivateMessageVO;
import org.vo.TagVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class MessageServiceImpl implements MessageService {

    @Override
    public ResultMessage addMessage(MessageVO message) {
        return null;
    }

    @Override
    public Boolean checkMessage(long messageId) {
        return null;
    }

    @Override
    public ResultMessage modifyMessage(MessageVO message) {
        return null;
    }

    @Override
    public MessageVO findMessageById(long mid) {
        return null;
    }

    @Override
    public List<MessageVO> findMessageByTag(TagVO tag) {
        return null;
    }

    @Override
    public ResultMessage sendPrivateMes(PrivateMessageVO pm) {
        return null;
    }

    @Override
    public List<PrivateMessageVO> findSentMes(long senderId) {
        return null;
    }

    @Override
    public List<PrivateMessageVO> findReceivedMes(long receiverId) {
        return null;
    }
}
