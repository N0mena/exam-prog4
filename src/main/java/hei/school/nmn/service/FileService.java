package hei.school.nmn.service;

import hei.school.nmn.endpoint.event.EventProducer;
import hei.school.nmn.endpoint.event.model.SubmissionEmailRequested;
import hei.school.nmn.entity.File;
import hei.school.nmn.file.bucket.BucketComponent;
import hei.school.nmn.repository.FileRepository;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class FileService {
  private final ImageService imageService;
  private final BucketComponent bucketComponent;
  private final FileRepository fileRepository;
  private final EventProducer<SubmissionEmailRequested> eventProducer;

  @SneakyThrows
  public File submit(String email, MultipartFile file) {

    String title = UUID.randomUUID() + "_" + file.getOriginalFilename();

    java.io.File tempFile = java.io.File.createTempFile("nb-", ".jpg");
    java.io.File fileWB = imageService.convertIntoWhiteAndBlanc(file, tempFile.getAbsolutePath());

    bucketComponent.upload(fileWB, title);

    fileWB.delete();
    tempFile.delete();

    File submission = fileRepository.save(new File(UUID.randomUUID(), title, email, Instant.now()));

    var event = SubmissionEmailRequested.builder().email(email).bucketKey(title).build();
    eventProducer.accept(List.of(event));

    return submission;
  }

  public List<File> findAll() {
    return fileRepository.findAll();
  }
}
