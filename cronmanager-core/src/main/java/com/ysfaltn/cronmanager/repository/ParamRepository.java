package com.ysfaltn.cronmanager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ysfaltn.cronmanager.entity.JobEntity;
import com.ysfaltn.cronmanager.entity.ParamEntity;


@Repository
@Transactional
public interface ParamRepository extends CrudRepository<ParamEntity, Integer>{
	@Query("select param from ParamEntity param where param.job=:job")
	List<ParamEntity> findByJob(@Param("job") JobEntity job);
}
