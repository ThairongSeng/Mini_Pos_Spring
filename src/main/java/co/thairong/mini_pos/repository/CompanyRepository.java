package co.thairong.mini_pos.repository;

import co.thairong.mini_pos.model.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByIsDeletedFalseOrderByIdDesc();

    Optional<Company> findByIdAndIsDeletedFalse(Long id);

    Page<Company> findByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    Page<Company> findByCompanyEngNameContainingIgnoreCaseAndIsDeletedFalse(String companyEngName, Pageable pageable);

}
