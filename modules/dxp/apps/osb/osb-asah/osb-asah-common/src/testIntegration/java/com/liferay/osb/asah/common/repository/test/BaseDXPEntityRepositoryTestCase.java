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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcos Martins
 */
public abstract class BaseDXPEntityRepositoryTestCase
	extends BaseRepositoryTestCase<DXPEntity, Long> {

	@Before
	public void setUp() throws Exception {
		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		Channel channel1 = new Channel("channel1");

		channel1.setId(11L);
		channel1.setIsNew(true);

		_channelRepository.save(channel1);

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource1.setId(123L);
		dataSource1.setIsNew(true);
		dataSource1.setProviderType("LIFERAY");
		dataSource1.setState("READY");
		dataSource1.setStatus("STARTED");
		dataSource1.setURL("");

		_dataSourceRepository.save(dataSource1);

		DXPEntity dxpEntity1 = new DXPEntity();

		dxpEntity1.setDataSourceId(123L);
		dxpEntity1.setFieldsJSONObject(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/user_fields1.json", this));
		dxpEntity1.setType(DXPEntity.Type.USER);

		DXPEntity dxpEntity2 = new DXPEntity();

		dxpEntity2.setDataSourceId(123L);
		dxpEntity2.setFieldsJSONObject(
			ResourceUtil.readResourceToJSONObject(
				"dependencies/user_fields2.json", this));
		dxpEntity2.setType(DXPEntity.Type.USER);

		setUpRepository(dxpEntity1, dxpEntity2);
	}

	@Override
	public void tearDown() {
		_dxpEntityRepository.deleteAll(entityModels);
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testCount() {
		super.testCount();
	}

	@Override
	@Test
	public void testDelete() {
		DXPEntity dxpEntity = entityModels.get(0);

		_dxpEntityRepository.delete(dxpEntity);

		DXPEntity.Type type = dxpEntity.getType();

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			null,
			new HashMap<String, Object>() {
				{
					put("dataSourceId", dxpEntity.getDataSourceId());
					put(
						"fields." + type.getIdFieldName(),
						dxpEntity.getIdFieldValue());
				}
			},
			type, 1);

		Assert.assertTrue(dxpEntities.isEmpty());
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testDeleteAll1() {
		super.testDeleteAll1();
	}

	@Override
	@Test
	public void testDeleteAll2() {
		_dxpEntityRepository.deleteAll(entityModels);

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			null,
			new HashMap<String, Object>() {
				{
					put("dataSourceId", 123L);
					put(
						"fields." + DXPEntity.Type.USER.getIdFieldName(),
						ListUtil.map(entityModels, DXPEntity::getIdFieldValue));
				}
			},
			DXPEntity.Type.USER, 1);

		Assert.assertTrue(dxpEntities.isEmpty());
	}

	@Test
	public void testDeleteByFieldValue() {
		DXPEntity dxpEntity = entityModels.get(0);

		_dxpEntityRepository.deleteByFieldNameEqualsAndType(
			"fields.emailAddress", "john.doe@liferay.com", DXPEntity.Type.USER);

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			null,
			new HashMap<String, Object>() {
				{
					put("dataSourceId", dxpEntity.getDataSourceId());
					put("fields.emailAddress", "john.doe@liferay.com");
				}
			},
			dxpEntity.getType(), 1);

		Assert.assertTrue(dxpEntities.isEmpty());
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testDeleteById() {
		super.testDeleteById();
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testExistsById() {
		super.testExistsById();
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testFindAll() {
		super.testFindAll();
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testFindAllById() {
		super.testFindAllById();
	}

	@Test
	public void testFindByFieldsAndType() {
		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			null,
			new HashMap<String, Object>() {
				{
					put("dataSourceId", 123);
					put("fields.contact.jobTitle", "electrician");
					put("fields.lastName", "Doe");
					put("fields.memberships." + _CLASS_NAME_GROUP, "20122");
				}
			},
			DXPEntity.Type.USER, 3);

		Assert.assertEquals(dxpEntities.toString(), 1, dxpEntities.size());
	}

	@Test
	public void testFindByFieldsAndTypePaginated() {
		DXPEntity dxpEntity = entityModels.get(0);

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			dxpEntity.getId(),
			Collections.singletonMap("fields.jobTitle", "electrician"),
			DXPEntity.Type.USER, 2);

		DXPEntity expectedDXPEntity = entityModels.get(1);
		DXPEntity actualDXPEntity = dxpEntities.get(0);

		Assert.assertEquals(
			dxpEntities.toString(), expectedDXPEntity.getId(),
			actualDXPEntity.getId());
	}

	@Override
	@Test(expected = UnsupportedOperationException.class)
	public void testFindById() {
		super.testFindById();
	}

	@Test
	public void testFindByMembershipIdAndType() {
		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.findByMembershipClassNameAndMembershipId(
				DXPEntity.Type.GROUP.getClassName(), 20121L);

		Assert.assertEquals(dxpEntities.toString(), 1, dxpEntities.size());
	}

	@Override
	protected CrudRepository<DXPEntity, Long> getCrudRepository() {
		return _dxpEntityRepository;
	}

	private static final String _CLASS_NAME_GROUP =
		"com.liferay.portal.kernel.model.Group";

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

}