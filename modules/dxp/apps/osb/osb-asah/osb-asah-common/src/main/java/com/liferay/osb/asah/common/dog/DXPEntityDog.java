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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.Organization;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.User;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class DXPEntityDog {

	public DXPEntity addDXPEntity(DXPEntity dxpEntity, DXPEntity.Type type) {
		dxpEntity.setId(null);

		dxpEntity.setType(type);

		return _mapDXPEntity(_dxpEntityRepository.save(dxpEntity));
	}

	public void delete(DXPEntity dxpEntity) {
		_dxpEntityRepository.delete(dxpEntity);
	}

	public void deleteByFieldNameEqualsAndType(
		String fieldName, String fieldValue, DXPEntity.Type type) {

		_dxpEntityRepository.deleteByFieldNameAndFieldValueAndType(
			fieldName, fieldValue, type);
	}

	public void deleteByType(DXPEntity.Type type) {
		_dxpEntityRepository.deleteByType(type);
	}

	public User fetchUserByFields(Map<String, Object> fields) {
		List<DXPEntity> dxpEntities =
			_dxpEntityRepository.findByAfterAndFieldsAndType(
				null, fields, 0, DXPEntity.Type.USER);

		if ((dxpEntities == null) || dxpEntities.isEmpty()) {
			return null;
		}

		return _mapDXPUser(new HashMap(), dxpEntities.get(0));
	}

	public List<? extends DXPEntity> findByAfterAndFieldsAndType(
		Long after, Map<String, Object> fields, int size, DXPEntity.Type type) {

		return _dxpEntityRepository.findByAfterAndFieldsAndType(
			after, fields, size, type);
	}

	public List<User> findUsersByMembershipClassNameAndMembershipId(
		String membershipClassName, String membershipId) {

		return ListUtil.map(
			_processDXPEntities(
				this::_mapDXPUser,
				_dxpEntityRepository.findByMembershipClassNameAndMembershipId(
					membershipClassName, Long.valueOf(membershipId))),
			dxpEntity -> (User)dxpEntity);
	}

	public Page<? extends DXPEntity> getDXPEntitiesPage(
		@Nullable Long channelId, @Nullable String keywords, int size,
		Sort sort, int start, DXPEntity.Type type) {

		List<Long> dataSourceIds = _getDataSourceIds(channelId);

		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		return PageableExecutionUtils.getPage(
			_mapDXPEntity(
				_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
					dataSourceIds, keywords, type, pageRequest),
				type),
			pageRequest,
			() -> _dxpEntityRepository.countByDataSourceIdsAndKeywordsAndType(
				dataSourceIds, keywords, type));
	}

	public DXPEntity updateDXPEntity(DXPEntity dxpEntity) {
		return _mapDXPEntity(_dxpEntityRepository.save(dxpEntity));
	}

	private List<Long> _getDataSourceIds(Long channelId) {
		List<Long> dataSourceIds = new ArrayList<>();

		if (channelId != null) {
			Channel channel = _channelDog.fetchChannel(channelId);

			if (channel != null) {
				dataSourceIds = ListUtil.map(
					channel.getChannelDataSources(),
					ChannelDataSource::getDataSourceId);
			}
		}

		return dataSourceIds;
	}

	private DXPEntity _mapDXPEntity(DXPEntity dxpEntity) {
		return _mapDXPEntity(new HashMap<>(), dxpEntity);
	}

	private List<? extends DXPEntity> _mapDXPEntity(
		List<DXPEntity> dxpEntities, DXPEntity.Type type) {

		if (type.isOrganization()) {
			return _processDXPEntities(this::_mapOrganization, dxpEntities);
		}

		if (type.isUser()) {
			return _processDXPEntities(this::_mapDXPUser, dxpEntities);
		}

		return _processDXPEntities(this::_mapDXPEntity, dxpEntities);
	}

	private DXPEntity _mapDXPEntity(
		Map<Long, String> dataSourceNames, DXPEntity dxpEntity) {

		dxpEntity.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				dxpEntity.getDataSourceId(),
				dataSourceId -> {
					if (dataSourceId != null) {
						DataSource dataSource = _dataSourceDog.fetchDataSource(
							dataSourceId);

						if (dataSource != null) {
							return dataSource.getName();
						}
					}

					return null;
				}));

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		dxpEntity.setName(fieldsJSONObject.optString("name"));

		return dxpEntity;
	}

	private User _mapDXPUser(
		Map<Long, String> dataSourceNames, DXPEntity dxpEntity) {

		User user = new User();

		user.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				dxpEntity.getDataSourceId(),
				dataSourceId -> {
					DataSource dataSource = _dataSourceDog.fetchDataSource(
						dataSourceId);

					if (dataSource != null) {
						return dataSource.getName();
					}

					return null;
				}));

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		user.setFirstName(fieldsJSONObject.getString("firstName"));
		user.setLastName(fieldsJSONObject.getString("lastName"));
		user.setScreenName(fieldsJSONObject.getString("screenName"));

		user.setId(dxpEntity.getId());

		return user;
	}

	private Organization _mapOrganization(
		Map<Long, String> dataSourceNames, DXPEntity dxpEntity) {

		Organization organization = new Organization();

		organization.setDataSourceName(
			dataSourceNames.computeIfAbsent(
				dxpEntity.getDataSourceId(),
				dataSourceId -> {
					DataSource dataSource = _dataSourceDog.fetchDataSource(
						dataSourceId);

					if (dataSource != null) {
						return dataSource.getName();
					}

					return null;
				}));
		organization.setId(dxpEntity.getId());

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		organization.setName(fieldsJSONObject.getString("name"));
		organization.setParentName(fieldsJSONObject.getString("parentName"));

		return organization;
	}

	private List<? extends DXPEntity> _processDXPEntities(
		BiFunction<Map<Long, String>, DXPEntity, ? extends DXPEntity>
			dxpEntityModelMapperFunction,
		List<DXPEntity> dxpEntities) {

		Map<Long, String> dataSourceNames = new HashMap<>();

		Stream<DXPEntity> stream = dxpEntities.stream();

		return stream.map(
			dxpEntity -> dxpEntityModelMapperFunction.apply(
				dataSourceNames, dxpEntity)
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}