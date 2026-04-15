package com.project.airBnbApp.dto;
import com.project.airBnbApp.entity.HotelContactInfo;
import lombok.Data;


@Data
public class HotelDto {
    private Long id;
    private String name;
    private String City;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;


}
