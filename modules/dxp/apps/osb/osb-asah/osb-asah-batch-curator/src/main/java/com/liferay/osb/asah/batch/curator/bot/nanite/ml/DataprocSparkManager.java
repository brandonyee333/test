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

package com.liferay.osb.asah.batch.curator.bot.nanite.ml;

import com.google.cloud.dataproc.v1.Job;
import com.google.cloud.dataproc.v1.JobControllerClient;
import com.google.cloud.dataproc.v1.JobControllerSettings;
import com.google.cloud.dataproc.v1.JobPlacement;
import com.google.cloud.dataproc.v1.PySparkJob;
import com.google.cloud.dataproc.v1.SubmitJobRequest;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.io.IOException;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class DataprocSparkManager implements SparkManager {

	@Override
	public void submitJob(
		List<String> arguments, String configuration, String name) {

		try {
			JobControllerClient jobControllerClient =
				_buildJobControllerClient();

			jobControllerClient.submitJob(
				_buildSubmitJobRequest(arguments, configuration, name));

			jobControllerClient.close();
		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage(), ioe);
		}
	}

	private Job _buildJob(
		List<String> arguments, String configuration, String name) {

		Job.Builder builder = Job.newBuilder();

		builder.setPlacement(_buildJobPlacement());
		builder.setPysparkJob(_buildPySparkJob(arguments, configuration, name));

		return builder.build();
	}

	private JobControllerClient _buildJobControllerClient() throws IOException {
		JobControllerSettings.Builder builder =
			JobControllerSettings.newBuilder();

		builder.setEndpoint(
			String.format("%s-dataproc.googleapis.com:443", _region));

		return JobControllerClient.create(builder.build());
	}

	private JobPlacement _buildJobPlacement() {
		JobPlacement.Builder builder = JobPlacement.newBuilder();

		builder.setClusterName(_clusterName);

		return builder.build();
	}

	private PySparkJob _buildPySparkJob(
		List<String> arguments, String configuration, String name) {

		PySparkJob.Builder builder = PySparkJob.newBuilder();

		builder.addArgs(name);

		builder.addArgs("--configuration");

		builder.addArgs(configuration);

		builder.addAllArgs(arguments);

		builder.addFileUris(
			String.format("gs://%s/resources/%s", _bucket, configuration));

		builder.setMainPythonFileUri(
			String.format("gs://%s/osb-asah-spark.py", _bucket));

		return builder.build();
	}

	private SubmitJobRequest _buildSubmitJobRequest(
		List<String> arguments, String configuration, String name) {

		SubmitJobRequest.Builder builder = SubmitJobRequest.newBuilder();

		builder.setJob(_buildJob(arguments, configuration, name));
		builder.setProjectId(_projectId);
		builder.setRegion(_region);

		return builder.build();
	}

	private static final Log _log = LogFactory.getLog(
		DataprocSparkManager.class);

	@Value("${osb.asah.spark.manager.bucket:analytics-cloud-osbasahspark}")
	private String _bucket;

	@Value("${osb.asah.spark.manager.dataproc.cluster.name:cluster-d9ec}")
	private String _clusterName;

	@Value(
		"${osb.asah.spark.manager.dataproc.project.id:liferaycloud-customer-ac}"
	)
	private String _projectId;

	@Value("${osb.asah.spark.manager.dataproc.region:us-west1}")
	private String _region;

}