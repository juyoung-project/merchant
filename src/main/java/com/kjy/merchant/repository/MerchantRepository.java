package com.kjy.merchant.repository;

import com.kjy.merchant.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    Optional< Merchant > findById(long id);
}
