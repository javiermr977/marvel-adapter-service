package com.marvel.core.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marvel.catalog.ConstantsApplication;
import com.marvel.core.facade.AuditFacade;
import com.marvel.core.repository.AuditRepository;
import com.marvel.core.service.AuditService;
import com.marvel.model.dto.characters.getAudit.Audit;
import com.marvel.model.entities.AuditUser;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuditFacadeImpl implements AuditFacade {
	@Autowired AuditService auditService;

	@Override
	public List<Audit> getAuditByUser(Long id) throws Exception {
		List<Audit> response = null;
		try {
			response = auditService.findByuser(id);
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	@Override
	public List<Audit> getAuditByComicsType() throws Exception {
		List<Audit> response = null;
		try {
			response = auditService.findByComicType();
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

}
