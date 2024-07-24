package com.garbuz.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garbuz.web.entity.Resume;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ResumeDao extends JpaRepository<Resume, Long> {
	/**
	 * Returns the total count of all resumes.
	 */
    long count();
    /**
     *  This methods will return the resume with the lowest ID.
     * @return  return the resume with the lowest ID
     */
    Resume findFirstByOrderByIdAsc();
}
