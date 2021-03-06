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
@Table(name = "commodity")
public class CommodityPO implements PO{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cid;

    private long vendorId;

    private boolean isDeleted;

    @Lob
    @Convert(converter = TagsConverter.class)
    private List<TagPO> tagPOS;

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

    @Column(length = 12000)
    private String content;

    private double price;

    private int remainedQuantity;

    private State status;
}
