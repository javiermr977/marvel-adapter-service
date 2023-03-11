package com.marvel.core.facade.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.marvel.api.marvelservice.CharactersController;
import com.marvel.catalog.ConstantsApplication;
import com.marvel.core.facade.CharactersFacade;
import com.marvel.model.entities.Serie;

import lombok.extern.slf4j.Slf4j;

import com.marvel.model.dto.responsemarvel200.ResponseMarvelService;
import com.marvel.core.repository.CharacterRepository;
import com.marvel.core.service.AuditService;
import com.marvel.core.service.CharacterService;
import com.marvel.model.entities.Character;
import com.marvel.model.dto.creatorsresponse200.ResponseCreators200;
import com.marvel.model.dto.response.Response200;

@Slf4j
@Component
public class CharactersFacadeImpl implements CharactersFacade{
	@Autowired
	private CharacterRepository repository;
	
	@Autowired
	private CharacterService characterService;
	
	@Autowired
	private AuditService auditService;
	
	@Override
	public List<com.marvel.model.dto.characters.getall.Character> getAllCharacters(String keyuser, String characterName, 
																				   String serieName, 
																				   String storyName) throws Exception {
		// TODO Auto-generated method stub
		List<com.marvel.model.dto.characters.getall.Character> response = null;
		try {
			response = characterService.findAllCharacters(characterName, serieName, storyName);
			auditService.generateAudit(keyuser, ConstantsApplication.ACTION_GET_ALL_CHARACTERS);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	@Override
	public com.marvel.model.dto.characters.getbycode.Character getComicsByCharacter(String keyuser, Long characterCode) throws Exception {
		com.marvel.model.dto.characters.getbycode.Character response = null;
		try {
			response = characterService.findCommicsByCharacters(characterCode);
			auditService.generateAudit(keyuser, ConstantsApplication.ACTION_GET_COMICS_BY_CHARACTERS);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	@Override
	public com.marvel.model.dto.characters.getcharacterbycode.Character getMediaByCharacter(String keyuser, Long characterCode) throws Exception {
	com.marvel.model.dto.characters.getcharacterbycode.Character response = null;
	try {
		response = characterService.findMediaByCharacters(characterCode);
		auditService.generateAudit(keyuser, ConstantsApplication.ACTION_GET_CHARACTERS_MEDIA);
	} catch (Exception e) {
		log.error(e.getMessage());
	}
	return response;
	}

	@Override
	public Page<com.marvel.model.dto.characters.images.Character> getAllImage(String keyuser, Pageable pageable) throws Exception {
		Page<com.marvel.model.dto.characters.images.Character> response = null;
		try {
			response = characterService.findAllImage(keyuser, pageable);
			auditService.generateAudit(keyuser, ConstantsApplication.ACTION_ALL_IMAGES_BY_CHARACTER);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

}
