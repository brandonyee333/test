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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.BQIdentity;
import com.liferay.osb.asah.common.entity.BQIndividual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.messaging.model.Message;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.BQIdentityRepository;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.DXPEntitiesIngestionNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.IndividualNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.OSBAsahDataflowEmulatorSpringTestContext;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcellus Tavares
 */
public class IndividualNaniteTest
	implements OSBAsahDataflowEmulatorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testMergeCustomFieldNameDifferentType() throws Exception {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("joe@liferay.com"));

		_bqIdentityRepository.insert(bqIdentity);

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/dxp_entities4.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			_dxpEntitiesIngestionNanite.processMessage(
				new Message<>(
					null,
					new HashMap<String, String>() {
						{
							put("dataSourceId", "1");
							put("projectId", "test");
						}
					},
					null, String.valueOf(jsonArray.getJSONObject(i))));
		}

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		Assertions.assertTrue(bqIndividualOptional.isPresent());

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());

		Map<String, JSONObject> jsonObjectMap = JSONUtil.toJSONObjectMap(
			_objectMapper.convertValue(
				bqIndividual.getFields(), JSONArray.class),
			"name");

		JSONObject jsonObject = jsonObjectMap.get("age");

		Assertions.assertEquals("[18]", jsonObject.getString("value"));

		jsonObject = jsonObjectMap.get("age_1");

		Assertions.assertEquals("20", jsonObject.getString("value"));
	}

	@Test
	public void testMergeCustomFieldNameSameType() throws Exception {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("joe@liferay.com"));

		_bqIdentityRepository.insert(bqIdentity);

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/dxp_entities5.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			_dxpEntitiesIngestionNanite.processMessage(
				new Message<>(
					null,
					new HashMap<String, String>() {
						{
							put("dataSourceId", "1");
							put("projectId", "test");
						}
					},
					null, String.valueOf(jsonArray.getJSONObject(i))));
		}

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());

		Map<String, JSONObject> jsonObjectMap = JSONUtil.toJSONObjectMap(
			_objectMapper.convertValue(
				bqIndividual.getFields(), JSONArray.class),
			"name");

		JSONObject jsonObject = jsonObjectMap.get("age");

		Assertions.assertEquals("20", jsonObject.getString("value"));
	}

	@Test
	public void testMergeDefaultField() throws Exception {
		BQIdentity bqIdentity = new BQIdentity();

		bqIdentity.setCreateDate(new Date());
		bqIdentity.setId(RandomTestUtil.randomUUID());
		bqIdentity.setIndividualId(DigestUtils.sha256Hex("joe@liferay.com"));

		_bqIdentityRepository.insert(bqIdentity);

		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/dxp_entities6.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			_dxpEntitiesIngestionNanite.processMessage(
				new Message<>(
					null,
					new HashMap<String, String>() {
						{
							put("dataSourceId", "1");
							put("projectId", "test");
						}
					},
					null, String.valueOf(jsonArray.getJSONObject(i))));
		}

		_individualNanite.run();

		Optional<BQIndividual> bqIndividualOptional =
			_bqIndividualRepository.findByEmailAddress("joe@liferay.com");

		BQIndividual bqIndividual = bqIndividualOptional.get();

		Assertions.assertEquals(
			"joe@liferay.com", bqIndividual.getEmailAddress());
		Assertions.assertEquals("Joseph", bqIndividual.getFirstName());

		Map<String, JSONObject> jsonObjectMap = JSONUtil.toJSONObjectMap(
			_objectMapper.convertValue(
				bqIndividual.getFields(), JSONArray.class),
			"name");

		JSONObject jsonObject = jsonObjectMap.get("country");

		Assertions.assertEquals("Brazil", jsonObject.getString("value"));

		jsonObject = jsonObjectMap.get("company");

		Assertions.assertEquals("Liferay", jsonObject.getString("value"));
	}

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private BQIdentityRepository _bqIdentityRepository;

	@Autowired
	private BQIndividualRepository _bqIndividualRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private DXPEntitiesIngestionNanite _dxpEntitiesIngestionNanite;

	@Autowired
	private IndividualNanite _individualNanite;

	@Autowired
	private ObjectMapper _objectMapper;

}