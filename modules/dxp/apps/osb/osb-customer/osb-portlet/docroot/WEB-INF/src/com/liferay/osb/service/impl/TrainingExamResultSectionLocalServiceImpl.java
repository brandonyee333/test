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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.TrainingExamResultSection;
import com.liferay.osb.service.base.TrainingExamResultSectionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Calvin Keum
 */
public class TrainingExamResultSectionLocalServiceImpl
	extends TrainingExamResultSectionLocalServiceBaseImpl {

	public TrainingExamResultSection addTrainingExamResultSection(
			long trainingExamResultId, String title, String sectionKey,
			boolean scoreIndicator, String scoringAlgorithm,
			String masteryScore, String score, String standardErrorOfEstimate,
			int correctCount, int incorrectCount, int skippedCount, int grade)
		throws SystemException {

		long trainingExamResultSectionId = counterLocalService.increment();

		TrainingExamResultSection trainingExamResultSection =
			trainingExamResultSectionPersistence.create(
				trainingExamResultSectionId);

		trainingExamResultSection.setTrainingExamResultId(trainingExamResultId);
		trainingExamResultSection.setTitle(title);
		trainingExamResultSection.setSectionKey(sectionKey);
		trainingExamResultSection.setScoreIndicator(scoreIndicator);
		trainingExamResultSection.setScoringAlgorithm(scoringAlgorithm);
		trainingExamResultSection.setMasteryScore(masteryScore);
		trainingExamResultSection.setScore(score);
		trainingExamResultSection.setStandardErrorOfEstimate(
			standardErrorOfEstimate);
		trainingExamResultSection.setCorrectCount(correctCount);
		trainingExamResultSection.setIncorrectCount(incorrectCount);
		trainingExamResultSection.setSkippedCount(skippedCount);
		trainingExamResultSection.setGrade(grade);

		return trainingExamResultSectionPersistence.update(
			trainingExamResultSection, false);
	}

	public List<TrainingExamResultSection> getTrainingExamResultSections(
			long trainingExamResultId)
		throws SystemException {

		return trainingExamResultSectionPersistence.findByTrainingExamResultId(
			trainingExamResultId);
	}

	public List<TrainingExamResultSection> search(
			long trainingExamResultId, LinkedHashMap<String, Object> params)
		throws SystemException {

		return trainingExamResultSectionFinder.findByTrainingExamResultId(
			trainingExamResultId, params);
	}

}