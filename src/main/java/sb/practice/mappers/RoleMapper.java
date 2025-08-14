package sb.practice.mappers;

import sb.practice.dto.RoleDTO;
import sb.practice.entities.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }

    public static Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }
}

