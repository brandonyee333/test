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

package com.liferay.osb.asah.salesforce.extractor.bot.nanite;

import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoOSBAsahTaskDog;
import com.liferay.osb.asah.common.json.JSONArrayPaginator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.util.TimeUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 * @author Michael Bowerman
 */
public class SalesforceExtractorIndividualsNanite implements Nanite {

	public SalesforceExtractorIndividualsNanite(
		SalesforceExtractorConfiguration salesforceExtractorConfiguration) {

		_salesforceExtractorConfiguration = salesforceExtractorConfiguration;

		_osbAsahDataSourceIdTermQueryBuilder = QueryBuilders.termQuery(
			"osbAsahDataSourceId",
			_salesforceExtractorConfiguration.getDataSourceId());
	}

	@PostConstruct
	public void init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Override
	public void run() throws Exception {
		_runLogger.log(
			_salesforceExtractorConfiguration.getDataSourceId(), this,
			"STARTED", _elasticsearchInvoker, "totalOperations",
			_elasticsearchInvoker.count(
				"audit-events",
				QueryBuilders.termsQuery("typeName", "Lead", "Contact")));

		try {
			_run();

			_runLogger.log(
				_salesforceExtractorConfiguration.getDataSourceId(), this,
				"COMPLETED", _elasticsearchInvoker);

			_faroInfoOSBAsahTaskDog.addOSBAsahTask(
				"SalesforceIndividualsNanite",
				JSONUtil.put(
					"dataSourceId",
					_salesforceExtractorConfiguration.getDataSourceId()
				).put(
					"type", "audit-events"
				));
		}
		catch (Exception e) {
			_runLogger.log(
				_salesforceExtractorConfiguration.getDataSourceId(), this,
				"FAILED", _elasticsearchInvoker);

			throw e;
		}
	}

	private void _addAuditEvent(
		String eventType, JSONObject jsonObject, String recordId,
		String typeName) {

		JSONObject auditEventJSONObject = JSONUtil.put(
			"additionalInfo", jsonObject
		).put(
			"dateCreated", DateUtil.newDateString()
		).put(
			"eventType", eventType
		).put(
			"osbAsahDataSourceId",
			_salesforceExtractorConfiguration.getDataSourceId()
		).put(
			"recordId", recordId
		).put(
			"typeName", typeName
		);

		try {
			_elasticsearchInvoker.add("audit-events", auditEventJSONObject);
		}
		catch (Exception e) {
			_log.error(
				"Unable to add audit event with JSON " +
					auditEventJSONObject.toString(),
				e);
		}
	}

	private JSONObject _buildIndividualJSONObject(
		JSONArray accountPKsJSONArray, String birthDate, String city,
		String company, String contactId, String country,
		String currencyIsoCode, String department, String description,
		String doNotCall, String email, String fax, String firstName,
		String fullName, String industry, String lastName, String leadId,
		String middleName, String mobilePhone, String phone, String postalCode,
		String salutation, String state, String street, String suffix,
		String title) {

		return JSONUtil.put(
			"accountPKs", accountPKsJSONArray
		).put(
			"birthDate", birthDate
		).put(
			"city", city
		).put(
			"company", company
		).put(
			"contactId", contactId
		).put(
			"country", country
		).put(
			"currencyIsoCode", currencyIsoCode
		).put(
			"department", department
		).put(
			"description", description
		).put(
			"doNotCall", doNotCall
		).put(
			"email", email
		).put(
			"fax", fax
		).put(
			"firstName", firstName
		).put(
			"fullName", fullName
		).put(
			"industry", industry
		).put(
			"lastName", lastName
		).put(
			"leadId", leadId
		).put(
			"middleName", middleName
		).put(
			"mobilePhone", mobilePhone
		).put(
			"phone", phone
		).put(
			"postalCode", postalCode
		).put(
			"salutation", salutation
		).put(
			"state", state
		).put(
			"street", street
		).put(
			"suffix", suffix
		).put(
			"title", title
		);
	}

