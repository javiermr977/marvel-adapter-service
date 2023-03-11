package com.marvel.catalog;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;



public final class ConstantsApplication {

	private ConstantsApplication() {
		throw new IllegalStateException("ConstantsApplication class");
	}
	public static final String SQL_GETBYNAMEANDSERIEANDSTORY = ""
			+ " select distinct id, character_code, description, image, image_ext, name from "
			+ "	(select "
			+ "		c.*, "
			+ "		coalesce(s.name, 'null') seriename, "
			+ "        coalesce(sto.name, 'null') storyname "
			+ "        from characters c"
			+ "		left join character_series cs on c.id = cs.character_id"
			+ "		left join series s on s.id = cs.serie_id"
			+ "		left join character_stories cst on c.id = cst.character_id "
			+ "		left join stories sto on sto.id = cst.story_id"
			+ "        group by c.id, seriename, storyname) x"
			+ " where upper(name) like upper(?1)"
			+ " and  upper(seriename) like upper(?2)"
			+ " and upper(storyname) like upper(?3)";
	public static final String MSG_NOTFOUND = "Record not found.";
	public static final String MSG_CONFLIC_ERROR = "something went wrong, if you don't know contact your administrator.";
	public static final String ACTION_GET_ALL_CHARACTERS = "CHARACTERS[GET ALL]";
	public static final String ACTION_GET_CHARACTERS_MEDIA = "CHARACTERS[GET MEDIA]";
	public static final String ACTION_GET_COMICS_BY_CHARACTERS = "COMICS[BY CHARACTER]";
	public static final String ACTION_GET_ALL_COMICS = "COMICS[GET ALL]";
	public static final String ACTION_GET_COMICS_BY_ID = "COMICS[GET BY ID]";
	public static final String ACTION_ALL_IMAGES_BY_CHARACTER = "CHARACTERS[GET ALL IMAGE PAGABLE]";
	public static final List<String> LIST_ACTIONS_COMICS = Arrays.asList("COMICS[BY CHARACTER]","COMICS[GET ALL]","COMICS[GET BY ID]");
	
	

    
	
}