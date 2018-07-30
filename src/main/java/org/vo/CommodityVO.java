package org.vo;

import org.po.CommodityPO;
import org.po.PO;
import org.po.TagPO;
import org.util.PostType;
import org.util.State;
import org.util.TransUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class CommodityVO implements VO{
    private long cid;
    private long writer;
    private double price;
    private int remainedQuantity;
    private List<TagVO> tagVOS;
    private PostType postType;
    private List<String> pic;
    private String video;
    private List<String> covers;
    private String title;
    private String content;
    private State status;


    public CommodityVO(long cid, long writer, double price, int remainedQuantity, List<TagVO> tagVOS, PostType postType, List<String> pic, String video, List<String> covers, String title, String content, State status) {
        this.cid = cid;
        this.writer = writer;
        this.price = price;
        this.remainedQuantity = remainedQuantity;
        this.tagVOS = tagVOS;
        this.postType = postType;
        this.pic = pic;
        this.video = video;
        this.covers = covers;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public CommodityVO(CommodityPO commodityPO){
        this.cid = commodityPO.getCid();
        this.writer = commodityPO.getVendorId();
        this.price = commodityPO.getPrice();
        this.remainedQuantity = commodityPO.getRemainedQuantity();
        this.postType = commodityPO.getPostType();
        this.pic = commodityPO.getPic();
        this.video = commodityPO.getVideo();
        this.covers = commodityPO.getCovers();
        this.title = commodityPO.getTitle();
        this.content = commodityPO.getContent();
        this.status = commodityPO.getStatus();

        List<TagPO> tagPOs = commodityPO.getTagPOS();
        this.tagVOS = new ArrayList<>(tagPOs.size());
        tagPOs.forEach(tagPO -> tagVOS.add(new TagVO(tagPO)));
    }

    @Override
    public PO toPO() {
        return new CommodityPO(cid, writer, false, TransUtil.toPOList(tagVOS), postType, pic, video, covers, title, content, price, remainedQuantity, status);
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getWriter() {
        return writer;
    }

    public void setWriter(long writer) {
        this.writer = writer;
    }

    public List<TagVO> getTagVOS() {
        return tagVOS;
    }

    public void setTagVOS(List<TagVO> tagVOS) {
        this.tagVOS = tagVOS;
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

    public List<String> getCovers() {
        return covers;
    }

    public void setCovers(List<String> covers) {
        this.covers = covers;
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

    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
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


}
