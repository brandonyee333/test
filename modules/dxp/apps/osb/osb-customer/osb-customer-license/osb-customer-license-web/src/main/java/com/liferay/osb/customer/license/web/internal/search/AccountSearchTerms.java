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

package com.liferay.osb.customer.license.web.internal.search;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class AccountSearchTerms extends AccountDisplayTerms {

	public AccountSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public String getFilter() {
		if (!isAdvancedSearch()) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(15);

		if (Validator.isNotNull(code)) {
			sb.append("code eq '");
			sb.append(code);
			sb.append("'");
		}

		if (Validator.isNotNull(dossieraAccountKey)) {
			if (sb.length() > 0) {
				sb.append(" and ");
			}

			sb.append("externalLinkEntityIds/any(s:s eq '");
			sb.append(dossieraAccountKey);
			sb.append("')");
		}

		if (Validator.isNotNull(koroneikiAccountKey)) {
			if (sb.length() > 0) {
				sb.append(" and ");
			}

			sb.append("accountKey eq '");
			sb.append(koroneikiAccountKey);
			sb.append("'");
		}

		if (Validator.isNotNull(name)) {
			if (sb.length() > 0) {
				sb.append(" and ");
			}

			sb.append("name eq '");
			sb.append(name);
			sb.append("'");
		}

		return sb.toString();
	}

}