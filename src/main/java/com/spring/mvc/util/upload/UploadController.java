package com.spring.mvc.util.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class UploadController {
//    첨부파일 경로 지정 -> 깃허브에 노출돼서 올라가면 보안에 취약
//    application.properties에 작성하고 application.properties은 안올림
//    file.upload.root-path : 이거 이름은 마음대로 작성
    //첨부파일 저장 루트경로
    @Value("${file.upload.root-path}")
    private String rootPath;


    @GetMapping("/upload-form")
    public String uploadForm(){
        return "/upload/upload-form";
    }

    @PostMapping("/upload-file")
//    MultipartFile : 이렇게 받아야 클라가 주는 첨부파일 받을 수 있음
//    thumbnail : 이거 jsp의 name 속성이랑 맞춰주거나 @RequestParam해주거나
    public String uploadForm(@RequestParam("thumbnail") MultipartFile file) {
        log.info("file-name : {}", file.getOriginalFilename()); //file-name : 현대카드 헤더 로고.png
        log.info("file-size : {}KB", (double) file.getSize() / 1024); //file-size : 1.3955078125KB
        log.info("file-type : {}", file.getContentType()); //file-type : image/png

        // 첨부파일을 스토리지에 저장
        // 1. 루트 디렉토리 생성
        File root = new File(rootPath);
        if (!root.exists()) root.mkdirs();

        // 2. 파일을 해당 경로에 저장
//        File uploadFile
//                = new File(rootPath, file.getOriginalFilename());
//
//        try {
//            file.transferTo(uploadFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        위에 대신 이렇게
        String filePath = FileUtil.uploadFile(file,rootPath);

        return "redirect:/upload-form";
    }
}
