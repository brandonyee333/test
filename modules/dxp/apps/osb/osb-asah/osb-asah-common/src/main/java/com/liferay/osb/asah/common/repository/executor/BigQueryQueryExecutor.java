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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang.StringUtils;

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
		Class<T> clazz, SelectFinalStep<Record> selectFinalStep) {

		List<T> list = new ArrayList<>();

		TableResult tableResult = _query(selectFinalStep);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			list.add(
				getObject(
					getConstructor(clazz),
					_toObjectMap(fieldValueList, tableResult)));
		}

		return list;
	}

	@Override
	public <T> List<T> queryForList(
		SelectFinalStep<Record1<T>> selectFinalStep) {

		List<T> list = new ArrayList<>();

		TableResult tableResult = _query(selectFinalStep);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			list.add((T)fieldValueList.get(0));
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

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			map.put(
				keyMapperFunction.apply(fieldValueList.get(0)),
				valueMapperFunction.apply(fieldValueList.get(1)));
		}

		return map;
	}

	@Override
	public <T, R> Map<T, R> queryForMap(
		SelectFinalStep<? extends Record> selectFinalStep) {

		Map<T, R> map = new LinkedHashMap<>();

		TableResult tableResult = _query(selectFinalStep);

		for (FieldValueList fieldValueList : tableResult.iterateAll()) {
			map.put((T)fieldValueList.get(0), (R)fieldValueList.get(1));
		}

		return map;
	}

	@Override
	public <T> Optional<T> queryForObject(
		Class<T> clazz, SelectFinalStep<Record> selectFinalStep) {

		TableResult tableResult = _query(selectFinalStep);

		List<FieldValueList> fieldValueLists = IterableUtils.toList(
			tableResult.iterateAll());

		if (fieldValueLists.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(
			getObject(
				getConstructor(clazz),
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

		if (field.getType() == LegacySQLTypeName.BOOLEAN) {
			return _toBooleanValue(fieldValue);
		}
		else if ((field.getType() == LegacySQLTypeName.DATE) ||
				 (field.getType() == LegacySQLTypeName.TIMESTAMP)) {

			return _toDateValue(fieldValue);
		}
		else if ((field.getType() == LegacySQLTypeName.INTEGER) ||
				 (field.getType() == LegacySQLTypeName.NUMERIC)) {

			return _toLongValue(fieldValue);
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

		return new Date(fieldValue.getTimestampValue());
	}

	private long _toLongValue(FieldValue fieldValue) {
		if ((fieldValue == null) || (fieldValue.getValue() == null)) {
			return 0;
		}

		return fieldValue.getLongValue();
	}

	private Map<String, Object> _toObjectMap(
		FieldValueList fieldValueList, TableResult tableResult) {

		Map<String, Object> objectMap = new HashMap<>();

		Schema schema = tableResult.getSchema();

		for (Field field : schema.getFields()) {
			objectMap.put(
				StringUtils.lowerCase(field.getName()),
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