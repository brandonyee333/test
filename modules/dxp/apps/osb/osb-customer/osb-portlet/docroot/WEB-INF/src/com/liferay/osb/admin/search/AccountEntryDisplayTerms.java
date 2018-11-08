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

package com.liferay.osb.admin.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

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

	public static final String ACCOUNT_WORKER_NAME = "accountWorkerName";

	public static final String ACCOUNT_WORKER_ROLE = "accountWorkerRole";

	public static final String ACCOUNT_WORKER_USER_ID = "accountWorkerUserId";

	public static final String CITY = "city";

	public static final String CODE = "code";

	public static final String CORP_ENTRY_NAME = "corpEntryName";

	public static final String COUNTRY_ID = "countryId";

	public static final String CREATE_DATE_GT_DAY = "createDateGTDay";

	public static final String CREATE_DATE_GT_MONTH = "createDateGTMonth";

	public static final String CREATE_DATE_GT_YEAR = "createDateGTYear";

	public static final String CREATE_DATE_LT_DAY = "createDateLTDay";

	public static final String CREATE_DATE_LT_MONTH = "createDateLTMonth";

	public static final String CREATE_DATE_LT_YEAR = "createDateLTYear";

	public static final String CREATE_USER_ID = "createUserId";

	public static final String CREATE_USER_NAME = "createUserName";

	public static final String DOSSIERA_ACCOUNT_KEY = "dossieraAccountKey";

	public static final String EXPIRED_SUPPORT = "expiredSupport";

	public static final String INDUSTRIES = "industries";

	public static final String INSTRUCTIONS = "instructions";

	public static final String MODIFIED_DATE_GT_DAY = "modifiedDateGTDay";

	public static final String MODIFIED_DATE_GT_MONTH = "modifiedDateGTMonth";

	public static final String MODIFIED_DATE_GT_YEAR = "modifiedDateGTYear";

	public static final String MODIFIED_DATE_LT_DAY = "modifiedDateLTDay";

	public static final String MODIFIED_DATE_LT_MONTH = "modifiedDateLTMonth";

	public static final String MODIFIED_DATE_LT_YEAR = "modifiedDateLTYear";

	public static final String MODIFIED_USER_ID = "modifiedUserId";

	public static final String MODIFIED_USER_NAME = "modifiedUserName";

	public static final String NAME = "name";

	public static final String NOTES = "notes";

	public static final String PARTNER_ENTRY_CODE = "partnerEntryCode";

	public static final String PARTNER_MANAGED_SUPPORT =
		"partnerManagedSupport";

	public static final String PRODUCT_ENTRY_IDS = "productEntryIds";

	public static final String REGION_ID = "regionId";

	public static final String STATUSES = "statuses";

	public static final String STREET = "street";

	public static final String SUPPORT = "support";

	public static final String SUPPORT_REGION_IDS = "supportRegionIds";

	public static final String SUPPORT_RESPONSE_IDS = "supportResponseIds";

	public static final String TICKET_SUPPORT = "ticketSupport";

	public static final String TIERS = "tiers";

	public static final String TYPES = "types";

	public static final String ZIP = "zip";

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
		accountWorkerName = ParamUtil.getString(
			portletRequest, ACCOUNT_WORKER_NAME);
		accountWorkerRole = ParamUtil.getLong(
			portletRequest, ACCOUNT_WORKER_ROLE);
		accountWorkerUserId = ParamUtil.getLong(
			portletRequest, ACCOUNT_WORKER_USER_ID);
		city = ParamUtil.getString(portletRequest, CITY);
		code = ParamUtil.getString(portletRequest, CODE);
		countryId = ParamUtil.getLong(portletRequest, COUNTRY_ID);
		corpEntryName = ParamUtil.getString(portletRequest, CORP_ENTRY_NAME);
		createDateGTDay = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_DAY);
		createDateGTMonth = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_MONTH);
		createDateGTYear = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_YEAR);
		createDateLTDay = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_DAY);
		createDateLTMonth = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_MONTH);
		createDateLTYear = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_YEAR);
		createUserId = ParamUtil.getLong(portletRequest, CREATE_USER_ID);
		createUserName = ParamUtil.getString(portletRequest, CREATE_USER_NAME);
		dossieraAccountKey = ParamUtil.getString(
			portletRequest, DOSSIERA_ACCOUNT_KEY);

		String expiredSupportString = ParamUtil.getString(
			portletRequest, EXPIRED_SUPPORT);

		if (Validator.isNotNull(expiredSupportString)) {
			expiredSupport = GetterUtil.getBoolean(expiredSupportString);
		}

		industries = ParamUtil.getIntegerValues(portletRequest, INDUSTRIES);
		instructions = ParamUtil.getString(portletRequest, INSTRUCTIONS);
		modifiedDateGTDay = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_GT_DAY);
		modifiedDateGTMonth = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_GT_MONTH);
		modifiedDateGTYear = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_GT_YEAR);
		modifiedDateLTDay = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_LT_DAY);
		modifiedDateLTMonth = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_LT_MONTH);
		modifiedDateLTYear = ParamUtil.getInteger(
			portletRequest, MODIFIED_DATE_LT_YEAR);
		modifiedUserId = ParamUtil.getLong(portletRequest, MODIFIED_USER_ID);
		modifiedUserName = ParamUtil.getString(
			portletRequest, MODIFIED_USER_NAME);
		name = ParamUtil.getString(portletRequest, NAME);
		notes = ParamUtil.getString(portletRequest, NOTES);
		partnerEntryCode = ParamUtil.getString(
			portletRequest, PARTNER_ENTRY_CODE);
		productEntryIds = ParamUtil.getLongValues(
			portletRequest, PRODUCT_ENTRY_IDS);

		String partnerManagedSupportString = ParamUtil.getString(
			portletRequest, PARTNER_MANAGED_SUPPORT);

		if (Validator.isNotNull(partnerManagedSupportString)) {
			partnerManagedSupport = GetterUtil.getBoolean(
				partnerManagedSupportString);
		}

		regionId = ParamUtil.getLong(portletRequest, REGION_ID);
		statuses = ParamUtil.getIntegerValues(portletRequest, STATUSES);
		street = ParamUtil.getString(portletRequest, STREET);
		support = ParamUtil.getInteger(portletRequest, SUPPORT);
		supportRegionIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_REGION_IDS);
		supportResponseIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_RESPONSE_IDS);
		tiers = ParamUtil.getIntegerValues(portletRequest, TIERS);

		String ticketSupportString = ParamUtil.getString(
			portletRequest, TICKET_SUPPORT);

		if (Validator.isNotNull(ticketSupportString)) {
			ticketSupport = GetterUtil.getBoolean(ticketSupportString);
		}

		types = ParamUtil.getIntegerValues(portletRequest, TYPES);
		zip = ParamUtil.getString(portletRequest, ZIP);
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

	public String getAccountWorkerName() {
		return accountWorkerName;
	}

	public long getAccountWorkerRole() {
		return accountWorkerRole;
	}

	public long getAccountWorkerUserId() {
		return accountWorkerUserId;
	}

	public String getCity() {
		return city;
	}

	public String getCode() {
		return code;
	}

	public String getCorpEntryName() {
		return corpEntryName;
	}

	public long getCountryId() {
		return countryId;
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

	public String getDossieraAccountKey() {
		return dossieraAccountKey;
	}

	public Boolean getExpiredSupport() {
		return expiredSupport;
	}

	public int[] getIndustries() {
		if (ArrayUtil.contains(industries, 0)) {
			return new int[0];
		}

		return industries;
	}

	public String getInstructions() {
		return instructions;
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

	public String getName() {
		return name;
	}

	public String getNotes() {
		return notes;
	}

	public String getPartnerEntryCode() {
		return partnerEntryCode;
	}

	public Boolean getPartnerManagedSupport() {
		return partnerManagedSupport;
	}

	public long[] getProductEntryIds() {
		if (ArrayUtil.contains(productEntryIds, 0)) {
			return new long[0];
		}

		return productEntryIds;
	}

	public long getRegionId() {
		return regionId;
	}

	public int[] getStatuses() {
		if (ArrayUtil.contains(statuses, -1)) {
			return new int[0];
		}

		return statuses;
	}

	public String getStreet() {
		return street;
	}

	public int getSupport() {
		return support;
	}

	public long[] getSupportRegionIds() {
		if (ArrayUtil.contains(supportRegionIds, 0)) {
			return new long[0];
		}

		return supportRegionIds;
	}

	public long[] getSupportResponseIds() {
		if (ArrayUtil.contains(supportResponseIds, 0)) {
			return new long[0];
		}

		return supportResponseIds;
	}

	public Boolean getTicketSupport() {
		return ticketSupport;
	}

	public int[] getTiers() {
		if (ArrayUtil.contains(tiers, 0)) {
			return new int[0];
		}

		return tiers;
	}

	public int[] getTypes() {
		if (ArrayUtil.contains(types, 0)) {
			return new int[0];
		}

		return types;
	}

	public String getZip() {
		return zip;
	}

	public boolean hasExpiredSupport() {
		if (expiredSupport == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasPartnerManagedSupport() {
		if (partnerManagedSupport == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasTicketSupport() {
		if (ticketSupport == null) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public boolean isAdvancedSearch() {
		if (super.isAdvancedSearch() || !isSearch()) {
			return true;
		}

		return false;
	}

	public boolean isExpiredSupport() {
		if (expiredSupport == null) {
			return false;
		}

		return expiredSupport.booleanValue();
	}

	public boolean isPartnerManagedSupport() {
		if (partnerManagedSupport == null) {
			return false;
		}

		return partnerManagedSupport.booleanValue();
	}

	public boolean isTicketSupport() {
		if (ticketSupport == null) {
			return false;
		}

		return ticketSupport.booleanValue();
	}

	protected long[] accountEnvironmentEnvASIds;
	protected long[] accountEnvironmentEnvDBIds;
	protected long[] accountEnvironmentEnvJVMIds;
	protected long[] accountEnvironmentEnvLFRIds;
	protected long[] accountEnvironmentEnvOSIds;
	protected String accountWorkerName;
	protected long accountWorkerRole;
	protected long accountWorkerUserId;
	protected String city;
	protected String code;
	protected String corpEntryName;
	protected long countryId;
	protected int createDateGTDay;
	protected int createDateGTMonth;
	protected int createDateGTYear;
	protected int createDateLTDay;
	protected int createDateLTMonth;
	protected int createDateLTYear;
	protected long createUserId;
	protected String createUserName;
	protected String dossieraAccountKey;
	protected Boolean expiredSupport;
	protected int[] industries;
	protected String instructions;
	protected int modifiedDateGTDay;
	protected int modifiedDateGTMonth;
	protected int modifiedDateGTYear;
	protected int modifiedDateLTDay;
	protected int modifiedDateLTMonth;
	protected int modifiedDateLTYear;
	protected long modifiedUserId;
	protected String modifiedUserName;
	protected String name;
	protected String notes;
	protected String partnerEntryCode;
	protected Boolean partnerManagedSupport;
	protected long[] productEntryIds;
	protected long regionId;
	protected int[] statuses;
	protected String street;
	protected int support;
	protected long[] supportRegionIds;
	protected long[] supportResponseIds;
	protected Boolean ticketSupport;
	protected int[] tiers;
	protected int[] types;
	protected String zip;

}