package uz.pdp.online.m5l10task1withgithub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.m5l10task1withgithub.dto.RoomDto;
import uz.pdp.online.m5l10task1withgithub.entity.Hotel;
import uz.pdp.online.m5l10task1withgithub.entity.Room;
import uz.pdp.online.m5l10task1withgithub.repository.HotelRepository;
import uz.pdp.online.m5l10task1withgithub.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        if (roomDto.getNumber()==null&& roomDto.getFloor()==null&&roomDto.getSize()==null&&roomDto.getHotelId()==null
        && roomDto.getIsActive()==null)
            return "don't give null field";

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "hotel not found by id";

        boolean exists = roomRepository.existsByNumberAndFloorAndSizeAndHotel_Id(roomDto.getNumber(), roomDto.getFloor(), roomDto.getSize(), roomDto.getHotelId());
        if (exists)
            return "This room in this hotel already exists";

        Room room=new Room();
        room.setFloor(roomDto.getFloor());
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        room.setIsActive(roomDto.getIsActive());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "room saved";
    }

    //Read  pageable
    @GetMapping
    public Page<Room>rooms(@RequestParam Integer page){

        Pageable pageable=PageRequest.of(page,2);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage;
    }


    //Read by hotel id pageable
    @GetMapping("/getByHotelId/{hotelId}")
    public Page<Room> roomPage(@PathVariable Integer hotelId ,@RequestParam Integer page){
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (!optionalHotel.isPresent())
            return null;
        Pageable pageable= PageRequest.of(page,3);
        Page<Room> roomPage = roomRepository.findAllByHotel_Id(hotelId, pageable);
        return roomPage;
    }

    //Reed room id
    @GetMapping("/{getByRoomId}")
    public Room room(@PathVariable Integer getByRoomId){
        Optional<Room> optionalRoom = roomRepository.findById(getByRoomId);
        if (optionalRoom.isPresent())
            return optionalRoom.get();

        return null;

    }


    @PutMapping("/{id}")
    public  String editRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "room not found by id";

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent())
            return "hotel not found by id";

        boolean exists = roomRepository.existsByNumberAndFloorAndSizeAndHotel_Id(roomDto.getNumber(), roomDto.getFloor(), roomDto.getSize(), roomDto.getHotelId());
        if (exists)
            return "This room already exists";

        Room room = optionalRoom.get();
        room.setHotel(optionalHotel.get());
        room.setIsActive(roomDto.getIsActive());
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "room edited";
    }



@DeleteMapping ("/{id}")
    public String deleteRoom(@PathVariable Integer id){

    Optional<Room> optionalRoom = roomRepository.findById(id);
    if (optionalRoom.isPresent()){
        Room room = optionalRoom.get();
        room.setIsActive(false);
        roomRepository.save(room);
        return "room isActive is false";
    }
    return "room not found by id";
}


}
