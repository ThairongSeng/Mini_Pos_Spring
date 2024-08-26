package co.thairong.mini_pos.repository;


import co.thairong.mini_pos.model.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findByIdAndIsDeletedFalse(Long id);

    Page<Supplier> findByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    Page<Supplier> findBySupplierEngNameContainingIgnoreCaseAndIsDeletedFalse(String supplierEngName, Pageable pageable);

}
