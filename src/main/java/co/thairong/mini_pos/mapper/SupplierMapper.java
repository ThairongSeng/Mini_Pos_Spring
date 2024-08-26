package co.thairong.mini_pos.mapper;


import co.thairong.mini_pos.model.dto.SupplierDto;
import co.thairong.mini_pos.model.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier supplierDtoToSupplier(SupplierDto supplierDto);

    SupplierDto supplierToSupplierDto(Supplier supplier);

}
