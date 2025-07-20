package io.mj2sdev.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import groovy.transform.builder.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

	@JoinColumn(name = "parent")
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity parent;
	private String name;
	private String groupName;
	
	@OneToMany(mappedBy = "parent")
	private List<CategoryEntity> children = new ArrayList<>();
}
