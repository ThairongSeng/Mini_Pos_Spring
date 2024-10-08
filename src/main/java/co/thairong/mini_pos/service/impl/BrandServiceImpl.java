package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.model.dto.BrandDto;
import co.thairong.mini_pos.model.entity.Brand;
import co.thairong.mini_pos.mapper.BrandMapper;
import co.thairong.mini_pos.repository.BrandRepository;
import co.thairong.mini_pos.service.BrandService;
import co.thairong.mini_pos.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;


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
        BrandDto existingBrandDto = getById(id);
        Brand existingBrand = brandMapper.brandDtoToBrand(existingBrandDto);

        existingBrand.setId(id);
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
        BrandDto existingBrand = getById(id);
        Brand brandOptional = brandMapper.brandDtoToBrand(existingBrand);

        brandOptional.setId(id);
        brandOptional.setDeleted(true);

        brandRepository.save(brandOptional);
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
            brandPage = brandRepository.findByIsDeletedFalseOrderByIdDesc(pageable);
        }

        // Convert to DTOs
        Page<BrandDto> brandDtoPage = brandPage.map(brandMapper::brandToBrandDto);

        return brandDtoPage;
    }


}
