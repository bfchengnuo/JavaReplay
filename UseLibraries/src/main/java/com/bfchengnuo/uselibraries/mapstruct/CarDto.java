package com.bfchengnuo.uselibraries.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 冰封承諾Andy
 * @date 2020/11/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDto {
    private String make;
    private int seatCount;
    private String type;
}