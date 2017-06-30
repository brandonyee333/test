/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.corpmembersactivities.util;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpGroup;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CorpGroupLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.model.Group;
import com.liferay.portlet.blogs.model.BlogsEntry;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Rachael Koestartyo
 * @author Peter Shin
 */
public class CorpMembersActivitiesUtil {

	public static List<SocialActivity> getSocialActivities(
			Group scopeGroup, int start, int end)
		throws Exception {

		long[] organizationIds = getOrganizationIds(scopeGroup);

		if ((organizationIds == null) || (organizationIds.length == 0)) {
			return Collections.emptyList();
		}

		List<Long> socialActivityIds = getSocialActivityIds(organizationIds);

		return getSocialActivities(socialActivityIds, start, end);
	}

	protected static long[] getOrganizationIds(Group scopeGroup)
		throws Exception {

		if ((scopeGroup == null) || !scopeGroup.isOrganization()) {
			return new long[0];
		}

		CorpEntry corpEntry =
			CorpEntryLocalServiceUtil.fetchOrganizationCorpEntry(
				scopeGroup.getClassPK());

		if (corpEntry != null) {
			return new long[] {corpEntry.getOrganizationId()};
		}

		CorpGroup corpGroup =
			CorpGroupLocalServiceUtil.fetchOrganizationCorpGroup(
				scopeGroup.getClassPK());

		if (corpGroup == null) {
			return new long[0];
		}

		long[] organizationIds = new long[] {corpGroup.getOrganizationId()};

		List<CorpEntry> corpEntries =
			CorpEntryLocalServiceUtil.getCorpGroupCorpEntries(
				corpGroup.getCorpGroupId());

		for (CorpEntry curCorpEntry : corpEntries) {
			if (curCorpEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				continue;
			}

			organizationIds = ArrayUtil.append(
				organizationIds, curCorpEntry.getOrganizationId());
		}

		return organizationIds;
	}

	protected static List<SocialActivity> getSocialActivities(
			List<Long> socialActivityIds, int start, int end)
		throws Exception {

		if (start < 0) {
			start = 0;
		}

		if ((end < 0) || (end > socialActivityIds.size())) {
			end = socialActivityIds.size();
		}

		List<SocialActivity> socialActivities = new ArrayList<SocialActivity>();

		for (int i = start; i < socialActivityIds.size(); i++) {
			SocialActivity socialActivity =
				SocialActivityLocalServiceUtil.fetchSocialActivity(
					socialActivityIds.get(i));

			if (socialActivity != null) {
				socialActivities.add(socialActivity);
			}

			if (socialActivities.size() == (end - start)) {
				break;
			}
		}

		return Collections.unmodifiableList(socialActivities);
	}

	protected static List<Long> getSocialActivityIds(long[] organizationIds)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			String sql = CustomSQLUtil.get(_FIND_BY_G_C_O);

			sql = StringUtil.replace(
				sql,
				new String[] {"[$CLASS_NAME_IDS$]", "[$ORGANIZATION_IDS$]"},
				new String[] {
					StringUtil.merge(_CLASS_NAME_IDS),
					StringUtil.merge(organizationIds)
				}
			);

			ps = con.prepareStatement(sql);

			ps.setLong(1, OSBConstants.GROUP_GUEST_ID);

			rs = ps.executeQuery();

			List<Long> socialActivityIds = new ArrayList<Long>();

			while (rs.next()) {
				long socialActivityId = rs.getLong(1);

				socialActivityIds.add(socialActivityId);
			}

			return socialActivityIds;
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	private static final long[] _CLASS_NAME_IDS = {
		PortalUtil.getClassNameId(BlogsEntry.class),
		PortalUtil.getClassNameId(MBMessage.class),
		PortalUtil.getClassNameId(WikiPage.class)
	};

	private static final String _FIND_BY_G_C_O =
		CorpMembersActivitiesUtil.class.getName() + ".findByG_C_O";

}