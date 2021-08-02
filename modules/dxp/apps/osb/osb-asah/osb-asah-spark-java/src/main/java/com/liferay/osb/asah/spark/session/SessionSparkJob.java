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

import com.liferay.osb.asah.spark.common.BaseSparkApplication;
import com.liferay.osb.asah.spark.common.Configuration;
import com.liferay.osb.asah.spark.common.SparkJob;
import com.liferay.osb.asah.spark.session.function.EventGroupByKeyFunction;
import com.liferay.osb.asah.spark.session.function.SessionBatchSinkFunction;
import com.liferay.osb.asah.spark.session.function.SessionFlatMapGroupsWithStateFunction;
import com.liferay.osb.asah.spark.session.model.Event;
import com.liferay.osb.asah.spark.session.model.Session;

import java.sql.Date;

import java.time.Duration;

import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.KeyValueGroupedDataset;
import org.apache.spark.sql.functions;
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
		String realtimeEventsPath = configuration.get(
			"google.storage.path.session.events.realtime",
			"google.storage.path.session.events.realtime");
		String sessionsPath = configuration.get(
			"google.storage.path.sessions", "google.storage.path.sessions");
		String sessionEventsPath = configuration.get(
			"google.storage.path.session.events",
			"google.storage.path.session.events");

		String duration = configuration.get(
			"session.duration", "session.duration");

		int durationMinutes = Integer.parseInt(duration);

		_sessionDuration = Duration.ofMinutes(durationMinutes);

		_sessionFlatMapGroupsWithState =
			new SessionFlatMapGroupsWithStateFunction(
				_sessionDuration.toMillis());

		_sparkApplication = baseSparkApplication;

		_sessionBatchSinkFunction = new SessionBatchSinkFunction(
			realtimeEventsPath, sessionEventsPath, sessionsPath);
	}

	@Override
	public void run() {
		try {
			_run();
		}
		catch (Exception exception) {
			exception.printStackTrace();
			run();
		}
	}

	private KeyValueGroupedDataset<Tuple2<String, Date>, Event>
		_createKeyValueGroupedDataset() {

		return _sparkApplication.getSparkSession(
		).readStream(
		).format(
			_TABLE_FORMAT_DELTA
		).option(
			"ignoreChanges", true
		).option(
			"ignoreDeletes", true
		).load(
			_eventsPath
		).withColumn(
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

	private StreamingQuery _createStreamingQuery() throws Exception {
		KeyValueGroupedDataset<Tuple2<String, Date>, Event> dataset =
			_createKeyValueGroupedDataset();

		DataStreamWriter<Session> dataStreamWriter =
			dataset.flatMapGroupsWithState(
				_sessionFlatMapGroupsWithState, OutputMode.Append(),
				_SESSION_ENCODER, _SESSION_ENCODER,
				GroupStateTimeout.EventTimeTimeout()
			).writeStream(
			).foreachBatch(
				_sessionBatchSinkFunction
			);

		return dataStreamWriter.start();
	}

	private void _run() throws Exception {
		StreamingQuery streamingQuery = _createStreamingQuery();

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
	private final BaseSparkApplication _sparkApplication;

}