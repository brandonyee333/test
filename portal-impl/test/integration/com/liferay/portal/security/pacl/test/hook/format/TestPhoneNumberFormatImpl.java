/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test.hook.format;

import com.liferay.portal.kernel.format.PhoneNumberFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class TestPhoneNumberFormatImpl implements PhoneNumberFormat {

	@Override
	public String format(String phoneNumber) {
		return "(TEST) " + phoneNumber;
	}

	@Override
	public String strip(String phoneNumber) {
		return phoneNumber;
	}

	@Override
	public boolean validate(String phoneNumber) {
		return true;
	}

}