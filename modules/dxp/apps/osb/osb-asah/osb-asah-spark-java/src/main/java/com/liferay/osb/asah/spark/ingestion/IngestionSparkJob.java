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

package com.liferay.osb.asah.spark.ingestion;

import com.liferay.osb.asah.spark.common.Configuration;
import com.liferay.osb.asah.spark.common.SparkJob;

import java.io.Serializable;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Minutes;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.streaming.pubsub.PubsubUtils;
import org.apache.spark.streaming.pubsub.SparkGCPCredentials;
import org.apache.spark.streaming.pubsub.SparkPubsubMessage;

import scala.Function1;
import scala.Option;

import scala.reflect.ClassTag;

/**
 * @author Marcellus Tavares
 */
public class IngestionSparkJob implements SparkJob {

	public IngestionSparkJob(
		IngestionSparkApplication ingestionSparkApplication) {

		_configuration = ingestionSparkApplication.getConfiguration();
		_sparkSession = ingestionSparkApplication.getSparkSession();
	}

	@Override
	public void run() {
		if (_log.isInfoEnabled()) {
			_log.info("Ingestion Spark job started");
		}

		try {
			_run();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private void _run() {
		long batchIntervalMinutes = Long.parseLong(
			_configuration.get("ingestion.batch.interval.minutes"));

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Ingestion streaming context initialized with batch " +
						"interval of %d minute(s)",
					batchIntervalMinutes));
		}

		StreamingContext streamingContext = new StreamingContext(
			_sparkSession.sparkContext(), Minutes.apply(batchIntervalMinutes));

		SparkGCPCredentials.Builder builder = SparkGCPCredentials.builder();

		DStream<SparkPubsubMessage> dStream = PubsubUtils.createStream(
			streamingContext, _configuration.get("google.projectId"),
			Option.apply(_configuration.get("google.pubsub.topic")),
			_configuration.get("google.pubsub.subscription"), builder.build(),
			StorageLevel.MEMORY_AND_DISK(), true);

		dStream.map(
			new SparkPubsubMessageToStringFunction1(),
			ClassTag.apply(String.class)
		).saveAsTextFiles(
			_configuration.get("google.storage.path.ingestion.output"), ""
		);

		streamingContext.start();
		streamingContext.awaitTermination();
	}

	private static final Log _log = LogFactory.getLog(IngestionSparkJob.class);

	private final Configuration _configuration;
	private final SparkSession _sparkSession;

	private static class SparkPubsubMessageToStringFunction1
		implements Function1<SparkPubsubMessage, String>, Serializable {

		@Override
		public String apply(SparkPubsubMessage sparkPubsubMessage) {
			return new String(
				sparkPubsubMessage.getData(), StandardCharsets.UTF_8);
		}

	}

}