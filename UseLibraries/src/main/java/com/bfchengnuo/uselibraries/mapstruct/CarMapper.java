package com.bfchengnuo.uselibraries.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 使用 @Mapper(componentModel = "spring") 可使用 Spring 的注入
 * @author kerronex
 */
@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    /**
     * 具体转换映射关系
     * 类型不一致使用自定义方法转换：
     *      属性 expression = "java(com.java.xxx.DateTransform.strToDate(car.getXX()))",
     * @param car 入参，可以是多个，使用 car.xxx 来指定
     * @return 转换后的对象
     */
    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount")
    })
    CarDto carToCarDto(Car car);
}