	private JSONObject _buildIndividualJSONObject(
		JSONObject jsonObject, String typeName) {

		JSONObject individualJSONObject = new JSONObject();

		if (typeName.equals("Contact")) {
			String accountId = jsonObject.optString("AccountId", null);

			JSONObject accountJSONObject = new JSONObject();

			if (accountId != null) {
				accountJSONObject = _elasticsearchInvoker.get(
					"Account", accountId);
			}

			String email = jsonObject.optString("Email", null);

			individualJSONObject = _buildIndividualJSONObject(
				_getAccountPKsJSONArray(accountId, email),
				jsonObject.optString("Birthdate", null),
				jsonObject.optString("MailingCity", null),
				accountJSONObject.optString("Name", null),
				jsonObject.getString("id"),
				jsonObject.optString("MailingCountry", null),
				jsonObject.optString("CurrencyIsoCode", null),
				jsonObject.optString("Department", null),
				jsonObject.optString("Description", null),
				jsonObject.optString("DoNotCall", null), email,
				jsonObject.optString("Fax", null),
				jsonObject.optString("FirstName", null),
				jsonObject.optString("Name", null), null,
				jsonObject.optString("LastName", null), null,
				jsonObject.optString("MiddleName", null),
				jsonObject.optString("MobilePhone", null),
				jsonObject.optString("Phone", null),
				jsonObject.optString("MailingPostalCode", null),
				jsonObject.optString("Salutation", null),
				jsonObject.optString("MailingState", null),
				jsonObject.optString("MailingStreet", null),
				jsonObject.optString("Suffix", null),
				jsonObject.optString("Title", null));
		}
		else if (typeName.equals("Lead")) {
			individualJSONObject = _buildIndividualJSONObject(
				null, null, jsonObject.optString("City", null),
				jsonObject.optString("Company", null), null,
				jsonObject.optString("Country", null),
				jsonObject.optString("CurrencyIsoCode", null), null,
				jsonObject.optString("Description", null), null,
				jsonObject.optString("Email", null),
				jsonObject.optString("Fax", null),
				jsonObject.optString("FirstName", null),
				jsonObject.optString("Name", null),
				jsonObject.optString("Industry", null),
				jsonObject.optString("LastName", null),
				jsonObject.getString("id"),
				jsonObject.optString("MiddleName", null),
				jsonObject.optString("MobilePhone", null),
				jsonObject.optString("Phone", null),
				jsonObject.optString("PostalCode", null),
				jsonObject.optString("Salutation", null),
				jsonObject.optString("State", null),
				jsonObject.optString("Street", null),
				jsonObject.optString("Suffix", null),
				jsonObject.optString("Title", null));
		}

		individualJSONObject.put("modifiedDate", DateUtil.newDateString());
		individualJSONObject.put(
			"osbAsahDataSourceId",
			_salesforceExtractorConfiguration.getDataSourceId());

		return individualJSONObject;
	}

	private JSONArray _getAccountPKsJSONArray(String accountId, String email) {
		if (email == null) {
			if (accountId == null) {
				return null;
			}

			return JSONUtil.put(accountId);
		}

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("accountPKs");

		termsValuesSourceBuilder.field("AccountId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite", Collections.singletonList(termsValuesSourceBuilder)
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery("Email", email)
			));

		searchSourceBuilder.size(0);

		JSONArray jsonArray = new JSONArray();

		while (true) {
			SearchResponse searchResponse = _elasticsearchInvoker.search(
				"Contact", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				jsonArray.put((String)keys.get("accountPKs"));
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return jsonArray;
	}

	private JSONObject _mergeContactsAndLeads(String email) {
		JSONObject individualJSONObject = null;

		JSONArray contactsJSONArray = _elasticsearchInvoker.get(
			"Contact",
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery("Email", email)
			));

		JSONArray leadsJSONArray = _elasticsearchInvoker.get(
			"Lead",
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery("Email", email)
			));

		for (int i = 0; i < contactsJSONArray.length(); i++) {
			JSONObject contactJSONObject = contactsJSONArray.getJSONObject(i);

			individualJSONObject = JSONUtil.merge(
				individualJSONObject,
				_buildIndividualJSONObject(contactJSONObject, "Contact"));
		}

