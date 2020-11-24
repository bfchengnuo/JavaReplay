package com.bfchengnuo.uselibraries;

import com.bfchengnuo.uselibraries.mapstruct.Car;
import com.bfchengnuo.uselibraries.mapstruct.CarDto;
import com.bfchengnuo.uselibraries.mapstruct.CarMapper;
import com.bfchengnuo.uselibraries.mapstruct.CarType;
import org.junit.Test;

/**
 * @author 冰封承諾Andy
 * @date 2020/11/9
 */
public class MapCarToDtoTest {

    @Test
    public void shouldMapCarToDto() {
        //given
        Car car = new Car( "Morris", 5, CarType.TYPE1 );

        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        System.out.println(car);
        System.out.println(carDto);
    }
}
