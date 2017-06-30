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

import com.liferay.osb.model.TrainingEvent;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TrainingEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrainingEvent
 * @generated
 */
public class TrainingEventCacheModel implements CacheModel<TrainingEvent>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{trainingEventId=");
		sb.append(trainingEventId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", DDLRecordSetId=");
		sb.append(DDLRecordSetId);
		sb.append(", partnerEntryId=");
		sb.append(partnerEntryId);
		sb.append(", trainingCertificateTemplateId=");
		sb.append(trainingCertificateTemplateId);
		sb.append(", trainingCourseId=");
		sb.append(trainingCourseId);
		sb.append(", trainingLocationId=");
		sb.append(trainingLocationId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", portalMinorVersion=");
		sb.append(portalMinorVersion);
		sb.append(", type=");
		sb.append(type);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", localizedSlides=");
		sb.append(localizedSlides);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", maxCustomers=");
		sb.append(maxCustomers);
		sb.append(", enrollmentURL=");
		sb.append(enrollmentURL);
		sb.append("}");

		return sb.toString();
	}

	public TrainingEvent toEntityModel() {
		TrainingEventImpl trainingEventImpl = new TrainingEventImpl();

		trainingEventImpl.setTrainingEventId(trainingEventId);
		trainingEventImpl.setUserId(userId);

		if (userName == null) {
			trainingEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			trainingEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			trainingEventImpl.setCreateDate(null);
		}
		else {
			trainingEventImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			trainingEventImpl.setModifiedDate(null);
		}
		else {
			trainingEventImpl.setModifiedDate(new Date(modifiedDate));
		}

		trainingEventImpl.setDDLRecordSetId(DDLRecordSetId);
		trainingEventImpl.setPartnerEntryId(partnerEntryId);
		trainingEventImpl.setTrainingCertificateTemplateId(trainingCertificateTemplateId);
		trainingEventImpl.setTrainingCourseId(trainingCourseId);
		trainingEventImpl.setTrainingLocationId(trainingLocationId);

		if (name == null) {
			trainingEventImpl.setName(StringPool.BLANK);
		}
		else {
			trainingEventImpl.setName(name);
		}

		if (emailAddress == null) {
			trainingEventImpl.setEmailAddress(StringPool.BLANK);
		}
		else {
			trainingEventImpl.setEmailAddress(emailAddress);
		}

		trainingEventImpl.setPortalMinorVersion(portalMinorVersion);
		trainingEventImpl.setType(type);

		if (languageId == null) {
			trainingEventImpl.setLanguageId(StringPool.BLANK);
		}
		else {
			trainingEventImpl.setLanguageId(languageId);
		}

		trainingEventImpl.setLocalizedSlides(localizedSlides);

		if (timeZoneId == null) {
			trainingEventImpl.setTimeZoneId(StringPool.BLANK);
		}
		else {
			trainingEventImpl.setTimeZoneId(timeZoneId);
		}

		if (startDate == Long.MIN_VALUE) {
			trainingEventImpl.setStartDate(null);
		}
		else {
			trainingEventImpl.setStartDate(new Date(startDate));
		}

		if (endDate == Long.MIN_VALUE) {
			trainingEventImpl.setEndDate(null);
		}
		else {
			trainingEventImpl.setEndDate(new Date(endDate));
		}

		trainingEventImpl.setAddressId(addressId);
		trainingEventImpl.setMaxCustomers(maxCustomers);

		if (enrollmentURL == null) {
			trainingEventImpl.setEnrollmentURL(StringPool.BLANK);
		}
		else {
			trainingEventImpl.setEnrollmentURL(enrollmentURL);
		}

		trainingEventImpl.resetOriginalValues();

		return trainingEventImpl;
	}

	public long trainingEventId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long DDLRecordSetId;
	public long partnerEntryId;
	public long trainingCertificateTemplateId;
	public long trainingCourseId;
	public long trainingLocationId;
	public String name;
	public String emailAddress;
	public int portalMinorVersion;
	public int type;
	public String languageId;
	public boolean localizedSlides;
	public String timeZoneId;
	public long startDate;
	public long endDate;
	public long addressId;
	public int maxCustomers;
	public String enrollmentURL;
}