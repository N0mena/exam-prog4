package hei.school.nmn.endpoint.rest.controller.model;

import hei.school.nmn.entity.File;
import hei.school.nmn.service.FileService;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("api/file")
public class FileController {
  private final FileService fileService;

  @PostMapping(consumes = "multipart/form-data")
  public ResponseEntity<File> soumettre(
      @RequestParam("email") String email, @RequestParam("file") MultipartFile file)
      throws IOException {

    File submission = fileService.submit(email, file);
    return ResponseEntity.status(HttpStatus.CREATED).body(submission);
  }

  @GetMapping
  public ResponseEntity<List<File>> findAll() {
    return ResponseEntity.ok(fileService.findAll());
  }
}
