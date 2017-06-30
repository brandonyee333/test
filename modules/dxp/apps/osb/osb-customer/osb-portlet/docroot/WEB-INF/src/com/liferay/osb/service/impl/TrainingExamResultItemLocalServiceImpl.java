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

import com.liferay.osb.model.TrainingExamResultItem;
import com.liferay.osb.service.base.TrainingExamResultItemLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Calvin Keum
 */
public class TrainingExamResultItemLocalServiceImpl
	extends TrainingExamResultItemLocalServiceBaseImpl {

	public TrainingExamResultItem addTrainingExamResultItem(
			long trainingExamResultId, long trainingExamResultSectionId,
			String name, String status, String key, int distractorCount,
			String type, String response, String score, int time, String
			learningResources)
		throws SystemException {

		long trainingExamResultItemId = counterLocalService.increment();

		TrainingExamResultItem trainingExamResultItem =
			trainingExamResultItemPersistence.create(trainingExamResultItemId);

		trainingExamResultItem.setTrainingExamResultId(trainingExamResultId);
		trainingExamResultItem.setTrainingExamResultSectionId(
			trainingExamResultSectionId);
		trainingExamResultItem.setName(name);
		trainingExamResultItem.setStatus(status);
		trainingExamResultItem.setKey(key);
		trainingExamResultItem.setDistractorCount(distractorCount);
		trainingExamResultItem.setType(type);
		trainingExamResultItem.setResponse(response);
		trainingExamResultItem.setScore(score);
		trainingExamResultItem.setTime(time);
		trainingExamResultItem.setLearningResources(learningResources);

		return trainingExamResultItemPersistence.update(
			trainingExamResultItem, false);
	}

	public List<TrainingExamResultItem> getTrainingExamResultItems(
			long trainingExamResultId)
		throws PortalException, SystemException {

		return trainingExamResultItemPersistence.findByTrainingExamResultId(
			trainingExamResultId);
	}

	public List<TrainingExamResultItem> search(
			long trainingExamResultId, LinkedHashMap<String, Object> params)
		throws SystemException {

		return trainingExamResultItemFinder.findByTrainingExamResultId(
			trainingExamResultId, params);
	}

}