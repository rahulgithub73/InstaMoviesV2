package org.cod.vo;

import java.io.Serializable;

import org.cod.entity.BaseEntity;

import lombok.Data;

@Data
public class RecentlyActivityVO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 77818328695739146L;

	private Long id;

	private Double startTime = 0.0;

	private String type;

	private String hlsPath;

}
