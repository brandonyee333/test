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

import com.liferay.osb.service.AccountEnvironmentAttachmentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountEnvironmentAttachmentLocalServiceClpInvoker {
	public AccountEnvironmentAttachmentLocalServiceClpInvoker() {
		_methodName0 = "addAccountEnvironmentAttachment";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.AccountEnvironmentAttachment"
			};

		_methodName1 = "createAccountEnvironmentAttachment";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteAccountEnvironmentAttachment";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteAccountEnvironmentAttachment";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.AccountEnvironmentAttachment"
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

		_methodName10 = "fetchAccountEnvironmentAttachment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getAccountEnvironmentAttachment";

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

		_methodName17 = "getAccountEnvironmentAttachments";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getAccountEnvironmentAttachmentsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateAccountEnvironmentAttachment";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.AccountEnvironmentAttachment"
			};

		_methodName162 = "getOSGiServiceIdentifier";

		_methodParameterTypes162 = new String[] {  };

		_methodName167 = "addAccountEnvironmentAttachment";

		_methodParameterTypes167 = new String[] {
				"long", "long", "com.liferay.portal.kernel.util.ObjectValuePair",
				"int"
			};

		_methodName168 = "addAccountEnvironmentAttachments";

		_methodParameterTypes168 = new String[] {
				"long", "long", "java.util.List", "java.util.List"
			};

		_methodName169 = "deleteAccountEnvironmentAttachment";

		_methodParameterTypes169 = new String[] {
				"com.liferay.osb.model.AccountEnvironmentAttachment"
			};

		_methodName170 = "fetchAccountEnvironmentAttachment";

		_methodParameterTypes170 = new String[] { "long", "int" };

		_methodName171 = "getAccountEnvironmentAttachments";

		_methodParameterTypes171 = new String[] { "long" };

		_methodName172 = "getFile";

		_methodParameterTypes172 = new String[] {
				"com.liferay.osb.model.AccountEnvironmentAttachment"
			};

		_methodName173 = "getFileAsStream";

		_methodParameterTypes173 = new String[] {
				"com.liferay.osb.model.AccountEnvironmentAttachment"
			};

		_methodName174 = "updateAccountEnvironmentAttachment";

		_methodParameterTypes174 = new String[] {
				"long", "long", "com.liferay.portal.kernel.util.ObjectValuePair",
				"int"
			};

		_methodName175 = "updateAccountEnvironmentAttachments";

		_methodParameterTypes175 = new String[] {
				"long", "long", "java.util.List", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.addAccountEnvironmentAttachment((com.liferay.osb.model.AccountEnvironmentAttachment)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.createAccountEnvironmentAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.deleteAccountEnvironmentAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.deleteAccountEnvironmentAttachment((com.liferay.osb.model.AccountEnvironmentAttachment)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getAccountEnvironmentAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getAccountEnvironmentAttachments(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getAccountEnvironmentAttachmentsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.updateAccountEnvironmentAttachment((com.liferay.osb.model.AccountEnvironmentAttachment)arguments[0]);
		}

		if (_methodName162.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes162, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName167.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes167, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.addAccountEnvironmentAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName168.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes168, parameterTypes)) {
			AccountEnvironmentAttachmentLocalServiceUtil.addAccountEnvironmentAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3]);

			return null;
		}

		if (_methodName169.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes169, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.deleteAccountEnvironmentAttachment((com.liferay.osb.model.AccountEnvironmentAttachment)arguments[0]);
		}

		if (_methodName170.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes170, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName171.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes171, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getAccountEnvironmentAttachments(((Long)arguments[0]).longValue());
		}

		if (_methodName172.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes172, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getFile((com.liferay.osb.model.AccountEnvironmentAttachment)arguments[0]);
		}

		if (_methodName173.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes173, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.getFileAsStream((com.liferay.osb.model.AccountEnvironmentAttachment)arguments[0]);
		}

		if (_methodName174.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes174, parameterTypes)) {
			return AccountEnvironmentAttachmentLocalServiceUtil.updateAccountEnvironmentAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>)arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName175.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes175, parameterTypes)) {
			AccountEnvironmentAttachmentLocalServiceUtil.updateAccountEnvironmentAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3]);

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
	private String _methodName162;
	private String[] _methodParameterTypes162;
	private String _methodName167;
	private String[] _methodParameterTypes167;
	private String _methodName168;
	private String[] _methodParameterTypes168;
	private String _methodName169;
	private String[] _methodParameterTypes169;
	private String _methodName170;
	private String[] _methodParameterTypes170;
	private String _methodName171;
	private String[] _methodParameterTypes171;
	private String _methodName172;
	private String[] _methodParameterTypes172;
	private String _methodName173;
	private String[] _methodParameterTypes173;
	private String _methodName174;
	private String[] _methodParameterTypes174;
	private String _methodName175;
	private String[] _methodParameterTypes175;
}