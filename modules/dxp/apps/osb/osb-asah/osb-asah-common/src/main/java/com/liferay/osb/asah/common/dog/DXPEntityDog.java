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

import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.DXPOrganization;
import com.liferay.osb.asah.common.model.DXPUser;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.data.domain.Pageable;
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
		dxpEntity.setModifiedDate(new Date());
		dxpEntity.setType(type);

		return _mapDXPEntity(_dxpEntityRepository.save(dxpEntity), type);
	}

	public void delete(DXPEntity dxpEntity) {
		_dxpEntityRepository.delete(dxpEntity);
	}

	public void deleteByFieldNameEqualsAndType(
		String fieldName, Object fieldValue, DXPEntity.Type type) {

		_dxpEntityRepository.deleteByFieldNameAndFieldValueAndType(
			fieldName, fieldValue, type);
	}

	public void deleteByType(DXPEntity.Type type) {
		_dxpEntityRepository.deleteByType(type);
	}

	public DXPEntity fetchByFieldsAndType(
		Map<String, Object> fields, DXPEntity.Type type) {

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			fields, type);

		if ((dxpEntities == null) || dxpEntities.isEmpty()) {
			return null;
		}

		return _mapDXPEntity(dxpEntities.get(0), type);
	}

	public List<? extends DXPEntity> findByAfterAndFieldsAndType(
		Long after, Map<String, Object> fields, int size, DXPEntity.Type type) {

		return _mapDXPEntities(
			_dxpEntityRepository.findByAfterAndFieldsAndType(
				after, fields, size, type),
			type);
	}

	public List<? extends DXPEntity> findByFieldsAndType(
		Map<String, Object> fields, DXPEntity.Type type) {

		return _mapDXPEntities(
			_dxpEntityRepository.findByFieldsAndType(fields, type), type);
	}

	public List<DXPUser> findDXPUsersByMembershipClassNameAndMembershipId(
		String membershipClassName, Long membershipId) {

		return ListUtil.map(
			_processDXPEntities(
				this::_mapDXPUser,
				_dxpEntityRepository.findByMembershipClassNameAndMembershipId(
					membershipClassName, membershipId)),
			dxpEntity -> (DXPUser)dxpEntity);
	}

	public Page<? extends DXPEntity> getDXPEntityPage(
		@Nullable Date fromModifiedDate, Date toModifiedDate,
		DXPEntity.Type type, Pageable pageable) {

		return PageableExecutionUtils.getPage(
			_mapDXPEntities(
				_dxpEntityRepository.findByTypeAndModifiedDateBetween(
					fromModifiedDate, toModifiedDate, type, pageable),
				type),
			pageable,
			() -> _dxpEntityRepository.countByTypeAndModifiedDateBetween(
				fromModifiedDate, toModifiedDate, type));
	}

	public Page<? extends DXPEntity> getDXPEntityPage(
		@Nullable Long channelId, @Nullable String keywords, int size,
		Sort sort, int start, DXPEntity.Type type) {

		List<Long> dataSourceIds = _getDataSourceIds(channelId);

		PageRequest pageRequest = PageRequest.of(start / size, size, sort);

		return PageableExecutionUtils.getPage(
			_mapDXPEntities(
				_dxpEntityRepository.searchByDataSourceIdsAndKeywordsAndType(
					dataSourceIds, keywords, type, pageRequest),
				type),
			pageRequest,
			() -> _dxpEntityRepository.countByDataSourceIdsAndKeywordsAndType(
				dataSourceIds, keywords, type));
	}

	public DXPEntity updateDXPEntity(DXPEntity dxpEntity) {
		dxpEntity.setModifiedDate(new Date());

		return _mapDXPEntity(
			_dxpEntityRepository.save(dxpEntity), dxpEntity.getType());
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

	private List<? extends DXPEntity> _mapDXPEntities(
		List<DXPEntity> dxpEntities, DXPEntity.Type type) {

		dxpEntities.forEach(dxpEntity -> dxpEntity.setType(type));

		if (type.isOrganization()) {
			return _processDXPEntities(this::_mapDXPOrganization, dxpEntities);
		}

		if (type.isUser()) {
			return _processDXPEntities(this::_mapDXPUser, dxpEntities);
		}

		return _processDXPEntities(this::_mapDXPEntity, dxpEntities);
	}

	private DXPEntity _mapDXPEntity(DXPEntity dxpEntity, DXPEntity.Type type) {
		dxpEntity.setType(type);

		if (type.isOrganization()) {
			return _mapDXPOrganization(new HashMap<>(), dxpEntity);
		}

		if (type.isUser()) {
			return _mapDXPUser(new HashMap<>(), dxpEntity);
		}

		return _mapDXPEntity(new HashMap<>(), dxpEntity);
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

	private DXPOrganization _mapDXPOrganization(
		Map<Long, String> dataSourceNames, DXPEntity dxpEntity) {

		DXPOrganization dxpOrganization = new DXPOrganization();

		dxpOrganization.setDataSourceName(
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
		dxpOrganization.setId(dxpEntity.getId());

		JSONObject fieldsJSONObject = dxpEntity.getFieldsJSONObject();

		dxpOrganization.setDataSourceId(dxpEntity.getDataSourceId());
		dxpOrganization.setFieldsJSONObject(fieldsJSONObject);
		dxpOrganization.setModifiedDate(dxpEntity.getModifiedDate());
		dxpOrganization.setName(fieldsJSONObject.optString("name"));
		dxpOrganization.setParentName(fieldsJSONObject.optString("parentName"));

		return dxpOrganization;
	}

	private DXPUser _mapDXPUser(
		Map<Long, String> dataSourceNames, DXPEntity dxpEntity) {

		DXPUser dxpUser = new DXPUser();

		dxpUser.setDataSourceName(
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

		dxpUser.setDataSourceId(dxpEntity.getDataSourceId());
		dxpUser.setFieldsJSONObject(fieldsJSONObject);
		dxpUser.setFirstName(fieldsJSONObject.optString("firstName"));
		dxpUser.setId(dxpEntity.getId());
		dxpUser.setLastName(fieldsJSONObject.optString("lastName"));
		dxpUser.setModifiedDate(dxpEntity.getModifiedDate());
		dxpUser.setScreenName(fieldsJSONObject.optString("screenName"));

		return dxpUser;
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

}