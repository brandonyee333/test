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

import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;

import org.apache.beam.sdk.transforms.DoFn;

/**
 * @author Riccardo Ferrari
 */
public class DXPEntityPubsubMessageParser
	extends DoFn<String, DXPEntityPubsubMessage> {

	@ProcessElement
	public void processElement(ProcessContext processContext) {
		try {
			DXPEntityPubsubMessage dxpEntityPubsubMessage =
				ObjectMapperUtil.readValue(
					DXPEntityPubsubMessage.class, processContext.element());

			processContext.output(dxpEntityPubsubMessage);
		}
		catch (Exception exception) {
			throw new RuntimeException(
				"Unable to parse DXP Entity Pubsub Message", exception);
		}
	}

}