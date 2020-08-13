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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.Account;
import com.liferay.osb.asah.backend.model.FieldMapping;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AccountDog {

	public Account getAccount(String id) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"accounts", _faroInfoElasticsearchInvoker,
			_buildSearchSourceBuilder(
				_getAccountOrganizationFetchSourceExcludes(),
				QueryBuilders.termQuery("id", id), 1, 0));

		if (!HitsUtil.hasHits(searchHits)) {
			throw new OSBAsahException(
				HttpStatus.BAD_REQUEST, "There is no account with ID " + id);
		}

		SearchHit[] searchHitArray = searchHits.getHits();

		return _mapAccount(searchHitArray[0]);
	}

	public ResultBag<Account> getAccountResultBag(int size, int start) {
		SearchHits searchHits = _dataDog.querySearchHits(
			"accounts", _faroInfoElasticsearchInvoker,
			_buildSearchSourceBuilder(
				_getAccountOrganizationFetchSourceExcludes(), null, size,
				start));

		return DogUtil.createResultBag(this::_mapAccount, searchHits);
	}

	public ResultBag<Account> getAccountResultBag(
		Object[] searchAfter, int size, SortBuilder<?> sortBuilder) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"accounts", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				_getAccountOrganizationFetchSourceExcludes(), null, searchAfter,
				size, sortBuilder));

		return DogUtil.createResultBag(this::_mapAccount, searchHits);
	}

	private static Object _getPropertyValue(
		JSONObject organizationJSONObject, String fieldName) {

		JSONArray propertyJSONArray = organizationJSONObject.optJSONArray(
			fieldName);

		if (propertyJSONArray == null) {
			return null;
		}

		JSONObject propertyJSONObject = propertyJSONArray.optJSONObject(0);

		if (propertyJSONObject == null) {
			return null;
		}

		return propertyJSONObject.get("value");
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		String[] fetchSourceExcludes, QueryBuilder queryBuilder, int size,
		int start) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.fetchSource(null, fetchSourceExcludes);
		searchSourceBuilder.from(start);

		if (queryBuilder != null) {
			searchSourceBuilder.query(queryBuilder);
		}

		searchSourceBuilder.size(size);

		return searchSourceBuilder;
	}

	private String[] _getAccountOrganizationFetchSourceExcludes() {
		ResultBag<FieldMapping> fieldMappingResultBag =
			_fieldMappingDog.getFieldMappingResultBag(
				"organization", "account", 20, 0);

		if (fieldMappingResultBag.getTotal() == 0) {
			return null;
		}

		List<String> fetchSourceExcludes = new ArrayList<>();

		for (FieldMapping fieldMapping : fieldMappingResultBag.getResults()) {
			if (fieldMapping.isRestricted()) {
				fetchSourceExcludes.add(
					"organization." + fieldMapping.getFieldName());
			}
		}

		return fetchSourceExcludes.toArray(new String[0]);
	}

	private Map<String, Object> _getAccountOrganizationProperties(
		JSONObject organizationJSONObject) {

		if (organizationJSONObject == null) {
			return Collections.emptyMap();
		}

		Map<String, Object> properties = new HashMap<>();

		for (String propertyName : organizationJSONObject.keySet()) {
			Object propertyValue = _getPropertyValue(
				organizationJSONObject, propertyName);

			if (propertyValue == null) {
				continue;
			}

			properties.put(propertyName, propertyValue);
		}

		return properties;
	}

	@PostConstruct
	private void _init() {
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private Account _mapAccount(SearchHit searchHit) {
		Account account = new Account();

		JSONObject accountJSONObject = new JSONObject(
			searchHit.getSourceAsMap());

		account.setActiveIndividualsCount(
			accountJSONObject.optLong("activeIndividualCount", 0));
		account.setDateCreated(
			_parseDate(accountJSONObject.getString("dateCreated")));
		account.setDateModified(
			_parseDate(accountJSONObject.getString("dateModified")));
		account.setEngagementScore(
			accountJSONObject.optDouble("engagementScore", 0));
		account.setId(accountJSONObject.getString("id"));
		account.setIndividualsCount(
			accountJSONObject.optLong("individualCount", 0));
		account.setProperties(
			_getAccountOrganizationProperties(
				accountJSONObject.getJSONObject("organization")));

		return account;
	}

	private Date _parseDate(String dateString) {
		try {
			return DateUtil.toUTCDate(dateString);
		}
		catch (ParseException pe) {
			_log.error("Unable to parse date " + dateString);

			return null;
		}
	}

	private static final Log _log = LogFactory.getLog(AccountDog.class);

	@Autowired
	private DataDog _dataDog;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

}