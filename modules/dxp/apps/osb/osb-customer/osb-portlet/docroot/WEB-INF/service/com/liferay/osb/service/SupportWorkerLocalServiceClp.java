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
public class SupportWorkerLocalServiceClp implements SupportWorkerLocalService {
	public SupportWorkerLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "hasSupportWorker";

		_methodParameterTypes0 = new String[] { "long", "int" };

		_methodName1 = "hasSupportWorker";

		_methodParameterTypes1 = new String[] {
				"long", "int", "long", "java.lang.Integer"
			};

		_methodName2 = "hasSupportWorker";

		_methodParameterTypes2 = new String[] { "long", "long" };

		_methodName3 = "hasSupportWorkerRole";

		_methodParameterTypes3 = new String[] { "long", "int" };

		_methodName4 = "isClockedIn";

		_methodParameterTypes4 = new String[] { "long" };

		_methodName5 = "isManagerOfWorker";

		_methodParameterTypes5 = new String[] { "long", "long" };

		_methodName6 = "addSupportWorker";

		_methodParameterTypes6 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName7 = "createSupportWorker";

		_methodParameterTypes7 = new String[] { "long" };

		_methodName8 = "deleteSupportWorker";

		_methodParameterTypes8 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName9 = "deleteSupportWorker";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "fetchSupportWorker";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAvailableSupportWorker";

		_methodParameterTypes11 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName12 = "getLongestOpenSupportWorker";

		_methodParameterTypes12 = new String[] {
				"java.util.List", "com.liferay.osb.model.TicketEntry"
			};

		_methodName13 = "getMostAvailableSupportWorker";

		_methodParameterTypes13 = new String[] {
				"com.liferay.osb.model.TicketEntry", "java.util.LinkedHashMap"
			};

		_methodName14 = "getNextOpenSupportWorker";

		_methodParameterTypes14 = new String[] {
				"java.util.List", "com.liferay.osb.model.TicketEntry"
			};

		_methodName15 = "getSupportWorker";

		_methodParameterTypes15 = new String[] { "long" };

		_methodName16 = "getSupportWorker";

		_methodParameterTypes16 = new String[] { "long", "long" };

		_methodName17 = "updateSupportWorker";

		_methodParameterTypes17 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName18 = "updateSupportWorker";

		_methodParameterTypes18 = new String[] {
				"long", "long", "boolean", "double", "int", "int", "int"
			};

		_methodName19 = "getActionableDynamicQuery";

		_methodParameterTypes19 = new String[] {  };

		_methodName20 = "dynamicQuery";

		_methodParameterTypes20 = new String[] {  };

		_methodName21 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes21 = new String[] {  };

		_methodName22 = "deletePersistedModel";

		_methodParameterTypes22 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName23 = "getPersistedModel";

		_methodParameterTypes23 = new String[] { "java.io.Serializable" };

		_methodName24 = "getAssignedWork";

		_methodParameterTypes24 = new String[] { "long" };

		_methodName25 = "getSupportWorkersCount";

		_methodParameterTypes25 = new String[] {  };

		_methodName26 = "getSupportWorkersCountBySupportLaborId";

		_methodParameterTypes26 = new String[] { "long" };

		_methodName27 = "searchCount";

		_methodParameterTypes27 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName28 = "searchCount";

		_methodParameterTypes28 = new String[] { "long", "java.lang.String" };

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

		_methodName34 = "getSupportWorkers";

		_methodParameterTypes34 = new String[] { "int", "int" };

		_methodName35 = "getSupportWorkersBySupportLaborId";

		_methodParameterTypes35 = new String[] { "long" };

		_methodName36 = "getSupportWorkersBySupportRegionId";

		_methodParameterTypes36 = new String[] { "long" };

		_methodName37 = "getTeamSupportWorkers";

		_methodParameterTypes37 = new String[] { "long" };

		_methodName38 = "getUserSupportTeamManagers";

		_methodParameterTypes38 = new String[] { "long", "java.lang.Integer" };

		_methodName39 = "getUserSupportWorkers";

		_methodParameterTypes39 = new String[] { "long" };

		_methodName40 = "search";

		_methodParameterTypes40 = new String[] {
				"java.lang.Boolean", "int", "java.util.LinkedHashMap"
			};

		_methodName41 = "search";

		_methodParameterTypes41 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName42 = "search";

