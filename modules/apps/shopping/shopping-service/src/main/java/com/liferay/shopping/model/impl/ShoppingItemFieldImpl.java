/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemFieldImpl extends ShoppingItemFieldBaseImpl {

	@Override
	public String[] getValuesArray() {
		return _valuesArray;
	}

	@Override
	public void setValues(String values) {
		_valuesArray = StringUtil.split(values);

		super.setValues(values);
	}

	@Override
	public void setValuesArray(String[] valuesArray) {
		_valuesArray = valuesArray;

		super.setValues(StringUtil.merge(valuesArray));
	}

	private String[] _valuesArray;

}