/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyController;
import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopDivisionRel;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.service.LoopDivisionRelLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopDivisionConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.List;

/**
 * @author Timothy Bell
 */
public class LoopDivisionRelUtil {

	public static void addLoopDivisionRel(
			AlloyController alloyController, long childLoopDivisionId,
			long loopPersonId, long parentLoopDivisionId)
		throws Exception {

		long loopDivisionRelsCount =
			_alloyServiceInvoker.executeDynamicQueryCount(
				new Object[] {
					"childLoopDivisionId", childLoopDivisionId, "loopPersonId",
					loopPersonId, "parentLoopDivisionId", parentLoopDivisionId
				});

		if (loopDivisionRelsCount > 0) {
			return;
		}

		LoopDivisionRel loopDivisionRel =
			LoopDivisionRelLocalServiceUtil.createLoopDivisionRel(0);

		loopDivisionRel.setChildLoopDivisionId(childLoopDivisionId);
		loopDivisionRel.setLoopPersonId(loopPersonId);
		loopDivisionRel.setParentLoopDivisionId(parentLoopDivisionId);

		alloyController.updateModelIgnoreRequest(loopDivisionRel);
	}

	public static void addLoopDivisionRels(
			AlloyController alloyController, long loopDivisionId,
			long loopPersonId)
		throws Exception {

		ThemeDisplay themeDisplay = alloyController.getThemeDisplay();

		int relatedLoopDivisionType = 0;

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

		if (loopDivision.getType() == LoopDivisionConstants.TYPE_DEPARTMENT) {
			relatedLoopDivisionType = LoopDivisionConstants.TYPE_TEAM;
		}
		else if (loopDivision.getType() == LoopDivisionConstants.TYPE_TEAM) {
			relatedLoopDivisionType = LoopDivisionConstants.TYPE_DEPARTMENT;
		}
		else {
			return;
		}

		List<Long> relatedLoopDivisionIds =
			LoopParticipantAssignmentUtil.
				getLoopParticipantAssignmentLoopDivisionIds(
					themeDisplay.getCompanyId(), loopPersonId,
					relatedLoopDivisionType, true, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS);

		for (long relatedLoopDivisionId : relatedLoopDivisionIds) {
			if (loopDivision.getType() ==
					LoopDivisionConstants.TYPE_DEPARTMENT) {

				addLoopDivisionRel(
					alloyController, relatedLoopDivisionId, loopPersonId,
					loopDivisionId);
			}
			else {
				addLoopDivisionRel(
					alloyController, loopDivisionId, loopPersonId,
					relatedLoopDivisionId);
			}
		}
	}

	public static void deleteLoopDivisionRels(long loopDivisionId)
		throws Exception {

		DynamicQuery dynamicQuery = _alloyServiceInvoker.buildDynamicQuery();

		Property childLoopDivisionIdProperty = PropertyFactoryUtil.forName(
			"childLoopDivisionId");
		Property parentLoopDivisionIdProperty = PropertyFactoryUtil.forName(
			"parentLoopDivisionId");

		dynamicQuery.add(
			RestrictionsFactoryUtil.or(
				childLoopDivisionIdProperty.eq(loopDivisionId),
				parentLoopDivisionIdProperty.eq(loopDivisionId)));

		List<LoopDivisionRel> loopDivisionRels =
			_alloyServiceInvoker.executeDynamicQuery(dynamicQuery);

		for (LoopDivisionRel loopDivisionRel : loopDivisionRels) {
			LoopDivisionRelLocalServiceUtil.deleteLoopDivisionRel(
				loopDivisionRel);
		}
	}

	public static void deleteLoopPersonLoopDivisionRels(
			long loopDivisionId, long loopPersonId)
		throws Exception {

		String propertyName = StringPool.BLANK;

		LoopDivision loopDivision =
			LoopDivisionLocalServiceUtil.getLoopDivision(loopDivisionId);

		if (loopDivision.getType() == LoopDivisionConstants.TYPE_DEPARTMENT) {
			propertyName = "parentLoopDivisionId";
		}
		else if (loopDivision.getType() == LoopDivisionConstants.TYPE_TEAM) {
			propertyName = "childLoopDivisionId";
		}
		else {
			return;
		}

		List<LoopDivisionRel> loopDivisionRels =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {
					"loopPersonId", loopPersonId, propertyName, loopDivisionId
				});

		for (LoopDivisionRel loopDivisionRel : loopDivisionRels) {
			LoopDivisionRelLocalServiceUtil.deleteLoopDivisionRel(
				loopDivisionRel);
		}
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopDivisionRel.class.getName());

}