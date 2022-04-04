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

package com.liferay.osb.asah.dataflow.ingestion.dxp.util;

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.BaseDXPEntity;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.BaseParserPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.BigQueryWriterPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.FixedDurationOrCountWindowPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.GCSWriterPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.PubsubReaderPTransform;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.transforms.Values;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;

import org.joda.time.Duration;

/**
 * @author Riccardo Ferrari
 * @author Rachael Koestartyo
 */
public class PipelineBuilder {

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
			parser.getSuccessTupleTag()
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
				_parser.getFailTupleTag()
			).apply(
				Values.create()
			);

		_writeToGCS(
			gcsBucket, failedParsePCollection, shardCount, triggerElementCount,
			triggerInterval);

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
			new PubsubReaderPTransform(subscription));

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
				triggerElementCount, Duration.standardSeconds(triggerInterval))
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