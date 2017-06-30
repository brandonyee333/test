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

import com.liferay.osb.service.AssetListAssetEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetListAssetEntryLocalServiceClpInvoker {
	public AssetListAssetEntryLocalServiceClpInvoker() {
		_methodName0 = "addAssetListAssetEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AssetListAssetEntry"
			};

		_methodName1 = "createAssetListAssetEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAssetListAssetEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAssetListAssetEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AssetListAssetEntry"
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

		_methodName9 = "fetchAssetListAssetEntry";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAssetListAssetEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAssetListAssetEntries";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAssetListAssetEntriesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAssetListAssetEntry";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AssetListAssetEntry"
			};

		_methodName15 = "updateAssetListAssetEntry";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AssetListAssetEntry", "boolean"
			};

		_methodName686 = "getBeanIdentifier";

		_methodParameterTypes686 = new String[] {  };

		_methodName687 = "setBeanIdentifier";

		_methodParameterTypes687 = new String[] { "java.lang.String" };

		_methodName692 = "addAssetListAssetEntry";

		_methodParameterTypes692 = new String[] { "long", "long" };

		_methodName693 = "deleteAssetListAssetEntry";

		_methodParameterTypes693 = new String[] {
				"com.liferay.osb.model.AssetListAssetEntry"
			};

		_methodName694 = "deleteAssetListAssetEntry";

		_methodParameterTypes694 = new String[] { "long" };

		_methodName695 = "deleteAssetListAssetListAssetEntry";

		_methodParameterTypes695 = new String[] { "long" };

		_methodName696 = "getAssetEntries";

		_methodParameterTypes696 = new String[] { "long" };

		_methodName697 = "getAssetListAssetEntries";

		_methodParameterTypes697 = new String[] { "long" };

		_methodName698 = "setAssetListAssetEntries";

		_methodParameterTypes698 = new String[] { "long", "java.util.List" };

		_methodName699 = "setAssetListAssetEntries";

		_methodParameterTypes699 = new String[] { "long", "long[][]" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.addAssetListAssetEntry((com.liferay.osb.model.AssetListAssetEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.createAssetListAssetEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.deleteAssetListAssetEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.deleteAssetListAssetEntry((com.liferay.osb.model.AssetListAssetEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.fetchAssetListAssetEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getAssetListAssetEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getAssetListAssetEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getAssetListAssetEntriesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.updateAssetListAssetEntry((com.liferay.osb.model.AssetListAssetEntry)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.updateAssetListAssetEntry((com.liferay.osb.model.AssetListAssetEntry)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			AssetListAssetEntryLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.addAssetListAssetEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.deleteAssetListAssetEntry((com.liferay.osb.model.AssetListAssetEntry)arguments[0]);
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.deleteAssetListAssetEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			AssetListAssetEntryLocalServiceUtil.deleteAssetListAssetListAssetEntry(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getAssetEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return AssetListAssetEntryLocalServiceUtil.getAssetListAssetEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			AssetListAssetEntryLocalServiceUtil.setAssetListAssetEntries(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.portlet.asset.model.AssetEntry>)arguments[1]);

			return null;
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			AssetListAssetEntryLocalServiceUtil.setAssetListAssetEntries(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
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
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
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
}