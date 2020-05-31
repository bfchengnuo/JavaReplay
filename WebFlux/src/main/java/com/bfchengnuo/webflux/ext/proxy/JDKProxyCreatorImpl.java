package com.bfchengnuo.webflux.ext.proxy;

import com.bfchengnuo.webflux.ext.MpsApi;
import com.bfchengnuo.webflux.ext.beans.MethodInfo;
import com.bfchengnuo.webflux.ext.beans.ServerInfo;
import com.bfchengnuo.webflux.ext.handle.RestHandle;
import com.bfchengnuo.webflux.ext.handle.SimpleClientRestHandleImpl;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通过 JDK 动态代理方式创建 {@link com.bfchengnuo.webflux.ext.UserApi} 代理对象
 *
 * @author 冰封承諾Andy
 * @date 2020/5/28
 */
public class JDKProxyCreatorImpl implements ProxyCreator {

    @Override
    public Object createProxy(Class<?> type) {
        ServerInfo serverInfo = extractServerInfo(type);
        RestHandle restHandle = new SimpleClientRestHandleImpl();

        restHandle.init(serverInfo);

        // JDK Proxy
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                // 代理的接口
                new Class[]{type},
                // 具体执行逻辑，多次执行
                (proxy, method, args) -> {
                    MethodInfo methodInfo = extractMethodInfo(method, args);
                    return restHandle.invokeRest(methodInfo);
                });
    }

    private MethodInfo extractMethodInfo(Method method, Object[] args) {
        MethodInfo methodInfo = new MethodInfo();

        // 可考虑进行重构
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof GetMapping) {
                methodInfo.setMethod(HttpMethod.GET);
                methodInfo.setUrl(((GetMapping) annotation).value()[0]);
            }
            // 判断其他请求方式，是一样的，省略
        }

        // 填充参数，可考虑进行重构
        Map<String, Object> params = new LinkedHashMap<>();
        methodInfo.setParams(params);
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            PathVariable pathVariable = parameters[i].getAnnotation(PathVariable.class);
            // 是否带有 @PathVariable
            if (pathVariable != null) {
                params.put(pathVariable.value(), args[i]);
            }

            // 判断是否带有 @RequestBody
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                methodInfo.setBody((Mono<?>) args[i]);
                methodInfo.setBodyType(extractElementType(parameters[i].getParameterizedType()));
            }
        }

        // 提取返回对象的信息，待重构
        // isAssignableFrom 判断是否是某个类型的子类，而 instanceof 是判断实例的
        boolean isMono = method.getReturnType().isAssignableFrom(Mono.class);
        methodInfo.setReturnMono(isMono);
        // 获得返回类型泛型中的实际类型
        methodInfo.setReturnType(extractElementType(method.getGenericReturnType()));

        return methodInfo;
    }

    /**
     * 获取泛型类型
     */
    private Class<?> extractElementType(Type genericReturnType) {
        Type[] types = ((ParameterizedType) genericReturnType).getActualTypeArguments();
        // 目前泛型只用了一个
        return (Class<?>) types[0];
    }

    private ServerInfo extractServerInfo(Class<?> type) {
        MpsApi annotation = type.getAnnotation(MpsApi.class);
        return ServerInfo.builder()
                .url(annotation.value())
                .build();
    }
}
