package entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Hibernate;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastName;
    private String firstName;
    private String secondName;
    private String email;
    @Column(name = "role_id")
    private Role role;

    public User() {
    }

    public User(Integer id, String lastName, String firstName, String secondName, String email, Role role) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        User other = (User) obj;
        return id != null && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", secondName=" + secondName
                + ", email=" + email + ", role=" + role + "]";
    }

    public enum Role {
        ADMINISTRATOR(1), SALE_USER(2), CUSTOMER_USER(3), SECURE_API_USER(4);

        private final Integer id;

        private Role(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }
    }
}
