package co.thairong.mini_pos.service;

import co.thairong.mini_pos.model.dto.CompanyDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    /**
     * Use for create company
     * @param companyDto
     * @return Company Dto
     */
    CompanyDto createCompany(CompanyDto companyDto);

    /**
     * Use for get all companies
     * @param
     * @return List of Company Dto
     */
    List<CompanyDto> getCompanyList();

    /**
     * Use for get company by id
     * @param id
     * @return Company Dto
     */
    CompanyDto getById(Long id);

    /**
     * Use for delete company
     * @param id
     * @return @ResponseStatus(HttpStatus.NO_CONTENT) //204
     */
    void deleteCompany(Long id);

    /**
     * Use for update company
     * @param companyDto
     * @return Company Dto
     */
    CompanyDto updateCompany(Long id, CompanyDto companyDto);

    /**
     * Use for get pagination of companies
     * @param params
     * @return Pagination of companies
     */
    Page<CompanyDto> getWithPagination(Map<String, String> params);

}