		_methodParameterTypes42 = new String[] {
				"long", "java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName43 = "dynamicQueryCount";

		_methodParameterTypes43 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName44 = "dynamicQueryCount";

		_methodParameterTypes44 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName45 = "addSupportWorkers";

		_methodParameterTypes45 = new String[] {
				"long[][]", "long", "double[][]", "int[][]", "int[][]",
				"int[][]"
			};

		_methodName46 = "clockInOut";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "decreaseAssignedWork";

		_methodParameterTypes47 = new String[] { "long", "double" };

		_methodName48 = "decreaseTicketEntryAssignedWork";

		_methodParameterTypes48 = new String[] { "long", "double" };

		_methodName49 = "deleteSupportWorkers";

		_methodParameterTypes49 = new String[] { "long" };

		_methodName50 = "deleteSupportWorkers";

		_methodParameterTypes50 = new String[] { "long[][]", "long" };

		_methodName51 = "increaseAssignedWork";

		_methodParameterTypes51 = new String[] { "long", "double" };

		_methodName52 = "increaseTicketEntryAssignedWork";

		_methodParameterTypes52 = new String[] { "long", "double" };

		_methodName53 = "recalculateUtilization";

		_methodParameterTypes53 = new String[] {  };
	}

	@Override
	public boolean hasSupportWorker(long userId, int notRole) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0, new Object[] { userId, notRole });
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

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean hasSupportWorker(long userId, int role,
		long locationSupportRegionId, java.lang.Integer supportTeamType) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1,
					new Object[] {
						userId,
						
					role,
						
					locationSupportRegionId,
						
					ClpSerializer.translateInput(supportTeamType)
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

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean hasSupportWorker(long userId, long supportTeamId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2,
					new Object[] { userId, supportTeamId });
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

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean hasSupportWorkerRole(long userId, int role) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3, new Object[] { userId, role });
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

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean isClockedIn(long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4, new Object[] { userId });
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

		return ((Boolean)returnObj).booleanValue();
	}

	@Override
	public boolean isManagerOfWorker(long userId, long workerUserId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] { userId, workerUserId });
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
	public com.liferay.osb.model.SupportWorker addSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] { ClpSerializer.translateInput(supportWorker) });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker createSupportWorker(
		long supportWorkerId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7, new Object[] { supportWorkerId });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker deleteSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] { ClpSerializer.translateInput(supportWorker) });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker deleteSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9, new Object[] { supportWorkerId });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker fetchSupportWorker(
		long supportWorkerId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10, new Object[] { supportWorkerId });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getLongestOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12,
					new Object[] {
						ClpSerializer.translateInput(supportWorkers),
						
					ClpSerializer.translateInput(ticketEntry)
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getMostAvailableSupportWorker(
		com.liferay.osb.model.TicketEntry ticketEntry,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13,
					new Object[] {
						ClpSerializer.translateInput(ticketEntry),
						
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getNextOpenSupportWorker(
		java.util.List<com.liferay.osb.model.SupportWorker> supportWorkers,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14,
					new Object[] {
						ClpSerializer.translateInput(supportWorkers),
						
					ClpSerializer.translateInput(ticketEntry)
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getSupportWorker(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15, new Object[] { supportWorkerId });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker getSupportWorker(long userId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName16,
					_methodParameterTypes16,
					new Object[] { userId, supportTeamId });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		com.liferay.osb.model.SupportWorker supportWorker) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName17,
					_methodParameterTypes17,
					new Object[] { ClpSerializer.translateInput(supportWorker) });
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.osb.model.SupportWorker updateSupportWorker(
		long supportWorkerId, long supportTeamId, boolean autoAssign,
		double maxWork, int escalationlevel, int escalationLevel2Role,
		int notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName18,
					_methodParameterTypes18,
					new Object[] {
						supportWorkerId,
						
					supportTeamId,
						
					autoAssign,
						
					maxWork,
						
					escalationlevel,
						
					escalationLevel2Role,
						
					notifications
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

		return (com.liferay.osb.model.SupportWorker)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19, new Object[] {  });
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
			returnObj = _invokableLocalService.invokeMethod(_methodName20,
					_methodParameterTypes20, new Object[] {  });
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
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21, new Object[] {  });
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
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22,
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
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23,
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
	public double getAssignedWork(long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24, new Object[] { userId });
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

		return ((Double)returnObj).doubleValue();
	}

	@Override
	public int getSupportWorkersCount() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName25,
					_methodParameterTypes25, new Object[] {  });
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
	public int getSupportWorkersCountBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26, new Object[] { supportLaborId });
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
	public int searchCount(long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] {
						supportLaborId,
						
					ClpSerializer.translateInput(firstName),
						
					ClpSerializer.translateInput(middleName),
						
					ClpSerializer.translateInput(lastName),
						
					ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(emailAddress),
						
					ClpSerializer.translateInput(supportTeamName),
						
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
	public int searchCount(long supportLaborId, java.lang.String keywords) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28,
					new Object[] {
						supportLaborId,
						
					ClpSerializer.translateInput(keywords)
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
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkers(
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName35,
					_methodParameterTypes35, new Object[] { supportLaborId });
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getSupportWorkersBySupportRegionId(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName36,
					_methodParameterTypes36, new Object[] { supportRegionId });
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getTeamSupportWorkers(
		long supportTeamId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName37,
					_methodParameterTypes37, new Object[] { supportTeamId });
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportTeamManagers(
		long userId, java.lang.Integer supportTeamType)
		throws com.liferay.portal.kernel.exception.PortalException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName38,
					_methodParameterTypes38,
					new Object[] {
						userId,
						
					ClpSerializer.translateInput(supportTeamType)
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> getUserSupportWorkers(
		long userId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName39,
					_methodParameterTypes39, new Object[] { userId });
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		java.lang.Boolean overUtilization, int escalationLevel,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName40,
					_methodParameterTypes40,
					new Object[] {
						ClpSerializer.translateInput(overUtilization),
						
					escalationLevel,
						
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String supportTeamName, boolean andSearch, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName41,
					_methodParameterTypes41,
					new Object[] {
						supportLaborId,
						
					ClpSerializer.translateInput(firstName),
						
					ClpSerializer.translateInput(middleName),
						
					ClpSerializer.translateInput(lastName),
						
					ClpSerializer.translateInput(screenName),
						
					ClpSerializer.translateInput(emailAddress),
						
					ClpSerializer.translateInput(supportTeamName),
						
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportWorker> search(
		long supportLaborId, java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName42,
					_methodParameterTypes42,
					new Object[] {
						supportLaborId,
						
					ClpSerializer.translateInput(keywords),
						
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

		return (java.util.List<com.liferay.osb.model.SupportWorker>)ClpSerializer.translateOutput(returnObj);
	}

	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName43,
					_methodParameterTypes43,
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
			returnObj = _invokableLocalService.invokeMethod(_methodName44,
					_methodParameterTypes44,
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
	public void addSupportWorkers(long[] userIds, long supportTeamId,
		double[] maxWork, int[] escalationLevels, int[] roles,
		int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName45,
				_methodParameterTypes45,
				new Object[] {
					ClpSerializer.translateInput(userIds),
					
				supportTeamId,
					
				ClpSerializer.translateInput(maxWork),
					
				ClpSerializer.translateInput(escalationLevels),
					
				ClpSerializer.translateInput(roles),
					
				ClpSerializer.translateInput(notifications)
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
	public void clockInOut(long supportWorkerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName46,
				_methodParameterTypes46, new Object[] { supportWorkerId });
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
	public void decreaseAssignedWork(long userId, double work) {
		try {
			_invokableLocalService.invokeMethod(_methodName47,
				_methodParameterTypes47, new Object[] { userId, work });
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

	@Override
	public void decreaseTicketEntryAssignedWork(long ticketEntryId, double work) {
		try {
			_invokableLocalService.invokeMethod(_methodName48,
				_methodParameterTypes48, new Object[] { ticketEntryId, work });
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

	@Override
	public void deleteSupportWorkers(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName49,
				_methodParameterTypes49, new Object[] { userId });
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
	public void deleteSupportWorkers(long[] userIds, long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			_invokableLocalService.invokeMethod(_methodName50,
				_methodParameterTypes50,
				new Object[] {
					ClpSerializer.translateInput(userIds),
					
				supportTeamId
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
	public void increaseAssignedWork(long userId, double work) {
		try {
			_invokableLocalService.invokeMethod(_methodName51,
				_methodParameterTypes51, new Object[] { userId, work });
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

	@Override
	public void increaseTicketEntryAssignedWork(long ticketEntryId, double work) {
		try {
			_invokableLocalService.invokeMethod(_methodName52,
				_methodParameterTypes52, new Object[] { ticketEntryId, work });
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

	@Override
	public void recalculateUtilization() {
		try {
			_invokableLocalService.invokeMethod(_methodName53,
				_methodParameterTypes53, new Object[] {  });
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
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
}