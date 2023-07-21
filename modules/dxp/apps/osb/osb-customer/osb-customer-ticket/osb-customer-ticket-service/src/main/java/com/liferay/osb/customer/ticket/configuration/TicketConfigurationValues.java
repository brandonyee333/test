/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.ticket.configuration;

/**
 * @author Amos Fong
 */
public class TicketConfigurationValues {

	public static final String FILE_REPOSITORY_DEFAULT_ID =
		TicketConfigurationUtil.get(
			PortletPropsKeys.FILE_REPOSITORY_DEFAULT_ID);

	public static final String[] FILE_REPOSITORY_IDS =
		TicketConfigurationUtil.getArray(PortletPropsKeys.FILE_REPOSITORY_IDS);

}