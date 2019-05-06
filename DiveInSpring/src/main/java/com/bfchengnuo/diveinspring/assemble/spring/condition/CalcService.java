package com.bfchengnuo.diveinspring.assemble.spring.condition;

/**
 * Created by 冰封承諾Andy on 2019/5/4.
 *
 * 用于条件装配的测试，将计算抽象为接口
 */
public interface CalcService {
    /**
     * 求和
     * @param values 数据集合
     * @return 总和
     */
    Integer sum(Integer... values);
}
