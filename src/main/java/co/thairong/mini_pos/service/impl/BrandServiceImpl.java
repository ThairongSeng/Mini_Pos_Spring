package co.thairong.mini_pos.service.impl;


import co.thairong.mini_pos.dto.BrandDto;
import co.thairong.mini_pos.entity.Brand;
import co.thairong.mini_pos.mapper.BrandMapper;
import co.thairong.mini_pos.repository.BrandRepository;
import co.thairong.mini_pos.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public BrandDto saveData(BrandDto brandDto) {
        Brand brand = brandMapper.brandDtoToBrand(brandDto);
        brandRepository.save(brand);

        return brandMapper.brandToBrandDto(brand);
    }

    @Override
    public BrandDto getById(Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Brand with id %d not found", brandId)));

        return brandMapper.brandToBrandDto(brand);
    }


    @Override
    public BrandDto updateData(Long id, BrandDto brandDto) {
        boolean isExist = brandRepository.existsById(id);

        if (isExist) {
            Brand brand = brandMapper.brandDtoToBrand(brandDto);
            brand.setId(id);
            brandRepository.save(brand);
            return this.getById(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Product with id %d not found", id));
    }

    @Override
    public List<BrandDto> getAllData() {
        List<Brand> brands = brandRepository.findAll();

        return brandMapper.listBrandToListBrandDto(brands);
    }
    @Override
    public void deleteData(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Brand with id %d not found", id));
        }
    }
}
