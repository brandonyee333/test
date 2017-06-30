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

import com.liferay.osb.model.TrainingExam;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing TrainingExam in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingExam
 * @generated
 */
public class TrainingExamCacheModel implements CacheModel<TrainingExam>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{trainingExamId=");
		sb.append(trainingExamId);
		sb.append(", trainingCertificateTemplateId=");
		sb.append(trainingCertificateTemplateId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	public TrainingExam toEntityModel() {
		TrainingExamImpl trainingExamImpl = new TrainingExamImpl();

		trainingExamImpl.setTrainingExamId(trainingExamId);
		trainingExamImpl.setTrainingCertificateTemplateId(trainingCertificateTemplateId);

		if (name == null) {
			trainingExamImpl.setName(StringPool.BLANK);
		}
		else {
			trainingExamImpl.setName(name);
		}

		trainingExamImpl.resetOriginalValues();

		return trainingExamImpl;
	}

	public long trainingExamId;
	public long trainingCertificateTemplateId;
	public String name;
}