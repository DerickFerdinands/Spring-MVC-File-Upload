package lk.ijse.spring.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileController {

    @PostMapping()
    public ArrayList<Byte> submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        System.out.println("Invoked");
        modelMap.addAttribute("file", file);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("C:\\Users\\deric\\Downloads\\filename.jpeg");
                Files.write(path, bytes);
                file.transferTo(path);
                byte[] imageBytes = Files.readAllBytes(path);
                ArrayList<Byte> byteStream = new ArrayList<>();
                for (byte b : imageBytes) {
                    byteStream.add(b);
                }
                return byteStream;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
