package com.openshift.coursecatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openshift.coursecatalogue.model.Enrollment;
import com.openshift.coursecatalogue.repositories.EnrollmentRepo;
/**
 * @author kaleembasha.akbar
 *
 * Service class for Enrollment related stuffs.
 */
@Service
public class EnrollmentService {
	@Autowired
	private EnrollmentRepo enrollmentRepo;

	
	public List<Enrollment> findAll(){
		return enrollmentRepo.findAll();
	}

}
