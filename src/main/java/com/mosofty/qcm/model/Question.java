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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="questions")
public class Question {

	@Id
	@Column(name="questionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="image")
	private String link;
	
	@ManyToOne
	@JoinColumn(name="quiz_id")
	private Quiz quiz;

	@OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Answer> answers;
}
