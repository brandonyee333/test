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

package com.liferay.osb.asah.dataflow.ingestion.dxp;

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.BaseDXPEntity;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Order;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Product;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.BaseParserPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.BigQueryWriterPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.FixedDurationOrCountWindowPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.GCSWriterPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.OrderParserPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.ProductParserPTransform;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.transforms.Values;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;

import org.joda.time.Duration;

/**
 * @author Riccardo Ferrari
 */
public class DXPCommerceEntitiesIngestionPipeline {

	public static void main(String[] args) {
		run(
			PipelineOptionsFactory.fromArgs(
				args
			).withValidation(
			).as(
				DXPCommerceEntitiesIngestionPipelineOptions.class
			));
	}

	public static PipelineResult run(
		DXPCommerceEntitiesIngestionPipelineOptions
			dxpCommerceEntitiesIngestionPipelineOptions) {

		// Default

		PipelineBuilder defaultPipelineBuilder = new PipelineBuilder(
			Pipeline.create(dxpCommerceEntitiesIngestionPipelineOptions));

		Pipeline pipeline = defaultPipelineBuilder.withPubsubSubscription(
			"Default",
			dxpCommerceEntitiesIngestionPipelineOptions.
				getDefaultPubsubSubscription()
		).withGCSWriter(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket(),
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).build();

		// Order

		PipelineBuilder orderPipelineBuilder = new PipelineBuilder(pipeline);

		pipeline = orderPipelineBuilder.withPubsubSubscription(
			"Order",
			dxpCommerceEntitiesIngestionPipelineOptions.
				getOrderPubsubSubscription()
		).withGCSWriter(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket(),
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).<Order>withBigQueryWriter(
			new OrderParserPTransform(),
			dxpCommerceEntitiesIngestionPipelineOptions.getOrderBigqueryTable()
		).withFailedParsedItemsToGCS(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed",
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).build();

		// Product

		PipelineBuilder productPipelineBuilder = new PipelineBuilder(pipeline);

		pipeline = productPipelineBuilder.withPubsubSubscription(
			"Product",
			dxpCommerceEntitiesIngestionPipelineOptions.
				getProductPubsubSubscription()
		).withGCSWriter(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket(),
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).<Product>withBigQueryWriter(
			new ProductParserPTransform(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getProductBigqueryTable()
		).withFailedParsedItemsToGCS(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed",
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).build();

		return pipeline.run();
	}

	public static class PipelineBuilder {

		public PipelineBuilder(Pipeline pipeline) {
			_pipeline = pipeline;
		}

		public Pipeline build() {
			return _pipeline;
		}

		public <T extends BaseDXPEntity> PipelineBuilder withBigQueryWriter(
			BaseParserPTransform<T> parser, String table) {

			_parser = parser;

			_parsedMessages = _pubsubMessages.apply(parser);

			_parsedMessages.get(
				parser.getSuccessTag()
			).apply(
				new BigQueryWriterPTransform<>(table)
			);

			return this;
		}

		public PipelineBuilder withFailedParsedItemsToGCS(
			String gcsBucket, int shardCount, int triggerElementCount,
			long triggerInterval) {

			PCollection<DXPEntityPubsubMessage> failedParsePCollection =
				_parsedMessages.get(
					_parser.getFailTag()
				).apply(
					Values.create()
				);

			_writeToGCS(
				gcsBucket, failedParsePCollection, shardCount,
				triggerElementCount, triggerInterval);

			return this;
		}

		public PipelineBuilder withGCSWriter(
			String gcsBucket, int shardCount, int triggerElementCount,
			long triggerInterval) {

			_writeToGCS(
				gcsBucket, _pubsubMessages, shardCount, triggerElementCount,
				triggerInterval);

			return this;
		}

		public PipelineBuilder withPubsubSubscription(
			String title, String subscription) {

			_pubsubMessages = _pipeline.apply(
				"Read Pubsub Subscription " + title,
				PubsubIO.readMessagesWithAttributes(
				).fromSubscription(
					subscription
				)
			).apply(
				MapElements.via(
					new SimpleFunction
						<PubsubMessage, DXPEntityPubsubMessage>() {

						@Override
						public DXPEntityPubsubMessage apply(
							PubsubMessage pubsubMessage) {

							return new DXPEntityPubsubMessage(pubsubMessage);
						}

					})
			);

			return this;
		}

		private void _writeToGCS(
			String gcsBucket, PCollection<DXPEntityPubsubMessage> pCollection,
			int shardCount, int triggerElementCount, long triggerInterval) {

			pCollection.apply(
				String.format(
					"Window By %s Elements Count Or %s Seconds",
					triggerElementCount, triggerInterval),
				new FixedDurationOrCountWindowPTransform<>(
					triggerElementCount,
					Duration.standardSeconds(triggerInterval))
			).apply(
				String.format(
					"Write to GCS Bucket %s Using %s Shard Count", gcsBucket,
					shardCount),
				new GCSWriterPTransform("part", ".json", gcsBucket, shardCount)
			);
		}

		private PCollectionTuple _parsedMessages;
		private BaseParserPTransform<?> _parser;
		private final Pipeline _pipeline;
		private PCollection<DXPEntityPubsubMessage> _pubsubMessages;

	}

}