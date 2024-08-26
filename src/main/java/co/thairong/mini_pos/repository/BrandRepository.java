package co.thairong.mini_pos.repository;


import co.thairong.mini_pos.model.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByIsDeletedFalseOrderByIdDesc();

    Optional<Brand> findByIdAndIsDeletedFalse(Long id);

    Page<Brand> findByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    Page<Brand> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name, Pageable pageable);

}

