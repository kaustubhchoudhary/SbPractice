package sb.practice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb.practice.dto.ApiResponseDTO;
import sb.practice.dto.RoleDTO;
import sb.practice.services.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "APIs for managing roles in the system")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Create a new role", description = "Add a new role to the system")
    @ApiResponse(responseCode = "201", description = "Role created successfully")
    @PostMapping
    public ResponseEntity<ApiResponseDTO<RoleDTO>> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO savedRole = roleService.createRole(roleDTO);

        ApiResponseDTO<RoleDTO> response = new ApiResponseDTO<>(
                HttpStatus.CREATED.value(),
                "Role created successfully",
                savedRole
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all roles", description = "Retrieve all roles available in the system")
    @ApiResponse(responseCode = "200", description = "List of roles retrieved successfully")
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<RoleDTO>>> getAllRoles() {
        List<RoleDTO> roleList = roleService.getAllRoles();

        ApiResponseDTO<List<RoleDTO>> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Roles retrieved successfully",
                roleList
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get role by ID", description = "Retrieve details of a role by its ID")
    @ApiResponse(responseCode = "200", description = "Role found")
    @ApiResponse(responseCode = "404", description = "Role not found")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> getRoleById(@PathVariable int id) {
        RoleDTO roleDTO = roleService.getRoleById(id);

        ApiResponseDTO<RoleDTO> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Role retrieved successfully",
                roleDTO
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update an existing role", description = "Update details of a role by ID")
    @ApiResponse(responseCode = "200", description = "Role updated successfully")
    @ApiResponse(responseCode = "404", description = "Role not found")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> updateRole(@PathVariable int id, @RequestBody RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);

        ApiResponseDTO<RoleDTO> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Role updated successfully",
                updatedRole
        );

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete role", description = "Delete a role by its ID")
    @ApiResponse(responseCode = "200", description = "Role deleted successfully")
    @ApiResponse(responseCode = "404", description = "Role not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<RoleDTO>> deleteRole(@PathVariable Integer id) {
        RoleDTO deletedRole = roleService.deleteRole(id);

        ApiResponseDTO<RoleDTO> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Role deleted successfully",
                deletedRole
        );

        return ResponseEntity.ok(response);
    }
}