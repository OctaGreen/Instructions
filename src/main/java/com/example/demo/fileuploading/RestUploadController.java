package com.example.demo.fileuploading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.fileuploading.StorageService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestUploadController {
    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @PostMapping("/uploadfile")
    public String uploadFileMulti(@RequestParam("uploadfile") MultipartFile file) throws Exception {
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());
            return "You successfully uploaded - " + file.getOriginalFilename();
        } catch (Exception e) {
            Logger log = LoggerFactory.getLogger(RestUploadController.class);
            log.info("An error occured during file upload: " + Arrays.toString(e.getStackTrace()));
            return "An error occured during file upload";
        }
    }

    @GetMapping("/getallfiles")
    public List<String> getListFiles() {
        List<String> lstFiles = new ArrayList<String>();
        try{
            lstFiles = files.stream()
                    .map(fileName -> MvcUriComponentsBuilder
                            .fromMethodName(RestUploadController.class, "getFile", fileName).build().toString())
                    .collect(Collectors.toList());
        }catch(Exception e){
            throw e;
        }
        for(String s: lstFiles){
            System.out.println(s);
        }
        return lstFiles;
    }

    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
