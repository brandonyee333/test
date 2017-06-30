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

	private Map<Integer, String> _fieldData = new HashMap<Integer, String>();
	private Map<Integer, Date> _fieldModifiedDates =
		new HashMap<Integer, Date>();
	private Map<Integer, String> _fieldModifiedUserNames =
		new HashMap<Integer, String>();

}