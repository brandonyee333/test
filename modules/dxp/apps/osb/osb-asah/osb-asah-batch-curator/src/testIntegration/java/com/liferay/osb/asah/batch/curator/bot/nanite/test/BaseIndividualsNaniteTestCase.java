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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public abstract class BaseIndividualsNaniteTestCase
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		_user1PK = generateUserPK();
		_user2PK = generateUserPK();
	}

	protected void addDataSource(String type) throws Exception {
		if (type.equals("CSV")) {
			_dataSourceJSONObject = _objectMapper.convertValue(
				_dataSourceDog.addDataSource(
					FaroInfoTestUtil.buildCSVDataSource()),
				JSONObject.class);
		}
		else if (type.equals("LIFERAY")) {
			_dataSourceJSONObject = _objectMapper.convertValue(
				_dataSourceDog.addDataSource(
					FaroInfoTestUtil.buildLiferayDataSource()),
				JSONObject.class);
		}
		else {
			throw new Exception("Unsupported data source type " + type);
		}
	}

	protected void addEmailFieldMapping() {
	}

	protected void addStandardFieldMappings() {
	}

	protected void assertDemographicsJSONObject(
		String dataSourceId, String fieldName, String fieldType,
		JSONObject individualJSONObject, String sourceName, String value) {

		_assertDemographicsJSONObject(
			fieldName, individualJSONObject, "dataSourceId", dataSourceId);
		_assertDemographicsJSONObject(
			fieldName, individualJSONObject, "fieldType", fieldType);
		_assertDemographicsJSONObject(
			fieldName, individualJSONObject, "name", fieldName);
		_assertDemographicsJSONObject(
			fieldName, individualJSONObject, "sourceName", sourceName);
		_assertDemographicsJSONObject(
			fieldName, individualJSONObject, "value", value);
	}

	protected void assertIndividuals() {
		JSONArray individualsJSONArray = faroInfoElasticsearchInvoker.get(
			"individuals");

		Assertions.assertEquals(2, individualsJSONArray.length());

		boolean individual1Exists = false;
		boolean individual2Exists = false;

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			JSONArray dataSourceIndividualPKArray =
				FaroInfoIndividualUtil.getIndividualPKsJSONArray(
					String.valueOf(getDataSourceId()),
					individualJSONObject.getJSONArray(
						"dataSourceIndividualPKs"));

			String individualPK = dataSourceIndividualPKArray.getString(0);

			if (Objects.equals(_user1PK, individualPK)) {
				individual1Exists = true;

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "email",
					"http://schema.org/email", individualJSONObject,
					getEmailDataSourceFieldName(), _INDIVIDUAL_1_EMAIL);

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "familyName", "Text",
					individualJSONObject, "lastName",
					_INDIVIDUAL_1_FAMILY_NAME);

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "givenName", "Text",
					individualJSONObject, "firstName",
					_INDIVIDUAL_1_GIVEN_NAME);

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "jobTitle", "Text",
					individualJSONObject, "jobTitle", _INDIVIDUAL_1_JOB_TITLE);
			}
			else if (Objects.equals(_user2PK, individualPK)) {
				individual2Exists = true;

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "email",
					"http://schema.org/email", individualJSONObject,
					getEmailDataSourceFieldName(), _INDIVIDUAL_2_EMAIL);

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "familyName", "Text",
					individualJSONObject, "lastName",
					_INDIVIDUAL_2_FAMILY_NAME);

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "givenName", "Text",
					individualJSONObject, "firstName",
					_INDIVIDUAL_2_GIVEN_NAME);

				assertDemographicsJSONObject(
					String.valueOf(getDataSourceId()), "jobTitle", "Text",
					individualJSONObject, "jobTitle", _INDIVIDUAL_2_JOB_TITLE);
			}
		}

		Assertions.assertTrue(individual1Exists);
		Assertions.assertTrue(individual2Exists);
	}

	protected abstract String generateUserPK();

	protected Long getDataSourceId() {
		if (_dataSourceJSONObject == null) {
			return null;
		}

		return Long.valueOf(_dataSourceJSONObject.getString("id"));
	}

	protected abstract String getEmailDataSourceFieldName();

	protected String getIndividual1Email() {
		return _INDIVIDUAL_1_EMAIL;
	}

	protected Map<String, Object> getIndividual1FieldsMap() {
		return new HashMap<String, Object>() {
			{
				put(getEmailDataSourceFieldName(), _INDIVIDUAL_1_EMAIL);
				put("firstName", _INDIVIDUAL_1_GIVEN_NAME);
				put("jobTitle", _INDIVIDUAL_1_JOB_TITLE);
				put("lastName", _INDIVIDUAL_1_FAMILY_NAME);
				put("subscription", _SUBSCRIPTION);
			}
		};
	}

	protected long getIndividual1UserId() {
		return _INDIVIDUAL_1_USER_ID;
	}

	protected String getIndividual2Email() {
		return _INDIVIDUAL_2_EMAIL;
	}

	protected Map<String, Object> getIndividual2FieldsMap() {
		return new HashMap<String, Object>() {
			{
				put(getEmailDataSourceFieldName(), _INDIVIDUAL_2_EMAIL);
				put("firstName", _INDIVIDUAL_2_GIVEN_NAME);
				put("jobTitle", _INDIVIDUAL_2_JOB_TITLE);
				put("lastName", _INDIVIDUAL_2_FAMILY_NAME);
				put("subscription", _SUBSCRIPTION);
			}
		};
	}

	protected long getIndividual2UserId() {
		return _INDIVIDUAL_2_USER_ID;
	}

	protected String getSubscription() {
		return _SUBSCRIPTION;
	}

	protected String getUser1PK() {
		return _user1PK;
	}

	protected String getUser2PK() {
		return _user2PK;
	}

	private void _assertDemographicsJSONObject(
		String fieldName, JSONObject individualJSONObject, String key,
		String value) {

		Assertions.assertEquals(
			value,
			JSONUtil.getValue(
				individualJSONObject, "JSONObject/demographics",
				"JSONArray/" + fieldName, "Object/0", "Object/" + key));
	}

	private static final String _INDIVIDUAL_1_EMAIL =
		RandomTestUtil.randomEmailAddress();

	private static final String _INDIVIDUAL_1_FAMILY_NAME =
		RandomTestUtil.randomString();

	private static final String _INDIVIDUAL_1_GIVEN_NAME =
		RandomTestUtil.randomString();

	private static final String _INDIVIDUAL_1_JOB_TITLE =
		RandomTestUtil.randomString();

	private static final long _INDIVIDUAL_1_USER_ID =
		RandomTestUtil.randomNumber();

	private static final String _INDIVIDUAL_2_EMAIL =
		RandomTestUtil.randomEmailAddress();

	private static final String _INDIVIDUAL_2_FAMILY_NAME =
		RandomTestUtil.randomString();

	private static final String _INDIVIDUAL_2_GIVEN_NAME =
		RandomTestUtil.randomString();

	private static final String _INDIVIDUAL_2_JOB_TITLE =
		RandomTestUtil.randomString();

	private static final long _INDIVIDUAL_2_USER_ID =
		RandomTestUtil.randomNumber();

	private static final String _SUBSCRIPTION = RandomTestUtil.randomString();

	@Autowired
	private DataSourceDog _dataSourceDog;

	private JSONObject _dataSourceJSONObject;

	@Autowired
	private ObjectMapper _objectMapper;

	private String _user1PK;
	private String _user2PK;

}