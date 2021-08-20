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

package com.liferay.osb.asah.common.faro.info.util;

import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Individual;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Rachael Koestartyo
 */
public class FaroInfoIndividualUtil {

	public static Map<String, String> getIndividualCustomFields(
		JSONObject customJSONObject) {

		if (customJSONObject == null) {
			return Collections.emptyMap();
		}

		Map<String, String> customFields = new HashMap<>();

		for (String fieldName : customJSONObject.keySet()) {
			Object fieldValue = _getFieldValue(customJSONObject, fieldName);

			if (fieldValue == null) {
				continue;
			}

			customFields.put(fieldName, String.valueOf(fieldValue));
		}

		return customFields;
	}

	public static Map<String, String> getIndividualDemographicFields(
		JSONObject demographicsJSONObject) {

		if (demographicsJSONObject == null) {
			return Collections.emptyMap();
		}

		Map<String, String> demographicFields = new HashMap<>();

		for (String fieldName : demographicsJSONObject.keySet()) {
			Object fieldValue = _getFieldValue(
				demographicsJSONObject, fieldName);

			if (fieldValue == null) {
				continue;
			}

			demographicFields.put(fieldName, String.valueOf(fieldValue));
		}

		return demographicFields;
	}

	public static String getIndividualEmail(Individual individual) {
		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Field emailField = stream.filter(
			field ->
				Objects.equals(field.getName(), "email") &&
				!Objects.isNull(field.getValue())
		).findFirst(
		).orElse(
			null
		);

		if (emailField != null) {
			return String.valueOf(emailField.getValue());
		}

		return null;
	}

	public static String getIndividualEmail(JSONObject demographicsJSONObject) {
		if (demographicsJSONObject == null) {
			return null;
		}

		Object emailAddress = _getFieldValue(demographicsJSONObject, "email");

		if (emailAddress != null) {
			return String.valueOf(emailAddress);
		}

		return null;
	}

	public static String getIndividualName(Individual individual) {
		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Field nameField = stream.filter(
			field ->
				Objects.equals(field.getName(), "name") &&
				!Objects.isNull(field.getValue())
		).findFirst(
		).orElse(
			null
		);

		if (nameField != null) {
			return String.valueOf(nameField.getValue());
		}

		stream = fields.stream();

		Field familyNameField = stream.filter(
			field ->
				Objects.equals(field.getName(), "familyName") &&
				!Objects.isNull(field.getValue())
		).findFirst(
		).orElse(
			null
		);

		stream = fields.stream();

		Field givenNameField = stream.filter(
			field ->
				Objects.equals(field.getName(), "givenName") &&
				!Objects.isNull(field.getValue())
		).findFirst(
		).orElse(
			null
		);

		if ((familyNameField == null) && (givenNameField == null)) {
			return null;
		}

		if (familyNameField == null) {
			return String.valueOf(givenNameField.getValue());
		}

		if (givenNameField == null) {
			return String.valueOf(familyNameField.getValue());
		}

		return givenNameField.getValue() + " " + familyNameField.getValue();
	}

	public static String getIndividualName(JSONObject demographicsJSONObject) {
		if (demographicsJSONObject == null) {
			return null;
		}

		Object name = _getFieldValue(demographicsJSONObject, "name");

		if (name != null) {
			return String.valueOf(name);
		}

		Object familyName = _getFieldValue(
			demographicsJSONObject, "familyName");
		Object givenName = _getFieldValue(demographicsJSONObject, "givenName");

		if ((familyName == null) && (givenName == null)) {
			return null;
		}

		if (familyName == null) {
			return String.valueOf(givenName);
		}

		if (givenName == null) {
			return String.valueOf(familyName);
		}

		return givenName + " " + familyName;
	}

	public static Map<Long, Set<String>> getIndividualPKs(
		Set<Individual.DataSourceIndividualPK> dataSourceIndividualPKs) {

		if (CollectionUtils.isEmpty(dataSourceIndividualPKs)) {
			return Collections.emptyMap();
		}

		Map<Long, Set<String>> individualPKs = new HashMap<>();

		for (Individual.DataSourceIndividualPK dataSourceIndividualPK :
				dataSourceIndividualPKs) {

			individualPKs.put(
				dataSourceIndividualPK.getDataSourceId(),
				dataSourceIndividualPK.getIndividualPKs());
		}

		return individualPKs;
	}

	public static JSONArray getIndividualPKsJSONArray(
		String dataSourceId, JSONArray dataSourceIndividualPKsJSONArray) {

		for (int i = 0; i < dataSourceIndividualPKsJSONArray.length(); i++) {
			JSONObject dataSourceIndividualPKsJSONObject =
				dataSourceIndividualPKsJSONArray.getJSONObject(i);

			if (Objects.equals(
					dataSourceIndividualPKsJSONObject.getString("dataSourceId"),
					dataSourceId)) {

				return dataSourceIndividualPKsJSONObject.getJSONArray(
					"individualPKs");
			}
		}

		return new JSONArray();
	}

	public static boolean isKnownIndividual(Individual individual) {
		Set<Field> fields = individual.getFields();

		Stream<Field> stream = fields.stream();

		Field emailField = stream.filter(
			field ->
				Objects.equals(field.getName(), "email") &&
				!Objects.isNull(field.getValue())
		).findFirst(
		).orElse(
			null
		);

		if (emailField != null) {
			return true;
		}

		return false;
	}

	private static Object _getFieldValue(
		JSONObject contextJSONObject, String fieldName) {

		JSONArray fieldJSONArray = contextJSONObject.optJSONArray(fieldName);

		if (fieldJSONArray == null) {
			return null;
		}

		JSONObject fieldJSONObject = fieldJSONArray.optJSONObject(0);

		if (fieldJSONObject == null) {
			return null;
		}

		return fieldJSONObject.opt("value");
	}

}