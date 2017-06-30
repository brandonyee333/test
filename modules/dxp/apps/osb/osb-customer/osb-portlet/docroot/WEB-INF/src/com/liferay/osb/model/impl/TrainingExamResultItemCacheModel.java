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

import com.liferay.osb.model.TrainingExamResultItem;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing TrainingExamResultItem in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResultItem
 * @generated
 */
public class TrainingExamResultItemCacheModel implements CacheModel<TrainingExamResultItem>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{trainingExamResultItemId=");
		sb.append(trainingExamResultItemId);
		sb.append(", trainingExamResultId=");
		sb.append(trainingExamResultId);
		sb.append(", trainingExamResultSectionId=");
		sb.append(trainingExamResultSectionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", status=");
		sb.append(status);
		sb.append(", key=");
		sb.append(key);
		sb.append(", distractorCount=");
		sb.append(distractorCount);
		sb.append(", type=");
		sb.append(type);
		sb.append(", response=");
		sb.append(response);
		sb.append(", score=");
		sb.append(score);
		sb.append(", time=");
		sb.append(time);
		sb.append(", learningResources=");
		sb.append(learningResources);
		sb.append("}");

		return sb.toString();
	}

	public TrainingExamResultItem toEntityModel() {
		TrainingExamResultItemImpl trainingExamResultItemImpl = new TrainingExamResultItemImpl();

		trainingExamResultItemImpl.setTrainingExamResultItemId(trainingExamResultItemId);
		trainingExamResultItemImpl.setTrainingExamResultId(trainingExamResultId);
		trainingExamResultItemImpl.setTrainingExamResultSectionId(trainingExamResultSectionId);

		if (name == null) {
			trainingExamResultItemImpl.setName(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setName(name);
		}

		if (status == null) {
			trainingExamResultItemImpl.setStatus(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setStatus(status);
		}

		if (key == null) {
			trainingExamResultItemImpl.setKey(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setKey(key);
		}

		trainingExamResultItemImpl.setDistractorCount(distractorCount);

		if (type == null) {
			trainingExamResultItemImpl.setType(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setType(type);
		}

		if (response == null) {
			trainingExamResultItemImpl.setResponse(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setResponse(response);
		}

		if (score == null) {
			trainingExamResultItemImpl.setScore(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setScore(score);
		}

		trainingExamResultItemImpl.setTime(time);

		if (learningResources == null) {
			trainingExamResultItemImpl.setLearningResources(StringPool.BLANK);
		}
		else {
			trainingExamResultItemImpl.setLearningResources(learningResources);
		}

		trainingExamResultItemImpl.resetOriginalValues();

		return trainingExamResultItemImpl;
	}

	public long trainingExamResultItemId;
	public long trainingExamResultId;
	public long trainingExamResultSectionId;
	public String name;
	public String status;
	public String key;
	public int distractorCount;
	public String type;
	public String response;
	public String score;
	public int time;
	public String learningResources;
}