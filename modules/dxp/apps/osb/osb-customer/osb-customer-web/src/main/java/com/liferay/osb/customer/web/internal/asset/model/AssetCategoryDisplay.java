/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.asset.model;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants;
import com.liferay.osb.customer.web.internal.util.comparator.AssetCategoryLeftCategoryIdComparator;
import com.liferay.osb.customer.web.internal.util.comparator.AssetCategoryNameComparator;
import com.liferay.osb.customer.web.internal.util.comparator.AssetVocabularyDescriptionComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author Amos Fong
 */
public class AssetCategoryDisplay {

	public AssetCategoryDisplay(
			Locale locale, List<AssetCategory> assetCategories)
		throws PortalException {

		_locale = locale;

		TreeSet<AssetCategory> assetCategoryTree = new TreeSet<>(
			new AssetCategoryLeftCategoryIdComparator(true));

		assetCategoryTree.addAll(assetCategories);

		for (AssetCategory assetCategory : assetCategoryTree) {
			AssetVocabulary assetVocabulary =
				AssetVocabularyLocalServiceUtil.getVocabulary(
					assetCategory.getVocabularyId());

			if (!_assetVocabularies.contains(assetVocabulary)) {
				_assetVocabularies.add(assetVocabulary);
			}

			List<AssetCategory> headAssetCategories =
				_headAssetCategoriesMap.get(assetCategory.getVocabularyId());

			if (headAssetCategories == null) {
				headAssetCategories = new ArrayList<>();

				headAssetCategories.add(assetCategory);

				_headAssetCategoriesMap.put(
					assetCategory.getVocabularyId(), headAssetCategories);
			}
			else {
				AssetCategory lastAssetCategory = headAssetCategories.get(
					headAssetCategories.size() - 1);

				if (isSubassetCategory(lastAssetCategory, assetCategory)) {
					addSubassetCategory(lastAssetCategory, assetCategory);
				}
				else {
					headAssetCategories.add(assetCategory);
				}
			}
		}

		Collections.sort(
			_assetVocabularies, new AssetVocabularyDescriptionComparator(true));
	}

	public BooleanFilter createBooleanFilter() throws PortalException {
		BooleanFilter assetVocabularyFilter = new BooleanFilter();

		for (AssetVocabulary assetVocabulary : _assetVocabularies) {
			List<AssetCategory> headAssetCategories =
				_headAssetCategoriesMap.get(assetVocabulary.getVocabularyId());

			if (!headAssetCategories.isEmpty()) {
				BooleanFilter assetCategoriesFilter =
					createAssetCategoriesFilter(headAssetCategories);

				assetVocabularyFilter.add(
					assetCategoriesFilter, BooleanClauseOccur.MUST);
			}
		}

		return assetVocabularyFilter;
	}

	public String renderAbstract() {
		StringBundler sb = new StringBundler();

		renderAbstract(
			sb, OSBCustomerConstants.ASSET_VOCABULARY_LIFERAY_PRODUCT_ID,
			false);
		renderAbstract(
			sb, OSBCustomerConstants.ASSET_VOCABULARY_ARTICLE_TYPE_ID, false);

		return sb.toString();
	}

	public String renderFull() throws PortalException {
		StringBundler sb = new StringBundler();

		for (AssetVocabulary assetVocabulary : _assetVocabularies) {
			if (assetVocabulary.getVocabularyId() ==
					OSBCustomerConstants.ASSET_VOCABULARY_LIFERAY_PRODUCT_ID) {

				renderTree(sb, assetVocabulary);
			}
			else {
				renderFull(sb, assetVocabulary);
			}
		}

		return sb.toString();
	}

