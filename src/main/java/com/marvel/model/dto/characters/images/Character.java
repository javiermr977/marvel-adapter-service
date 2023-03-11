package com.marvel.model.dto.characters.images;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Character implements Serializable {
	
	private String image;
	private static final long serialVersionUID = 6273889636562123328L;
}
