package org.application.data.messagedata;

import org.application.PlaythingApplication;
import org.application.po.CommentPO;
import org.application.po.MessagePO;
import org.application.po.PrivateMessagePO;
import org.application.util.ResultMessage;
import org.application.util.State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * @author miaomuzhi
 * @since 2018/8/5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PlaythingApplication.class)
@WebAppConfiguration
@ContextConfiguration(classes = {org.application.data.messagedata.MessageDAOImpl.class, org.application.po.TagPO.class, org.application.po.MessagePO.class, org.application.po.CommentPO.class})
public class MessageDAOImplTest {

    private MessageDAO messageDAO;

    private MessagePO messagePO;
    private CommentPO commentPO;
    private PrivateMessagePO pmPO;

    @Autowired
    public void setMessageDAO(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    @Before
    public void setUp(){
        messagePO = new MessagePO();
        messagePO.setContent("the first message");
        commentPO = new CommentPO();
        commentPO.setContent("first comment of the first message");
        commentPO.setPostId(1);
        commentPO.setReplierId(2);
        pmPO = new PrivateMessagePO();
        pmPO.setContent("first private message");
        pmPO.setSenderId(0);
        pmPO.setReceiverId(3);
    }

    @Test
    @Transactional
    @Rollback
    public void addMessage() {
        assertEquals(ResultMessage.SUCCESS, messageDAO.addMessage(messagePO));
    }

    @Test
    @Transactional
    @Rollback
    public void checkMessage() {
        messagePO.setStatus(State.WAITING);
        messageDAO.addMessage(messagePO);
        messageDAO.checkMessage(messagePO.getMessageId());
        assertEquals(State.PERMITTED, messageDAO.findMessageById(messagePO.getMessageId()).getStatus());
    }

    @Test
    @Transactional
    @Rollback
    public void modifyMessage() {
        messageDAO.addMessage(messagePO);

        MessagePO messagePO1 = new MessagePO();
        messagePO1.setMessageId(messagePO.getMessageId());
        messagePO1.setContent("second");
        messageDAO.modifyMessage(messagePO1);

        assertEquals("second", messageDAO.findMessageById(messagePO.getMessageId()).getContent());
    }


    @Test
    @Transactional
    @Rollback
    public void findAllMessages() {
        messageDAO.addMessage(messagePO);
        assertEquals(1, messageDAO.findAllMessages().size());
    }

    @Test
    @Transactional
    @Rollback
    public void addComment() {
        assertEquals(ResultMessage.SUCCESS, messageDAO.addComment(commentPO));
    }

    @Test
    @Transactional
    @Rollback
    public void getCommentById() {
        messageDAO.addComment(commentPO);
        assertEquals("first comment of the first message", messageDAO.getCommentById(commentPO.getCommentId()).getContent());
    }

    @Test
    @Transactional
    @Rollback
    public void getCommentByMessageId() {
        commentPO.setPostId(3);
        messageDAO.addComment(commentPO);
        assertEquals("first comment of the first message", messageDAO.getCommentByPostId(3).get(0).getContent());
    }

    @Test
    @Transactional
    @Rollback
    public void sendPrivateMes() {
        assertEquals(ResultMessage.SUCCESS, messageDAO.sendPrivateMes(pmPO));
    }

    @Test
    @Transactional
    @Rollback
    public void modifyPrivateMes() {
        messageDAO.sendPrivateMes(pmPO);
        pmPO.setContent("after modified");
        messageDAO.modifyPrivateMes(pmPO);
        assertEquals("after modified", messageDAO.findSentMes(0).get(0).getContent());
    }

    @Test
    @Transactional
    @Rollback
    public void checkPrivateMes() {
        messageDAO.sendPrivateMes(pmPO);
        messageDAO.checkPrivateMes(pmPO.getPmId());
        assertEquals(true, messageDAO.findSentMes(0).get(0).isChecked());
    }

    @Test
    @Transactional
    @Rollback
    public void findReceivedMes() {
        messageDAO.sendPrivateMes(pmPO);
        assertEquals(1, messageDAO.findReceivedMes(3).size());
    }
}