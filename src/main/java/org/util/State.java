package org.util;

/**
 * @author miaomuzhi
 * @since 2018/7/19
 *
 */
public enum State {
    /**
     * 待审批
     */
    WAITING,
    /**
     * 已批准
     */
    PERMITTED,
    /**
     * 审核不通过
     */
    DENIED,
    /**
     * 精华（可视为已批准的子类）
     */
    RECOMMENDED
}
