package sb.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private String address;
    private String phoneNo;

    private MultipartFile profileImage;   // single image
    private List<MultipartFile> idDocs;   // multiple ID documents
}
