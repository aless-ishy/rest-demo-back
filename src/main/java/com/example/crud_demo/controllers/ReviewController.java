package com.example.crud_demo.controllers;

import com.example.crud_demo.models.Review;
import com.example.crud_demo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    @PostMapping("/addreview")
    public Review insertReview(@RequestBody Review review){
        return reviewRepository.save(review);
    }
}
