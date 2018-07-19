package org.businesslogic.recommendationbl;

import org.util.ResultMessage;
import org.vo.RecommendationVO;
import org.vo.TagVO;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public interface RecommendationService {

    ResultMessage addRecommendation(RecommendationVO recommendationVO);

    ResultMessage deleteRecommendation(long recommendationId);

    ResultMessage modifyRecommendation(RecommendationVO recommendationVO);

    RecommendationVO findRecommendationById(long rid);

    List<RecommendationVO> findRecommendationByTag(List<TagVO> tags);

}
