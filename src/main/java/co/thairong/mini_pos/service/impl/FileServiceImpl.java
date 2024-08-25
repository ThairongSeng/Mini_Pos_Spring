package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.dto.FileDto;
import co.thairong.mini_pos.service.FileService;
import co.thairong.mini_pos.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {

    private FileUtil fileUtil;

    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public FileDto uploadSingleFile(MultipartFile file) {

        return fileUtil.uploadFile(file);
    }

    @Override
    public List<FileDto> uploadMultipleFiles(List<MultipartFile> files) {
       List<FileDto> fileDtoList = new ArrayList<>();

       for (MultipartFile file : files) {
           fileDtoList.add(uploadSingleFile(file));
       }
       return fileDtoList;
    }
}
