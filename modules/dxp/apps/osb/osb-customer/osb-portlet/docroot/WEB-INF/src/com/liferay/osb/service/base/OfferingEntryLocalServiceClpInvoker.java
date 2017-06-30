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

import com.liferay.osb.service.OfferingEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class OfferingEntryLocalServiceClpInvoker {
	public OfferingEntryLocalServiceClpInvoker() {
		_methodName0 = "addOfferingEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.OfferingEntry"
			};

		_methodName1 = "createOfferingEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteOfferingEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteOfferingEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.OfferingEntry"
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

		_methodName9 = "fetchOfferingEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getOfferingEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getOfferingEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getOfferingEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateOfferingEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.OfferingEntry"
			};

		_methodName15 = "updateOfferingEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.OfferingEntry", "boolean"
			};

		_methodName688 = "getBeanIdentifier";

		_methodParameterTypes688 = new String[] {  };

		_methodName689 = "setBeanIdentifier";

		_methodParameterTypes689 = new String[] { "java.lang.String" };

		_methodName694 = "addOfferingEntry";

		_methodParameterTypes694 = new String[] {
				"long", "long", "long", "long", "long", "java.lang.String",
				"int", "int", "boolean", "long", "long", "long", "boolean",
				"long", "int", "int", "int"
			};

		_methodName695 = "checkOfferingEntries";

		_methodParameterTypes695 = new String[] {  };

		_methodName696 = "deleteOfferingEntry";

		_methodParameterTypes696 = new String[] { "long" };

		_methodName697 = "deleteOfferingEntry";

		_methodParameterTypes697 = new String[] {
				"com.liferay.osb.model.OfferingEntry"
			};

		_methodName698 = "fetchOfferingEntry";

		_methodParameterTypes698 = new String[] { "long" };

		_methodName699 = "getAccountEntryOfferingEntries";

		_methodParameterTypes699 = new String[] { "long" };

		_methodName700 = "getAccountEntryOfferingEntriesCount";

		_methodParameterTypes700 = new String[] { "long" };

		_methodName701 = "getOrderEntryOfferingEntries";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "hasActiveTrialOfferingEntry";

		_methodParameterTypes702 = new String[] { "long" };

		_methodName703 = "search";

		_methodParameterTypes703 = new String[] {
				"long", "long", "int[][]", "int[][]", "int", "int", "int", "int",
				"int", "int", "java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName704 = "searchCount";

		_methodParameterTypes704 = new String[] {
				"long", "long", "int[][]", "int[][]", "int", "int", "int", "int",
				"int", "int", "java.util.LinkedHashMap", "boolean"
			};

		_methodName705 = "updateOfferingEntry";

		_methodParameterTypes705 = new String[] {
				"long", "long", "long", "long", "long", "long",
				"java.lang.String", "int", "int", "boolean", "long", "long",
				"long", "boolean", "long", "int", "int"
			};

		_methodName706 = "updateStatus";

		_methodParameterTypes706 = new String[] { "long", "long", "int" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.addOfferingEntry((com.liferay.osb.model.OfferingEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.createOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.deleteOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.deleteOfferingEntry((com.liferay.osb.model.OfferingEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.fetchOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getOfferingEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getOfferingEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.updateOfferingEntry((com.liferay.osb.model.OfferingEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.updateOfferingEntry((com.liferay.osb.model.OfferingEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			OfferingEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.addOfferingEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Boolean)arguments[8]).booleanValue(),
				((Long)arguments[9]).longValue(),
				((Long)arguments[10]).longValue(),
				((Long)arguments[11]).longValue(),
				((Boolean)arguments[12]).booleanValue(),
				((Long)arguments[13]).longValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue());
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			OfferingEntryLocalServiceUtil.checkOfferingEntries();

			return null;
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.deleteOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.deleteOfferingEntry((com.liferay.osb.model.OfferingEntry)arguments[0]);
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.fetchOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntriesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.getOrderEntryOfferingEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.hasActiveTrialOfferingEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (int[])arguments[2],
				(int[])arguments[3], ((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[10],
				((Boolean)arguments[11]).booleanValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[14]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (int[])arguments[2],
				(int[])arguments[3], ((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[10],
				((Boolean)arguments[11]).booleanValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.updateOfferingEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				(java.lang.String)arguments[6],
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Boolean)arguments[9]).booleanValue(),
				((Long)arguments[10]).longValue(),
				((Long)arguments[11]).longValue(),
				((Long)arguments[12]).longValue(),
				((Boolean)arguments[13]).booleanValue(),
				((Long)arguments[14]).longValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue());
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return OfferingEntryLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
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
	private String _methodName688;
	private String[] _methodParameterTypes688;
	private String _methodName689;
	private String[] _methodParameterTypes689;
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
}