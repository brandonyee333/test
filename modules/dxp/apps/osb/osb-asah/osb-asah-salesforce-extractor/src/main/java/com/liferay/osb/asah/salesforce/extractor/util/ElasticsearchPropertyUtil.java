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

package com.liferay.osb.asah.salesforce.extractor.util;

import com.liferay.osb.asah.common.json.JSONUtil;

import com.sforce.soap.partner.Field;
import com.sforce.soap.partner.FieldType;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;

import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class ElasticsearchPropertyUtil {

	public static String getElasticsearchPropertyType(Field salesforceField) {
		FieldType salesforceFieldType = salesforceField.getType();

		String salesforceFieldTypeName = String.valueOf(salesforceFieldType);

		String elasticSearchPropertyType = _elasticsearchPropertyTypes.get(
			salesforceFieldTypeName);

		if (salesforceFieldTypeName.equals("textarea") &&
			(salesforceField.getLength() > 32766)) {

			elasticSearchPropertyType = "binary";
		}

		if (elasticSearchPropertyType != null) {
			return elasticSearchPropertyType;
		}

		if (_log.isWarnEnabled()) {
			_log.error(
				"Invalid Salesforce field type " + salesforceFieldTypeName);
		}

		return "string";
	}

	public static Object toJSONObjectValue(
		Field salesforceField, Object value) {

		String salesforceFieldName = salesforceField.getName();

		if (salesforceFieldName.equals("NewValue") ||
			salesforceFieldName.equals("OldValue")) {

			JSONObject jsonObject = JSONUtil.put(
				"value", String.valueOf(value));

			return jsonObject.toString();
		}

		String elasticsearchPropertyType = getElasticsearchPropertyType(
			salesforceField);

		if (elasticsearchPropertyType.equals("binary")) {
			String stringValue = String.valueOf(value);

			return new ByteArrayInputStream(
				stringValue.getBytes(StandardCharsets.UTF_8));
		}

		if (elasticsearchPropertyType.equals("boolean")) {
			return Boolean.valueOf(String.valueOf(value));
		}

		if (elasticsearchPropertyType.equals("double")) {
			BigDecimal bigDecimal = new BigDecimal(String.valueOf(value));

			return bigDecimal.toPlainString();
		}

		return String.valueOf(value);
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchPropertyUtil.class);

	private static final Map<String, String> _elasticsearchPropertyTypes =
		new HashMap<String, String>() {
			{
				put("anyType", "keyword");
				put("boolean", "boolean");
				put("combobox", "keyword");
				put("currency", "double");
				put("date", "keyword");
				put("datetime", "keyword");
				put("double", "double");
				put("email", "keyword");
				put("encryptedstring", "keyword");
				put("id", "keyword");
				put("int", "integer");
				put("multipicklist", "keyword");
				put("percent", "double");
				put("phone", "keyword");
				put("picklist", "keyword");
				put("reference", "keyword");
				put("string", "keyword");
				put("textarea", "keyword");
				put("time", "keyword");
				put("url", "keyword");
			}
		};

}