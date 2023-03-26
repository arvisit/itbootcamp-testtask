package dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import constants.ValidationConstants;

@JsonPropertyOrder({ "fullName", "email", "role" })
public class UserDto {
    @JsonIgnore
    private Integer id;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank(message = "{msg.error.field.notblank}")
    @Size(min = 1, max = 40, message = "{msg.error.userdto.lastname.length}")
    @Pattern(regexp = ValidationConstants.LATIN_ONLY, message = "{msg.error.field.onlylatin")
    private String lastName;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank(message = "{msg.error.field.notblank}")
    @Size(min = 1, max = 20, message = "{msg.error.userdto.firstname.length}")
    @Pattern(regexp = ValidationConstants.LATIN_ONLY, message = "{msg.error.field.onlylatin")
    private String firstName;

    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank(message = "{msg.error.field.notblank}")
    @Size(min = 1, max = 40, message = "{msg.error.userdto.secondname.length}")
    @Pattern(regexp = ValidationConstants.LATIN_ONLY, message = "{msg.error.field.onlylatin")
    private String secondName;

    @NotBlank(message = "{msg.error.field.notblank}")
    @Size(max = 50, message = "{msg.error.userdto.email.length}")
    @Email(message = "{msg.error.userdto.email.patern}")
    private String email;

    private RoleDto role;

    public UserDto() {
    }

    public UserDto(Integer id,
            @NotBlank(message = "{msg.error.field.notblank}") @Size(min = 1, max = 40, message = "{msg.error.userdto.lastname.length}") @Pattern(regexp = "[a-zA-Z]*", message = "{msg.error.field.onlylatin") String lastName,
            @NotBlank(message = "{msg.error.field.notblank}") @Size(min = 1, max = 20, message = "{msg.error.userdto.firstname.length}") @Pattern(regexp = "[a-zA-Z]*", message = "{msg.error.field.onlylatin") String firstName,
            @NotBlank(message = "{msg.error.field.notblank}") @Size(min = 1, max = 40, message = "{msg.error.userdto.secondname.length}") @Pattern(regexp = "[a-zA-Z]*", message = "{msg.error.field.onlylatin") String secondName,
            @NotBlank(message = "{msg.error.field.notblank}") @Size(max = 50, message = "{msg.error.userdto.email.length}") @Email(message = "{msg.error.userdto.email.patern}") String email,
            RoleDto role) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    @JsonProperty(value = "fullName", access = Access.READ_ONLY)
    public String getFullName() {
        return (firstName + " " + secondName + " " + lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (Hibernate.getClass(this) != Hibernate.getClass(obj))
            return false;
        UserDto other = (UserDto) obj;
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "UserDto [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", secondName="
                + secondName + ", email=" + email + ", role=" + role + "]";
    }

    public enum RoleDto {
        ADMINISTRATOR(1), SALE_USER(2), CUSTOMER_USER(3), SECURE_API_USER(4);

        private final Integer id;

        private RoleDto(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }
    }
}

