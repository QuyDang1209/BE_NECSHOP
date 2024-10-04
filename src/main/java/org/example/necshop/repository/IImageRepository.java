package org.example.necshop.repository;

import org.example.necshop.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Images, Long> {
}
