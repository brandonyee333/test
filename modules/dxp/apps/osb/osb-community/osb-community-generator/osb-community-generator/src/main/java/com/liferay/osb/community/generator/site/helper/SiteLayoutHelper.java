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

package com.liferay.osb.community.generator.site.helper;

import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(service = SiteLayoutHelper.class)
public class SiteLayoutHelper {

	public Layout updateLayout(
			long groupId, String name, String title, String description,
			String type, boolean hidden, String friendlyURL)
		throws Exception {

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			groupId, false, friendlyURL);

		if (layout == null) {
			Group group = _groupLocalService.getGroup(groupId);

			layout = _layoutLocalService.addLayout(
				group.getCreatorUserId(), groupId, false, 0, name, title,
				description, type, hidden, friendlyURL, new ServiceContext());
		}
		else {
			Map<Locale, String> nameMap = layout.getNameMap();

			Locale locale = LocaleUtil.getSiteDefault();

			nameMap.put(locale, name);

			Map<Locale, String> titleMap = layout.getTitleMap();

			titleMap.put(locale, title);

			Map<Locale, String> descriptionMap = layout.getDescriptionMap();

			descriptionMap.put(locale, description);

			Map<Locale, String> friendlyURLMap = new HashMap<>();

			friendlyURLMap.put(locale, friendlyURL);

			layout = _layoutLocalService.updateLayout(
				layout.getGroupId(), false, layout.getLayoutId(), 0, nameMap,
				titleMap, descriptionMap, layout.getKeywordsMap(),
				layout.getRobotsMap(), type, hidden, friendlyURLMap, false,
				null, new ServiceContext());
		}

		return layout;
	}

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

}