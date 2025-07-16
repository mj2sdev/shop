package io.mj2sdev.shop.model.entity;

import java.util.ArrayList;
import java.util.List;

import groovy.transform.builder.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Category extends Base{

	@JoinColumn(name = "parent")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category parent;
	private String name;
	private String groupName;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> children = new ArrayList<>();
}
