/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import com.liferay.portal.kernel.model.User;

import java.util.List;

import oasis.names.tc.wsrp.v2.types.UserProfile;

import org.apache.axis.message.MessageElement;

/**
 * @author Michael C. Han
 */
public interface ConsumerRequestExtension {

	public void addClientAttributes(List<MessageElement> messageElements);

	public void addUserProfileAttributes(UserProfile userProfile, User user);

}