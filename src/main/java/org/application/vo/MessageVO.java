package org.application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.po.MessagePO;
import org.application.po.PO;
import org.application.po.TagPO;
import org.application.util.ClassType;
import org.application.util.PostType;
import org.application.util.State;
import org.application.util.TransUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageVO implements VO {

    private long messageId;

    private long writer;

    private List<TagVO> tags;

    private PostType postType;

    private String video;

    private List<String> covers;

    private String title;

    private String content;

    private State status;

    private ClassType type = ClassType.SHARE;


    public MessageVO(MessagePO messagePO){
        this.messageId = messagePO.getMessageId();
        this.writer = messagePO.getUserId();
        this.postType = messagePO.getPostType();
        this.video = messagePO.getVideo();
        this.covers = messagePO.getCovers();
        this.title = messagePO.getTitle();
        this.content = messagePO.getContent();
        this.status = messagePO.getStatus();

        List<TagPO> tagPOs = messagePO.getTags();
        this.tags = new ArrayList<>(tagPOs.size());
        tagPOs.forEach(tagPO -> tags.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new MessagePO(messageId, writer, TransUtil.toPOList(tags), postType, video, covers, title, content, false, status);
    }
}
