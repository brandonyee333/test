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

import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetAttachmentLocalServiceClpInvoker {
	public AssetAttachmentLocalServiceClpInvoker() {
		_methodName0 = "addAssetAttachment";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AssetAttachment"
			};

		_methodName1 = "createAssetAttachment";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAssetAttachment";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAssetAttachment";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AssetAttachment"
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

		_methodName9 = "fetchAssetAttachment";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getAssetAttachment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getAssetAttachments";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getAssetAttachmentsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateAssetAttachment";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.AssetAttachment"
			};

		_methodName15 = "updateAssetAttachment";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.AssetAttachment", "boolean"
			};

		_methodName680 = "getBeanIdentifier";

		_methodParameterTypes680 = new String[] {  };

		_methodName681 = "setBeanIdentifier";

		_methodParameterTypes681 = new String[] { "java.lang.String" };

		_methodName686 = "addAssetAttachment";

		_methodParameterTypes686 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"byte[][]"
			};

		_methodName687 = "addAssetAttachment";

		_methodParameterTypes687 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"java.io.File"
			};

		_methodName688 = "addAssetAttachment";

		_methodParameterTypes688 = new String[] {
				"long", "long", "long", "java.lang.String", "int", "int",
				"java.io.File", "byte[][]"
			};

		_methodName689 = "addAssetAttachment";

		_methodParameterTypes689 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String", "int",
				"int", "byte[][]"
			};

		_methodName690 = "addAssetAttachment";

		_methodParameterTypes690 = new String[] {
				"long", "java.lang.String", "long", "java.lang.String", "int",
				"int", "java.io.File"
			};

		_methodName691 = "copyAssetAttachment";

		_methodParameterTypes691 = new String[] { "long" };

		_methodName692 = "copyAssetAttachment";

		_methodParameterTypes692 = new String[] { "long", "long", "long" };

		_methodName693 = "copyAssetAttachment";

		_methodParameterTypes693 = new String[] {
				"long", "java.lang.String", "long"
			};

		_methodName694 = "deleteAssetAttachment";

		_methodParameterTypes694 = new String[] {
				"com.liferay.osb.model.AssetAttachment"
			};

		_methodName695 = "deleteAssetAttachment";

		_methodParameterTypes695 = new String[] { "long" };

		_methodName696 = "deleteAssetAttachments";

		_methodParameterTypes696 = new String[] { "java.util.Date", "long", "long" };

		_methodName697 = "deleteAssetAttachments";

		_methodParameterTypes697 = new String[] { "java.lang.String", "long" };

		_methodName698 = "fetchAssetAttachment";

		_methodParameterTypes698 = new String[] { "long" };

		_methodName699 = "getAssetAttachments";

		_methodParameterTypes699 = new String[] { "java.lang.String", "long" };

		_methodName700 = "getAssetAttachments";

		_methodParameterTypes700 = new String[] {
				"java.lang.String", "long", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName701 = "getAssetAttachmentsCount";

		_methodParameterTypes701 = new String[] { "java.lang.String", "long" };

		_methodName702 = "getAssetAttachmentsCount";

		_methodParameterTypes702 = new String[] {
				"java.lang.String", "long", "int"
			};

		_methodName703 = "updateAssetAttachment";

		_methodParameterTypes703 = new String[] { "long", "byte[][]" };

		_methodName704 = "updateAssetAttachment";

		_methodParameterTypes704 = new String[] { "long", "java.io.File" };

		_methodName705 = "updateAssetAttachment";

		_methodParameterTypes705 = new String[] {
				"long", "java.lang.String", "long", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.addAssetAttachment((com.liferay.osb.model.AssetAttachment)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.createAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.deleteAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.deleteAssetAttachment((com.liferay.osb.model.AssetAttachment)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.fetchAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachments(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachmentsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.updateAssetAttachment((com.liferay.osb.model.AssetAttachment)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.updateAssetAttachment((com.liferay.osb.model.AssetAttachment)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			AssetAttachmentLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.addAssetAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (byte[])arguments[6]);
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.addAssetAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (java.io.File)arguments[6]);
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.addAssetAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (java.io.File)arguments[6],
				(byte[])arguments[7]);
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.addAssetAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (byte[])arguments[6]);
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.addAssetAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(), (java.io.File)arguments[6]);
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.copyAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.copyAssetAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.copyAssetAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.deleteAssetAttachment((com.liferay.osb.model.AssetAttachment)arguments[0]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.deleteAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			AssetAttachmentLocalServiceUtil.deleteAssetAttachments((java.util.Date)arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			AssetAttachmentLocalServiceUtil.deleteAssetAttachments((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.fetchAssetAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachments((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachments((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachmentsCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachmentsCount((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.updateAssetAttachment(((Long)arguments[0]).longValue(),
				(byte[])arguments[1]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.updateAssetAttachment(((Long)arguments[0]).longValue(),
				(java.io.File)arguments[1]);
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return AssetAttachmentLocalServiceUtil.updateAssetAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue());
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
}