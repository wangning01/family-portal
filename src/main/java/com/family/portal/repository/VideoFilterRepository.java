package com.family.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.portal.domain.VideoFilter;

@Repository
public interface VideoFilterRepository extends JpaRepository<VideoFilter, Integer> {

}
