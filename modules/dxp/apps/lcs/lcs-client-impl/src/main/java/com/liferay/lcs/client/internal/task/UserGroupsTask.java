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

package com.liferay.lcs.client.internal.task;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.internal.advisor.LCSKeyAdvisor;
import com.liferay.lcs.client.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.messaging.PortalModelMessage;
import com.liferay.portal.kernel.cluster.ClusterMasterExecutor;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 */
@Component(
	property = "lcs.client.scheduled.task.name=com.liferay.lcs.task.UserGroupsTask",
	service = ScheduledTask.class
)
public class UserGroupsTask extends BasePortalModelTask {

	@Activate
	public void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		setClusterMasterExecutor(_clusterMasterExecutor);
		setLCSGatewayService(_lcsGatewayClient);
		setLCSKeyAdvisor(_lcsKeyAdvisor);
		setPauseInterval(lcsConfiguration.scheduledTaskPauseInterval());
		setPageSize(lcsConfiguration.scheduledTaskPageSize());
	}

	@Override
	protected List<Map<String, Object>> getModels(int start, int end) {
		List<Map<String, Object>> userGroupMaps = new ArrayList<>();

		List<UserGroup> userGroups = _userGroupLocalService.getUserGroups(
			start, end);

		for (UserGroup userGroup : userGroups) {
			userGroupMaps.add(getUserGroupMap(userGroup));
		}

		return userGroupMaps;
	}

	@Override
	protected long getModelsCount() {
		return _userGroupLocalService.getUserGroupsCount();
	}

	@Override
	protected PortalModelMessage.Type getPortalModelType() {
		return PortalModelMessage.Type.USER_GROUP;
	}

	protected Map<String, Object> getUserGroupMap(UserGroup userGroup) {
		Map<String, Object> userGroupMap = new HashMap<>();

		int userGroupUsersCount = _userLocalService.getUserGroupUsersCount(
			userGroup.getUserGroupId());

		userGroupMap.put("companyId", userGroup.getCompanyId());
		userGroupMap.put("name", userGroup.getName());
		userGroupMap.put("userCount", userGroupUsersCount);
		userGroupMap.put("userGroupId", userGroup.getUserGroupId());
		userGroupMap.put("uuid", userGroup.getUuid());

		return userGroupMap;
	}

	@Reference
	private ClusterMasterExecutor _clusterMasterExecutor;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSGatewayClient _lcsGatewayClient;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	@Reference
	private UserGroupLocalService _userGroupLocalService;

	@Reference
	private UserLocalService _userLocalService;

}