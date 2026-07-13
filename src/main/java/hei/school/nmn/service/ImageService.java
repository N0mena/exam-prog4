package hei.school.nmn.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImageService {

  public java.io.File convertIntoWhiteAndBlanc(MultipartFile file, String tempFilePath) throws IOException {
    BufferedImage originalImage = ImageIO.read(file.getInputStream());
    if (originalImage == null) {
      throw new IllegalArgumentException("Invalid image file");
    }

    BufferedImage grayscaleImage =
        new BufferedImage(
            originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

    var graphics = grayscaleImage.getGraphics();
    graphics.drawImage(originalImage, 0, 0, null);
    graphics.dispose();

    java.io.File grayscaleFile = new java.io.File(tempFilePath);
    ImageIO.write(grayscaleImage, "jpg", grayscaleFile);

    return grayscaleFile;
  }
}
