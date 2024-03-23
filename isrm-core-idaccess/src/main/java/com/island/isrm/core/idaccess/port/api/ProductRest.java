package com.island.isrm.core.idaccess.port.api;

import com.island.isrm.core.idaccess.port.repo.dao.projection.ProductCodeAndName;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductRest {

    @GetMapping("/list/code/name")
    public List<ProductCodeAndName> listCodeAndName() {
        List<ProductCodeAndName> data = new ArrayList<>();
        data.add(new ProductCodeAndName("P1000", "稀土元素"));
        data.add(new ProductCodeAndName("P1001", "硅酸盐"));
        data.add(new ProductCodeAndName("P1002", "钢材"));
        data.add(new ProductCodeAndName("P1003", "塑料"));
        data.add(new ProductCodeAndName("P1004", "玻璃"));
        data.add(new ProductCodeAndName("P1005", "贵金属"));
        data.add(new ProductCodeAndName("P1006", "石油化工产品"));
        data.add(new ProductCodeAndName("P1007", "煤炭"));
        data.add(new ProductCodeAndName("P1008", "木材"));
        data.add(new ProductCodeAndName("P1009", "水泥"));
        data.add(new ProductCodeAndName("P1010", "防爆操作柱/100/1 AC220V 10A  IP65 WF2 Ex de IIC T6 Gb/Ex tD A21 IP65 T80℃  立式(配立杆)下进线 壳体均配防雨罩和电缆格兰，旋钮，启停自复位，且带锁停挡位/工程塑料"));
        return data;
    }
}
