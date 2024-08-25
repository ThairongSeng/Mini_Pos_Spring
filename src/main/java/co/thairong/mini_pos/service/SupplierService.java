package co.thairong.mini_pos.service;


import co.thairong.mini_pos.dto.BrandDto;
import co.thairong.mini_pos.dto.SupplierDto;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface SupplierService {

    SupplierDto createSupplier(SupplierDto supplierDto);

    SupplierDto getById(Long id);

    SupplierDto updateSupplier(Long id, SupplierDto supplierDto);

    void deleteSupplier(Long id);

    Page<SupplierDto> getWithPagination(Map<String, String> params);
}
