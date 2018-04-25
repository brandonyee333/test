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

import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Kyle Bischof
 */
public class AuditEntryConstants {

	public static final int ACTION_ADD = 1;

	public static final int ACTION_ASSIGN = 2;

	public static final int ACTION_AUDIT = 11;

	public static final int ACTION_DELETE = 3;

	public static final int ACTION_ESCALATE = 4;

	public static final int ACTION_FORWARD = 10;

	public static final int ACTION_RENEW = 7;

	public static final int ACTION_SOLUTION_PROPOSED = 8;

	public static final int ACTION_SOLUTION_REJECTED = 9;

	public static final int ACTION_UNASSIGN = 5;

	public static final int ACTION_UPDATE = 6;

	public static final int FIELD_ADDITIONAL_COMMENTS = 34038;

	public static final int FIELD_ADDRESS = 34069;

	public static final int FIELD_BODY = 34002;

	public static final int FIELD_CLUSTER_NUMBER_OF_NODES = 34046;

	public static final int FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE = 34047;

	public static final int FIELD_CODE = 34061;

	public static final int FIELD_COMPONENT = 34003;

	public static final int FIELD_CORP_ENTRY_NAME = 34059;

	public static final int FIELD_CORP_PROJECT = 34058;

	public static final int FIELD_DATA_FOLDER_UPLOAD_METHOD = 34039;

	public static final int FIELD_DATABASE_UPLOAD_METHOD = 34040;

	public static final int FIELD_DELETE_DATE = 34036;

	public static final int FIELD_DESCRIPTION = 34005;

	public static final int FIELD_DOC_LIB_PERSISTENCE = 34041;

	public static final int FIELD_DUE_DATE = 34006;

	public static final int FIELD_ENV_AS = 34001;

	public static final int FIELD_ENV_BROWSER = 34029;

	public static final int FIELD_ENV_CS = 34056;

	public static final int FIELD_ENV_DB = 34004;

	public static final int FIELD_ENV_JVM = 34010;

	public static final int FIELD_ENV_LFR = 34011;

	public static final int FIELD_ENV_NAME = 34028;

	public static final int FIELD_ENV_OS = 34012;

	public static final int FIELD_ENV_SEARCH = 34057;

	public static final int FIELD_ESCALATION_LEVEL = 34007;

	public static final int FIELD_FILE = 34009;

	public static final int FIELD_HOST_NAMES = 34042;

	public static final int FIELD_IGNORE_DUE_DATE = 34008;

	public static final int FIELD_INDUSTRY = 34063;

	public static final int FIELD_INSTRUCTIONS = 34065;

	public static final int FIELD_IP_ADDRESSES = 34043;

	public static final int FIELD_LANGUAGES = 34070;

	public static final int FIELD_LICENSE_PURPOSE = 34044;

	public static final int FIELD_LICENSE_TYPE = 34045;

	public static final int FIELD_NAME = 34060;

	public static final int FIELD_NOT_APPLICABLE = 34072;

	public static final int FIELD_NOTES = 34066;

	public static final int FIELD_OFFERINGS = 34073;

	public static final int FIELD_PARTNER = 34055;

	public static final int FIELD_PARTNER_MANAGED_SUPPORT = 34064;

	public static final int FIELD_PENDING_CUSTOMER = 34031;

	public static final int FIELD_PENDING_LIFERAY = 34032;

	public static final int FIELD_PENDING_PARTNER = 34033;

	public static final int FIELD_PRIMARY = 34035;

	public static final int FIELD_RENEW_COUNT = 34025;

	public static final int FIELD_RENEWAL_CONTACT_USER = 34067;

	public static final int FIELD_REPLICATE = 34037;

	public static final int FIELD_REPORTED_BY = 34013;

	public static final int FIELD_REPRODUCTION_STEPS = 34024;

	public static final int FIELD_RESOLUTION = 34014;

