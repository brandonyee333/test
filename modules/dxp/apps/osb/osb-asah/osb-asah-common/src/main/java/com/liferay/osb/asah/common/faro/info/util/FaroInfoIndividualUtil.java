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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Rachael Koestartyo
 */
public class FaroInfoIndividualUtil {

	public static JSONArray getIndividualAccountNamesJSONArray(
		ElasticsearchInvoker elasticsearchInvoker, String individualId) {

		Map<String, JSONObject> individualAccountNamesJSONObjects =
			getIndividualAccountNamesJSONObjects(
				elasticsearchInvoker,
				JSONUtil.put(
					elasticsearchInvoker.fetch("individuals", individualId)));

		JSONObject individualAccountNamesJSONObject =
			individualAccountNamesJSONObjects.get(individualId);

		return individualAccountNamesJSONObject.getJSONArray("account-names");
	}

	public static Map<String, JSONObject> getIndividualAccountNamesJSONObjects(
		ElasticsearchInvoker elasticsearchInvoker,
		JSONArray individualsJSONArray) {

		Map<String, JSONObject> individualAccountNamesJSONObjects =
			new HashMap<>();

		Map<String, JSONObject> individualAccountsJSONObjects =
			getIndividualAccountsJSONObjects(
				elasticsearchInvoker, individualsJSONArray);

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			Set<String> accountNames = new HashSet<>();

			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			JSONObject individualAccountsJSONObject =
				individualAccountsJSONObjects.get(
					individualJSONObject.getString("id"));

			JSONArray accountsJSONArray =
				individualAccountsJSONObject.getJSONArray("accounts");

			for (int j = 0; j < accountsJSONArray.length(); j++) {
				JSONObject accountJSONObject = accountsJSONArray.getJSONObject(
					j);

				Object accountName = _getFieldValue(
					accountJSONObject.getJSONObject("organization"),
					"accountName");

				if (accountName == null) {
					continue;
				}

				accountNames.add((String)accountName);
			}

			individualAccountNamesJSONObjects.put(
				individualJSONObject.getString("id"),
				JSONUtil.put("account-names", new JSONArray(accountNames)));
		}

		return individualAccountNamesJSONObjects;
	}

	public static Map<String, JSONObject> getIndividualAccountsJSONObjects(
		ElasticsearchInvoker elasticsearchInvoker,
		JSONArray individualsJSONArray) {

		Map<String, JSONObject> individualAccountsJSONObjects = new HashMap<>();

		Map<String, Set<String>> individualAccountPKs =
			_getIndividualAccountPKs(individualsJSONArray);

		if (individualAccountPKs.isEmpty()) {
			return individualAccountsJSONObjects;
		}

		Map<String, JSONObject> accountJSONObjects = new HashMap<>();

		Collection<Set<String>> values = individualAccountPKs.values();

		Stream<Set<String>> stream = values.stream();

		JSONArray accountJSONArray = elasticsearchInvoker.get(
			"accounts",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"organization.accountId.value",
					stream.flatMap(
						Set::stream
					).collect(
						Collectors.toSet()
					))));

		for (int i = 0; i < accountJSONArray.length(); i++) {
			JSONObject accountJSONObject = accountJSONArray.getJSONObject(i);

			Object accountId = _getFieldValue(
				accountJSONObject.getJSONObject("organization"), "accountId");

			if (accountId == null) {
				continue;
			}

			accountJSONObjects.put((String)accountId, accountJSONObject);
		}

		for (Map.Entry<String, Set<String>> entry :
				individualAccountPKs.entrySet()) {

			JSONArray jsonArray = new JSONArray();

			for (String accountPK : entry.getValue()) {
				JSONObject accountJSONObject = accountJSONObjects.get(
					accountPK);

				if (accountJSONObject != null) {
					jsonArray.put(accountJSONObject);
				}
			}

			individualAccountsJSONObjects.put(
				entry.getKey(), JSONUtil.put("accounts", jsonArray));
		}

		return individualAccountsJSONObjects;
	}

	public static Map<String, String> getIndividualDemographics(
		JSONObject demographicsJSONObject) {

		if (demographicsJSONObject == null) {
			return Collections.emptyMap();
		}

		Map<String, String> demographics = new HashMap<>();

		for (String fieldName : demographicsJSONObject.keySet()) {
			Object fieldValue = _getFieldValue(
				demographicsJSONObject, fieldName);

			if (fieldValue == null) {
				continue;
			}

			demographics.put(fieldName, String.valueOf(fieldValue));
		}

		return demographics;
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
		else if (familyName == null) {
			return String.valueOf(givenName);
		}
		else if (givenName == null) {
			return String.valueOf(familyName);
		}

		return givenName + " " + familyName;
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

	public static Map<String, JSONArray> getIndividualPKsJSONArrays(
		JSONArray dataSourceIndividualPKsJSONArray) {

		if (dataSourceIndividualPKsJSONArray == null) {
			return Collections.emptyMap();
		}

		Map<String, JSONArray> individualPKsJSONArrays = new HashMap<>();

		for (int i = 0; i < dataSourceIndividualPKsJSONArray.length(); i++) {
			JSONObject dataSourceIndividualPKsJSONObject =
				dataSourceIndividualPKsJSONArray.getJSONObject(i);

			individualPKsJSONArrays.put(
				dataSourceIndividualPKsJSONObject.getString("dataSourceId"),
				dataSourceIndividualPKsJSONObject.getJSONArray(
					"individualPKs"));
		}

		return individualPKsJSONArrays;
	}

	public static boolean isKnownIndividual(JSONObject individualJSONObject) {
		JSONObject demographicsJSONObject = individualJSONObject.optJSONObject(
			"demographics");

		if ((demographicsJSONObject != null) &&
			demographicsJSONObject.has("email")) {

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

	private static Map<String, Set<String>> _getIndividualAccountPKs(
		JSONArray individualsJSONArray) {

		Map<String, Set<String>> individualsAccountPKs = new HashMap<>();

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			Set<String> accountPKs = new HashSet<>();

			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			JSONArray dataSourceAccountPKsJSONArray =
				individualJSONObject.getJSONArray("dataSourceAccountPKs");

			for (int j = 0; j < dataSourceAccountPKsJSONArray.length(); j++) {
				JSONObject dataSourceAccountPKsJSONObject =
					dataSourceAccountPKsJSONArray.getJSONObject(j);

				accountPKs.addAll(
					JSONUtil.toStringSet(
						dataSourceAccountPKsJSONObject.optJSONArray(
							"accountPKs")));
			}

			individualsAccountPKs.put(
				individualJSONObject.getString("id"), accountPKs);
		}

		return individualsAccountPKs;
	}

}