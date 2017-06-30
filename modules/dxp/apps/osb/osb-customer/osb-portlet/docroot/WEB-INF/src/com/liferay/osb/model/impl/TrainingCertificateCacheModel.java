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

import com.liferay.osb.model.TrainingCertificate;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingCertificate in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificate
 * @generated
 */
public class TrainingCertificateCacheModel implements CacheModel<TrainingCertificate>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{trainingCertificateId=");
		sb.append(trainingCertificateId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", trainingCustomerId=");
		sb.append(trainingCustomerId);
		sb.append(", userProfileHistoryId=");
		sb.append(userProfileHistoryId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", certifiedDate=");
		sb.append(certifiedDate);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", surveyStatus=");
		sb.append(surveyStatus);
		sb.append("}");

		return sb.toString();
	}

	public TrainingCertificate toEntityModel() {
		TrainingCertificateImpl trainingCertificateImpl = new TrainingCertificateImpl();

		trainingCertificateImpl.setTrainingCertificateId(trainingCertificateId);
		trainingCertificateImpl.setUserId(userId);

		if (userName == null) {
			trainingCertificateImpl.setUserName(StringPool.BLANK);
		}
		else {
			trainingCertificateImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			trainingCertificateImpl.setCreateDate(null);
		}
		else {
			trainingCertificateImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trainingCertificateImpl.setModifiedDate(null);
		}
		else {
			trainingCertificateImpl.setModifiedDate(new Date(modifiedDate));
		}

		trainingCertificateImpl.setTrainingCustomerId(trainingCustomerId);
		trainingCertificateImpl.setUserProfileHistoryId(userProfileHistoryId);

		if (key == null) {
			trainingCertificateImpl.setKey(StringPool.BLANK);
		}
		else {
			trainingCertificateImpl.setKey(key);
		}

		if (certifiedDate == Long.MIN_VALUE) {
			trainingCertificateImpl.setCertifiedDate(null);
		}
		else {
			trainingCertificateImpl.setCertifiedDate(new Date(certifiedDate));
		}

		if (comments == null) {
			trainingCertificateImpl.setComments(StringPool.BLANK);
		}
		else {
			trainingCertificateImpl.setComments(comments);
		}

		trainingCertificateImpl.setSurveyStatus(surveyStatus);

		trainingCertificateImpl.resetOriginalValues();

		return trainingCertificateImpl;
	}

	public long trainingCertificateId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long trainingCustomerId;
	public long userProfileHistoryId;
	public String key;
	public long certifiedDate;
	public String comments;
	public int surveyStatus;
}