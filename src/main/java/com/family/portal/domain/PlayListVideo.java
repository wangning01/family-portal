package com.family.portal.domain;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PLAY_LIST_VIDEO")
@Data
@Builder
public class PlayListVideo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PLAY_LIST_ID")
	private Long playListId;
	
	@Id
	@Column(name="VIDEO_ID")
	private Long videoId;
	
//	@ManyToOne
//	private Video video;
//	
	
	
}
