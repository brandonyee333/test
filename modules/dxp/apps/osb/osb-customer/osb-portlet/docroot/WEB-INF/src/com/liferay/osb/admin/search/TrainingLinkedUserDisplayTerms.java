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

package com.liferay.osb.admin.search;

import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

import javax.portlet.PortletRequest;

/**
 * @author Douglas Wong
 */
public class TrainingLinkedUserDisplayTerms extends DisplayTerms {

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String FIRST_NAME = "firstName";

	public static final String LAST_NAME = "lastName";

	public TrainingLinkedUserDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		long trainingCustomerId = ParamUtil.getLong(
			portletRequest, "trainingCustomerId");

		String defaultFirstName = StringPool.BLANK;
		String defaultLastName = StringPool.BLANK;

		try {
			TrainingCustomer trainingCustomer =
				TrainingCustomerLocalServiceUtil.getTrainingCustomer(
					trainingCustomerId);

			User user = trainingCustomer.getUser();

			defaultFirstName = user.getFirstName();
			defaultLastName = user.getLastName();
		}
		catch (Exception e) {
		}

		emailAddress = ParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = ParamUtil.getString(
			portletRequest, FIRST_NAME, defaultFirstName);
		lastName = ParamUtil.getString(
			portletRequest, LAST_NAME, defaultLastName);
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	protected String emailAddress;
	protected String firstName;
	protected String lastName;

}