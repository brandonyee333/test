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
import com.liferay.osb.asah.common.dog.OrganizationDog;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.faro.info.dog.test.BaseFaroInfoDogTestCase;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.DXPRawTestUtil;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class OrganizationDogTest extends BaseFaroInfoDogTestCase {

	@Before
	public void setUp() {
		_liferayDataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject();

		_liferayDataSourceJSONObject.put("id", RandomTestUtil.randomId());

		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				null, "custom",
				Collections.singletonMap(
					_liferayDataSourceJSONObject.getString("id"), "address"),
				"address", "Text", "organization"));
		_fieldMappingRepository.save(
			FaroInfoTestUtil.buildFieldMapping(
				null, "custom",
				Collections.singletonMap(
					_liferayDataSourceJSONObject.getString("id"),
					"dateFounded"),
				"dateFounded", "Date", "organization"));
	}

	@Test
	public void testAddOrganization() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				_liferayDataSourceJSONObject.getString("id")
			).put(
				"expando", JSONUtil.put("address", "1400 Montefino Ave")
			);

		Organization organization = _organizationDog.addOrganization(
			dxpRawOrganizationJSONObject,
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class));

		Set<Field> fields = organization.getCustomFields();

		Stream<Field> stream = fields.stream();

		Field addressField = stream.filter(
			field -> Objects.equals(field.getName(), "address")
		).findFirst(
		).orElse(
			null
		);

		Assert.assertEquals("1400 Montefino Ave", addressField.getValue());

		Assert.assertNotNull(organization.getCreateDate());
		Assert.assertEquals(
			_liferayDataSourceJSONObject.getString("id"),
			String.valueOf(organization.getDataSourceId()));
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getString("name"),
			organization.getName());
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getString("nameTreePath"),
			organization.getNameTreePath());
		Assert.assertEquals(
			Long.valueOf(
				dxpRawOrganizationJSONObject.getLong("organizationId")),
			organization.getOrganizationPK());
		Assert.assertEquals(
			Long.valueOf(
				dxpRawOrganizationJSONObject.getLong("parentOrganizationId")),
			organization.getParentOrganizationPK());
		Assert.assertEquals(
			dxpRawOrganizationJSONObject.getString("type"),
			organization.getType());
	}

	@Test
	public void testDeleteOrganization() throws Exception {
		Organization organization = _organizationDog.addOrganization(
			DXPRawTestUtil.buildOrganizationJSONObject(
				_liferayDataSourceJSONObject.getString("id")),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class));

		_organizationDog.deleteOrganization(organization);

		Assert.assertEquals(0, _organizationRepository.count());

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assert.assertEquals(asahTasks.toString(), 1, asahTasks.size());
	}

	@Test
	public void testUpdateOrganization() throws Exception {
		JSONObject dxpRawOrganizationJSONObject =
			DXPRawTestUtil.buildOrganizationJSONObject(
				_liferayDataSourceJSONObject.getString("id"));

		Organization organization = _organizationDog.addOrganization(
			dxpRawOrganizationJSONObject,
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class));

		organization = _organizationDog.updateOrganization(
			dxpRawOrganizationJSONObject.put(
				"expando",
				JSONUtil.put(
					"dateFounded", String.valueOf(System.currentTimeMillis()))
			).put(
				"name", "marketing"
			),
			_objectMapper.convertValue(
				_liferayDataSourceJSONObject, DataSource.class),
			organization);

		Assert.assertEquals("marketing", organization.getName());

		Set<Field> customFields = organization.getCustomFields();

		Stream<Field> stream = customFields.stream();

		Field dateFoundedField = stream.filter(
			field -> Objects.equals(field.getName(), "dateFounded")
		).findFirst(
		).orElse(
			null
		);

		Assert.assertNotNull(dateFoundedField.getValue());

		List<AsahTask> asahTasks = _asahTaskDog.getAsahTasks(
			"UpdateDynamicMembershipsNanite");

		Assert.assertEquals(asahTasks.toString(), 1, asahTasks.size());
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private FieldMappingRepository _fieldMappingRepository;

	private JSONObject _liferayDataSourceJSONObject;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationDog _organizationDog;

	@Autowired
	private OrganizationRepository _organizationRepository;

}