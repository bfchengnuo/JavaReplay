package com.bfchengnuo.uselibraries.spring.jdbc;

/**
 * 通用 Dao 的接口，临时
 *
 * @author Created by 冰封承諾Andy on 2019/9/18.
 */
public interface SimpleDao<T, K> {
    /**
     * 插入一条记录
     * @param data 插入的数据
     * @return 是否插入成功
     */
    boolean add(T data);

    /**
     * 根据 id 删除一条数据
     * @param id id
     * @return 是否删除成功
     */
    boolean deleteById(K id);

    /**
     * 根据 id 更新一条数据
     * @param data 更新的数据
     * @param id id
     * @return 是否更新成功
     */
    boolean updateById(T data, K id);

    /**
     * 根据 id 查询一条记录
     * @param id id
     * @return 一条数据
     */
    T queryById(K id);
}
