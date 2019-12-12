package ph.com.adcpp.core.controller.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.ProductRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.commons.response.ProductResponse;
import ph.com.adcpp.models.entity.Product;
import ph.com.adcpp.models.service.ProductService;
import ph.com.adcpp.utils.ResponseUtil;

import java.util.List;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private ObjectMapper mapper;

    public ProductController(ProductService productService, ObjectMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostMapping("/save")
    public BaseResponse save(@RequestBody ProductRequest request) {

        log.info("Saving new product...");
        productService.save(mapper.convertValue(request, Product.class));
        log.info("New product successfully saved.");

        return ResponseUtil.success();
    }

    //TODO
    @GetMapping("/get-all-products")
    public BaseResponse getAllProducts() {

        log.info("Getting all products...");
        List<ProductResponse> responses = mapper.convertValue(productService.findAll(), List.class);
        log.info("Returned [{}] products", responses.size());

        return ResponseUtil.success(responses);
    }
}
