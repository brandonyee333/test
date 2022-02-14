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

package com.liferay.osb.asah.dataflow.ingestion;

import com.google.api.services.bigquery.model.TableRow;

import com.liferay.osb.asah.dataflow.common.DateUtil;
import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;
import com.liferay.osb.asah.dataflow.io.WriteToText;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import java.util.Map;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.WriteResult;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.GroupByKey;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SerializableFunction;
import org.apache.beam.sdk.transforms.WithKeys;
import org.apache.beam.sdk.transforms.windowing.AfterProcessingTime;
import org.apache.beam.sdk.transforms.windowing.AfterWatermark;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.IntervalWindow;
import org.apache.beam.sdk.transforms.windowing.PaneInfo;
import org.apache.beam.sdk.transforms.windowing.Repeatedly;
import org.apache.beam.sdk.transforms.windowing.Sessions;
import org.apache.beam.sdk.transforms.windowing.TimestampCombiner;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;
import org.apache.beam.sdk.values.TypeDescriptor;
import org.apache.commons.codec.digest.DigestUtils;

import org.joda.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcellus Tavares
 */
public class StreamingIngestionPipeline {

	public static void main(String[] args) {
		run(
			PipelineOptionsFactory.fromArgs(
				args
			).withValidation(
			).as(
				StreamingIngestionPipelineOptions.class
			));
	}

	public static PipelineResult run(
		StreamingIngestionPipelineOptions streamingIngestionPipelineOptions) {

		Pipeline pipeline = Pipeline.create(streamingIngestionPipelineOptions);

		pipeline.apply(
			"Backup PubSub Messages",
			new BackupPubsubMessages(
				streamingIngestionPipelineOptions.getInputSubscription(),
				streamingIngestionPipelineOptions.getOutputDirectory(),
				streamingIngestionPipelineOptions.getOutputFileNamePrefix(),
				streamingIngestionPipelineOptions.getWriteInterval()));

		PCollection<KV<String, Iterable<AnalyticsEvent>>>
			sessionizedAnalyticsEventsPCollection = pipeline.apply(
				"Read PubSub Messages",
				PubsubIO.readStrings(
				).fromSubscription(
					streamingIngestionPipelineOptions.getInputSubscription()
				).withTimestampAttribute(
					"eventDate"
				)
			).apply(
				"Parse Analytics Events", ParDo.of(new AnalyticsEventParser())
			).apply(
				"Create Sessions",
				new Sessionizer(
					streamingIngestionPipelineOptions.
						getSessionWindowAllowedLateness(),
					streamingIngestionPipelineOptions.
						getSessionWindowEarlyFiringsInterval(),
					streamingIngestionPipelineOptions.
						getSessionWindowGapDuration())
			);

		sessionizedAnalyticsEventsPCollection.apply(
			"Write Events",
			new EventBigQueryWriter(
				streamingIngestionPipelineOptions.getEventTableName()));
		sessionizedAnalyticsEventsPCollection.apply(
			"Write Event Properties",
			new EventPropertyBigQueryWriter(
				streamingIngestionPipelineOptions.getEventPropertyTableName()));
		sessionizedAnalyticsEventsPCollection.apply(
			"Write Sessions",
			new SessionBigQueryWriter(
				streamingIngestionPipelineOptions.getSessionTableName()));

		return pipeline.run();
	}

	public static class AnalyticsEventParser
		extends DoFn<String, AnalyticsEvent> {

		@ProcessElement
		public void processElement(ProcessContext processContext) {
			try {
				AnalyticsEvent analyticsEvent = ObjectMapperUtil.readValue(
					AnalyticsEvent.class, processContext.element());

				processContext.output(analyticsEvent);
			}
			catch (Exception exception) {
				_logger.error(
					"Unable to parse analytics event message {}",
					processContext.element());
			}
		}

		private static final Logger _logger = LoggerFactory.getLogger(
			AnalyticsEventParser.class);

	}

	public static class BackupPubsubMessages extends PTransform<PBegin, PDone> {

