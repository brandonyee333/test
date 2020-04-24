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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.alloy.mvc.AlloyServiceInvoker;
import com.liferay.osb.loop.constants.LoopConstants;
import com.liferay.osb.loop.model.LoopJobTitle;
import com.liferay.osb.loop.service.LoopJobTitleLocalServiceUtil;
import com.liferay.osb.loop.web.internal.composite.LoopJobTitleComposite;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Timothy Bell
 */
public class LoopJobTitleUtil {

	public static LoopJobTitle fetchLoopJobTitle(
			HttpServletRequest request, ThemeDisplay themeDisplay)
		throws Exception {

		String id = ParamUtil.getString(request, "id");

		if (Validator.isNumber(id)) {
			return LoopJobTitleLocalServiceUtil.fetchLoopJobTitle(
				GetterUtil.getLong(id));
		}
		else if (id.indexOf(LoopConstants.URL_TOKEN_LOOP_JOB_TITLES_NAME) ==
					0) {

			return fetchLoopJobTitle(
				themeDisplay.getCompanyId(), id.substring(1));
		}

		return null;
	}

	public static LoopJobTitle getLoopJobTitle(
			ThemeDisplay themeDisplay, String id)
		throws Exception {

		LoopJobTitle loopJobTitle = fetchLoopJobTitle(
			themeDisplay.getCompanyId(), id);

		if (loopJobTitle == null) {
			throw new Exception("The job title does not exist");
		}

		return loopJobTitle;
	}

	public static List<LoopJobTitleComposite> getLoopJobTitleComposites(
			ThemeDisplay themeDisplay)
		throws Exception {

		AlloyServiceInvoker loopJobTitleAlloyServiceInvoker =
			new AlloyServiceInvoker(LoopJobTitle.class.getName());

		DynamicQuery loopJobTitleDynamicQuery =
			loopJobTitleAlloyServiceInvoker.buildDynamicQuery(
				new Object[] {"companyId", themeDisplay.getCompanyId()});

		Projection loopJobTitleIdProjection = ProjectionFactoryUtil.property(
			"loopJobTitleId");

		loopJobTitleDynamicQuery.setProjection(loopJobTitleIdProjection);

		List<Long> loopJobTitleIds =
			loopJobTitleAlloyServiceInvoker.executeDynamicQuery(
				loopJobTitleDynamicQuery);

		return LoopCompositeUtil.getComposites(
			loopJobTitleIds, LoopJobTitleComposite.class,
			new Class<?>[] {Long.class, ThemeDisplay.class},
			new Object[] {themeDisplay});
	}

	protected static LoopJobTitle fetchLoopJobTitle(long companyId, String name)
		throws Exception {

		List<LoopJobTitle> loopJobTitles =
			_alloyServiceInvoker.executeDynamicQuery(
				new Object[] {"companyId", companyId, "name", name});

		if (!loopJobTitles.isEmpty()) {
			return loopJobTitles.get(0);
		}

		return null;
	}

	private static final AlloyServiceInvoker _alloyServiceInvoker =
		new AlloyServiceInvoker(LoopJobTitle.class.getName());

}