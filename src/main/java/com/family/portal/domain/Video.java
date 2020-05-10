package com.family.portal.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="VIDEO")
@Data
@Builder
public class Video {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_VIDEO")
	@SequenceGenerator(name="SEQ_VIDEO",sequenceName="SEQ_VIDEO_ID", allocationSize=1)
	@Column(name="VIDEO_ID")
	private Long videoId;
	
	@Column(name="LANG")
	private String language;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="YOUTUBE_LINK")
	private String youtubeLink;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PERFORMED_BY")
	private String performedBy;
	
	@Column(name="FEATURE_BY")
	private String featureBy;
	
}
