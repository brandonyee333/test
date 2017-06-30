/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingExamResultSectionServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingExamResultSectionServiceSoap
 * @generated
 */
public class TrainingExamResultSectionSoap implements Serializable {
	public static TrainingExamResultSectionSoap toSoapModel(
		TrainingExamResultSection model) {
		TrainingExamResultSectionSoap soapModel = new TrainingExamResultSectionSoap();

		soapModel.setTrainingExamResultSectionId(model.getTrainingExamResultSectionId());
		soapModel.setTrainingExamResultId(model.getTrainingExamResultId());
		soapModel.setTitle(model.getTitle());
		soapModel.setSectionKey(model.getSectionKey());
		soapModel.setScoreIndicator(model.getScoreIndicator());
		soapModel.setScoringAlgorithm(model.getScoringAlgorithm());
		soapModel.setMasteryScore(model.getMasteryScore());
		soapModel.setScore(model.getScore());
		soapModel.setStandardErrorOfEstimate(model.getStandardErrorOfEstimate());
		soapModel.setCorrectCount(model.getCorrectCount());
		soapModel.setIncorrectCount(model.getIncorrectCount());
		soapModel.setSkippedCount(model.getSkippedCount());
		soapModel.setGrade(model.getGrade());

		return soapModel;
	}

	public static TrainingExamResultSectionSoap[] toSoapModels(
		TrainingExamResultSection[] models) {
		TrainingExamResultSectionSoap[] soapModels = new TrainingExamResultSectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamResultSectionSoap[][] toSoapModels(
		TrainingExamResultSection[][] models) {
		TrainingExamResultSectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingExamResultSectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingExamResultSectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingExamResultSectionSoap[] toSoapModels(
		List<TrainingExamResultSection> models) {
		List<TrainingExamResultSectionSoap> soapModels = new ArrayList<TrainingExamResultSectionSoap>(models.size());

		for (TrainingExamResultSection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingExamResultSectionSoap[soapModels.size()]);
	}

	public TrainingExamResultSectionSoap() {
	}

	public long getPrimaryKey() {
		return _trainingExamResultSectionId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingExamResultSectionId(pk);
	}

	public long getTrainingExamResultSectionId() {
		return _trainingExamResultSectionId;
	}

	public void setTrainingExamResultSectionId(long trainingExamResultSectionId) {
		_trainingExamResultSectionId = trainingExamResultSectionId;
	}

	public long getTrainingExamResultId() {
		return _trainingExamResultId;
	}

	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultId = trainingExamResultId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getSectionKey() {
		return _sectionKey;
	}

	public void setSectionKey(String sectionKey) {
		_sectionKey = sectionKey;
	}

	public boolean getScoreIndicator() {
		return _scoreIndicator;
	}

	public boolean isScoreIndicator() {
		return _scoreIndicator;
	}

	public void setScoreIndicator(boolean scoreIndicator) {
		_scoreIndicator = scoreIndicator;
	}

	public String getScoringAlgorithm() {
		return _scoringAlgorithm;
	}

	public void setScoringAlgorithm(String scoringAlgorithm) {
		_scoringAlgorithm = scoringAlgorithm;
	}

	public String getMasteryScore() {
		return _masteryScore;
	}

	public void setMasteryScore(String masteryScore) {
		_masteryScore = masteryScore;
	}

	public String getScore() {
		return _score;
	}

	public void setScore(String score) {
		_score = score;
	}

	public String getStandardErrorOfEstimate() {
		return _standardErrorOfEstimate;
	}

	public void setStandardErrorOfEstimate(String standardErrorOfEstimate) {
		_standardErrorOfEstimate = standardErrorOfEstimate;
	}

	public int getCorrectCount() {
		return _correctCount;
	}

	public void setCorrectCount(int correctCount) {
		_correctCount = correctCount;
	}

	public int getIncorrectCount() {
		return _incorrectCount;
	}

	public void setIncorrectCount(int incorrectCount) {
		_incorrectCount = incorrectCount;
	}

	public int getSkippedCount() {
		return _skippedCount;
	}

	public void setSkippedCount(int skippedCount) {
		_skippedCount = skippedCount;
	}

	public int getGrade() {
		return _grade;
	}

	public void setGrade(int grade) {
		_grade = grade;
	}

	private long _trainingExamResultSectionId;
	private long _trainingExamResultId;
	private String _title;
	private String _sectionKey;
	private boolean _scoreIndicator;
	private String _scoringAlgorithm;
	private String _masteryScore;
	private String _score;
	private String _standardErrorOfEstimate;
	private int _correctCount;
	private int _incorrectCount;
	private int _skippedCount;
	private int _grade;
}