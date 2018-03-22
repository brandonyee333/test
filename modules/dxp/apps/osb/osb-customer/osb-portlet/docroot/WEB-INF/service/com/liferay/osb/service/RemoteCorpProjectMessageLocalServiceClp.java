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

import com.liferay.portal.kernel.service.InvokableLocalService;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RemoteCorpProjectMessageLocalServiceClp
	implements RemoteCorpProjectMessageLocalService {
	public RemoteCorpProjectMessageLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "addCorpProjectMessage";

		_methodParameterTypes0 = new String[] {
				"long", "long", "int", "int", "java.lang.String",
				"java.lang.String", "boolean", "boolean", "boolean"
			};

		_methodName2 = "getOSGiServiceIdentifier";

		_methodParameterTypes2 = new String[] {  };

		_methodName3 = "deleteCorpProjectMessage";

		_methodParameterTypes3 = new String[] { "long" };
	}

	@Override
	public com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		long userId, long corpProjectId, int type, int severityLevel,
		java.lang.String title, java.lang.String content, boolean displayCP,
		boolean displayLCS, boolean displayLESA)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] {
						userId,
						
					corpProjectId,
						
					type,
						
					severityLevel,
						
					ClpSerializer.translateInput(title),
						
					ClpSerializer.translateInput(content),
						
					displayCP,
						
					displayLCS,
						
					displayLESA
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

		return (com.liferay.osb.model.CorpProjectMessage)ClpSerializer.translateOutput(returnObj);
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
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
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

	@Override
	public void deleteCorpProjectMessage(long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName3,
				_methodParameterTypes3, new Object[] { corpProjectMessageId });
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
	}

	private InvokableLocalService _invokableLocalService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
}