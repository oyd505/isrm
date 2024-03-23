package com.island.isrm.core.inquiry.port.repo.dao;

import com.island.isrm.core.inquiry.port.repo.dao.dataobject.InquiryProductDO;
import com.island.isrm.core.inquiry.port.repo.dao.projection.InquiryProductBasic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InquiryProductJpa extends ListCrudRepository<InquiryProductDO, Long> {
    Optional<InquiryProductDO> findByIdAndInquiryCode(Long id, String inquiryCode);

    @Query("SELECT ip.id as id, ip.inquiryCode as inquiryCode, ip.code as code, ip.name as name, ip.quantity as quantity, ip.units as units, ip.factoryName as factoryName, ip.deliveryDate as deliveryDate" +
            " FROM InquiryProductDO ip WHERE ip.inquiryCode = ?1")
    List<InquiryProductBasic> findProductBasicByInquiryCode(String inquiryCode);

    List<InquiryProductDO> findByInquiryCode(String inquiryCode);

    void deleteByInquiryCode(String inquiryCode);
}
