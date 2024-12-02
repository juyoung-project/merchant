package com.kjy.merchant.service;

import com.kjy.merchant.dto.MerchantDto;
import com.kjy.merchant.entity.Merchant;
import com.kjy.merchant.repository.MerchantRepository;
import com.kjy.merchant.util.MemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public void createMerchant(MerchantDto dto) {

        Merchant merchant = Merchant.builder()
                .address(dto.getAddress())
                .contactNumber(dto.getContactNumber())
                .name(dto.getName())
                .createMemberId(MemberUtils.getCurrentMember().getId())
                .memberId(MemberUtils.getCurrentMember().getId())
                .addressGu(dto.getAddressGu())
                .addressSi(dto.getAddressSi())
                .owner(dto.getOwner())
                .businessNumber(dto.getBusinessNumber())
                .contractEndDate(dto.getContractEndDate())
                .openDate(dto.getOpenDate())
                .build();

        merchantRepository.save(merchant);
    }

}
