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

package com.liferay.osb.asah.common.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoOrganizationsFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.Organization;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPOrganization;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.postgresql.converter.helper.OrganizationsFilterStringConverterHelper;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.OrganizationRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class OrganizationDog {

	public Organization addOrganization(
			JSONObject dataJSONObject, DataSource dataSource)
		throws Exception {

		Organization organization = new Organization();

		organization.setCreateDate(new Date());
		organization.setDataSourceId(dataSource.getId());
		organization.setModifiedDate(
			new Date(dataJSONObject.getLong("modifiedDate")));
		organization.setName(dataJSONObject.getString("name"));
		organization.setNameTreePath(dataJSONObject.optString("nameTreePath"));
		organization.setOrganizationPK(
			dataJSONObject.getLong("organizationId"));
		organization.setParentName(dataJSONObject.optString("parentName"));
		organization.setParentOrganizationPK(
			dataJSONObject.optLong("parentOrganizationId"));
		organization.setType(dataJSONObject.optString("type"));

		organization = _organizationRepository.save(organization);

		Long organizationId = organization.getId();

		List<Field> customFields = _fieldDog.addFields(
			"custom", dataJSONObject, dataSource, organizationId,
			"organization");

		if (CollectionUtils.isNotEmpty(customFields)) {
			organization.setCustomFields(new HashSet<>(customFields));

			organization = _organizationRepository.save(organization);
		}

		organization = populateOrganization(organization);

		_dxpEntityDog.addDXPEntity(
			_buildDXPOrganization(organization), DXPEntity.Type.ORGANIZATION);

		return organization;
	}

	public void deleteOrganization(Organization organization) {
		if (organization == null) {
			return;
		}

		_organizationRepository.delete(organization);

		_dxpEntityDog.deleteByFieldNameEqualsAndType(
			"fields.organizationPK", organization.getOrganizationPK(),
			DXPEntity.Type.ORGANIZATION);

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeFilter",
				"(referencedOrganizationIds eq " + organization.getId() + ")"
			));
	}

	public List<Organization> findByDataSourceIdAndOrganizationPKIn(
		Long dataSourceId, Collection<Long> organizationPKs) {

		return _organizationRepository.findByDataSourceIdAndOrganizationPKIn(
			dataSourceId, organizationPKs);
	}

	public Page<Organization> getOrganizationPage(
		@Nullable String name, int size,
		com.liferay.osb.asah.common.model.Sort sort, int start) {

		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		return PageableExecutionUtils.getPage(
			_organizationRepository.findByName(name, pageRequest), pageRequest,
			() -> _organizationRepository.countByName(name));
	}

	public Page<Transformation> getTransformationPage(
		String apply, @Nullable String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_organizationRepository.getOrganizationTransformations(
				apply,
				new FilterHelper(
					_faroInfoOrganizationsFilterStringConverterHelper,
					filterString, _organizationsFilterStringConverterHelper),
				pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public Organization populateOrganization(Organization organization) {
		if (organization == null) {
			return null;
		}

		List<Field> fields = _fieldDog.getOwnerIdFields(
			"custom", organization.getId());

		organization.setCustomFields(new HashSet<>(fields));

		return organization;
	}

	public List<Organization> searchOrganizations(
		String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(page, size);

		return _organizationRepository.searchOrganizations(
			new FilterHelper(
				_faroInfoOrganizationsFilterStringConverterHelper, filterString,
				_organizationsFilterStringConverterHelper),
			pageRequest);
	}

	public Organization updateOrganization(
			JSONObject dataJSONObject, DataSource dataSource,
			Organization organization)
		throws Exception {

		_fieldDog.updateFields(
			"custom", dataJSONObject, dataSource,
			Collections.singletonList(organization.getId()), "organization");

		List<Field> fields =
			_fieldRepository.findByContextAndOwnerIdAndOwnerType(
				"custom", organization.getId(), "organization");

		Stream<Field> stream = fields.stream();

		organization.setCustomFields(stream.collect(Collectors.toSet()));

		organization.setName(dataJSONObject.getString("name"));
		organization.setNameTreePath(
			dataJSONObject.optString("nameTreePath", null));
		organization.setParentName(
			dataJSONObject.optString("parentName", null));
		organization.setParentOrganizationPK(
			dataJSONObject.optLong("parentOrganizationId"));
		organization.setTreePath(dataJSONObject.optString("treePath"));
		organization.setType(dataJSONObject.optString("type", null));

		organization = _organizationRepository.save(organization);

		_dxpEntityDog.updateDXPEntity(_buildDXPOrganization(organization));

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"removeFilter",
				"(referencedOrganizationIds eq " + organization.getId() + ")"
			));

		return populateOrganization(organization);
	}

	private DXPOrganization _buildDXPOrganization(Organization organization) {
		DXPOrganization dxpOrganization = new DXPOrganization();

		dxpOrganization.setDataSourceId(organization.getDataSourceId());
		dxpOrganization.setFieldsJSONObject(
			_objectMapper.convertValue(organization, JSONObject.class));

		DXPEntity dxpEntity = _dxpEntityDog.fetchByFieldsAndType(
			Collections.singletonMap(
				"fields.organizationPK", organization.getOrganizationPK()),
			DXPEntity.Type.ORGANIZATION);

		if (dxpEntity != null) {
			dxpOrganization.setId(dxpEntity.getId());
		}

		return dxpOrganization;
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private DXPEntityDog _dxpEntityDog;

	@Autowired
	private FaroInfoOrganizationsFilterStringConverterHelper
		_faroInfoOrganizationsFilterStringConverterHelper;

	@Autowired
	private FieldDog _fieldDog;

	@Autowired
	private FieldRepository _fieldRepository;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private OrganizationRepository _organizationRepository;

	@Autowired
	private OrganizationsFilterStringConverterHelper
		_organizationsFilterStringConverterHelper;

}