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

package com.liferay.osb.asah.dataflow.ingestion.dxp.function;

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.PubsubMessageAttributes;

import java.nio.charset.StandardCharsets;

import org.apache.beam.sdk.io.gcp.pubsub.PubsubMessage;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

/**
 * @author Riccardo Ferrari
 */
public class PubsubMessageParserDoFn
	extends DoFn<PubsubMessage, KV<PubsubMessageAttributes, String>> {

	@ProcessElement
	public void processElement(ProcessContext processContext) {
		PubsubMessage pubsubMessage = processContext.element();

		PubsubMessageAttributes pubsubMessageAttributes =
			new PubsubMessageAttributes(pubsubMessage.getAttributeMap());

		String payload = new String(
			pubsubMessage.getPayload(), StandardCharsets.UTF_8);

		processContext.output(KV.of(pubsubMessageAttributes, payload));
	}

}