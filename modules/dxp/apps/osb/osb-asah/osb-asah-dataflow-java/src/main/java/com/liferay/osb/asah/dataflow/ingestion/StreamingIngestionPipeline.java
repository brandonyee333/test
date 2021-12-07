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

import com.liferay.osb.asah.dataflow.io.WriteToText;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;

import org.joda.time.Duration;

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
			"Read Pub/Sub Messages",
			PubsubIO.readStrings(
			).fromSubscription(
				streamingIngestionPipelineOptions.getInputSubscription()
			)
		).apply(
			streamingIngestionPipelineOptions.getWindowDuration() +
				" minute(s) Window",
			Window.into(
				FixedWindows.of(
					Duration.standardMinutes(
						streamingIngestionPipelineOptions.getWindowDuration())))
		).apply(
			"Write File(s)",
			new WriteToText.WriteOneFilePerWindow(
				streamingIngestionPipelineOptions.getOutputFileNamePrefix(),
				streamingIngestionPipelineOptions.getOutputDirectory())
		);

		return pipeline.run();
	}

}