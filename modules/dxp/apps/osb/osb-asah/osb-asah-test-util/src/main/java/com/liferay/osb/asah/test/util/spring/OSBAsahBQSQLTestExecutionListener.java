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

package com.liferay.osb.asah.test.util.spring;

import com.google.api.gax.paging.Page;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.Table;
import com.google.cloud.bigquery.TableId;

import com.liferay.osb.asah.common.bigquery.BigQuerySchemaManager;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.TestContext;

/**
 * @author Leslie Wong
 */
public class OSBAsahBQSQLTestExecutionListener
	extends BaseOSBAsahTestExecutionListener {

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		if (!_isTestExecutionListenerEnabled()) {
			return;
		}

		BQSQLResource bqSQLResource = AnnotatedElementUtils.getMergedAnnotation(
			testContext.getTestMethod(), BQSQLResource.class);

		if (bqSQLResource != null) {
			StringBuilder sb = new StringBuilder();

			Page<Table> tablePage = _bigQuery.listTables("test");

			for (Table table : tablePage.iterateAll()) {
				TableId tableId = table.getTableId();

				sb.append(
					String.format(
						"DELETE FROM `%s.%s` WHERE TRUE;", tableId.getDataset(),
						tableId.getTable()));
			}

			QueryJobConfiguration queryJobConfiguration =
				QueryJobConfiguration.newBuilder(
					sb.toString()
				).build();

			_bigQuery.query(queryJobConfiguration);
		}

		for (String cacheName : _cacheManager.getCacheNames()) {
			Cache cache = _cacheManager.getCache(cacheName);

			if (cache != null) {
				cache.invalidate();
			}
		}
	}

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		super.beforeTestClass(testContext);

		if (!_isTestExecutionListenerEnabled()) {
			return;
		}

		Class<?> clazz = testContext.getTestClass();

		BQSQLResource[] bqSQLResources = clazz.getAnnotationsByType(
			BQSQLResource.class);

		if (bqSQLResources.length == 0) {
			return;
		}

		if (bqSQLResources.length > 1) {
			throw new IllegalArgumentException(
				"Only 1 BQSQLResource annotation allowed");
		}

		_prepareTables(clazz, bqSQLResources[0]);
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		if (!_isTestExecutionListenerEnabled()) {
			return;
		}

		BQSQLResource bqSQLResource = AnnotatedElementUtils.getMergedAnnotation(
			testContext.getTestMethod(), BQSQLResource.class);

		if (bqSQLResource != null) {
			_prepareTables(testContext.getTestClass(), bqSQLResource);
		}
	}

	private void _createSchema() {
		Dataset testDataset = _bigQuery.getDataset("test");

		if (testDataset == null) {
			_bigQuerySchemaManager.createSchema("test");
		}
	}

	private String _getResourcePath(BQSQLResource bqSQLResource) {
		if (StringUtils.startsWith(bqSQLResource.resourcePath(), "/")) {
			return bqSQLResource.resourcePath();
		}

		return "dependencies/" + bqSQLResource.resourcePath();
	}

	private boolean _isTestExecutionListenerEnabled() {
		if (_bigQuery != null) {
			return true;
		}

		return false;
	}

	private void _prepareTables(Class<?> clazz, BQSQLResource bqSQLResource)
		throws Exception {

		if (!Objects.equals(bqSQLResource.resourcePath(), "")) {
			ClassPathResource classPathResource = new ClassPathResource(
				_getResourcePath(bqSQLResource), clazz);

			File file = classPathResource.getFile();

			String replaceSQLVariables =
				TestExecutionListenerUtil.replaceSQLVariables(
					new String(
						Files.readAllBytes(file.toPath()),
						StandardCharsets.UTF_8));

			_createSchema();

			QueryJobConfiguration queryJobConfiguration =
				QueryJobConfiguration.newBuilder(
					replaceSQLVariables
				).setDefaultDataset(
					"test"
				).build();

			_bigQuery.query(queryJobConfiguration);
		}
	}

	@Autowired
	private BigQuery _bigQuery;

	@Autowired
	private BigQuerySchemaManager _bigQuerySchemaManager;

	@Autowired
	private CacheManager _cacheManager;

}