package com.qualist.cronmanager.repository;

import com.qualist.cronmanager.entity.JobEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Yusuf on 8.4.2016.
 */
@Repository
@Transactional
public interface JobRepository extends CrudRepository<JobEntity, Integer>
{
}
