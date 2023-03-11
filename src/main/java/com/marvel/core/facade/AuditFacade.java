package com.marvel.core.facade;

import java.util.List;

public interface AuditFacade {
	public List<com.marvel.model.dto.characters.getAudit.Audit> getAuditByUser(Long id) throws Exception;
	public List<com.marvel.model.dto.characters.getAudit.Audit> getAuditByComicsType() throws Exception;
}
