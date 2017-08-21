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
public class TicketEntryServiceClp implements TicketEntryService {
	public TicketEntryServiceClp(InvokableService invokableService) {
		_invokableService = invokableService;

		_methodName0 = "addTicketEntry";

		_methodParameterTypes0 = new String[] {
				"long", "long", "long", "java.lang.String", "long",
				"java.lang.String", "java.lang.String", "int", "int", "int",
				"int", "int", "int", "java.util.Map", "java.util.List"
			};

		_methodName1 = "forwardTicketEntry";

		_methodParameterTypes1 = new String[] { "long", "java.lang.String" };

		_methodName2 = "getTicketEntry";

		_methodParameterTypes2 = new String[] { "long", "long" };

		_methodName3 = "getTicketEntry";

		_methodParameterTypes3 = new String[] { "long" };

		_methodName4 = "updatePendingTypes";

		_methodParameterTypes4 = new String[] { "long", "int[][]" };

		_methodName5 = "updateTicketEntry";

		_methodParameterTypes5 = new String[] {
				"long", "long", "long", "long", "int", "int", "int", "int",
				"int"
			};

		_methodName6 = "updateTicketEntry";

		_methodParameterTypes6 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.util.Map", "int[][]", "java.util.List",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName7 = "search";

		_methodParameterTypes7 = new String[] {
				"long", "long", "java.lang.String", "java.util.LinkedHashMap",
				"int", "int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName8 = "search";

		_methodParameterTypes8 = new String[] {
				"long", "long", "java.lang.String", "int[][]",
				"java.lang.Boolean", "java.util.Date", "java.util.Date",
				"java.lang.String", "int[][]", "int[][]", "int[][]", "long[][]",
				"long[][]", "long[][]", "long[][]", "long[][]", "int[][]",
				"int[][]", "java.util.Date", "java.util.Date", "java.util.Date",
				"java.util.Date", "java.util.LinkedHashMap", "boolean", "int",
				"int", "com.liferay.portal.kernel.search.Sort[][]"
			};

		_methodName9 = "searchCount";

		_methodParameterTypes9 = new String[] { "java.lang.String" };

		_methodName10 = "searchCount";

		_methodParameterTypes10 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName12 = "getOSGiServiceIdentifier";

		_methodParameterTypes12 = new String[] {  };

		_methodName13 = "search";

		_methodParameterTypes13 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName14 = "search";

		_methodParameterTypes14 = new String[] {
				"long", "java.lang.String", "int[][]", "java.lang.Boolean",
				"int", "int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String", "int[][]", "int[][]",
				"int[][]", "int[][]", "long[][]", "long[][]", "long[][]",
				"long[][]", "long[][]", "int[][]", "int[][]", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int", "int",
				"int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName15 = "closeTicketEntry";

		_methodParameterTypes15 = new String[] { "long", "int", "java.lang.String" };

		_methodName16 = "escalateTicketEntry";

		_methodParameterTypes16 = new String[] { "long" };
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
			returnObj = _invokableService.invokeMethod(_methodName0,
					_methodParameterTypes0,
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
	public com.liferay.osb.model.TicketEntry forwardTicketEntry(
		long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] {
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
			returnObj = _invokableService.invokeMethod(_methodName2,
					_methodParameterTypes2,
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
			returnObj = _invokableService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] { ticketEntryId });
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
	public com.liferay.osb.model.TicketEntry updatePendingTypes(
		long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName4,
					_methodParameterTypes4,
					new Object[] {
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
	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long assigneeUserId, long supportRegionId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName5,
					_methodParameterTypes5,
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
			returnObj = _invokableService.invokeMethod(_methodName6,
					_methodParameterTypes6,
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
	public com.liferay.portal.kernel.search.Hits search(long reportedByUserId,
		long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] {
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

		return (com.liferay.portal.kernel.search.Hits)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long reportedByUserId,
		long accountEntryId, java.lang.String name, int[] accountEntryTier,
		java.lang.Boolean satisfiedDueDate, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.String content, int[] status,
		int[] severity, int[] escalationLevel, long[] envOS, long[] envDB,
		long[] envJVM, long[] envAS, long[] envLFR, int[] components,
		int[] resolution, java.util.Date closedDateGT,
		java.util.Date closedDateLT, java.util.Date dueDateGT,
		java.util.Date dueDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] {
						reportedByUserId,
						
					accountEntryId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(accountEntryTier),
						
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

		return (com.liferay.portal.kernel.search.Hits)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName9,
					_methodParameterTypes9,
					new Object[] { ClpSerializer.translateInput(keywords) });
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
	public int searchCount(long reportedByUserId, java.lang.String name,
		int[] accountEntryTier, java.lang.Boolean satisfiedDueDate,
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
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName10,
					_methodParameterTypes10,
					new Object[] {
						reportedByUserId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(accountEntryTier),
						
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
			returnObj = _invokableService.invokeMethod(_methodName12,
					_methodParameterTypes12, new Object[] {  });
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
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName13,
					_methodParameterTypes13,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
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

		return (java.util.List<com.liferay.osb.model.TicketEntry>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		long reportedByUserId, java.lang.String name, int[] accountEntryTier,
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
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableService.invokeMethod(_methodName14,
					_methodParameterTypes14,
					new Object[] {
						reportedByUserId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(accountEntryTier),
						
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
	public void closeTicketEntry(long ticketEntryId, int resolution,
		java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableService.invokeMethod(_methodName15,
				_methodParameterTypes15,
				new Object[] {
					ticketEntryId,
					
				resolution,
					
				ClpSerializer.translateInput(body)
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
	public void escalateTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableService.invokeMethod(_methodName16,
				_methodParameterTypes16, new Object[] { ticketEntryId });
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
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
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
}