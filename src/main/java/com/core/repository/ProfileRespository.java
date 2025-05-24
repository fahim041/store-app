package com.core.repository;

import com.core.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRespository extends CrudRepository<Profile, Long> {
}
