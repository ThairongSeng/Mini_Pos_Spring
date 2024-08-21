package co.thairong.mini_pos.repository;


import co.thairong.mini_pos.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    List<Brand> findByIsDeletedFalse();

    Optional<Brand> findByIdAndIsDeletedFalse(Long id);
}

