package co.thairong.mini_pos.service.impl;

import co.thairong.mini_pos.dto.BrandDto;
import co.thairong.mini_pos.dto.CompanyDto;
import co.thairong.mini_pos.entity.Brand;
import co.thairong.mini_pos.entity.Company;
import co.thairong.mini_pos.mapper.CompanyMapper;
import co.thairong.mini_pos.repository.CompanyRepository;
import co.thairong.mini_pos.service.CompanyService;
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
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.companyDtoToCompany(companyDto);
        companyRepository.save(company);

        return companyMapper.companyToCompanyDto(company);
    }

    @Override
    public List<CompanyDto> getCompanyList() {
        List<Company> companyList = companyRepository.findByIsDeletedFalseOrderByIdDesc();

        return companyMapper.companyListToCompanyDtoList(companyList);
    }

    @Override
    public CompanyDto getById(Long companyId) {
        Company company = companyRepository.findByIdAndIsDeletedFalse(companyId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Company with id %d not found", companyId)));

        return companyMapper.companyToCompanyDto(company);
    }

    @Override
    public void deleteCompany(Long companyId) {
        Company company = companyRepository.findByIdAndIsDeletedFalse(companyId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Company with id %d not found", companyId)));

        company.setDeleted(true);

        companyRepository.save(company);
    }

    @Override
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Company with id %d not found", id)));

        company.setCompanyLocalName(companyDto.companyLocalName());
        company.setCompanyEngName(companyDto.companyEngName());
        company.setCompanyEmail(companyDto.companyEmail());
        company.setCompanyPhone(companyDto.companyPhone());
        company.setCompanyAddress(companyDto.companyAddress());
        company.setVatNumber(companyDto.vatNumber());

        companyRepository.save(company);

        return companyMapper.companyToCompanyDto(company);
    }

    @Override
    public Page<CompanyDto> getWithPagination(Map<String, String> params) {
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
        Page<Company> companyPage;
        if (params.containsKey(searchName)) {
            String companyEngName = params.get(searchName);
            companyPage = companyRepository.findByCompanyEngNameContainingIgnoreCaseAndIsDeletedFalse(companyEngName, pageable);
        } else {
            companyPage = companyRepository.findByIsDeletedFalseOrderByIdDesc(pageable);
        }

        // Convert to DTOs
        Page<CompanyDto> companyDtoPage = companyPage.map(companyMapper::companyToCompanyDto);

        return companyDtoPage;
    }
}
