/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.util;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Julio Camarero
 */
@Component(immediate = true, service = RecentGroupManager.class)
public class RecentGroupManager {

	public void addRecentGroup(HttpServletRequest request, Group group) {
		addRecentGroup(request, group.getGroupId());
	}

	public void addRecentGroup(HttpServletRequest request, long groupId) {
		long liveGroupId = _getLiveGroupId(groupId);

		if (liveGroupId <= 0) {
			return;
		}

		Group liveGroup = _groupLocalService.fetchGroup(liveGroupId);

		if (liveGroup.isLayoutPrototype() || liveGroup.isLayoutSetPrototype()) {
			return;
		}

		String value = _getRecentGroupsValue(request);

		List<Long> groupIds = ListUtil.fromArray(
			ArrayUtil.toLongArray(StringUtil.split(value, 0L)));

		groupIds.remove(liveGroupId);

		groupIds.add(0, liveGroupId);

		_setRecentGroupsValue(request, StringUtil.merge(groupIds));
	}

	public List<Group> getRecentGroups(HttpServletRequest request) {
		String value = _getRecentGroupsValue(request);

		return getRecentGroups(value);
	}

	protected List<Group> getRecentGroups(String value) {
		long[] groupIds = StringUtil.split(value, 0L);

		if (ArrayUtil.isEmpty(groupIds)) {
			return Collections.emptyList();
		}

		List<Group> groups = new ArrayList<>(groupIds.length);

		for (long groupId : groupIds) {
			Group group = _groupLocalService.fetchGroup(groupId);

			if (!_groupLocalService.isLiveGroupActive(group)) {
				continue;
			}

			groups.add(group);
		}

		return groups;
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	private long _getLiveGroupId(long groupId) {
		Group group = _groupLocalService.fetchGroup(groupId);

		if (group == null) {
			return 0;
		}

		if (!group.isStagedRemotely() && group.isStagingGroup()) {
			return group.getLiveGroupId();
		}

		return groupId;
	}

	private String _getRecentGroupsValue(HttpServletRequest request) {
		return SessionClicks.get(request, _KEY_RECENT_GROUPS, null);
	}

	private void _setRecentGroupsValue(
		HttpServletRequest request, String value) {

		SessionClicks.put(request, _KEY_RECENT_GROUPS, value);
	}

	private static final String _KEY_RECENT_GROUPS =
		"com.liferay.site.util_recentGroups";

	private GroupLocalService _groupLocalService;

}