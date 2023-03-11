package com.marvel.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.marvel.catalog.ConstantsApplication;
import com.marvel.catalog.CustomException;
import com.marvel.core.facade.impl.CharactersFacadeImpl;
import com.marvel.core.repository.CharacterRepository;
import com.marvel.core.service.CharacterService;
import com.marvel.model.dto.comics.getall.Comic;
import com.marvel.model.entities.Character;
import com.marvel.model.entities.CharacterComic;
import com.marvel.model.entities.Serie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CharacterServiceImpl implements CharacterService {
	@Autowired
	private CharacterRepository repository;
	
	@Override
	public List<com.marvel.model.dto.characters.getall.Character> findAllCharacters(String characterName, String serieName, String storyName) throws Exception {
		List<com.marvel.model.dto.characters.getall.Character> response = null;
		List<Character> charactersFromDataBase = null;
		try {
			charactersFromDataBase = repository.findAllByFilters(characterName != null ? "%" + characterName + "%" : "%%", 
					serieName != null ? "%" + serieName + "%" : "%%", 
							storyName != null ? "%" + storyName + "%" : "%%");
			response = builResponseFromGetByNameAndSerieAndStory(charactersFromDataBase);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	@Override
	public com.marvel.model.dto.characters.getbycode.Character findCommicsByCharacters(Long characterCode)
			throws Exception {
		com.marvel.model.dto.characters.getbycode.Character response = null;
		Optional<Character> optionalCharacter = null;
		try {
			optionalCharacter = repository.findByCharacterCode(characterCode);
			if(optionalCharacter.isPresent()) {
				response = builResponseFromGetByCommicCode(optionalCharacter.get());
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}
	
	@Override
	public com.marvel.model.dto.characters.getcharacterbycode.Character findMediaByCharacters(Long characterCode)
			throws Exception {
		Character characterFromDataBase = null;
		com.marvel.model.dto.characters.getcharacterbycode.Character response = null;
		try {
			characterFromDataBase = repository.findByCharacterCode(characterCode)
					.orElseThrow(() -> new CustomException(ConstantsApplication.MSG_NOTFOUND, HttpStatus.NOT_FOUND));

			response = buildResponseForGetCharacterByCode(characterFromDataBase);
		} catch (CustomException e) {
			throw e;
		} catch (Exception e) {
			throw new CustomException(ConstantsApplication.MSG_CONFLIC_ERROR, HttpStatus.CONFLICT);
		}
		return response;
	}
	
	private List<com.marvel.model.dto.characters.getall.Character> builResponseFromGetByNameAndSerieAndStory(
			List<Character> charactersFromDataBase) throws Exception {
		List<com.marvel.model.dto.characters.getall.Character> response = null;
		try {
			response = charactersFromDataBase.stream().map(it -> {
				com.marvel.model.dto.characters.getall.Character character;
				character = new com.marvel.model.dto.characters.getall.Character();
				List<com.marvel.model.dto.characters.getall.Story> stories = null;
				List<com.marvel.model.dto.characters.getall.Serie> series = null;
	
				character.setCharacterCode(it.getCharacterCode());
				character.setName(it.getName());
				character.setDescription(it.getDescription());
	
				stories = it.getCharacterStories().stream().map(characterStoryFromDataBase -> {
					com.marvel.model.dto.characters.getall.Story story;
					story = new com.marvel.model.dto.characters.getall.Story();
					com.marvel.model.entities.Story storyFromDataBase = characterStoryFromDataBase.getStory();
	
					story.setName(storyFromDataBase.getName());
					story.setStoryCode(storyFromDataBase.getStoryCode());
	
					return story;
				}).collect(Collectors.toList());
				character.setStories(stories);
	
				series = it.getCharacterSeries().stream().map(characterSeriesFromDataBase -> {
					com.marvel.model.dto.characters.getall.Serie serie;
					serie = new com.marvel.model.dto.characters.getall.Serie();
					Serie serieFromDataBase = characterSeriesFromDataBase.getSerie();
	
					serie.setName(serieFromDataBase.getName());
					serie.setSerieCode(serieFromDataBase.getSerieCode());
	
					return serie;
				}).collect(Collectors.toList());
				character.setSeries(series);
	
				return character;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return response;
	}
	
	private com.marvel.model.dto.characters.getbycode.Character builResponseFromGetByCommicCode(
			Character charactersFromDataBase) throws Exception {
		com.marvel.model.dto.characters.getbycode.Character character = new com.marvel.model.dto.characters.getbycode.Character();
		try {
			
			
			List<com.marvel.model.dto.characters.getbycode.Comic> lstComics = new ArrayList<>();
			List<CharacterComic> lstCharacterComic = new ArrayList<>();
			
			character.setCharacterCode(charactersFromDataBase.getCharacterCode());
			character.setName(charactersFromDataBase.getName());
			character.setDescription(charactersFromDataBase.getDescription());
			
			lstCharacterComic = charactersFromDataBase.getCharacterComics();
			
			for(CharacterComic c : lstCharacterComic) {
				com.marvel.model.dto.characters.getbycode.Comic comic = new com.marvel.model.dto.characters.getbycode.Comic();
				comic.setComicCode(c.getComic().getComicCode());
				comic.setName(c.getComic().getName());
				
				lstComics.add(comic);
				
			}
			
			character.setComics(lstComics);
			
				
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return character;
	}
	
	private com.marvel.model.dto.characters.getcharacterbycode.Character buildResponseForGetCharacterByCode(Character characterFromDataBase) {
		com.marvel.model.dto.characters.getcharacterbycode.Character response = null;
		try {
			response = new com.marvel.model.dto.characters.getcharacterbycode.Character();
			response.setCharacterCode(characterFromDataBase.getCharacterCode());
			response.setName(characterFromDataBase.getName());
			response.setDescription(characterFromDataBase.getDescription());
			response.setImage(characterFromDataBase.getImage()+characterFromDataBase.getImageExt());

		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	@Override
	public Page<com.marvel.model.dto.characters.images.Character> findAllImage(String keyuser, Pageable pageable) throws Exception {
		List<com.marvel.model.dto.characters.images.Character> response = new ArrayList<>();
		Page<com.marvel.model.dto.characters.images.Character> pageResult = null;
		List<Character> charactersFromDataBase = null;
		try {
			charactersFromDataBase = repository.findAll();
			
			for(Character db : charactersFromDataBase) {
				com.marvel.model.dto.characters.images.Character ch = new com.marvel.model.dto.characters.images.Character();
				ch.setImage(db.getImage()+db.getImageExt());
				response.add(ch);
			}
			int startOfPage = pageable.getPageNumber() * pageable.getPageSize();
			int endOfPage = Math.min(startOfPage + pageable.getPageSize(), response.size());
			pageResult = new PageImpl<com.marvel.model.dto.characters.images.Character>(response.subList(startOfPage, endOfPage), pageable, response.size());
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return pageResult;
	}

	



}
