package controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import controller.util.PaginationUtil;
import dto.UserDto;
import interfaces.UserService;

@RestController
public class AppController {

    private static final Logger log = LogManager.getLogger(AppController.class);

    private final UserService userService;
    private final PaginationUtil paginationUtil;

    public AppController(UserService userService, PaginationUtil paginationUtil) {
        this.userService = userService;
        this.paginationUtil = paginationUtil;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> findAllUsers(HttpServletRequest req) {
        Optional<Pageable> pageable = paginationUtil.getPaginationRest(req);
        Page<UserDto> users = pageable.isPresent() ? userService.findAll(pageable.get()) : userService.findAll();
        if (users.isEmpty()) {
            log.warn("No users yet");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info("Getting all users");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> addUser(@RequestBody @Valid UserDto userDto) {
        UserDto user = userService.addUser(userDto);
        log.info("New user added");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
