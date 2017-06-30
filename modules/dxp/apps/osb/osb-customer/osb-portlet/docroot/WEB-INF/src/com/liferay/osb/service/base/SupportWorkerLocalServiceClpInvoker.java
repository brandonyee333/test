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

package com.liferay.osb.service.base;

import com.liferay.osb.service.SupportWorkerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class SupportWorkerLocalServiceClpInvoker {
	public SupportWorkerLocalServiceClpInvoker() {
		_methodName0 = "addSupportWorker";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName1 = "createSupportWorker";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSupportWorker";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSupportWorker";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchSupportWorker";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getSupportWorker";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getSupportWorkers";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getSupportWorkersCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateSupportWorker";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.SupportWorker"
			};

		_methodName15 = "updateSupportWorker";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.SupportWorker", "boolean"
			};

		_methodName680 = "getBeanIdentifier";

		_methodParameterTypes680 = new String[] {  };

		_methodName681 = "setBeanIdentifier";

		_methodParameterTypes681 = new String[] { "java.lang.String" };

		_methodName686 = "addSupportWorkers";

		_methodParameterTypes686 = new String[] {
				"long[][]", "long", "double[][]", "int[][]", "int[][]",
				"int[][]"
			};

		_methodName687 = "clockInOut";

		_methodParameterTypes687 = new String[] { "long" };

		_methodName688 = "decreaseAssignedWork";

		_methodParameterTypes688 = new String[] { "long", "double" };

		_methodName689 = "decreaseTicketEntryAssignedWork";

		_methodParameterTypes689 = new String[] { "long", "double" };

		_methodName690 = "deleteSupportWorkers";

		_methodParameterTypes690 = new String[] { "long" };

		_methodName691 = "deleteSupportWorkers";

		_methodParameterTypes691 = new String[] { "long[][]", "long" };

		_methodName692 = "getAssignedWork";

		_methodParameterTypes692 = new String[] { "long" };

		_methodName693 = "getAvailableSupportWorker";

		_methodParameterTypes693 = new String[] {
				"com.liferay.osb.model.TicketEntry"
			};

		_methodName694 = "getLongestOpenSupportWorker";

		_methodParameterTypes694 = new String[] {
				"java.util.List", "com.liferay.osb.model.TicketEntry"
			};

		_methodName695 = "getMostAvailableSupportWorker";

		_methodParameterTypes695 = new String[] {
				"com.liferay.osb.model.TicketEntry", "java.util.LinkedHashMap"
			};

		_methodName696 = "getNextOpenSupportWorker";

		_methodParameterTypes696 = new String[] {
				"java.util.List", "com.liferay.osb.model.TicketEntry"
			};

		_methodName697 = "getSupportWorker";

		_methodParameterTypes697 = new String[] { "long", "long" };

		_methodName698 = "getSupportWorkersBySupportLaborId";

		_methodParameterTypes698 = new String[] { "long" };

		_methodName699 = "getSupportWorkersBySupportRegionId";

		_methodParameterTypes699 = new String[] { "long" };

		_methodName700 = "getSupportWorkersCountBySupportLaborId";

		_methodParameterTypes700 = new String[] { "long" };

		_methodName701 = "getTeamSupportWorkers";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "getUserSupportTeamManagers";

		_methodParameterTypes702 = new String[] { "long", "java.lang.Integer" };

		_methodName703 = "getUserSupportWorkers";

		_methodParameterTypes703 = new String[] { "long" };

		_methodName704 = "hasSupportWorker";

		_methodParameterTypes704 = new String[] { "long", "int" };

		_methodName705 = "hasSupportWorker";

		_methodParameterTypes705 = new String[] {
				"long", "int", "long", "java.lang.Integer"
			};

		_methodName706 = "hasSupportWorker";

		_methodParameterTypes706 = new String[] { "long", "long" };

		_methodName707 = "hasSupportWorkerRole";

		_methodParameterTypes707 = new String[] { "long", "int" };

		_methodName708 = "increaseAssignedWork";

		_methodParameterTypes708 = new String[] { "long", "double" };

		_methodName709 = "increaseTicketEntryAssignedWork";

		_methodParameterTypes709 = new String[] { "long", "double" };

		_methodName710 = "isClockedIn";

		_methodParameterTypes710 = new String[] { "long" };

		_methodName711 = "isManagerOfWorker";

		_methodParameterTypes711 = new String[] { "long", "long" };

		_methodName712 = "recalculateUtilization";

		_methodParameterTypes712 = new String[] {  };

		_methodName713 = "search";

		_methodParameterTypes713 = new String[] {
				"java.lang.Boolean", "int", "java.util.LinkedHashMap"
			};

		_methodName714 = "search";

		_methodParameterTypes714 = new String[] {
				"long", "java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName715 = "search";

		_methodParameterTypes715 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName716 = "searchCount";

		_methodParameterTypes716 = new String[] { "long", "java.lang.String" };

		_methodName717 = "searchCount";

		_methodParameterTypes717 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "boolean"
			};

		_methodName718 = "updateSupportWorker";

		_methodParameterTypes718 = new String[] {
				"long", "long", "boolean", "double", "int", "int", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.addSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.createSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.deleteSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.deleteSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.fetchSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorker(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.updateSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.updateSupportWorker((com.liferay.osb.model.SupportWorker)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			SupportWorkerLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			SupportWorkerLocalServiceUtil.addSupportWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue(), (double[])arguments[2],
				(int[])arguments[3], (int[])arguments[4], (int[])arguments[5]);

			return null;
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			SupportWorkerLocalServiceUtil.clockInOut(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			SupportWorkerLocalServiceUtil.decreaseAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			SupportWorkerLocalServiceUtil.decreaseTicketEntryAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			SupportWorkerLocalServiceUtil.deleteSupportWorkers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			SupportWorkerLocalServiceUtil.deleteSupportWorkers((long[])arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getAssignedWork(((Long)arguments[0]).longValue());
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getAvailableSupportWorker((com.liferay.osb.model.TicketEntry)arguments[0]);
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getLongestOpenSupportWorker((java.util.List<com.liferay.osb.model.SupportWorker>)arguments[0],
				(com.liferay.osb.model.TicketEntry)arguments[1]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getMostAvailableSupportWorker((com.liferay.osb.model.TicketEntry)arguments[0],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getNextOpenSupportWorker((java.util.List<com.liferay.osb.model.SupportWorker>)arguments[0],
				(com.liferay.osb.model.TicketEntry)arguments[1]);
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersBySupportLaborId(((Long)arguments[0]).longValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersBySupportRegionId(((Long)arguments[0]).longValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getSupportWorkersCountBySupportLaborId(((Long)arguments[0]).longValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getTeamSupportWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getUserSupportTeamManagers(((Long)arguments[0]).longValue(),
				(java.lang.Integer)arguments[1]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.getUserSupportWorkers(((Long)arguments[0]).longValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorker(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorker(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.Integer)arguments[3]);
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.hasSupportWorkerRole(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			SupportWorkerLocalServiceUtil.increaseAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			SupportWorkerLocalServiceUtil.increaseTicketEntryAssignedWork(((Long)arguments[0]).longValue(),
				((Double)arguments[1]).doubleValue());

			return null;
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.isClockedIn(((Long)arguments[0]).longValue());
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.isManagerOfWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			SupportWorkerLocalServiceUtil.recalculateUtilization();

			return null;
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.search((java.lang.Boolean)arguments[0],
				((Integer)arguments[1]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[2]);
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10]);
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return SupportWorkerLocalServiceUtil.updateSupportWorker(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue(),
				((Double)arguments[3]).doubleValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
		}

		throw new UnsupportedOperationException();
	}

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
	private String _methodName680;
	private String[] _methodParameterTypes680;
	private String _methodName681;
	private String[] _methodParameterTypes681;
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
	private String _methodName688;
	private String[] _methodParameterTypes688;
	private String _methodName689;
	private String[] _methodParameterTypes689;
	private String _methodName690;
	private String[] _methodParameterTypes690;
	private String _methodName691;
	private String[] _methodParameterTypes691;
	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
	private String _methodName694;
	private String[] _methodParameterTypes694;
	private String _methodName695;
	private String[] _methodParameterTypes695;
	private String _methodName696;
	private String[] _methodParameterTypes696;
	private String _methodName697;
	private String[] _methodParameterTypes697;
	private String _methodName698;
	private String[] _methodParameterTypes698;
	private String _methodName699;
	private String[] _methodParameterTypes699;
	private String _methodName700;
	private String[] _methodParameterTypes700;
	private String _methodName701;
	private String[] _methodParameterTypes701;
	private String _methodName702;
	private String[] _methodParameterTypes702;
	private String _methodName703;
	private String[] _methodParameterTypes703;
	private String _methodName704;
	private String[] _methodParameterTypes704;
	private String _methodName705;
	private String[] _methodParameterTypes705;
	private String _methodName706;
	private String[] _methodParameterTypes706;
	private String _methodName707;
	private String[] _methodParameterTypes707;
	private String _methodName708;
	private String[] _methodParameterTypes708;
	private String _methodName709;
	private String[] _methodParameterTypes709;
	private String _methodName710;
	private String[] _methodParameterTypes710;
	private String _methodName711;
	private String[] _methodParameterTypes711;
	private String _methodName712;
	private String[] _methodParameterTypes712;
	private String _methodName713;
	private String[] _methodParameterTypes713;
	private String _methodName714;
	private String[] _methodParameterTypes714;
	private String _methodName715;
	private String[] _methodParameterTypes715;
	private String _methodName716;
	private String[] _methodParameterTypes716;
	private String _methodName717;
	private String[] _methodParameterTypes717;
	private String _methodName718;
	private String[] _methodParameterTypes718;
}