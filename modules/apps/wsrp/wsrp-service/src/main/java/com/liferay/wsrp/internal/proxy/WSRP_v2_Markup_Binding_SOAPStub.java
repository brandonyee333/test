/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.proxy;

import java.net.URL;

import javax.xml.rpc.Service;

import org.apache.axis.AxisFault;

/**
 * @author Michael Young
 */
public class WSRP_v2_Markup_Binding_SOAPStub
	extends oasis.names.tc.wsrp.v2.bind.WSRP_v2_Markup_Binding_SOAPStub
	implements Stub {

	public WSRP_v2_Markup_Binding_SOAPStub() throws AxisFault {
	}

	public WSRP_v2_Markup_Binding_SOAPStub(Service service) throws AxisFault {
		super(service);
	}

	public WSRP_v2_Markup_Binding_SOAPStub(URL endpointURL, Service service)
		throws AxisFault {

		super(endpointURL, service);
	}

}