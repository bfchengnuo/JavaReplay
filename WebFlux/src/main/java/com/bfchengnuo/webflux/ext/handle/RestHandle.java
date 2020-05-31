package com.bfchengnuo.webflux.ext.handle;

import com.bfchengnuo.webflux.ext.beans.MethodInfo;
import com.bfchengnuo.webflux.ext.beans.ServerInfo;

/**
 * 处理 Rest 请求的抽取接口
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public interface RestHandle {
    /**
     * 初始化服务器信息
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用 Restful 接口返回结果
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
