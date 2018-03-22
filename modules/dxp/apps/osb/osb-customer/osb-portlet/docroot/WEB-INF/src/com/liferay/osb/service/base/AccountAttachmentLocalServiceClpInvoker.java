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

import com.liferay.osb.service.AccountAttachmentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountAttachmentLocalServiceClpInvoker {
	public AccountAttachmentLocalServiceClpInvoker() {
		_methodName0 = "addAccountAttachment";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AccountAttachment"
			};

		_methodName1 = "createAccountAttachment";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAccountAttachment";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAccountAttachment";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AccountAttachment"
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

		_methodName10 = "fetchAccountAttachment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAccountAttachment";

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

		_methodName17 = "getAccountAttachments";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getAccountAttachmentsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateAccountAttachment";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.AccountAttachment"
			};

		_methodName278 = "getOSGiServiceIdentifier";

		_methodParameterTypes278 = new String[] {  };

		_methodName283 = "addAccountAttachment";

		_methodParameterTypes283 = new String[] {
				"long", "long", "long",
				"com.liferay.portal.kernel.util.ObjectValuePair", "int"
			};

		_methodName284 = "addAccountAttachments";

		_methodParameterTypes284 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List"
			};

		_methodName285 = "deleteAccountAttachment";

		_methodParameterTypes285 = new String[] {
				"com.liferay.osb.model.AccountAttachment"
			};

		_methodName286 = "deleteAccountAttachment";

		_methodParameterTypes286 = new String[] { "long" };

		_methodName287 = "deleteAccountAttachments";

		_methodParameterTypes287 = new String[] { "long", "long" };

		_methodName288 = "getAccountAttachments";

		_methodParameterTypes288 = new String[] { "long" };

		_methodName289 = "getAccountAttachments";

		_methodParameterTypes289 = new String[] { "long", "long" };

		_methodName290 = "getAccountAttachments";

		_methodParameterTypes290 = new String[] { "long", "long", "int" };

		_methodName291 = "getFileAsStream";

		_methodParameterTypes291 = new String[] {
				"com.liferay.osb.model.AccountAttachment"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.addAccountAttachment((com.liferay.osb.model.AccountAttachment)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.createAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.deleteAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.deleteAccountAttachment((com.liferay.osb.model.AccountAttachment)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.fetchAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getAccountAttachments(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getAccountAttachmentsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.updateAccountAttachment((com.liferay.osb.model.AccountAttachment)arguments[0]);
		}

		if (_methodName278.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes278, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.addAccountAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>)arguments[3],
				((Integer)arguments[4]).intValue());
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.addAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4]);
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.deleteAccountAttachment((com.liferay.osb.model.AccountAttachment)arguments[0]);
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.deleteAccountAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			AccountAttachmentLocalServiceUtil.deleteAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getAccountAttachments(((Long)arguments[0]).longValue());
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getAccountAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return AccountAttachmentLocalServiceUtil.getFileAsStream((com.liferay.osb.model.AccountAttachment)arguments[0]);
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
	private String _methodName288;
	private String[] _methodParameterTypes288;
	private String _methodName289;
	private String[] _methodParameterTypes289;
	private String _methodName290;
	private String[] _methodParameterTypes290;
	private String _methodName291;
	private String[] _methodParameterTypes291;
}