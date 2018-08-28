package org.application.businesslogic.recommendationbl;

import org.application.data.recommendationdata.RecommendationDAO;
import org.application.po.RecommendationPO;
import org.application.po.TagPO;
import org.application.util.ResultMessage;
import org.application.util.TransUtil;
import org.application.vo.RecommendationVO;
import org.application.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class RecommendationServiceImpl implements RecommendationService {

    private RecommendationDAO recommendationDAO;

    @Autowired
    public void setRecommendationDAO(RecommendationDAO recommendationDAO) {
        this.recommendationDAO = recommendationDAO;
    }

    @Override
    public ResultMessage addRecommendation(RecommendationVO recommendationVO) {
        return recommendationDAO.addRecommendation((RecommendationPO) recommendationVO.toPO());
    }

    @Override
    public ResultMessage deleteRecommendation(long recommendationId) {
        return recommendationDAO.deleteRecommendation(recommendationId);
    }

    @Override
    public ResultMessage modifyRecommendation(RecommendationVO recommendationVO) {
        return recommendationDAO.modifyRecommendation((RecommendationPO) recommendationVO.toPO());
    }

    @Override
    public RecommendationVO findRecommendationById(long rid) {
        RecommendationPO recommendationPO = recommendationDAO.findRecommendationById(rid);
        if (recommendationPO != null && !recommendationPO.isDeleted()){
            return new RecommendationVO(recommendationPO);
        } else {
            return null;
        }
    }

    @Override
    public List<RecommendationVO> findRecommendationByTag(List<TagVO> tags) {
        List<RecommendationPO> recommendationPOS = recommendationDAO.findAllRecommendations();
        TransUtil.removeDeleted(recommendationPOS);

        recommendationPOS.removeIf(recommendationPO -> {
            boolean isDuplicate = false;
            for (TagPO tagPO : recommendationPO.getTags()) {
                for (TagVO tag : tags) {
                    if (tagPO.getContent().equals(tag.getContent())){
                        isDuplicate = true;
                        break;
                    }
                }
            }
            return !isDuplicate;//如果没有重合，则删除
        });

        List<RecommendationVO> recommendationVOS = new ArrayList<>(recommendationPOS.size());
        recommendationPOS.forEach(recommendationPO -> recommendationVOS.add(new RecommendationVO(recommendationPO)));
        return recommendationVOS;
    }
}
