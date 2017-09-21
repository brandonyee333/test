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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.impl.CorpProjectImpl;
import com.liferay.osb.service.base.RemoteCorpProjectLocalServiceBaseImpl;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Amos Fong
 */
public class RemoteCorpProjectLocalServiceImpl
	extends RemoteCorpProjectLocalServiceBaseImpl {

	@Override
	public CorpProject addCorpProject(
			String dossieraProjectKey, String salesforceProjectKey, String name)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI("/add-corp-project"));

		Map<String, String> parts = new HashMap<>();

		parts.put("dossieraProjectKey", dossieraProjectKey);
		parts.put("name", name);
		parts.put("salesforceProjectKey", salesforceProjectKey);

		options.setParts(parts);

		JSONObject jsonObject = _sendRequest(options);

		return _translate(jsonObject);
	}

	@Override
	public CorpProject deleteCorpProject(long corpProjectId)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI("/delete-corp-project"));

		Map<String, String> parts = new HashMap<>();

		parts.put("corpProjectId", String.valueOf(corpProjectId));

		options.setParts(parts);

		JSONObject jsonObject = _sendRequest(options);

		return _translate(jsonObject);
	}

	@Override
	public CorpProject updateCorpProject(long corpProjectId, String name)
		throws PortalException {

		Http.Options options = new Http.Options();

		options.setLocation(_toURI("/update-corp-project"));

		Map<String, String> parts = new HashMap<>();

		parts.put("corpProjectId", String.valueOf(corpProjectId));
		parts.put("name", name);

		options.setParts(parts);

		JSONObject jsonObject = _sendRequest(options);

		return _translate(jsonObject);
	}

	private JSONObject _sendRequest(Http.Options options)
		throws PortalException {

		if (_log.isDebugEnabled()) {
			_log.debug("Sending request to: " + options.getLocation());
			_log.debug("Parameters: " + MapUtil.toString(options.getParts()));
		}

		options.addHeader("OSB_API_Token", PortletPropsValues.WEB_API_TOKEN);
		options.setPost(true);

		String response = StringPool.BLANK;

		try {
			byte[] bytes = HttpUtil.URLtoByteArray(options);

			if (bytes != null) {
				response = new String(bytes);
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Response: " + response);
			}
		}
		catch (IOException ioe) {
			throw new RemoteServiceException(ioe);
		}

		if (Validator.isNotNull(response)) {
			return JSONFactoryUtil.createJSONObject(response);
		}
		else {
			return JSONFactoryUtil.createJSONObject();
		}
	}

	private String _toURI(String endpoint) {
		StringBundler sb = new StringBundler(4);

		sb.append(Http.HTTPS_WITH_SLASH);
		sb.append(PortletPropsValues.WEB_DOMAIN_NAME);
		sb.append(_URL_API_JSONWS_CORP_PROJECT);
		sb.append(endpoint);

		return sb.toString();
	}

	private CorpProject _translate(JSONObject jsonObject) {
		CorpProject corpProject = new CorpProjectImpl();

		corpProject.setCorpProjectId(jsonObject.getLong("corpProjectId"));
		corpProject.setCreateDate(new Date(jsonObject.getLong("createDate")));
		corpProject.setDossieraProjectKey(
			jsonObject.getString("dossieraProjectKey"));
		corpProject.setModifiedDate(
			new Date(jsonObject.getLong("modifiedDate")));
		corpProject.setName(jsonObject.getString("name"));
		corpProject.setSalesforceProjectKey(
			jsonObject.getString("salesforceProjectKey"));

		String userUuid = jsonObject.getString("userUuid");

		List<User> users = userPersistence.findByUuid(userUuid);

		if (!users.isEmpty()) {
			User user = users.get(0);

			corpProject.setUserId(user.getUserId());
			corpProject.setUserName(user.getFullName());
		}

		return corpProject;
	}

	private static final String _URL_API_JSONWS_CORP_PROJECT =
		"/osb-portlet/api/jsonws/corpproject";

	private static final Log _log = LogFactoryUtil.getLog(
		RemoteCorpProjectLocalServiceImpl.class);

}