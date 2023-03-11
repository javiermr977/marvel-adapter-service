package com.marvel.api.marvelservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.core.facade.AuditFacade;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/audit/")
public class AuditController {
	@Autowired
	AuditFacade auditFacade;
	
	@GetMapping(value = "/{userCode}/user")
	public ResponseEntity<List<com.marvel.model.dto.characters.getAudit.Audit>> getAuditByUser(
			@PathVariable("userCode") Long userCode) throws Exception {
		ResponseEntity<List<com.marvel.model.dto.characters.getAudit.Audit>> response = null;
		List<com.marvel.model.dto.characters.getAudit.Audit> auditResponse = null;
		try {
			auditResponse = auditFacade.getAuditByUser(userCode);
			response = new ResponseEntity<List<com.marvel.model.dto.characters.getAudit.Audit>>(
					auditResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	@GetMapping(value = "/comicType")
	public ResponseEntity<List<com.marvel.model.dto.characters.getAudit.Audit>> getAuditByComic() throws Exception {
		ResponseEntity<List<com.marvel.model.dto.characters.getAudit.Audit>> response = null;
		List<com.marvel.model.dto.characters.getAudit.Audit> auditResponse = null;
		try {
			auditResponse = auditFacade.getAuditByComicsType();
			response = new ResponseEntity<List<com.marvel.model.dto.characters.getAudit.Audit>>(
					auditResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

}
