package com.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.URL;
import com.model.URLStatistic;

@Repository
public interface URLStatisticsRepository extends CrudRepository<URLStatistic, String>{
	Optional<URLStatistic> findByUrl(URL url);
}
