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

package com.liferay.osb.community.generator.basic.project.internal.site;

import com.liferay.osb.community.generator.basic.project.site.constants.BasicProjectSiteConstants;
import com.liferay.osb.community.generator.layout.LayoutGenerator;
import com.liferay.osb.community.generator.site.BaseSiteGenerator;
import com.liferay.osb.community.generator.site.SiteGenerator;
import com.liferay.osb.community.generator.site.helper.SiteLayoutHelper;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.service.LayoutSetLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 */
@Component(
	immediate = true,
	property = {
		"osb.community.site.generator.key=" + BasicProjectSiteConstants.BASIC_PROJECT_SITE_KEY,
		"osb.community.site.generator.name=Basic Project"
	},
	service = SiteGenerator.class
)
public class BasicProjectSiteGenerator extends BaseSiteGenerator {

	@Override
	public void generate(long groupId) throws Exception {
		generateLayout(groupId, _overviewLayoutGenerator);

		generateLayout(groupId, _documentationLayoutGenerator);

		generateLayout(groupId, _contributionLayoutGenerator);

		generateLayoutSets(groupId);
	}

	protected void generateLayout(long groupId, LayoutGenerator layoutGenerator)
		throws Exception {

		Layout layout = _siteLayoutHelper.updateLayout(
			groupId, layoutGenerator.getLayoutName(),
			layoutGenerator.getLayoutTitle(),
			layoutGenerator.getLayoutDescription(),
			layoutGenerator.getLayoutType(), layoutGenerator.getLayoutHidden(),
			layoutGenerator.getLayoutFriendlyURL());

		layoutGenerator.generate(layout.getPlid());
	}

	protected void generateLayoutSets(long groupId) throws Exception {
		LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
			groupId, false);

		_layoutSetLocalService.updateLookAndFeel(
			groupId, "osbcommunity_WAR_osbcommunity",
			layoutSet.getColorSchemeId(), layoutSet.getCss());
	}

	@Reference(target = "(osb.community.layout.friendly.url=/contribution)")
	private LayoutGenerator _contributionLayoutGenerator;

	@Reference(target = "(osb.community.layout.friendly.url=/documentation)")
	private LayoutGenerator _documentationLayoutGenerator;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference(target = "(osb.community.layout.friendly.url=/overview)")
	private LayoutGenerator _overviewLayoutGenerator;

	@Reference
	private SiteLayoutHelper _siteLayoutHelper;

}