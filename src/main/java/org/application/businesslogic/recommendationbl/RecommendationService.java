package org.application.businesslogic.recommendationbl;

import org.application.util.ResultMessage;
import org.application.vo.RecommendationVO;
import org.application.vo.TagVO;

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
