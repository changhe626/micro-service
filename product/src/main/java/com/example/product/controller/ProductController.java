package com.example.product.controller;

import com.example.product.dto.CartDTO;
import com.example.product.entity.ProductCategory;
import com.example.product.entity.ProductInfo;
import com.example.product.service.CategoryService;
import com.example.product.service.ProductService;
import com.example.product.util.ResultVoUtil;
import com.example.product.vo.ProductInfoVo;
import com.example.product.vo.ProductVo;
import com.example.product.vo.ResultVo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 1.查询所有在架商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据
     */
    @GetMapping("list")
    public ResultVo list() {
        List<ProductInfo> upAll = productService.findUpAll();
        List<Integer> list = upAll.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> type = categoryService.findByCategoryTypeIn(list);
        List<ProductVo> vos = new ArrayList<>();
        for (ProductCategory category : type) {
            ProductVo vo = new ProductVo();
            vo.setCategoryName(category.getCategoryName());
            vo.setCategoryType(category.getCategoryType());
            List<ProductInfoVo> productVos = new ArrayList<>();
            for (ProductInfo info : upAll) {
                ProductInfoVo productInfo = new ProductInfoVo();
                BeanUtils.copyProperties(info, productInfo);
                productVos.add(productInfo);
            }
            vo.setProductInfoVoList(productVos);
            vos.add(vo);
        }
        return ResultVoUtil.success(vos);
    }


    /**
     * 获取商品列表, 给订单服务用的.
     * 因为使用了注解RequestBody 所以必须使用post的方式
     */
    @PostMapping("/listForOrder")
    public List<ProductInfo> listForOrder(@RequestBody List<String> productList){
        return productService.listForOrder(productList);
    }


    @PostMapping("/descProductStock")
    public void descProductStock(@RequestBody List<CartDTO> list){
        productService.descProductStock(list);
    }


}
