package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.dto.BrandDto;
import co.thairong.mini_pos.entity.Brand;
import co.thairong.mini_pos.mapper.BrandMapper;
import co.thairong.mini_pos.service.BrandService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping()
    public BaseRest<?> createBrand(@Valid @RequestBody BrandDto brandDto) {

        BrandDto brandDto1 = brandService.saveData(brandDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Brand has been created successfully")
                .data(brandDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping("{id}")
    public BaseRest<?> getBrandById(@PathVariable Long id) {

        BrandDto brandDto = brandService.getById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Brand has been found successfully")
                .data(brandDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("{id}")
    public BaseRest<?> updateById(@PathVariable Long id, @Valid @RequestBody BrandDto brandDto) {

        BrandDto brandDto1 = brandService.updateData(id, brandDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Brand has been updated successfully")
                .data(brandDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping()
    public BaseRest<?> getAllBrands() {
        List<BrandDto> brandDtos = brandService.getAllData();

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Brands have been found successfully")
                .data(brandDtos)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        brandService.deleteData(id);
    }


}
