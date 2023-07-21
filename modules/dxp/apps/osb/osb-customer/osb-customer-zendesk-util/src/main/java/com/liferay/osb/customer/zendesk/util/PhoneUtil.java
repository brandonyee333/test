/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = PhoneUtil.class)
public class PhoneUtil {

	public String convertToE164(Phone phone) throws PortalException {
		try {
			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

			Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(
				phone.getNumber(), _UNKNOWN_REGION);

			String number = phoneNumberUtil.format(
				phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);

			if (Validator.isNotNull(phone.getExtension())) {
				number += "x" + phone.getExtension();
			}

			return number;
		}
		catch (NumberParseException npe) {
			return StringPool.BLANK;
		}
	}

	private static final String _UNKNOWN_REGION = "ZZ";

}