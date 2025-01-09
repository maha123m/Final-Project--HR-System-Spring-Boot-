package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConstant;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")// this means all endpoints (url)start with /api
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //@GetMapping("/public/categories") the same of below
    @RequestMapping(value ="/public/categories",method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getALLCategories (
            @RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize",defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstant.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstant.SORT_DIR, required = false) String sortOrder) {//DIR:direction
        CategoryResponse categoryResponse = categoryService.getALLCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>( categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity <CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
       CategoryDTO savedCategoryDTO =  categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long id){
            CategoryDTO deletedCategory =categoryService.deleteCategory(id);
            // way to return response
            return new ResponseEntity<>(deletedCategory, HttpStatus.OK);// I prefer this
            // return ResponseEntity.ok(status);
           // return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long id){
        CategoryDTO savedCategoryDTO =categoryService.updateCategory(categoryDTO,id);
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }


    //
      /*
    @GetMapping("/echo")
    public ResponseEntity<String> echoMessage(@RequestParam(name = "message",defaultValue = "Hello World") String message){
    //public ResponseEntity<String> echoMessage(@RequestParam(name = "message") String message) if i forget to enter value in postman i will get bad request {
    // public ResponseEntity<String> echoMessage(@RequestParam(name = "message",required = false) String message) i will get null without bad request in postman
        return new ResponseEntity<>("Echoed Message: " + message, HttpStatus.OK);
    }*/

}
