package sb.practice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb.practice.dto.RoleDTO;
import sb.practice.entities.Role;
import sb.practice.exceptions.ResourceNotFoundException;
import sb.practice.exceptions.ResourceAlreadyExistsException;
import sb.practice.mappers.RoleMapper;
import sb.practice.repositories.RoleRepository;

import java.util.List;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO createRole(RoleDTO roleDTO) {
        // Check if role name already exists
        if (roleRepository.existsByRoleName(roleDTO.getRoleName())) {
            throw new ResourceAlreadyExistsException("Role", "roleName" , roleDTO.getRoleName());
        }

        Role role = RoleMapper.toEntity(roleDTO);
        return RoleMapper.toDTO(roleRepository.save(role));
    }

    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        if (roles.isEmpty()) {
            throw new ResourceNotFoundException("No roles found in the system.");
        }

        return roles.stream()
                .map(r -> new RoleDTO(r.getRoleId(), r.getRoleName()))
                .toList();
    }

    public RoleDTO getRoleById(int id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + id + " not found"));

        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }

    public RoleDTO updateRole(int id, RoleDTO roleDTO) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + id + " not found."));

        existingRole.setRoleName(roleDTO.getRoleName());
        Role updatedRole = roleRepository.save(existingRole);

        return RoleMapper.toDTO(updatedRole);
    }


    public RoleDTO deleteRole(int id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role with ID " + id + " not found."));

        roleRepository.delete(role);

        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }

}