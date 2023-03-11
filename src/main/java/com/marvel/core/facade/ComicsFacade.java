package com.marvel.core.facade;

import org.springframework.data.domain.Page;

import com.marvel.model.dto.comics.getall.Comic;

public interface ComicsFacade {
	public Page<Comic> getAllComics(String keyuser, Integer offset, Integer limit) throws Exception;
	public com.marvel.model.dto.comics.getbycode.Comic findbyCode(String keyuser,Long comicCode) throws Exception;
}
