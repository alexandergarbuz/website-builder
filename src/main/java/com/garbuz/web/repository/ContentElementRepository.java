package com.garbuz.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garbuz.web.entity.ContentElement;

public interface ContentElementRepository extends JpaRepository<ContentElement, Long> {
	
	
	List<ContentElement> findByOrderByParentPathAscPathAsc();
	
	List<ContentElement> findByParentPathAndPath(final String parentPath, final String path);
	
	

}
