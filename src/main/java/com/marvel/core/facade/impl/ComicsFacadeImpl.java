package com.marvel.core.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.marvel.catalog.ConstantsApplication;
import com.marvel.catalog.CustomException;
import com.marvel.core.facade.ComicsFacade;
import com.marvel.core.service.AuditService;
import com.marvel.model.dto.comics.getall.Comic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ComicsFacadeImpl implements ComicsFacade{
	@Autowired
	private com.marvel.core.service.ComicService comicService;
	
	@Autowired
	private AuditService auditService;
	
	@Override
	public Page<Comic> getAllComics(String keyuser, Integer offset, Integer limit) throws Exception {
		// TODO Auto-generated method stub
		Page<com.marvel.model.dto.comics.getall.Comic> response = null;
		Pageable pageable = null;
		try {
			pageable = PageRequest.of(offset, limit);
			response = comicService.findAllComics(pageable);
			auditService.generateAudit(keyuser, ConstantsApplication.ACTION_GET_ALL_COMICS);
		} catch (Exception e) {
			throw new CustomException(ConstantsApplication.MSG_CONFLIC_ERROR, HttpStatus.CONFLICT);
		}
		return response;
	}

	@Override
	public com.marvel.model.dto.comics.getbycode.Comic findbyCode(String keyuser, Long comicCode) throws Exception {
		// TODO Auto-generated method stub
		com.marvel.model.dto.comics.getbycode.Comic response = null;
		try {
			response = comicService.findbyCode(comicCode);
			auditService.generateAudit(keyuser, ConstantsApplication.ACTION_GET_COMICS_BY_ID);
		} catch (Exception e) {
			// TODO: handle exception
			throw new CustomException(ConstantsApplication.MSG_CONFLIC_ERROR, HttpStatus.CONFLICT);
		}
		return response;
	}

}
