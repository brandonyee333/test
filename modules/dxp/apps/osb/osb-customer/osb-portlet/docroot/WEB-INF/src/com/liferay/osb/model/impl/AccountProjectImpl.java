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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AccountInformation;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alan Zhang
 */
public class AccountProjectImpl extends AccountProjectBaseImpl {

	public AccountProjectImpl() {
	}

	public void addData(AccountInformation accountInformation) {
		_fieldData.put(
			accountInformation.getFieldId(), accountInformation.getData());
	}

	public String getData(int fieldId) {
		return GetterUtil.getString(_fieldData.get(fieldId));
	}

	public void setData(List<AccountInformation> accountInformationList) {
		for (AccountInformation accountInformation : accountInformationList) {
			_fieldData.put(
				accountInformation.getFieldId(), accountInformation.getData());
		}
	}

	private Map<Integer, String> _fieldData = new HashMap<>();

}