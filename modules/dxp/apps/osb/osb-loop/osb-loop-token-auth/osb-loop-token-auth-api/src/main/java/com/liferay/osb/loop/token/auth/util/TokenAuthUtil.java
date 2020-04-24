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