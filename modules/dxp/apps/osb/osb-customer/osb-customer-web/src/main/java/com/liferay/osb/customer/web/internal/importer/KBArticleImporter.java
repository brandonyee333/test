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

package com.liferay.osb.customer.web.internal.importer;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.importer.KBArticleInfo;
import com.liferay.osb.customer.web.internal.util.KBArticleUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Alan Zhang
 */
public class KBArticleImporter {

	public KBArticleImporter(InputStream inputStream) throws Exception {
		List<AssetVocabulary> assetVocabularies =
			AssetVocabularyLocalServiceUtil.getGroupVocabularies(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID);

		for (AssetVocabulary assetVocabulary : assetVocabularies) {
			List<AssetCategory> assetCategories =
				AssetCategoryLocalServiceUtil.getVocabularyCategories(
					assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (AssetCategory assetCategory : assetCategories) {
				String key = getAssetCategoryKey(
					assetVocabulary.getName(),
					assetCategory.getTitle(Locale.US, true));

				_assetCategoryMap.put(key, assetCategory);
			}
		}

		parseCSV(inputStream);
	}

	public void addErrorMessage(String message) {
		_errorMessages.add(message);
	}

	public List<String> getErrorMessages() {
		return _errorMessages;
	}

	public Map<String, KBArticleInfo> getKBArticleInfo() {
		return _kbArticleInfoMap;
	}

	public KBArticleInfo getKBArticleInfo(String key) {
		return _kbArticleInfoMap.get(key);
	}

	public KBArticleInfo removeKBArticleInfo(String key) {
		return _kbArticleInfoMap.remove(key);
	}

	protected long[] filterAssetCategoryIds(
		long[] assetCategoryIds, String assetVocabularyName,
		String assetCategoryName) {

		if (Validator.isNull(assetCategoryName)) {
			return assetCategoryIds;
		}

		assetCategoryName = assetCategoryName.replace(
			StringPool.QUOTE, StringPool.BLANK);

		String[] names = splitAssetCategoryName(assetCategoryName);

		for (String name : names) {
			if (Validator.isNull(name.trim())) {
				continue;
			}

			String key = getAssetCategoryKey(assetVocabularyName, name);

			AssetCategory assetCategory = _assetCategoryMap.get(key);

			if (assetCategory == null) {
				addErrorMessage("Cannot find category: " + name);

				continue;
			}

			assetCategoryIds = ArrayUtil.append(
				assetCategoryIds, assetCategory.getCategoryId());
		}

		return assetCategoryIds;
	}

	protected String[] filterAssetTagNames(String assetTagNames) {
		if (Validator.isNull(assetTagNames)) {
			return new String[0];
		}

		assetTagNames = assetTagNames.replace(
			StringPool.QUOTE, StringPool.BLANK);

		return StringUtil.split(assetTagNames);
	}

	protected String getAssetCategoryKey(
		String assetVocabularyName, String assetCategoryName) {

		StringBundler sb = new StringBundler(3);

		sb.append(KBArticleInfo.normalize(assetVocabularyName));
		sb.append(StringPool.POUND);
		sb.append(assetCategoryName.trim());

		return sb.toString();
	}

	protected boolean isAssetVocabulary(
		String header, List<AssetVocabulary> assetVocabularies) {

		for (AssetVocabulary assetVocabulary : assetVocabularies) {
			String assetVocabularyHeader = KBArticleInfo.normalize(
				assetVocabulary.getName());

			if (assetVocabularyHeader.equals(header)) {
				return true;
			}
		}

		return false;
	}

	protected void parseCSV(InputStream inputStream) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(inputStream));

		List<AssetVocabulary> assetVocabularies =
			KBArticleUtil.getAssetVocabularies();

		try {
			String headLine = bufferedReader.readLine();

			String[] headers = StringUtil.split(headLine);

			int urlTitleIndex = 0;
			int tagIndex = 0;
			Map<Integer, String> assetVocabularyIndexMap = new HashMap<>();

			for (int i = 0; i < headers.length; i++) {
				if (headers[i].equals(KBArticleInfo.FIELD_URL_TITLE)) {
					urlTitleIndex = i;
				}
				else if (headers[i].equals(KBArticleInfo.FIELD_TAG)) {
					tagIndex = i;
				}
				else if (isAssetVocabulary(headers[i], assetVocabularies)) {
					assetVocabularyIndexMap.put(i, headers[i]);
				}
			}

			String line = null;

			while (Validator.isNotNull(line = bufferedReader.readLine())) {
				String[] values = _delimiter.split(line, headers.length);

				if (headers.length > values.length) {
					addErrorMessage("Cannot find category: " + line);

					continue;
				}

				String urlTitle = GetterUtil.getString(values[urlTitleIndex]);

				if (Validator.isNull(urlTitle)) {
					addErrorMessage("Cannot find title: " + line);

					continue;
				}

				String[] assetTagNames = filterAssetTagNames(values[tagIndex]);

				if (assetTagNames.length == 0) {
					addErrorMessage("Cannot find tag: " + line);
				}

				long[] assetCategoryIds = new long[0];

				for (int index : assetVocabularyIndexMap.keySet()) {
					String assetCategoryNames = GetterUtil.getString(
						values[index]);

					assetCategoryIds = filterAssetCategoryIds(
						assetCategoryIds, assetVocabularyIndexMap.get(index),
						assetCategoryNames);
				}

				if (assetCategoryIds.length == 0) {
					addErrorMessage("Cannot find category: " + line);
				}

				KBArticleInfo kbArticleInfo = new KBArticleInfo(
					assetCategoryIds, assetTagNames);

				_kbArticleInfoMap.put(urlTitle, kbArticleInfo);
			}
		}
		finally {
			bufferedReader.close();
		}
	}

	protected String[] splitAssetCategoryName(String assetCategoryName) {
		String[] specialNames = new String[0];

		for (String specialAssetCategoryName : _specialAssetCategoryNames) {
			if (assetCategoryName.contains(specialAssetCategoryName)) {
				specialNames = ArrayUtil.append(
					specialNames, specialAssetCategoryName);

				assetCategoryName = assetCategoryName.replaceAll(
					specialAssetCategoryName, StringPool.BLANK);
			}
		}

		String[] names = StringUtil.split(assetCategoryName);

		return ArrayUtil.append(names, specialNames);
	}

	private static final Pattern _delimiter = Pattern.compile(
		",(?=(?:[^\"]*\"[^\"]*\"[^\"]*)*$|[^\"]*$)", Pattern.CASE_INSENSITIVE);
	private static final String[] _specialAssetCategoryNames = {
		"Alerts, Announcements", "Categories, Tags, Vocabularies",
		"Deployment, Environments", "Staging, Export/Import",
		"UI Infrastructure, Accessibility",
		"User, Membership, and Role Management", "Workflow, Workflow Forms"
	};

	private final Map<String, AssetCategory> _assetCategoryMap =
		new HashMap<>();
	private final List<String> _errorMessages = new ArrayList<>();
	private final Map<String, KBArticleInfo> _kbArticleInfoMap =
		new HashMap<>();

}