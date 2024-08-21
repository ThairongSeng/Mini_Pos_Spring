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
import java.util.Optional;

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
        Brand brand = brandRepository.findByIdAndIsDeletedFalse(brandId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Brand with id %d not found", brandId)));

        return brandMapper.brandToBrandDto(brand);
    }


    @Override
    public BrandDto updateData(Long id, BrandDto brandDto) {
        Brand existingBrand = brandRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Brand with id %d not found", id)));

        existingBrand.setName(brandDto.name());
        brandRepository.save(existingBrand);
        return brandMapper.brandToBrandDto(existingBrand);
    }

    @Override
    public List<BrandDto> getAllData() {
        List<Brand> brands = brandRepository.findByIsDeletedFalse();

        return brandMapper.listBrandToListBrandDto(brands);
    }


    @Override
    public void deleteData(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);

        if (brandOptional.isPresent()) {
            Brand brand = brandOptional.get();
            brand.setDeleted(true);
            brandRepository.save(brand);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Brand with id %d not found", id));
        }
    }

}
