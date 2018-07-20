package org.data.recommendationdata;


import org.po.RecommendationPO;
import org.po.TagPO;
import org.springframework.stereotype.Component;
import org.util.ResultMessage;

import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/20
 */
@Component
public class RecommendationDAOImpl implements RecommendationDAO {
    @Override
    public ResultMessage addRecommendation(RecommendationPO recommendationPO) {
        return null;
    }

    @Override
    public ResultMessage deleteRecommendation(long recommendationId) {
        return null;
    }

    @Override
    public ResultMessage modifyRecommendation(RecommendationPO recommendationPO) {
        return null;
    }

    @Override
    public RecommendationPO findRecommendationById(long rid) {
        return null;
    }

    @Override
    public List<RecommendationPO> findRecommendationByTag(List<TagPO> tags) {
        return null;
    }
}
