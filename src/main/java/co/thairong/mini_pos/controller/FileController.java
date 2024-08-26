package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.model.dto.FileDto;
import co.thairong.mini_pos.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping
    public BaseRest<?> uploadSingleFile(@RequestPart("file") MultipartFile file) {

        FileDto fileDto = fileService.uploadSingleFile(file);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("File has been uploaded successfully")
                .data(fileDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/images")
    public BaseRest<?> uploadSingleFile(@RequestPart("files") List<MultipartFile> files) {

        List<FileDto> fileDto = fileService.uploadMultipleFiles(files);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Files have been uploaded successfully")
                .data(fileDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
