package io.mj2sdev.shop.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Category extends Common{

	@JoinColumn(name = "parent")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category parent;
	private String name;
	private String groupName;
	
	@OneToMany(mappedBy = "parent")
	private List<Category> children = new ArrayList<>();
}
