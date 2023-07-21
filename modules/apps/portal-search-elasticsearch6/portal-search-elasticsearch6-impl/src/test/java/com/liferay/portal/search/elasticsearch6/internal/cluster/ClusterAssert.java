/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch6.internal.cluster;

import com.liferay.portal.search.elasticsearch6.internal.connection.ElasticsearchFixture;
import com.liferay.portal.search.elasticsearch6.internal.connection.HealthExpectations;
import com.liferay.portal.search.test.util.IdempotentRetryAssert;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;

import org.junit.Assert;

/**
 * @author André de Oliveira
 */
public class ClusterAssert {

	public static void assert1PrimaryAnd1UnassignedShard(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 1;
					activeShards = 1;
					numberOfDataNodes = 1;
					numberOfNodes = 1;
					status = ClusterHealthStatus.YELLOW;
					unassignedShards = 1;
				}
			});
	}

	public static void assert1PrimaryShardAnd2Nodes(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 1;
					activeShards = 1;
					numberOfDataNodes = 2;
					numberOfNodes = 2;
					status = ClusterHealthStatus.GREEN;
					unassignedShards = 0;
				}
			});
	}

	public static void assert1PrimaryShardOnly(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 1;
					activeShards = 1;
					numberOfDataNodes = 1;
					numberOfNodes = 1;
					status = ClusterHealthStatus.GREEN;
					unassignedShards = 0;
				}
			});
	}

	public static void assert1ReplicaAnd1UnassignedShard(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 1;
					activeShards = 2;
					numberOfDataNodes = 2;
					numberOfNodes = 2;
					status = ClusterHealthStatus.YELLOW;
					unassignedShards = 1;
				}
			});
	}

	public static void assert1ReplicaShard(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 1;
					activeShards = 2;
					numberOfDataNodes = 2;
					numberOfNodes = 2;
					status = ClusterHealthStatus.GREEN;
					unassignedShards = 0;
				}
			});
	}

	public static void assert2Primary2UnassignedShardsAnd1Node(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 2;
					activeShards = 2;
					numberOfDataNodes = 1;
					numberOfNodes = 1;
					status = ClusterHealthStatus.YELLOW;
					unassignedShards = 2;
				}
			});
	}

	public static void assert2PrimaryShards1ReplicaAnd2Nodes(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 2;
					activeShards = 4;
					numberOfDataNodes = 2;
					numberOfNodes = 2;
					status = ClusterHealthStatus.GREEN;
					unassignedShards = 0;
				}
			});
	}

	public static void assert2PrimaryShardsAnd2Nodes(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 2;
					activeShards = 2;
					numberOfDataNodes = 2;
					numberOfNodes = 2;
					status = ClusterHealthStatus.GREEN;
					unassignedShards = 0;
				}
			});
	}

	public static void assert2ReplicaShards(
			ElasticsearchFixture elasticsearchFixture)
		throws Exception {

		assertHealth(
			elasticsearchFixture,
			new HealthExpectations() {
				{
					activePrimaryShards = 1;
					activeShards = 3;
					numberOfDataNodes = 3;
					numberOfNodes = 3;
					status = ClusterHealthStatus.GREEN;
					unassignedShards = 0;
				}
			});
	}

	public static void assertHealth(
			final ElasticsearchFixture elasticsearchFixture,
			final HealthExpectations healthExpectations)
		throws Exception {

		IdempotentRetryAssert.retryAssert(
			10, TimeUnit.MINUTES,
			new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					ClusterHealthResponse clusterHealthResponse =
						elasticsearchFixture.getClusterHealthResponse(
							healthExpectations);

					_assertHealth(clusterHealthResponse, healthExpectations);

					return null;
				}

			});
	}

	private static void _assertHealth(
		ClusterHealthResponse clusterHealthResponse,
		HealthExpectations expectedHealthExpectations) {

		HealthExpectations actualHealthExpectations = new HealthExpectations(
			clusterHealthResponse);

		Assert.assertEquals(
			expectedHealthExpectations.toString(),
			actualHealthExpectations.toString());
	}

}