	public static final int FIELD_ROLE = 34015;

	public static final int FIELD_SERVER_CONFIGURATIONS = 34048;

	public static final int FIELD_SERVER_IDS = 34049;

	public static final int FIELD_SEVERITY = 34016;

	public static final int FIELD_SEVERITY_REASON = 34026;

	public static final int FIELD_SEVERITY_REASON_COMMENTS = 34027;

	public static final int FIELD_STATUS = 34017;

	public static final int FIELD_STATUS_MESSAGE = 34068;

	public static final int FIELD_STEPS_TO_UPGRADE = 34052;

	public static final int FIELD_SUBCOMPONENT = 34050;

	public static final int FIELD_SUBJECT = 34018;

	public static final int FIELD_SUPPORT = 34019;

	public static final int FIELD_SUPPORT_REGION = 34051;

	public static final int FIELD_SUPPORT_REGIONS = 34071;

	public static final int FIELD_SUPPORT_TEAM = 34034;

	public static final int FIELD_SYSTEM_STATUS = 34023;

	public static final int FIELD_TICKET_CALL = 34030;

	public static final int FIELD_TIER = 34054;

	public static final int FIELD_TYPE = 34062;

	public static final int FIELD_UPGRADE_TO_ENV_LFR = 34053;

	public static final int FIELD_URL = 34020;

	public static final int FIELD_USER = 34021;

	public static final int FIELD_WEIGHT = 34022;

	public static final int[] FIELDS_I18N = {
		FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE, FIELD_COMPONENT,
		FIELD_DOC_LIB_PERSISTENCE, FIELD_ENV_AS, FIELD_ENV_DB, FIELD_ENV_JVM,
		FIELD_ESCALATION_LEVEL, FIELD_IGNORE_DUE_DATE, FIELD_INDUSTRY,
		FIELD_LICENSE_PURPOSE, FIELD_LICENSE_TYPE,
		FIELD_PARTNER_MANAGED_SUPPORT, FIELD_PENDING_CUSTOMER,
		FIELD_PENDING_LIFERAY, FIELD_PENDING_PARTNER, FIELD_RESOLUTION,
		FIELD_ROLE, FIELD_SEVERITY, FIELD_STATUS, FIELD_SYSTEM_STATUS,
		FIELD_TIER, FIELD_TYPE, FIELD_WEIGHT
	};

	public static final String LIST_TYPE_FIELD =
		AuditEntry.class.getName() + ".field";

	public static final String NOT_AVAILABLE = "N/A";

	public static String getActionLabel(int action) {
		if (action == ACTION_ADD) {
			return "added";
		}
		else if (action == ACTION_ASSIGN) {
			return "assigned";
		}
		else if (action == ACTION_AUDIT) {
			return "audited";
		}
		else if (action == ACTION_DELETE) {
			return "deleted";
		}
		else if (action == ACTION_ESCALATE) {
			return "escalated";
		}
		else if (action == ACTION_FORWARD) {
			return "forwarded";
		}
		else if (action == ACTION_SOLUTION_PROPOSED) {
			return "solution-proposed";
		}
		else if (action == ACTION_SOLUTION_REJECTED) {
			return "solution-rejected";
		}
		else if (action == ACTION_UNASSIGN) {
			return "unassigned";
		}
		else if (action == ACTION_UPDATE) {
			return "updated";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getFieldClassNameIdLabel(long fieldClassNameId) {
		return getFieldClassNameLabel(
			PortalUtil.getClassName(fieldClassNameId));
	}

	public static String getFieldClassNameLabel(String fieldClassName) {
		if (Validator.isNull(fieldClassName)) {
			return NOT_AVAILABLE;
		}
		else if (fieldClassName.equals(AccountEntry.class.getName())) {
			return "project";
		}
		else if (fieldClassName.equals(OfferingEntry.class.getName())) {
			return "offering";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getFieldLabel(int field) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(field);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

}