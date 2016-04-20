package com.qualist.cronmanager.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.qualist.cronmanager.entity.JobEntity;
import com.qualist.cronmanager.entity.ResultEntity;

@Repository
@Transactional
public interface ResultRepository extends CrudRepository<ResultEntity, Integer>
{
	@Query("select result from ResultEntity result where result.job=:job order by job.createdAt desc")
	List<ResultEntity> findByJob(@Param("job") JobEntity job);
}