		for (int i = 0; i < leadsJSONArray.length(); i++) {
			JSONObject leadJSONObject = leadsJSONArray.getJSONObject(i);

			individualJSONObject = JSONUtil.merge(
				_buildIndividualJSONObject(leadJSONObject, "Lead"),
				individualJSONObject);
		}

		return individualJSONObject;
	}

	private void _process(JSONObject auditEventJSONObject) {
		JSONObject additionalInfoJSONObject =
			auditEventJSONObject.getJSONObject("additionalInfo");

		String email = additionalInfoJSONObject.optString("Email");

		JSONObject oldIndividualJSONObject = _elasticsearchInvoker.fetch(
			"individuals",
			BoolQueryBuilderUtil.filter(
				_osbAsahDataSourceIdTermQueryBuilder
			).filter(
				QueryBuilders.termQuery("email", email)
			));

		JSONObject newIndividualJSONObject = null;

		if (!Objects.equals(
				auditEventJSONObject.getString("eventType"), "DELETE")) {

			String typeName = auditEventJSONObject.getString("typeName");

			JSONObject individualJSONObject = _buildIndividualJSONObject(
				_elasticsearchInvoker.get(
					typeName, auditEventJSONObject.getString("recordId")),
				typeName);

			if (oldIndividualJSONObject == null) {
				newIndividualJSONObject = individualJSONObject;
			}
			else {
				if (typeName.equals("Contact")) {
					newIndividualJSONObject = JSONUtil.merge(
						oldIndividualJSONObject, individualJSONObject);
				}
				else if (typeName.equals("Lead")) {
					newIndividualJSONObject = JSONUtil.merge(
						individualJSONObject, oldIndividualJSONObject);
				}
			}
		}
		else if (oldIndividualJSONObject != null) {
			newIndividualJSONObject = _mergeContactsAndLeads(email);

			if (newIndividualJSONObject == null) {
				_elasticsearchInvoker.delete(
					"individuals", oldIndividualJSONObject);

				_addAuditEvent(
					"DELETE", oldIndividualJSONObject,
					oldIndividualJSONObject.getString("id"), "individuals");
			}
		}

		if ((newIndividualJSONObject != null) &&
			(oldIndividualJSONObject != null)) {

			newIndividualJSONObject.put(
				"id", oldIndividualJSONObject.getString("id"));
		}

		if (newIndividualJSONObject != null) {
			JSONObject jsonObject = _elasticsearchInvoker.save(
				"individuals", newIndividualJSONObject);

			_addAuditEvent(
				"UPDATE", jsonObject, jsonObject.getString("id"),
				"individuals");
		}

		_elasticsearchInvoker.delete("audit-events", auditEventJSONObject);
	}

	private void _run() throws Exception {
		for (String typeName : new String[] {"Lead", "Contact"}) {
			long time = System.currentTimeMillis();

			if (_log.isInfoEnabled()) {
				_log.info("Curate " + typeName);
			}

			new JSONArrayPaginator() {

				@Override
				protected JSONArray paginate(int start, int end)
					throws Exception {

					JSONArray jsonArray = new JSONArray(
						_elasticsearchInvoker.get(
							"audit-events",
							searchSourceBuilder -> {
								searchSourceBuilder.from(start);
								searchSourceBuilder.query(
									BoolQueryBuilderUtil.filter(
										_osbAsahDataSourceIdTermQueryBuilder
									).filter(
										QueryBuilders.termQuery(
											"typeName", typeName)
									));
								searchSourceBuilder.size(end - start);
								searchSourceBuilder.sort("id");
							}));

					for (int i = 0; i < jsonArray.length(); i++) {
						_process(jsonArray.getJSONObject(i));
					}

					processedCount += jsonArray.length();

					if (_log.isInfoEnabled()) {
						_log.info(
							"Curated " + processedCount + " " + typeName +
								" records");
					}

					return jsonArray;
				}

			};

			if (_log.isInfoEnabled()) {
				_log.info(
					"Curated " + typeName + " in " + TimeUtil.format(time));
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceExtractorIndividualsNanite.class);

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoOSBAsahTaskDog _faroInfoOSBAsahTaskDog;

	private final TermQueryBuilder _osbAsahDataSourceIdTermQueryBuilder;

	@Autowired
	private RunLogger _runLogger;

	private final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration;

}