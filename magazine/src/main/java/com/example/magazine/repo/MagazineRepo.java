package com.example.magazine.repo;

import com.example.magazine.model.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazineRepo extends JpaRepository<Magazine, Long> {
}
