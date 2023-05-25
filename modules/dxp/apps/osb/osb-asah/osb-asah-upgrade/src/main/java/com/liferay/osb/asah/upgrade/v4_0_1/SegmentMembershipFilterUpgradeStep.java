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

package com.liferay.osb.asah.upgrade.v4_0_1;

import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SegmentMembershipFilterUpgradeStep implements UpgradeStep {

	public SegmentMembershipFilterUpgradeStep(
		SegmentRepository segmentRepository) {

		_segmentRepository = segmentRepository;
	}

	@Override
	public void upgrade(String version) {
		_upgradeMembershipIds("groupId", "groups", _groupIdsPattern);
		_upgradeMembershipIds("roleId", "roles", _roleIdsPattern);
		_upgradeMembershipIds("teamId", "teams", _teamIdsPattern);
		_upgradeMembershipIds(
			"userGroupId", "user-groups", _userGroupIdsPattern);
		_upgradeOrganizationIds();

		_disableSegments();
	}

	private void _disableSegments() {
		for (Segment segment : _segmentRepository.findAll()) {
			String filterString = segment.getFilter();

			if (StringUtils.isBlank(filterString)) {
				continue;
			}

			if (StringUtils.equalsIgnoreCase("DISABLED", segment.getState())) {
				continue;
			}

			if (StringUtils.containsAnyIgnoreCase(
					filterString, "demographics/address", "demographics/city",
					"demographics/country", "demographics/department",
					"demographics/division", "demographics/fullName",
					"demographics/gender", "demographics/region",
					"demographics/role", "demographics/state")) {

				segment.setState("DISABLED");

				_segmentRepository.save(segment);
			}
		}
	}

	@PostConstruct
	private void _init() {
		_namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
			_dataSource);
	}

	private void _saveSegment(String filterString, Segment segment) {
		if (!Objects.equals(filterString, segment.getFilter())) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Upgrading segment filter from {%s} to {%s}",
						segment.getFilter(), filterString));
			}

			segment.setFilter(filterString);

			_segmentRepository.save(segment);
		}
	}

	private void _upgradeMembershipIds(
		String classPKName, String collectionName, Pattern pattern) {

		for (Segment segment : _segmentRepository.findAll()) {
			String filterString = segment.getFilter();

			if (StringUtils.isBlank(filterString)) {
				continue;
			}

			Matcher matcher = pattern.matcher(filterString);

			while (matcher.find()) {
				String id = matcher.group("id");

				JSONObject jsonObject = _dxpRawElasticsearchInvoker.fetch(
					collectionName, id);

				if (jsonObject != null) {
					continue;
				}

				Optional<DXPEntity> dxpEntityOptional =
					_dxpEntityRepository.findById(Long.valueOf(id));

				if (!dxpEntityOptional.isPresent()) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							String.format(
								"Unable to upgrade segment filter %s. " +
									"Segment ID: %s",
								filterString, segment.getId()));
					}
				}
				else {
					DXPEntity dxpEntity = dxpEntityOptional.get();

					String dataSourceId = String.valueOf(
						dxpEntity.getDataSourceId());

					JSONObject fieldsJSONObject =
						dxpEntity.getFieldsJSONObject();

					String classPK = String.valueOf(
						fieldsJSONObject.get(classPKName));

					filterString = filterString.replace(
						id,
						DigestUtils.sha256Hex(
							ProjectIdThreadLocal.getProjectId() + "#" +
								dataSourceId + "#" + classPK));
				}
			}

			_saveSegment(filterString, segment);
		}
	}

	private void _upgradeOrganizationIds() {
		for (Segment segment : _segmentRepository.findAll()) {
			String filterString = segment.getFilter();

			if (StringUtils.isBlank(filterString)) {
				continue;
			}

			Matcher matcher = _organizationIdsPattern.matcher(filterString);

			while (matcher.find()) {
				String id = matcher.group("id");

				JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
					"organizations", id);

				if (jsonObject != null) {
					continue;
				}

				Optional<DXPEntity> dxpEntityOptional =
					_dxpEntityRepository.findById(Long.valueOf(id));

				if (!dxpEntityOptional.isPresent()) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							String.format(
								"Unable to upgrade segment filter %s. " +
									"Segment ID: %s",
								filterString, segment.getId()));
					}
				}
				else {
					DXPEntity dxpEntity = dxpEntityOptional.get();

					String dataSourceId = String.valueOf(
						dxpEntity.getDataSourceId());

					JSONObject fieldsJSONObject =
						dxpEntity.getFieldsJSONObject();

					String classPK = String.valueOf(
						fieldsJSONObject.get("organizationPK"));

					filterString = filterString.replace(
						id,
						DigestUtils.sha256Hex(
							ProjectIdThreadLocal.getProjectId() + "#" +
								dataSourceId + "#" + classPK));
				}
			}

			_saveSegment(filterString, segment);
		}
	}

	private static final Log _log = LogFactory.getLog(
		SegmentMembershipFilterUpgradeStep.class);

	private static final Pattern _groupIdsPattern = Pattern.compile(
		"groupIds (eq|ne) '(?<id>[\\d]+)'");
	private static final Pattern _organizationIdsPattern = Pattern.compile(
		"organizations\\.filter\\(filter='(id|parentId) (eq|ne) " +
			"''(?<id>[\\d]+)'''");
	private static final Pattern _roleIdsPattern = Pattern.compile(
		"roleIds (eq|ne) '(?<id>[\\d]+)'");
	private static final Pattern _teamIdsPattern = Pattern.compile(
		"teamIds (eq|ne) '(?<id>[\\d]+)'");
	private static final Pattern _userGroupIdsPattern = Pattern.compile(
		"userGroupIds (eq|ne) '(?<id>[\\d]+)'");

	@Autowired
	private DataSource _dataSource;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private NamedParameterJdbcTemplate _namedParameterJdbcTemplate;
	private final SegmentRepository _segmentRepository;

}