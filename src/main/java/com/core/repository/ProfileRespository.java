package com.core.repository;

import com.core.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRespository extends CrudRepository<Profile, Long> {
}
