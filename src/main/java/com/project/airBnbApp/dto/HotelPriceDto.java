package com.project.airBnbApp.dto;

import com.project.airBnbApp.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelPriceDto {

    private Hotel hotel;
    private Double price;

}