		public BackupPubsubMessages(
			String inputSubscription, String outputDirectory,
			String outputFileNamePrefix, long writeIntervalInMinutes) {

			_inputSubscription = inputSubscription;
			_outputDirectory = outputDirectory;
			_outputFileNamePrefix = outputFileNamePrefix;
			_writeIntervalInMinutes = writeIntervalInMinutes;
		}

		@Override
		public PDone expand(PBegin pBegin) {
			return pBegin.apply(
				PubsubIO.readStrings(
				).fromSubscription(
					String.format("%s_backup", _inputSubscription)
				)
			).apply(
				_writeIntervalInMinutes + " Minutes Window",
				Window.into(
					FixedWindows.of(
						Duration.standardMinutes(_writeIntervalInMinutes)))
			).apply(
				"Write Files",
				new WriteToText.WriteOneFilePerWindow(
					_outputFileNamePrefix, _outputDirectory)
			);
		}

		private final String _inputSubscription;
		private final String _outputDirectory;
		private final String _outputFileNamePrefix;
		private final long _writeIntervalInMinutes;

	}

	public static class EventBigQueryWriter
		extends PTransform
			<PCollection<KV<String, Iterable<AnalyticsEvent>>>, WriteResult> {

		public EventBigQueryWriter(String eventTableName) {
			_eventTableName = eventTableName;
		}

		@Override
		public WriteResult expand(
			PCollection<KV<String, Iterable<AnalyticsEvent>>> pCollection) {

			return pCollection.apply(
				"Create Event Table Rows",
				ParDo.of(
					new DoFn<KV<String, Iterable<AnalyticsEvent>>, TableRow>() {

						@ProcessElement
						public void process(
							IntervalWindow intervalWindow, PaneInfo paneInfo,
							ProcessContext processContext) {

							KV<String, Iterable<AnalyticsEvent>> element =
								processContext.element();

							String sessionId = DigestUtils.sha256Hex(
								element.getKey() + "#" +
									intervalWindow.start());

							for (AnalyticsEvent analyticsEvent :
									element.getValue()) {

								_outputEventTableRow(
									analyticsEvent, processContext, sessionId);
							}
						}

					})
			).apply(
				"Write Event Properties Rows to Big Query",
				BigQueryIO.writeTableRows(
				).to(
					_eventTableName
				).withCreateDisposition(
					BigQueryIO.Write.CreateDisposition.CREATE_NEVER
				).withMethod(
					BigQueryIO.Write.Method.STREAMING_INSERTS
				).withWriteDisposition(
					BigQueryIO.Write.WriteDisposition.WRITE_APPEND
				).withoutValidation()
			);
		}

		private final String _eventTableName;

	}

	public static class EventPropertyBigQueryWriter
		extends PTransform
			<PCollection<KV<String, Iterable<AnalyticsEvent>>>, WriteResult> {

		public EventPropertyBigQueryWriter(String eventPropertyTableName) {
			_eventPropertyTableName = eventPropertyTableName;
		}

		@Override
		public WriteResult expand(
			PCollection<KV<String, Iterable<AnalyticsEvent>>> pCollection) {

			return pCollection.apply(
				"Create Event Properties Table Rows",
				ParDo.of(
					new DoFn<KV<String, Iterable<AnalyticsEvent>>, TableRow>() {

						@ProcessElement
						public void process(ProcessContext processContext) {
							KV<String, Iterable<AnalyticsEvent>> element =
								processContext.element();

							for (AnalyticsEvent analyticsEvent :
									element.getValue()) {

								_outputEventPropertyTableRows(
									analyticsEvent, processContext);
							}
						}

					})
			).apply(
				"Write Event Property Rows to Big Query",
				BigQueryIO.writeTableRows(
				).to(
					_eventPropertyTableName
				).withCreateDisposition(
					BigQueryIO.Write.CreateDisposition.CREATE_NEVER
				).withMethod(
					BigQueryIO.Write.Method.STREAMING_INSERTS
				).withWriteDisposition(
					BigQueryIO.Write.WriteDisposition.WRITE_APPEND
				).withoutValidation()
			);
		}

		private final String _eventPropertyTableName;

	}

