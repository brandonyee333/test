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

import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.repository.CSVIndividualRepository;
import com.liferay.osb.asah.upgrade.BaseMigrationUpgradeStep;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leilany Ulisses
 */
@Component
public class CSVIndividualMigrationUpgradeStep
	extends BaseMigrationUpgradeStep {

	@Override
	protected Consumer<Object> getConsumer() {
		return object -> {
			CSVIndividual csvIndividual = _objectMapper.convertValue(
				object, CSVIndividual.class);

			csvIndividual.setIsNew(Boolean.TRUE);

			_csvIndividualRepository.save(csvIndividual);
		};
	}

	@Override
	protected String getElasticsearchCollectionName() {
		return "csv-individuals";
	}

	@Override
	protected String getSelectLatestIdSQL() {
		return "SELECT id FROM csvindividual ORDER BY id DESC LIMIT 1";
	}

	@Autowired
	private CSVIndividualRepository _csvIndividualRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}