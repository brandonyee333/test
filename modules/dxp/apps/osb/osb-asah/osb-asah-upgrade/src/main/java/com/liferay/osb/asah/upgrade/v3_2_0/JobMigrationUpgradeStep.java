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

package com.liferay.osb.asah.upgrade.v3_2_0;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class JobMigrationUpgradeStep extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<JSONObject> getConsumer() {
		return jsonObject -> {
			Job job = _objectMapper.convertValue(jsonObject, Job.class);

			job.setIsNew(Boolean.TRUE);

			_jobRepository.save(job);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "jobs";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM job ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "job_id_seq";
	}

	@Autowired
	private JobRepository _jobRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}