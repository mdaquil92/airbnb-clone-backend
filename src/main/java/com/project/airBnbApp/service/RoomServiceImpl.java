package com.project.airBnbApp.service;

import com.project.airBnbApp.dto.RoomDto;
import com.project.airBnbApp.entity.Hotel;
import com.project.airBnbApp.entity.Room;
import com.project.airBnbApp.entity.User;
import com.project.airBnbApp.exception.ResourceNotFoundException;
import com.project.airBnbApp.exception.UnAuthorizedException;
import com.project.airBnbApp.repository.HotelRepository;
import com.project.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.airBnbApp.util.AppUtils.getCurrentUser;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

   @Transactional
    @Override
    public RoomDto createNewRoom(Long hotelId ,RoomDto roomDto) {
       log.info("creating a new room inn hotel with Id:{}",hotelId);
        Hotel hotel=hotelRepository
                .findById(hotelId)
                .orElseThrow(()->new ResourceNotFoundException
                        ("Hotel Not Found with ID :"+hotelId));

       User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       if(!user.equals(hotel.getOwner())){
           throw new UnAuthorizedException("This user does not own this hotel with id : "+hotelId);
       }

        Room room=modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room=roomRepository.save(room);

        if(hotel.getActive()){
        inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room,RoomDto.class);

    }

    @Override
    public List<RoomDto> getAllRoomInHotel(Long hotelId) {
        log.info("Getting all room in hotel with ID:{}",hotelId);
        Hotel hotel=hotelRepository
                .findById(hotelId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Hotel not found with Id:"+hotelId));

        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotel with id : "+hotelId);
        }

        return hotel.getRooms()
                .stream()
                .map(element->modelMapper.map(element,RoomDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room with ID: {}",roomId);
        Room room=roomRepository
                .findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with Id: "+roomId));

        return modelMapper.map(room,RoomDto.class);
    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with Id {}", roomId);
        Room room=roomRepository
                .findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with Id: "+roomId));

        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.equals(room.getHotel())){
            throw new UnAuthorizedException("This user does not own this hotel with id : "+roomId);
        }

        inventoryService.deleteAllInventories(room);

        roomRepository.deleteById(roomId);

    }

    @Override
    @Transactional
    public RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto) {
        log.info("Updating the Room with ID: {}",roomId);
        Hotel hotel=hotelRepository
                .findById(hotelId)
                .orElseThrow(
                        ()->new ResourceNotFoundException("Hotel not found with Id: "+hotelId));

         User user=getCurrentUser();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotel with id : "+hotelId);
        }

        Room room=roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not found with Id: "+roomId));

        modelMapper.map(roomDto,room);
        room.setId(roomId);

        //TODO : if price or inventory is updated , then update the inventory for this room

        room=roomRepository.save(room);
        return  modelMapper.map(room,RoomDto.class);
    }
}
