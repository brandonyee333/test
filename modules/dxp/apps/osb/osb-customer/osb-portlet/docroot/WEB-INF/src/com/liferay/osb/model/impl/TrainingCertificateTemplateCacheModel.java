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

import com.liferay.osb.model.TrainingCertificateTemplate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingCertificateTemplate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificateTemplate
 * @generated
 */
public class TrainingCertificateTemplateCacheModel implements CacheModel<TrainingCertificateTemplate>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{trainingCertificateTemplateId=");
		sb.append(trainingCertificateTemplateId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", type=");
		sb.append(type);
		sb.append("}");

		return sb.toString();
	}

	public TrainingCertificateTemplate toEntityModel() {
		TrainingCertificateTemplateImpl trainingCertificateTemplateImpl = new TrainingCertificateTemplateImpl();

		trainingCertificateTemplateImpl.setTrainingCertificateTemplateId(trainingCertificateTemplateId);
		trainingCertificateTemplateImpl.setUserId(userId);

		if (userName == null) {
			trainingCertificateTemplateImpl.setUserName(StringPool.BLANK);
		}
		else {
			trainingCertificateTemplateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			trainingCertificateTemplateImpl.setCreateDate(null);
		}
		else {
			trainingCertificateTemplateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trainingCertificateTemplateImpl.setModifiedDate(null);
		}
		else {
			trainingCertificateTemplateImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		if (name == null) {
			trainingCertificateTemplateImpl.setName(StringPool.BLANK);
		}
		else {
			trainingCertificateTemplateImpl.setName(name);
		}

		if (description == null) {
			trainingCertificateTemplateImpl.setDescription(StringPool.BLANK);
		}
		else {
			trainingCertificateTemplateImpl.setDescription(description);
		}

		trainingCertificateTemplateImpl.setType(type);

		trainingCertificateTemplateImpl.resetOriginalValues();

		return trainingCertificateTemplateImpl;
	}

	public long trainingCertificateTemplateId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String description;
	public int type;
}