package uz.pdp.online.m5l10task1withgithub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.m5l10task1withgithub.entity.Hotel;
import uz.pdp.online.m5l10task1withgithub.entity.Room;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

    boolean existsByName(String name);



}
