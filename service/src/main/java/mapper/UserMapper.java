package mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dto.UserDto;
import entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role", target = "role")
    UserDto toDto(User user);

    @Mapping(source = "role", target = "role")
    User toEntity(UserDto userDto);
}
