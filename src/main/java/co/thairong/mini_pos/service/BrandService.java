package co.thairong.mini_pos.service;

import co.thairong.mini_pos.dto.BrandDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {

    BrandDto saveData(BrandDto brandDto);

    BrandDto getById(Long brandId);

    BrandDto updateData(Long id, BrandDto brandDto);

    List<BrandDto> getAllData();

    void deleteData(Long id);

    Page<BrandDto> getWithPagination(Map<String, String> params);

}
