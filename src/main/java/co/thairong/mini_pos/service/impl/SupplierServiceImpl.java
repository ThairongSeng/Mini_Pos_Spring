package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.model.dto.SupplierDto;
import co.thairong.mini_pos.model.entity.Supplier;
import co.thairong.mini_pos.mapper.SupplierMapper;
import co.thairong.mini_pos.repository.SupplierRepository;
import co.thairong.mini_pos.service.SupplierService;
import co.thairong.mini_pos.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierDto createSupplier(SupplierDto supplierDto) {

        Supplier supplier = supplierMapper.supplierDtoToSupplier(supplierDto);
        supplier = supplierRepository.save(supplier);

        return supplierMapper.supplierToSupplierDto(supplier);
    }

    @Override
    public SupplierDto getById(Long id) {
        Supplier supplier = supplierRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Supplier with id %d is not found.", id)
        ));

        return supplierMapper.supplierToSupplierDto(supplier);
    }


    @Override
    public SupplierDto updateSupplier(Long id, SupplierDto supplierDto) {
        SupplierDto supplierDto1 = getById(id);
        Supplier supplier = supplierMapper.supplierDtoToSupplier(supplierDto1);

        supplier.setId(id);
        supplier.setSupplierLocalName(supplierDto.supplierLocalName());
        supplier.setSupplierEngName(supplierDto.supplierEngName());
        supplier.setSupplierAddress(supplierDto.supplierAddress());
        supplier.setSupplierEmail(supplierDto.supplierEmail());
        supplier.setSupplierPhone(supplierDto.supplierPhone());
        supplier.setVatNumber(supplierDto.vatNumber());

        supplierRepository.save(supplier);

        return supplierMapper.supplierToSupplierDto(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        SupplierDto supplierDto = getById(id);
        Supplier supplier = supplierMapper.supplierDtoToSupplier(supplierDto);

        supplier.setId(id);
        supplier.setDeleted(true);

        supplierRepository.save(supplier);
    }

    @Override
    public Page<SupplierDto> getWithPagination(Map<String, String> params) {
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
        Page<Supplier> brandPage;
        if (params.containsKey(searchName)) {
            String name = params.get(searchName);
            brandPage = supplierRepository.findBySupplierEngNameContainingIgnoreCaseAndIsDeletedFalse(name, pageable);
        } else {
            brandPage = supplierRepository.findByIsDeletedFalseOrderByIdDesc(pageable);
        }

        // Convert to DTOs
        Page<SupplierDto> supplierDtoPage = brandPage.map(supplierMapper::supplierToSupplierDto);

        return supplierDtoPage;
    }

}
