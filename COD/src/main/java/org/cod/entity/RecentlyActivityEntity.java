package org.cod.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "recently_activity")
public class RecentlyActivityEntity implements Serializable {
	
	
	private static final long serialVersionUID = 8991610819224786873L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long activityId;
	
	private Double startTime= 0.0;
	
	private String type;
	
	private Long userId;

}
