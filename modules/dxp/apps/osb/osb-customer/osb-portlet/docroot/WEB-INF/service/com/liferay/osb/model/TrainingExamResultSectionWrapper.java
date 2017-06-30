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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TrainingExamResultSection}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingExamResultSection
 * @generated
 */
public class TrainingExamResultSectionWrapper
	implements TrainingExamResultSection,
		ModelWrapper<TrainingExamResultSection> {
	public TrainingExamResultSectionWrapper(
		TrainingExamResultSection trainingExamResultSection) {
		_trainingExamResultSection = trainingExamResultSection;
	}

	public Class<?> getModelClass() {
		return TrainingExamResultSection.class;
	}

	public String getModelClassName() {
		return TrainingExamResultSection.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingExamResultSectionId",
			getTrainingExamResultSectionId());
		attributes.put("trainingExamResultId", getTrainingExamResultId());
		attributes.put("title", getTitle());
		attributes.put("sectionKey", getSectionKey());
		attributes.put("scoreIndicator", getScoreIndicator());
		attributes.put("scoringAlgorithm", getScoringAlgorithm());
		attributes.put("masteryScore", getMasteryScore());
		attributes.put("score", getScore());
		attributes.put("standardErrorOfEstimate", getStandardErrorOfEstimate());
		attributes.put("correctCount", getCorrectCount());
		attributes.put("incorrectCount", getIncorrectCount());
		attributes.put("skippedCount", getSkippedCount());
		attributes.put("grade", getGrade());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingExamResultSectionId = (Long)attributes.get(
				"trainingExamResultSectionId");

		if (trainingExamResultSectionId != null) {
			setTrainingExamResultSectionId(trainingExamResultSectionId);
		}

		Long trainingExamResultId = (Long)attributes.get("trainingExamResultId");

		if (trainingExamResultId != null) {
			setTrainingExamResultId(trainingExamResultId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String sectionKey = (String)attributes.get("sectionKey");

		if (sectionKey != null) {
			setSectionKey(sectionKey);
		}

		Boolean scoreIndicator = (Boolean)attributes.get("scoreIndicator");

		if (scoreIndicator != null) {
			setScoreIndicator(scoreIndicator);
		}

		String scoringAlgorithm = (String)attributes.get("scoringAlgorithm");

		if (scoringAlgorithm != null) {
			setScoringAlgorithm(scoringAlgorithm);
		}

		String masteryScore = (String)attributes.get("masteryScore");

		if (masteryScore != null) {
			setMasteryScore(masteryScore);
		}

		String score = (String)attributes.get("score");

		if (score != null) {
			setScore(score);
		}

		String standardErrorOfEstimate = (String)attributes.get(
				"standardErrorOfEstimate");

		if (standardErrorOfEstimate != null) {
			setStandardErrorOfEstimate(standardErrorOfEstimate);
		}

		Integer correctCount = (Integer)attributes.get("correctCount");

		if (correctCount != null) {
			setCorrectCount(correctCount);
		}

		Integer incorrectCount = (Integer)attributes.get("incorrectCount");

		if (incorrectCount != null) {
			setIncorrectCount(incorrectCount);
		}

		Integer skippedCount = (Integer)attributes.get("skippedCount");

		if (skippedCount != null) {
			setSkippedCount(skippedCount);
		}

		Integer grade = (Integer)attributes.get("grade");

		if (grade != null) {
			setGrade(grade);
		}
	}

	/**
	* Returns the primary key of this training exam result section.
	*
	* @return the primary key of this training exam result section
	*/
	public long getPrimaryKey() {
		return _trainingExamResultSection.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training exam result section.
	*
	* @param primaryKey the primary key of this training exam result section
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingExamResultSection.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training exam result section ID of this training exam result section.
	*
	* @return the training exam result section ID of this training exam result section
	*/
	public long getTrainingExamResultSectionId() {
		return _trainingExamResultSection.getTrainingExamResultSectionId();
	}

	/**
	* Sets the training exam result section ID of this training exam result section.
	*
	* @param trainingExamResultSectionId the training exam result section ID of this training exam result section
	*/
	public void setTrainingExamResultSectionId(long trainingExamResultSectionId) {
		_trainingExamResultSection.setTrainingExamResultSectionId(trainingExamResultSectionId);
	}

	/**
	* Returns the training exam result ID of this training exam result section.
	*
	* @return the training exam result ID of this training exam result section
	*/
	public long getTrainingExamResultId() {
		return _trainingExamResultSection.getTrainingExamResultId();
	}

	/**
	* Sets the training exam result ID of this training exam result section.
	*
	* @param trainingExamResultId the training exam result ID of this training exam result section
	*/
	public void setTrainingExamResultId(long trainingExamResultId) {
		_trainingExamResultSection.setTrainingExamResultId(trainingExamResultId);
	}

	/**
	* Returns the title of this training exam result section.
	*
	* @return the title of this training exam result section
	*/
	public java.lang.String getTitle() {
		return _trainingExamResultSection.getTitle();
	}

	/**
	* Sets the title of this training exam result section.
	*
	* @param title the title of this training exam result section
	*/
	public void setTitle(java.lang.String title) {
		_trainingExamResultSection.setTitle(title);
	}

	/**
	* Returns the section key of this training exam result section.
	*
	* @return the section key of this training exam result section
	*/
	public java.lang.String getSectionKey() {
		return _trainingExamResultSection.getSectionKey();
	}

	/**
	* Sets the section key of this training exam result section.
	*
	* @param sectionKey the section key of this training exam result section
	*/
	public void setSectionKey(java.lang.String sectionKey) {
		_trainingExamResultSection.setSectionKey(sectionKey);
	}

	/**
	* Returns the score indicator of this training exam result section.
	*
	* @return the score indicator of this training exam result section
	*/
	public boolean getScoreIndicator() {
		return _trainingExamResultSection.getScoreIndicator();
	}

	/**
	* Returns <code>true</code> if this training exam result section is score indicator.
	*
	* @return <code>true</code> if this training exam result section is score indicator; <code>false</code> otherwise
	*/
	public boolean isScoreIndicator() {
		return _trainingExamResultSection.isScoreIndicator();
	}

	/**
	* Sets whether this training exam result section is score indicator.
	*
	* @param scoreIndicator the score indicator of this training exam result section
	*/
	public void setScoreIndicator(boolean scoreIndicator) {
		_trainingExamResultSection.setScoreIndicator(scoreIndicator);
	}

	/**
	* Returns the scoring algorithm of this training exam result section.
	*
	* @return the scoring algorithm of this training exam result section
	*/
	public java.lang.String getScoringAlgorithm() {
		return _trainingExamResultSection.getScoringAlgorithm();
	}

	/**
	* Sets the scoring algorithm of this training exam result section.
	*
	* @param scoringAlgorithm the scoring algorithm of this training exam result section
	*/
	public void setScoringAlgorithm(java.lang.String scoringAlgorithm) {
		_trainingExamResultSection.setScoringAlgorithm(scoringAlgorithm);
	}

	/**
	* Returns the mastery score of this training exam result section.
	*
	* @return the mastery score of this training exam result section
	*/
	public java.lang.String getMasteryScore() {
		return _trainingExamResultSection.getMasteryScore();
	}

	/**
	* Sets the mastery score of this training exam result section.
	*
	* @param masteryScore the mastery score of this training exam result section
	*/
	public void setMasteryScore(java.lang.String masteryScore) {
		_trainingExamResultSection.setMasteryScore(masteryScore);
	}

	/**
	* Returns the score of this training exam result section.
	*
	* @return the score of this training exam result section
	*/
	public java.lang.String getScore() {
		return _trainingExamResultSection.getScore();
	}

	/**
	* Sets the score of this training exam result section.
	*
	* @param score the score of this training exam result section
	*/
	public void setScore(java.lang.String score) {
		_trainingExamResultSection.setScore(score);
	}

	/**
	* Returns the standard error of estimate of this training exam result section.
	*
	* @return the standard error of estimate of this training exam result section
	*/
	public java.lang.String getStandardErrorOfEstimate() {
		return _trainingExamResultSection.getStandardErrorOfEstimate();
	}

	/**
	* Sets the standard error of estimate of this training exam result section.
	*
	* @param standardErrorOfEstimate the standard error of estimate of this training exam result section
	*/
	public void setStandardErrorOfEstimate(
		java.lang.String standardErrorOfEstimate) {
		_trainingExamResultSection.setStandardErrorOfEstimate(standardErrorOfEstimate);
	}

	/**
	* Returns the correct count of this training exam result section.
	*
	* @return the correct count of this training exam result section
	*/
	public int getCorrectCount() {
		return _trainingExamResultSection.getCorrectCount();
	}

	/**
	* Sets the correct count of this training exam result section.
	*
	* @param correctCount the correct count of this training exam result section
	*/
	public void setCorrectCount(int correctCount) {
		_trainingExamResultSection.setCorrectCount(correctCount);
	}

	/**
	* Returns the incorrect count of this training exam result section.
	*
	* @return the incorrect count of this training exam result section
	*/
	public int getIncorrectCount() {
		return _trainingExamResultSection.getIncorrectCount();
	}

	/**
	* Sets the incorrect count of this training exam result section.
	*
	* @param incorrectCount the incorrect count of this training exam result section
	*/
	public void setIncorrectCount(int incorrectCount) {
		_trainingExamResultSection.setIncorrectCount(incorrectCount);
	}

	/**
	* Returns the skipped count of this training exam result section.
	*
	* @return the skipped count of this training exam result section
	*/
	public int getSkippedCount() {
		return _trainingExamResultSection.getSkippedCount();
	}

	/**
	* Sets the skipped count of this training exam result section.
	*
	* @param skippedCount the skipped count of this training exam result section
	*/
	public void setSkippedCount(int skippedCount) {
		_trainingExamResultSection.setSkippedCount(skippedCount);
	}

	/**
	* Returns the grade of this training exam result section.
	*
	* @return the grade of this training exam result section
	*/
	public int getGrade() {
		return _trainingExamResultSection.getGrade();
	}

	/**
	* Sets the grade of this training exam result section.
	*
	* @param grade the grade of this training exam result section
	*/
	public void setGrade(int grade) {
		_trainingExamResultSection.setGrade(grade);
	}

	public boolean isNew() {
		return _trainingExamResultSection.isNew();
	}

	public void setNew(boolean n) {
		_trainingExamResultSection.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingExamResultSection.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingExamResultSection.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingExamResultSection.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingExamResultSection.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingExamResultSection.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingExamResultSection.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingExamResultSection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingExamResultSectionWrapper((TrainingExamResultSection)_trainingExamResultSection.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingExamResultSection trainingExamResultSection) {
		return _trainingExamResultSection.compareTo(trainingExamResultSection);
	}

	@Override
	public int hashCode() {
		return _trainingExamResultSection.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingExamResultSection> toCacheModel() {
		return _trainingExamResultSection.toCacheModel();
	}

	public com.liferay.osb.model.TrainingExamResultSection toEscapedModel() {
		return new TrainingExamResultSectionWrapper(_trainingExamResultSection.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingExamResultSection toUnescapedModel() {
		return new TrainingExamResultSectionWrapper(_trainingExamResultSection.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingExamResultSection.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingExamResultSection.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingExamResultSection.persist();
	}

	public java.lang.String getResult() {
		return _trainingExamResultSection.getResult();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingExamResultSectionWrapper)) {
			return false;
		}

		TrainingExamResultSectionWrapper trainingExamResultSectionWrapper = (TrainingExamResultSectionWrapper)obj;

		if (Validator.equals(_trainingExamResultSection,
					trainingExamResultSectionWrapper._trainingExamResultSection)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingExamResultSection getWrappedTrainingExamResultSection() {
		return _trainingExamResultSection;
	}

	public TrainingExamResultSection getWrappedModel() {
		return _trainingExamResultSection;
	}

	public void resetOriginalValues() {
		_trainingExamResultSection.resetOriginalValues();
	}

	private TrainingExamResultSection _trainingExamResultSection;
}