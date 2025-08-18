package sb.practice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb.practice.dto.ApiResponse;
import sb.practice.dto.PartyDTO;
import sb.practice.services.PartyService;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    // Create Party
    @PostMapping
    public ResponseEntity<ApiResponse<PartyDTO>> createParty(@ModelAttribute PartyDTO partyDTO) {
        PartyDTO savedParty = partyService.addParty(partyDTO);

        ApiResponse<PartyDTO> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Party created successfully",
                savedParty
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Update Party
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PartyDTO>> updateParty(
            @PathVariable int id,
            @ModelAttribute PartyDTO partyDTO) {

        PartyDTO updatedParty = partyService.updateParty(id, partyDTO);

        ApiResponse<PartyDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Party updated successfully",
                updatedParty
        );

        return ResponseEntity.ok(response);
    }

    // Delete Party
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteParty(@PathVariable int id) {
        partyService.deleteParty(id);

        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Party deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }

    // Get Party by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PartyDTO>> getPartyById(@PathVariable int id) {
        PartyDTO party = partyService.getPartyById(id);

        ApiResponse<PartyDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Party retrieved successfully",
                party
        );

        return ResponseEntity.ok(response);
    }

    // Get All Parties
    @GetMapping
    public ResponseEntity<ApiResponse<List<PartyDTO>>> getAllParties() {
        List<PartyDTO> partyList = partyService.getAllParties();

        ApiResponse<List<PartyDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Parties retrieved successfully",
                partyList
        );

        return ResponseEntity.ok(response);
    }
}
