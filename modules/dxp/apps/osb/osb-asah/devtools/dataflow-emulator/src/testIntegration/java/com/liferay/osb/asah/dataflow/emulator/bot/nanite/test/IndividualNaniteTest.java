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
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.json.JSONUtil;
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
		BQUser bqUser1 = new BQUser();

		bqUser1.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser1.setEmailAddress("joe@liferay.com");
		bqUser1.setFieldsJSONArray(
			JSONUtil.putAll(
				JSONUtil.put(
					"name", "country"
				).put(
					"value", "Brazil"
				)));
		bqUser1.setId(RandomTestUtil.randomString());
		bqUser1.setIsNew(Boolean.TRUE);
		bqUser1.setModifiedDate(DateUtil.toUTCDate("2022-08-04T12:00:00.000Z"));

		_bqUserRepository.save(bqUser1);

		BQUser bqUser2 = new BQUser();

		bqUser2.setDataSourceId(RandomTestUtil.randomNumber());
		bqUser2.setEmailAddress("joe@liferay.com");
		bqUser2.setFieldsJSONArray(
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
		bqUser2.setId(RandomTestUtil.randomString());
		bqUser2.setIsNew(Boolean.TRUE);
		bqUser2.setModifiedDate(DateUtil.toUTCDate("2022-08-03T12:00:00.000Z"));

		_bqUserRepository.save(bqUser2);

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());
		Assertions.assertEquals(
			bqUser1.getModifiedDate(), bqIndividual.getModifiedDate());

		JSONAssert.assertEquals(
			JSONUtil.putAll(
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser1.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser1.getModifiedDate())
				).put(
					"name", "country"
				).put(
					"value", "Brazil"
				),
				JSONUtil.put(
					"dataSourceId", String.valueOf(bqUser2.getDataSourceId())
				).put(
					"modifiedDate",
					DateUtil.toUTCString(bqUser2.getModifiedDate())
				).put(
					"name", "company"
				).put(
					"value", "Liferay"
				)),
			bqIndividual.getFieldsJSONArray(), false);
	}

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private IndividualNanite _individualNanite;

}