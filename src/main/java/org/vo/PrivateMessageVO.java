package org.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.po.PO;
import org.po.PrivateMessagePO;

import java.time.LocalDateTime;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateMessageVO implements VO{

    private long pmId;

    private long senderId;

    private long receiverId;

    private String title;

    private LocalDateTime time;

    private boolean isChecked;

    private String content;


    public PrivateMessageVO(PrivateMessagePO privateMessagePO){
        this.pmId = privateMessagePO.getPmId();
        this.senderId = privateMessagePO.getSenderId();
        this.receiverId = privateMessagePO.getReceiverId();
        this.title = privateMessagePO.getTitle();
        this.content = privateMessagePO.getContent();
        this.time = privateMessagePO.getTime();
        this.isChecked = privateMessagePO.isChecked();
    }

    @Override
    public PO toPO() {
        return new PrivateMessagePO(pmId, senderId, receiverId, false, title, time, isChecked, content);
    }
}
