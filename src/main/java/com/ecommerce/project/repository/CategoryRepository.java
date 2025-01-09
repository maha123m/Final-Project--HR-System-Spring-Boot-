package com.ecommerce.project.repository;

import com.ecommerce.project.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends JpaRepository <Category,Long>{
    Category findByName(@NotBlank @Size(min = 5 ,message = "Category name must contain at least 5 characters") String name);
}
