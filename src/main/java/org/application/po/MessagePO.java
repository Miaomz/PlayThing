package org.application.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.util.PostType;
import org.application.util.State;
import org.application.util.TagsConverter;

import javax.persistence.*;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MessagePO implements PO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    private long userId;

    @Lob
    @Convert(converter = TagsConverter.class)
    private List<TagPO> tags;

    /**
     * 标示文本的嵌入图片还是视频
     */
    private PostType postType;

    /**
     * 嵌入视频的url；若postType为pic，则无意义
     */
    private String video;

    /**
     * 嵌入图片的url列表；若postType为video，则无意义
     */
    @ElementCollection
    private List<String> covers;

    private String title;

    @Column(length = 2048)
    private String content;

    private boolean isDeleted;

    private State status;
}
