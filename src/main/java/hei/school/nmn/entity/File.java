package hei.school.nmn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file")
@AllArgsConstructor
@Getter
@Setter
public class File {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "created_at", nullable = false)
  private Instant createdAt;
}
