package com.marvel.core.facade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.marvel.model.dto.response.Response200;

public interface CharactersFacade {
	public List<com.marvel.model.dto.characters.getall.Character> getAllCharacters(String keyuser, String characterName, 
																				   String serieName, String storyName) throws Exception;
	public Page<com.marvel.model.dto.characters.images.Character> getAllImage(String keyuser, Pageable pageable) throws Exception;
	
	public com.marvel.model.dto.characters.getbycode.Character getComicsByCharacter(String keyuser, Long characterCode) throws Exception;
	public com.marvel.model.dto.characters.getcharacterbycode.Character getMediaByCharacter(String keyuser,Long characterCode) throws Exception;
}
