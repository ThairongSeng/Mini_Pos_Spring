package co.thairong.mini_pos.service.impl;


import co.thairong.mini_pos.dto.BrandDto;
import co.thairong.mini_pos.entity.Brand;
import co.thairong.mini_pos.mapper.BrandMapper;
import co.thairong.mini_pos.repository.BrandRepository;
import co.thairong.mini_pos.service.BrandService;
import co.thairong.mini_pos.service.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
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
        List<Brand> brands = brandRepository.findByIsDeletedFalseOrderByIdDesc();

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


    @Override
    public Page<BrandDto> getWithPagination(Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        String searchName = PageUtil.SEARCH_NAME;

        // Extract pagination parameters
        if (params.containsKey(PageUtil.PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }

        if (params.containsKey(PageUtil.PAGE_NUM)) {
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUM));
        }

        // Create Pageable object
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);

        // Fetch paginated result
        Page<Brand> brandPage;
        if (params.containsKey(searchName)) {
            String name = params.get(searchName);
            brandPage = brandRepository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name, pageable);
        } else {
            brandPage = brandRepository.findByIsDeletedFalse(pageable);
        }

        // Convert to DTOs
        Page<BrandDto> brandDtoPage = brandPage.map(brandMapper::brandToBrandDto);

        return brandDtoPage;
    }


}
