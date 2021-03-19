package uz.pdp.online.m5l10task1withgithub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m5l10task1withgithub.entity.Hotel;
import uz.pdp.online.m5l10task1withgithub.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Integer> {


      boolean existsByNumberAndFloorAndSizeAndHotel_Id(Integer number, Integer floor, Integer size, Integer hotel_id);
      Page<Room>findAllByHotel_Id(Integer hotel_id, Pageable pageable);




}
