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

import com.liferay.osb.model.TrainingExamResult;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingExamResult in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExamResult
 * @generated
 */
public class TrainingExamResultCacheModel implements CacheModel<TrainingExamResult>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{trainingExamResultId=");
		sb.append(trainingExamResultId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", trainingExamId=");
		sb.append(trainingExamId);
		sb.append(", recordType=");
		sb.append(recordType);
		sb.append(", registrationNumber=");
		sb.append(registrationNumber);
		sb.append(", formKey=");
		sb.append(formKey);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", testScore=");
		sb.append(testScore);
		sb.append(", correctCount=");
		sb.append(correctCount);
		sb.append(", incorrectCount=");
		sb.append(incorrectCount);
		sb.append(", skippedCount=");
		sb.append(skippedCount);
		sb.append(", grade=");
		sb.append(grade);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	public TrainingExamResult toEntityModel() {
		TrainingExamResultImpl trainingExamResultImpl = new TrainingExamResultImpl();

		trainingExamResultImpl.setTrainingExamResultId(trainingExamResultId);

		if (createDate == Long.MIN_VALUE) {
			trainingExamResultImpl.setCreateDate(null);
		}
		else {
			trainingExamResultImpl.setCreateDate(new Date(createDate));
		}

		trainingExamResultImpl.setTrainingExamId(trainingExamId);
		trainingExamResultImpl.setRecordType(recordType);

		if (registrationNumber == null) {
			trainingExamResultImpl.setRegistrationNumber(StringPool.BLANK);
		}
		else {
			trainingExamResultImpl.setRegistrationNumber(registrationNumber);
		}

		if (formKey == null) {
			trainingExamResultImpl.setFormKey(StringPool.BLANK);
		}
		else {
			trainingExamResultImpl.setFormKey(formKey);
		}

		if (startDate == Long.MIN_VALUE) {
			trainingExamResultImpl.setStartDate(null);
		}
		else {
			trainingExamResultImpl.setStartDate(new Date(startDate));
		}

		if (testScore == null) {
			trainingExamResultImpl.setTestScore(StringPool.BLANK);
		}
		else {
			trainingExamResultImpl.setTestScore(testScore);
		}

		trainingExamResultImpl.setCorrectCount(correctCount);
		trainingExamResultImpl.setIncorrectCount(incorrectCount);
		trainingExamResultImpl.setSkippedCount(skippedCount);
		trainingExamResultImpl.setGrade(grade);
		trainingExamResultImpl.setStatus(status);

		trainingExamResultImpl.resetOriginalValues();

		return trainingExamResultImpl;
	}

	public long trainingExamResultId;
	public long createDate;
	public long trainingExamId;
	public int recordType;
	public String registrationNumber;
	public String formKey;
	public long startDate;
	public String testScore;
	public int correctCount;
	public int incorrectCount;
	public int skippedCount;
	public int grade;
	public int status;
}