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

package com.liferay.osb.asah.common.repository.helper;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

import com.liferay.osb.asah.common.repository.util.BigQueryUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.StringUtils;

import org.jooq.SelectFinalStep;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class BigQueryHelper {

	public long count(SelectFinalStep selectFinalStep) {
		TableResult tableResult = query(selectFinalStep);

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		if (fieldValueLists.isEmpty()) {
			return 0;
		}

		FieldValueList fieldValueList = fieldValueLists.get(0);

		return BigQueryUtil.getLong(fieldValueList.get(0));
	}

	public <T> T get(
		T defaultValue, Function<FieldValueList, T> function,
		SelectFinalStep selectFinalStep) {

		TableResult tableResult = query(selectFinalStep);

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		if (fieldValueLists.isEmpty()) {
			return defaultValue;
		}

		return function.apply(fieldValueLists.get(0));
	}

	public <T> List<T> getList(
		Function<FieldValueList, T> function, SelectFinalStep selectFinalStep) {

		List<T> list = new ArrayList<>();

		TableResult tableResult = query(selectFinalStep);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			list.add(function.apply(fieldValueList));
		}

		return list;
	}

	public TableResult query(SelectFinalStep selectFinalStep) {
		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(
			_translate(String.valueOf(selectFinalStep.getQuery()))
		).build();

		try {
			return _bigQuery.query(queryConfig);
		}
		catch (JobException jobException) {
			throw new RuntimeException(jobException);
		}
		catch (InterruptedException interruptedException) {
			throw new RuntimeException(interruptedException);
		}
	}

	private String _getBigQueryTableName(String tableName) {
		return "`" + _googleProjectId + "." +
			ProjectIdThreadLocal.getProjectId() + "." +
				StringUtils.lowerCase(tableName.replace("BQ", "") + "`");
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
		_googleProjectId = bigQueryOptions.getProjectId();
	}

	private String _translate(String query) {
		for (String tableName : _TABLE_NAMES) {
			query = query.replaceAll(
				"(?<![\\w\\d])" + tableName + "(?![\\w\\d])",
				_getBigQueryTableName(tableName));
		}

		return query;
	}

	private static final String[] _TABLE_NAMES = {"BQEvent", "BQEventProperty"};

	private BigQuery _bigQuery;
	private String _googleProjectId;

}