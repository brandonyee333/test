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

package com.liferay.osb.asah.upgrade.v3_0_5;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class IndividualEventUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		long currentIndividualId = 0;
		String projectId = ProjectIdThreadLocal.getProjectId();

		while (true) {
			long start = System.currentTimeMillis();

			List<Long> individualIds = _getIndividualIds(currentIndividualId);

			if (individualIds.isEmpty()) {
				break;
			}

			currentIndividualId = individualIds.get(individualIds.size() - 1);

			_updateIndividuaIds(individualIds, projectId);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Dispatched %d individual IDs in %d ms",
						individualIds.size(),
						System.currentTimeMillis() - start));
			}
		}

		try {
			_semaphore.acquireUninterruptibly(10);
		}
		finally {
			_semaphore.release(10);
		}
	}

	@PreDestroy
	private void _destroy() {
		_executorService.shutdown();

		try {
			if (!_executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				_executorService.shutdownNow();
			}
		}
		catch (InterruptedException interruptedException) {
			_log.error(
				"Interrupted while waiting for termination of executor",
				interruptedException);
		}
	}

	private List<Long> _getIndividualIds(long currentIndividualId) {
		return JSONUtil.toLongList(
			_faroInfoElasticsearchInvoker.get(
				"individuals", SortBuilders.fieldSort("id"),
				new String[] {"id"},
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("demographics.email")
				).filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						currentIndividualId
					)
				),
				1000),
			"id");
	}

	private void _updateIndividuaIds(
		List<Long> individualIds, String projectId) {

		_semaphore.acquireUninterruptibly();

		CompletableFuture.runAsync(
			() -> {
				long start = System.currentTimeMillis();

				ProjectIdThreadLocal.setProjectId(projectId);

				try (Connection connection =
						_postgreSQLDataSource.getConnection();
					PreparedStatement preparedStatement1 =
						connection.prepareStatement(
							"SELECT individualId, sessionId FROM Event WHERE " +
								"individualId = ANY(?) GROUP BY " +
									"individualId, sessionId ORDER BY " +
										"individualId, sessionId")) {

					preparedStatement1.setArray(
						1,
						connection.createArrayOf(
							"BIGINT", individualIds.toArray(new Long[0])));

					try (ResultSet resultSet =
							preparedStatement1.executeQuery();
						PreparedStatement preparedStatement2 =
							connection.prepareStatement(
								"UPDATE Event SET individualId = ? WHERE " +
									"sessionId = ? AND individualId != ?")) {

						int i = 0;

						while (resultSet.next()) {
							long individualId = resultSet.getLong(1);
							String sessionId = resultSet.getString(2);

							preparedStatement2.setLong(1, individualId);
							preparedStatement2.setString(2, sessionId);
							preparedStatement2.setLong(3, individualId);

							preparedStatement2.addBatch();

							i++;

							if ((i % 10000) == 0) {
								preparedStatement2.executeBatch();
							}
						}

						preparedStatement2.executeBatch();
					}
					catch (Exception exception) {
						_log.error(
							"Unable to update session IDs for individual IDs " +
								individualIds,
							exception);
					}
				}
				catch (Exception exception) {
					_log.error(
						"Unable to get session IDs for individual IDs " +
							individualIds,
						exception);
				}
				finally {
					_semaphore.release();
				}

				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"%s processed %d individual IDs in %d ms",
							individualIds.size(),
							System.currentTimeMillis() - start));
				}
			},
			_executorService);
	}

	private static final Log _log = LogFactory.getLog(
		IndividualEventUpgradeStep.class);

	private final ExecutorService _executorService =
		Executors.newFixedThreadPool(5);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	@Qualifier("postgreSQLDataSource")
	private DataSource _postgreSQLDataSource;

	private final Semaphore _semaphore = new Semaphore(10, true);

}