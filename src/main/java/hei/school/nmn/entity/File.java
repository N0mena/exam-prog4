package hei.school.nmn.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "file")
@AllArgsConstructor
@Getter
@Setter
public class File {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "email")
    private String email;
    
}
