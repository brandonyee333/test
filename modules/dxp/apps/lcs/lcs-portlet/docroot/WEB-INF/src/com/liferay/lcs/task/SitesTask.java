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

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.PortalModelMessage;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 * @author Igor Beslic
 */
public class SitesTask extends BasePortalModelTask {

	protected DynamicQuery getGroupDynamicQuery() {
		DynamicQuery dynamicQuery = GroupLocalServiceUtil.dynamicQuery();

		Criterion siteCriterion = RestrictionsFactoryUtil.eq("site", true);

		dynamicQuery.add(siteCriterion);

		Criterion activeCriterion = RestrictionsFactoryUtil.eq("active", true);

		dynamicQuery.add(activeCriterion);

		return dynamicQuery;
	}

	@Override
	protected List<Map<String, Object>> getModels(int start, int end) {
		List<Map<String, Object>> siteMaps = new ArrayList<>();

		List<Group> groups = GroupLocalServiceUtil.dynamicQuery(
			getGroupDynamicQuery(), start, end);

		for (Group group : groups) {
			siteMaps.add(getSiteMap(group));
		}

		return siteMaps;
	}

	@Override
	protected long getModelsCount() {
		return GroupLocalServiceUtil.dynamicQueryCount(getGroupDynamicQuery());
	}

	@Override
	protected PortalModelMessage.Type getPortalModelType() {
		return PortalModelMessage.Type.SITE;
	}

	protected Map<String, Object> getSiteMap(Group group) {
		Map<String, Object> site = new HashMap<>();

		site.put("companyId", group.getCompanyId());
		site.put("friendlyURL", group.getFriendlyURL());
		site.put("groupId", group.getGroupId());
		site.put("groupKey", group.getGroupKey());
		site.put("name", group.getName());
		site.put("parentGroupId", group.getParentGroupId());
		site.put("uuid", group.getUuid());

		return site;
	}

}