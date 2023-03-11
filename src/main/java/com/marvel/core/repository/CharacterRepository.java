package com.marvel.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marvel.catalog.ConstantsApplication;
import com.marvel.model.entities.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
	public List<Character> findByCharacterCodeIn(List<Long> characterCodes);
	public Optional<Character> findByCharacterCode(Long characterCode);	
	
	@Query(nativeQuery = true, value = ConstantsApplication.SQL_GETBYNAMEANDSERIEANDSTORY)
    List<Character> findAllByFilters(@Param("name") String name, @Param("storyName") String storyName, @Param("serieName") String serieName);
}
