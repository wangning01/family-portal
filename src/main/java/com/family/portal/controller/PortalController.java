package com.family.portal.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.family.portal.domain.Video;
import com.family.portal.domain.VideoFilter;
import com.family.portal.domain.VideoFilterItem;
import com.family.portal.repository.VideoRepository;
import com.family.portal.service.FilterService;

@RequestMapping(value="/api")
@RestController
public class PortalController {

	@Autowired 
	VideoRepository videoRepository;
	
	@Autowired
	private FilterService filterService;
	
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
		
		
//		List<VideoFilter> filters = new ArrayList<>();
//		
//		VideoFilter filter = VideoFilter.builder().title("Language").icon("language").build();
//		
//		List<VideoFilter> filterItems = new ArrayList<>();
//		filterItems.add(VideoFilter.builder().title("Chinese").build());
//		filterItems.add(VideoFilter.builder().title("English").build());
//		filter.setItems(filterItems);
//		
//		filters.add(filter);
		return this.filterService.getAllFilters();
	}
	
	@RequestMapping(path="/filterBy/{column}/{value}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody List<Video> filteredVideos(@PathVariable String column, @PathVariable String value){
		
		return this.filterService.videosByFilterItem(VideoFilterItem.builder().byColumn(column).value(value).build());
	}
	
	
	
}
