package converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import entity.User.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getId();
    }

    @Override
    public Role convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        }
        return Stream.of(Role.values())
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
