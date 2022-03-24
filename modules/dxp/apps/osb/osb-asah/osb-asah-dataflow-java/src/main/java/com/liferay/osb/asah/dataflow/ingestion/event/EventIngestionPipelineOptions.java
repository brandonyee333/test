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

package com.liferay.osb.asah.dataflow.ingestion.event;

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

/**
 * @author Marcellus Tavares
 */
public interface EventIngestionPipelineOptions extends DataflowPipelineOptions {

	@Description(
		"Return the pub/sub subscription input name. The name should be in the format of projects/<project-id>/subscriptions/<subscription-name>."
	)
	@Validation.Required
	public String getInputSubscription();

	@Description(
		"Return the output directory for the files. The directory must end with a slash."
	)
	@Validation.Required
	public String getOutputDirectory();

	@Default.String("output")
	@Description("Return the output file name prefix. The default is 'output'.")
	public String getOutputFileNamePrefix();

	@Default.Long(5)
	public long getSessionWindowAllowedLateness();

	@Default.Long(1)
	public long getSessionWindowEarlyFiringsInterval();

	@Default.Long(30)
	public long getSessionWindowGapDuration();

	@Default.Long(5)
	@Description(
		"Return the interval in minutes in which the data will be written. The default is 5 minutes."
	)
	public long getWriteInterval();

	public void setInputSubscription(String inputSubscription);

	public void setOutputDirectory(String outputDirectory);

	public void setOutputFileNamePrefix(String outputFileNamePrefix);

	public void setSessionWindowAllowedLateness(long value);

	public void setSessionWindowEarlyFiringsInterval(long value);

	public void setSessionWindowGapDuration(long value);

	public void setWriteInterval(long value);

}