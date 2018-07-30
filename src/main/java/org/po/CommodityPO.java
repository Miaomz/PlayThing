package org.po;

import org.util.PostType;
import org.util.State;

import javax.persistence.*;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Entity
@Table(name = "commodity")
public class CommodityPO implements PO{

    @Id
    @GeneratedValue
    private long cid;

    private long vendorId;

    private boolean isDeleted;

    @OneToMany(targetEntity = TagPO.class)
    private List<TagPO> tagPOS;

    /**
     * 标示文本的嵌入图片还是视频
     */
    private PostType postType;

    /**
     * 嵌入图片的url列表；若postType为video，则无意义
     */
    @ElementCollection
    private List<String> pic;

    /**
     * 嵌入视频的url；若postType为pic，则无意义
     */
    private String video;

    @ElementCollection
    private List<String> covers;

    private String title;

    @Column(length = 2048)
    private String content;

    private double price;

    private int remainedQuantity;

    private State status;

    public CommodityPO() {}

    public CommodityPO(long cid, long vendorId, boolean isDeleted, List<TagPO> tagPOS, PostType postType, List<String> pic, String video, List<String> covers, String title, String content, double price, int remainedQuantity, State status) {
        this.cid = cid;
        this.vendorId = vendorId;
        this.isDeleted = isDeleted;
        this.tagPOS = tagPOS;
        this.postType = postType;
        this.pic = pic;
        this.video = video;
        this.covers = covers;
        this.title = title;
        this.content = content;
        this.price = price;
        this.remainedQuantity = remainedQuantity;
        this.status = status;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public List<TagPO> getTagPOS() {
        return tagPOS;
    }

    public void setTagPOS(List<TagPO> tagPOS) {
        this.tagPOS = tagPOS;
    }

    public List<String> getCovers() {
        return covers;
    }

    public void setCovers(List<String> covers) {
        this.covers = covers;
    }

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainedQuantity() {
        return remainedQuantity;
    }

    public void setRemainedQuantity(int remainedQuantity) {
        this.remainedQuantity = remainedQuantity;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
