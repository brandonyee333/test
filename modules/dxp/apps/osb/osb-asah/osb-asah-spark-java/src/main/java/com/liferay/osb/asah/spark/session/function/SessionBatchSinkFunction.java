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

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.api.java.function.VoidFunction2;
import org.apache.spark.sql.DataFrameWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions;

/**
 * @author Robson Pastor
 */
public class SessionBatchSinkFunction
	implements VoidFunction2<Dataset<Session>, Long> {

	public SessionBatchSinkFunction(
		String realtimeEventsPath, String sessionEventsPath,
		String sessionsPath) {

		Objects.requireNonNull(
			realtimeEventsPath, "realtimeEventsPath is null");
		Objects.requireNonNull(sessionEventsPath, "sessionEventsPath is null");
		Objects.requireNonNull(sessionsPath, "sessionsPath is null");

		_realtimeEventsPath = realtimeEventsPath;
		_sessionEventsPath = sessionEventsPath;
		_sessionsPath = sessionsPath;
	}

	@Override
	public void call(Dataset<Session> dataset, Long batchNumber) {
		dataset.persist();

		saveFinishedSessionEvents(dataset);
		saveFinishedSessions(dataset);
		saveRealtimeSessionEvents(dataset);

		dataset.unpersist();
	}

	public void saveFinishedSessionEvents(Dataset<Session> dataset) {
		if (_log.isInfoEnabled()) {
			_log.info("Saving finished session events");
		}

		Dataset<Row> finishedSessionEventsDataset = dataset.filter(
			"finished == true"
		).select(
			functions.explode(
				functions.col("events")
			).alias(
				"e"
			),
			functions.col("sessionId")
		).select(
			functions.col("e.*"), functions.col("sessionId")
		).drop(
			"iterationNumber"
		);

		DataFrameWriter<Row> dataFrameWriter =
			finishedSessionEventsDataset.write();

		dataFrameWriter.format(
			"parquet"
		).mode(
			"append"
		).partitionBy(
			"projectId", "date"
		).save(
			_sessionEventsPath
		);
	}

	public void saveFinishedSessions(Dataset<Session> dataset) {
		if (_log.isInfoEnabled()) {
			_log.info("Saving finished sessions");
		}

		Dataset<Row> finishedSessionsDataset = dataset.filter(
			"finished == true"
		).drop(
			"finished", "events", "iterationNumber"
		).select(
			functions.col("*")
		);

		DataFrameWriter<Row> dataFrameWriter = finishedSessionsDataset.write();

		dataFrameWriter.format(
			"parquet"
		).mode(
			"append"
		).partitionBy(
			"projectId", "date"
		).save(
			_sessionsPath
		);
	}

	public void saveRealtimeSessionEvents(Dataset<Session> dataset) {
		if (_log.isInfoEnabled()) {
			_log.info("Saving realtime events");
		}

		Dataset<Row> unfinishedSessionEventsDataset = dataset.select(
			functions.explode(
				functions.col("events")
			).alias(
				"e"
			),
			functions.col(
				"iterationNumber"
			).alias(
				"sessionIterationNumber"
			),
			functions.col("sessionId")
		).select(
			functions.col("e.*"), functions.col("sessionIterationNumber"),
			functions.col("sessionId")
		).filter(
			"iterationNumber == sessionIterationNumber"
		).drop(
			"iterationNumber", "sessionIterationNumber"
		);

		DataFrameWriter<Row> dataFrameWriter =
			unfinishedSessionEventsDataset.write();

		dataFrameWriter.format(
			"parquet"
		).mode(
			"append"
		).partitionBy(
			"projectId", "date"
		).save(
			_realtimeEventsPath
		);
	}

	private static final Log _log = LogFactory.getLog(
		SessionBatchSinkFunction.class);

	private final String _realtimeEventsPath;
	private final String _sessionEventsPath;
	private final String _sessionsPath;

}