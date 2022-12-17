package com.ubi.academicapplication.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ubi.academicapplication.dto.regionDto.RegionDto;
import com.ubi.academicapplication.entity.Region;

@Component
public class RegionMapper {

	ModelMapper modelMapper = new ModelMapper();

	public RegionDto entityToDto(Region region) {
		return modelMapper.map(region, RegionDto.class);
	}
	
	public RegionDto toDto( Region region)
	{
		return new RegionDto(region.getId(),region.getCode(),region.getName());
	}

	public List<RegionDto> entitiesToDtos(List<Region> region) {
		return region.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toList());
	}
	
	public Set<RegionDto> entitiesToDto(Set<Region> region) {
		return region.stream().filter(Objects::nonNull).map(this::entityToDto).collect(Collectors.toSet());
	}

	public Region dtoToEntity(RegionDto regionDto) {
		return modelMapper.map(regionDto, Region.class);
	}

	public List<Region> dtosToEntities(List<RegionDto> regionDto) {
		return regionDto.stream().filter(Objects::nonNull).map(this::dtoToEntity).collect(Collectors.toList());
	}
}
