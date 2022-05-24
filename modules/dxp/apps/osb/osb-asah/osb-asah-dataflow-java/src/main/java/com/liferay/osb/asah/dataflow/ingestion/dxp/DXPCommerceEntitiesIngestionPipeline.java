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

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Order;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Product;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.OrderParserPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.transform.ProductParserPTransform;
import com.liferay.osb.asah.dataflow.ingestion.dxp.util.PipelineBuilder;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.options.PipelineOptionsFactory;

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
			dxpCommerceEntitiesIngestionPipelineOptions.getOrderBigQueryTable()
		).withFailedParsedItemsToGCS(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed/parse",
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).withFailedBigQueryItemsToGCS(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed/bigquery",
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
				getProductBigQueryTable()
		).withFailedParsedItemsToGCS(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed/parse",
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).withFailedBigQueryItemsToGCS(
			dxpCommerceEntitiesIngestionPipelineOptions.getGCSBucket() +
				"failed/bigquery",
			dxpCommerceEntitiesIngestionPipelineOptions.getShardCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerElementCount(),
			dxpCommerceEntitiesIngestionPipelineOptions.
				getTriggerIntervalDuration()
		).build();

		return pipeline.run();
	}

}