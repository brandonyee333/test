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

package com.liferay.osb.util.comparator;

import com.liferay.osb.model.CorpProject;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Joan Kim
 */
public class CorpProjectNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "OSB_CorpProject.name ASC";

	public static final String ORDER_BY_DESC = "OSB_CorpProject.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public CorpProjectNameComparator() {
		this(true);
	}

	public CorpProjectNameComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		CorpProject corpProject1 = (CorpProject)obj1;
		CorpProject corpProject2 = (CorpProject)obj2;

		String name1 = StringUtil.toLowerCase(corpProject1.getName());
		String name2 = StringUtil.toLowerCase(corpProject2.getName());

		int value = name1.compareTo(name2);

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_asc) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _asc;
	}

	private boolean _asc;

}