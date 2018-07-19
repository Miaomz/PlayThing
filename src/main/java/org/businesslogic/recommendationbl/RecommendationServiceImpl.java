package org.businesslogic.recommendationbl;

import org.springframework.stereotype.Component;
import org.util.ResultMessage;
import org.vo.RecommendationVO;
import org.vo.TagVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
@Component
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public ResultMessage addRecommendation(RecommendationVO recommendationVO) {
        return null;
    }

    @Override
    public ResultMessage deleteRecommendation(long recommendationId) {
        return null;
    }

    @Override
    public ResultMessage modifyRecommendation(RecommendationVO recommendationVO) {
        return null;
    }

    @Override
    public RecommendationVO findRecommendationById(long rid) {
        return null;
    }

    @Override
    public List<RecommendationVO> findRecommendationByTag(List<TagVO> tags) {
        return null;
    }
}
