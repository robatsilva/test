package myhunter.com.controllers;

import myhunter.com.models.Product;
import myhunter.com.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductPageController {

    private final ProductService productService;

    public ProductPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/page")
    public String showProductsPage(Model model,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        List<Product> products = productService.findWithFilters(name, minPrice, maxPrice, sortBy, order);
        model.addAttribute("products", products);
        model.addAttribute("name", name);
        model.addAttribute("minPrice", minPrice);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("order", order);
        return "products";
    }

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping("/products/save")
    public String saveProduct(@RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam Boolean available) {
        Product product;

        if (id != null) {
            product = productService.findById(id)
                    .orElse(new Product());
            product.setId(id);
        } else {
            product = new Product();
        }

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setAvailable(available);

        productService.save(product);

        return "redirect:/products/page";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado: " + id));
        model.addAttribute("product", product);
        return "edit_product";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products/page";
    }
}
