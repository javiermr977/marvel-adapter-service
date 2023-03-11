package com.marvel.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.marvel.catalog.ConstantsApplication;
import com.marvel.core.repository.CharacterRepository;
import com.marvel.core.repository.UserRepository;
import com.marvel.core.service.AuditService;
import com.marvel.model.dto.characters.getAudit.Audit;
import com.marvel.model.entities.AuditUser;
import com.marvel.model.entities.UserMarvel;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class AuditServiceImpl implements AuditService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private com.marvel.core.repository.AuditRepository auditRepository;
	
	@Override
	public void generateAudit(String keyuser, String action) throws Exception {
		List<UserMarvel> lstUser = userRepository.findAll()
				.stream().filter(us -> us.getKeyUser().equals(keyuser))
				.collect(Collectors.toList());		
		if(lstUser.isEmpty()) {
			throw new Exception("Usuario no encontrado");
		}
		UserMarvel us = lstUser.get(0);
		AuditUser aud = new AuditUser();
		aud.setAction(action);
		aud.setUser(us);
		aud.setCreatedAt(new Date());

		auditRepository.save(aud);
		
		
	}

	@Override
	public List<Audit> findByuser(Long id) {
		List<AuditUser> lstAuditdb = auditRepository.findAll();
		List<Audit> lstAud = lstAuditdb
				.stream()
				.filter(a -> a.getUser().getId().equals(id))
				.map(it -> {
			Audit audit = new Audit();
			audit.setId(it.getId());
			audit.setDateAudit(it.getCreatedAt());
			audit.setAction(it.getAction());
			audit.setUserCode(it.getUser().getId());
			audit.setUsername(it.getUser().getUsername());
			
			return audit;
			
		}).collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return lstAud;
	}

	@Override
	public List<Audit> findByComicType() {
		List<AuditUser> lstAuditdb = auditRepository.findAll();
		List<Audit> lstAud = lstAuditdb
				.stream()
				.filter(a -> ConstantsApplication.LIST_ACTIONS_COMICS.contains(a.getAction()))
				.map(it -> {
			Audit audit = new Audit();
			audit.setId(it.getId());
			audit.setDateAudit(it.getCreatedAt());
			audit.setAction(it.getAction());
			audit.setUserCode(it.getUser().getId());
			audit.setUsername(it.getUser().getUsername());
			
			return audit;
			
		}).collect(Collectors.toList());
		
		// TODO Auto-generated method stub
		return lstAud;
	}

}
