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

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.PubsubMessageAttributes;

import java.nio.charset.StandardCharsets;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;

/**
 * @author Riccardo Ferrari
 */
public class PubsubReaderPTransform
	extends PTransform
		<PBegin, PCollection<KV<PubsubMessageAttributes, String>>> {

	public PubsubReaderPTransform(String pubsubSubscription) {
		_pubsubSubscription = pubsubSubscription;
	}

	@Override
	public PCollection<KV<PubsubMessageAttributes, String>> expand(
		PBegin pBegin) {

		return pBegin.apply(
			PubsubIO.readMessagesWithAttributes(
			).fromSubscription(
				_pubsubSubscription
			)
		).apply(
			MapElements.via(
				new SimpleFunction
					<PubsubMessage, KV<PubsubMessageAttributes, String>>() {

					@Override
					public KV<PubsubMessageAttributes, String> apply(
						PubsubMessage pubsubMessage) {

						PubsubMessageAttributes pubsubMessageAttributes =
							new PubsubMessageAttributes(
								pubsubMessage.getAttributeMap());

						String payload = new String(
							pubsubMessage.getPayload(), StandardCharsets.UTF_8);

						return KV.of(pubsubMessageAttributes, payload);
					}

				})
		);
	}

	private final String _pubsubSubscription;

}