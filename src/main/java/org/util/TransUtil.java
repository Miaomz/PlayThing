package org.util;

import org.vo.VO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 */
public class TransUtil {

    private TransUtil(){}//util class

    @SuppressWarnings("unchecked")
    public static <T> List<T> toPOList(List<? extends VO> vos){
        if (vos == null){
            return new ArrayList<>();
        }

        List<T> pos = new ArrayList<>(vos.size());
        vos.forEach(vo -> pos.add((T)vo.toPO()));
        return pos;
    }
}
