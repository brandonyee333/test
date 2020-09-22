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

import com.liferay.osb.util.WorkflowConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Locale;

/**
 * @author Kyle Bischof
 */
public class AccountEntryConstants {

	public static final int DEFAULT_REDIRECT_ACCOUNT_ENTRYID = 0;

	public static final int INDUSTRY_OTHER = 35015;

	public static final String LANGUAGE_ID_CHINESE = "zh_CN";

	public static final String LANGUAGE_ID_ENGLISH = "en_US";

	public static final String LANGUAGE_ID_JAPANESE = "ja_JP";

	public static final String LANGUAGE_ID_PORTUGUESE = "pt_BR";

	public static final String LANGUAGE_ID_SPANISH = "es_ES";

	public static final String[] LANGUAGES = {
		LANGUAGE_ID_CHINESE, LANGUAGE_ID_ENGLISH, LANGUAGE_ID_JAPANESE,
		LANGUAGE_ID_PORTUGUESE, LANGUAGE_ID_SPANISH
	};

	public static final String LIST_TYPE_INDUSTRY =
		AccountEntry.class.getName() + ".industry";

	public static final int[] STATUSES = {
		WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_CLOSED,
		WorkflowConstants.STATUS_EXPIRED, WorkflowConstants.STATUS_PENDING,
		WorkflowConstants.STATUS_REJECTED
	};

	public static final int[] STATUSES_ACTIVE = {
		WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_CLOSED,
		WorkflowConstants.STATUS_EXPIRED
	};

	public static final int TIER_T1 = 3;

	public static final int TIER_T2 = 4;

	public static final int TIER_T3 = 5;

	public static final int TIER_T4 = 1;

	public static final int[] TIERS = {TIER_T1, TIER_T2, TIER_T3, TIER_T4};

	public static final int TYPE_ANALYTICS_CLOUD_BASIC = 5;

	public static final int TYPE_GROUP = 1;

	public static final int TYPE_INDIVIDUAL = 2;

	public static final int TYPE_INTERNAL_TEST = 3;

	public static final int TYPE_TRIAL = 4;

	public static final int[] TYPES = {
		TYPE_ANALYTICS_CLOUD_BASIC, TYPE_GROUP, TYPE_INDIVIDUAL,
		TYPE_INTERNAL_TEST, TYPE_TRIAL
	};

	public static String getIndustryLabel(int industry) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(industry);

			return listType.getName();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public static String getLanguageLabel(String languageId) {
		if (Validator.isNotNull(languageId)) {
			Locale locale = LocaleUtil.fromLanguageId(languageId);

			return locale.getDisplayLanguage();
		}

		return StringPool.BLANK;
	}

	public static String getTierLabel(int tier) {
		if (tier == TIER_T1) {
			return "T1";
		}
		else if (tier == TIER_T2) {
			return "T2";
		}
		else if (tier == TIER_T3) {
			return "T3";
		}
		else if (tier == TIER_T4) {
			return "T4";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String getTypeLabel(int type) {
		if (type == TYPE_ANALYTICS_CLOUD_BASIC) {
			return "analytics-cloud-basic";
		}
		else if (type == TYPE_GROUP) {
			return "group";
		}
		else if (type == TYPE_INDIVIDUAL) {
			return "individual";
		}
		else if (type == TYPE_INTERNAL_TEST) {
			return "internal-test";
		}
		else if (type == TYPE_TRIAL) {
			return "trial";
		}
		else {
			return null;
		}
	}

}