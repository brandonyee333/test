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

	public static String convertToE164(Phone phone) throws PortalException {
		try {
			String number = phone.getNumber();

			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

			Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(
				number, _UNKNOWN_REGION);

			number = phoneNumberUtil.format(
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