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

package com.liferay.osb.customer.zendesk.model;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 * @author Kyle Bischof
 */
public class ZendeskUser {

	public ZendeskUser() {
	}

	public String getEmail() {
		return _email;
	}

	public String getExternalId() {
		return _externalId;
	}

	public String getLocale() {
		return _locale;
	}

	public String getName() {
		return _name;
	}

	public String getOrganizationName() {
		return _organizationName;
	}

	public JSONArray getTags() {
		return _tags;
	}
	
	public long getZendeskUserId() {
		return _zendeskUserId;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public void setExternalId(String externalId) {
		_externalId = externalId;
	}

	public void setLocale(String locale) {
		_locale = locale;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrganizationName(String organizationName) {
		_organizationName = organizationName;
	}

	public void setTags(JSONArray tags) {
		_tags = tags;
	}
	
	public void setZendeskUserId(long zendeskUserId) {
		_zendeskUserId = zendeskUserId;
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject();

		fieldsJSONObject.put("name", _name);

		if (Validator.isNotNull(_email)) {
			fieldsJSONObject.put("email", _email);
		}

		if (Validator.isNotNull(_externalId)) {
			fieldsJSONObject.put("external_id", _externalId);
		}

		if (Validator.isNotNull(_locale)) {
			fieldsJSONObject.put("locale", _locale);
		}

		JSONObject organizationJSONObject = JSONFactoryUtil.createJSONObject();

		organizationJSONObject.put("name", _organizationName);

		fieldsJSONObject.put("organization", organizationJSONObject);

		if (_tags.length() > 0) {
			fieldsJSONObject.put("tags", _tags);
		}

		jsonObject.put("user", fieldsJSONObject);

		return jsonObject;
	}

	private String _email;
	private String _externalId;
	private String _locale;
	private String _name;
	private String _organizationName;
	private JSONArray _tags = JSONFactoryUtil.createJSONArray();
	private long _zendeskUserId;

}