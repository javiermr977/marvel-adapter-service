package com.marvel.model.dto.characters.getAudit;

import java.io.Serializable;
import java.util.Date;
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
public class Audit implements Serializable {
	
	private Long id;
	private String action;
	private Date dateAudit;
	private Long userCode;
	private String username;
	
	private static final long serialVersionUID = 800700170736896598L;
	
	
}
