package org.application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.application.po.CommodityPO;
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
public class CommodityVO implements VO{
    private long cid;
    private long writer;
    private double price;
    private int remainedQuantity;
    private List<TagVO> tagVOS;
    private PostType postType;
    private String video;
    private List<String> covers;
    private String title;
    private String content;
    private State status;

    private ClassType type = ClassType.COMMODITY;


    public CommodityVO(CommodityPO commodityPO){
        this.cid = commodityPO.getCid();
        this.writer = commodityPO.getVendorId();
        this.price = commodityPO.getPrice();
        this.remainedQuantity = commodityPO.getRemainedQuantity();
        this.postType = commodityPO.getPostType();
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
        return new CommodityPO(cid, writer, false, TransUtil.toPOList(tagVOS), postType, video, covers, title, content, price, remainedQuantity, status);
    }
}
