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

import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vishal Reddy
 */
public abstract class BaseIndividualsNaniteTestCase extends BaseNaniteTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

		_individual1PK = generateIndividualPK();
		_individual2PK = generateIndividualPK();
	}

	protected void addDataSource(String type) throws Exception {
		if (type.equals("CSV")) {
			_dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
				FaroInfoTestUtil.buildCSVDataSourceJSONObject());
		}
		else if (type.equals("LIFERAY")) {
			_dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject());
		}
		else if (type.equals("SALESFORCE")) {
			_dataSourceJSONObject = _faroInfoDataSourceDog.addDataSource(
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());
		}
		else {
			throw new Exception("Unsupported data source type " + type);
		}
	}

	protected void addEmailFieldMapping() {
		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
				getDataSourceId(), getEmailDataSourceFieldName(), "email",
				"http://schema.org/email"));
	}

	protected void addStandardFieldMappings() {
		faroInfoElasticsearchInvoker.add(
			"field-mappings",
			JSONUtil.putAll(
				FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
					getDataSourceId(), "lastName", "familyName", "Text"),
				FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
					getDataSourceId(), "firstName", "givenName", "Text"),
				FaroInfoTestUtil.buildIndividualFieldMappingJSONObject(
					getDataSourceId(), "jobTitle", "jobTitle", "Text")));
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

		Assert.assertEquals(2, individualsJSONArray.length());

		boolean individual1Exists = false;
		boolean individual2Exists = false;

		for (int i = 0; i < individualsJSONArray.length(); i++) {
			JSONObject individualJSONObject =
				individualsJSONArray.getJSONObject(i);

			JSONArray individualPKsJSONArray =
				FaroInfoIndividualUtil.getIndividualPKsJSONArray(
					getDataSourceId(),
					individualJSONObject.getJSONArray(
						"dataSourceIndividualPKs"));

			String individualPK = individualPKsJSONArray.getString(0);

			if (Objects.equals(_individual1PK, individualPK)) {
				individual1Exists = true;

				assertDemographicsJSONObject(
					getDataSourceId(), "email", "http://schema.org/email",
					individualJSONObject, getEmailDataSourceFieldName(),
					_INDIVIDUAL_1_EMAIL);

				assertDemographicsJSONObject(
					getDataSourceId(), "familyName", "Text",
					individualJSONObject, "lastName",
					_INDIVIDUAL_1_FAMILY_NAME);

				assertDemographicsJSONObject(
					getDataSourceId(), "givenName", "Text",
					individualJSONObject, "firstName",
					_INDIVIDUAL_1_GIVEN_NAME);

				assertDemographicsJSONObject(
					getDataSourceId(), "jobTitle", "Text", individualJSONObject,
					"jobTitle", _INDIVIDUAL_1_JOB_TITLE);
			}
			else if (Objects.equals(_individual2PK, individualPK)) {
				individual2Exists = true;

				assertDemographicsJSONObject(
					getDataSourceId(), "email", "http://schema.org/email",
					individualJSONObject, getEmailDataSourceFieldName(),
					_INDIVIDUAL_2_EMAIL);

				assertDemographicsJSONObject(
					getDataSourceId(), "familyName", "Text",
					individualJSONObject, "lastName",
					_INDIVIDUAL_2_FAMILY_NAME);

				assertDemographicsJSONObject(
					getDataSourceId(), "givenName", "Text",
					individualJSONObject, "firstName",
					_INDIVIDUAL_2_GIVEN_NAME);

				assertDemographicsJSONObject(
					getDataSourceId(), "jobTitle", "Text", individualJSONObject,
					"jobTitle", _INDIVIDUAL_2_JOB_TITLE);
			}
		}

		Assert.assertTrue(individual1Exists);
		Assert.assertTrue(individual2Exists);
	}

	protected abstract String generateIndividualPK();

	protected String getDataSourceId() {
		if (_dataSourceJSONObject == null) {
			return null;
		}

		return _dataSourceJSONObject.getString("id");
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

	protected String getIndividual1PK() {
		return _individual1PK;
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

	protected String getIndividual2PK() {
		return _individual2PK;
	}

	protected long getIndividual2UserId() {
		return _INDIVIDUAL_2_USER_ID;
	}

	protected String getSubscription() {
		return _SUBSCRIPTION;
	}

	private void _assertDemographicsJSONObject(
		String fieldName, JSONObject individualJSONObject, String key,
		String value) {

		Assert.assertEquals(
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

	private JSONObject _dataSourceJSONObject;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	private String _individual1PK;
	private String _individual2PK;

}