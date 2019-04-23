package com.bfchengnuo.designpattern.factory.Simple;

/**
 * Created by 冰封承諾Andy on 5/3/2017.
 * 简单工厂模式，工厂类角色
 * 负责生产对象，根据要求
 */
class LoliManager {

    // 返回的类型是接口，实际上返回的实现此接口的具体对象
    static Loli factory(String type) {
        switch (type) {
            case "stan":
                // 如果对象的实例化需要很多步骤的话
                return new StandardLoli();
            case "leg":
                return new LegitimateLoli();
            default:
                throw new RuntimeException("没有找到相应的匹配");
        }
    }
}
