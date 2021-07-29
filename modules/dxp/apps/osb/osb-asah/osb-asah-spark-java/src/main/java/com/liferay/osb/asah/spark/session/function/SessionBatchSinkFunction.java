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

package com.liferay.osb.asah.spark.session.function;

import com.liferay.osb.asah.spark.session.model.Session;

import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.functions;

/**
 * @author Robson Pastor
 */
public class SessionBatchSinkFunction
	implements VoidFunction2<Dataset<Session>, Long> {

	public SessionBatchSinkFunction(
		String realtimeEventsPath, String sessionEventsPath,
		String sessionsPath) {

		_realtimeEventsPath = realtimeEventsPath;
		_sessionEventsPath = sessionEventsPath;
		_sessionsPath = sessionsPath;
	}

	@Override
	public void call(Dataset<Session> dataset, Long batchNumber) {
		dataset.persist();

		saveRealtimeEvents(dataset);
		saveRealtimeEvents(dataset);
		saveFinishedSessionsEvents(dataset);
		saveFinishedSessions(dataset);
		dataset.unpersist();
	}

	public void saveFinishedSessions(Dataset<Session> dataset) {
		dataset.filter(
			"finished == true"
		).drop(
			"finished", "events", "iterationNumber"
		).select(
			functions.col("*")
		).write(
		).format(
			_TABLE_FORMAT_DELTA
		).mode(
			"append"
		).option(
			"mergeSchema", _MERGE_SCHEMA
		).partitionBy(
			"projectId", "date"
		).save(
			_sessionsPath
		);
	}

	public void saveFinishedSessionsEvents(Dataset<Session> dataset) {
		dataset.filter(
			"finished == true"
		).select(
			functions.explode(
				functions.col("events")
			).alias(
				"e"
			)
		).select(
			functions.col("e.*")
		).drop(
			"iterationNumber"
		).write(
		).format(
			_TABLE_FORMAT_DELTA
		).mode(
			"append"
		).option(
			"mergeSchema", _MERGE_SCHEMA
		).partitionBy(
			"projectId", "date"
		).save(
			_sessionEventsPath
		);
	}

	public void saveRealtimeEvents(Dataset<Session> dataset) {
		dataset.select(
			functions.explode(
				functions.col("events")
			).alias(
				"e"
			),
			functions.col(
				"iterationNumber"
			).alias(
				"sessionIterationNumber"
			)
		).select(
			functions.col("e.*"), functions.col("sessionIterationNumber")
		).filter(
			"iterationNumber == sessionIterationNumber"
		).drop(
			"iterationNumber", "sessionIterationNumber"
		).write(
		).format(
			_TABLE_FORMAT_DELTA
		).mode(
			"append"
		).option(
			"mergeSchema", _MERGE_SCHEMA
		).partitionBy(
			"projectId", "date"
		).save(
			_realtimeEventsPath
		);
	}

	private static final boolean _MERGE_SCHEMA = true;

	private static final String _TABLE_FORMAT_DELTA = "delta";

	private final String _realtimeEventsPath;
	private final String _sessionEventsPath;
	private final String _sessionsPath;

}