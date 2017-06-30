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

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.FieldConstants;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;

import java.util.List;
import java.util.Map;

/**
 * @author Peter Shin
 */
public class TrainingCustomerImpl extends TrainingCustomerBaseImpl {

	public TrainingCustomerImpl() {
	}

	public String getEmailAddress() {
		User user = getUser();

		if (user == null) {
			return StringPool.BLANK;
		}

		long trainingCustomerId = getTrainingCustomerId();

		if ((getClassNameId() ==
				PortalUtil.getClassNameId(TrainingEvent.class)) &&
			(getClassPK() !=
				TrainingEventConstants.DEFAULT_TRAINING_EVENT_ID)) {

			TrainingCustomer trainingCustomer = null;

			try {
				trainingCustomer =
					TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(
						getUserId(),
						PortalUtil.getClassNameId(TrainingEvent.class),
						TrainingEventConstants.DEFAULT_TRAINING_EVENT_ID);
			}
			catch (Exception e) {
				_log.error(e, e);
			}

			if (trainingCustomer == null) {
				return user.getEmailAddress();
			}

			trainingCustomerId = trainingCustomer.getTrainingCustomerId();
		}

		String generatedEmailAddress = AdminUtil.getGeneratedEmailAddress(
			trainingCustomerId);

		if (Validator.equals(user.getEmailAddress(), generatedEmailAddress)) {
			return StringPool.BLANK;
		}
		else {
			return user.getEmailAddress();
		}
	}

	public String getFirstName() {
		User user = getUser();

		if (user == null) {
			return StringPool.BLANK;
		}

		return user.getFirstName();
	}

	public String getLastName() {
		User user = getUser();

		if (user == null) {
			return StringPool.BLANK;
		}

		return user.getLastName();
	}

	public String getTrainingCertificateKey()
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			TrainingCertificateLocalServiceUtil.
				fetchTrainingCertificateByTrainingCustomerId(
					getTrainingCustomerId());

		if (trainingCertificate == null) {
			return StringPool.BLANK;
		}

		String key = trainingCertificate.getKey();

		String[] keyParts = key.split("(?<=\\G.{5})");

		return StringUtil.merge(keyParts, StringPool.DASH);
	}

	public String getTrainingSurveyResultHTML()
		throws PortalException, SystemException {

		if ((getStatus() != TrainingCustomerConstants.STATUS_CERTIFIED) ||
			(getClassNameId() !=
				PortalUtil.getClassNameId(TrainingEvent.class))) {

			return StringPool.BLANK;
		}

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.getTrainingEvent(getClassPK());

		DDLRecordSet ddlRecordSet =
			DDLRecordSetLocalServiceUtil.fetchDDLRecordSet(
				trainingEvent.getDDLRecordSetId());

		if (ddlRecordSet == null) {
			return StringPool.BLANK;
		}

		DDMStructure ddmStructure = ddlRecordSet.getDDMStructure(0);

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				getTrainingCustomerId());

		List<DDLRecord> ddlRecords = DDLRecordLocalServiceUtil.getRecords(
			trainingEvent.getDDLRecordSetId(), trainingCustomer.getUserId());

		DDLRecord ddlRecord = ddlRecords.get(0);

		DDLRecordVersion ddlRecordVersion = ddlRecord.getRecordVersion();

		Fields fields = StorageEngineUtil.getFields(
			ddlRecordVersion.getDDMStorageId());

		Map<String, Map<String, String>> fieldsMap = ddmStructure.getFieldsMap(
			trainingEvent.getLanguageId());

		StringBundler sb = new StringBundler(fieldsMap.size() * 5);

		for (Map<String, String> fieldMap : fieldsMap.values()) {
			sb.append("<b>");

			String label = fieldMap.get(FieldConstants.LABEL);

			sb.append(label);

			sb.append("</b><br />");

			String value = StringPool.BLANK;

			String name = fieldMap.get(FieldConstants.NAME);

			if (fields.contains(name)) {
				Field field = fields.get(name);

				value = HtmlUtil.escape(
					field.getRenderedValue(
						LocaleUtil.fromLanguageId(
							trainingEvent.getLanguageId(), false)));
			}
			else {
				value = StringPool.BLANK;
			}

			sb.append(value);

			sb.append("<br /><br />");
		}

		return sb.toString();
	}

	public String getType() {
		return AdminUtil.getTrainingCustomerType(getUser());
	}

	public User getUser() {
		try {
			if (_user == null) {
				_user = UserLocalServiceUtil.getUserById(getUserId());
			}

			return _user;
		}
		catch (Exception e) {
			_log.error(e, e);

			return null;
		}
	}

	public boolean isTrainingUser() {
		if (Validator.equals(
				getType(), TrainingCustomerConstants.TYPE_TRAINING_USER)) {

			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TrainingCustomerImpl.class);

	private User _user;

}