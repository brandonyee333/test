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

package com.liferay.osb.asah.common.dog.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.DXPEntityDog;
import com.liferay.osb.asah.common.dog.OrganizationDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.test.util.faro.DXPRawTestUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Matthew Kong
 */
public class OrganizationDogTest
	extends BaseFaroInfoDogTestCase
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_liferayDataSource = FaroInfoTestUtil.buildLiferayDataSource();

		_liferayDataSource.setId(RandomTestUtil.randomNumber());

		_dataSourceRepository.save(_liferayDataSource);

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				null, "custom",
				Collections.singletonMap(
					String.valueOf(_liferayDataSource.getId()), "address"),
				"address", "Text", "organization"));
		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				null, "custom",
				Collections.singletonMap(
					String.valueOf(_liferayDataSource.getId()), "dateFounded"),
				"dateFounded", "Date", "organization"));
	}

	@Test
	public void testAddOrganization() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId())
			).put(
				"expando", JSONUtil.put("address", "1400 Montefino Ave")
			);

		Organization organization = _organizationDog.addOrganization(
			dxpRawOrganizationJSONObject, _liferayDataSource);

		Assertions.assertNotNull(
			_dxpEntityDog.fetchByFieldsAndType(
				Collections.singletonMap("fields.name", organization.getName()),
				DXPEntity.Type.ORGANIZATION));

		Set<Field> fields = organization.getCustomFields();

		Stream<Field> stream = fields.stream();

		Field addressField = stream.filter(
			field -> Objects.equals(field.getName(), "address")
		).findFirst(
		).orElse(
			null
		);

		Assertions.assertEquals("1400 Montefino Ave", addressField.getValue());

		Assertions.assertNotNull(organization.getCreateDate());
		Assertions.assertEquals(
			_liferayDataSource.getId(), organization.getDataSourceId());
		Assertions.assertEquals(
			dxpRawOrganizationJSONObject.getString("name"),
			organization.getName());
		Assertions.assertEquals(
			dxpRawOrganizationJSONObject.getString("nameTreePath"),
			organization.getNameTreePath());
		Assertions.assertEquals(
			dxpRawOrganizationJSONObject.getLong("organizationId"),
			organization.getOrganizationPK());
		Assertions.assertEquals(
			dxpRawOrganizationJSONObject.getLong("parentOrganizationId"),
			organization.getParentOrganizationPK());
		Assertions.assertEquals(
			dxpRawOrganizationJSONObject.getString("type"),
			organization.getType());
	}

	@Test
	public void testDeleteOrganization() throws Exception {
		Organization organization = _organizationDog.addOrganization(
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId())),
			_liferayDataSource);

		_organizationDog.deleteOrganization(organization);

		Assertions.assertEquals(0, _organizationRepository.count());

		Assertions.assertNull(
			_dxpEntityDog.fetchByFieldsAndType(
				Collections.singletonMap("id", organization.getId()),
				DXPEntity.Type.ORGANIZATION));

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());
	}

	@Test
	public void testFindByDataSourceIdAndOrganizationPKIn() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId()));

		Organization organization = _organizationDog.addOrganization(
			dxpRawOrganizationJSONObject, _liferayDataSource);

		List<Organization> organizations =
			_organizationDog.findByDataSourceIdAndOrganizationPKIn(
				organization.getDataSourceId(),
				Collections.singletonList(organization.getOrganizationPK()));

		Assertions.assertFalse(organizations.isEmpty());
	}

	@Test
	public void testGetOrganizationPage() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId()));

		Organization organization = _organizationDog.addOrganization(
			dxpRawOrganizationJSONObject, _liferayDataSource);

		String name = organization.getName();

		Page<Organization> organizationPage =
			_organizationDog.getOrganizationPage(
				name.substring(3), 10, new Sort("name", "asc"), 0);

		Assertions.assertEquals(organizationPage.getTotalElements(), 1);
	}

	@Test
	public void testUpdateOrganization() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				String.valueOf(_liferayDataSource.getId()));

		Organization organization = _organizationDog.addOrganization(
			dxpRawOrganizationJSONObject, _liferayDataSource);

		organization = _organizationDog.updateOrganization(
			dxpRawOrganizationJSONObject.put(
				"expando",
				JSONUtil.put(
					"dateFounded", String.valueOf(System.currentTimeMillis()))
			).put(
				"name", "marketing"
			),
			_liferayDataSource, organization);

		Assertions.assertEquals("marketing", organization.getName());

		Set<Field> customFields = organization.getCustomFields();

		Stream<Field> stream = customFields.stream();

		Field dateFoundedField = stream.filter(
			field -> Objects.equals(field.getName(), "dateFounded")
		).findFirst(
		).orElse(
			null
		);

		Assertions.assertNotNull(dateFoundedField.getValue());

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			Collections.singletonMap(
				"fields.organizationPK", organization.getOrganizationPK()),
			DXPEntity.Type.ORGANIZATION);

		Assertions.assertEquals(
			organization,
			_objectMapper.convertValue(
				dxpEntity.getFieldsJSONObject(), Organization.class));
		Assertions.assertEquals("marketing", dxpEntity.getName());

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assertions.assertEquals(1, asahTasks.size(), asahTasks.toString());
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	private DataSource _liferayDataSource;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private OrganizationRepository _organizationRepository;

}