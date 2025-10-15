package com.polstat.perpustakaan.mapper;
import com.polstat.perpustakaan.dto.UserDto;
import com.polstat.perpustakaan.entity.User;
public class UserMapper {
    public static User mapToUser(UserDto userDto){
        return User.builder().id(userDto.getId()).name(userDto.getName())
                .email(userDto.getEmail()).password(userDto.getPassword()).build();
    }
    public static UserDto mapToUserDto(User user){
        return UserDto.builder().id(user.getId()).name(user.getName())
                .email(user.getEmail()).password(user.getPassword()).build();
    }
}