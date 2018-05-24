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

package com.liferay.portal.workflow.kaleo.designer.util.comparator;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

/**
 * Orders Kaleo draft definitions according to their creation date during
 * listing operations. The order can be ascending or descending and is defined
 * by the value specified in the class constructor.
 *
 * @author Rafael Praxedes
 * @see    com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalService#getKaleoDraftDefinitions(
 *         String, int, int, int, OrderByComparator, ServiceContext)
 */
public class KaleoDraftDefinitionCreateDateComparator
	extends OrderByComparator<KaleoDraftDefinition> {

	public static final String ORDER_BY_ASC =
		"KaleoDraftDefinition.createDate ASC";

	public static final String ORDER_BY_DESC =
		"KaleoDraftDefinition.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public KaleoDraftDefinitionCreateDateComparator() {
		this(false);
	}

	public KaleoDraftDefinitionCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		KaleoDraftDefinition kaleoDraftDefinition1,
		KaleoDraftDefinition kaleoDraftDefinition2) {

		int value = DateUtil.compareTo(
			kaleoDraftDefinition1.getCreateDate(),
			kaleoDraftDefinition2.getCreateDate());

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
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
		return _ascending;
	}

	private final boolean _ascending;

}