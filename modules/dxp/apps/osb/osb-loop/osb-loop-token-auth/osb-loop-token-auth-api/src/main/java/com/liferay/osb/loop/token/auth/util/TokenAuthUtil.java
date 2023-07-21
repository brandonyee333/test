/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.util;

import com.liferay.osb.loop.token.auth.comparator.TokenAuthEntryCreateDateComparator;
import com.liferay.osb.loop.token.auth.comparator.TokenAuthEntryLoginDateComparator;
import com.liferay.osb.loop.token.auth.comparator.TokenAuthEntryUserNameComparator;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Bruno Farache
 */
public class TokenAuthUtil {

	public static OrderByComparator getTokenAuthEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		if (orderByCol.equals("create-date")) {
			return new TokenAuthEntryCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("last-login")) {
			return new TokenAuthEntryLoginDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			return new TokenAuthEntryUserNameComparator(orderByAsc);
		}

		return null;
	}

}