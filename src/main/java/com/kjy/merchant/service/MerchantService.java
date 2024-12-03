package com.kjy.merchant.service;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.dto.MerchantDto;
import com.kjy.merchant.entity.Merchant;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.repository.MerchantRepository;
import com.kjy.merchant.util.MemberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class MerchantService {
    private static final Logger logger = LogManager.getLogger(MerchantService.class);
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

    /**
     * read 정책
     * 1. 본인이 생성한 가맹점만 볼 수 있음 ( 추후 같은 부서원에 데이터는 볼 수 있도록 수정
     * 2. 검색 추가 ( 가맹점명, 사업자번호, 주소, 휴폐업 상태 )
     *
     * @return
     */
    public List<Merchant> readMerchant(MerchantDto dto) {
        logger.info("리스트 가지고 오기");
        return merchantRepository.findByCreateMemberId(MemberUtils.getCurrentMember().getId());
    }

    /**
     * 업데이트와 삭제는 본인것 추후 같은 부서원들것만 가능함
     * */
    public void updateMerchant(MerchantDto dto) {
        executeWithPermission(dto, merchant -> {
            merchant.setName(dto.getName());
            merchant.setAddress(dto.getAddress());
            merchant.setAddressSi(dto.getAddressSi());
            merchant.setAddressGu(dto.getAddressGu());
            merchant.setContactNumber(dto.getContactNumber());
            merchant.setBusinessNumber(dto.getBusinessNumber());
            merchant.setContractEndDate(dto.getContractEndDate());
            merchant.setOpenDate(dto.getOpenDate());
            merchant.setOwner(dto.getOwner());
            merchant.setStatus(dto.getStatus());
            merchant.setUpdateMemberId(MemberUtils.getCurrentMember().getId());
            merchantRepository.save(merchant);
        });
    }

    public void deleteMerchant(MerchantDto dto) {
        executeWithPermission(dto, merchant -> {
            merchant.setDelYn("Y"); // 삭제 로직
            merchantRepository.save(merchant);
        });
    }

    private void executeWithPermission(MerchantDto dto, Consumer<Merchant> action) {
        Merchant merchant = findByMerchant(dto);
        checkPermission(merchant);
        action.accept(merchant); // 작업 위임하여 처리
    }

    private Merchant findByMerchant(MerchantDto dto) {
        return merchantRepository.findById(dto.getId())
                .orElseThrow(() -> new BizException(Code.ERROR, "가맹점을 찾지 못했습니다."));
    }

    private void checkPermission(Merchant merchant) {
        Long currentMemberId = MemberUtils.getCurrentMember().getId();
        if (!currentMemberId.equals(merchant.getCreateMemberId())) {
            throw new BizException(Code.ERROR, "수정/삭제 권한이 존재하지 않습니다.");
        }
    }
}
