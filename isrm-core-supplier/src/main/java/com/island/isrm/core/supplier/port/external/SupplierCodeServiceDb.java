package com.island.isrm.core.supplier.port.external;

import com.island.isrm.core.supplier.domain.dp.SupplierCode;
import com.island.isrm.core.supplier.domain.external.SupplierCodeService;
import com.island.isrm.core.supplier.port.repo.dao.SupplierJpa;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class SupplierCodeServiceDb implements SupplierCodeService {
    private final SupplierJpa supplierJpa;

    public SupplierCodeServiceDb(SupplierJpa supplierJpa) {
        this.supplierJpa = supplierJpa;
    }

    @Override
    public SupplierCode generateSerialNumber() {
        String maxSupplierCode = this.supplierJpa.findMaxSupplierCode();
        if (StringUtils.hasText(maxSupplierCode)) {
            Integer maxSerialNumber = Integer.parseInt(maxSupplierCode.substring(1));
            return new SupplierCode(String.format("S%d", maxSerialNumber + 1));
        } else {
            return new SupplierCode("S1000");
        }
    }
}
