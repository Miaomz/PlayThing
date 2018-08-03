package org.data.recommendationdata;

import org.po.RecommendationPO;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
public interface RecommendationDAO {

    ResultMessage addRecommendation(RecommendationPO recommendationPO);

    ResultMessage deleteRecommendation(long recommendationId);

    ResultMessage modifyRecommendation(RecommendationPO recommendationPO);

    RecommendationPO findRecommendationById(long rid);

    List<RecommendationPO> findAllRecommendations();
}
