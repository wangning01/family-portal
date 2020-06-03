package com.family.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.family.portal.domain.PlayList;
import com.family.portal.domain.Video;
import com.family.portal.domain.VideoFilter;
import com.family.portal.domain.VideoFilterItem;
import com.family.portal.repository.PlayListRepository;
import com.family.portal.repository.VideoRepository;
import com.family.portal.service.FilterService;

@RequestMapping(value="/api")
@RestController
public class PortalController {

	@Autowired 
	VideoRepository videoRepository;
	
	@Autowired
	private FilterService filterService;

	@Autowired
	private PlayListRepository playListRepository;
	
	@RequestMapping(value="/addVideo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Video addVideo(@RequestBody Video video){
		return this.videoRepository.save(video);
	}
	
	
	@RequestMapping(value="/saveVideo", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Video saveVideo(@RequestBody Video video){
		return this.videoRepository.save(video);
	}
	
	
	@RequestMapping(value="/allVideos", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Video> allVideos(){
		return this.videoRepository.findAll();
	}
	
	@RequestMapping(path="/deleteVideo/{videoId}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Long> deleteVideo(@PathVariable Long videoId){
		this.videoRepository.deleteById(videoId);
		Map<String, Long> result =  new HashMap<>();
		result.put("videoId", videoId);
		return result;
	}
	
	@RequestMapping(value="/filters", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<VideoFilter> leftNavFilters(){
		
		return this.filterService.getAllFilters();
	}
	
	@RequestMapping(path="/filterBy/{column}/{value}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Video> filteredVideos(@PathVariable String column, @PathVariable String value){
		
		return this.filterService.videosByFilterItem(VideoFilterItem.builder().byColumn(column).value(value).build());
	}
	
	@RequestMapping(value="/getPlaylist/{playlistId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody PlayList getPlaylist(@PathVariable Long playlistId){
		 Optional<PlayList> op =  this.playListRepository.findById(playlistId);
		 return op.isPresent() ?  op.get() :  PlayList.builder().build();
	}
	
	
	@RequestMapping(value="/getPlaylists", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<PlayList> getPlaylists(){
		
		return this.playListRepository.findAll();
	}
	
	@RequestMapping(value="/addToPlayList", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void addToPlayList(@RequestBody Video video){
		 PlayList playList = this.playListRepository.findById(1001L).get();
		 playList.getVideos().add(video);
		 this.playListRepository.save(playList);
	}
	
	@RequestMapping(value="/createNewPlaylist", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void createPlayList(@RequestBody PlayList playlist){
		 this.playListRepository.save(playlist);
	}
	
	
}
