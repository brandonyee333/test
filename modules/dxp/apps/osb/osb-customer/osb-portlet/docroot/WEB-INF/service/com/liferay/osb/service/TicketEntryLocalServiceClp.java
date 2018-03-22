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
public class TicketEntryLocalServiceClp implements TicketEntryLocalService {
	public TicketEntryLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "hasParticipant";

		_methodParameterTypes0 = new String[] { "long", "long" };

		_methodName1 = "addTicketEntry";

		_methodParameterTypes1 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName2 = "addTicketEntry";

		_methodParameterTypes2 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName3 = "createTicketEntry";

		_methodParameterTypes3 = new String[] { "long" };

		_methodName4 = "deleteTicketEntry";

		_methodParameterTypes4 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName5 = "deleteTicketEntry";

		_methodParameterTypes5 = new String[] { "long" };

		_methodName6 = "fetchTicketEntry";

		_methodParameterTypes6 = new String[] { "long" };

		_methodName7 = "forwardTicketEntry";

		_methodParameterTypes7 = new String[] { "long", "long", "java.lang.String" };

		_methodName8 = "getTicketEntry";

		_methodParameterTypes8 = new String[] { "long", "long" };

		_methodName9 = "getTicketEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "updateCustomerModifiedDate";

		_methodParameterTypes10 = new String[] { "long", "long", "java.util.Date" };

		_methodName11 = "updatePendingTypes";

		_methodParameterTypes11 = new String[] { "long", "long", "int[][]" };

		_methodName12 = "updateTicketEntry";

		_methodParameterTypes12 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName13 = "updateTicketEntry";

		_methodParameterTypes13 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName14 = "updateTicketEntry";

		_methodParameterTypes14 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.util.Map", "int[][]", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName15 = "updateWorkerModifiedDate";

		_methodParameterTypes15 = new String[] { "long", "java.util.Date" };

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

		_methodName21 = "search";

