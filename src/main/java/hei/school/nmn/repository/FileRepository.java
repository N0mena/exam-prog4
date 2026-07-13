package hei.school.nmn.repository;

import hei.school.nmn.entity.File;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, UUID> {}
