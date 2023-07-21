/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.wiki.model.WikiNode;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.util.comparator.NodeNameComparator;
import com.liferay.wiki.util.comparator.PageCreateDateComparator;
import com.liferay.wiki.util.comparator.PageModifiedDateComparator;
import com.liferay.wiki.util.comparator.PageTitleComparator;
import com.liferay.wiki.util.comparator.PageVersionComparator;

/**
 * @author Sergio González
 */
public class WikiPortletUtil {

	public static OrderByComparator<WikiNode> getNodeOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<WikiNode> orderByComparator = null;

		if (orderByCol.equals("name")) {
			orderByComparator = new NodeNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static OrderByComparator<WikiPage> getPageOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator<WikiPage> orderByComparator = null;

		if (orderByCol.equals("createDate")) {
			orderByComparator = new PageCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("modifiedDate")) {
			orderByComparator = new PageModifiedDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("title")) {
			orderByComparator = new PageTitleComparator(orderByAsc);
		}
		else if (orderByCol.equals("version")) {
			orderByComparator = new PageVersionComparator(orderByAsc);
		}

		return orderByComparator;
	}

}