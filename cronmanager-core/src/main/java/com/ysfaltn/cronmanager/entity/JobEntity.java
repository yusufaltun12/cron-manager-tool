package com.ysfaltn.cronmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * Created by Yusuf on 8.4.2016.
 */

@Entity
@Table(name = "jobs")
public class JobEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="job_name")
    private String jobName;

    @Column(name="group_name")
    private String groupName;

    @Column(name="schedule")
    private String schedule;
    
    @Column(name="executable_file_path")
    private String executableFilePath;
    
    @JsonIgnore
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

	public String getExecutableFilePath() {
		return executableFilePath;
	}

	public void setExecutableFilePath(String executableFilePath) {
		this.executableFilePath = executableFilePath;
	}
}
