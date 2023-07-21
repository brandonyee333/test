/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalServiceUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Kenneth Chang
 */
public class VerifyLayout extends VerifyProcess {

	protected void deleteLinkedOrphanedLayouts() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(4);

			sb.append("delete from Layout where layoutPrototypeUuid != '' ");
			sb.append("and layoutPrototypeUuid not in (select uuid_ from ");
			sb.append("LayoutPrototype) and layoutPrototypeLinkEnabled = ");
			sb.append("[$TRUE$]");

			runSQL(sb.toString());
		}
	}

	@Override
	protected void doVerify() throws Exception {
		deleteLinkedOrphanedLayouts();
		updateUnlinkedOrphanedLayouts();
		verifyFriendlyURL();
		verifyLayoutPrototypeLinkEnabled();
		verifyUuid();
	}

	protected void updateUnlinkedOrphanedLayouts() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			StringBundler sb = new StringBundler(4);

			sb.append("update Layout set layoutPrototypeUuid = null where ");
			sb.append("layoutPrototypeUuid != '' and layoutPrototypeUuid not ");
			sb.append("in (select uuid_ from LayoutPrototype) and ");
			sb.append("layoutPrototypeLinkEnabled = [$FALSE$]");

			runSQL(sb.toString());
		}
	}

	protected void verifyFriendlyURL() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Layout> layouts =
				LayoutLocalServiceUtil.getNullFriendlyURLLayouts();

			for (Layout layout : layouts) {
				List<LayoutFriendlyURL> layoutFriendlyURLs =
					LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
						layout.getPlid());

				for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
					String friendlyURL =
						StringPool.SLASH + layout.getLayoutId();

					LayoutLocalServiceUtil.updateFriendlyURL(
						layout.getUserId(), layout.getPlid(), friendlyURL,
						layoutFriendlyURL.getLanguageId());
				}
			}

			ActionableDynamicQuery actionableDynamicQuery =
				LayoutFriendlyURLLocalServiceUtil.getActionableDynamicQuery();

			actionableDynamicQuery.setAddCriteriaMethod(
				new ActionableDynamicQuery.AddCriteriaMethod() {

					@Override
					public void addCriteria(DynamicQuery dynamicQuery) {
						DynamicQuery layoutDynamicQuery =
							LayoutLocalServiceUtil.dynamicQuery();

						Projection projection = ProjectionFactoryUtil.property(
							"plid");

						layoutDynamicQuery.setProjection(projection);

						Property plidProperty = PropertyFactoryUtil.forName(
							"plid");

						dynamicQuery.add(
							plidProperty.notIn(layoutDynamicQuery));
					}

				});
			actionableDynamicQuery.setPerformActionMethod(
				new ActionableDynamicQuery.PerformActionMethod
					<LayoutFriendlyURL>() {

					@Override
					public void performAction(
						LayoutFriendlyURL layoutFriendlyURL) {

						LayoutFriendlyURLLocalServiceUtil.
							deleteLayoutFriendlyURL(layoutFriendlyURL);
					}

				});

			actionableDynamicQuery.performActions();
		}
	}

	protected void verifyLayoutPrototypeLinkEnabled() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL(
				"update Layout set layoutPrototypeLinkEnabled = [$FALSE$] " +
					"where type_ = 'link_to_layout' and " +
						"layoutPrototypeLinkEnabled = [$TRUE$]");
		}
	}

	protected void verifyUuid() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			verifyUuid("AssetEntry");

			runSQL(
				"update Layout set uuid_ = sourcePrototypeLayoutUuid where " +
					"sourcePrototypeLayoutUuid != '' and uuid_ != " +
						"sourcePrototypeLayoutUuid");
		}
	}

	protected void verifyUuid(String tableName) throws Exception {
		StringBundler sb = new StringBundler(12);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set layoutUuid = (select distinct ");
		sb.append("sourcePrototypeLayoutUuid from Layout where Layout.uuid_ ");
		sb.append("= ");
		sb.append(tableName);
		sb.append(".layoutUuid) where exists (select 1 from Layout where ");
		sb.append("Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid and Layout.uuid_ != ");
		sb.append("Layout.sourcePrototypeLayoutUuid and ");
		sb.append("Layout.sourcePrototypeLayoutUuid != '')");

		runSQL(sb.toString());
	}

}