package org.cod.repository;

import org.cod.entity.MoviesCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesCategoryRepository extends JpaRepository<MoviesCategoryEntity, Long> {

}
