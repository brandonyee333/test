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

import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.DXPEntitiesIngestionNanite;
import com.liferay.osb.asah.dataflow.emulator.bot.nanite.OSBAsahDataflowEmulatorSpringTestContext;
import com.liferay.osb.asah.dataflow.emulator.entity.BQExpandoValue;
import com.liferay.osb.asah.dataflow.emulator.entity.BQOrganization;
import com.liferay.osb.asah.dataflow.emulator.entity.BQUser;
import com.liferay.osb.asah.dataflow.emulator.repository.BQExpandoColumnRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQExpandoValueRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQGroupRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQOrganizationRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQRoleRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQTeamRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQUserGroupRepository;
import com.liferay.osb.asah.dataflow.emulator.repository.BQUserRepository;
import com.liferay.osb.asah.dataflow.emulator.util.DatabaseUtil;
import com.liferay.osb.asah.test.util.annotation.MessageBusChannel;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.IterableUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Marcos Martins
 */
public class DXPEntitiesIngestionNaniteTest
	implements OSBAsahDataflowEmulatorSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@MessageBusChannel(
		channel = Channel.DXP_ENTITIES_MESSAGE,
		resourcePath = "dxp_entities1.json"
	)
	@Test
	public void testRun() throws Exception {
		_dxpEntitiesIngestionNanite.run();

		Assertions.assertEquals(1, _bqExpandoColumnRepository.count());
		Assertions.assertEquals(2, _bqExpandoValueRepository.count());
		Assertions.assertEquals(1, _bqGroupRepository.count());

		Assertions.assertEquals(1, _bqOrganizationRepository.count());

		List<BQOrganization> bqOrganizations = IterableUtils.toList(
			_bqOrganizationRepository.findAll());

		Assertions.assertEquals(
			1, bqOrganizations.size(), bqOrganizations.toString());

		BQOrganization bqOrganization = bqOrganizations.get(0);

		Long[] expandoColumnIds1 = bqOrganization.getExpandoColumnIds();

		Assertions.assertEquals(1, expandoColumnIds1.length);

		Long[] expandoValueIds1 = bqOrganization.getExpandoValueIds();

		Assertions.assertEquals(1, expandoValueIds1.length);

		Assertions.assertEquals(1, _bqRoleRepository.count());
		Assertions.assertEquals(1, _bqTeamRepository.count());
		Assertions.assertEquals(1, _bqUserGroupRepository.count());

		List<BQUser> bqUsers = IterableUtils.toList(
			_bqUserRepository.findAll());

		Assertions.assertEquals(1, bqUsers.size(), bqUsers.toString());

		BQUser bqUser = bqUsers.get(0);

		Long[] expandoColumnIds2 = bqUser.getExpandoColumnIds();

		Assertions.assertEquals(1, expandoColumnIds2.length);

		Long[] expandoValueIds2 = bqUser.getExpandoValueIds();

		Assertions.assertEquals(1, expandoValueIds2.length);
	}

	@MessageBusChannel(
		channel = Channel.DXP_ENTITIES_MESSAGE,
		resourcePath = "dxp_entities2.json"
	)
	@Test
	public void testRunWithExistingDXPEntity() throws Exception {
		DatabaseUtil.createTables(_dataSource, "test");

		BQOrganization bqOrganization = new BQOrganization();

		bqOrganization.setId(
			DigestUtils.sha256Hex(String.join("#", "test", "1", "123")));

		bqOrganization.setIsNew(true);
		bqOrganization.setName("Test");

		BQExpandoValue bqExpandoValue = new BQExpandoValue();

		bqExpandoValue.setClassPK(123L);
		bqExpandoValue.setColumnId(1L);
		bqExpandoValue.setClassType(BQExpandoValue.ClassType.ORGANIZATION);
		bqExpandoValue.setValue("1234");

		bqExpandoValue = _bqExpandoValueRepository.save(bqExpandoValue);

		bqOrganization.setExpandoValueIds(new Long[] {bqExpandoValue.getId()});

		_bqOrganizationRepository.save(bqOrganization);

		_dxpEntitiesIngestionNanite.run();

		Assertions.assertEquals(1, _bqOrganizationRepository.count());

		List<BQOrganization> bqOrganizations = IterableUtils.toList(
			_bqOrganizationRepository.findAll());

		Assertions.assertEquals(
			1, bqOrganizations.size(), bqOrganizations.toString());

		bqOrganization = bqOrganizations.get(0);

		Long[] expandoColumnIds1 = bqOrganization.getExpandoColumnIds();

		Assertions.assertEquals(1, expandoColumnIds1.length);

		Long[] expandoValueIds1 = bqOrganization.getExpandoValueIds();

		Assertions.assertEquals(1, expandoValueIds1.length);

		Assertions.assertEquals(1, _bqExpandoValueRepository.count());
	}

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
	@Qualifier("postgreSQLDataSource")
	private DataSource _dataSource;

	@Autowired
	private DXPEntitiesIngestionNanite _dxpEntitiesIngestionNanite;

}