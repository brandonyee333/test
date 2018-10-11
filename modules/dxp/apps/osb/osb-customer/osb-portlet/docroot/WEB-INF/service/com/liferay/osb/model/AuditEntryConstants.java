/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

	public static final int ACTION_RENEW = 7;

	public static final int ACTION_UNASSIGN = 5;

	public static final int ACTION_UPDATE = 6;

	public static final int FIELD_ADDRESS = 34069;

	public static final int FIELD_CODE = 34061;

	public static final int FIELD_CORP_ENTRY_NAME = 34059;

	public static final int FIELD_CORP_PROJECT = 34058;

	public static final int FIELD_DOSSIERA_ACCOUNT_KEY = 34078;

	public static final int FIELD_ENV_AS = 34001;

	public static final int FIELD_ENV_BROWSER = 34029;

	public static final int FIELD_ENV_CS = 34056;

	public static final int FIELD_ENV_DB = 34004;

	public static final int FIELD_ENV_JVM = 34010;

	public static final int FIELD_ENV_LFR = 34011;

	public static final int FIELD_ENV_NAME = 34028;

	public static final int FIELD_ENV_OS = 34012;

	public static final int FIELD_ENV_SEARCH = 34057;

	public static final int FIELD_FILE = 34009;

	public static final int FIELD_INDUSTRY = 34063;

	public static final int FIELD_INSTRUCTIONS = 34065;

	public static final int FIELD_LANGUAGES = 34070;

	public static final int FIELD_NAME = 34060;

	public static final int FIELD_NOT_APPLICABLE = 34072;

	public static final int FIELD_NOTES = 34066;

	public static final int FIELD_OFFERINGS = 34073;

	public static final int FIELD_PARTNER = 34055;

	public static final int FIELD_PARTNER_MANAGED_SUPPORT = 34064;

	public static final int FIELD_PRIMARY = 34035;

	public static final int FIELD_RENEW_COUNT = 34025;

	public static final int FIELD_RENEWAL_CONTACT_USER = 34067;

	public static final int FIELD_REPLICATE = 34037;

	public static final int FIELD_ROLE = 34015;

	public static final int FIELD_STATUS = 34017;

	public static final int FIELD_STATUS_MESSAGE = 34068;

	public static final int FIELD_SUPPORT_REGION = 34051;

	public static final int FIELD_SUPPORT_REGIONS = 34071;

	public static final int FIELD_TIER = 34054;

	public static final int FIELD_TYPE = 34062;

	public static final int FIELD_URL = 34020;

	public static final int FIELD_USER = 34021;

	public static final int[] FIELDS_I18N = {
		FIELD_ENV_AS, FIELD_ENV_DB, FIELD_ENV_JVM, FIELD_INDUSTRY,
		FIELD_PARTNER_MANAGED_SUPPORT, FIELD_ROLE, FIELD_STATUS, FIELD_TIER,
		FIELD_TYPE
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