package com.example.demo.user.mapper;

import com.example.demo.user.dto.UserDTO;
import com.example.demo.user.dto.UserListDTO;
import com.example.demo.user.dto.UserMinimalDTO;
import com.example.demo.user.model.User;
import org.mapstruct.*;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "user.username")
    })
    UserMinimalDTO userMinimalFromUser(User user);

    @Mappings({
            @Mapping(target = "name", source = "user.username"),
            @Mapping(target = "roles", ignore = true)
    })
    UserListDTO userListDtoFromUser(User user);

    @Mappings({
            @Mapping(target = "username", source = "userDTO.username"),
            @Mapping(target = "roles", ignore = true)
    })
    User userFromDtoToUser(UserDTO userDTO);

    @Mappings({
            @Mapping(target = "username", source = "user.username"),
            @Mapping(target = "roles", ignore = true)
    })
    UserDTO userFromUserToDto(User user);

    @AfterMapping
    default void populateRoles(User user, @MappingTarget UserListDTO userListDTO) {
        userListDTO.setRoles(user.getRoles().stream().map(role -> role.getName().name()).collect(Collectors.toSet()));
    }


}

