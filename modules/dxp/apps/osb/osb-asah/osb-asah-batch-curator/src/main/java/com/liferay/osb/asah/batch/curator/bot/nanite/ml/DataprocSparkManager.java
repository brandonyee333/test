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
import com.google.cloud.dataproc.v1.JobStatus;
import com.google.cloud.dataproc.v1.PySparkJob;
import com.google.cloud.dataproc.v1.SubmitJobRequest;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public JobState getJobState(String jobId) {
		try (JobControllerClient jobControllerClient =
				_buildJobControllerClient()) {

			Job job = jobControllerClient.getJob(_projectId, _region, jobId);

			JobStatus jobStatus = job.getStatus();

			JobStatus.State state = jobStatus.getState();

			if (JobStatus.State.DONE.equals(state)) {
				return JobState.COMPLETE;
			}
			else if (JobStatus.State.RUNNING.equals(state)) {
				return JobState.RUNNING;
			}
			else if (JobStatus.State.ERROR.equals(state)) {
				return JobState.ERROR;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return JobState.UNKNOWN;
	}

	@Override
	public String submitJob(
		List<String> arguments, String configuration, List<String> jars,
		String name, Map<String, String> properties) {

		try (JobControllerClient jobControllerClient =
				_buildJobControllerClient()) {

			Job job = jobControllerClient.submitJob(
				_buildSubmitJobRequest(
					arguments, configuration, jars, name, properties));

			return job.getJobUuid();
		}
		catch (IOException ioe) {
			_log.error(ioe.getMessage(), ioe);
		}

		return null;
	}

	private Job _buildJob(
		List<String> arguments, String configuration, List<String> jars,
		String name, Map<String, String> properties) {

		Job.Builder builder = Job.newBuilder();

		builder.setPlacement(_buildJobPlacement());
		builder.setPysparkJob(
			_buildPySparkJob(arguments, configuration, jars, name, properties));

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
		List<String> arguments, String configuration, List<String> jars,
		String name, Map<String, String> properties) {

		PySparkJob.Builder builder = PySparkJob.newBuilder();

		builder.addArgs(name);

		builder.addArgs("--configuration");

		builder.addArgs(configuration);

		builder.addAllArgs(arguments);

		builder.addFileUris(
			String.format("gs://%s/resources/%s", _bucket, configuration));

		for (String jar : jars) {
			builder.addJarFileUris(
				String.format("gs://%s/libs/%s", _bucket, jar));
		}

		builder.setMainPythonFileUri(
			String.format("gs://%s/osb-asah-spark.py", _bucket));

		builder.putAllProperties(properties);

		_putPySparkJobBuilderEnvironmentVariables(builder, properties);

		return builder.build();
	}

	private SubmitJobRequest _buildSubmitJobRequest(
		List<String> arguments, String configuration, List<String> jars,
		String name, Map<String, String> properties) {

		SubmitJobRequest.Builder builder = SubmitJobRequest.newBuilder();

		builder.setJob(
			_buildJob(arguments, configuration, jars, name, properties));
		builder.setProjectId(_projectId);
		builder.setRegion(_region);

		return builder.build();
	}

	private PySparkJob.Builder _putPySparkJobBuilderEnvironmentVariables(
		PySparkJob.Builder pySparkJobBuilder, Map<String, String> properties) {

		for (Map.Entry<String, String> entry : properties.entrySet()) {
			Matcher matcher = _environmentVariablePattern.matcher(
				entry.getKey());

			if (matcher.matches()) {
				if (_log.isDebugEnabled()) {
					_log.debug("Adding variable: " + entry.getKey());
				}

				pySparkJobBuilder.putProperties(
					"spark.executorEnv." + entry.getKey(), entry.getValue());

				pySparkJobBuilder.putProperties(
					"spark.yarn.appMasterEnv." + entry.getKey(),
					entry.getValue());
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug("Skipping variable: " + entry.getKey());
				}
			}
		}

		return pySparkJobBuilder;
	}

	private static final Log _log = LogFactory.getLog(
		DataprocSparkManager.class);

	private static final Pattern _environmentVariablePattern = Pattern.compile(
		"^[A-Z_]{1}[A-Z0-9_]+$");

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