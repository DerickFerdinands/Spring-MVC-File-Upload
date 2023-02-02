package lk.ijse.spring.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileController {

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("/path/to/uploaded_file.txt");
                Files.write(path, bytes);

                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded '" + file.getOriginalFilename() + "'");
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("message", "Failed to upload '" + file.getOriginalFilename() + "'");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Failed to upload '" + file.getOriginalFilename() + "' because it was empty");
        }

        return "redirect:/upload_status";
    }
}
