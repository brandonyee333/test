/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class AssetCategoryUtil {

	public static final String CATEGORY_SEPARATOR = "_CATEGORY_";

	public static long[] filterCategoryIds(
		long vocabularyId, long[] categoryIds) {

		List<Long> filteredCategoryIds = new ArrayList<>();

		for (long categoryId : categoryIds) {
			AssetCategory category =
				AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

			if (category == null) {
				continue;
			}

			if (category.getVocabularyId() == vocabularyId) {
				filteredCategoryIds.add(category.getCategoryId());
			}
		}

		return ArrayUtil.toArray(filteredCategoryIds.toArray(new Long[0]));
	}

	public static String[] getCategoryIdsTitles(
		String categoryIds, String categoryNames, long vocabularyId,
		ThemeDisplay themeDisplay) {

		if (Validator.isNotNull(categoryIds)) {
			long[] categoryIdsArray = GetterUtil.getLongValues(
				StringUtil.split(categoryIds));

			if (vocabularyId > 0) {
				categoryIdsArray = filterCategoryIds(
					vocabularyId, categoryIdsArray);
			}

			categoryIds = StringPool.BLANK;
			categoryNames = StringPool.BLANK;

			if (categoryIdsArray.length > 0) {
				StringBundler categoryIdsSB = new StringBundler(
					categoryIdsArray.length * 2);
				StringBundler categoryNamesSB = new StringBundler(
					categoryIdsArray.length * 2);

				for (long categoryId : categoryIdsArray) {
					AssetCategory category =
						AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

					if (category == null) {
						continue;
					}

					categoryIdsSB.append(categoryId);
					categoryIdsSB.append(StringPool.COMMA);

					categoryNamesSB.append(
						category.getTitle(themeDisplay.getLocale()));
					categoryNamesSB.append(CATEGORY_SEPARATOR);
				}

				if (categoryIdsSB.index() > 0) {
					categoryIdsSB.setIndex(categoryIdsSB.index() - 1);
					categoryNamesSB.setIndex(categoryNamesSB.index() - 1);

					categoryIds = categoryIdsSB.toString();
					categoryNames = categoryNamesSB.toString();
				}
			}
		}

		return new String[] {categoryIds, categoryNames};
	}

}