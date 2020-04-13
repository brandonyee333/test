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

package com.liferay.osb.community.meetup.groups.web.internal.action;

import com.liferay.osb.community.meetup.client.MeetupClient;
import com.liferay.osb.community.meetup.groups.web.internal.constants.MeetupGroupsPortletKeys;
import com.liferay.osb.community.meetup.model.MeetupGroup;
import com.liferay.osb.community.meetup.service.MeetupGroupLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + MeetupGroupsPortletKeys.MEETUP_GROUPS,
		"mvc.command.name=/"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		Template template = (Template)renderRequest.getAttribute(
			WebKeys.TEMPLATE);
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		List<Map<String, Object>> meetupGroups = null;

		try {
			meetupGroups = getMeetupGroups(renderRequest);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			meetupGroups = new ArrayList<>(0);
		}

		template.put("meetupGroups", meetupGroups);

		Locale locale = themeDisplay.getLocale();

		template.put("strings", getStringsMap(renderRequest, locale));

		return "View";
	}

	protected List<Map<String, Object>> getMeetupGroups(
			RenderRequest renderRequest)
		throws Exception {

		List<MeetupGroup> meetupGroups =
			_meetupGroupLocalService.getMeetupGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		List<Map<String, Object>> meetupGroupsList = new ArrayList<>(
			meetupGroups.size());

		if (meetupGroups.isEmpty()) {
			meetupGroups = _meetupClient.getMeetupGroups();

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				MeetupGroup.class.getName(), renderRequest);

			for (MeetupGroup meetupGroup : meetupGroups) {
				_meetupGroupLocalService.addMeetupGroup(
					meetupGroup.getName(), meetupGroup.getCity(),
					meetupGroup.getMemberCount(), meetupGroup.getDescription(),
					meetupGroup.getUrl(), serviceContext);
			}

			meetupGroups = _meetupGroupLocalService.getMeetupGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}

		for (MeetupGroup meetupGroup : meetupGroups) {
			Map<String, Object> meetupGroupMap = new HashMap<>(
				meetupGroups.size());

			meetupGroupMap.put("city", meetupGroup.getCity());
			meetupGroupMap.put("description", meetupGroup.getDescription());
			meetupGroupMap.put("memberCount", meetupGroup.getMemberCount());
			meetupGroupMap.put("name", meetupGroup.getName());
			meetupGroupMap.put("url", meetupGroup.getUrl());

			meetupGroupsList.add(meetupGroupMap);
		}

		return meetupGroupsList;
	}

	protected Map<String, Object> getStringsMap(
		RenderRequest renderRequest, Locale locale) {

		Map<String, Object> stringsMap = new HashMap<>();

		ResourceBundle resourceBundle =
			_resourceBundleLoader.loadResourceBundle(
				LanguageUtil.getLanguageId(locale));

		stringsMap.put(
			"find-out-where-and-when-people-near-you-meet",
			LanguageUtil.get(
				resourceBundle,
				"find-out-where-and-when-people-near-you-meet"));
		stringsMap.put(
			"groups-meeting-nearby",
			LanguageUtil.get(resourceBundle, "groups-meeting-nearby"));
		stringsMap.put(
			"learn-more", LanguageUtil.get(resourceBundle, "learn-more"));

		return stringsMap;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ViewMVCRenderCommand.class);

	@Reference
	private MeetupClient _meetupClient;

	@Reference
	private MeetupGroupLocalService _meetupGroupLocalService;

	@Reference(
		target = "(component.name=com.liferay.osb.community.lang.ResourceBundleLoaderComponent)",
		unbind = "-"
	)
	private ResourceBundleLoader _resourceBundleLoader;

}