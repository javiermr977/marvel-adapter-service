package com.marvel.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComicService {
	public Page<com.marvel.model.dto.comics.getall.Comic> findAllComics(Pageable pageable) throws Exception;
	public com.marvel.model.dto.comics.getbycode.Comic findbyCode(Long comicCode) throws Exception;
}
