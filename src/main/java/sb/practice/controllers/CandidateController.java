package sb.practice.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sb.practice.dto.ApiResponseDTO;
import sb.practice.dto.CandidatePartyDTO;
import sb.practice.services.CandidateService;

@RestController
@RequestMapping("/api/candidates")
@Tag(name = "Candidates", description = "APIs for managing candidates in the system")
public class CandidateController {

    @Autowired
    CandidateService candidateService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<CandidatePartyDTO>> assignCandidate(@RequestBody CandidatePartyDTO dto) {
        CandidatePartyDTO saved = candidateService.assignCandidateToParty(dto);

        ApiResponseDTO<CandidatePartyDTO> response = new ApiResponseDTO<>(
                HttpStatus.CREATED.value(),
                "Candidate assigned to party successfully",
                saved
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
