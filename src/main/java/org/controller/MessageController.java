package org.controller;

import org.businesslogic.messagebl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.util.ResultMessage;
import org.vo.PrivateMessageVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Controller
public class MessageController {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
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

        //reverse the list, from ascending to descending
        Collections.reverse(result);
        return result;
    }

    @RequestMapping("/send_privateMessage")
    public ResultMessage sendPrivateMessage(@RequestParam long receiverId, @RequestParam String content){
        PrivateMessageVO privateMessageVO = new PrivateMessageVO();
        //TODO set sender id
        privateMessageVO.setReceiverId(receiverId);
        privateMessageVO.setTime(LocalDateTime.now());
        privateMessageVO.setChecked(false);//when the message is sent, it is originally not checked by receiver
        privateMessageVO.setContent(content);
        return messageService.sendPrivateMes(privateMessageVO);
    }

    @RequestMapping("/hasAllMessageChecked")
    public boolean hasAllMessageChecked(){
        //TODO
        return false;
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
}
