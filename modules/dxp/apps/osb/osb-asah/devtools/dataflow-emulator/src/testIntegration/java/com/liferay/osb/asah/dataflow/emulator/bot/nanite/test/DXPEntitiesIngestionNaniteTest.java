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

import com.liferay.osb.asah.common.entity.BQExpandoValue;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.entity.BQUserGroup;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.repository.BQAccountEntryRepository;
import com.liferay.osb.asah.common.repository.BQAccountGroupRepository;
import com.liferay.osb.asah.common.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.common.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.common.repository.BQGroupRepository;
import com.liferay.osb.asah.common.repository.BQOrganizationRepository;
import com.liferay.osb.asah.common.repository.BQRoleRepository;
import com.liferay.osb.asah.common.repository.BQTeamRepository;
import com.liferay.osb.asah.common.repository.BQUserGroupRepository;
import com.liferay.osb.asah.common.repository.BQUserRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.DXPEntitiesIngestionNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.OSBAsahDataflowEmulatorSpringTestContext;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.IterableUtils;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
public class DXPEntitiesIngestionNaniteTest
	implements OSBAsahDataflowEmulatorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testRun() throws Exception {
		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/dxp_entities1.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			_messageBus.sendMessage(
				Channel.DXP_ENTITIES_DEFAULT,
				String.valueOf(jsonArray.getJSONObject(i)),
				new HashMap<String, String>() {
					{
						put("dataSourceId", "1");
						put("projectId", "test");
					}
				});
		}

		_dxpEntitiesIngestionNanite.run();

		Assertions.assertEquals(1, _bqAccountEntryRepository.count());
		Assertions.assertEquals(1, _bqAccountGroupRepository.count());
		Assertions.assertEquals(1, _bqExpandoColumnRepository.count());
		Assertions.assertEquals(2, _bqExpandoValueRepository.count());
		Assertions.assertEquals(1, _bqGroupRepository.count());

		Assertions.assertEquals(1, _bqOrganizationRepository.count());

		List<BQOrganization> bqOrganizations = IterableUtils.toList(
			_bqOrganizationRepository.findAll());

		Assertions.assertEquals(
			1, bqOrganizations.size(), bqOrganizations.toString());

		BQOrganization bqOrganization = bqOrganizations.get(0);

		String[] expandoColumnIds1 = bqOrganization.getExpandoColumnIds();

		Assertions.assertEquals(1, expandoColumnIds1.length);

		String[] expandoValueIds1 = bqOrganization.getExpandoValueIds();

		Assertions.assertEquals(1, expandoValueIds1.length);

		Assertions.assertEquals(1, _bqRoleRepository.count());
		Assertions.assertEquals(1, _bqTeamRepository.count());
		Assertions.assertEquals(1, _bqUserGroupRepository.count());

		List<BQUser> bqUsers = IterableUtils.toList(
			_bqUserRepository.findAll());

		Assertions.assertEquals(1, bqUsers.size(), bqUsers.toString());

		BQUser bqUser = bqUsers.get(0);

		String[] expandoColumnIds2 = bqUser.getExpandoColumnIds();

		Assertions.assertEquals(1, expandoColumnIds2.length);

		String[] expandoValueIds2 = bqUser.getExpandoValueIds();

		Assertions.assertEquals(1, expandoValueIds2.length);
	}

	@Test
	public void testRunAnalyticsDeleteMessage() throws Exception {
		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/dxp_entities3.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			_messageBus.sendMessage(
				Channel.DXP_ENTITIES_DEFAULT,
				String.valueOf(jsonArray.getJSONObject(i)),
				new HashMap<String, String>() {
					{
						put("dataSourceId", "1");
						put("projectId", "test");
					}
				});
		}

		BQUserGroup bqUserGroup = new BQUserGroup();

		bqUserGroup.setId(
			DigestUtils.sha256Hex(String.join("#", "test", "1", "123")));
		bqUserGroup.setIsNew(true);
		bqUserGroup.setName("test");

		_bqUserGroupRepository.save(bqUserGroup);

		_dxpEntitiesIngestionNanite.run();

		Optional<BQUserGroup> bqUserGroupOptional =
			_bqUserGroupRepository.findById(
				DigestUtils.sha256Hex(String.join("#", "test", "1", "123")));

		Assertions.assertFalse(bqUserGroupOptional.isPresent());
	}

	@Test
	public void testRunWithExistingDXPEntity() throws Exception {
		JSONArray jsonArray = ResourceUtil.readResourceToJSONArray(
			"dependencies/dxp_entities2.json", this);

		for (int i = 0; i < jsonArray.length(); i++) {
			_messageBus.sendMessage(
				Channel.DXP_ENTITIES_DEFAULT,
				String.valueOf(jsonArray.getJSONObject(i)),
				new HashMap<String, String>() {
					{
						put("dataSourceId", "1");
						put("projectId", "test");
					}
				});
		}

		BQOrganization bqOrganization = new BQOrganization();

		bqOrganization.setId(
			DigestUtils.sha256Hex(String.join("#", "test", "1", "123")));

		bqOrganization.setIsNew(true);
		bqOrganization.setName("Test");

		BQExpandoValue bqExpandoValue = new BQExpandoValue();

		bqExpandoValue.setClassPK(123L);
		bqExpandoValue.setColumnId("1");
		bqExpandoValue.setClassType(BQExpandoValue.ClassType.ORGANIZATION);

		bqExpandoValue.setId(
			DigestUtils.sha256Hex(String.join("#", "test", "1", "1", "123")));

		bqExpandoValue.setIsNew(true);
		bqExpandoValue.setValue("1234");

		bqExpandoValue = _bqExpandoValueRepository.save(bqExpandoValue);

		bqOrganization.setExpandoValueIds(
			new String[] {bqExpandoValue.getId()});

		_bqOrganizationRepository.save(bqOrganization);

		_dxpEntitiesIngestionNanite.run();

		Assertions.assertEquals(1, _bqOrganizationRepository.count());

		List<BQOrganization> bqOrganizations = IterableUtils.toList(
			_bqOrganizationRepository.findAll());

		Assertions.assertEquals(
			1, bqOrganizations.size(), bqOrganizations.toString());

		bqOrganization = bqOrganizations.get(0);

		String[] expandoColumnIds1 = bqOrganization.getExpandoColumnIds();

		Assertions.assertEquals(1, expandoColumnIds1.length);

		String[] expandoValueIds1 = bqOrganization.getExpandoValueIds();

		Assertions.assertEquals(1, expandoValueIds1.length);

		Assertions.assertEquals(1, _bqExpandoValueRepository.count());
	}

	@Autowired
	private BQAccountEntryRepository _bqAccountEntryRepository;

	@Autowired
	private BQAccountGroupRepository _bqAccountGroupRepository;

	@Autowired
	private BQExpandoColumnRepository _bqExpandoColumnRepository;

	@Autowired
	private BQExpandoValueRepository _bqExpandoValueRepository;

	@Autowired
	private BQGroupRepository _bqGroupRepository;

	@Autowired
	private BQOrganizationRepository _bqOrganizationRepository;

	@Autowired
	private BQRoleRepository _bqRoleRepository;

	@Autowired
	private BQTeamRepository _bqTeamRepository;

	@Autowired
	private BQUserGroupRepository _bqUserGroupRepository;

	@Autowired
	private BQUserRepository _bqUserRepository;

	@Autowired
	private DXPEntitiesIngestionNanite _dxpEntitiesIngestionNanite;

	@Autowired
	private MessageBus _messageBus;

}