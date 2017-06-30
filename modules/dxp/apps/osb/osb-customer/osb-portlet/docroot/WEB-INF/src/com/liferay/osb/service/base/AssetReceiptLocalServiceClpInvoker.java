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

import com.liferay.osb.service.AssetReceiptLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetReceiptLocalServiceClpInvoker {
	public AssetReceiptLocalServiceClpInvoker() {
		_methodName0 = "addAssetReceipt";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AssetReceipt"
			};

		_methodName1 = "createAssetReceipt";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAssetReceipt";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAssetReceipt";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AssetReceipt"
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

		_methodName9 = "fetchAssetReceipt";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAssetReceipt";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAssetReceipts";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAssetReceiptsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAssetReceipt";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AssetReceipt"
			};

		_methodName15 = "updateAssetReceipt";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AssetReceipt", "boolean"
			};

		_methodName696 = "getBeanIdentifier";

		_methodParameterTypes696 = new String[] {  };

		_methodName697 = "setBeanIdentifier";

		_methodParameterTypes697 = new String[] { "java.lang.String" };

		_methodName702 = "deleteAssetReceipt";

		_methodParameterTypes702 = new String[] {
				"com.liferay.osb.model.AssetReceipt"
			};

		_methodName703 = "deleteAssetReceipt";

		_methodParameterTypes703 = new String[] { "long" };

		_methodName704 = "deleteAssetReceipts";

		_methodParameterTypes704 = new String[] { "java.lang.String", "long" };

		_methodName705 = "fetchAssetReceipt";

		_methodParameterTypes705 = new String[] {
				"java.lang.String", "long", "java.lang.String", "long"
			};

		_methodName706 = "getAssetReceipt";

		_methodParameterTypes706 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName707 = "getAssetReceipt";

		_methodParameterTypes707 = new String[] {
				"java.lang.String", "long", "java.lang.String", "long"
			};

		_methodName708 = "getAssetReceipts";

		_methodParameterTypes708 = new String[] {
				"long", "java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName709 = "getAssetReceiptsCount";

		_methodParameterTypes709 = new String[] {
				"long", "java.util.LinkedHashMap"
			};

		_methodName710 = "hasAssetReceipt";

		_methodParameterTypes710 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName711 = "hasAssetReceipt";

		_methodParameterTypes711 = new String[] {
				"java.lang.String", "long", "java.lang.String", "long"
			};

		_methodName712 = "purchaseAsset";

		_methodParameterTypes712 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String",
				"java.lang.String", "long", "long"
			};

		_methodName713 = "purchaseAssets";

		_methodParameterTypes713 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String",
				"java.lang.String", "long", "long", "long", "long", "int"
			};

		_methodName714 = "updateAssetReceipt";

		_methodParameterTypes714 = new String[] {
				"long", "java.lang.String", "long"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.addAssetReceipt((com.liferay.osb.model.AssetReceipt)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.createAssetReceipt(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.deleteAssetReceipt(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.deleteAssetReceipt((com.liferay.osb.model.AssetReceipt)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.fetchAssetReceipt(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceipt(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceipts(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceiptsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.updateAssetReceipt((com.liferay.osb.model.AssetReceipt)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.updateAssetReceipt((com.liferay.osb.model.AssetReceipt)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			AssetReceiptLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.deleteAssetReceipt((com.liferay.osb.model.AssetReceipt)arguments[0]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.deleteAssetReceipt(((Long)arguments[0]).longValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.deleteAssetReceipts((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.fetchAssetReceipt((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], ((Long)arguments[3]).longValue());
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceipt(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceipt((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], ((Long)arguments[3]).longValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceipts(((Long)arguments[0]).longValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.getAssetReceiptsCount(((Long)arguments[0]).longValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[1]);
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.hasAssetReceipt(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.hasAssetReceipt((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], ((Long)arguments[3]).longValue());
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.purchaseAsset(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue());
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.purchaseAssets(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				((Long)arguments[8]).longValue(),
				((Integer)arguments[9]).intValue());
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return AssetReceiptLocalServiceUtil.updateAssetReceipt(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
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
	private String _methodName696;
	private String[] _methodParameterTypes696;
	private String _methodName697;
	private String[] _methodParameterTypes697;
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
}