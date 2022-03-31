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

import org.apache.beam.sdk.io.gcp.pubsub.PubsubIO;
import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.PBegin;
import org.apache.beam.sdk.values.PCollection;

/**
 * @author Riccardo Ferrari
 */
public class PubsubReaderPTransform
	extends PTransform<PBegin, PCollection<DXPEntityPubsubMessage>> {

	public PubsubReaderPTransform(String pubsubSubscription) {
		_pubsubSubscription = pubsubSubscription;
	}

	@Override
	public PCollection<DXPEntityPubsubMessage> expand(PBegin pBegin) {
		return pBegin.apply(
			PubsubIO.readMessagesWithAttributes(
			).fromSubscription(
				_pubsubSubscription
			)
		).apply(
			MapElements.via(
				new SimpleFunction<PubsubMessage, DXPEntityPubsubMessage>() {

					@Override
					public DXPEntityPubsubMessage apply(
						PubsubMessage pubsubMessage) {

						return new DXPEntityPubsubMessage(pubsubMessage);
					}

				})
		);
	}

	private final String _pubsubSubscription;

}