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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.Metadata;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import scala.Tuple2;

/**
 * @author Marcellus Tavares
 */
public class SessionSparkJob implements SparkJob {

	public SessionSparkJob(SessionSparkApplication baseSparkApplication) {
		Configuration configuration = baseSparkApplication.getConfiguration();

		_backoffMaxAttempts = Long.parseLong(
			configuration.get("backoff.max.attempts", "20"));
		_backoffMaxWaitingTime = Long.parseLong(
			configuration.get("backoff.max.waiting.time", "600000"));
		_checkpointPath = configuration.get(
			"google.storage.path.checkpoint", "google.storage.path.checkpoint");
		_eventsPath = configuration.get(
			"google.storage.path.events", "google.storage.path.events");
		_sessionBatchSinkFunction = new SessionBatchSinkFunction(
			configuration.get(
				"google.storage.path.session.events.realtime", null),
			configuration.get("google.storage.path.session.events", null),
			configuration.get("google.storage.path.sessions", null));
		_sessionDuration = Duration.ofMinutes(
			Integer.parseInt(configuration.get("session.duration", "30")));

		_sparkSession = baseSparkApplication.getSparkSession();

		_sessionFlatMapGroupsWithState =
			new SessionFlatMapGroupsWithStateFunction(
				_sessionDuration.toMillis());
	}

	@Override
	public void run() {
		if (_log.isInfoEnabled()) {
			_log.info("Session spark job started");
		}

		try {
			StreamingQuery streamingQuery = startStreamingQuery();

			streamingQuery.awaitTermination();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			_wait();

			run();
		}
	}

	protected StreamingQuery startStreamingQuery() throws Exception {
		KeyValueGroupedDataset<Tuple2<String, Date>, Event>
			keyValueGroupedDataset = _createKeyValueGroupedDataset();

		DataStreamWriter<Session> dataStreamWriter =
			keyValueGroupedDataset.flatMapGroupsWithState(
				_sessionFlatMapGroupsWithState, OutputMode.Append(),
				_SESSION_ENCODER, _SESSION_ENCODER,
				GroupStateTimeout.EventTimeTimeout()
			).writeStream(
			).option(
				"checkpointLocation", _checkpointPath
			).foreachBatch(
				_sessionBatchSinkFunction
			);

		if (_log.isInfoEnabled()) {
			_log.info("Starting streaming query");
		}

		return dataStreamWriter.start();
	}

	private KeyValueGroupedDataset<Tuple2<String, Date>, Event>
		_createKeyValueGroupedDataset() {

		if (_log.isInfoEnabled()) {
			_log.info("Creating grouped dataset");
		}

		DataStreamReader dataStreamReader = _sparkSession.readStream();

		Dataset<Row> eventsDataset = dataStreamReader.format(
			_TABLE_FORMAT_JSON
		).option(
			"ignoreChanges", true
		).option(
			"ignoreDeletes", true
		).schema(
			_EVENT_SCHEMA
		).load(
			_eventsPath
		);

		return eventsDataset.withColumn(
			"date",
			functions.to_date(
				functions.from_utc_timestamp(
					functions.col("eventDate"),
					functions.col("projectTimeZoneId")))
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

	private void _wait() {
		_backoffAttemptNumber += 1;

		if (_backoffAttemptNumber > _backoffMaxAttempts) {
			throw new RuntimeException("Backoff max attempts exceeded");
		}

		_backoffWaitingTime = Math.min(
			2 * _backoffWaitingTime, _backoffMaxWaitingTime);

		try {
			Thread.sleep(_backoffWaitingTime);
		}
		catch (InterruptedException interruptedException) {
			_log.error(interruptedException, interruptedException);
		}
	}

	private static final Encoder<Event> _EVENT_ENCODER = Encoders.bean(
		Event.class);

	private static final EventGroupByKeyFunction _EVENT_GROUP_BY_KEY_FUNCTION =
		new EventGroupByKeyFunction();

	private static final StructType _EVENT_SCHEMA = new StructType(
		new StructField[] {
			new StructField(
				"applicationId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"channelId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"clientIP", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"context",
				DataTypes.createMapType(
					DataTypes.StringType, DataTypes.StringType),
				true, Metadata.empty()),
			new StructField(
				"createDate", DataTypes.TimestampType, true, Metadata.empty()),
			new StructField(
				"dataSourceId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"eventDate", DataTypes.TimestampType, true, Metadata.empty()),
			new StructField(
				"eventId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"eventProperties",
				DataTypes.createMapType(
					DataTypes.StringType, DataTypes.StringType),
				true, Metadata.empty()),
			new StructField(
				"knownIndividual", DataTypes.BooleanType, true,
				Metadata.empty()),
			new StructField("id", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"individualId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"normalizedEventDate", DataTypes.LongType, true,
				Metadata.empty()),
			new StructField(
				"projectId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"projectTimeZoneId", DataTypes.StringType, false,
				Metadata.empty()),
			new StructField(
				"segmentNames",
				DataTypes.createArrayType(DataTypes.StringType, false), true,
				Metadata.empty()),
			new StructField(
				"sessionId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"userId", DataTypes.StringType, true, Metadata.empty()),
			new StructField(
				"variantId", DataTypes.StringType, true, Metadata.empty())
		});

	private static final Encoder<Tuple2<String, Date>> _KEY_ENCODER =
		Encoders.tuple(Encoders.STRING(), Encoders.DATE());

	private static final Encoder<Session> _SESSION_ENCODER = Encoders.bean(
		Session.class);

	private static final String _TABLE_FORMAT_JSON = "json";

	private static final Log _log = LogFactory.getLog(SessionSparkJob.class);

	private long _backoffAttemptNumber;
	private final long _backoffMaxAttempts;
	private final long _backoffMaxWaitingTime;
	private long _backoffWaitingTime = 10000;
	private final String _checkpointPath;
	private final String _eventsPath;
	private final SessionBatchSinkFunction _sessionBatchSinkFunction;
	private final Duration _sessionDuration;
	private final SessionFlatMapGroupsWithStateFunction
		_sessionFlatMapGroupsWithState;
	private final SparkSession _sparkSession;

}