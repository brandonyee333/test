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

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.role.update",
	service = RoleUpdateMessageProcessor.class
)
public class RoleUpdateMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		Role role = fetchRole(jsonObject);

		if (role == null) {
			return;
		}

		String name = jsonObject.getString("name");

		roleLocalService.updateRole(
			role.getRoleId(), name, role.getTitleMap(),
			role.getDescriptionMap(), role.getSubtype(), null);
	}

}