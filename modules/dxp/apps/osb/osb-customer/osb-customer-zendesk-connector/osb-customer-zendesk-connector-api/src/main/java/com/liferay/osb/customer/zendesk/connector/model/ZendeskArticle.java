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

package com.liferay.osb.customer.zendesk.connector.model;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ZendeskArticle {

	public ZendeskArticle() {
	}

	public void addTranslation(String locale, String title, String body) {
		_locales.add(locale);

		_titleMap.put(locale, title);

		_bodyMap.put(locale, body);
	}

	public String getBody(String locale) {
		return _bodyMap.get(locale);
	}

	public long getId() {
		return _id;
	}

	public String[] getLabelNames() {
		return _labelNames;
	}

	public List<String> getLocales() {
		return _locales;
	}

	public int getPosition() {
		return _position;
	}

	public long getSectionId() {
		return _sectionId;
	}

	public String getTitle(String locale) {
		return _titleMap.get(locale);
	}

	public void setId(long id) {
		_id = id;
	}

	public void setLabelNames(String[] labelNames) {
		_labelNames = labelNames;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public void setSectionId(long sectionId) {
		_sectionId = sectionId;
	}

	public JSONObject toJSONObject(boolean isNew) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject articleJSONObject = JSONFactoryUtil.createJSONObject();

		if (!ArrayUtil.isEmpty(_labelNames)) {
			articleJSONObject.put("label_names", StringUtil.merge(_labelNames));
		}

		if (_position > 0) {
			articleJSONObject.put("position", _position);
		}

		if (isNew) {
			JSONArray translationsJSONArray = JSONFactoryUtil.createJSONArray();

			for (String locale : _locales) {
				JSONObject translationJSONObject = getTranslationJSONObject(
					locale);

				translationsJSONArray.put(translationJSONObject);
			}

			articleJSONObject.put("translations", translationsJSONArray);
		}

		jsonObject.put("article", articleJSONObject);

		return jsonObject;
	}

	public JSONObject toTranslationJSONObject(String locale) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject translationJSONObject = getTranslationJSONObject(locale);

		jsonObject.put("translation", translationJSONObject);

		return jsonObject;
	}

	protected JSONObject getTranslationJSONObject(String locale) {
		JSONObject translationJSONObject = JSONFactoryUtil.createJSONObject();

		translationJSONObject.put("body", _bodyMap.get(locale));
		translationJSONObject.put("locale", locale);
		translationJSONObject.put("title", _titleMap.get(locale));

		return translationJSONObject;
	}

	private Map<String, String> _bodyMap = new HashMap<>();
	private long _id;
	private String[] _labelNames;
	private List<String> _locales = new ArrayList<>();
	private int _position;
	private long _sectionId;
	private Map<String, String> _titleMap = new HashMap<>();

}