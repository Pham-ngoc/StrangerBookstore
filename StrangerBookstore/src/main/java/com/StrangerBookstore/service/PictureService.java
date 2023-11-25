package com.StrangerBookstore.service;

import com.StrangerBookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PictureService {

    @Autowired
    ResourceLoader resourceLoader;

    public String getImageName(@RequestParam("product_img") MultipartFile file) {
        String filename = "";
        try {
            String uploadRootPath = resourceLoader.getResource("classpath:static/product_img/").getFile()
                    .getAbsolutePath();

            if (!Files.exists(Paths.get(uploadRootPath))) {
                Files.createDirectories(Paths.get(uploadRootPath));
            }

            filename = file.getOriginalFilename();
            Path filePath = Paths.get(uploadRootPath, filename);
            Files.write(filePath, file.getBytes());
            // Kiểm tra định dạng của tệp
            boolean isImage = Files.probeContentType(filePath).startsWith("image");
            return filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filename;
    }
}
