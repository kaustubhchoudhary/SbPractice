package sb.practice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb.practice.dto.ApiResponseDTO;
import sb.practice.dto.PartyDTO;
import sb.practice.services.PartyService;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
@Tag(name = "Parties", description = "APIs for managing parties in the system")
public class PartyController {

    @Autowired
    private PartyService partyService;

    // Create Party
    @PostMapping
    public ResponseEntity<ApiResponseDTO<PartyDTO>> createParty(@ModelAttribute PartyDTO partyDTO) {
        PartyDTO savedParty = partyService.addParty(partyDTO);

        ApiResponseDTO<PartyDTO> response = new ApiResponseDTO<>(
                HttpStatus.CREATED.value(),
                "Party created successfully",
                savedParty
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update Party
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<PartyDTO>> updateParty(
            @PathVariable int id,
            @ModelAttribute PartyDTO partyDTO) {

        PartyDTO updatedParty = partyService.updateParty(id, partyDTO);

        ApiResponseDTO<PartyDTO> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Party updated successfully",
                updatedParty
        );

        return ResponseEntity.ok(response);
    }

    // Delete Party
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteParty(@PathVariable int id) {
        partyService.deleteParty(id);

        ApiResponseDTO<Void> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Party deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }

    // Get Party by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<PartyDTO>> getPartyById(@PathVariable int id) {
        PartyDTO party = partyService.getPartyById(id);

        ApiResponseDTO<PartyDTO> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Party retrieved successfully",
                party
        );

        return ResponseEntity.ok(response);
    }

    // Get All Parties
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<PartyDTO>>> getAllParties() {
        List<PartyDTO> partyList = partyService.getAllParties();

        ApiResponseDTO<List<PartyDTO>> response = new ApiResponseDTO<>(
                HttpStatus.OK.value(),
                "Parties retrieved successfully",
                partyList
        );

        return ResponseEntity.ok(response);
    }
}
