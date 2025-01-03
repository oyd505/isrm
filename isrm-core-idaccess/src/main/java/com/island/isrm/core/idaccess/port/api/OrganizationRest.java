package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.port.repo.dao.projection.OrganizationCodeAndName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织结构相关的REST接口控制器
 * 提供不同类型的组织编码和名称列表
 *
 * @author dao.ouyang
 * @since 2024-12-15
 */
@RestController
@RequestMapping("/v1/organization")
public class OrganizationRest {

    /**
     * 获取采购组织编码和名称列表
     *
     * @return 采购组织编码和名称列表
     */
    @GetMapping("/list/po/code/name")
    public List<OrganizationCodeAndName> listCodeAndNameForPurchasing() {
        List<OrganizationCodeAndName> data = new ArrayList<>();
        data.add(new OrganizationCodeAndName("P1000", "原油采购部"));
        data.add(new OrganizationCodeAndName("P1001", "物资采购部"));
        return data;
    }

    /**
     * 获取公司编码和名称列表
     *
     * @return 公司编码和名称列表
     */
    @GetMapping("/list/co/code/name")
    public List<OrganizationCodeAndName> listCodeAndNameForCompany() {
        List<OrganizationCodeAndName> data = new ArrayList<>();
        data.add(new OrganizationCodeAndName("C1000", "湖南分公司"));
        data.add(new OrganizationCodeAndName("C1001", "山东分公司"));
        return data;
    }

    /**
     * 获取工厂的组织编码和名称列表
     *
     * @return 工厂的组织编码和名称列表
     */
    @GetMapping("/list/fo/code/name")
    public List<OrganizationCodeAndName> listCodeAndNameForFactory() {
        List<OrganizationCodeAndName> data = new ArrayList<>();
        data.add(new OrganizationCodeAndName("F1000", "长沙一厂"));
        data.add(new OrganizationCodeAndName("F1001", "宁乡二厂"));
        data.add(new OrganizationCodeAndName("F1002", "东营一厂"));
        data.add(new OrganizationCodeAndName("F1003", "济南二厂"));
        return data;
    }
}
