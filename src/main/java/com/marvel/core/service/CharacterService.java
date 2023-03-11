package com.marvel.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.marvel.model.dto.responsemarvel200.Result;

public interface CharacterService {
	public List<com.marvel.model.dto.characters.getall.Character> findAllCharacters(String characterName, String serieName, String storyName) throws Exception;
	public Page<com.marvel.model.dto.characters.images.Character> findAllImage(String keyuser, Pageable pageable) throws Exception;
	public com.marvel.model.dto.characters.getbycode.Character findCommicsByCharacters(Long characterCode) throws Exception;
	public com.marvel.model.dto.characters.getcharacterbycode.Character findMediaByCharacters(Long characterCode) throws Exception;
}
