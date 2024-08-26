package co.thairong.mini_pos.controller;


import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.model.dto.PageDto;
import co.thairong.mini_pos.model.dto.SupplierDto;
import co.thairong.mini_pos.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public BaseRest<?> createSupplier(@Valid @RequestBody SupplierDto supplierDto) {

        SupplierDto supplierDto1 = supplierService.createSupplier(supplierDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Supplier has been created successfully")
                .data(supplierDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping("{id}")
    public BaseRest<?> getSupplier(@PathVariable Long id) {

        SupplierDto supplierDto = supplierService.getById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Supplier has been found successfully")
                .data(supplierDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("{id}")
    public BaseRest<?> updateSupplier(@PathVariable Long id,@Valid @RequestBody SupplierDto supplierDto) {

        SupplierDto supplierDto1 = supplierService.updateSupplier(id, supplierDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Supplier has been updated successfully")
                .data(supplierDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
    }


    @GetMapping("/page")
    public BaseRest<?> getSupplierList(@RequestParam(required = false) Map<String, String> params) {

        Page<SupplierDto> supplierDtoPage = supplierService.getWithPagination(params);
        PageDto pageDto = new PageDto(supplierDtoPage);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Suppliers have been found successfully")
                .data(pageDto)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
