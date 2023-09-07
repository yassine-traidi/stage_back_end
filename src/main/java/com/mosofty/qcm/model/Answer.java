package com.mosofty.qcm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name="answers")
public class Answer {
	
	@Id
	@Column(name="answerId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="content")
	private String content;
	
	@Column(name="isCorrect")
	private boolean value;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="question_id",nullable = false)
	private Question question;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="candidate_response_Id")
	private CandidateResponse candidateResponse;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "answers",fetch = FetchType.LAZY)
	private Set<User> users;
	
	
	
	public boolean getValue() {
		return this.value;
	}
	

}
