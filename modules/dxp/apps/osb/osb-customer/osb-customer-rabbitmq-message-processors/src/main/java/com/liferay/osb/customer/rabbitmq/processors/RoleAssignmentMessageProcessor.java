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

package com.liferay.osb.customer.rabbitmq.processors;

import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = {"routing.key=entity.role.assigned"},
	service = RoleAssignmentMessageProcessor.class
)
public class RoleAssignmentMessageProcessor implements MessageProcessor {

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		try {
			assignRole(message);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected void assignRole(String message) throws Exception {
		JSONObject jsonObject = _jsonFactory.createJSONObject(message.trim());

		JSONObject roleJSONObject = jsonObject.getJSONObject("role");

		Role role = fetchRole(roleJSONObject);

		if (role == null) {
			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		User user = fetchUser(userJSONObject);

		if (user == null) {
			return;
		}

		_userLocalService.addRoleUser(role.getRoleId(), user.getUserId());
	}

	protected Role fetchRole(JSONObject jsonObject) {
		String uuid = jsonObject.getString("uuid");

		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			Role role = _roleLocalService.fetchRoleByUuidAndCompanyId(
				uuid, company.getCompanyId());

			if (role != null) {
				return role;
			}
		}

		return null;
	}

	protected User fetchUser(JSONObject jsonObject) {
		String uuid = jsonObject.getString("uuid");

		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			User user = _userLocalService.fetchUserByUuidAndCompanyId(
				uuid, company.getCompanyId());

			if (user != null) {
				return user;
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RoleAssignmentMessageProcessor.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}