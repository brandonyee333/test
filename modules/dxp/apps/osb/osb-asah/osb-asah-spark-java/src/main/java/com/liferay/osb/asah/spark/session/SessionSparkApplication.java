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

package com.liferay.osb.asah.spark.session;

import com.liferay.osb.asah.spark.common.BaseSparkApplication;
import com.liferay.osb.asah.spark.common.Configuration;
import com.liferay.osb.asah.spark.common.SparkJobPipeline;

import java.util.Arrays;

import org.apache.spark.SparkConf;

/**
 * @author Marcellus Tavares
 */
public class SessionSparkApplication extends BaseSparkApplication {

	public static void main(String[] args) {
		SessionSparkApplication sessionSparkApplication =
			new SessionSparkApplication(
				new Configuration(args, "session.properties"));

		sessionSparkApplication.start();
	}

	public SessionSparkApplication(Configuration configuration) {
		_configuration = configuration;
	}

	public Configuration getConfiguration() {
		return _configuration;
	}

	@Override
	public void start() {
		SparkJobPipeline.run(Arrays.asList(new SessionSparkJob(this)));
	}

	@Override
	protected SparkConf createSparkConf() {
		SparkConf sparkConf = new SparkConf();

		sparkConf.setAppName("Session Spark Application");

		return sparkConf;
	}

	private final Configuration _configuration;

}