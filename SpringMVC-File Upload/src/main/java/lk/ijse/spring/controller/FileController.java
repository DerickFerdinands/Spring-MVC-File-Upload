package lk.ijse.spring.controller;

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
    public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        System.out.println("Invoked");
        modelMap.addAttribute("file", file);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File myObj = new File("C:\\Users\\deric\\Downloads\\filename.png");
                System.out.println(file.getName());
                myObj.createNewFile();
                System.out.println("File created: " + myObj.getName());
                Path path = Paths.get("C:\\Users\\deric\\Downloads\\filename.png");
                Files.write(path, bytes);
                System.out.println("Done!");


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "fileUploadView";
    }
}
