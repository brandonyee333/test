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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.InvokableService;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketCallServiceClp implements TicketCallService {
	public TicketCallServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "addTicketCall";

		_methodParameterTypes0 = new String[] {
				"long", "int", "int", "int", "int", "int", "int", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName2 = "getOSGiServiceIdentifier";

		_methodParameterTypes2 = new String[] {  };
	}

	@Override
	public com.liferay.osb.model.TicketCall addTicketCall(long ticketEntryId,
		int type, int callDateMonth, int callDateDay, int callDateYear,
		int callDateHour, int callDateMinute, long callLength,
		java.lang.String customerName, java.lang.String customerContact,
		java.lang.String confirmation, java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] {
						ticketEntryId,
						
					type,
						
					callDateMonth,
						
					callDateDay,
						
					callDateYear,
						
					callDateHour,
						
					callDateMinute,
						
					callLength,
						
					ClpSerializer.translateInput(customerName),
						
					ClpSerializer.translateInput(customerContact),
						
					ClpSerializer.translateInput(confirmation),
						
					ClpSerializer.translateInput(instructions)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TicketCall)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName2,
					_methodParameterTypes2, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	private InvokableService _invokableService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName2;
	private String[] _methodParameterTypes2;
}