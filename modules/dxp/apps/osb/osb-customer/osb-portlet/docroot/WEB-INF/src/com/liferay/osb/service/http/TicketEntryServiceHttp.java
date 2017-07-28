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

package com.liferay.osb.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.TicketEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link TicketEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryServiceSoap
 * @see HttpPrincipal
 * @see TicketEntryServiceUtil
 * @generated
 */
@ProviderType
public class TicketEntryServiceHttp {
	public static com.liferay.osb.model.TicketEntry addTicketEntry(
		HttpPrincipal httpPrincipal, long userId, long offeringEntryId,
		long supportRegionId, java.lang.String languageId, long ticketId,
		java.lang.String subject, java.lang.String description,
		int systemStatus, int status, int weight, int escalationLevel,
		int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"addTicketEntry", _addTicketEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					offeringEntryId, supportRegionId, languageId, ticketId,
					subject, description, systemStatus, status, weight,
					escalationLevel, component, subcomponent,
					ticketInformationFieldsMap, ticketAttachments);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void closeTicketEntry(HttpPrincipal httpPrincipal,
		long ticketEntryId, int resolution, java.lang.String body)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"closeTicketEntry", _closeTicketEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketEntryId, resolution, body);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void escalateTicketEntry(HttpPrincipal httpPrincipal,
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"escalateTicketEntry", _escalateTicketEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketEntryId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketEntry forwardTicketEntry(
		HttpPrincipal httpPrincipal, long ticketEntryId,
		java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"forwardTicketEntry", _forwardTicketEntryParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketEntryId, commentBody);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		HttpPrincipal httpPrincipal, long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"getTicketEntries", _getTicketEntriesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.model.TicketEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getTicketEntriesCount(HttpPrincipal httpPrincipal,
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"getTicketEntriesCount",
					_getTicketEntriesCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		HttpPrincipal httpPrincipal, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"getTicketEntry", _getTicketEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		HttpPrincipal httpPrincipal, long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"getTicketEntry", _getTicketEntryParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId, ticketId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.Hits search(
		HttpPrincipal httpPrincipal, long reportedByUserId,
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
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"search", _searchParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					reportedByUserId, accountEntryId, name, accountEntryTier,
					satisfiedDueDate, createDateGT, createDateLT, content,
					status, severity, escalationLevel, envOS, envDB, envJVM,
					envAS, envLFR, components, resolution, closedDateGT,
					closedDateLT, dueDateGT, dueDateLT, params, andSearch,
					start, end, sorts);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.search.Hits)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.search.Hits search(
		HttpPrincipal httpPrincipal, long reportedByUserId,
		long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"search", _searchParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					reportedByUserId, accountEntryId, keywords, params, start,
					end, sorts);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.portal.kernel.search.Hits)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
		HttpPrincipal httpPrincipal, long reportedByUserId,
		java.lang.String name, int[] accountEntryTier,
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
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"search", _searchParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					reportedByUserId, name, accountEntryTier, satisfiedDueDate,
					createDateGTDay, createDateGTMonth, createDateGTYear,
					createDateLTDay, createDateLTMonth, createDateLTYear,
					subject, description, body, status, severity, weights,
					escalationLevel, envOS, envDB, envJVM, envAS, envLFR,
					components, resolution, closedDateGTDay, closedDateGTMonth,
					closedDateGTYear, closedDateLTDay, closedDateLTMonth,
					closedDateLTYear, dueDateGTDay, dueDateGTMonth,
					dueDateGTYear, dueDateLTDay, dueDateLTMonth, dueDateLTYear,
					params, andSearch, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.model.TicketEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
		HttpPrincipal httpPrincipal, java.lang.String keywords, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"search", _searchParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					keywords, start, end, obc);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.osb.model.TicketEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal,
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
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"searchCount", _searchCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					reportedByUserId, name, accountEntryTier, satisfiedDueDate,
					createDateGTDay, createDateGTMonth, createDateGTYear,
					createDateLTDay, createDateLTMonth, createDateLTYear,
					subject, description, body, status, severity, weights,
					escalationLevel, envOS, envDB, envJVM, envAS, envLFR,
					components, resolution, closedDateGTDay, closedDateGTMonth,
					closedDateGTYear, closedDateLTDay, closedDateLTMonth,
					closedDateLTYear, dueDateGTDay, dueDateGTMonth,
					dueDateGTYear, dueDateLTDay, dueDateLTMonth, dueDateLTYear,
					params, andSearch);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int searchCount(HttpPrincipal httpPrincipal,
		java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"searchCount", _searchCountParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketEntry updatePendingTypes(
		HttpPrincipal httpPrincipal, long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"updatePendingTypes", _updatePendingTypesParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketEntryId, pendingTypes);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		HttpPrincipal httpPrincipal, long userId, long ticketEntryId,
		long assigneeUserId, long supportRegionId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"updateTicketEntry", _updateTicketEntryParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					ticketEntryId, assigneeUserId, supportRegionId,
					dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
					dueDateMinute);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		HttpPrincipal httpPrincipal, long userId, long ticketEntryId,
		long reportedByUserId, long offeringEntryId, long supportRegionId,
		java.lang.String languageId, java.lang.String subject,
		java.lang.String description, java.lang.String reproductionSteps,
		int severity, int status, int weight, int escalationLevel,
		int component, int subcomponent, java.lang.String subcomponentCustom,
		int resolution, int dueDateMonth, int dueDateDay, int dueDateYear,
		int dueDateHour, int dueDateMinute, boolean ignoreDueDate,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketEntryServiceUtil.class,
					"updateTicketEntry", _updateTicketEntryParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					ticketEntryId, reportedByUserId, offeringEntryId,
					supportRegionId, languageId, subject, description,
					reproductionSteps, severity, status, weight,
					escalationLevel, component, subcomponent,
					subcomponentCustom, resolution, dueDateMonth, dueDateDay,
					dueDateYear, dueDateHour, dueDateMinute, ignoreDueDate,
					ticketInformationFieldsMap, pendingTypes,
					ticketAttachments, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketEntryServiceHttp.class);
	private static final Class<?>[] _addTicketEntryParameterTypes0 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			long.class, java.lang.String.class, java.lang.String.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			java.util.Map.class, java.util.List.class
		};
	private static final Class<?>[] _closeTicketEntryParameterTypes1 = new Class[] {
			long.class, int.class, java.lang.String.class
		};
	private static final Class<?>[] _escalateTicketEntryParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _forwardTicketEntryParameterTypes3 = new Class[] {
			long.class, java.lang.String.class
		};
	private static final Class<?>[] _getTicketEntriesParameterTypes4 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getTicketEntriesCountParameterTypes5 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getTicketEntryParameterTypes6 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getTicketEntryParameterTypes7 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _searchParameterTypes8 = new Class[] {
			long.class, long.class, java.lang.String.class, int[].class,
			java.lang.Boolean.class, java.util.Date.class, java.util.Date.class,
			java.lang.String.class, int[].class, int[].class, int[].class,
			long[].class, long[].class, long[].class, long[].class, long[].class,
			int[].class, int[].class, java.util.Date.class, java.util.Date.class,
			java.util.Date.class, java.util.Date.class,
			java.util.LinkedHashMap.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.search.Sort[].class
		};
	private static final Class<?>[] _searchParameterTypes9 = new Class[] {
			long.class, long.class, java.lang.String.class,
			java.util.LinkedHashMap.class, int.class, int.class,
			com.liferay.portal.kernel.search.Sort[].class
		};
	private static final Class<?>[] _searchParameterTypes10 = new Class[] {
			long.class, java.lang.String.class, int[].class,
			java.lang.Boolean.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int[].class, int[].class, int[].class,
			int[].class, long[].class, long[].class, long[].class, long[].class,
			long[].class, int[].class, int[].class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, int.class, int.class,
			java.util.LinkedHashMap.class, boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchParameterTypes11 = new Class[] {
			java.lang.String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _searchCountParameterTypes12 = new Class[] {
			long.class, java.lang.String.class, int[].class,
			java.lang.Boolean.class, int.class, int.class, int.class, int.class,
			int.class, int.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, int[].class, int[].class, int[].class,
			int[].class, long[].class, long[].class, long[].class, long[].class,
			long[].class, int[].class, int[].class, int.class, int.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, int.class, int.class, int.class,
			java.util.LinkedHashMap.class, boolean.class
		};
	private static final Class<?>[] _searchCountParameterTypes13 = new Class[] {
			java.lang.String.class
		};
	private static final Class<?>[] _updatePendingTypesParameterTypes14 = new Class[] {
			long.class, int[].class
		};
	private static final Class<?>[] _updateTicketEntryParameterTypes15 = new Class[] {
			long.class, long.class, long.class, long.class, int.class, int.class,
			int.class, int.class, int.class
		};
	private static final Class<?>[] _updateTicketEntryParameterTypes16 = new Class[] {
			long.class, long.class, long.class, long.class, long.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class, int.class, int.class,
			int.class, int.class, int.class, int.class, java.lang.String.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			boolean.class, java.util.Map.class, int[].class,
			java.util.List.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}