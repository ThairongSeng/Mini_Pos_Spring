package co.thairong.mini_pos.mapper;


import co.thairong.mini_pos.model.dto.CompanyDto;
import co.thairong.mini_pos.model.entity.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company companyDtoToCompany(CompanyDto companyDto);

    CompanyDto companyToCompanyDto(Company company);

    List<CompanyDto> companyListToCompanyDtoList(List<Company> companyList);
}
