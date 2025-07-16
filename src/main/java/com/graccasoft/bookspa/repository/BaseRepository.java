package com.graccasoft.bookspa.repository;
import com.graccasoft.bookspa.model.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> {
}
