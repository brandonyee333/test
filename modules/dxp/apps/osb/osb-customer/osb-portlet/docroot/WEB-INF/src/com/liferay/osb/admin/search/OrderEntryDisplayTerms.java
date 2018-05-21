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
 * @author Alan Zhang
 */
public class OrderEntryDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_ENTRY_ID = "accountEntryId";

	public static final String ACCOUNT_ENTRY_NAME = "accountEntryName";

	public static final String ACTUAL_START_DATE_GT_DAY =
		"actualStartDateGTDay";

	public static final String ACTUAL_START_DATE_GT_MONTH =
		"actualStartDateGTMonth";

	public static final String ACTUAL_START_DATE_GT_YEAR =
		"actualStartDateGTYear";

	public static final String ACTUAL_START_DATE_LT_DAY =
		"actualStartDateLTDay";

	public static final String ACTUAL_START_DATE_LT_MONTH =
		"actualStartDateLTMonth";

	public static final String ACTUAL_START_DATE_LT_YEAR =
		"actualStartDateLTYear";

	public static final String CREATE_DATE_GT_DAY = "createDateGTDay";

	public static final String CREATE_DATE_GT_MONTH = "createDateGTMonth";

	public static final String CREATE_DATE_GT_YEAR = "createDateGTYear";

	public static final String CREATE_DATE_LT_DAY = "createDateLTDay";

	public static final String CREATE_DATE_LT_MONTH = "createDateLTMonth";

	public static final String CREATE_DATE_LT_YEAR = "createDateLTYear";

	public static final String CREATE_USER_ID = "createUserId";

	public static final String CREATE_USER_NAME = "createUserName";

	public static final String MODIFIED_DATE_GT_DAY = "modifiedDateGTDay";

	public static final String MODIFIED_DATE_GT_MONTH = "modifiedDateGTMonth";

	public static final String MODIFIED_DATE_GT_YEAR = "modifiedDateGTYear";

	public static final String MODIFIED_DATE_LT_DAY = "modifiedDateLTDay";

	public static final String MODIFIED_DATE_LT_MONTH = "modifiedDateLTMonth";

	public static final String MODIFIED_DATE_LT_YEAR = "modifiedDateLTYear";

	public static final String MODIFIED_USER_ID = "modifiedUserId";

	public static final String MODIFIED_USER_NAME = "modifiedUserName";

	public static final String PRODUCT_ENTRY_ID = "productEntryId";

	public static final String PRORATED = "prorated";

	public static final String PURCHASE_ORDER_KEY = "purchaseOrderKey";

	public static final String START_DATE_GT_DAY = "startDateGTDay";

	public static final String START_DATE_GT_MONTH = "startDateGTMonth";

	public static final String START_DATE_GT_YEAR = "startDateGTYear";

	public static final String START_DATE_LT_DAY = "startDateLTDay";

	public static final String START_DATE_LT_MONTH = "startDateLTMonth";

	public static final String START_DATE_LT_YEAR = "startDateLTYear";

	public static final String STATUSES = "statuses";

	public OrderEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		accountEntryId = ParamUtil.getLong(portletRequest, ACCOUNT_ENTRY_ID);
		accountEntryName = ParamUtil.getString(
			portletRequest, ACCOUNT_ENTRY_NAME);
		actualStartDateGTDay = ParamUtil.getInteger(
			portletRequest, ACTUAL_START_DATE_GT_DAY);
		actualStartDateGTMonth = ParamUtil.getInteger(
			portletRequest, ACTUAL_START_DATE_GT_MONTH);
		actualStartDateGTYear = ParamUtil.getInteger(
			portletRequest, ACTUAL_START_DATE_GT_YEAR);
		actualStartDateLTDay = ParamUtil.getInteger(
			portletRequest, ACTUAL_START_DATE_LT_DAY);
		actualStartDateLTMonth = ParamUtil.getInteger(
			portletRequest, ACTUAL_START_DATE_LT_MONTH);
		actualStartDateLTYear = ParamUtil.getInteger(
			portletRequest, ACTUAL_START_DATE_LT_YEAR);
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
		productEntryId = ParamUtil.getLong(portletRequest, PRODUCT_ENTRY_ID);

		String proratedString = ParamUtil.getString(portletRequest, PRORATED);

		if (Validator.isNotNull(proratedString)) {
			prorated = GetterUtil.getBoolean(proratedString);
		}

		purchaseOrderKey = ParamUtil.getString(
			portletRequest, PURCHASE_ORDER_KEY);
		startDateGTDay = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_DAY);
		startDateGTMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_MONTH);
		startDateGTYear = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_YEAR);
		startDateLTDay = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_DAY);
		startDateLTMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_MONTH);
		startDateLTYear = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_YEAR);
		statuses = ParamUtil.getIntegerValues(portletRequest, STATUSES);
	}

	public long getAccountEntryId() {
		return accountEntryId;
	}

	public String getAccountEntryName() {
		return accountEntryName;
	}

	public int getActualStartDateGTDay() {
		return actualStartDateGTDay;
	}

	public int getActualStartDateGTMonth() {
		return actualStartDateGTMonth;
	}

	public int getActualStartDateGTYear() {
		return actualStartDateGTYear;
	}

	public int getActualStartDateLTDay() {
		return actualStartDateLTDay;
	}

	public int getActualStartDateLTMonth() {
		return actualStartDateLTMonth;
	}

	public int getActualStartDateLTYear() {
		return actualStartDateLTYear;
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

	public long getProductEntryId() {
		return productEntryId;
	}

	public Boolean getProrated() {
		return prorated;
	}

	public String getPurchaseOrderKey() {
		return purchaseOrderKey;
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

	public int[] getStatuses() {
		if (ArrayUtil.contains(statuses, -1)) {
			return new int[0];
		}

		return statuses;
	}

	public Boolean hasProrated() {
		if (prorated == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isProrated() {
		if (prorated == null) {
			return false;
		}

		return prorated.booleanValue();
	}

	protected long accountEntryId;
	protected String accountEntryName;
	protected int actualStartDateGTDay;
	protected int actualStartDateGTMonth;
	protected int actualStartDateGTYear;
	protected int actualStartDateLTDay;
	protected int actualStartDateLTMonth;
	protected int actualStartDateLTYear;
	protected int createDateGTDay;
	protected int createDateGTMonth;
	protected int createDateGTYear;
	protected int createDateLTDay;
	protected int createDateLTMonth;
	protected int createDateLTYear;
	protected long createUserId;
	protected String createUserName;
	protected int modifiedDateGTDay;
	protected int modifiedDateGTMonth;
	protected int modifiedDateGTYear;
	protected int modifiedDateLTDay;
	protected int modifiedDateLTMonth;
	protected int modifiedDateLTYear;
	protected long modifiedUserId;
	protected String modifiedUserName;
	protected long productEntryId;
	protected Boolean prorated;
	protected String purchaseOrderKey;
	protected int startDateGTDay;
	protected int startDateGTMonth;
	protected int startDateGTYear;
	protected int startDateLTDay;
	protected int startDateLTMonth;
	protected int startDateLTYear;
	protected int[] statuses;

}