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

import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketInformation;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.service.base.TicketInformationLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brent Krone-Schmidt
 */
public class TicketInformationLocalServiceImpl
	extends TicketInformationLocalServiceBaseImpl {

	public TicketInformation addTicketInformation(
			long ticketEntryId, long fieldId, String data)
		throws PortalException {

		Date now = new Date();

		long ticketInformationId = counterLocalService.increment();

		TicketInformation ticketInformation =
			ticketInformationPersistence.create(ticketInformationId);

		ticketInformation.setCreateDate(now);
		ticketInformation.setModifiedDate(now);
		ticketInformation.setTicketEntryId(ticketEntryId);
		ticketInformation.setFieldId(fieldId);
		ticketInformation.setData(data);

		//TODO implement serviceContext how needed

		ServiceContext serviceContext = new ServiceContext();

		return ticketInformationPersistence.update(
			ticketInformation, serviceContext);
	}

	public String getData(long ticketEntryId, long fieldId)
		throws PortalException {

		TicketInformation ticketInformation =
			ticketInformationPersistence.findByTEI_FI(ticketEntryId, fieldId);

		return ticketInformation.getData();
	}

	public Map<Long, String> getFieldsMap(long ticketEntryId) {
		List<TicketInformation> ticketInformationList =
			ticketInformationPersistence.findByTicketEntryId(ticketEntryId);

		HashMap<Long, String> fieldsMap = new HashMap<>();

		for (TicketInformation ticketInformation : ticketInformationList) {
			fieldsMap.put(
				ticketInformation.getFieldId(), ticketInformation.getData());
		}

		return fieldsMap;
	}

	public List<TicketInformation> getTicketInformationList(
		long ticketEntryId) {

		return ticketInformationPersistence.findByTicketEntryId(ticketEntryId);
	}

	public List<TicketInformation> updateTicketInformation(
			long ticketEntryId, Map<Long, String> fieldsMap)
		throws PortalException {

		List<TicketInformation> ticketInformationList = new ArrayList<>();

		//TODO implement serviceContext as needed

		ServiceContext serviceContext = new ServiceContext();

		for (Map.Entry<Long, String> entry : fieldsMap.entrySet()) {
			long fieldId = entry.getKey();
			String data = entry.getValue();

			TicketInformation ticketInformation =
				ticketInformationPersistence.fetchByTEI_FI(
					ticketEntryId, fieldId);

			if (ticketInformation == null) {
				ticketInformation = addTicketInformation(
					ticketEntryId, fieldId, data);
			}
			else {
				ticketInformation.setModifiedDate(new Date());
				ticketInformation.setData(data);

				ticketInformation = ticketInformationPersistence.update(
					ticketInformation, serviceContext);
			}

			ticketInformationList.add(ticketInformation);
		}

		return ticketInformationList;
	}

	public List<TicketInformation> updateTicketInformation(
			long userId, String userName, long ticketEntryId,
			Map<Long, String> fieldsMap, ServiceContext serviceContext)
		throws PortalException {

		Date now = serviceContext.getCreateDate(new Date());

		Map<Long, String> oldfieldsMap = getFieldsMap(ticketEntryId);

		List<TicketInformation> ticketInformationList = new ArrayList<>();

		for (Map.Entry<Long, String> entry : fieldsMap.entrySet()) {
			long fieldId = entry.getKey();
			String data = entry.getValue();

			TicketInformation ticketInformation =
				ticketInformationPersistence.fetchByTEI_FI(
					ticketEntryId, fieldId);

			if (ticketInformation == null) {
				ticketInformation = addTicketInformation(
					ticketEntryId, fieldId, data);
			}
			else {
				ticketInformation.setModifiedDate(now);
				ticketInformation.setData(data);

				//TODO implement serviceContext as needed

				ticketInformation = ticketInformationPersistence.update(
					ticketInformation, serviceContext);
			}

			ticketInformationList.add(ticketInformation);
		}

		// Audit Entry

		updateAuditEntry(
			userId, userName, ticketEntryId, now, oldfieldsMap, fieldsMap,
			serviceContext);

		return ticketInformationList;
	}

	protected void updateAuditEntry(
			long userId, String userName, long classPK, Date createDate,
			Map<Long, String> oldFieldsMap, Map<Long, String> newFieldsMap,
			ServiceContext serviceContext)
		throws PortalException {

		long classNameId = classNameLocalService.getClassNameId(
			TicketEntry.class.getName());

		long auditSetId = GetterUtil.getLong(
			serviceContext.getAttribute("auditSetId"));

		if (auditSetId == 0) {
			auditSetId = auditEntryLocalService.getNextAuditSetId(
				TicketEntry.class.getName(), classPK);
		}

		int auditAction = AuditEntryConstants.ACTION_UPDATE;

		newFieldsMap = updateCustomFieldsAuditEntry(
			userId, userName, createDate, classNameId, classPK, auditSetId,
			auditAction, oldFieldsMap, newFieldsMap);

		for (Map.Entry<Long, String> entry : newFieldsMap.entrySet()) {
			long fieldId = entry.getKey();
			String newData = entry.getValue();

			String oldData = oldFieldsMap.get(fieldId);

			if (newData.equals(oldData)) {
				continue;
			}

			int auditField = TicketInformationConstants.getAuditField(fieldId);
			int visibility = TicketInformationConstants.getAuditVisibility(
				fieldId);
			String oldLabel = TicketInformationConstants.getAuditLabel(
				fieldId, oldData);
			String newLabel = TicketInformationConstants.getAuditLabel(
				fieldId, newData);

			auditEntryLocalService.addAuditEntry(
				userId, userName, createDate, classNameId, classPK, auditSetId,
				classNameId, classPK, auditAction, auditField, visibility,
				oldLabel, oldData, newLabel, newData);
		}
	}

	protected Map<Long, String> updateCustomFieldsAuditEntry(
			long userId, String userName, Date createDate, long classNameId,
			long classPK, long auditSetId, int auditAction,
			Map<Long, String> oldFieldsMap, Map<Long, String> newFieldsMap)
		throws PortalException {

		String newDatabaseUploadMethod = newFieldsMap.get(
			TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD);

		if (newDatabaseUploadMethod != null) {
			String oldDatabaseUploadMethod = GetterUtil.getString(
				oldFieldsMap.get(
					TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD));

			String oldDatabaseFTPFileName = GetterUtil.getString(
				oldFieldsMap.get(
					TicketInformationConstants.FIELD_DATABASE_FTP_FILENAME));
			String newDatabaseFTPFileName = GetterUtil.getString(
				newFieldsMap.get(
					TicketInformationConstants.FIELD_DATABASE_FTP_FILENAME));

			if (!oldDatabaseUploadMethod.equals(newDatabaseUploadMethod) ||
				!oldDatabaseFTPFileName.equals(newDatabaseFTPFileName)) {

				int auditField = TicketInformationConstants.getAuditField(
					TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD);
				int visibility = TicketInformationConstants.getAuditVisibility(
					TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD);

				auditEntryLocalService.addAuditEntry(
					userId, userName, createDate, classNameId, classPK,
					auditSetId, classNameId, classPK, auditAction, auditField,
					visibility, oldDatabaseFTPFileName, oldDatabaseUploadMethod,
					newDatabaseFTPFileName, newDatabaseUploadMethod);
			}

			newFieldsMap.remove(
				TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD);
			newFieldsMap.remove(
				TicketInformationConstants.FIELD_DATABASE_FTP_FILENAME);
		}

		String newDataFolderUploadMethod = newFieldsMap.get(
			TicketInformationConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD);

		if (newDataFolderUploadMethod != null) {
			String oldDataFolderUploadMethod = GetterUtil.getString(
				oldFieldsMap.get(
					TicketInformationConstants.
						FIELD_DATA_FOLDER_UPLOAD_METHOD));

			String oldDataFolderFTPFileName = GetterUtil.getString(
				oldFieldsMap.get(
					TicketInformationConstants.FIELD_DATA_FOLDER_FTP_FILENAME));
			String newDataFolderFTPFileName = GetterUtil.getString(
				newFieldsMap.get(
					TicketInformationConstants.FIELD_DATA_FOLDER_FTP_FILENAME));

			if (!oldDataFolderUploadMethod.equals(newDataFolderUploadMethod) ||
				!oldDataFolderFTPFileName.equals(newDataFolderFTPFileName)) {

				int auditField = TicketInformationConstants.getAuditField(
					TicketInformationConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD);
				int visibility = TicketInformationConstants.getAuditVisibility(
					TicketInformationConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD);

				auditEntryLocalService.addAuditEntry(
					userId, userName, createDate, classNameId, classPK,
					auditSetId, classNameId, classPK, auditAction, auditField,
					visibility, oldDataFolderFTPFileName,
					oldDataFolderUploadMethod, newDataFolderFTPFileName,
					newDataFolderUploadMethod);
			}

			newFieldsMap.remove(
				TicketInformationConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD);
			newFieldsMap.remove(
				TicketInformationConstants.FIELD_DATA_FOLDER_FTP_FILENAME);
		}

		String newEnvBrowser = newFieldsMap.get(
			TicketInformationConstants.FIELD_ENV_BROWSER);

		if (newEnvBrowser != null) {
			String oldEnvBrowser = GetterUtil.getString(
				oldFieldsMap.get(TicketInformationConstants.FIELD_ENV_BROWSER));

			String oldEnvBrowserCustom = GetterUtil.getString(
				oldFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM));
			String newEnvBrowserCustom = GetterUtil.getString(
				newFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM));

			if (!oldEnvBrowser.equals(newEnvBrowser) ||
				!oldEnvBrowserCustom.equals(newEnvBrowserCustom)) {

				int auditField = TicketInformationConstants.getAuditField(
					TicketInformationConstants.FIELD_ENV_BROWSER);
				int visibility = TicketInformationConstants.getAuditVisibility(
					TicketInformationConstants.FIELD_ENV_BROWSER);

				auditEntryLocalService.addAuditEntry(
					userId, userName, createDate, classNameId, classPK,
					auditSetId, classNameId, classPK, auditAction, auditField,
					visibility, oldEnvBrowserCustom, oldEnvBrowser,
					newEnvBrowserCustom, newEnvBrowser);
			}

			newFieldsMap.remove(TicketInformationConstants.FIELD_ENV_BROWSER);
			newFieldsMap.remove(
				TicketInformationConstants.FIELD_ENV_BROWSER_CUSTOM);
		}

		String newEnvOS = newFieldsMap.get(
			TicketInformationConstants.FIELD_ENV_OS);

		if (newEnvOS != null) {
			String oldEnvOS = GetterUtil.getString(
				oldFieldsMap.get(TicketInformationConstants.FIELD_ENV_OS));

			String oldEnvOSCustom = GetterUtil.getString(
				oldFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_OS_CUSTOM));
			String newEnvOSCustom = GetterUtil.getString(
				newFieldsMap.get(
					TicketInformationConstants.FIELD_ENV_OS_CUSTOM));

			if (!oldEnvOS.equals(newEnvOS) ||
				!oldEnvOSCustom.equals(newEnvOSCustom)) {

				int auditField = TicketInformationConstants.getAuditField(
					TicketInformationConstants.FIELD_ENV_OS);
				int visibility = TicketInformationConstants.getAuditVisibility(
					TicketInformationConstants.FIELD_ENV_OS);

				auditEntryLocalService.addAuditEntry(
					userId, userName, createDate, classNameId, classPK,
					auditSetId, classNameId, classPK, auditAction, auditField,
					visibility, oldEnvOSCustom, oldEnvOS, newEnvOSCustom,
					newEnvOS);
			}

			newFieldsMap.remove(TicketInformationConstants.FIELD_ENV_OS);
			newFieldsMap.remove(TicketInformationConstants.FIELD_ENV_OS_CUSTOM);
		}

		return newFieldsMap;
	}

}