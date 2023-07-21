/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.util;

import javax.xml.namespace.QName;

import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Markup_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_PortletManagement_PortType;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_Registration_PortType;
import oasis.names.tc.wsrp.v2.types.PortletDescription;
import oasis.names.tc.wsrp.v2.types.PropertyDescription;
import oasis.names.tc.wsrp.v2.types.RegistrationContext;
import oasis.names.tc.wsrp.v2.types.ServiceDescription;

/**
 * @author Matthew Tambara
 */
public interface WSRPConsumerManager {

	public String getDisplayName(PortletDescription portletDescription);

	public QName getEventQName(QName qName);

	public WSRP_v2_Markup_PortType getMarkupService() throws Exception;

	public PortletDescription getPortletDescription(String portletHandle);

	public WSRP_v2_PortletManagement_PortType getPortletManagementService();

	public PropertyDescription getPropertyDescription(String name);

	public PropertyDescription[] getPropertyDescriptions();

	public WSRP_v2_Registration_PortType getRegistrationService();

	public ServiceDescription getServiceDescription();

	public String getWsdl();

	public void updateServiceDescription(
			RegistrationContext registrationContext)
		throws Exception;

}