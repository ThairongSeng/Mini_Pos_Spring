package co.thairong.mini_pos.controller;

import co.thairong.mini_pos.base.BaseRest;
import co.thairong.mini_pos.dto.BrandDto;
import co.thairong.mini_pos.dto.CompanyDto;
import co.thairong.mini_pos.dto.PageDto;
import co.thairong.mini_pos.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping()
    public BaseRest<?> createCompany(@Valid @RequestBody CompanyDto companyDto) {

        CompanyDto companyDto1 = companyService.createCompany(companyDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.CREATED.value())
                .message("Company is created successfully")
                .data(companyDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping()
    public BaseRest<?> getAllCompanies() {

        List<CompanyDto> companyDtoList = companyService.getCompanyList();

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Companies are found successfully")
                .data(companyDtoList)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("{id}")
    public BaseRest<?> getCompanyById(@PathVariable Long id) {

        CompanyDto companyDto = companyService.getById(id);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Company is found successfully")
                .data(companyDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }

    @PutMapping("{id}")
    public BaseRest<?> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyDto companyDto) {
        CompanyDto companyDto1 = companyService.updateCompany(id, companyDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Company is updated successfully")
                .data(companyDto1)
                .timestamp(LocalDateTime.now())
                .build();
    }


    @GetMapping("/page")
    public BaseRest<?> getCompanyPage(@RequestParam(required = false) Map<String, String> params) {

        Page<CompanyDto> companyDtoPage = companyService.getWithPagination(params);
        PageDto pageDto = new PageDto(companyDtoPage);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Companies have been found successfully")
                .data(pageDto)
                .timestamp(LocalDateTime.now())
                .build();
    }

}
