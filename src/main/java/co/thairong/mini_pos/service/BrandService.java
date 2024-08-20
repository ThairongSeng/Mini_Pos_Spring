package co.thairong.mini_pos.service;

import co.thairong.mini_pos.dto.BrandDto;

import java.util.List;

public interface BrandService {

    BrandDto saveData(BrandDto brandDto);

    BrandDto getById(Long brandId);

    BrandDto updateData(Long id, BrandDto brandDto);

    List<BrandDto> getAllData();

    void deleteData(Long id);
}
