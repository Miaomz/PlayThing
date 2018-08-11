package org.util;

import org.po.PO;
import org.vo.VO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public static void removeDeleted(List<? extends PO> prevList){
        prevList.removeIf(prev -> {
            boolean isDeleted = true;//remove if some exception has been generated
            try {
                Method method = prev.getClass().getMethod("isDeleted");
                isDeleted = (Boolean) method.invoke(prev);
            } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e){
                LoggerUtil.getLogger().info(e);
            }
            return isDeleted;
        });
    }
}
