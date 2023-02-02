package lk.ijse.spring.controller;

import org.springframework.ui.ModelMap;
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
    public String  submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        System.out.println("Invoked");
        modelMap.addAttribute("file", file);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("uploaded_file.txt");
                Files.write(path, bytes);
                System.out.println("Done!");
            } catch (IOException e) {
              e.printStackTrace();
            }
        }
        return "fileUploadView";
    }
}
