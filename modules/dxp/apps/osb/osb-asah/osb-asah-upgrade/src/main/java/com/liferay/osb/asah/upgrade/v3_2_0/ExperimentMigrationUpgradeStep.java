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

import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.repository.ExperimentRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Robson Pastor
 */
@Component
public class ExperimentMigrationUpgradeStep extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<Object> getConsumer() {
		return object -> {
			Experiment experiment = _objectMapper.convertValue(
				object, Experiment.class);

			experiment.setIsNew(Boolean.TRUE);

			_experimentRepository.save(experiment);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "experiments";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM experiment ORDER BY id DESC LIMIT 1";
	}

	@Override
	protected String getSequenceName() {
		return "experiment_id_seq";
	}

	@Autowired
	private ExperimentRepository _experimentRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}