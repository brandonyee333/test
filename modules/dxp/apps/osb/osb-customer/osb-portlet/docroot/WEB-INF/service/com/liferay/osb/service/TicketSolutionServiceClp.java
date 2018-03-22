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
public class TicketSolutionServiceClp implements TicketSolutionService {
	public TicketSolutionServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "addTicketSolution";

		_methodParameterTypes0 = new String[] {
				"long", "long", "java.lang.String", "boolean", "int",
				"java.lang.String", "int", "boolean", "boolean", "boolean",
				"boolean", "int", "int", "java.lang.String", "java.util.List",
				"java.util.List", "java.util.List"
			};

		_methodName1 = "updateTicketSolution";

		_methodParameterTypes1 = new String[] {
				"long", "long", "int", "long", "java.lang.String", "int"
			};

		_methodName3 = "getOSGiServiceIdentifier";

		_methodParameterTypes3 = new String[] {  };
	}

	@Override
	public com.liferay.osb.model.TicketSolution addTicketSolution(long userId,
		long ticketEntryId, java.lang.String summary,
		boolean useCustomerSummary, int issueType, java.lang.String solution,
		int type, boolean customerSpecific, boolean environmentSpecific,
		boolean versionSpecific, boolean reviewForKB, int status,
		int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws java.lang.Exception {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(summary),
						
					useCustomerSummary,
						
					issueType,
						
					ClpSerializer.translateInput(solution),
						
					type,
						
					customerSpecific,
						
					environmentSpecific,
						
					versionSpecific,
						
					reviewForKB,
						
					status,
						
					ticketEntrySubcomponent,
						
					ClpSerializer.translateInput(ticketEntrySubcomponentCustom),
						
					ClpSerializer.translateInput(ticketLinkURLs),
						
					ClpSerializer.translateInput(ticketLinkTypes),
						
					ClpSerializer.translateInput(ticketAttachments)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.lang.Exception) {
				throw (java.lang.Exception)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TicketSolution)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketSolution updateTicketSolution(
		long ticketSolutionId, long ticketEntryId, int status,
		long statusByUserId, java.lang.String statusMessage, int statusReason)
		throws java.lang.Exception {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] {
						ticketSolutionId,
						
					ticketEntryId,
						
					status,
						
					statusByUserId,
						
					ClpSerializer.translateInput(statusMessage),
						
					statusReason
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.lang.Exception) {
				throw (java.lang.Exception)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TicketSolution)ClpSerializer.translateOutput(returnObj);
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
			returnObj = _invokableService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] {  });
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
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName3;
	private String[] _methodParameterTypes3;
}