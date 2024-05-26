package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.addCategory(category);
        return ResponseEntity.ok(category1);
    }

    //get category
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId) {
        return this.categoryService.getCategory(categoryId);
    }

    //get all categories
    @GetMapping("/")
    public ResponseEntity<?> getCategories() {
        return ResponseEntity.ok(this.categoryService.getCategories());
    }

    //update category
    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {

        Long cid = category.getCid();
        try {
            Long categoryId = categoryService.getCategoryId(cid);
        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Id not found");
        }

        this.categoryService.updateCategory(category);
        return ResponseEntity.ok("Data updated successfully");
    }


    //delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long cid) {

        try {
            Long categoryId = categoryService.getCategoryId(cid);
        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Id not found");
        }
        this.categoryService.deleteCategory(cid);
        return ResponseEntity.ok("Data Deleted successfully");
    }


}
