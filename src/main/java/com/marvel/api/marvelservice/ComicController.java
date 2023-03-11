package com.marvel.api.marvelservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.core.facade.ComicsFacade;
import com.marvel.model.dto.comics.getall.Comic;


@RestController
@RequestMapping(path = "/api/v1/comics/")
public class ComicController {
	
	@Autowired
	private ComicsFacade comicFacade;

	@GetMapping(value = "/all")
	public ResponseEntity<Page<Comic>> findAll(
			@RequestParam(name = "keyuser", required = true) String keyuser,
			@RequestParam(name = "offset", required = false) Optional<Integer> offset,
			@RequestParam(name = "limit", required = false) Optional<Integer> limit) throws Exception{
		ResponseEntity<Page<Comic>> response = null;
		Page<Comic> comicsResponse = null;
		try {
			comicsResponse = comicFacade.getAllComics(keyuser, offset.orElse(0), limit.orElse(20));
			response = new ResponseEntity<Page<Comic>>(comicsResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
	
	@GetMapping("/{comicCode}")
	public ResponseEntity<com.marvel.model.dto.comics.getbycode.Comic> findbyCode(
			@RequestParam(name = "keyuser", required = true) String keyuser,
			@PathVariable("comicCode") Long comicCode ) throws Exception{
		ResponseEntity<com.marvel.model.dto.comics.getbycode.Comic> response = null;
		com.marvel.model.dto.comics.getbycode.Comic comicResponse = null;
		try {
			comicResponse = comicFacade.findbyCode(keyuser, comicCode);
			response = new ResponseEntity<com.marvel.model.dto.comics.getbycode.Comic>(comicResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
