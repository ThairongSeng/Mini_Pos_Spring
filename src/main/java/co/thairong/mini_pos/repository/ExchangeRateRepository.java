package co.thairong.mini_pos.repository;


import co.thairong.mini_pos.model.entity.ExchangeRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findByIdAndIsDeletedFalse(Long id);

    Page<ExchangeRate> findByIsDeletedFalseOrderByIdDesc(Pageable pageable);

}
