package com.example.Web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Web.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
