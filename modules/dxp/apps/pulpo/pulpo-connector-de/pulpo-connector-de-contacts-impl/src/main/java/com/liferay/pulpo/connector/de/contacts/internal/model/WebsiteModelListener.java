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

package com.liferay.pulpo.connector.de.contacts.internal.model;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Website;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = ContactsModelListener.class)
public class WebsiteModelListener extends ContactsModelListener<Website> {

	@Override
	public void onAfterCreate(Website model) throws ModelListenerException {
		onAfterUpdate(model);
	}

	@Override
	protected List<Website> getModels(User user) {
		return user.getWebsites();
	}

}