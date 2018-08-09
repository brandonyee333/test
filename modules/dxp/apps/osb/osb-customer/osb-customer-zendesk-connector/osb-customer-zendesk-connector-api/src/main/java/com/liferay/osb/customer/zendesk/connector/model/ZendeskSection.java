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

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class ZendeskSection {

	public ZendeskSection() {
	}

	public long getCategoryId() {
		return _categoryId;
	}

	public String getDescription() {
		return _description;
	}

	public long getId() {
		return _id;
	}

	public String getLocale() {
		return _locale;
	}

	public String getName() {
		return _name;
	}

	public int getPosition() {
		return _position;
	}

	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setId(long id) {
		_id = id;
	}

	public void setLocale(String locale) {
		_locale = locale;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public JSONObject toJSONObject(boolean isNew) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject();

		if (isNew) {
			fieldsJSONObject.put("category_id", _categoryId);
		}

		if (isNew && Validator.isNotNull(_description)) {
			fieldsJSONObject.put("description", _description);
		}

		if (isNew) {
			fieldsJSONObject.put("locale", _locale);
		}

		if (isNew) {
			fieldsJSONObject.put("name", _name);
		}

		if (_position > 0) {
			fieldsJSONObject.put("position", _position);
		}

		jsonObject.put("section", fieldsJSONObject);

		return jsonObject;
	}

	private long _categoryId;
	private String _description;
	private long _id;
	private String _locale;
	private String _name;
	private int _position;

}