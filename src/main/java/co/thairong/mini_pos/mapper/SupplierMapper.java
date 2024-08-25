package co.thairong.mini_pos.mapper;


import co.thairong.mini_pos.dto.SupplierDto;
import co.thairong.mini_pos.entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier supplierDtoToSupplier(SupplierDto supplierDto);

    SupplierDto supplierToSupplierDto(Supplier supplier);

    List<SupplierDto> listSupplierToListSupplierDto(List<Supplier> suppliers);

}
