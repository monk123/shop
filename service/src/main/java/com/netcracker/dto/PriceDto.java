package com.netcracker.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@EqualsAndHashCode
@ToString
@Getter @Setter
public class PriceDto {

    private double priceFrom;
    private double priceTo;
}
