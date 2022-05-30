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

import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.DXPEntityParserPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.util.PipelineBuilder;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

/**
 * @author Marcos Martins
 * @author Rachael Koestartyo
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

		PipelineBuilder defaultPipelineBuilder = new PipelineBuilder(
			Pipeline.create(dxpEntitiesIngestionPipelineOptions));

		Pipeline pipeline = defaultPipelineBuilder.withBigQueryWriter(
			new DXPEntityParserPTransform(), "dxpentity"
		).withFailedParsedItemsToGCS(
			dxpEntitiesIngestionPipelineOptions.getGCSBucket() + "failed/parse",
			dxpEntitiesIngestionPipelineOptions.getShardCount(),
			dxpEntitiesIngestionPipelineOptions.getTriggerElementCount(),
			dxpEntitiesIngestionPipelineOptions.getTriggerIntervalDuration()
		).withFailedBigQueryItemsToGCS(
			dxpEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed/bigquery",
			dxpEntitiesIngestionPipelineOptions.getShardCount(),
			dxpEntitiesIngestionPipelineOptions.getTriggerElementCount(),
			dxpEntitiesIngestionPipelineOptions.getTriggerIntervalDuration()
		).withGCSWriter(
			dxpEntitiesIngestionPipelineOptions.getGCSBucket(),
			dxpEntitiesIngestionPipelineOptions.getShardCount(),
			dxpEntitiesIngestionPipelineOptions.getTriggerElementCount(),
			dxpEntitiesIngestionPipelineOptions.getTriggerIntervalDuration()
		).withPubsubSubscription(
			dxpEntitiesIngestionPipelineOptions.getPubsubSubscription(),
			"DXPEntity"
		).build();

		return pipeline.run();
	}

}