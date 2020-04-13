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

package com.liferay.osb.community.generator.guest.internal.layout;

import com.liferay.osb.community.generator.guest.site.constants.GuestSiteConstants;
import com.liferay.osb.community.generator.layout.BaseLayoutGenerator;
import com.liferay.osb.community.generator.layout.LayoutGenerator;
import com.liferay.osb.community.generator.layout.LayoutVersion;
import com.liferay.osb.community.generator.layout.helper.LayoutWebContentHelper;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yury Butrymovich
 * @author Ryan Park
 * @author Haote Chou
 */
@Component(
	immediate = true,
	property = {
		"osb.community.layout.description=",
		"osb.community.layout.friendly.url=/home",
		"osb.community.layout.hidden:Boolean=true",
		"osb.community.layout.name=Home", "osb.community.layout.title=Home",
		"osb.community.layout.type=" + LayoutConstants.TYPE_PORTLET,
		"osb.community.site.generator.key=" + GuestSiteConstants.GUEST_SITE_KEY
	},
	service = LayoutGenerator.class
)
public class HomeLayoutGenerator extends BaseLayoutGenerator {

	public static final int LAYOUT_VERSION = 1;

	@Override
	public int getLayoutVersion() {
		return LAYOUT_VERSION;
	}

	@Override
	protected void doGenerate(long plid) throws Exception {
		Layout layout = _layoutLocalService.getLayout(plid);

		layout.setTypeSettings(StringPool.BLANK);

		LayoutTypePortlet layoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		User user = _userLocalService.getDefaultUser(layout.getCompanyId());

		layoutTypePortlet.setLayoutTemplateId(
			user.getUserId(), "1_column", false);

		layoutTypePortlet.addPortletId(
			user.getUserId(), _RANDOM_NINE_PORTLET_ID, "column-1", 1, false);

		layoutTypePortlet.addPortletId(
			user.getUserId(), _OPEN_SOURCE_FOR_LIFE_PORTLET_ID, "column-1", 2,
			false);

		_layoutLocalService.updateLayout(
			layout.getGroupId(), layout.isPrivateLayout(), layout.getLayoutId(),
			layout.getTypeSettings());

		_layoutWebContentHelper.setWebContent(
			layout, _OPEN_SOURCE_FOR_LIFE_PORTLET_ID, "OPEN_SOURCE_FOR_LIFE",
			"Open Source. For Life.", "/web_content/open_source_for_life",
			bundleContext.getBundle());
	}

	@Reference
	private void setLayoutVersion(LayoutVersion layoutVersion) {
		this.layoutVersion = layoutVersion;
	}

	private static final String _OPEN_SOURCE_FOR_LIFE_PORTLET_ID =
		"com_liferay_journal_content_web_portlet_JournalContentPortlet_" +
			"INSTANCE_pq90jAdypFZ3";

	private static final String _RANDOM_NINE_PORTLET_ID =
		"com_liferay_osb_community_doc_project_random_nine_web_" +
			"DocProjectRandomNinePortlet";

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutWebContentHelper _layoutWebContentHelper;

	@Reference
	private UserLocalService _userLocalService;

}