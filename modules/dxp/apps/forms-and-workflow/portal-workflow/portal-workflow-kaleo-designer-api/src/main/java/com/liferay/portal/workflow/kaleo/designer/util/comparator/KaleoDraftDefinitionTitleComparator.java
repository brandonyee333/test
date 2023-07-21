/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

/**
 * Orders Kaleo draft definitions according to their title during listing
 * operations. The order can be ascending or descending and is defined by the
 * value specified in the class constructor.
 *
 * @author Rafael Praxedes
 * @see    com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionLocalService#getKaleoDraftDefinitions(
 *         String, int, int, int, OrderByComparator, ServiceContext)
 */
public class KaleoDraftDefinitionTitleComparator
	extends OrderByComparator<KaleoDraftDefinition> {

	public static final String ORDER_BY_ASC = "KaleoDraftDefinition.title ASC";

	public static final String ORDER_BY_DESC =
		"KaleoDraftDefinition.title DESC";

	public static final String[] ORDER_BY_FIELDS = {"title"};

	public KaleoDraftDefinitionTitleComparator() {
		this(false);
	}

	public KaleoDraftDefinitionTitleComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		KaleoDraftDefinition kaleoDraftDefinition1,
		KaleoDraftDefinition kaleoDraftDefinition2) {

		String title1 = StringUtil.toLowerCase(
			kaleoDraftDefinition1.getTitle());
		String title2 = StringUtil.toLowerCase(
			kaleoDraftDefinition2.getTitle());

		int value = title1.compareTo(title2);

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
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