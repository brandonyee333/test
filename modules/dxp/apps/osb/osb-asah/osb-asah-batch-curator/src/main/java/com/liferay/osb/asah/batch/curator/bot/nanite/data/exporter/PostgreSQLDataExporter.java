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

package com.liferay.osb.asah.batch.curator.bot.nanite.data.exporter;

import com.fasterxml.jackson.core.JsonFactory;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.DataExportTask;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SelectSelectStep;
import org.jooq.impl.DSL;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.Pageable;

/**
 * @author Thiago Buarque
 */
public class PostgreSQLDataExporter extends BaseDataExporter {

	public PostgreSQLDataExporter(
			DataExportTask dataExportTask, String dateFieldName,
			DSLContext dslContext, JsonFactory jsonFactory,
			OutputStream outputStream, String tableName)
		throws Exception {

		super(jsonFactory, outputStream);

		_dataExportTask = dataExportTask;
		_dateFieldName = dateFieldName;
		_dslContext = dslContext;
		_tableName = tableName;
	}

	@Override
	protected JSONObject doGetResultPageJSONObject(Pageable pageable) {
		SelectSelectStep<Record> selectSelectStep = _dslContext.select();

		Result<Record> records = selectSelectStep.from(
			_tableName
		).where(
			_getConditions()
		).limit(
			pageable.getPageSize()
		).offset(
			pageable.getOffset()
		).fetch();

		return JSONUtil.put(
			"results",
			new JSONArray(
				records.map(record -> new JSONObject(record.intoMap()))));
	}

	private List<Condition> _getConditions() {
		List<Condition> conditions = new ArrayList<>();

		if (_dataExportTask.getFromDate() != null) {
			conditions.add(
				DSL.field(
					_dateFieldName
				).greaterOrEqual(
					Optional.ofNullable(
						_dataExportTask.getFromDate()
					).orElse(
						_defaultDate
					)
				));
		}

		if (_dataExportTask.getToDate() != null) {
			conditions.add(
				DSL.field(
					_dateFieldName
				).lessOrEqual(
					Optional.ofNullable(
						_dataExportTask.getToDate()
					).orElse(
						_defaultDate
					)
				));
		}

		return conditions;
	}

	private static final Date _defaultDate = DateUtil.toUTCDate(
		"1970-01-01T00:00:00.000Z");

	private final DataExportTask _dataExportTask;
	private final String _dateFieldName;
	private final DSLContext _dslContext;
	private final String _tableName;

}