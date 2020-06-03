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

package com.liferay.osb.customer.license.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountEntryDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_ENVIRONMENT_ENV_AS_IDS =
		"accountEnvironmentEnvASIds";

	public static final String ACCOUNT_ENVIRONMENT_ENV_DB_IDS =
		"accountEnvironmentEnvDBIds";

	public static final String ACCOUNT_ENVIRONMENT_ENV_JVM_IDS =
		"accountEnvironmentEnvJVMIds";

	public static final String ACCOUNT_ENVIRONMENT_ENV_LFR_IDS =
		"accountEnvironmentEnvLFRIds";

	public static final String ACCOUNT_ENVIRONMENT_ENV_OS_IDS =
		"accountEnvironmentEnvOSIds";

	public static final String CODE = "code";

	public static final String DOSSIERA_ACCOUNT_KEY = "dossieraAccountKey";

	public static final String INSTRUCTIONS = "instructions";

	public static final String KORONEIKI_ACCOUNT_KEY = "koroneikiAccountKey";

	public static final String NAME = "name";

	public static final String PRODUCT_ENTRY_IDS = "productEntryIds";

	public static final String STATUSES = "statuses";

	public static final String SUPPORT_REGION_IDS = "supportRegionIds";

	public AccountEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		accountEnvironmentEnvASIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_ENVIRONMENT_ENV_AS_IDS);
		accountEnvironmentEnvDBIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_ENVIRONMENT_ENV_DB_IDS);
		accountEnvironmentEnvJVMIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_ENVIRONMENT_ENV_JVM_IDS);
		accountEnvironmentEnvLFRIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_ENVIRONMENT_ENV_LFR_IDS);
		accountEnvironmentEnvOSIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_ENVIRONMENT_ENV_OS_IDS);
		code = ParamUtil.getString(portletRequest, CODE);
		dossieraAccountKey = ParamUtil.getString(
			portletRequest, DOSSIERA_ACCOUNT_KEY);
		koroneikiAccountKey = ParamUtil.getString(
			portletRequest, KORONEIKI_ACCOUNT_KEY);
		instructions = ParamUtil.getString(portletRequest, INSTRUCTIONS);
		name = ParamUtil.getString(portletRequest, NAME);
		productEntryIds = ParamUtil.getLongValues(
			portletRequest, PRODUCT_ENTRY_IDS);
		statuses = ParamUtil.getIntegerValues(portletRequest, STATUSES);
		supportRegionIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_REGION_IDS);
	}

	public long[] getAccountEnvironmentEnvASIds() {
		if (ArrayUtil.contains(accountEnvironmentEnvASIds, 0)) {
			return new long[0];
		}

		return accountEnvironmentEnvASIds;
	}

	public long[] getAccountEnvironmentEnvDBIds() {
		if (ArrayUtil.contains(accountEnvironmentEnvDBIds, 0)) {
			return new long[0];
		}

		return accountEnvironmentEnvDBIds;
	}

	public long[] getAccountEnvironmentEnvJVMIds() {
		if (ArrayUtil.contains(accountEnvironmentEnvJVMIds, 0)) {
			return new long[0];
		}

		return accountEnvironmentEnvJVMIds;
	}

	public long[] getAccountEnvironmentEnvLFRIds() {
		if (ArrayUtil.contains(accountEnvironmentEnvLFRIds, 0)) {
			return new long[0];
		}

		return accountEnvironmentEnvLFRIds;
	}

	public long[] getAccountEnvironmentEnvOSIds() {
		if (ArrayUtil.contains(accountEnvironmentEnvOSIds, 0)) {
			return new long[0];
		}

		return accountEnvironmentEnvOSIds;
	}

	public String getCode() {
		return code;
	}

	public String getDossieraAccountKey() {
		return dossieraAccountKey;
	}

	public String getInstructions() {
		return instructions;
	}

	public String getKoroneikiAccountKey() {
		return koroneikiAccountKey;
	}

	public String getName() {
		return name;
	}

	public long[] getProductEntryIds() {
		if (ArrayUtil.contains(productEntryIds, 0)) {
			return new long[0];
		}

		return productEntryIds;
	}

	public int[] getStatuses() {
		if (ArrayUtil.contains(statuses, -1)) {
			return new int[0];
		}

		return statuses;
	}

	public long[] getSupportRegionIds() {
		if (ArrayUtil.contains(supportRegionIds, 0)) {
			return new long[0];
		}

		return supportRegionIds;
	}

	@Override
	public boolean isAdvancedSearch() {
		if (super.isAdvancedSearch() || !isSearch()) {
			return true;
		}

		return false;
	}

	protected long[] accountEnvironmentEnvASIds;
	protected long[] accountEnvironmentEnvDBIds;
	protected long[] accountEnvironmentEnvJVMIds;
	protected long[] accountEnvironmentEnvLFRIds;
	protected long[] accountEnvironmentEnvOSIds;
	protected String code;
	protected String dossieraAccountKey;
	protected String instructions;
	protected String koroneikiAccountKey;
	protected String name;
	protected long[] productEntryIds;
	protected int[] statuses;
	protected long[] supportRegionIds;

}