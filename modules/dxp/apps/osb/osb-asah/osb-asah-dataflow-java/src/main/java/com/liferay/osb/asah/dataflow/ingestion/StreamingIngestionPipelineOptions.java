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

import org.apache.beam.runners.dataflow.options.DataflowPipelineOptions;
import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.Validation;

/**
 * @author Marcellus Tavares
 */
public interface StreamingIngestionPipelineOptions
	extends DataflowPipelineOptions {

	@Validation.Required
	public String getInputSubscription();

	@Validation.Required
	public String getOutputDirectory();

	@Default.String("output")
	public String getOutputFileNamePrefix();

	@Default.Long(5)
	public long getWindowDuration();

	public void setInputSubscription(String inputSubscription);

	public void setOutputDirectory(String outputDirectory);

	public void setOutputFileNamePrefix(String outputFileNamePrefix);

	public void setWindowDuration(long value);

}