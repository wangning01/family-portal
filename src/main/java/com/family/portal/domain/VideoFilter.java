package com.family.portal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="VIDEO_FILTER")
public class VideoFilter {
	
	@Id
	@Column(name="VIDEO_FILTER_ID")
	private Long videoFilterId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="ICON")
	private String icon;
	
	@Column(name="BY_COLUMN")
	private String byColumn;
	
	@Transient
	private List<VideoFilterItem> items;

}
