package com.qualist.cronmanager.repository;

import com.qualist.cronmanager.entity.JobEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Yusuf on 8.4.2016.
 */
public interface JobRepository extends CrudRepository<JobEntity, Integer>
{
}
