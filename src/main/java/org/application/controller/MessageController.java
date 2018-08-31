package org.application.controller;

import org.application.businesslogic.messagebl.MessageService;
import org.application.businesslogic.userbl.UserService;
import org.application.util.ClassType;
import org.application.util.ResultMessage;
import org.application.vo.CommentVO;
import org.application.vo.PrivateMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

import static org.application.util.ClassType.SHARE;
import static org.application.util.ConstantString.USER_ID;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@RestController
public class MessageController {

    private MessageService messageService;
    private UserService userService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/get_chat")
    public List<PrivateMessageVO> getChat(@RequestParam long chatterOne, @RequestParam long chatterTwo){
        List<PrivateMessageVO> oneReceived = messageService.findReceivedMes(chatterOne);
        List<PrivateMessageVO> twoReceived = messageService.findReceivedMes(chatterTwo);
        List<PrivateMessageVO> oneSent = messageService.findSentMes(chatterOne);
        List<PrivateMessageVO> twoSent = messageService.findSentMes(chatterTwo);

        //remove private messages with other users
        findChat(oneReceived, twoSent);
        findChat(twoReceived, oneSent);

        sortList(oneReceived);
        sortList(twoReceived);

        //merge two collections according to the time
        List<PrivateMessageVO> result = new ArrayList<>(oneReceived.size() + twoReceived.size());
        while (!oneReceived.isEmpty() && !twoReceived.isEmpty()){
            PrivateMessageVO candidOne = oneReceived.get(0);
            PrivateMessageVO candidTwo = twoReceived.get(0);

            boolean isFirstLater = candidOne.getTime().isAfter(candidTwo.getTime());
            PrivateMessageVO chosen = (isFirstLater ? candidOne : candidTwo);
            if (isFirstLater){
                oneReceived.remove(chosen);
            } else {
                twoReceived.remove(chosen);
            }
            result.add(chosen);
        }
        result.addAll(oneReceived);
        result.addAll(twoReceived);
        return result;
    }

    @RequestMapping("/send_privateMessage")
    public ResultMessage sendPrivateMessage(@RequestParam long receiverId, @RequestParam String content, HttpSession session){
        PrivateMessageVO privateMessageVO = new PrivateMessageVO();
        privateMessageVO.setSenderId((Long) session.getAttribute(USER_ID));
        privateMessageVO.setReceiverId(receiverId);
        privateMessageVO.setTime(LocalDateTime.now());
        privateMessageVO.setChecked(false);//when the message is sent, it is originally not checked by receiver
        privateMessageVO.setContent(content);
        return messageService.sendPrivateMes(privateMessageVO);
    }

    @RequestMapping("/hasAllMessageChecked")
    public boolean hasAllMessageChecked(HttpSession session){
        List<PrivateMessageVO> privateMessageVOS = messageService.findReceivedMes((Long) session.getAttribute(USER_ID));
        for (PrivateMessageVO privateMessageVO : privateMessageVOS) {
            if (!privateMessageVO.isChecked()){
                return false;
            }
        }
        return true;
    }

    @RequestMapping("/get_unsolved_contacts")
    public Map<Long, Integer> getUnsolvedContacts(HttpSession session){
        List<PrivateMessageVO> receivedMessages = messageService.findReceivedMes((Long) session.getAttribute(USER_ID));//current user is receiver
        Map<Long, Integer> result = new HashMap<>();
        receivedMessages.forEach(receivedMessage -> {
            long senderId = receivedMessage.getSenderId();
            result.putIfAbsent(senderId, 0);//the senders whose messages have read completely still need to be displayed

            if (!receivedMessage.isChecked()){
                result.put(senderId, result.get(senderId) + 1);
            }
        });
        return result;
    }

    @RequestMapping("/read_private_message")
    public void readPrivateMessage(@RequestParam long senderId, HttpSession session){
        List<PrivateMessageVO> receivedMessages = messageService.findReceivedMes((Long) session.getAttribute(USER_ID));
        receivedMessages.forEach(receivedMessage -> {
            if (receivedMessage.getSenderId() == senderId){
                receivedMessage.setChecked(true);
            }
        });
    }

    @RequestMapping("/get_comment")
    public List<CommentVO> getComments(@RequestParam long postId, @RequestParam ClassType type){
        if (type == SHARE)
            return messageService.findCommentsByMessage(postId);
        else
            return messageService.findCommentByCommodity(postId);
    }

    @RequestMapping("/share_comment")
    public ResultMessage shareComment(@RequestParam long postId, @RequestParam String content, @RequestParam ClassType type, HttpSession session){
        long replierId = (Long) session.getAttribute(USER_ID);
        String replier = userService.findUserById(replierId).getUserName();

        CommentVO commentVO = new CommentVO();
        commentVO.setReplierId(replierId);
        commentVO.setReplier(replier);
        commentVO.setPostId(postId);
        commentVO.setTime(LocalDateTime.now());
        commentVO.setType(type);
        commentVO.setContent(content);
        return messageService.addComment(commentVO);
    }

    /**
     * utility function, find the intersection
     * @param oneReceived all private messages one have received
     * @param anotherSent all private messages another person have sent
     */
    private void findChat(List<PrivateMessageVO> oneReceived, List<PrivateMessageVO> anotherSent){
        oneReceived.removeIf(privateMessageVO -> {
            boolean isNotSentByTwo = true;
            for (PrivateMessageVO messageVO : anotherSent) {
                if (messageVO.getPmId() == privateMessageVO.getPmId()){
                    isNotSentByTwo = false;
                    break;
                }
            }
            return isNotSentByTwo;
        });
    }

    private void sortList(List<PrivateMessageVO> messageVOS){
        for (int i = 1; i < messageVOS.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (messageVOS.get(j).getTime().isAfter(messageVOS.get(j-1).getTime())){
                    Collections.swap(messageVOS, j, j-1);
                } else {
                    break;
                }
            }
        }
    }
}
