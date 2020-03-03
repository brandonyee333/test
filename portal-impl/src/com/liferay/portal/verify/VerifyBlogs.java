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

package com.liferay.portal.verify;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.StagingUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

/**
 * @author Raymond Augé
 */
public class VerifyBlogs extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		updateEntryAssets();
		updateStagedPortletNames();
		verifyStatus();
	}

	protected void updateEntryAssets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<BlogsEntry> entries =
				BlogsEntryLocalServiceUtil.getNoAssetEntries();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing " + entries.size() + " entries with no asset");
			}

			for (BlogsEntry entry : entries) {
				try {
					BlogsEntryLocalServiceUtil.updateAsset(
						entry.getUserId(), entry, null, null, null, null);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Unable to update asset for entry ",
								String.valueOf(entry.getEntryId()), ": ",
								e.getMessage()));
					}
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Assets verified for entries");
			}
		}
	}

	protected void updateStagedPortletNames() throws PortalException {
		ActionableDynamicQuery groupActionableDynamicQuery =
			GroupLocalServiceUtil.getActionableDynamicQuery();

		groupActionableDynamicQuery.setAddCriteriaMethod(
			dynamicQuery -> {
				Property siteProperty = PropertyFactoryUtil.forName("site");

				dynamicQuery.add(siteProperty.eq(Boolean.TRUE));
			});
		groupActionableDynamicQuery.setPerformActionMethod(
			(ActionableDynamicQuery.PerformActionMethod<Group>)group -> {
				UnicodeProperties typeSettingsProperties =
					group.getTypeSettingsProperties();

				if (typeSettingsProperties == null) {
					return;
				}

				String propertyKey = StagingUtil.getStagedPortletId(
					PortletKeys.BLOGS);

				String propertyValue = typeSettingsProperties.getProperty(
					propertyKey);

				if (Validator.isNull(propertyValue)) {
					return;
				}

				typeSettingsProperties.remove(propertyKey);

				propertyKey = StagingUtil.getStagedPortletId(
					PortletKeys.BLOGS_ADMIN);

				typeSettingsProperties.put(propertyKey, propertyValue);

				group.setTypeSettingsProperties(typeSettingsProperties);

				GroupLocalServiceUtil.updateGroup(group);
			});

		groupActionableDynamicQuery.performActions();
	}

	protected void verifyStatus() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL(
				"update BlogsEntry set status = " +
					WorkflowConstants.STATUS_APPROVED +
						" where status is null");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(VerifyBlogs.class);

}