package co.thairong.mini_pos.repository;

import co.thairong.mini_pos.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByIdAndIsDeletedFalse(Long id);

    Page<Customer> findByIsDeletedFalseOrderByIdDesc(Pageable pageable);

    Page<Customer> findByCustomerEngNameContainingIgnoreCaseAndIsDeletedFalse(String customerEngName, Pageable pageable);

}
