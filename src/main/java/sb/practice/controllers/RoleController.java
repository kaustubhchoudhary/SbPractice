package sb.practice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb.practice.dto.ApiResponse;
import sb.practice.dto.RoleDTO;
import sb.practice.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleDTO>> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO savedRole = roleService.createRole(roleDTO);

        ApiResponse<RoleDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Role created successfully",
                savedRole
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getAllRoles() {
        List<RoleDTO> roleList = roleService.getAllRoles();

        ApiResponse<List<RoleDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Roles retrieved successfully",
                roleList
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> getRoleById(@PathVariable int id) {
        RoleDTO roleDTO = roleService.getRoleById(id);

        ApiResponse<RoleDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Role retrieved successfully",
                roleDTO
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);

        ApiResponse<RoleDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Role updated successfully",
                updatedRole
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<RoleDTO>> deleteRole(@PathVariable Integer id) {
        RoleDTO deletedRole = roleService.deleteRole(id);

        ApiResponse<RoleDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Role deleted successfully",
                deletedRole
        );

        return ResponseEntity.ok(response);
    }
}
