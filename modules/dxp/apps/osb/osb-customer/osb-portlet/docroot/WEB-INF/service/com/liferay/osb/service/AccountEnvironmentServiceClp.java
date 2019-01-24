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
public class AccountEnvironmentServiceClp implements AccountEnvironmentService {
	public AccountEnvironmentServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "addAccountEnvironment";

		_methodParameterTypes0 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.util.List", "java.util.List"
			};

		_methodName1 = "deleteAccountEnvironment";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "getAccountEnvironment";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "updateAccountEnvironment";

		_methodParameterTypes3 = new String[] {
				"long", "long", "java.lang.String", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.util.List", "java.util.List"
			};

		_methodName5 = "getOSGiServiceIdentifier";

		_methodParameterTypes5 = new String[] {  };

		_methodName6 = "getAccountEnvironments";

		_methodParameterTypes6 = new String[] { "long" };

		_methodName7 = "getAccountEnvironmentsMap";

		_methodParameterTypes7 = new String[] { "long" };
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		long accountEntryId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] {
						accountEntryId,
						
					productEntryId,
						
					ClpSerializer.translateInput(name),
						
					envOS,
						
					ClpSerializer.translateInput(envOSCustom),
						
					envDB,
						
					envJVM,
						
					envAS,
						
					envLFR,
						
					envCommerce,
						
					envBrowser,
						
					envCS,
						
					ClpSerializer.translateInput(envSearch),
						
					ClpSerializer.translateInput(files),
						
					ClpSerializer.translateInput(types)
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

		return (com.liferay.osb.model.AccountEnvironment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { accountEnvironmentId });
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

		return (com.liferay.osb.model.AccountEnvironment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment getAccountEnvironment(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] { accountEnvironmentId });
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

		return (com.liferay.osb.model.AccountEnvironment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		long accountEnvironmentId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] {
						accountEnvironmentId,
						
					productEntryId,
						
					ClpSerializer.translateInput(name),
						
					envOS,
						
					ClpSerializer.translateInput(envOSCustom),
						
					envDB,
						
					envJVM,
						
					envAS,
						
					envLFR,
						
					envCommerce,
						
					envBrowser,
						
					envCS,
						
					ClpSerializer.translateInput(envSearch),
						
					ClpSerializer.translateInput(files),
						
					ClpSerializer.translateInput(types)
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

		return (com.liferay.osb.model.AccountEnvironment)ClpSerializer.translateOutput(returnObj);
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
			returnObj = _invokableService.invokeMethod(_methodName5,
					_methodParameterTypes5, new Object[] {  });
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
	public java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName6,
					_methodParameterTypes6, new Object[] { accountEntryId });
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

		return (java.util.List<com.liferay.osb.model.AccountEnvironment>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>> getAccountEnvironmentsMap(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName7,
					_methodParameterTypes7, new Object[] { accountEntryId });
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

		return (java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>>)ClpSerializer.translateOutput(returnObj);
	}

	private InvokableService _invokableService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
}