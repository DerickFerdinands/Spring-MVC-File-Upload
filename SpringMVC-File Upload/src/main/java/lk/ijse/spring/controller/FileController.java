package lk.ijse.spring.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileController {

    @PostMapping
    public ResponseEntity<Resource> submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        System.out.println("Invoked");
        modelMap.addAttribute("file", file);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:\\Users\\deric\\Downloads\\filename.jpeg");
                Files.write(path, bytes);
                file.transferTo(path);
                Resource resource = new UrlResource(path.toUri());

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
