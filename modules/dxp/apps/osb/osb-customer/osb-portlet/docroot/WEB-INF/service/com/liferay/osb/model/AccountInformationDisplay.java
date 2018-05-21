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

import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Zhang
 */
public class AccountInformationDisplay {

	public AccountInformationDisplay(
		List<AccountInformation> accountInformationList) {

		for (AccountInformation accountInformation : accountInformationList) {
			_fieldData.put(
				accountInformation.getFieldId(), accountInformation.getData());
			_fieldModifiedDates.put(
				accountInformation.getFieldId(),
				accountInformation.getModifiedDate());
			_fieldModifiedUserNames.put(
				accountInformation.getFieldId(),
				accountInformation.getModifiedUserName());
		}
	}

	public String getData(int fieldId) {
		return GetterUtil.getString(_fieldData.get(fieldId));
	}

	public Date getModifiedDate(int fieldId) {
		return _fieldModifiedDates.get(fieldId);
	}

	public String getModifiedUserName(int fieldId) {
		return GetterUtil.getString(_fieldModifiedUserNames.get(fieldId));
	}

	private Map<Integer, String> _fieldData = new HashMap<>();
	private Map<Integer, Date> _fieldModifiedDates = new HashMap<>();
	private Map<Integer, String> _fieldModifiedUserNames = new HashMap<>();

}