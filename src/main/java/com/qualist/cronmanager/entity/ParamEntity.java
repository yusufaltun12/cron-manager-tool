package com.qualist.cronmanager.entity;

import javax.persistence.*;

/**
 * Created by Yusuf on 8.4.2016.
 */
@Entity
@Table(name = "params")
public class ParamEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="job_id")
    private JobEntity job;

    @Column(name = "param")
    private String param;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(JobEntity job) {
        this.job = job;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
