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

package com.liferay.osb.customer.zendesk.synchronizer.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = {})
public class AddressUtil {

	public static String convertAddressToString(PostalAddress postalAddress) {
		if (postalAddress == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(7);

		if (Validator.isNotNull(postalAddress.getStreetAddressLine1())) {
			sb.append(postalAddress.getStreetAddressLine1());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(postalAddress.getStreetAddressLine2())) {
			sb.append(postalAddress.getStreetAddressLine2());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(postalAddress.getStreetAddressLine3())) {
			sb.append(postalAddress.getStreetAddressLine3());
			sb.append(StringPool.NEW_LINE);
		}

		StringBundler sb2 = new StringBundler(7);

		if (Validator.isNotNull(postalAddress.getAddressLocality())) {
			sb2.append(postalAddress.getAddressLocality());
		}

		if (Validator.isNotNull(postalAddress.getAddressRegion())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(postalAddress.getAddressRegion());
		}

		if (Validator.isNotNull(postalAddress.getPostalCode())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(postalAddress.getPostalCode());
		}

		if (Validator.isNotNull(postalAddress.getAddressCountry())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.NEW_LINE);
			}

			sb2.append(postalAddress.getAddressCountry());
		}

		sb.append(sb2);

		return sb.toString();
	}

}