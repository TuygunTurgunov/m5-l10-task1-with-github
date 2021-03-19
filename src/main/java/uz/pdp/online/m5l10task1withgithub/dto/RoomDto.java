package uz.pdp.online.m5l10task1withgithub.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
    private  Boolean isActive;
}