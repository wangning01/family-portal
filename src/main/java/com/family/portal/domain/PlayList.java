package com.family.portal.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="PLAY_LIST")
@Data
@Builder 
public class PlayList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SEQ_PLAY_LIST")
	@SequenceGenerator(name="SEQ_PLAY_LIST",sequenceName="SEQ_PLAY_LIST_ID", allocationSize=1)
	@Column(name="PLAY_LIST_ID")
	private Long playListId;
	
	@Column(name="NAME")
	private String name;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PLAY_LIST_VIDEO", joinColumns = { @JoinColumn(name = "PLAY_LIST_ID") }, inverseJoinColumns = { @JoinColumn(name = "VIDEO_ID") })
	private List<Video> videos;
	
	
	
}
