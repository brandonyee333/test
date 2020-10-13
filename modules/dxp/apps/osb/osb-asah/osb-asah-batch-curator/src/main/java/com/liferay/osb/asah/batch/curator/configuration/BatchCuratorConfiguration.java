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

package com.liferay.osb.asah.batch.curator.configuration;

import com.liferay.osb.asah.batch.curator.bot.scheduling.OSBAsahTaskManager;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
@Configuration
public class BatchCuratorConfiguration {

	@Bean(name = "contentRecommendationDataSolutionNaniteRunnable")
	@ConditionalOnGoogleApplicationCredentials
	public Runnable contentRecommendationDataSolutionNaniteRunnable() {
		return () -> _osbAsahTaskManager.runNanites(
			"ContentRecommendationDataSolutionNanite");
	}

	@Bean(name = "dataprocSparkManagerMonitoringNaniteRunnable")
	@ConditionalOnGoogleApplicationCredentials
	public Runnable dataprocSparkManagerMonitoringNaniteRunnable() {
		return () -> _osbAsahTaskManager.runNanites(
			"DataprocSparkManagerMonitoringNanite");
	}

	@Bean(name = "deleteDXPBatchEntitiesNaniteRunnable")
	@ConditionalOnGoogleApplicationCredentials
	public Runnable deleteDXPBatchEntitiesNaniteRunnable() {
		return () -> _osbAsahTaskManager.runNanites(
			"DeleteDXPBatchEntitiesNanite");
	}

	@Autowired
	private OSBAsahTaskManager _osbAsahTaskManager;

}