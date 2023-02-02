package lk.ijse.spring.config;

import lk.ijse.spring.controller.FileController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {FileController.class})
public class WebAppConfig {
}
