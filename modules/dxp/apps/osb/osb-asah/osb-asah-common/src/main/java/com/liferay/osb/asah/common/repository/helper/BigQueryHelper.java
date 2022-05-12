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
import com.google.cloud.bigquery.JobException;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;

import org.jooq.SelectFinalStep;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class BigQueryHelper {

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