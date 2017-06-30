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

import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetReceiptLicenseLocalServiceClpInvoker {
	public AssetReceiptLicenseLocalServiceClpInvoker() {
		_methodName0 = "addAssetReceiptLicense";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AssetReceiptLicense"
			};

		_methodName1 = "createAssetReceiptLicense";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAssetReceiptLicense";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAssetReceiptLicense";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AssetReceiptLicense"
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

		_methodName9 = "fetchAssetReceiptLicense";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAssetReceiptLicense";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAssetReceiptLicenses";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAssetReceiptLicensesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAssetReceiptLicense";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AssetReceiptLicense"
			};

		_methodName15 = "updateAssetReceiptLicense";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AssetReceiptLicense", "boolean"
			};

		_methodName680 = "getBeanIdentifier";

		_methodParameterTypes680 = new String[] {  };

		_methodName681 = "setBeanIdentifier";

		_methodParameterTypes681 = new String[] { "java.lang.String" };

		_methodName686 = "addAssetReceiptLicense";

		_methodParameterTypes686 = new String[] {
				"long", "long", "long", "long", "long", "long", "long", "long",
				"java.lang.String", "java.util.Date", "java.util.Date"
			};

		_methodName687 = "deleteAssetReceiptLicense";

		_methodParameterTypes687 = new String[] {
				"com.liferay.osb.model.AssetReceiptLicense"
			};

		_methodName688 = "deleteAssetReceiptLicense";

		_methodParameterTypes688 = new String[] { "long" };

		_methodName689 = "getActiveAssetReceiptLicenses";

		_methodParameterTypes689 = new String[] { "long", "int", "int", "int" };

		_methodName690 = "getActiveAssetReceiptLicensesCount";

		_methodParameterTypes690 = new String[] { "long" };

		_methodName691 = "getActiveAssetReceiptLicensesCount";

		_methodParameterTypes691 = new String[] { "long", "int" };

		_methodName692 = "getActiveAssetReceiptLicenseTypeAllotment";

		_methodParameterTypes692 = new String[] { "long", "int" };

		_methodName693 = "getAssetReceiptLicense";

		_methodParameterTypes693 = new String[] { "java.lang.String" };

		_methodName694 = "getAssetReceiptLicenses";

		_methodParameterTypes694 = new String[] { "long" };

		_methodName695 = "getAssetReceiptLicenses";

		_methodParameterTypes695 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName696 = "getAssetReceiptLicenses";

		_methodParameterTypes696 = new String[] {
				"java.lang.String", "long", "int", "int"
			};

		_methodName697 = "getAssetReceiptLicenses";

		_methodParameterTypes697 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName698 = "getAssetReceiptLicenses";

		_methodParameterTypes698 = new String[] {
				"java.lang.String", "long", "java.lang.String", "int", "int"
			};

		_methodName699 = "getAssetReceiptLicenses";

		_methodParameterTypes699 = new String[] {
				"java.lang.String", "long", "java.lang.String", "int", "long",
				"int", "int"
			};

		_methodName700 = "getAssetReceiptLicensesCount";

		_methodParameterTypes700 = new String[] { "long" };

		_methodName701 = "getAssetReceiptLicensesCount";

		_methodParameterTypes701 = new String[] {
				"long", "long", "java.lang.String"
			};

		_methodName702 = "getAssetReceiptLicensesCount";

		_methodParameterTypes702 = new String[] { "java.lang.String", "long" };

		_methodName703 = "getAssetReceiptLicensesCount";

		_methodParameterTypes703 = new String[] {
				"java.lang.String", "long", "java.lang.String"
			};

		_methodName704 = "getAssetReceiptLicensesCount";

		_methodParameterTypes704 = new String[] {
				"java.lang.String", "long", "java.lang.String", "int", "long"
			};

		_methodName705 = "getRenewedAssetReceiptLicenses";

		_methodParameterTypes705 = new String[] { "long", "int", "int", "int" };

		_methodName706 = "getRenewedAssetReceiptLicensesCount";

		_methodParameterTypes706 = new String[] { "long", "int" };

		_methodName707 = "getRenewedAssetReceiptLicenseTypeAllotment";

		_methodParameterTypes707 = new String[] { "long", "int" };

		_methodName708 = "hasAssetReceiptLicense";

		_methodParameterTypes708 = new String[] { "long" };

		_methodName709 = "updateAssetReceiptLicenses";

		_methodParameterTypes709 = new String[] {
				"long", "int", "java.util.Date", "java.util.Date"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.addAssetReceiptLicense((com.liferay.osb.model.AssetReceiptLicense)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.createAssetReceiptLicense(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.deleteAssetReceiptLicense(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.deleteAssetReceiptLicense((com.liferay.osb.model.AssetReceiptLicense)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.fetchAssetReceiptLicense(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.updateAssetReceiptLicense((com.liferay.osb.model.AssetReceiptLicense)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.updateAssetReceiptLicense((com.liferay.osb.model.AssetReceiptLicense)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			AssetReceiptLicenseLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.addAssetReceiptLicense(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				(java.lang.String)arguments[8], (java.util.Date)arguments[9],
				(java.util.Date)arguments[10]);
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.deleteAssetReceiptLicense((com.liferay.osb.model.AssetReceiptLicense)arguments[0]);
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.deleteAssetReceiptLicense(((Long)arguments[0]).longValue());
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenses(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicensesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicensesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getActiveAssetReceiptLicenseTypeAllotment(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense((java.lang.String)arguments[0]);
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(((Long)arguments[0]).longValue());
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount(((Long)arguments[0]).longValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.lang.String)arguments[2]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicensesCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getRenewedAssetReceiptLicenses(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getRenewedAssetReceiptLicensesCount(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.getRenewedAssetReceiptLicenseTypeAllotment(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.hasAssetReceiptLicense(((Long)arguments[0]).longValue());
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return AssetReceiptLicenseLocalServiceUtil.updateAssetReceiptLicenses(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.util.Date)arguments[2], (java.util.Date)arguments[3]);
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
}