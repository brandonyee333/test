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

package com.liferay.osb.asah.upgrade.v4_0_2;

import com.liferay.osb.asah.common.dog.BQAssetDog;
import com.liferay.osb.asah.common.dog.BQGroupDog;
import com.liferay.osb.asah.common.dog.BQOrganizationDog;
import com.liferay.osb.asah.common.dog.BQRoleDog;
import com.liferay.osb.asah.common.dog.BQTeamDog;
import com.liferay.osb.asah.common.dog.BQUserDog;
import com.liferay.osb.asah.common.dog.BQUserGroupDog;
import com.liferay.osb.asah.common.entity.BQAsset;
import com.liferay.osb.asah.common.entity.BQGroup;
import com.liferay.osb.asah.common.entity.BQOrganization;
import com.liferay.osb.asah.common.entity.BQRole;
import com.liferay.osb.asah.common.entity.BQTeam;
import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.entity.BQUserGroup;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.filter.expression.FilterExpression;
import com.liferay.osb.asah.common.filter.expression.FilterExpressionReferencedObjectsVisitor;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class SegmentReferencedObjectsUpgradeStep implements UpgradeStep {

	public SegmentReferencedObjectsUpgradeStep(
		SegmentRepository segmentRepository) {

		_segmentRepository = segmentRepository;
	}

	@Override
	public void upgrade(String version) {
		for (Segment segment : _segmentRepository.findAll()) {
			if (StringUtils.isBlank(segment.getFilter())) {
				continue;
			}

			FilterExpressionReferencedObjectsVisitor
				filterExpressionReferencedObjectsVisitor =
					new FilterExpressionReferencedObjectsVisitor();

			new FilterExpression(
				segment.getFilter(), filterExpressionReferencedObjectsVisitor);

			Map<String, Set<String>> referencedObjectIds =
				filterExpressionReferencedObjectsVisitor.
					getReferencedObjectIds();

			if (referencedObjectIds.isEmpty()) {
				continue;
			}

			segment.setReferencedAssetIds(
				SetUtil.map(
					referencedObjectIds.get("referencedAssetIds"),
					String::valueOf));
			segment.setReferencedFieldMappingFieldNames(
				SetUtil.map(
					referencedObjectIds.get("referencedFieldMappingFieldNames"),
					String::valueOf));
			segment.setReferencedGroupIds(
				SetUtil.map(
					referencedObjectIds.get("referencedGroupIds"),
					String::valueOf));
			segment.setReferencedOrganizationIds(
				SetUtil.map(
					referencedObjectIds.get("referencedOrganizationIds"),
					String::valueOf));
			segment.setReferencedRoleIds(
				SetUtil.map(
					referencedObjectIds.get("referencedRoleIds"),
					String::valueOf));
			segment.setReferencedTeamIds(
				SetUtil.map(
					referencedObjectIds.get("referencedTeamIds"),
					String::valueOf));
			segment.setReferencedUserGroupIds(
				SetUtil.map(
					referencedObjectIds.get("referencedUserGroupIds"),
					String::valueOf));
			segment.setReferencedUserIds(
				SetUtil.map(
					referencedObjectIds.get("referencedUserIds"),
					String::valueOf));

			_setReferencedDataSourceIds(segment);

			if (_log.isInfoEnabled()) {
				_log.info("Upgrading segment ID: " + segment.getId());
			}

			_segmentRepository.save(segment);
		}
	}

	private void _setReferencedDataSourceIds(Segment segment) {
		Set<Long> referencedDataSourceIds =
			segment.getReferencedDataSourceIds();

		List<BQAsset> bqAssets = _bqAssetDog.getBQAssets(
			segment.getReferencedAssetIds());

		if (!bqAssets.isEmpty()) {
			Stream<BQAsset> bqAssetsStream = bqAssets.stream();

			referencedDataSourceIds.addAll(
				bqAssetsStream.map(
					BQAsset::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		List<BQGroup> bqGroups = _bqGroupDog.getBQGroups(
			segment.getReferencedGroupIds());

		if (!bqGroups.isEmpty()) {
			Stream<BQGroup> bqGroupsStream = bqGroups.stream();

			referencedDataSourceIds.addAll(
				bqGroupsStream.map(
					BQGroup::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		List<BQOrganization> bqOrganizations =
			_bqOrganizationDog.getBQOrganizations(
				segment.getReferencedOrganizationIds());

		if (!bqOrganizations.isEmpty()) {
			Stream<BQOrganization> bqOrganizationsStream =
				bqOrganizations.stream();

			referencedDataSourceIds.addAll(
				bqOrganizationsStream.map(
					BQOrganization::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		List<BQRole> bqRoles = _bqRoleDog.getBQRoles(
			segment.getReferencedRoleIds());

		if (!bqRoles.isEmpty()) {
			Stream<BQRole> bqRolesStream = bqRoles.stream();

			referencedDataSourceIds.addAll(
				bqRolesStream.map(
					BQRole::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		List<BQTeam> bqTeams = _bqTeamDog.getBQTeams(
			segment.getReferencedTeamIds());

		if (!bqTeams.isEmpty()) {
			Stream<BQTeam> bqTeamsStream = bqTeams.stream();

			referencedDataSourceIds.addAll(
				bqTeamsStream.map(
					BQTeam::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		List<BQUserGroup> bqUserGroups = _bqUserGroupDog.getBQUserGroups(
			segment.getReferencedUserGroupIds());

		if (!bqUserGroups.isEmpty()) {
			Stream<BQUserGroup> bqUserGroupsStream = bqUserGroups.stream();

			referencedDataSourceIds.addAll(
				bqUserGroupsStream.map(
					BQUserGroup::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		List<BQUser> bqUsers = _bqUserDog.getBQUsers(
			segment.getReferencedUserIds());

		if (!bqUsers.isEmpty()) {
			Stream<BQUser> bqUsersStream = bqUsers.stream();

			referencedDataSourceIds.addAll(
				bqUsersStream.map(
					BQUser::getDataSourceId
				).collect(
					Collectors.toSet()
				));
		}

		segment.setReferencedDataSourceIds(referencedDataSourceIds);
	}

	private static final Log _log = LogFactory.getLog(
		SegmentReferencedObjectsUpgradeStep.class);

	@Autowired
	private BQAssetDog _bqAssetDog;

	@Autowired
	private BQGroupDog _bqGroupDog;

	@Autowired
	private BQOrganizationDog _bqOrganizationDog;

	@Autowired
	private BQRoleDog _bqRoleDog;

	@Autowired
	private BQTeamDog _bqTeamDog;

	@Autowired
	private BQUserDog _bqUserDog;

	@Autowired
	private BQUserGroupDog _bqUserGroupDog;

	private final SegmentRepository _segmentRepository;

}