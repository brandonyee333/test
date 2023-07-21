/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.model.SocialRequest;
import com.liferay.social.kernel.service.SocialRequestLocalServiceUtil;

/**
 * @author Bryan Engler
 * @author Sherry Yang
 */
public class VerifySocial extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		ActionableDynamicQuery socialRequestActionableDynamicQuery =
			SocialRequestLocalServiceUtil.getActionableDynamicQuery();

		socialRequestActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					long classNameId = PortalUtil.getClassNameId(Group.class);

					dynamicQuery.add(classNameIdProperty.eq(classNameId));

					Property classPKProperty = PropertyFactoryUtil.forName(
						"classPK");

					DynamicQuery groupDynamicQuery =
						DynamicQueryFactoryUtil.forClass(Group.class);

					Projection projection = ProjectionFactoryUtil.property(
						"groupId");

					groupDynamicQuery.setProjection(projection);

					dynamicQuery.add(classPKProperty.notIn(groupDynamicQuery));
				}

			});
		socialRequestActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SocialRequest>() {

				@Override
				public void performAction(SocialRequest socialRequest) {
					try (LoggingTimer loggingTimer = new LoggingTimer()) {
						SocialRequestLocalServiceUtil.deleteRequest(
							socialRequest);
					}
				}

			});

		socialRequestActionableDynamicQuery.performActions();
	}

}