		_methodParameterTypes21 = new String[] {
				"long", "long", "long", "java.lang.String",
				"java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName22 = "search";

		_methodParameterTypes22 = new String[] {
				"long", "long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName23 = "getTicketEntries";

		_methodParameterTypes23 = new String[] {
				"com.liferay.portal.kernel.search.Hits"
			};

		_methodName24 = "getTicketEntriesCount";

		_methodParameterTypes24 = new String[] {  };

		_methodName25 = "getTicketEntriesCount";

		_methodParameterTypes25 = new String[] { "java.util.Date" };

		_methodName26 = "getValidTicketEntriesCount";

		_methodParameterTypes26 = new String[] { "long" };

		_methodName27 = "searchCount";

		_methodParameterTypes27 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap"
			};

		_methodName28 = "searchCount";

		_methodParameterTypes28 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName30 = "getOSGiServiceIdentifier";

		_methodParameterTypes30 = new String[] {  };

		_methodName31 = "dynamicQuery";

		_methodParameterTypes31 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName32 = "dynamicQuery";

		_methodParameterTypes32 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName33 = "dynamicQuery";

		_methodParameterTypes33 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName34 = "getTicketEntries";

		_methodParameterTypes34 = new String[] { "int", "int" };

		_methodName35 = "getTicketEntries";

		_methodParameterTypes35 = new String[] { "java.util.Date", "int", "int" };

		_methodName36 = "getTicketFeedbackTicketEntries";

		_methodParameterTypes36 = new String[] {
				"long", "int", "int", "int", "int"
			};

		_methodName37 = "search";

		_methodParameterTypes37 = new String[] {
				"java.lang.String", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName38 = "search";

		_methodParameterTypes38 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName39 = "dynamicQueryCount";

		_methodParameterTypes39 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName40 = "dynamicQueryCount";

		_methodParameterTypes40 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName41 = "checkInactiveTicketEntries";

		_methodParameterTypes41 = new String[] {  };

		_methodName42 = "checkOnHoldTicketEntries";

		_methodParameterTypes42 = new String[] {  };

		_methodName43 = "deleteTicketEntries";

		_methodParameterTypes43 = new String[] { "long" };

		_methodName44 = "escalateTicketEntry";

		_methodParameterTypes44 = new String[] { "long", "long" };

		_methodName45 = "reindexTicketEntry";

		_methodParameterTypes45 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName46 = "reindexTicketEntry";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "sendEmail";

		_methodParameterTypes47 = new String[] {
				"long", "com.liferay.osb.model.TicketEntry",
				"com.liferay.osb.model.TicketComment", "java.lang.String"
			};

		_methodName48 = "sendEmail";

		_methodParameterTypes48 = new String[] {
				"long", "long", "com.liferay.osb.model.TicketComment",
				"java.lang.String"
			};

		_methodName49 = "syncToJIRA";

		_methodParameterTypes49 = new String[] { "long" };
	}

	@Override
	public boolean hasParticipant(long userId, long ticketEntryId)
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

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public com.liferay.osb.model.TicketEntry addTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] { ClpSerializer.translateInput(ticketEntry) });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry addTicketEntry(long userId,
		long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] {
						userId,
						
					offeringEntryId,
						
					supportRegionId,
						
					ClpSerializer.translateInput(languageId),
						
					ticketId,
						
					ClpSerializer.translateInput(subject),
						
					ClpSerializer.translateInput(description),
						
					systemStatus,
						
					status,
						
					weight,
						
					escalationLevel,
						
					component,
						
					subcomponent,
						
					ClpSerializer.translateInput(ticketInformationFieldsMap),
						
					ClpSerializer.translateInput(ticketAttachments)
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry createTicketEntry(
		long ticketEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] { ticketEntryId });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry deleteTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] { ClpSerializer.translateInput(ticketEntry) });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry deleteTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5, new Object[] { ticketEntryId });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry fetchTicketEntry(
		long ticketEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6, new Object[] { ticketEntryId });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry forwardTicketEntry(long userId,
		long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(commentBody)
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] { accountEntryId, ticketId });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry getTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9, new Object[] { ticketEntryId });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateCustomerModifiedDate(
		long userId, long ticketEntryId, java.util.Date customerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(customerModifiedDate)
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updatePendingTypes(long userId,
		long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					ClpSerializer.translateInput(pendingTypes)
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12,
					new Object[] { ClpSerializer.translateInput(ticketEntry) });
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long assigneeUserId, long supportRegionId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					assigneeUserId,
						
					supportRegionId,
						
					dueDateMonth,
						
					dueDateDay,
						
					dueDateYear,
						
					dueDateHour,
						
					dueDateMinute
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long reportedByUserId, long offeringEntryId,
		long supportRegionId, java.lang.String languageId,
		java.lang.String subject, java.lang.String description,
		java.lang.String reproductionSteps, int severity, int status,
		int weight, int escalationLevel, int component, int subcomponent,
		java.lang.String subcomponentCustom, int resolution, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean ignoreDueDate,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14,
					new Object[] {
						userId,
						
					ticketEntryId,
						
					reportedByUserId,
						
					offeringEntryId,
						
					supportRegionId,
						
					ClpSerializer.translateInput(languageId),
						
					ClpSerializer.translateInput(subject),
						
					ClpSerializer.translateInput(description),
						
					ClpSerializer.translateInput(reproductionSteps),
						
					severity,
						
					status,
						
					weight,
						
					escalationLevel,
						
					component,
						
					subcomponent,
						
					ClpSerializer.translateInput(subcomponentCustom),
						
					resolution,
						
					dueDateMonth,
						
					dueDateDay,
						
					dueDateYear,
						
					dueDateHour,
						
					dueDateMinute,
						
					ignoreDueDate,
						
					ClpSerializer.translateInput(ticketInformationFieldsMap),
						
					ClpSerializer.translateInput(pendingTypes),
						
					ClpSerializer.translateInput(ticketAttachments),
						
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateWorkerModifiedDate(
		long ticketEntryId, java.util.Date workerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] {
						ticketEntryId,
						
					ClpSerializer.translateInput(workerModifiedDate)
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

		return (com.liferay.osb.model.TicketEntry)ClpSerializer.translateOutput(returnObj);
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
	public com.liferay.portal.kernel.search.Hits search(long searchUserId,
		long reportedByUserId, long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21,
					new Object[] {
						searchUserId,
						
					reportedByUserId,
						
					accountEntryId,
						
					ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(params),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(sorts)
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

		return (com.liferay.portal.kernel.search.Hits)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long searchUserId,
		long reportedByUserId, long accountEntryId, java.lang.String name,
		int[] accountEntryTiers, java.lang.Boolean satisfiedDueDate,
		java.util.Date createDateGT, java.util.Date createDateLT,
		java.lang.String content, int[] status, int[] severity,
		int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolution,
		java.util.Date closedDateGT, java.util.Date closedDateLT,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22,
					new Object[] {
						searchUserId,
						
					reportedByUserId,
						
					accountEntryId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(accountEntryTiers),
						
					ClpSerializer.translateInput(satisfiedDueDate),
						
					ClpSerializer.translateInput(createDateGT),
						
					ClpSerializer.translateInput(createDateLT),
						
					ClpSerializer.translateInput(content),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(severity),
						
					ClpSerializer.translateInput(escalationLevel),
						
					ClpSerializer.translateInput(envOS),
						
					ClpSerializer.translateInput(envDB),
						
					ClpSerializer.translateInput(envJVM),
						
					ClpSerializer.translateInput(envAS),
						
					ClpSerializer.translateInput(envLFR),
						
					ClpSerializer.translateInput(components),
						
					ClpSerializer.translateInput(resolution),
						
					ClpSerializer.translateInput(closedDateGT),
						
					ClpSerializer.translateInput(closedDateLT),
						
					ClpSerializer.translateInput(dueDateGT),
						
					ClpSerializer.translateInput(dueDateLT),
						
					ClpSerializer.translateInput(params),
						
					andSearch,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(sorts)
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

		return (com.liferay.portal.kernel.search.Hits)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.util.Tuple getTicketEntries(
		com.liferay.portal.kernel.search.Hits hits)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23,
					new Object[] { ClpSerializer.translateInput(hits) });
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

		return (com.liferay.portal.kernel.util.Tuple)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int getTicketEntriesCount() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24, new Object[] {  });
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
	public int getTicketEntriesCount(java.util.Date modifiedDate) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName25,
					_methodParameterTypes25,
					new Object[] { ClpSerializer.translateInput(modifiedDate) });
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
	public int getValidTicketEntriesCount(long offeringEntryId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26, new Object[] { offeringEntryId });
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
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(params)
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
	public int searchCount(long reportedByUserId, java.lang.String name,
		int[] accountEntryTiers, java.lang.Boolean satisfiedDueDate,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.String subject, java.lang.String description,
		java.lang.String body, int[] status, int[] severity, int[] weights,
		int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolution,
		int closedDateGTDay, int closedDateGTMonth, int closedDateGTYear,
		int closedDateLTDay, int closedDateLTMonth, int closedDateLTYear,
		int dueDateGTDay, int dueDateGTMonth, int dueDateGTYear,
		int dueDateLTDay, int dueDateLTMonth, int dueDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28,
					new Object[] {
						reportedByUserId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(accountEntryTiers),
						
					ClpSerializer.translateInput(satisfiedDueDate),
						
					createDateGTDay,
						
					createDateGTMonth,
						
					createDateGTYear,
						
					createDateLTDay,
						
					createDateLTMonth,
						
					createDateLTYear,
						
					ClpSerializer.translateInput(subject),
						
					ClpSerializer.translateInput(description),
						
					ClpSerializer.translateInput(body),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(severity),
						
					ClpSerializer.translateInput(weights),
						
					ClpSerializer.translateInput(escalationLevel),
						
					ClpSerializer.translateInput(envOS),
						
					ClpSerializer.translateInput(envDB),
						
					ClpSerializer.translateInput(envJVM),
						
					ClpSerializer.translateInput(envAS),
						
					ClpSerializer.translateInput(envLFR),
						
					ClpSerializer.translateInput(components),
						
					ClpSerializer.translateInput(resolution),
						
					closedDateGTDay,
						
					closedDateGTMonth,
						
					closedDateGTYear,
						
					closedDateLTDay,
						
					closedDateLTMonth,
						
					closedDateLTYear,
						
					dueDateGTDay,
						
					dueDateGTMonth,
						
					dueDateGTYear,
						
					dueDateLTDay,
						
					dueDateLTMonth,
						
					dueDateLTYear,
						
					ClpSerializer.translateInput(params),
						
					andSearch
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
			returnObj = _invokableLocalService.invokeMethod(_methodName30,
					_methodParameterTypes30, new Object[] {  });
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
			returnObj = _invokableLocalService.invokeMethod(_methodName31,
					_methodParameterTypes31,
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
			returnObj = _invokableLocalService.invokeMethod(_methodName32,
					_methodParameterTypes32,
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
			returnObj = _invokableLocalService.invokeMethod(_methodName33,
					_methodParameterTypes33,
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
	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName34,
					_methodParameterTypes34, new Object[] { start, end });
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

		return (java.util.List<com.liferay.osb.model.TicketEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		java.util.Date modifiedDate, int start, int end) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName35,
					_methodParameterTypes35,
					new Object[] {
						ClpSerializer.translateInput(modifiedDate),
						
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

		return (java.util.List<com.liferay.osb.model.TicketEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketFeedbackTicketEntries(
		long userId, int createdGTDay, int createdGTMonth, int createdGTYear,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName36,
					_methodParameterTypes36,
					new Object[] {
						userId,
						
					createdGTDay,
						
					createdGTMonth,
						
					createdGTYear,
						
					status
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

		return (java.util.List<com.liferay.osb.model.TicketEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName37,
					_methodParameterTypes37,
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

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TicketEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		long reportedByUserId, java.lang.String name, int[] accountEntryTiers,
		java.lang.Boolean satisfiedDueDate, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear, java.lang.String subject,
		java.lang.String description, java.lang.String body, int[] status,
		int[] severity, int[] weights, int[] escalationLevel, long[] envOS,
		long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolution, int closedDateGTDay,
		int closedDateGTMonth, int closedDateGTYear, int closedDateLTDay,
		int closedDateLTMonth, int closedDateLTYear, int dueDateGTDay,
		int dueDateGTMonth, int dueDateGTYear, int dueDateLTDay,
		int dueDateLTMonth, int dueDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName38,
					_methodParameterTypes38,
					new Object[] {
						reportedByUserId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(accountEntryTiers),
						
					ClpSerializer.translateInput(satisfiedDueDate),
						
					createDateGTDay,
						
					createDateGTMonth,
						
					createDateGTYear,
						
					createDateLTDay,
						
					createDateLTMonth,
						
					createDateLTYear,
						
					ClpSerializer.translateInput(subject),
						
					ClpSerializer.translateInput(description),
						
					ClpSerializer.translateInput(body),
						
					ClpSerializer.translateInput(status),
						
					ClpSerializer.translateInput(severity),
						
					ClpSerializer.translateInput(weights),
						
					ClpSerializer.translateInput(escalationLevel),
						
					ClpSerializer.translateInput(envOS),
						
					ClpSerializer.translateInput(envDB),
						
					ClpSerializer.translateInput(envJVM),
						
					ClpSerializer.translateInput(envAS),
						
					ClpSerializer.translateInput(envLFR),
						
					ClpSerializer.translateInput(components),
						
					ClpSerializer.translateInput(resolution),
						
					closedDateGTDay,
						
					closedDateGTMonth,
						
					closedDateGTYear,
						
					closedDateLTDay,
						
					closedDateLTMonth,
						
					closedDateLTYear,
						
					dueDateGTDay,
						
					dueDateGTMonth,
						
					dueDateGTYear,
						
					dueDateLTDay,
						
					dueDateLTMonth,
						
					dueDateLTYear,
						
					ClpSerializer.translateInput(params),
						
					andSearch,
						
					start,
						
					end,
						
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

		return (java.util.List<com.liferay.osb.model.TicketEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName39,
					_methodParameterTypes39,
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
			returnObj = _invokableLocalService.invokeMethod(_methodName40,
					_methodParameterTypes40,
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
	public void checkInactiveTicketEntries() throws java.lang.Exception {
		try {
			_invokableLocalService.invokeMethod(_methodName41,
				_methodParameterTypes41, new Object[] {  });
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
	}

	@Override
	public void checkOnHoldTicketEntries() throws java.lang.Exception {
		try {
			_invokableLocalService.invokeMethod(_methodName42,
				_methodParameterTypes42, new Object[] {  });
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
	}

	@Override
	public void deleteTicketEntries(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName43,
				_methodParameterTypes43, new Object[] { accountEntryId });
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

	@Override
	public void escalateTicketEntry(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName44,
				_methodParameterTypes44, new Object[] { userId, ticketEntryId });
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

	@Override
	public void reindexTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName45,
				_methodParameterTypes45,
				new Object[] { ClpSerializer.translateInput(ticketEntry) });
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

	@Override
	public void reindexTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName46,
				_methodParameterTypes46, new Object[] { ticketEntryId });
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

	@Override
	public void sendEmail(long userId,
		com.liferay.osb.model.TicketEntry ticketEntry,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName47,
				_methodParameterTypes47,
				new Object[] {
					userId,
					
				ClpSerializer.translateInput(ticketEntry),
					
				ClpSerializer.translateInput(ticketComment),
					
				ClpSerializer.translateInput(action)
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
	}

	@Override
	public void sendEmail(long userId, long ticketEntryId,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName48,
				_methodParameterTypes48,
				new Object[] {
					userId,
					
				ticketEntryId,
					
				ClpSerializer.translateInput(ticketComment),
					
				ClpSerializer.translateInput(action)
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
	}

	@Override
	public void syncToJIRA(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName49,
				_methodParameterTypes49, new Object[] { ticketEntryId });
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
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
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
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
}