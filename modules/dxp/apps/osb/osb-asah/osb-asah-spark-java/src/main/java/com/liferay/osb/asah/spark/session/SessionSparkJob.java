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

package com.liferay.osb.asah.spark.session;

import com.liferay.osb.asah.spark.common.Configuration;
import com.liferay.osb.asah.spark.common.SparkJob;
import com.liferay.osb.asah.spark.session.function.EventGroupByKeyFunction;
import com.liferay.osb.asah.spark.session.function.SessionBatchSinkFunction;
import com.liferay.osb.asah.spark.session.function.SessionFlatMapGroupsWithStateFunction;
import com.liferay.osb.asah.spark.session.model.Event;
import com.liferay.osb.asah.spark.session.model.Session;

import java.sql.Date;

import java.time.Duration;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.KeyValueGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.DataStreamReader;
import org.apache.spark.sql.streaming.DataStreamWriter;
import org.apache.spark.sql.streaming.GroupStateTimeout;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;

import scala.Tuple2;

/**
 * @author Marcellus Tavares
 */
public class SessionSparkJob implements SparkJob {

	public SessionSparkJob(SessionSparkApplication baseSparkApplication) {
		Configuration configuration = baseSparkApplication.getConfiguration();

		_eventsPath = configuration.get(
			"google.storage.path.events", "google.storage.path.events");

		_sessionBatchSinkFunction = new SessionBatchSinkFunction(
			configuration.get(
				"google.storage.path.session.events.realtime", null),
			configuration.get("google.storage.path.session.events", null),
			configuration.get("google.storage.path.sessions", null));

		int durationMinutes = Integer.parseInt(
			configuration.get("session.duration", "30"));

		_sessionDuration = Duration.ofMinutes(durationMinutes);

		_sessionFlatMapGroupsWithState =
			new SessionFlatMapGroupsWithStateFunction(
				_sessionDuration.toMillis());

		_sparkSession = baseSparkApplication.getSparkSession();
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			run();
		}
	}

	private KeyValueGroupedDataset<Tuple2<String, Date>, Event>
		_createKeyValueGroupedDataset() {

		DataStreamReader dataStreamReader = _sparkSession.readStream();

		Dataset<Row> eventsDataset = dataStreamReader.format(
			_TABLE_FORMAT_DELTA
		).option(
			"ignoreChanges", true
		).option(
			"ignoreDeletes", true
		).load(
			_eventsPath
		);

		return eventsDataset.withColumn(
			"date", functions.to_date(functions.col("eventDate"))
		).withColumn(
			"iterationNumber", functions.lit(0)
		).withColumn(
			"sessionId", null
		).select(
			functions.col("*")
		).withWatermark(
			"eventDate",
			String.format("%d minutes", _sessionDuration.toMinutes())
		).as(
			_EVENT_ENCODER
		).groupByKey(
			_EVENT_GROUP_BY_KEY_FUNCTION, _KEY_ENCODER
		);
	}

	private void _run() throws Exception {
		KeyValueGroupedDataset<Tuple2<String, Date>, Event>
			keyValueGroupedDataset = _createKeyValueGroupedDataset();

		DataStreamWriter<Session> dataStreamWriter =
			keyValueGroupedDataset.flatMapGroupsWithState(
				_sessionFlatMapGroupsWithState, OutputMode.Append(),
				_SESSION_ENCODER, _SESSION_ENCODER,
				GroupStateTimeout.EventTimeTimeout()
			).writeStream(
			).foreachBatch(
				_sessionBatchSinkFunction
			);

		StreamingQuery streamingQuery = dataStreamWriter.start();

		streamingQuery.processAllAvailable();
	}

	private static final Encoder<Event> _EVENT_ENCODER = Encoders.bean(
		Event.class);

	private static final EventGroupByKeyFunction _EVENT_GROUP_BY_KEY_FUNCTION =
		new EventGroupByKeyFunction();

	private static final Encoder<Tuple2<String, Date>> _KEY_ENCODER =
		Encoders.tuple(Encoders.STRING(), Encoders.DATE());

	private static final Encoder<Session> _SESSION_ENCODER = Encoders.bean(
		Session.class);

	private static final String _TABLE_FORMAT_DELTA = "delta";

	private final String _eventsPath;
	private final SessionBatchSinkFunction _sessionBatchSinkFunction;
	private final Duration _sessionDuration;
	private final SessionFlatMapGroupsWithStateFunction
		_sessionFlatMapGroupsWithState;
	private final SparkSession _sparkSession;

}