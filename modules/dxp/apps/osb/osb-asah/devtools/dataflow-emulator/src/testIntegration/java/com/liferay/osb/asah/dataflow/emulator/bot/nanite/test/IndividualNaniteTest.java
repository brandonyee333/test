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

package com.liferay.osb.asah.dataflow.emulator.bot.nanite.test;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.IndividualNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.OSBAsahDataflowEmulatorSpringTestContext;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public class IndividualNaniteTest
	implements OSBAsahDataflowEmulatorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testRun() throws Exception {
		BQCSVUser bqCSVUser = new BQCSVUser();

		bqCSVUser.setDataSourceId(RandomTestUtil.randomNumber());
		bqCSVUser.setEmailAddress("joe@liferay.com");
		bqCSVUser.setFieldsJSONArray(
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "country"
				).put(
					"value", "Brazil"
				)));
		bqCSVUser.setModifiedDate(
			DateUtil.toUTCDate("2022-08-04T12:00:00.000Z"));

		_bqCSVUserRepository.save(bqCSVUser);

		BQUser bqUser = new BQUser();

		bqUser.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser.setEmailAddress("joe@liferay.com");
		bqUser.setFieldsJSONArray(
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "country"
				).put(
					"value", "Argentina"
				),
				JSONUtil.put(
					"name", "company"
				).put(
					"value", "Liferay"
				)));
		bqUser.setId(RandomTestUtil.randomString());
		bqUser.setIsNew(Boolean.TRUE);
		bqUser.setModifiedDate(DateUtil.toUTCDate("2022-08-03T12:00:00.000Z"));

		_bqUserRepository.save(bqUser);

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());
		Assertions.assertEquals(
			bqCSVUser.getModifiedDate(), bqIndividual.getModifiedDate());

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqCSVUser.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqCSVUser.getModifiedDate())
				).put(
					"name", "country"
				).put(
					"value", "Brazil"
				),
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser.getModifiedDate())
				).put(
					"name", "company"
				).put(
					"value", "Liferay"
				)),
			bqIndividual.getFieldsJSONArray(), false);
	}

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private IndividualNanite _individualNanite;

}