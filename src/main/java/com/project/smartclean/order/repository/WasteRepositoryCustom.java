package com.project.smartclean.order.repository;

import com.project.smartclean.order.entity.Item;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WasteRepositoryCustom extends JpaRepository<Item, Long> , QuerydslPredicateExecutor<Item> {
    //Page<Item> findByWasteNameContaining(String searchKeyword);
    @Query("SELECT b FROM Item b")
    Page<Item>getWasteList(BooleanBuilder builder, Pageable pageable);
//    Page<Item>getAllList(Pageable pageable);

}
