package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.port.repo.dao.projection.ContactNameAndPhone;
import com.island.isrm.core.idaccess.port.repo.dao.projection.SupplierCodeAndName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/supplier")
public class SupplierRest {

    @GetMapping("/list/code/name")
    public List<SupplierCodeAndName> listCodeAndName() {
        List<SupplierCodeAndName> data = new ArrayList<>();
        data.add(new SupplierCodeAndName("S1000", "华润供应商有限公司"));
        data.add(new SupplierCodeAndName("S1001", "鑫源供应商有限公司"));
        data.add(new SupplierCodeAndName("S1002", "联创供应商有限公司"));
        data.add(new SupplierCodeAndName("S1003", "恒达供应商有限公司"));
        data.add(new SupplierCodeAndName("S1004", "博远供应商有限公司"));
        data.add(new SupplierCodeAndName("S1005", "信诚供应商有限公司"));
        data.add(new SupplierCodeAndName("S1006", "宏图供应商有限公司"));
        data.add(new SupplierCodeAndName("S1007", "汇丰供应商有限公司"));
        data.add(new SupplierCodeAndName("S1008", "锦绣供应商有限公司"));
        data.add(new SupplierCodeAndName("S1009", "卓越供应商有限公司"));
        data.add(new SupplierCodeAndName("S1010", "中国平安财产保险股份有限公司东营中心支公司"));
        return data;
    }

    @GetMapping("/{supplierCode}/contact/list/name/phone")
    public List<ContactNameAndPhone> listContact(@PathVariable String supplierCode) {
        List<ContactNameAndPhone> data = new ArrayList<>();
        data.add(new ContactNameAndPhone("S1000", "张三", "13800000001"));
        data.add(new ContactNameAndPhone("S1001", "李四", "13900000002"));
        data.add(new ContactNameAndPhone("S1002", "王五", "13700000003"));
        data.add(new ContactNameAndPhone("S1003", "赵六", "15900000004"));
        data.add(new ContactNameAndPhone("S1004", "陈七", "13600000005"));
        data.add(new ContactNameAndPhone("S1005", "周八", "15800000006"));
        data.add(new ContactNameAndPhone("S1006", "吴九", "13500000007"));
        data.add(new ContactNameAndPhone("S1007", "郑十", "15700000008"));
        data.add(new ContactNameAndPhone("S1008", "冯十一", "13400000009"));
        data.add(new ContactNameAndPhone("S1009", "何十二", "15600000012"));
        data.add(new ContactNameAndPhone("S1010", "马十三", "13000000013"));
        return data.stream().filter(contact -> contact.getSupplierCode().equals(supplierCode)).toList();
    }
}
