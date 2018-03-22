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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.LicenseEntryLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class LicenseEntryLocalServiceClpInvoker {
	public LicenseEntryLocalServiceClpInvoker() {
		_methodName0 = "addLicenseEntry";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.LicenseEntry"
			};

		_methodName1 = "createLicenseEntry";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteLicenseEntry";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteLicenseEntry";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.LicenseEntry"
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

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchLicenseEntry";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getLicenseEntry";

		_methodParameterTypes11 = new String[] { "long" };

		_methodName12 = "getActionableDynamicQuery";

		_methodParameterTypes12 = new String[] {  };

		_methodName13 = "getIndexableActionableDynamicQuery";

		_methodParameterTypes13 = new String[] {  };

		_methodName15 = "deletePersistedModel";

		_methodParameterTypes15 = new String[] {
				"com.liferay.portal.kernel.model.PersistedModel"
			};

		_methodName16 = "getPersistedModel";

		_methodParameterTypes16 = new String[] { "java.io.Serializable" };

		_methodName17 = "getLicenseEntries";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getLicenseEntriesCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateLicenseEntry";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.LicenseEntry"
			};

		_methodName278 = "getOSGiServiceIdentifier";

		_methodParameterTypes278 = new String[] {  };

		_methodName283 = "addLicenseEntry";

		_methodParameterTypes283 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "int",
				"int"
			};

		_methodName284 = "getLicenseEntries";

		_methodParameterTypes284 = new String[] { "long" };

		_methodName285 = "getLicenseEntries";

		_methodParameterTypes285 = new String[] { "long", "int" };

		_methodName286 = "getLicenseEntry";

		_methodParameterTypes286 = new String[] { "long", "java.lang.String" };

		_methodName287 = "updateLicenseEntry";

		_methodParameterTypes287 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "int",
				"int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.addLicenseEntry((com.liferay.osb.model.LicenseEntry)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.createLicenseEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.deleteLicenseEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.deleteLicenseEntry((com.liferay.osb.model.LicenseEntry)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.fetchLicenseEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getLicenseEntry(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getLicenseEntries(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getLicenseEntriesCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.updateLicenseEntry((com.liferay.osb.model.LicenseEntry)arguments[0]);
		}

		if (_methodName278.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes278, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.addLicenseEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getLicenseEntries(((Long)arguments[0]).longValue());
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getLicenseEntries(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.getLicenseEntry(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return LicenseEntryLocalServiceUtil.updateLicenseEntry(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
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
	private String _methodName278;
	private String[] _methodParameterTypes278;
	private String _methodName283;
	private String[] _methodParameterTypes283;
	private String _methodName284;
	private String[] _methodParameterTypes284;
	private String _methodName285;
	private String[] _methodParameterTypes285;
	private String _methodName286;
	private String[] _methodParameterTypes286;
	private String _methodName287;
	private String[] _methodParameterTypes287;
}