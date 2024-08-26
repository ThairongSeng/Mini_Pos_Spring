package co.thairong.mini_pos.mapper;


import co.thairong.mini_pos.model.dto.BrandDto;
import co.thairong.mini_pos.model.entity.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand brandDtoToBrand(BrandDto brandDto);

    BrandDto brandToBrandDto(Brand brand);

    List<BrandDto> listBrandToListBrandDto(List<Brand> brand);
}
