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
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.windowing.FixedWindows;
import org.apache.beam.sdk.transforms.windowing.Window;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

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

		PCollection<String> pubSubMessages = pipeline.apply(
			"Read PubSub Messages",
			PubsubIO.readStrings(
			).fromSubscription(
				streamingIngestionPipelineOptions.getInputSubscription()
			));

		pubSubMessages.apply(
			"Backup PubSub Messages",
			new BackupPubSubMessages(
				streamingIngestionPipelineOptions.getOutputDirectory(),
				streamingIngestionPipelineOptions.getOutputFileNamePrefix(),
				streamingIngestionPipelineOptions.getWriteInterval()));

		return pipeline.run();
	}

	public static class BackupPubSubMessages
		extends PTransform<PCollection<String>, PDone> {

		public BackupPubSubMessages(
			String outputDirectory, String outputFileNamePrefix,
			long writeIntervalInMinutes) {

			_outputDirectory = outputDirectory;
			_outputFileNamePrefix = outputFileNamePrefix;
			_writeIntervalInMinutes = writeIntervalInMinutes;
		}

		@Override
		public PDone expand(PCollection<String> pCollection) {
			return pCollection.apply(
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

		private final String _outputDirectory;
		private final String _outputFileNamePrefix;
		private final long _writeIntervalInMinutes;

	}

}