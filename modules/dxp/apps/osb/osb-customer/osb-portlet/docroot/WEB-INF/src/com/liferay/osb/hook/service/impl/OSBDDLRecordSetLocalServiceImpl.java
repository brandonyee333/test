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

package com.liferay.osb.hook.service.impl;

import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.RequiredDDLRecordSetDDLRecordException;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.service.TrainingCourseLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalService;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceWrapper;

import java.util.Locale;
import java.util.Map;

/**
 * @author Calvin Keum
 */
public class OSBDDLRecordSetLocalServiceImpl
	extends DDLRecordSetLocalServiceWrapper {

	public OSBDDLRecordSetLocalServiceImpl(
		DDLRecordSetLocalService ddlRecordSetLocalService) {

		super(ddlRecordSetLocalService);
	}

	@Override
	public DDLRecordSet addRecordSet(
			long userId, long groupId, long ddmStructureId, String recordSetKey,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			int minDisplayRows, int scope, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long trainingCourseId = GetterUtil.getLong(
			serviceContext.getAttribute("trainingCourseId"));

		if (trainingCourseId > 0) {
			nameMap = getNameMap(serviceContext);

			descriptionMap = nameMap;
		}

		return super.addRecordSet(
			userId, groupId, ddmStructureId, recordSetKey, nameMap,
			descriptionMap, minDisplayRows, scope, serviceContext);
	}

	@Override
	public void deleteRecordSet(long recordSetId)
		throws PortalException, SystemException {

		TrainingEvent trainingEvent =
			TrainingEventLocalServiceUtil.fetchTrainingEventByDDLRecordSetId(
				recordSetId);

		if (trainingEvent != null) {
			validate(recordSetId);

			trainingEvent.setDDLRecordSetId(0);

			TrainingEventLocalServiceUtil.updateTrainingEvent(
				trainingEvent, false);
		}

		super.deleteRecordSet(recordSetId);
	}

	@Override
	public DDLRecordSet updateRecordSet(
			long recordSetId, long ddmStructureId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, int minDisplayRows,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		long trainingCourseId = GetterUtil.getLong(
			serviceContext.getAttribute("trainingCourseId"));

		if (trainingCourseId > 0) {
			nameMap = getNameMap(serviceContext);

			descriptionMap = nameMap;
		}

		return super.updateRecordSet(
			recordSetId, ddmStructureId, nameMap, descriptionMap,
			minDisplayRows, serviceContext);
	}

	protected Map<Locale, String> getNameMap(ServiceContext serviceContext)
		throws PortalException, SystemException {

		StringBundler sb = new StringBundler(5);

		int type = GetterUtil.getInteger(serviceContext.getAttribute("type"));

		if (type == TrainingEventConstants.TYPE_PRIVATE) {
			String name = GetterUtil.getString(
				serviceContext.getAttribute("name"));

			sb.append(name);
		}
		else {
			String addressCity = GetterUtil.getString(
				serviceContext.getAttribute("addressCity"));

			if (Validator.isNotNull(addressCity)) {
				sb.append(addressCity);
				sb.append(StringPool.SPACE);
			}

			sb.append(
				StringUtil.upperCaseFirstLetter(
					TrainingEventConstants.getTypeLabel(type)));
		}

		sb.append(StringPool.SPACE);

		long trainingCourseId = GetterUtil.getLong(
			serviceContext.getAttribute("trainingCourseId"));

		TrainingCourse trainingCourse =
			TrainingCourseLocalServiceUtil.getTrainingCourse(trainingCourseId);

		sb.append(trainingCourse.getName());

		return LocalizationUtil.getLocalizationMap(sb.toString());
	}

	protected void validate(long recordSetId)
		throws PortalException, SystemException {

		int recordsCount = DDLRecordLocalServiceUtil.getRecordsCount(
			recordSetId, WorkflowConstants.STATUS_ANY);

		if (recordsCount > 0) {
			throw new RequiredDDLRecordSetDDLRecordException();
		}
	}

}