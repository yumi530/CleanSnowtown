package com.project.smartclean.order.repository;

import com.project.smartclean.order.entity.Districts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Long> {
}
