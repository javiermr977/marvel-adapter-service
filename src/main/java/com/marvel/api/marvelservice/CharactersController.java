package com.marvel.api.marvelservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.core.facade.CharactersFacade;
import com.marvel.model.dto.characters.getall.Character;
import com.marvel.model.dto.response.Response200;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/api/v1/characters/")
public class CharactersController {
	@Autowired
	CharactersFacade charactersFacade;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<com.marvel.model.dto.characters.getall.Character>> getAllCharacters(
			@RequestParam(name = "keyuser", required = true) String keyuser,
			@RequestParam(name = "character", required = false) String character,
			@RequestParam(name = "serie", required = false) String serie,
			@RequestParam(name = "story", required = false) String story) throws Exception {
		ResponseEntity<List<com.marvel.model.dto.characters.getall.Character>> response = null;
		List<com.marvel.model.dto.characters.getall.Character> characterResponse = null;
		try {
			characterResponse = charactersFacade.getAllCharacters(keyuser, character, serie, story);
			response = new ResponseEntity<List<com.marvel.model.dto.characters.getall.Character>>(
					characterResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	@GetMapping(value = "/allImage")
	public ResponseEntity<?> getAllImage(
			@RequestParam(name = "keyuser", required = true) String keyuser,
			@RequestParam Integer page, @RequestParam Integer size) throws Exception {
		ResponseEntity<?> response = null;
		List<com.marvel.model.dto.characters.images.Character> characterResponse = null;
		try {
			Pageable pageable = PageRequest.of(page, size);
            Page<com.marvel.model.dto.characters.images.Character> dtos  = charactersFacade.getAllImage(keyuser, pageable);
			response = new ResponseEntity<>(
					dtos, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	@GetMapping(path = "/{characterCode}/comics")
	public ResponseEntity<com.marvel.model.dto.characters.getbycode.Character> findComicsByCharacter(
			@RequestParam(name = "keyuser", required = true) String keyuser,
			@PathVariable("characterCode") Long characterCode) throws Exception {
		ResponseEntity<com.marvel.model.dto.characters.getbycode.Character> response = null;
		com.marvel.model.dto.characters.getbycode.Character characterResponse = null;
		try {
			characterResponse = charactersFacade.getComicsByCharacter(keyuser,characterCode );
			response = new ResponseEntity<com.marvel.model.dto.characters.getbycode.Character>(
					characterResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	@GetMapping(path = "/{characterCode}/media")
	public ResponseEntity<com.marvel.model.dto.characters.getcharacterbycode.Character> findMediaByCharacter(
			@RequestParam(name = "keyuser", required = true) String keyuser,
			@PathVariable("characterCode") Long characterCode) throws Exception {
		ResponseEntity<com.marvel.model.dto.characters.getcharacterbycode.Character> response = null;
		com.marvel.model.dto.characters.getcharacterbycode.Character characterResponse = null;
		try {
			characterResponse = charactersFacade.getMediaByCharacter(keyuser,characterCode);
			response = new ResponseEntity<com.marvel.model.dto.characters.getcharacterbycode.Character>(
					characterResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
