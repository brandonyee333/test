/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.bind;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.wsrp.internal.proxy.TypeConvertorUtil;

import java.rmi.RemoteException;

import oasis.names.tc.wsrp.v1.intf.WSRP_v1_ServiceDescription_PortType;
import oasis.names.tc.wsrp.v1.types.GetServiceDescription;
import oasis.names.tc.wsrp.v1.types.ServiceDescription;
import oasis.names.tc.wsrp.v2.intf.WSRP_v2_ServiceDescription_PortType;

/**
 * @author Michael Young
 */
public class V1ServiceDescriptionServiceImpl
	extends BaseServiceImpl implements WSRP_v1_ServiceDescription_PortType {

	@Override
	public ServiceDescription getServiceDescription(
			GetServiceDescription v1GetServiceDescription)
		throws RemoteException {

		try {
			return doGetServiceDescription(v1GetServiceDescription);
		}
		catch (RemoteException re) {
			_log.error(re, re);

			throw re;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	protected ServiceDescription doGetServiceDescription(
			GetServiceDescription v1GetServiceDescription)
		throws Exception {

		oasis.names.tc.wsrp.v2.types.GetServiceDescription
			v2GetServiceDescription =
				(oasis.names.tc.wsrp.v2.types.GetServiceDescription)
					TypeConvertorUtil.convert(v1GetServiceDescription, 1);

		oasis.names.tc.wsrp.v2.types.ServiceDescription v2ServiceDescription =
			_v2ServiceDescriptionService.getServiceDescription(
				v2GetServiceDescription);

		return (ServiceDescription)TypeConvertorUtil.convert(
			v2ServiceDescription, 2);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		V1ServiceDescriptionServiceImpl.class);

	private static final WSRP_v2_ServiceDescription_PortType
		_v2ServiceDescriptionService = new V2ServiceDescriptionServiceImpl();

}