	public static class SessionBigQueryWriter
		extends PTransform
			<PCollection<KV<String, Iterable<AnalyticsEvent>>>, WriteResult> {

		public SessionBigQueryWriter(String sessionTableName) {
			_sessionTableName = sessionTableName;
		}

		@Override
		public WriteResult expand(
			PCollection<KV<String, Iterable<AnalyticsEvent>>> pCollection) {

			return pCollection.apply(
				"Create Session Table Rows",
				ParDo.of(
					new DoFn<KV<String, Iterable<AnalyticsEvent>>, TableRow>() {

						@ProcessElement
						public void process(
							IntervalWindow intervalWindow, PaneInfo paneInfo,
							ProcessContext processContext) {

							if (paneInfo.isLast()) {
								_outputSessionTableRow(
									intervalWindow, processContext);
							}
						}

					})
			).apply(
				"Write Session Rows to Big Query",
				BigQueryIO.writeTableRows(
				).to(
					_sessionTableName
				).withCreateDisposition(
					BigQueryIO.Write.CreateDisposition.CREATE_NEVER
				).withMethod(
					BigQueryIO.Write.Method.STREAMING_INSERTS
				).withWriteDisposition(
					BigQueryIO.Write.WriteDisposition.WRITE_APPEND
				).withoutValidation()
			);
		}

		private final String _sessionTableName;

	}

	public static class Sessionizer
		extends PTransform
			<PCollection<AnalyticsEvent>,
			 PCollection<KV<String, Iterable<AnalyticsEvent>>>> {

		public Sessionizer(
			long sessionWindowAllowedLatenessInMinutes,
			long sessionWindowEarlyFiringIntervalInMinutes,
			long sessionWindowGapDurationInMinutes) {

			_sessionWindowAllowedLatenessInMinutes =
				sessionWindowAllowedLatenessInMinutes;
			_sessionWindowEarlyFiringIntervalInMinutes =
				sessionWindowEarlyFiringIntervalInMinutes;
			_sessionWindowGapDurationInMinutes =
				sessionWindowGapDurationInMinutes;
		}

		@Override
		public PCollection<KV<String, Iterable<AnalyticsEvent>>> expand(
			PCollection<AnalyticsEvent> pCollection) {

			return pCollection.apply(
				"Add Session Key", _buildWithKeysPTransform()
			).apply(
				"Apply Session Window", _buildSessionWindow()
			).apply(
				GroupByKey.create()
			);
		}

		private Window<KV<String, AnalyticsEvent>> _buildSessionWindow() {
			return Window.<KV<String, AnalyticsEvent>>into(
				Sessions.withGapDuration(
					Duration.standardMinutes(
						_sessionWindowGapDurationInMinutes))
			).triggering(
				Repeatedly.forever(
					AfterWatermark.pastEndOfWindow(
					).withEarlyFirings(
						AfterProcessingTime.pastFirstElementInPane(
						).plusDelayOf(
							Duration.standardMinutes(
								_sessionWindowEarlyFiringIntervalInMinutes)
						)
					))
			).withAllowedLateness(
				Duration.standardMinutes(
					_sessionWindowAllowedLatenessInMinutes),
				Window.ClosingBehavior.FIRE_ALWAYS
			).discardingFiredPanes(
			).withTimestampCombiner(
				TimestampCombiner.END_OF_WINDOW
			);
		}

		private WithKeys<String, AnalyticsEvent> _buildWithKeysPTransform() {
			WithKeys<String, AnalyticsEvent> withKeys = WithKeys.of(
				new SerializableFunction<AnalyticsEvent, String>() {

					@Override
					public String apply(AnalyticsEvent analyticsEvent) {
						ZonedDateTime utcZonedDateTime =
							DateUtil.toUTCZonedDateTime(
								analyticsEvent.eventDate);

						ZonedDateTime projectZonedDateTime =
							utcZonedDateTime.withZoneSameInstant(
								ZoneId.of(analyticsEvent.projectTimeZoneId));

						return String.format(
							"%s#%s#%s#%s#%s", analyticsEvent.projectId,
							analyticsEvent.dataSourceId,
							analyticsEvent.channelId, analyticsEvent.userId,
							projectZonedDateTime.toLocalDate());
					}

				});

			return withKeys.withKeyType(TypeDescriptor.of(String.class));
		}

		private final long _sessionWindowAllowedLatenessInMinutes;
		private final long _sessionWindowEarlyFiringIntervalInMinutes;
		private final long _sessionWindowGapDurationInMinutes;

	}

