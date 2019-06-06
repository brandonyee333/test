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

package com.liferay.osb.customer.zendesk.model.listener.util;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = {})
public class ZendeskModelListenerUtil {

	public static String convertAddressToString(Address address) {
		if (address == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(7);

		if (Validator.isNotNull(address.getStreet1())) {
			sb.append(address.getStreet1());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(address.getStreet2())) {
			sb.append(address.getStreet2());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(address.getStreet3())) {
			sb.append(address.getStreet3());
			sb.append(StringPool.NEW_LINE);
		}

		StringBundler sb2 = new StringBundler(7);

		if (Validator.isNotNull(address.getCity())) {
			sb2.append(address.getCity());
		}

		Region region = address.getRegion();

		if (Validator.isNotNull(region.getName())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(region.getName());
		}

		if (Validator.isNotNull(address.getZip())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(address.getZip());
		}

		Country country = address.getCountry();

		if (Validator.isNotNull(country.getName())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.NEW_LINE);
			}

			sb2.append(
				LanguageUtil.get(
					LocaleUtil.US, "country." + country.getName()));
		}

		sb.append(sb2);

		return sb.toString();
	}

}