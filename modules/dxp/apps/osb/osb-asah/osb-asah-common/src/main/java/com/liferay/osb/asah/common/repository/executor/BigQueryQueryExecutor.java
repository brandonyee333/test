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

package com.liferay.osb.asah.common.repository.executor;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.FieldList;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.LegacySQLTypeName;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.TableResult;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectFinalStep;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class BigQueryQueryExecutor implements QueryExecutor {

	@Override
	public BigDecimal queryForBigDecimal(
		SelectFinalStep<Record1<Number>> selectFinalStep) {

		TableResult tableResult = _query(selectFinalStep);

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		if (fieldValueLists.isEmpty()) {
			return BigDecimal.ZERO;
		}

		FieldValueList fieldValueList = fieldValueLists.get(0);

		return _toBigDecimalValue(fieldValueList.get(0));
	}

	@Override
	public <T> List<T> queryForList(
		Function<Map<String, Object>, T> rowMapperFunction,
		SelectFinalStep<? extends Record> selectFinalStep) {

		List<T> list = new ArrayList<>();

		TableResult tableResult = _query(selectFinalStep);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			list.add(
				rowMapperFunction.apply(
					_toObjectMap(fieldValueList, tableResult)));
		}

		return list;
	}

	@Override
	public long queryForLong(SelectFinalStep selectFinalStep) {
		TableResult tableResult = _query(selectFinalStep);

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		if (fieldValueLists.isEmpty()) {
			return 0;
		}

		FieldValueList fieldValueList = fieldValueLists.get(0);

		return _toLongValue(fieldValueList.get(0));
	}

	@Override
	public <T, R> Map<T, R> queryForMap(
		Function<Object, T> keyMapperFunction,
		SelectFinalStep<? extends Record> selectFinalStep,
		Function<Object, R> valueMapperFunction) {

		Map<T, R> map = new LinkedHashMap<>();

		TableResult tableResult = _query(selectFinalStep);

		Schema schema = tableResult.getSchema();

		FieldList fieldList = schema.getFields();

		Field field = fieldList.get(1);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			FieldValue keyFieldValue = fieldValueList.get(0);
			FieldValue valueFieldValue = fieldValueList.get(1);

			map.put(
				keyMapperFunction.apply(keyFieldValue.getValue()),
				valueMapperFunction.apply(_getField(valueFieldValue, field)));
		}

		return map;
	}

	@Override
	public Map<String, Object> queryForMap(
		SelectFinalStep<? extends Record> selectFinalStep) {

		TableResult tableResult = _query(selectFinalStep);

		List<FieldValueList> fieldValueList = IterableUtils.toList(
			tableResult.getValues());

		if (fieldValueList.isEmpty()) {
			return null;
		}

		return _toObjectMap(fieldValueList.get(0), tableResult);
	}

	@Override
	public <T> Optional<T> queryForObject(
		Function<Map<String, Object>, T> rowMapperFunction,
		SelectFinalStep<Record> selectFinalStep) {

		TableResult tableResult = _query(selectFinalStep);

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		if (fieldValueLists.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(
			rowMapperFunction.apply(
				_toObjectMap(fieldValueLists.get(0), tableResult)));
	}

	private String _getBigQueryTableName(String tableName) {
		return "`" + _googleProjectId + "." +
			ProjectIdThreadLocal.getProjectId() + "." +
				StringUtils.lowerCase(tableName.replace("BQ", "") + "`");
	}

	private Object _getField(FieldValue fieldValue, Field field) {
		if (fieldValue.getAttribute() == FieldValue.Attribute.REPEATED) {
			List<Object> objects = new ArrayList<>();

			for (FieldValue repeatedFieldValue :
					fieldValue.getRepeatedValue()) {

				objects.add(_getField(repeatedFieldValue, field));
			}

			return objects;
		}

		if ((field.getType() == LegacySQLTypeName.BIGNUMERIC) ||
			(field.getType() == LegacySQLTypeName.FLOAT) ||
			(field.getType() == LegacySQLTypeName.INTEGER) ||
			(field.getType() == LegacySQLTypeName.NUMERIC)) {

			return _toBigDecimalValue(fieldValue);
		}
		else if (field.getType() == LegacySQLTypeName.BOOLEAN) {
			return _toBooleanValue(fieldValue);
		}
		else if ((field.getType() == LegacySQLTypeName.DATE) ||
				 (field.getType() == LegacySQLTypeName.TIMESTAMP)) {

			return _toDateValue(fieldValue);
		}
		else if (field.getType() == LegacySQLTypeName.STRING) {
			return _toStringValue(fieldValue);
		}

		return fieldValue.getValue();
	}

	@PostConstruct
	private void _init() {
		BigQueryOptions bigQueryOptions = BigQueryOptions.getDefaultInstance();

		_bigQuery = bigQueryOptions.getService();
		_googleProjectId = bigQueryOptions.getProjectId();
	}

	private TableResult _query(SelectFinalStep selectFinalStep) {
		String translatedQuery = _translate(
			String.valueOf(selectFinalStep.getQuery()));

		if (_log.isDebugEnabled()) {
			_log.debug("Executing query: " + translatedQuery);
		}

		QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(
			translatedQuery
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

	private BigDecimal _toBigDecimalValue(FieldValue fieldValue) {
		if ((fieldValue == null) || (fieldValue.getValue() == null)) {
			return BigDecimal.ZERO;
		}

		return fieldValue.getNumericValue();
	}

	private boolean _toBooleanValue(FieldValue fieldValue) {
		if ((fieldValue == null) || (fieldValue.getValue() == null)) {
			return false;
		}

		return fieldValue.getBooleanValue();
	}

	private Date _toDateValue(FieldValue fieldValue) {
		if ((fieldValue == null) || (fieldValue.getValue() == null)) {
			return null;
		}

		return new Date(fieldValue.getTimestampValue() / 1000);
	}

	private long _toLongValue(FieldValue fieldValue) {
		if ((fieldValue == null) || (fieldValue.getValue() == null)) {
			return 0;
		}

		return fieldValue.getLongValue();
	}

	private Map<String, Object> _toObjectMap(
		FieldValueList fieldValueList, TableResult tableResult) {

		Map<String, Object> objectMap = new LinkedHashMap<>();

		Schema schema = tableResult.getSchema();

		for (Field field : schema.getFields()) {
			objectMap.put(
				field.getName(),
				_getField(fieldValueList.get(field.getName()), field));
		}

		return objectMap;
	}

	private String _toStringValue(FieldValue fieldValue) {
		if ((fieldValue == null) || (fieldValue.getValue() == null)) {
			return null;
		}

		return fieldValue.getStringValue();
	}

	private String _translate(String query) {
		for (String name : _FUNCTION_AND_TABLE_NAMES) {
			query = query.replaceAll(
				"(?<![\\w\\d])" + name + "(?![\\w\\d])",
				_getBigQueryTableName(name));
		}

		return query.replace("\\''", "\\'");
	}

	private static final String[] _FUNCTION_AND_TABLE_NAMES = {
		"BlogDaily", "BlogHourly", "BQEvent", "BQEventProperty",
		"BQFieldMapping", "BQIdentity", "BQIdentityActivity",
		"BQIdentityChannel", "BQIndividual", "BQIndividualInterestScore",
		"BQMembership", "BQOrder", "BQSession", "CustomAssetDaily",
		"CustomAssetHourly", "DocumentLibraryDaily", "DocumentLibraryHourly",
		"FormDaily", "FormHourly", "JournalDaily", "JournalHourly", "PageDaily",
		"PageHourly", "SEARCH_TERM"
	};

	private static final Log _log = LogFactory.getLog(
		BigQueryQueryExecutor.class);

	private BigQuery _bigQuery;
	private String _googleProjectId;

}