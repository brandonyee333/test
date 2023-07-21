/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
			sb.append("contains(code, '");
			sb.append(code);
			sb.append("')");
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

			sb.append("contains(name, '");
			sb.append(name);
			sb.append("')");
		}

		return sb.toString();
	}

}