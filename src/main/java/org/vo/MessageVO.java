package org.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.po.MessagePO;
import org.po.PO;
import org.po.TagPO;
import org.util.ClassType;
import org.util.PostType;
import org.util.State;
import org.util.TransUtil;

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

    private List<String> pic;

    private String video;

    private List<String> covers;

    private String title;

    private String content;

    private State status;

    public static final ClassType TYPE = ClassType.MESSAGE;


    public MessageVO(MessagePO messagePO){
        this.messageId = messagePO.getMessageId();
        this.writer = messagePO.getUserId();
        this.postType = messagePO.getPostType();
        this.pic = messagePO.getPic();
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
        return new MessagePO(messageId, writer, TransUtil.toPOList(tags), postType, pic, video, covers, title, content, false, status);
    }
}
