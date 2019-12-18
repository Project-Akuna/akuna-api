package ph.com.adcpp.models.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.models.entity.Product;
import ph.com.adcpp.models.repository.ProductRepository;

import java.util.List;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Slf4j
@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
