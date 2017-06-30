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

package com.liferay.osb.model;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brent Krone-Schmidt
 */
public class TicketInformationConstants {

	public static final long FIELD_ADDITIONAL_COMMENTS = 107;

	public static final long FIELD_CLUSTER_NUMBER_OF_NODES = 109;

	public static final long FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE = 110;

	public static final long FIELD_DATA_FOLDER_FTP_FILENAME = 117;

	public static final long FIELD_DATA_FOLDER_UPLOAD_METHOD = 116;

	public static final long FIELD_DATABASE_FTP_FILENAME = 114;

	public static final long FIELD_DATABASE_UPLOAD_METHOD = 115;

	public static final long FIELD_DOC_LIB_PERSISTENCE = 112;

	public static final long FIELD_ENV_AS = 2;

	public static final long FIELD_ENV_BROWSER = 3;

	public static final long FIELD_ENV_BROWSER_CUSTOM = 9;

	public static final long FIELD_ENV_CS = 7;

	public static final long FIELD_ENV_DB = 4;

	public static final long FIELD_ENV_JVM = 5;

	public static final long FIELD_ENV_LFR = 1;

	public static final long FIELD_ENV_NAME = 11;

	public static final long FIELD_ENV_OS = 6;

	public static final long FIELD_ENV_OS_CUSTOM = 10;

	public static final long FIELD_ENV_SEARCH = 8;

	public static final long FIELD_HOST_NAMES = 105;

	public static final long FIELD_IP_ADDRESSES = 104;

	public static final long FIELD_LICENSE_PURPOSE = 106;

	public static final long FIELD_LICENSE_TYPE = 102;

	public static final long FIELD_SERVER_CONFIGURATIONS = 108;

	public static final long FIELD_SERVER_IDS = 103;

	public static final long FIELD_STEPS_TO_UPGRADE = 113;

	public static final long FIELD_UPGRADE_TO_ENV_LFR = 111;

	public static final int getAuditField(long field) {
		if (field == FIELD_ADDITIONAL_COMMENTS) {
			return AuditEntryConstants.FIELD_ADDITIONAL_COMMENTS;
		}
		else if (field == FIELD_CLUSTER_NUMBER_OF_NODES) {
			return AuditEntryConstants.FIELD_CLUSTER_NUMBER_OF_NODES;
		}
		else if (field == FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE) {
			return AuditEntryConstants.FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE;
		}
		else if ((field == FIELD_DATA_FOLDER_FTP_FILENAME) ||
				 (field == FIELD_DATA_FOLDER_UPLOAD_METHOD)) {

			return AuditEntryConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD;
		}
		else if ((field == FIELD_DATABASE_FTP_FILENAME) ||
				 (field == FIELD_DATABASE_UPLOAD_METHOD)) {

			return AuditEntryConstants.FIELD_DATABASE_UPLOAD_METHOD;
		}
		else if (field == FIELD_DOC_LIB_PERSISTENCE) {
			return AuditEntryConstants.FIELD_DOC_LIB_PERSISTENCE;
		}
		else if (field == FIELD_ENV_AS) {
			return AuditEntryConstants.FIELD_ENV_AS;
		}
		else if (field == FIELD_ENV_BROWSER) {
			return AuditEntryConstants.FIELD_ENV_BROWSER;
		}
		else if (field == FIELD_ENV_CS) {
			return AuditEntryConstants.FIELD_ENV_CS;
		}
		else if (field == FIELD_ENV_DB) {
			return AuditEntryConstants.FIELD_ENV_DB;
		}
		else if (field == FIELD_ENV_JVM) {
			return AuditEntryConstants.FIELD_ENV_JVM;
		}
		else if (field == FIELD_ENV_LFR) {
			return AuditEntryConstants.FIELD_ENV_LFR;
		}
		else if (field == FIELD_ENV_NAME) {
			return AuditEntryConstants.FIELD_ENV_NAME;
		}
		else if (field == FIELD_ENV_OS) {
			return AuditEntryConstants.FIELD_ENV_OS;
		}
		else if (field == FIELD_ENV_SEARCH) {
			return AuditEntryConstants.FIELD_ENV_SEARCH;
		}
		else if (field == FIELD_HOST_NAMES) {
			return AuditEntryConstants.FIELD_HOST_NAMES;
		}
		else if (field == FIELD_IP_ADDRESSES) {
			return AuditEntryConstants.FIELD_IP_ADDRESSES;
		}
		else if (field == FIELD_LICENSE_PURPOSE) {
			return AuditEntryConstants.FIELD_LICENSE_PURPOSE;
		}
		else if (field == FIELD_LICENSE_TYPE) {
			return AuditEntryConstants.FIELD_LICENSE_TYPE;
		}
		else if (field == FIELD_SERVER_CONFIGURATIONS) {
			return AuditEntryConstants.FIELD_SERVER_CONFIGURATIONS;
		}
		else if (field == FIELD_SERVER_IDS) {
			return AuditEntryConstants.FIELD_SERVER_IDS;
		}
		else if (field == FIELD_STEPS_TO_UPGRADE) {
			return AuditEntryConstants.FIELD_STEPS_TO_UPGRADE;
		}
		else if (field == FIELD_UPGRADE_TO_ENV_LFR) {
			return AuditEntryConstants.FIELD_UPGRADE_TO_ENV_LFR;
		}

		return 0;
	}

	public static final String getAuditLabel(long fieldId, String data) {
		int value = GetterUtil.getInteger(data);

		if ((fieldId == FIELD_ENV_AS) || (fieldId == FIELD_ENV_CS) ||
			(fieldId == FIELD_ENV_DB) || (fieldId == FIELD_ENV_JVM) ||
			(fieldId == FIELD_ENV_LFR) ||
			(fieldId == FIELD_UPGRADE_TO_ENV_LFR)) {

			return TicketEntryConstants.getEnvLabel(value);
		}
		else if (fieldId == FIELD_ENV_SEARCH) {
			int[] values = StringUtil.split(data, StringPool.NEW_LINE, 0);

			List<String> labels = new ArrayList<String>();

			for (int searchValue : values) {
				labels.add(TicketEntryConstants.getEnvLabel(searchValue));
			}

			return StringUtil.merge(labels, StringPool.NEW_LINE);
		}
		else if (fieldId == FIELD_LICENSE_TYPE) {
			return TicketEntryConstants.getLicenseTypeLabel(value);
		}
		else if (fieldId == FIELD_LICENSE_PURPOSE) {
			return TicketEntryConstants.getLicensePurposeLabel(value);
		}
		else if (fieldId == FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE) {
			return TicketEntryConstants.getClusterServerCommunicationTypeLabel(
				value);
		}
		else if ((fieldId == FIELD_DATABASE_UPLOAD_METHOD) ||
				 (fieldId == FIELD_DATA_FOLDER_UPLOAD_METHOD)) {

			return TicketEntryConstants.getUploadMethodLabel(value);
		}
		else if (fieldId == FIELD_DOC_LIB_PERSISTENCE) {
			return TicketEntryConstants.getDocLibPersistenceLabel(value);
		}

		return StringPool.BLANK;
	}

	public static final int getAuditVisibility(long fieldId) {
		return VisibilityConstants.PUBLIC;
	}

	public static final int getMaxLength(long field) {
		if ((field == FIELD_ENV_BROWSER_CUSTOM) ||
			(field == FIELD_ENV_OS_CUSTOM)) {

			return 150;
		}
		else {
			return 0;
		}
	}

}