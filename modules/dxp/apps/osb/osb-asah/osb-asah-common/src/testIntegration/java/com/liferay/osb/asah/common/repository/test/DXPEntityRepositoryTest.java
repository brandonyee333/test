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
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPOrganization;
import com.liferay.osb.asah.common.model.DXPUser;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@Import(JDBCTestConfiguration.class)
public class DXPEntityRepositoryTest
	extends BaseRepositoryTestCase<DXPEntity, Long> {

	@BeforeEach
	public void setUp() throws Exception {
		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		Channel channel = new Channel("channel1");

		channel.setId(11L);
		channel.setIsNew(true);

		_channelRepository.save(channel);

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(123L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");
		dataSource.setState("READY");
		dataSource.setStatus("STARTED");
		dataSource.setURL("");

		_dataSourceRepository.save(dataSource);

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

	@AfterEach
	@Override
	public void tearDown() {
		_dxpEntityRepository.deleteAll(entityModels);
	}

	@Override
	@Test
	public void testCount() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testCount);
	}

	@Override
	@Test
	public void testDelete() {
		DXPEntity dxpEntity = entityModels.get(0);

		_dxpEntityRepository.delete(dxpEntity);

		DXPEntity.Type type = dxpEntity.getType();

		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.findByAfterAndFieldsAndType(
				null,
				new HashMap<String, Object>() {
					{
						put("dataSourceId", dxpEntity.getDataSourceId());
						put(
							"fields." + type.getIdFieldName(),
							dxpEntity.getIdFieldValue());
					}
				},
				1, type);

		Assertions.assertTrue(dxpEntities.isEmpty());
	}

	@Override
	@Test
	public void testDeleteAll1() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testDeleteAll1);
	}

	@Override
	@Test
	public void testDeleteAll2() {
		_dxpEntityRepository.deleteAll(entityModels);

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			new HashMap<String, Object>() {
				{
					put("dataSourceId", 123L);
					put(
						"fields." + DXPEntity.Type.USER.getIdFieldName(),
						ListUtil.map(entityModels, DXPEntity::getIdFieldValue));
				}
			},
			DXPEntity.Type.USER);

		Assertions.assertTrue(dxpEntities.isEmpty());
	}

	@Test
	public void testDeleteByFieldValue() {
		DXPEntity dxpEntity = entityModels.get(0);

		_dxpEntityRepository.deleteByFieldNameAndFieldValueAndType(
			"fields.emailAddress", "john.doe@liferay.com", DXPEntity.Type.USER);

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			new HashMap<String, Object>() {
				{
					put("dataSourceId", dxpEntity.getDataSourceId());
					put("fields.emailAddress", "john.doe@liferay.com");
				}
			},
			dxpEntity.getType());

		Assertions.assertTrue(dxpEntities.isEmpty());
	}

	@Override
	@Test
	public void testDeleteById() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testDeleteById);
	}

	@Override
	@Test
	public void testFindAll1() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testFindAll1);
	}

	@Override
	@Test
	public void testFindAll2() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testFindAll2);
	}

	@Override
	@Test
	public void testFindAll3() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testFindAll3);
	}

	@Override
	@Test
	public void testFindAllById() {
		Assertions.assertThrows(
			UnsupportedOperationException.class, super::testFindAllById);
	}

	@Test
	public void testFindByFieldsAndType() {
		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			new HashMap<String, Object>() {
				{
					put("dataSourceId", 123);
					put("fields.contact.jobTitle", "electrician");
					put("fields.lastName", "Doe");
					put("fields.memberships." + _CLASS_NAME_GROUP, "20122");
				}
			},
			DXPEntity.Type.USER);

		Assertions.assertEquals(1, dxpEntities.size(), dxpEntities.toString());
	}

	@Test
	public void testFindByFieldsAndTypePaginated() {
		DXPEntity dxpEntity = entityModels.get(0);

		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.findByAfterAndFieldsAndType(
				dxpEntity.getId(),
				Collections.singletonMap("fields.jobTitle", "electrician"), 2,
				DXPEntity.Type.USER);

		DXPEntity expectedDXPEntity = entityModels.get(1);
		DXPEntity actualDXPEntity = dxpEntities.get(0);

		Assertions.assertEquals(
			expectedDXPEntity.getId(), actualDXPEntity.getId(),
			dxpEntities.toString());
	}

	@Test
	public void testFindByMembershipIdAndType() {
		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.findByMembershipClassNameAndMembershipId(
				DXPEntity.Type.GROUP.getClassName(), 20121L);

		Assertions.assertEquals(1, dxpEntities.size(), dxpEntities.toString());
	}

	@Test
	public void testSave() {
		super.testSave();

		DXPEntity dxpEntity = new DXPEntity();

		dxpEntity.setDataSourceId(123L);
		dxpEntity.setFieldsJSONObject(JSONUtil.put("name", "Test Group"));
		dxpEntity.setId(1L);
		dxpEntity.setIsNew(true);
		dxpEntity.setType(DXPEntity.Type.GROUP);

		Assertions.assertEquals(
			dxpEntity, _dxpEntityRepository.save(dxpEntity));

		DXPOrganization dxpOrganization = new DXPOrganization();

		dxpOrganization.setDataSourceId(123L);
		dxpOrganization.setFieldsJSONObject(
			JSONUtil.put("name", "Test Organization"));
		dxpOrganization.setId(2L);
		dxpOrganization.setIsNew(true);

		Assertions.assertEquals(
			dxpOrganization, _dxpEntityRepository.save(dxpOrganization));

		DXPUser dxpUser = new DXPUser();

		dxpUser.setDataSourceId(123L);
		dxpUser.setFieldsJSONObject(
			JSONUtil.put(
				"firstName", "Test"
			).put(
				"lastName", "Test"
			));
		dxpUser.setId(3L);
		dxpUser.setIsNew(true);

		Assertions.assertEquals(dxpUser, _dxpEntityRepository.save(dxpUser));
	}

	@Test
	public void testSearchByDataSourceIdsAndKeywordsAndTypeSortedByName() {
		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
				Arrays.asList(123L), null, DXPEntity.Type.USER,
				PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "name")));

		Assertions.assertEquals(2, dxpEntities.size(), dxpEntities.toString());

		DXPEntity dxpEntity = dxpEntities.get(0);

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		Assertions.assertEquals(
			"Jane", fieldsJSONObject.getString("firstName"));

		dxpEntities =
			_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
				Arrays.asList(123L), null, DXPEntity.Type.USER,
				PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "name")));

		dxpEntity = dxpEntities.get(0);

		fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		Assertions.assertEquals(
			"John", fieldsJSONObject.getString("firstName"));

		Assertions.assertEquals(2, dxpEntities.size(), dxpEntities.toString());
	}

	@Override
	protected PagingAndSortingRepository<DXPEntity, Long>
		getPagingAndSortingRepository() {

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