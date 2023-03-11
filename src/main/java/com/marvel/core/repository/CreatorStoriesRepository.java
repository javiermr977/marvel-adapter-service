package com.marvel.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvel.model.entities.CreatorStory;

public interface CreatorStoriesRepository extends JpaRepository<CreatorStory, Long> {

}
