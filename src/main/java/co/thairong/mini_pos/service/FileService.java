package co.thairong.mini_pos.service;

import co.thairong.mini_pos.model.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileDto uploadSingleFile(MultipartFile file);

    List<FileDto> uploadMultipleFiles(List<MultipartFile> files);

}
