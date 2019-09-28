package com.family.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.family.portal.domain.Video;
import com.family.portal.domain.VideoFilter;
import com.family.portal.domain.VideoFilterItem;
import com.family.portal.repository.VideoFilterRepository;
import com.family.portal.repository.VideoRepository;

@Service
public class FilterService {

	@Autowired
	private VideoFilterRepository videoFilterRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private VideoRepository videoRepository;

	
	public List<VideoFilter> getAllFilters() {
		List<VideoFilter> filters = this.videoFilterRepository.findAll();
		filters.stream().forEach(filter -> {
			this.populateFilterItems(filter);
			// Add "All" item to a filter
			filter.getItems().add(VideoFilterItem.builder().value("ALL").byColumn(filter.getByColumn()).build());
		});
		
		
		return filters;
	}
	private void populateFilterItems(VideoFilter filter) {
		List<VideoFilterItem> filterItems = new ArrayList<>();
		List<String> values = this.jdbcTemplate.queryForList("SELECT DISTINCT "+filter.getByColumn()+" FROM VIDEO", String.class);
		values.stream().forEach(value -> {
//			filterItems.add(VideoFilter.builder().name(item).byColumn(filter.getByColumn()).build());
			filterItems.add(VideoFilterItem.builder().value(value).byColumn(filter.getByColumn()).build());
		});
		
		filter.setItems(filterItems);
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Video> videosByFilterItem(VideoFilterItem filterItem) {
		StringBuilder sql = new StringBuilder("SELECT * FROM VIDEO WHERE 1=1 ");
		
		if(filterItem.getValue().equals("ALL"))
			return this.videoRepository.findAll();
		
		if(filterItem.getByColumn() != null && !filterItem.getValue().equals("ALL")) 
			sql.append("AND ").append(filterItem.getByColumn()).append(" = ?");
		List<Video> result = this.jdbcTemplate.query(sql.toString(), new Object[] {filterItem.getValue()}, 
				new BeanPropertyRowMapper(Video.class));
		return result;
	}
	

}
