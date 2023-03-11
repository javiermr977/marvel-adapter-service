package com.marvel.core.service;

import java.util.List;

public interface AuditService {
	public void generateAudit(String keyuser, String action) throws Exception;
	public List<com.marvel.model.dto.characters.getAudit.Audit> findByuser (Long id);
	public List<com.marvel.model.dto.characters.getAudit.Audit> findByComicType ();
}
