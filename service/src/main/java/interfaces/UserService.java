package interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dto.UserDto;

public interface UserService {
    UserDto addUser(UserDto user);

    Page<UserDto> findAll();

    Page<UserDto> findAll(Pageable pageable);
}
