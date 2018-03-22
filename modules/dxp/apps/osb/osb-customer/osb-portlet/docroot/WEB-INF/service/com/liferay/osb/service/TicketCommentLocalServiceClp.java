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
public class TicketCommentLocalServiceClp implements TicketCommentLocalService {
	public TicketCommentLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "addAwayMessageTicketComment";

		_methodParameterTypes0 = new String[] { "long", "long" };

		_methodName1 = "addTicketComment";

		_methodParameterTypes1 = new String[] {
				"com.liferay.osb.model.TicketComment"
			};

		_methodName2 = "addTicketComment";

		_methodParameterTypes2 = new String[] {
				"long", "long", "java.lang.String", "int", "int", "int",
				"int[][]", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName3 = "createTicketComment";

		_methodParameterTypes3 = new String[] { "long" };

		_methodName4 = "deleteTicketComment";

		_methodParameterTypes4 = new String[] {
				"com.liferay.osb.model.TicketComment"
			};

		_methodName5 = "deleteTicketComment";

		_methodParameterTypes5 = new String[] { "long" };

		_methodName6 = "deleteTicketComment";

		_methodParameterTypes6 = new String[] {
				"long", "com.liferay.osb.model.TicketComment"
			};

		_methodName7 = "deleteTicketComment";

		_methodParameterTypes7 = new String[] { "long", "long" };

		_methodName8 = "fetchLastTicketComment";

		_methodParameterTypes8 = new String[] {
				"long", "long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName9 = "fetchLastTicketComment";

		_methodParameterTypes9 = new String[] {
				"long", "long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName10 = "fetchTicketComment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getLastTicketComment";

		_methodParameterTypes11 = new String[] {
				"long", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName12 = "getTicketComment";

		_methodParameterTypes12 = new String[] { "long" };

		_methodName13 = "updateTicketComment";

		_methodParameterTypes13 = new String[] {
				"com.liferay.osb.model.TicketComment"
			};

		_methodName14 = "updateTicketComment";

		_methodParameterTypes14 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"int[][]", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName15 = "updateTicketCommentType";

		_methodParameterTypes15 = new String[] { "long", "int" };

		_methodName16 = "getActionableDynamicQuery";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "dynamicQuery";

		_methodParameterTypes17 = new String[] {  };

		_methodName18 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "deletePersistedModel";

		_methodParameterTypes19 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName20 = "getPersistedModel";

		_methodParameterTypes20 = new String[] { "java.io.Serializable" };

		_methodName21 = "getOrganizationTicketCommentsCount";

		_methodParameterTypes21 = new String[] { "long[][]", "long", "int" };

		_methodName22 = "getTicketCommentsCount";

		_methodParameterTypes22 = new String[] {  };

		_methodName23 = "getTicketCommentsCount";

		_methodParameterTypes23 = new String[] { "long", "int[][]", "int[][]" };

		_methodName24 = "getTicketCommentsCount";

		_methodParameterTypes24 = new String[] {
				"long", "long", "int[][]", "int[][]"
			};

		_methodName26 = "getOSGiServiceIdentifier";

		_methodParameterTypes26 = new String[] {  };

		_methodName27 = "dynamicQuery";

		_methodParameterTypes27 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName28 = "dynamicQuery";

		_methodParameterTypes28 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName29 = "dynamicQuery";

		_methodParameterTypes29 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName30 = "getTicketComments";

		_methodParameterTypes30 = new String[] { "int", "int" };

		_methodName31 = "getTicketComments";

		_methodParameterTypes31 = new String[] { "long", "int[][]", "int[][]" };

		_methodName32 = "getTicketComments";

		_methodParameterTypes32 = new String[] {
				"long", "long", "int[][]", "int[][]"
			};

		_methodName33 = "dynamicQueryCount";

		_methodParameterTypes33 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName34 = "dynamicQueryCount";

		_methodParameterTypes34 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName35 = "resetSolutionTicketComment";

		_methodParameterTypes35 = new String[] { "long" };
	}

	@Override
	public com.liferay.osb.model.TicketComment addAwayMessageTicketComment(
		long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] { userId, ticketEntryId });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment addTicketComment(
		com.liferay.osb.model.TicketComment ticketComment) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { ClpSerializer.translateInput(ticketComment) });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment addTicketComment(long userId,
		long ticketEntryId, java.lang.String body, int type, int visibility,
		int status, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(body),
						
					type,
						
					visibility,
						
					status,
						
					ClpSerializer.translateInput(pendingTypes),
						
					ClpSerializer.translateInput(serviceContext)
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment createTicketComment(
		long ticketCommentId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] { ticketCommentId });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		com.liferay.osb.model.TicketComment ticketComment) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] { ClpSerializer.translateInput(ticketComment) });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5, new Object[] { ticketCommentId });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, com.liferay.osb.model.TicketComment ticketComment)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						userId,
						
					ClpSerializer.translateInput(ticketComment)
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment deleteTicketComment(
		long userId, long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] { userId, ticketCommentId });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					visibility,
						
					status,
						
					ClpSerializer.translateInput(obc)
					});
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment fetchLastTicketComment(
		long userId, long ticketEntryId, int visibility, int status, int type,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					visibility,
						
					status,
						
					type,
						
					ClpSerializer.translateInput(obc)
					});
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment fetchTicketComment(
		long ticketCommentId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10, new Object[] { ticketCommentId });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment getLastTicketComment(
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] {
						ticketEntryId,
						
					visibility,
						
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment getTicketComment(
		long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12, new Object[] { ticketCommentId });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketComment(
		com.liferay.osb.model.TicketComment ticketComment) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13,
					new Object[] { ClpSerializer.translateInput(ticketComment) });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14,
					new Object[] {
						userId,
						
					ticketCommentId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(body),
						
					visibility,
						
					status,
						
					ClpSerializer.translateInput(pendingTypes),
						
					ClpSerializer.translateInput(serviceContext)
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketComment updateTicketCommentType(
		long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] { ticketCommentId, type });
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

		return (com.liferay.osb.model.TicketComment)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName16,
					_methodParameterTypes16, new Object[] {  });
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

		return (com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName17,
					_methodParameterTypes17, new Object[] {  });
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

		return (com.liferay.portal.kernel.dao.orm.DynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName18,
					_methodParameterTypes18, new Object[] {  });
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

		return (com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19,
					new Object[] { ClpSerializer.translateInput(persistedModel) });
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

		return (com.liferay.portal.kernel.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName20,
					_methodParameterTypes20,
					new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
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

		return (com.liferay.portal.kernel.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int getOrganizationTicketCommentsCount(long[] organizationIds,
		long ticketEntryId, int visibility) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21,
					new Object[] {
						ClpSerializer.translateInput(organizationIds),
						
					ticketEntryId,
						
					visibility
					});
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

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int getTicketCommentsCount() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22, new Object[] {  });
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

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int getTicketCommentsCount(long ticketEntryId, int[] visibilities,
		int[] statuses) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23,
					new Object[] {
						ticketEntryId,
						
					ClpSerializer.translateInput(visibilities),
						
					ClpSerializer.translateInput(statuses)
					});
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

		return ((Integer)returnObj).intValue();
	}

	@Override
	public int getTicketCommentsCount(long userId, long ticketEntryId,
		int[] visibilities, int[] statuses) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(visibilities),
						
					ClpSerializer.translateInput(statuses)
					});
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
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26, new Object[] {  });
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
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
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

		return (java.util.List<T>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end
					});
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

		return (java.util.List<T>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName29,
					_methodParameterTypes29,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(orderByComparator)
					});
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

		return (java.util.List<T>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName30,
					_methodParameterTypes30, new Object[] { start, end });
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

		return (java.util.List<com.liferay.osb.model.TicketComment>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long ticketEntryId, int[] visibilities, int[] statuses) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName31,
					_methodParameterTypes31,
					new Object[] {
						ticketEntryId,
						
					ClpSerializer.translateInput(visibilities),
						
					ClpSerializer.translateInput(statuses)
					});
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

		return (java.util.List<com.liferay.osb.model.TicketComment>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketComment> getTicketComments(
		long userId, long ticketEntryId, int[] visibilities, int[] statuses) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName32,
					_methodParameterTypes32,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(visibilities),
						
					ClpSerializer.translateInput(statuses)
					});
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

		return (java.util.List<com.liferay.osb.model.TicketComment>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName33,
					_methodParameterTypes33,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
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

		return ((Long)returnObj).longValue();
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName34,
					_methodParameterTypes34,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					ClpSerializer.translateInput(projection)
					});
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

		return ((Long)returnObj).longValue();
	}

	@Override
	public void resetSolutionTicketComment(long ticketEntryId) {
		try {
			_invokableLocalService.invokeMethod(_methodName35,
				_methodParameterTypes35, new Object[] { ticketEntryId });
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
	}

	private InvokableLocalService _invokableLocalService;
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
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
}