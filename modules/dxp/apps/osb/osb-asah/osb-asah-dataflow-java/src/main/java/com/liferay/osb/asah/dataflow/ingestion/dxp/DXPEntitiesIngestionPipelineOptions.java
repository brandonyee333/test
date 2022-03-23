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

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

/**
 * @author Riccardo Ferrari
 */
public interface DXPEntitiesIngestionPipelineOptions
	extends DataflowPipelineOptions {

	@Description("Return the GCS output bucket")
	@Validation.Required
	public String getGCSBucket();

	@Description(
		"Return the pubsub subscription name. The name should be in the format of projects/<project-id>/subscriptions/<subscription-name>."
	)
	@Validation.Required
	public String getPubsubSubscription();

	@Description("Return the shard count")
	@Validation.Required
	public int getShardCount();

	@Description("Return the trigger minimum element count")
	@Validation.Required
	public int getTriggerElementCount();

	@Description("Return the trigger interval duration in seconds")
	@Validation.Required
	public long getTriggerIntervalDuration();

	public void setGCSBucket(String gcsBucket);

	public void setPubsubSubscription(String pubsubSubscription);

	public void setShardCount(int shardCount);

	public void setTriggerElementCount(int triggerElementCount);

	public void setTriggerIntervalDuration(long triggerIntervalDuration);

}