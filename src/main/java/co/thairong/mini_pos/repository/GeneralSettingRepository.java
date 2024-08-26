package co.thairong.mini_pos.repository;

import co.thairong.mini_pos.model.entity.GeneralSetting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneralSettingRepository extends JpaRepository<GeneralSetting, Long> {

    Optional<GeneralSetting> findCustomerByIdAndIsDeletedFalse(Long id);

    Page<GeneralSetting> findByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    Page<GeneralSetting> findBySiteTitleContainingIgnoreCaseAndIsDeletedFalse(String siteTitle, Pageable pageable);


}
