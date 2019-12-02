package org.cod.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "movies_category")
public class MoviesCategoryEntity implements Serializable {

    private static final long serialVersionUID = -6784547496909823085L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String categoryName;
	
	@OneToMany(mappedBy = "movieCategory", cascade = {CascadeType.ALL})
    private List<MoviesEntity> moviesList;
	
	
}
