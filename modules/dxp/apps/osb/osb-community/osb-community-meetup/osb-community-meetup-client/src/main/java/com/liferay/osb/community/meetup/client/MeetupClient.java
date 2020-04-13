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

package com.liferay.osb.community.meetup.client;

import com.liferay.osb.community.meetup.client.util.MeetupServiceConfigurationUtil;
import com.liferay.osb.community.meetup.model.MeetupGroup;
import com.liferay.osb.community.meetup.service.MeetupGroupLocalService;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Http;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jamie Sammons
 */
@Component(immediate = true, service = MeetupClient.class)
public class MeetupClient {

	public List<MeetupGroup> getMeetupGroups() throws Exception {
		Http.Options options = new Http.Options();

		String apiCallURL = _API_CALL_PREFIX + "groups/";

		apiCallURL = _http.addParameter(
			apiCallURL, "key", MeetupServiceConfigurationUtil.getAPIKey());

		options.setLocation(apiCallURL);

		String content = _http.URLtoString(options);

		Http.Response response = options.getResponse();

		if (response.getResponseCode() != HttpServletResponse.SC_OK) {
			throw new Exception("Unable to get groups from Meetup");
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray(content);

		List<MeetupGroup> meetupGroups = new ArrayList<>(jsonArray.length());

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			MeetupGroup meetupGroup =
				_meetupGroupLocalService.createMeetupGroup(0);

			meetupGroup.setName(jsonObject.getString("name"));
			meetupGroup.setCity(jsonObject.getString("city"));
			meetupGroup.setMemberCount(jsonObject.getInt("member_count"));
			meetupGroup.setDescription(
				HtmlUtil.stripHtml(jsonObject.getString("description")));
			meetupGroup.setUrl(
				"https://www.meetup.com/" + jsonObject.getString("urlname"));

			meetupGroups.add(meetupGroup);
		}

		return meetupGroups;
	}

	private static final String _API_CALL_PREFIX =
		"https://api.meetup.com/pro/liferay/";

	@Reference
	private Http _http;

	@Reference
	private MeetupGroupLocalService _meetupGroupLocalService;

}