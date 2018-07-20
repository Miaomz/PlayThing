package org.businesslogic.recommendationbl;

import org.data.recommendationdata.RecommendationDAO;
import org.po.RecommendationPO;
import org.po.TagPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.RecommendationVO;
import org.vo.TagVO;

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
        return new RecommendationVO(recommendationDAO.findRecommendationById(rid));
    }

    @Override
    public List<RecommendationVO> findRecommendationByTag(List<TagVO> tags) {
        List<TagPO> tagPOS = new ArrayList<>(tags.size());
        tags.forEach(tagVO -> tagPOS.add((TagPO) tagVO.toPO()));
        List<RecommendationPO> recommendationPOS = recommendationDAO.findRecommendationByTag(tagPOS);
        List<RecommendationVO> recommendationVOS = new ArrayList<>(recommendationPOS.size());
        recommendationPOS.forEach(recommendationPO -> recommendationVOS.add(new RecommendationVO(recommendationPO)));
        return recommendationVOS;
    }
}
