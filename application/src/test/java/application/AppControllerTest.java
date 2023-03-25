package application;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import app.Application;
import dto.UserDto;
import dto.UserDto.RoleDto;
import interfaces.UserService;

@SpringBootTest
@ContextConfiguration(classes = Application.class)
@AutoConfigureMockMvc
public class AppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void whenFindAllUsersSuccess() throws Exception {
        Page<UserDto> page = new PageImpl<>(List
                .of(new UserDto(3, "John", "Smith", "William", "jsmith@mail.com", RoleDto.CUSTOMER_USER)));
        when(userService.findAll()).thenReturn(page);
        List<UserDto> users = page.getContent();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String outputJson = objectWriter.writeValueAsString(users);
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON).content(outputJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void whenThereIsNoUsers() throws Exception {
        Page<UserDto> page = new PageImpl<>(new ArrayList<>());
        when(userService.findAll()).thenReturn(page);
        List<UserDto> users = page.getContent();
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String outputJson = objectWriter.writeValueAsString(users);
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON).content(outputJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenAddUserSuccess() throws Exception {
        UserDto user = new UserDto(3, "John", "Smith", "William", "jsmith@mail.com", RoleDto.CUSTOMER_USER);
        when(userService.addUser(user)).thenReturn(user);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String inputJson = objectWriter.writeValueAsString(user);
        mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON).content(inputJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