	public String renderVocabularyCategories(long vocabularyId) {
		List<AssetCategory> headAssetCategories = _headAssetCategoriesMap.get(
			vocabularyId);

		if ((headAssetCategories == null) || headAssetCategories.isEmpty()) {
			return StringPool.BLANK;
		}

		Collections.sort(
			headAssetCategories, new AssetCategoryNameComparator(true));

		List<AssetCategory> assetCategories = new ArrayList<>();

		for (AssetCategory headAssetCategory : headAssetCategories) {
			assetCategories.addAll(getSubassetCategories(headAssetCategory));
		}

		return ListUtil.toString(assetCategories, AssetCategory.NAME_ACCESSOR);
	}

	protected void addSubassetCategory(
		AssetCategory assetCategory, AssetCategory subassetCategory) {

		List<AssetCategory> assetCategories = _subassetCategoriesMap.get(
			assetCategory.getCategoryId());

		if (assetCategories == null) {
			assetCategories = new ArrayList<>();

			_subassetCategoriesMap.put(
				assetCategory.getCategoryId(), assetCategories);
		}
		else {
			for (AssetCategory curAssetCategory : assetCategories) {
				if (isSubassetCategory(curAssetCategory, subassetCategory)) {
					addSubassetCategory(curAssetCategory, subassetCategory);

					return;
				}
			}
		}

		assetCategories.add(subassetCategory);
	}

	protected BooleanFilter createAssetCategoriesFilter(
			List<AssetCategory> assetCategories)
		throws PortalException {

		BooleanFilter assetCategoriesFilter = new BooleanFilter();

		for (AssetCategory assetCategory : assetCategories) {
			BooleanFilter assetCategoryFilter = new BooleanFilter();

			assetCategoryFilter.addTerm(
				"assetCategoryIds", assetCategory.getCategoryId());

			List<AssetCategory> childAssetCategories =
				_subassetCategoriesMap.get(assetCategory.getCategoryId());

			if (childAssetCategories != null) {
				BooleanFilter childAssetCategoriesFilter =
					createAssetCategoriesFilter(childAssetCategories);

				assetCategoryFilter.add(
					childAssetCategoriesFilter, BooleanClauseOccur.MUST);
			}

			assetCategoriesFilter.add(
				assetCategoryFilter, BooleanClauseOccur.SHOULD);
		}

		return assetCategoriesFilter;
	}

	protected List<AssetCategory> getSubassetCategories(
		AssetCategory assetCategory) {

		List<AssetCategory> assetCategories = new ArrayList<>();

		assetCategories.add(assetCategory);

		List<AssetCategory> subassetCategories = _subassetCategoriesMap.get(
			assetCategory.getCategoryId());

		if ((subassetCategories == null) || subassetCategories.isEmpty()) {
			return assetCategories;
		}

		Collections.sort(
			subassetCategories, new AssetCategoryNameComparator(true));

		for (AssetCategory subassetCategory : subassetCategories) {
			assetCategories.addAll(getSubassetCategories(subassetCategory));
		}

		return assetCategories;
	}

	protected boolean isLeafNode(List<AssetCategory> assetCategories) {
		for (AssetCategory assetCategory : assetCategories) {
			if ((assetCategory.getRightCategoryId() -
					assetCategory.getLeftCategoryId()) > 1) {

				return false;
			}
		}

		return true;
	}

	protected boolean isSubassetCategory(
		AssetCategory assetCategory, AssetCategory subassetCategory) {

		if ((assetCategory.getLeftCategoryId() <
				subassetCategory.getLeftCategoryId()) &&
			(assetCategory.getRightCategoryId() >
				subassetCategory.getRightCategoryId())) {

			return true;
		}

		return false;
	}

	protected void renderAbstract(
		StringBundler sb, AssetCategory assetCategory,
		boolean renderAdditionInfo) {

		sb.append(renderAssetCategory(assetCategory, renderAdditionInfo));

		List<AssetCategory> childAssetCategories = _subassetCategoriesMap.get(
			assetCategory.getCategoryId());

		if ((childAssetCategories != null) && !childAssetCategories.isEmpty()) {
			sb.append(": ");

			for (int i = 0; i < childAssetCategories.size(); i++) {
				AssetCategory childAssetCategory = childAssetCategories.get(i);

				sb.append(
					renderAssetCategory(
						childAssetCategory, renderAdditionInfo));

				if ((i + 1) < childAssetCategories.size()) {
					sb.append(", ");
				}
			}
		}
	}

