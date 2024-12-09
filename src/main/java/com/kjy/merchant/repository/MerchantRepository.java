package com.kjy.merchant.repository;

import com.kjy.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    Optional< Merchant > findById(long id);

    @Query(value =  "SELECT m FROM Merchant m WHERE m.createMemberId = :id AND m.delYn = 'N'")
    List<Merchant> findByCreateMemberId(Long id);

}
