package net.csonic.graphqlchallenge.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class FileController {

    @GetMapping("/schema")
    public ResponseEntity<Resource> getImage() throws Exception {

        ClassPathResource resource = new ClassPathResource("static/demo.png");

        // Return ResponseEntity with image content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
