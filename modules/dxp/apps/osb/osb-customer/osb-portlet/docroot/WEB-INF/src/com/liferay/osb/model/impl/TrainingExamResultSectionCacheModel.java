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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.TrainingExamResultSection;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing TrainingExamResultSection in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultSection
 * @generated
 */
public class TrainingExamResultSectionCacheModel implements CacheModel<TrainingExamResultSection>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{trainingExamResultSectionId=");
		sb.append(trainingExamResultSectionId);
		sb.append(", trainingExamResultId=");
		sb.append(trainingExamResultId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", sectionKey=");
		sb.append(sectionKey);
		sb.append(", scoreIndicator=");
		sb.append(scoreIndicator);
		sb.append(", scoringAlgorithm=");
		sb.append(scoringAlgorithm);
		sb.append(", masteryScore=");
		sb.append(masteryScore);
		sb.append(", score=");
		sb.append(score);
		sb.append(", standardErrorOfEstimate=");
		sb.append(standardErrorOfEstimate);
		sb.append(", correctCount=");
		sb.append(correctCount);
		sb.append(", incorrectCount=");
		sb.append(incorrectCount);
		sb.append(", skippedCount=");
		sb.append(skippedCount);
		sb.append(", grade=");
		sb.append(grade);
		sb.append("}");

		return sb.toString();
	}

	public TrainingExamResultSection toEntityModel() {
		TrainingExamResultSectionImpl trainingExamResultSectionImpl = new TrainingExamResultSectionImpl();

		trainingExamResultSectionImpl.setTrainingExamResultSectionId(trainingExamResultSectionId);
		trainingExamResultSectionImpl.setTrainingExamResultId(trainingExamResultId);

		if (title == null) {
			trainingExamResultSectionImpl.setTitle(StringPool.BLANK);
		}
		else {
			trainingExamResultSectionImpl.setTitle(title);
		}

		if (sectionKey == null) {
			trainingExamResultSectionImpl.setSectionKey(StringPool.BLANK);
		}
		else {
			trainingExamResultSectionImpl.setSectionKey(sectionKey);
		}

		trainingExamResultSectionImpl.setScoreIndicator(scoreIndicator);

		if (scoringAlgorithm == null) {
			trainingExamResultSectionImpl.setScoringAlgorithm(StringPool.BLANK);
		}
		else {
			trainingExamResultSectionImpl.setScoringAlgorithm(scoringAlgorithm);
		}

		if (masteryScore == null) {
			trainingExamResultSectionImpl.setMasteryScore(StringPool.BLANK);
		}
		else {
			trainingExamResultSectionImpl.setMasteryScore(masteryScore);
		}

		if (score == null) {
			trainingExamResultSectionImpl.setScore(StringPool.BLANK);
		}
		else {
			trainingExamResultSectionImpl.setScore(score);
		}

		if (standardErrorOfEstimate == null) {
			trainingExamResultSectionImpl.setStandardErrorOfEstimate(StringPool.BLANK);
		}
		else {
			trainingExamResultSectionImpl.setStandardErrorOfEstimate(standardErrorOfEstimate);
		}

		trainingExamResultSectionImpl.setCorrectCount(correctCount);
		trainingExamResultSectionImpl.setIncorrectCount(incorrectCount);
		trainingExamResultSectionImpl.setSkippedCount(skippedCount);
		trainingExamResultSectionImpl.setGrade(grade);

		trainingExamResultSectionImpl.resetOriginalValues();

		return trainingExamResultSectionImpl;
	}

	public long trainingExamResultSectionId;
	public long trainingExamResultId;
	public String title;
	public String sectionKey;
	public boolean scoreIndicator;
	public String scoringAlgorithm;
	public String masteryScore;
	public String score;
	public String standardErrorOfEstimate;
	public int correctCount;
	public int incorrectCount;
	public int skippedCount;
	public int grade;
}