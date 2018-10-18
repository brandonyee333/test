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

package com.liferay.osb.customer.zendesk.web.service.internal.search;

import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class QueryImpl implements Query {

	public void addCriterion(String criterion) {
		_criteria.add(criterion);
	}

	public int getPage() {
		return _page;
	}

	public void setPage(int page) {
		_page = page;
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		for (String criterion : _criteria) {
			if (sb.index() != 0) {
				sb.append(StringPool.SPACE);
			}

			sb.append(criterion);
		}

		return sb.toString();
	}

	private final Set<String> _criteria = new HashSet<>();
	private int _page;

}