package uz.pdp.online.m5l10task1withgithub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private Integer floor;

    private Integer size;

    @ManyToOne
    private Hotel hotel;

    private  Boolean isActive;

}