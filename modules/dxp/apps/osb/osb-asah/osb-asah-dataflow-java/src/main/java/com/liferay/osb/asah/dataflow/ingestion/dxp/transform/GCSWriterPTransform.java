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

package com.liferay.osb.asah.dataflow.ingestion.dxp.transform;

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;

import org.apache.beam.sdk.coders.StringUtf8Coder;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.transforms.Contextful;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.SerializableFunction;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.POutput;

/**
 * @author Riccardo Ferrari
 */
public class GCSWriterPTransform
	extends PTransform<PCollection<DXPEntityPubsubMessage>, POutput> {

	public GCSWriterPTransform(
		String fileNamePrefix, String fileNameSuffix, String gcsBucket,
		int numShards) {

		_fileNamePrefix = fileNamePrefix;
		_fileNameSuffix = fileNameSuffix;
		_gcsBucket = gcsBucket;
		_numShards = numShards;
	}

	@Override
	public POutput expand(PCollection<DXPEntityPubsubMessage> input) {
		return input.apply(
			FileIO.<String, DXPEntityPubsubMessage>writeDynamic(
			).by(
				dxpEntityPubsubMessage -> {
					DXPEntityPubsubMessage.Attributes attributes =
						dxpEntityPubsubMessage.getAttributes();

					return String.format(
						"%s/%s/%s/%s/%s/", attributes.getProjectId(),
						attributes.getDataSourceId(),
						attributes.getResourceName(),
						attributes.getUploadType(), attributes.getUploadTime());
				}
			).via(
				Contextful.fn(
					(SerializableFunction<DXPEntityPubsubMessage, String>)
						DXPEntityPubsubMessage::getPayload),
				TextIO.sink()
			).to(
				_gcsBucket
			).withDestinationCoder(
				StringUtf8Coder.of()
			).withNumShards(
				_numShards
			).withNaming(
				new SerializableFunction<String, FileIO.Write.FileNaming>() {

					@Override
					public FileIO.Write.FileNaming apply(String input) {
						return FileIO.Write.defaultNaming(
							input + _fileNamePrefix, _fileNameSuffix);
					}

				}
			));
	}

	private final String _fileNamePrefix;
	private final String _fileNameSuffix;
	private final String _gcsBucket;
	private final int _numShards;

}