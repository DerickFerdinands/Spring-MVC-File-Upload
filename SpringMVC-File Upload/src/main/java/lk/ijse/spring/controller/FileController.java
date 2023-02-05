package lk.ijse.spring.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileController {

    @PostMapping
    public ResponseEntity<byte[]> submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        System.out.println("Invoked");
        modelMap.addAttribute("file", file);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:\\Users\\deric\\Downloads\\filename.jpeg");
                Files.write(path, bytes);
                file.transferTo(path);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                        .body(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<byte[]> getImage() {
        System.out.println("GET");
        try {
            // Read the image file from the specified directory.
            Path path = Paths.get("C:\\Users\\deric\\Downloads\\filename.jpeg");
            byte[] imageBytes = Files.readAllBytes(path);

            // Set the content type and return the image in the response body.
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
