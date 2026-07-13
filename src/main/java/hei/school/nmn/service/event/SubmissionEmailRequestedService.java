package hei.school.nmn.service.event;

import hei.school.nmn.endpoint.event.model.SubmissionEmailRequested;
import hei.school.nmn.file.bucket.BucketComponent;
import hei.school.nmn.mail.Email;
import hei.school.nmn.mail.Mailer;
import jakarta.mail.internet.InternetAddress;
import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmissionEmailRequestedService implements Consumer<SubmissionEmailRequested> {
  private final Mailer mailer;
  private final BucketComponent bucketComponent;

  @SneakyThrows
  @Override
  public void accept(SubmissionEmailRequested event) {
    String lienS3 = bucketComponent.presign(event.getBucketKey(), Duration.ofDays(7)).toString();

    InternetAddress destinataire = new InternetAddress(event.getEmail());
    Email email =
        new Email(
            destinataire,
            List.of(),
            List.of(),
            "Your Image in Black and White",
            "Here the final result of the image: " + lienS3,
            List.of());

    mailer.accept(email);
  }
}
