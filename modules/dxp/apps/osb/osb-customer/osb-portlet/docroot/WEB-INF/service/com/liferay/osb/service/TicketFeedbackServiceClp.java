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
public class TicketFeedbackServiceClp implements TicketFeedbackService {
	public TicketFeedbackServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "addTicketFeedback";

		_methodParameterTypes0 = new String[] { "long", "int", "int" };

		_methodName1 = "fetchFirstOpenTicketFeedback";

		_methodParameterTypes1 = new String[] { "long", "long", "int" };

		_methodName2 = "fetchFirstTicketFeedback";

		_methodParameterTypes2 = new String[] { "long", "int" };

		_methodName3 = "getTicketFeedback";

		_methodParameterTypes3 = new String[] { "long" };

		_methodName4 = "updateTicketFeedback";

		_methodParameterTypes4 = new String[] {
				"long", "int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String"
			};

		_methodName5 = "searchCount";

		_methodParameterTypes5 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName6 = "searchCount";

		_methodParameterTypes6 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean"
			};

		_methodName8 = "getOSGiServiceIdentifier";

		_methodParameterTypes8 = new String[] {  };

		_methodName9 = "getTicketFeedbacks";

		_methodParameterTypes9 = new String[] { "long", "int" };

		_methodName10 = "search";

		_methodParameterTypes10 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName11 = "search";

		_methodParameterTypes11 = new String[] {
				"java.lang.String", "int", "int", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "java.lang.Integer",
				"java.lang.String", "java.lang.Integer", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.lang.Integer[][]",
				"java.lang.Integer[][]", "java.util.LinkedHashMap", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};
	}

	@Override
	public com.liferay.osb.model.TicketFeedback addTicketFeedback(
		long ticketEntryId, int subject, int satisfied)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] { ticketEntryId, subject, satisfied });
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

		return (com.liferay.osb.model.TicketFeedback)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { userId, ticketEntryId, subject });
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

		return (com.liferay.osb.model.TicketFeedback)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback fetchFirstTicketFeedback(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] { ticketEntryId, subject });
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

		return (com.liferay.osb.model.TicketFeedback)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback getTicketFeedback(
		long ticketFeedbackId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] { ticketFeedbackId });
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

		return (com.liferay.osb.model.TicketFeedback)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketFeedback updateTicketFeedback(
		long ticketFeedbackId, int satisfied, int answer1, int answer2,
		int answer3, int rating1, int rating2, int rating3, int rating4,
		java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] {
						ticketFeedbackId,
						
					satisfied,
						
					answer1,
						
					answer2,
						
					answer3,
						
					rating1,
						
					rating2,
						
					rating3,
						
					rating4,
						
					ClpSerializer.translateInput(comments)
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

		return (com.liferay.osb.model.TicketFeedback)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(params)
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

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int searchCount(java.lang.String name, int createdGTDay,
		int createdGTMonth, int createdGTYear, int createdLTDay,
		int createdLTMonth, int createdLTYear, int modifiedGTDay,
		int modifiedGTMonth, int modifiedGTYear, int modifiedLTDay,
		int modifiedLTMonth, int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						ClpSerializer.translateInput(name),
						
					createdGTDay,
						
					createdGTMonth,
						
					createdGTYear,
						
					createdLTDay,
						
					createdLTMonth,
						
					createdLTYear,
						
					modifiedGTDay,
						
					modifiedGTMonth,
						
					modifiedGTYear,
						
					modifiedLTDay,
						
					modifiedLTMonth,
						
					modifiedLTYear,
						
					ClpSerializer.translateInput(satisfied),
						
					ClpSerializer.translateInput(comments),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(ratings1),
						
					ClpSerializer.translateInput(ratings2),
						
					ClpSerializer.translateInput(ratings3),
						
					ClpSerializer.translateInput(ratings4),
						
					ClpSerializer.translateInput(params),
						
					andSearch
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

		return ((Integer)returnObj).intValue();
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
			returnObj = _invokableService.invokeMethod(_methodName8,
					_methodParameterTypes8, new Object[] {  });
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
	public java.util.List<com.liferay.osb.model.TicketFeedback> getTicketFeedbacks(
		long ticketEntryId, int subject)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] { ticketEntryId, subject });
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

		return (java.util.List<com.liferay.osb.model.TicketFeedback>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(params),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
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

		return (java.util.List<com.liferay.osb.model.TicketFeedback>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketFeedback> search(
		java.lang.String name, int createdGTDay, int createdGTMonth,
		int createdGTYear, int createdLTDay, int createdLTMonth,
		int createdLTYear, int modifiedGTDay, int modifiedGTMonth,
		int modifiedGTYear, int modifiedLTDay, int modifiedLTMonth,
		int modifiedLTYear, java.lang.Integer satisfied,
		java.lang.String comments, java.lang.Integer status,
		java.lang.Integer[] ratings1, java.lang.Integer[] ratings2,
		java.lang.Integer[] ratings3, java.lang.Integer[] ratings4,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] {
						ClpSerializer.translateInput(name),
						
					createdGTDay,
						
					createdGTMonth,
						
					createdGTYear,
						
					createdLTDay,
						
					createdLTMonth,
						
					createdLTYear,
						
					modifiedGTDay,
						
					modifiedGTMonth,
						
					modifiedGTYear,
						
					modifiedLTDay,
						
					modifiedLTMonth,
						
					modifiedLTYear,
						
					ClpSerializer.translateInput(satisfied),
						
					ClpSerializer.translateInput(comments),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(ratings1),
						
					ClpSerializer.translateInput(ratings2),
						
					ClpSerializer.translateInput(ratings3),
						
					ClpSerializer.translateInput(ratings4),
						
					ClpSerializer.translateInput(params),
						
					andSearch,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
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

		return (java.util.List<com.liferay.osb.model.TicketFeedback>)ClpSerializer.translateOutput(returnObj);
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
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
}