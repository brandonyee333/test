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

package com.liferay.osb.license.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class LicenseKeyDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_ENTRY_NAME = "accountEntryName";

	public static final String ACTIVE = "active";

	public static final String CREATE_DATE_GT_DAY = "createDateGTDay";

	public static final String CREATE_DATE_GT_MONTH = "createDateGTMonth";

	public static final String CREATE_DATE_GT_YEAR = "createDateGTYear";

	public static final String CREATE_DATE_LT_DAY = "createDateLTDay";

	public static final String CREATE_DATE_LT_MONTH = "createDateLTMonth";

	public static final String CREATE_DATE_LT_YEAR = "createDateLTYear";

	public static final String CREATE_USER_ID = "createUserId";

	public static final String CREATE_USER_NAME = "createUserName";

	public static final String DESCRIPTION = "description";

	public static final String EXPIRATION_DATE_GT_DAY = "expirationDateGTDay";

	public static final String EXPIRATION_DATE_GT_MONTH =
		"expirationDateGTMonth";

	public static final String EXPIRATION_DATE_GT_YEAR = "expirationDateGTYear";

	public static final String EXPIRATION_DATE_LT_DAY = "expirationDateLTDay";

	public static final String EXPIRATION_DATE_LT_MONTH =
		"expirationDateLTMonth";

	public static final String EXPIRATION_DATE_LT_YEAR = "expirationDateLTYear";

	public static final String HOST_NAME = "hostName";

	public static final String IP_ADDRESS = "ipAddress";

	public static final String KEY = "key";

	public static final String LICENSE_ENTRY_IDS = "licenseEntryIds";

	public static final String LICENSE_KEY_SET_NAME = "licenseKeySetName";

	public static final String MAC_ADDRESS = "macAddress";

	public static final String MODIFIED_DATE_GT_DAY = "modifiedDateGTDay";

	public static final String MODIFIED_DATE_GT_MONTH = "modifiedDateGTMonth";

	public static final String MODIFIED_DATE_GT_YEAR = "modifiedDateGTYear";

	public static final String MODIFIED_DATE_LT_DAY = "modifiedDateLTDay";

	public static final String MODIFIED_DATE_LT_MONTH = "modifiedDateLTMonth";

	public static final String MODIFIED_DATE_LT_YEAR = "modifiedDateLTYear";

	public static final String MODIFIED_USER_ID = "modifiedUserId";

	public static final String MODIFIED_USER_NAME = "modifiedUserName";

	public static final String OWNER = "owner";

	public static final String PRODUCT_ENTRY_IDS = "productEntryIds";

	public static final String PRODUCT_ENTRY_NAME = "productEntryName";

	public static final String PRODUCT_VERSIONS = "productVersions";

	public static final String SERVER_ID = "serverId";

	public static final String START_DATE_GT_DAY = "startDateGTDay";

	public static final String START_DATE_GT_MONTH = "startDateGTMonth";

	public static final String START_DATE_GT_YEAR = "startDateGTYear";

	public static final String START_DATE_LT_DAY = "startDateLTDay";

	public static final String START_DATE_LT_MONTH = "startDateLTMonth";

	public static final String START_DATE_LT_YEAR = "startDateLTYear";

	public LicenseKeyDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		accountEntryName = ParamUtil.getString(
			portletRequest, ACCOUNT_ENTRY_NAME);

		String activeString = ParamUtil.getString(portletRequest, ACTIVE);

		if (Validator.isNotNull(activeString)) {
			active = GetterUtil.getBoolean(activeString);
		}

		createDateGTDay = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_DAY);
		createDateGTMonth = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_MONTH, -1);
		createDateGTYear = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_YEAR);
		createDateLTDay = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_DAY);
		createDateLTMonth = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_MONTH, -1);
		createDateLTYear = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_YEAR);
		createUserId = ParamUtil.getLong(portletRequest, CREATE_USER_ID);
		createUserName = ParamUtil.getString(portletRequest, CREATE_USER_NAME);
		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		expirationDateGTDay = ParamUtil.getInteger(
			portletRequest, EXPIRATION_DATE_GT_DAY);
		expirationDateGTMonth = ParamUtil.getInteger(
			portletRequest, EXPIRATION_DATE_GT_MONTH, -1);
		expirationDateGTYear = ParamUtil.getInteger(
			portletRequest, EXPIRATION_DATE_GT_YEAR);
		expirationDateLTDay = ParamUtil.getInteger(
			portletRequest, EXPIRATION_DATE_LT_DAY);
		expirationDateLTMonth = ParamUtil.getInteger(
			portletRequest, EXPIRATION_DATE_LT_MONTH, -1);
		expirationDateLTYear = ParamUtil.getInteger(
			portletRequest, EXPIRATION_DATE_LT_YEAR);
		hostName = ParamUtil.getString(portletRequest, HOST_NAME);
		ipAddress = ParamUtil.getString(portletRequest, IP_ADDRESS);
		key = ParamUtil.getString(portletRequest, KEY);
		licenseEntryIds = ParamUtil.getLongValues(
			portletRequest, LICENSE_ENTRY_IDS);
		licenseKeySetName = ParamUtil.getString(
			portletRequest, LICENSE_KEY_SET_NAME);
		macAddress = ParamUtil.getString(portletRequest, MAC_ADDRESS);
		modifiedDateGTDay = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_GT_DAY);
		modifiedDateGTMonth = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_GT_MONTH, -1);
		modifiedDateGTYear = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_GT_YEAR);
		modifiedDateLTDay = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_LT_DAY);
		modifiedDateLTMonth = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_LT_MONTH, -1);
		modifiedDateLTYear = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_LT_YEAR);
		modifiedUserId = ParamUtil.getLong(portletRequest, MODIFIED_USER_ID);
		modifiedUserName = ParamUtil.getString(
			portletRequest, MODIFIED_USER_NAME);
		owner = ParamUtil.getString(portletRequest, OWNER);
		productEntryIds = ParamUtil.getLongValues(
			portletRequest, PRODUCT_ENTRY_IDS);
		productEntryName = ParamUtil.getString(
			portletRequest, PRODUCT_ENTRY_NAME);
		productVersions = ParamUtil.getIntegerValues(
			portletRequest, PRODUCT_VERSIONS);
		serverId = ParamUtil.getString(portletRequest, SERVER_ID);
		startDateGTDay = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_DAY);
		startDateGTMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_MONTH, -1);
		startDateGTYear = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_YEAR);
		startDateLTDay = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_DAY);
		startDateLTMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_MONTH, -1);
		startDateLTYear = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_YEAR);
	}

	public String getAccountEntryName() {
		return accountEntryName;
	}

	public Boolean getActive() {
		return active;
	}

	public int getCreateDateGTDay() {
		return createDateGTDay;
	}

	public int getCreateDateGTMonth() {
		return createDateGTMonth;
	}

	public int getCreateDateGTYear() {
		return createDateGTYear;
	}

	public int getCreateDateLTDay() {
		return createDateLTDay;
	}

	public int getCreateDateLTMonth() {
		return createDateLTMonth;
	}

	public int getCreateDateLTYear() {
		return createDateLTYear;
	}

	public long getCreateUserId() {
		return createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public String getDescription() {
		return description;
	}

	public int getExpirationDateGTDay() {
		return expirationDateGTDay;
	}

	public int getExpirationDateGTMonth() {
		return expirationDateGTMonth;
	}

	public int getExpirationDateGTYear() {
		return expirationDateGTYear;
	}

	public int getExpirationDateLTDay() {
		return expirationDateLTDay;
	}

	public int getExpirationDateLTMonth() {
		return expirationDateLTMonth;
	}

	public int getExpirationDateLTYear() {
		return expirationDateLTYear;
	}

	public String getHostName() {
		return hostName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getKey() {
		return key;
	}

	public long[] getLicenseEntryIds() {
		if (ArrayUtil.contains(licenseEntryIds, 0)) {
			return new long[0];
		}

		return licenseEntryIds;
	}

	public String getLicenseKeySetName() {
		return licenseKeySetName;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public int getModifiedDateGTDay() {
		return modifiedDateGTDay;
	}

	public int getModifiedDateGTMonth() {
		return modifiedDateGTMonth;
	}

	public int getModifiedDateGTYear() {
		return modifiedDateGTYear;
	}

	public int getModifiedDateLTDay() {
		return modifiedDateLTDay;
	}

	public int getModifiedDateLTMonth() {
		return modifiedDateLTMonth;
	}

	public int getModifiedDateLTYear() {
		return modifiedDateLTYear;
	}

	public long getModifiedUserId() {
		return modifiedUserId;
	}

	public String getModifiedUserName() {
		return modifiedUserName;
	}

	public String getOwner() {
		return owner;
	}

	public long[] getProductEntryIds() {
		if (ArrayUtil.contains(productEntryIds, 0)) {
			return new long[0];
		}

		return productEntryIds;
	}

	public String getProductEntryName() {
		return productEntryName;
	}

	public int[] getProductVersions() {
		if (ArrayUtil.contains(productVersions, 0)) {
			return new int[0];
		}

		return productVersions;
	}

	public String getServerId() {
		return serverId;
	}

	public int getStartDateGTDay() {
		return startDateGTDay;
	}

	public int getStartDateGTMonth() {
		return startDateGTMonth;
	}

	public int getStartDateGTYear() {
		return startDateGTYear;
	}

	public int getStartDateLTDay() {
		return startDateLTDay;
	}

	public int getStartDateLTMonth() {
		return startDateLTMonth;
	}

	public int getStartDateLTYear() {
		return startDateLTYear;
	}

	public boolean hasActive() {
		if (active == null) {
			return false;
		}

		return true;
	}

	public boolean isActive() {
		if (active == null) {
			return false;
		}

		return active.booleanValue();
	}

	@Override
	public boolean isAdvancedSearch() {
		if (super.isAdvancedSearch() || !isSearch()) {
			return true;
		}

		return false;
	}

	protected String accountEntryName;
	protected Boolean active;
	protected int createDateGTDay;
	protected int createDateGTMonth;
	protected int createDateGTYear;
	protected int createDateLTDay;
	protected int createDateLTMonth;
	protected int createDateLTYear;
	protected long createUserId;
	protected String createUserName;
	protected String description;
	protected int expirationDateGTDay;
	protected int expirationDateGTMonth;
	protected int expirationDateGTYear;
	protected int expirationDateLTDay;
	protected int expirationDateLTMonth;
	protected int expirationDateLTYear;
	protected String hostName;
	protected String ipAddress;
	protected String key;
	protected long[] licenseEntryIds;
	protected String licenseKeySetName;
	protected String macAddress;
	protected int modifiedDateGTDay;
	protected int modifiedDateGTMonth;
	protected int modifiedDateGTYear;
	protected int modifiedDateLTDay;
	protected int modifiedDateLTMonth;
	protected int modifiedDateLTYear;
	protected long modifiedUserId;
	protected String modifiedUserName;
	protected String owner;
	protected long[] productEntryIds;
	protected String productEntryName;
	protected int[] productVersions;
	protected String serverId;
	protected int startDateGTDay;
	protected int startDateGTMonth;
	protected int startDateGTYear;
	protected int startDateLTDay;
	protected int startDateLTMonth;
	protected int startDateLTYear;

}