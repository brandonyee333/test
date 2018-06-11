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

package com.liferay.osb.customer.util.comparator;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class AssetVocabularyDescriptionComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "AssetVocabulary.description ASC";

	public static final String ORDER_BY_DESC =
		"AssetVocabulary.description DESC";

	public static final String[] ORDER_BY_FIELDS = {"description"};

	public AssetVocabularyDescriptionComparator() {
		this(false);
	}

	public AssetVocabularyDescriptionComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetVocabulary assetVocabulary1 = (AssetVocabulary)obj1;
		AssetVocabulary assetVocabulary2 = (AssetVocabulary)obj2;

		String description1 = assetVocabulary1.getDescription();
		String description2 = assetVocabulary2.getDescription();

		int value = description1.compareTo(description2);

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