package com.mosofty.qcm.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="quizzes")
public class Quiz {
	
	@Id
	@Column(name="quizId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "quiz",cascade = CascadeType.DETACH,orphanRemoval = true)
	private List<Question> questions = new ArrayList<Question>();
	
	@JsonIgnore
	@OneToMany(mappedBy ="quiz",cascade =CascadeType.ALL,orphanRemoval = true)
	private List<Score> scores=new ArrayList<Score>();
	
	

}