	protected void renderAbstract(
		StringBundler sb, long vocabularyId, boolean renderAdditionInfo) {

		List<AssetCategory> headAssetCategories = _headAssetCategoriesMap.get(
			vocabularyId);

		if ((headAssetCategories == null) || headAssetCategories.isEmpty()) {
			return;
		}

		for (AssetCategory headAssetCategory : headAssetCategories) {
			if (sb.length() > 0) {
				sb.append(" - ");
			}

			renderAbstract(sb, headAssetCategory, renderAdditionInfo);
		}
	}

	protected String renderAssetCategory(
		AssetCategory assetCategory, boolean renderAdditionInfo) {

		if (renderAdditionInfo) {
			StringBundler sb = new StringBundler(6);

			sb.append("<span class=\"asset-category\" ");
			sb.append("data-asset-category-id=\"");
			sb.append(assetCategory.getCategoryId());
			sb.append("\">");
			sb.append(assetCategory.getTitle(_locale));
			sb.append("</span>");

			return sb.toString();
		}
		else {
			return assetCategory.getTitle(_locale);
		}
	}

	protected void renderFull(
		StringBundler sb, AssetVocabulary assetVocabulary) {

		List<AssetCategory> headAssetCategories = _headAssetCategoriesMap.get(
			assetVocabulary.getVocabularyId());

		if ((headAssetCategories == null) || headAssetCategories.isEmpty()) {
			return;
		}

		sb.append("<div><div><u>");
		sb.append(assetVocabulary.getTitle(_locale));
		sb.append("</u></div><ul>");

		for (AssetCategory headAssetCategory : headAssetCategories) {
			sb.append("<li>");

			renderAbstract(sb, headAssetCategory, true);

			sb.append("</li>");
		}

		sb.append("</ul></div>");
	}

	protected void renderTree(
		StringBundler sb, AssetVocabulary assetVocabulary) {

		List<AssetCategory> headAssetCategories = _headAssetCategoriesMap.get(
			assetVocabulary.getVocabularyId());

		if ((headAssetCategories == null) || headAssetCategories.isEmpty()) {
			return;
		}

		sb.append("<div><div><u>");
		sb.append(assetVocabulary.getTitle(_locale));
		sb.append("</u></div>");

		renderTree(sb, headAssetCategories);

		sb.append("</div>");
	}

	protected void renderTree(
		StringBundler sb, List<AssetCategory> assetCategories) {

		if ((assetCategories == null) || assetCategories.isEmpty()) {
			return;
		}

		Collections.sort(
			assetCategories, new AssetCategoryNameComparator(true));

		sb.append("<ul>");

		if (isLeafNode(assetCategories)) {
			sb.append("<li>");

			for (int i = 0; i < assetCategories.size(); i++) {
				AssetCategory assetCategory = assetCategories.get(i);

				sb.append(renderAssetCategory(assetCategory, true));

				if ((i + 1) < assetCategories.size()) {
					sb.append(", ");
				}
			}

			sb.append("</li>");
		}
		else {
			for (AssetCategory assetCategory : assetCategories) {
				sb.append("<li>");
				sb.append(renderAssetCategory(assetCategory, true));

				List<AssetCategory> childAssetCategories =
					_subassetCategoriesMap.get(assetCategory.getCategoryId());

				renderTree(sb, childAssetCategories);

				sb.append("</li>");
			}
		}

		sb.append("</ul>");
	}

	private final List<AssetVocabulary> _assetVocabularies = new ArrayList<>();
	private final Map<Long, List<AssetCategory>> _headAssetCategoriesMap =
		new HashMap<>();
	private final Locale _locale;
	private final Map<Long, List<AssetCategory>> _subassetCategoriesMap =
		new HashMap<>();

}