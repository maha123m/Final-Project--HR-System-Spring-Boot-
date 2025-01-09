package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstant;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/admin/categories/{id}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                                 @PathVariable Long id){// id for category
        ProductDTO savedProductDTO = productService.addProduct(id,productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProduct(
            @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_PRODUCTS_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstant.SORT_DIR, required = false) String sortOrder){

        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>( productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{id}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long id,
                                                                 @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                                 @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                                                                 @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                                 @RequestParam(name = "sortOrder",defaultValue = AppConstant.SORT_DIR, required = false) String sortOrder){// Id for category
        ProductResponse productResponse = productService.searchByCategory(id,pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>( productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductByKeyword(@PathVariable String keyword,
                                                               @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
                                                               @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                                                               @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_PRODUCTS_BY, required = false) String sortBy,
                                                               @RequestParam(name = "sortOrder",defaultValue = AppConstant.SORT_DIR, required = false) String sortOrder){
        ProductResponse productResponse = productService.searchProductByKeyword(keyword,pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>( productResponse, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable Long id){
        ProductDTO updatedProductDTO = productService.updateProduct(id,productDTO);
        return new ResponseEntity<>(updatedProductDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id){
        ProductDTO deletedProductDTO = productService.deleteProduct(id);
        return new ResponseEntity<>(deletedProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long id,
                                                         @RequestParam ("image") MultipartFile image) throws IOException {
        ProductDTO updatedProduct = productService.updateProductImage(id,image);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }


}
