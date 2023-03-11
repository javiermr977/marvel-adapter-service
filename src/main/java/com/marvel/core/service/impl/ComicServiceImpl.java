package com.marvel.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.marvel.catalog.ConstantsApplication;
import com.marvel.catalog.CustomException;
import com.marvel.core.service.ComicService;
import com.marvel.model.dto.comics.getall.Comic;
import com.marvel.core.repository.ComicRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ComicServiceImpl implements ComicService{
	@Autowired
	private ComicRepository comicRepository;
	
	@Override
	public Page<Comic> findAllComics(Pageable pageable) throws Exception {
	Page<com.marvel.model.entities.Comic> comicsFromDataBase = null;
	Page<com.marvel.model.dto.comics.getall.Comic> response = null;
	try {
		comicsFromDataBase = comicRepository.findAll(pageable);
		response = buildResponseFromFindAll(comicsFromDataBase);
	} catch (Exception e) {
		throw new CustomException(ConstantsApplication.MSG_CONFLIC_ERROR, HttpStatus.CONFLICT);
	}
	return response;
	}

	@Override
	public com.marvel.model.dto.comics.getbycode.Comic findbyCode(Long comicCode) throws Exception {
		com.marvel.model.entities.Comic comicFromDataBase = null;
		com.marvel.model.dto.comics.getbycode.Comic response = null;
		try {
			comicFromDataBase = comicRepository.findByComicCode(comicCode)
				.orElseThrow(()->  new CustomException(ConstantsApplication.MSG_NOTFOUND, HttpStatus.NOT_FOUND));
			response = buildResponseForFindByCode(comicFromDataBase);
		} catch (Exception e) {
			throw new CustomException(ConstantsApplication.MSG_CONFLIC_ERROR, HttpStatus.CONFLICT);
		}
		return response;
	}
	
	private Page<com.marvel.model.dto.comics.getall.Comic> buildResponseFromFindAll(Page<com.marvel.model.entities.Comic> comicsFromDataBase){
		Page<com.marvel.model.dto.comics.getall.Comic> response = null;
		try {
			response = comicsFromDataBase
					.map( it -> {
						List<com.marvel.model.dto.comics.getall.Character> characters = null; 
						com.marvel.model.dto.comics.getall.Comic comic = new com.marvel.model.dto.comics.getall.Comic();
						comic.setComicCode(it.getComicCode());
						comic.setName(it.getName());
						
						characters = it.getCharacterComics()
							.stream()
							.map( characterComicsFromDataBase -> {
								com.marvel.model.dto.comics.getall.Character character = new com.marvel.model.dto.comics.getall.Character();
								com.marvel.model.entities.Character characterFromDataBase = characterComicsFromDataBase.getCharacter();
								character.setCharacterCode(characterFromDataBase.getCharacterCode());
								character.setDescription(characterFromDataBase.getDescription());
								character.setName(characterFromDataBase.getName());
								
								return character;
							}).collect(Collectors.toList());
						comic.setCharacters(characters);
						
						return comic;
					});
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	private com.marvel.model.dto.comics.getbycode.Comic buildResponseForFindByCode(com.marvel.model.entities.Comic comicFromDataBase){
		List<com.marvel.model.dto.comics.getbycode.Character> characters = null; 
		com.marvel.model.dto.comics.getbycode.Comic comic = null;
		try {
			comic = new com.marvel.model.dto.comics.getbycode.Comic();
			comic.setComicCode(comicFromDataBase.getComicCode());
			comic.setName(comicFromDataBase.getName());
			
			characters = comicFromDataBase
			.getCharacterComics()
			.stream()
			.map( characterComicsFromDataBase -> {
				com.marvel.model.dto.comics.getbycode.Character character = new com.marvel.model.dto.comics.getbycode.Character();
				com.marvel.model.entities.Character characterFromDataBase = characterComicsFromDataBase.getCharacter();
				character.setCharacterCode(characterFromDataBase.getCharacterCode());
				character.setDescription(characterFromDataBase.getDescription());
				character.setName(characterFromDataBase.getName());
				
				return character;
			}).collect(Collectors.toList());
			comic.setCharacters(characters);
		} catch (Exception e) {
			throw e;
		}
		return comic;
	}


}