	private static void _outputEventPropertyTableRows(
		AnalyticsEvent analyticsEvent, DoFn.ProcessContext processContext) {

		Map<String, String> eventProperties = analyticsEvent.eventProperties;

		for (Map.Entry<String, String> entry : eventProperties.entrySet()) {
			TableRow tableRow = new TableRow();

			tableRow.set("channelId", Long.parseLong(analyticsEvent.channelId));
			tableRow.set("eventDate", analyticsEvent.eventDate);
			tableRow.set("id", analyticsEvent.id);
			tableRow.set("name", entry.getKey());
			tableRow.set("projectId", analyticsEvent.projectId);
			tableRow.set("value", entry.getValue());

			processContext.output(tableRow);
		}
	}

	private static void _outputEventTableRow(
		AnalyticsEvent analyticsEvent, DoFn.ProcessContext processContext,
		String sessionId) {

		TableRow tableRow = new TableRow();

		tableRow.set("applicationId", analyticsEvent.applicationId);

		Map<String, String> context = analyticsEvent.context;

		tableRow.set("browserName", context.get("browserName"));
		tableRow.set("canonicalUrl", context.get("canonicalUrl"));

		tableRow.set("channelId", Long.parseLong(analyticsEvent.channelId));
		tableRow.set("city", context.get("city"));
		tableRow.set("contentLanguageId", context.get("contentLanguageId"));
		tableRow.set("context", ObjectMapperUtil.writeValueAsString(context));
		tableRow.set("country", context.get("country"));
		tableRow.set("createDate", analyticsEvent.createDate);
		tableRow.set(
			"dataSourceId", Long.parseLong(analyticsEvent.dataSourceId));
		tableRow.set("description", context.get("description"));
		tableRow.set("deviceType", context.get("deviceType"));
		tableRow.set("eventDate", analyticsEvent.eventDate);
		tableRow.set("eventId", analyticsEvent.eventId);
		tableRow.set(
			"eventProperties",
			ObjectMapperUtil.writeValueAsString(
				analyticsEvent.eventProperties));
		tableRow.set("experienceId", context.get("experienceId"));
		tableRow.set("id", analyticsEvent.id);
		tableRow.set(
			"individualId", Long.parseLong(analyticsEvent.individualId));
		tableRow.set("keywords", context.get("keywords"));
		tableRow.set("knownIndividual", analyticsEvent.knownIndividual);
		tableRow.set("languageId", context.get("languageId"));
		tableRow.set("platformName", context.get("platformName"));
		tableRow.set("projectId", analyticsEvent.projectId);
		tableRow.set("projectTimeZoneId", analyticsEvent.projectTimeZoneId);
		tableRow.set("referrer", context.get("referrer"));
		tableRow.set("region", context.get("region"));
		tableRow.set("sessionId", sessionId);
		tableRow.set("segmentNames", analyticsEvent.segmentNames);
		tableRow.set("timezoneOffset", context.get("timezoneOffset"));
		tableRow.set("title", context.get("title"));
		tableRow.set("url", context.get("url"));
		tableRow.set("userId", analyticsEvent.userId);
		tableRow.set("variantId", context.get("variantId"));

		processContext.output(tableRow);
	}

	private static void _outputSessionTableRow(
		IntervalWindow intervalWindow, DoFn.ProcessContext processContext) {

		TableRow tableRow = new TableRow();

		KV<String, Iterable<AnalyticsEvent>> element =
			(KV<String, Iterable<AnalyticsEvent>>)processContext.element();

		String sessionKey = element.getKey();

		String[] sessionKeyParts = sessionKey.split("#");

		tableRow.set("channelId", Long.parseLong(sessionKeyParts[2]));

		tableRow.set(
			"id",
			DigestUtils.sha256Hex(sessionKey + "#" + intervalWindow.start()));
		tableRow.set("projectId", sessionKeyParts[0]);
		tableRow.set("sessionEnd", String.valueOf(intervalWindow.end()));
		tableRow.set("sessionStart", String.valueOf(intervalWindow.start()));

		processContext.output(tableRow);
	}

}