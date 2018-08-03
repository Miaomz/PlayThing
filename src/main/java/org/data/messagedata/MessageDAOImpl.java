package org.data.messagedata;

import org.po.CommentPO;
import org.po.MessagePO;
import org.po.PrivateMessagePO;
import org.springframework.stereotype.Component;
import org.util.LoggerUtil;
import org.util.ResultMessage;
import org.util.State;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class MessageDAOImpl implements MessageDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ResultMessage addMessage(MessagePO message) {
        try {
            entityManager.persist(message);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public Boolean checkMessage(long messageId) {
        try {
            Query query = entityManager.createQuery("update MessagePO m set m.status = :permitted where m.messageId = :id");
            query.setParameter("permitted", State.PERMITTED);
            query.setParameter("id", messageId);
            int statusCode = query.executeUpdate();
            return statusCode == 1;//如正确运行，则被修改的行数（即返回值）为1
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return false;
        }
    }

    @Override
    public ResultMessage modifyMessage(MessagePO message) {
        try {
            entityManager.merge(message);
            entityManager.flush();
        }catch (PersistenceException pe){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public MessagePO findMessageById(long mid) {
        return entityManager.find(MessagePO.class, mid);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MessagePO> findAllMessages() {
        Query query = entityManager.createQuery("select m from MessagePO m");
        return query.getResultList();
    }

    @Override
    public ResultMessage addComment(CommentPO commentPO) {
        try {
            entityManager.persist(commentPO);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public CommentPO getCommentById(long commentId) {
        return entityManager.find(CommentPO.class, commentId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CommentPO> getCommentByMessageId(long messageId) {
        Query query = entityManager.createQuery("select c from CommentPO c where c.postId = :messageId");
        query.setParameter("messageId", messageId);
        return query.getResultList();
    }

    @Override
    public ResultMessage sendPrivateMes(PrivateMessagePO pm) {
        try {
            entityManager.persist(pm);
        }catch (PersistenceException pe){
            LoggerUtil.getLogger().info(pe);
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage modifyPrivateMes(PrivateMessagePO pm) {
        try {
            entityManager.merge(pm);
            entityManager.flush();
        }catch (PersistenceException pe){
            return ResultMessage.FAILURE;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage checkPrivateMes(long pmId) {
        Query query = entityManager.createQuery("update PrivateMessagePO p set p.isChecked = true");
        return (query.executeUpdate() == 1) ? ResultMessage.SUCCESS: ResultMessage.FAILURE;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PrivateMessagePO> findSentMes(long senderId) {
        Query query = entityManager.createQuery("select p from PrivateMessagePO p where p.senderId = :sid");
        query.setParameter("sid", senderId);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PrivateMessagePO> findReceivedMes(long receiverId) {
        Query query = entityManager.createQuery("select p from PrivateMessagePO p where p.receiverId = :rid");
        query.setParameter("rid", receiverId);
        return query.getResultList();
    }
}
