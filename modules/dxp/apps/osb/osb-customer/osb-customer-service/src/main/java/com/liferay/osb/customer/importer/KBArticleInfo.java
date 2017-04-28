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

package com.liferay.osb.customer.importer;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil;
import com.liferay.asset.kernel.service.AssetTagLocalServiceUtil;
import com.liferay.knowledge.base.constants.KBArticleConstants;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.osb.customer.asset.model.AssetCategoryDisplay;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.CSVUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.util.xml.DocUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alan Zhang
 */
public class KBArticleInfo {

	public static final String FIELD_AUDIENCE = "audience";

	public static final String FIELD_SECTION = "section";

	public static final String FIELD_SUBSECTION = "subsection";

	public static final String FIELD_TAG = "tag";

	public static final String FIELD_TITLE = "title";

	public static final String FIELD_URL_TITLE = "url-title";

	public static String exportToCVSHeader(
		List<AssetVocabulary> assetVocabularies) {

		List<String> headers = new ArrayList<>();

		for (String field : _fields) {
			headers.add(field);
		}

		for (AssetVocabulary assetVocabulary : assetVocabularies) {
			String name = normalize(assetVocabulary.getName());

			headers.add(name);
		}

		return StringUtil.merge(headers);
	}

	public static String normalize(String name) {
		name = name.replaceAll(StringPool.SPACE, StringPool.DASH);

		return StringUtil.toLowerCase(name);
	}

	public KBArticleInfo(KBArticle kbArticle, String section, String subsection)
		throws PortalException {

		_assetCategoryIds = new long[0];
		_assetLinkEntryIds = new long[0];
		_fieldInfo = new HashMap<>();

		List<AssetCategory> assetCategories =
			AssetCategoryLocalServiceUtil.getCategories(
				KBArticleConstants.getClassName(), kbArticle.getClassPK());

		_assetCategoryDisplay = new AssetCategoryDisplay(
			Locale.US, assetCategories);

		_kbArticle = kbArticle;

		_assetTagNames = AssetTagLocalServiceUtil.getTagNames(
			_kbArticle.getClassNameId(), _kbArticle.getClassPK());

		String title = StringUtil.toLowerCase(kbArticle.getTitle());

		if (title.contains("internal:")) {
			_fieldInfo.put(FIELD_AUDIENCE, "Internal");
		}
		else {
			_fieldInfo.put(FIELD_AUDIENCE, "External");
		}

		_fieldInfo.put(FIELD_SECTION, section);
		_fieldInfo.put(FIELD_SUBSECTION, subsection);
		_fieldInfo.put(FIELD_TITLE, _kbArticle.getTitle());
		_fieldInfo.put(FIELD_URL_TITLE, _kbArticle.getUrlTitle());
	}

	public KBArticleInfo(long[] assetCategoryIds, String[] assetTagNames) {
		_assetCategoryDisplay = null;
		_assetCategoryIds = assetCategoryIds;
		_assetLinkEntryIds = new long[0];
		_assetTagNames = assetTagNames;
		_fieldInfo = new HashMap<>();
		_kbArticle = null;
	}

	public String exportToCVS(List<AssetVocabulary> assetVocabularies) {
		StringBundler sb = new StringBundler(
			(assetVocabularies.size() + _fields.length) * 2);

		for (String field : _fields) {
			String content = _fieldInfo.get(field);

			if (field.endsWith(FIELD_SECTION) && !_leafNode) {
				content = StringPool.BLANK;
			}

			if (field.equals(FIELD_TAG)) {
				content = StringUtil.merge(
					getAssetTagNames(), StringPool.COMMA);
			}

			sb.append(CSVUtil.encode(content));
			sb.append(StringPool.COMMA);
		}

		for (AssetVocabulary assetVocabulary : assetVocabularies) {
			String assetCategoryNames = getAssetCategoryNames(
				assetVocabulary.getVocabularyId());

			sb.append(CSVUtil.encode(assetCategoryNames));

			sb.append(StringPool.COMMA);
		}

		String cvsString = sb.toString();

		return cvsString.substring(0, cvsString.length() - 1);
	}

	public Element exportToXML(
		Element rootElement, List<AssetVocabulary> assetVocabularies) {

		Element articleElement = rootElement.addElement("article");

		for (String field : _fields) {
			String content = _fieldInfo.get(field);

			if (field.equals(FIELD_TAG)) {
				content = StringUtil.merge(
					getAssetTagNames(), StringPool.COMMA);
			}

			if (Validator.isNotNull(content)) {
				DocUtil.add(articleElement, field, content);
			}
		}

		for (AssetVocabulary assetVocabulary : assetVocabularies) {
			String assetCategoryNames = getAssetCategoryNames(
				assetVocabulary.getVocabularyId());

			if (Validator.isNotNull(assetCategoryNames)) {
				String header = normalize(assetVocabulary.getName());

				DocUtil.add(articleElement, header, assetCategoryNames);
			}
		}

		return articleElement;
	}

	public long[] getAssetCategoryIds() {
		return _assetCategoryIds;
	}

	public long[] getAssetLinkEntryIds() {
		return _assetLinkEntryIds;
	}

	public String[] getAssetTagNames() {
		return _assetTagNames;
	}

	public void setLeafNode(boolean leafNode) {
		_leafNode = leafNode;
	}

	protected String getAssetCategoryNames(long vocabularyId) {
		return _assetCategoryDisplay.renderVocabularyCategories(vocabularyId);
	}

	private static final String[] _fields = new String[] {
		FIELD_SECTION, FIELD_SUBSECTION, FIELD_TITLE, FIELD_URL_TITLE,
		FIELD_AUDIENCE, FIELD_TAG
	};

	private final AssetCategoryDisplay _assetCategoryDisplay;
	private final long[] _assetCategoryIds;
	private final long[] _assetLinkEntryIds;
	private final String[] _assetTagNames;
	private final Map<String, String> _fieldInfo;
	private final KBArticle _kbArticle;
	private boolean _leafNode = true;

}