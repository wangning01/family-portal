package com.family.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.family.portal.domain.PlayList;
import com.family.portal.domain.Video;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Long> {
	
//	@Query("select v from Video v order by v.videoId")
//	public List<Video> findAll();

}
