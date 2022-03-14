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

import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.FixedDurationOrCountWindowPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.GCSWriterPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.PubsubReaderPTransform;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

import org.joda.time.Duration;

/**
 * @author Riccardo Ferrari
 */
public class DXPEntitiesIngestionPipeline {

	public static void main(String[] args) {
		run(
			PipelineOptionsFactory.fromArgs(
				args
			).withValidation(
			).as(
				DXPEntitiesIngestionPipelineOptions.class
			));
	}

	public static PipelineResult run(
		DXPEntitiesIngestionPipelineOptions
			dxpEntitiesIngestionPipelineOptions) {

		Pipeline pipeline = Pipeline.create(
			dxpEntitiesIngestionPipelineOptions);

		pipeline.apply(
			String.format(
				"Read Pubsub Subscription (%s)",
				dxpEntitiesIngestionPipelineOptions.getPubsubSubscription()),
			new PubsubReaderPTransform(
				dxpEntitiesIngestionPipelineOptions.getPubsubSubscription())
		).apply(
			String.format(
				"Window By %s Elements Count Or %s Seconds",
				dxpEntitiesIngestionPipelineOptions.getTriggerElementCount(),
				dxpEntitiesIngestionPipelineOptions.
					getTriggerIntervalDuration()),
			new FixedDurationOrCountWindowPTransform<>(
				dxpEntitiesIngestionPipelineOptions.getTriggerElementCount(),
				Duration.standardSeconds(
					dxpEntitiesIngestionPipelineOptions.
						getTriggerIntervalDuration()))
		).apply(
			String.format(
				"Write To GCS Bucket %s Using %s Shard Count",
				dxpEntitiesIngestionPipelineOptions.getGCSBucket(),
				dxpEntitiesIngestionPipelineOptions.getShardCount()),
			new GCSWriterPTransform(
				"part", ".json",
				dxpEntitiesIngestionPipelineOptions.getGCSBucket(),
				dxpEntitiesIngestionPipelineOptions.getShardCount())
		);

		return pipeline.run();
	}

}