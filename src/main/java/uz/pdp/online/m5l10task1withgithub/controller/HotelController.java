package uz.pdp.online.m5l10task1withgithub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m5l10task1withgithub.entity.Hotel;
import uz.pdp.online.m5l10task1withgithub.repository.HotelRepository;

import java.util.Optional;

@RequestMapping("/hotel")
@RestController
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    //Post
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){

        if (hotel.getName()==null)
            return "hotel name is null";
        boolean exists = hotelRepository.existsByName(hotel.getName());
        if (exists)
            return "such hotel already exists";
        Hotel newHotel=new Hotel();
        newHotel.setName(hotel.getName());
        newHotel.setIsActive(hotel.getIsActive());
        hotelRepository.save(newHotel);
        return "Hotel saved";
    }

    //Read
    @GetMapping
    public Page <Hotel> hotelPage(@RequestParam Integer page){
        Pageable pageable= PageRequest.of(page,2);
        Page<Hotel> hotelPage = hotelRepository.findAll(pageable);
        return hotelPage;
    }
    //Read by id
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent())
            return optionalHotel.get();
        return null;
    }

    //Put
    @PutMapping("/{id}")
    public String editHotel (@PathVariable Integer id , @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent())
            return "hotel not found by id";

        boolean existsByName = hotelRepository.existsByName(hotel.getName());
        if (existsByName)
            return "this hotel name already exists";

        Hotel editHotel = optionalHotel.get();
        editHotel.setIsActive(hotel.getIsActive());
        editHotel.setName(hotel.getName());
        hotelRepository.save(editHotel);
        return "hotel edited";
    }
    @DeleteMapping("/{id}")
    public  String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel hotel = optionalHotel.get();
            hotel.setIsActive(false);
            hotelRepository.save(hotel);
            return "hotel isActive is false";
        }
        return "hotel not found by id";

    }
}
