package com.family.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.portal.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
