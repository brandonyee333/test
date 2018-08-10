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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class ZendeskSection {

	public ZendeskSection() {
	}

	public void addTranslation(String locale, String name, String description) {
		_locales.add(locale);

		_nameMap.put(locale, name);

		_descriptionMap.put(locale, description);
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public String getDescription(String locale) {
		return _descriptionMap.get(locale);
	}

	public long getId() {
		return _id;
	}

	public List<String> getLocales() {
		return _locales;
	}

	public String getName(String locale) {
		return _nameMap.get(locale);
	}

	public int getPosition() {
		return _position;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public JSONObject toJSONObject(boolean isNew) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject sectionJSONObject = JSONFactoryUtil.createJSONObject();

		if (isNew) {
			sectionJSONObject.put("category_id", _categoryId);
		}

		if (_position > 0) {
			sectionJSONObject.put("position", _position);
		}

		if (isNew) {
			JSONArray translationsJSONArray = JSONFactoryUtil.createJSONArray();

			for (String locale : _locales) {
				JSONObject translationJSONObject = getTranslationJSONObject(
					locale);

				translationsJSONArray.put(translationJSONObject);
			}

			sectionJSONObject.put("translations", translationsJSONArray);
		}

		jsonObject.put("section", sectionJSONObject);

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

		translationJSONObject.put("body", _descriptionMap.get(locale));
		translationJSONObject.put("locale", locale);
		translationJSONObject.put("title", _nameMap.get(locale));

		return translationJSONObject;
	}

	private long _categoryId;
	private Map<String, String> _descriptionMap = new HashMap<>();
	private long _id;
	private List<String> _locales = new ArrayList<>();
	private Map<String, String> _nameMap = new HashMap<>();
	private int _position;

}