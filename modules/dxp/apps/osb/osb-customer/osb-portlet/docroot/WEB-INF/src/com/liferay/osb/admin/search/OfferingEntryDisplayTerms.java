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

import com.liferay.osb.model.OfferingEntry;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Alan Zhang
 */
public class OfferingEntryDisplayTerms extends DisplayTerms {

	public static final String EMAIL_ADDRESS =
		OfferingEntry.class.getName() + "emailAddress";

	public static final String FIRST_NAME =
		OfferingEntry.class.getName() + "firstName";

	public static final String LAST_NAME =
		OfferingEntry.class.getName() + "lastName";

	public static final String MIDDLE_NAME =
		OfferingEntry.class.getName() + "middleName";

	public static final String SCREEN_NAME =
		OfferingEntry.class.getName() + "screenName";

	public OfferingEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		emailAddress = ParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = ParamUtil.getString(portletRequest, FIRST_NAME);
		lastName = ParamUtil.getString(portletRequest, LAST_NAME);
		middleName = ParamUtil.getString(portletRequest, MIDDLE_NAME);
		screenName = ParamUtil.getString(portletRequest, SCREEN_NAME);
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

	public String getMiddleName() {
		return middleName;
	}

	public String getScreenName() {
		return screenName;
	}

	protected String emailAddress;
	protected String firstName;
	protected String lastName;
	protected String middleName;
	protected String screenName;

}