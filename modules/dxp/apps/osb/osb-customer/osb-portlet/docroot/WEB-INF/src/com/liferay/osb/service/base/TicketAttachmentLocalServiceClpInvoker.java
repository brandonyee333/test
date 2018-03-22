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

import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketAttachmentLocalServiceClpInvoker {
	public TicketAttachmentLocalServiceClpInvoker() {
		_methodName0 = "addTicketAttachment";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName1 = "createTicketAttachment";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTicketAttachment";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTicketAttachment";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
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

		_methodName10 = "fetchTicketAttachment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getTicketAttachment";

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

		_methodName17 = "getTicketAttachments";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getTicketAttachmentsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateTicketAttachment";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName278 = "getOSGiServiceIdentifier";

		_methodParameterTypes278 = new String[] {  };

		_methodName283 = "addTicketAttachment";

		_methodParameterTypes283 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "int", "int",
				"java.lang.String", "int"
			};

		_methodName284 = "addTicketAttachments";

		_methodParameterTypes284 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName285 = "checkAvailability";

		_methodParameterTypes285 = new String[] { "long", "java.lang.String" };

		_methodName286 = "cleanTicketAttachments";

		_methodParameterTypes286 = new String[] {  };

		_methodName287 = "deleteTicketAttachment";

		_methodParameterTypes287 = new String[] { "long", "long" };

		_methodName288 = "deleteTicketAttachment";

		_methodParameterTypes288 = new String[] { "long", "long", "int" };

		_methodName289 = "deleteTicketAttachment";

		_methodParameterTypes289 = new String[] {
				"long", "com.liferay.osb.model.TicketAttachment"
			};

		_methodName290 = "fetchTicketAttachment";

		_methodParameterTypes290 = new String[] { "long", "int" };

		_methodName291 = "fetchTicketAttachment";

		_methodParameterTypes291 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName292 = "getFileAsStream";

		_methodParameterTypes292 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName293 = "getTicketAttachments";

		_methodParameterTypes293 = new String[] { "long" };

		_methodName294 = "getTicketAttachments";

		_methodParameterTypes294 = new String[] {
				"long", "int[][]", "int[][]", "int"
			};

		_methodName295 = "getTicketAttachments";

		_methodParameterTypes295 = new String[] { "long", "long" };

		_methodName296 = "getTicketAttachments";

		_methodParameterTypes296 = new String[] { "long", "long", "int", "int" };

		_methodName297 = "getTicketAttachmentsCount";

		_methodParameterTypes297 = new String[] { "long", "int[][]", "int[][]" };

		_methodName298 = "getTicketAttachmentsZipFile";

		_methodParameterTypes298 = new String[] { "long", "int[][]" };

		_methodName299 = "replicateTicketAttachment";

		_methodParameterTypes299 = new String[] { "long", "long" };

		_methodName300 = "updateDeleteDate";

		_methodParameterTypes300 = new String[] { "long", "long", "java.util.Date" };

		_methodName301 = "updateExtractedText";

		_methodParameterTypes301 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName302 = "updateStatus";

		_methodParameterTypes302 = new String[] {
				"com.liferay.portal.kernel.model.User", "java.util.List", "long",
				"int", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName303 = "updateTicketAttachment";

		_methodParameterTypes303 = new String[] { "long", "long", "int", "int" };

		_methodName304 = "updateTicketAttachment";

		_methodParameterTypes304 = new String[] {
				"long", "long", "long", "int", "int"
			};

		_methodName305 = "updateTicketAttachments";

		_methodParameterTypes305 = new String[] {
				"java.util.List", "long", "java.util.List", "java.util.List"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.addTicketAttachment((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.createTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName278.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes278, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName283.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes283, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.addTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.lang.String)arguments[7],
				((Integer)arguments[8]).intValue());
		}

		if (_methodName284.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes284, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName285.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes285, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.checkAvailability(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName286.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes286, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.cleanTicketAttachments();

			return null;
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());

			return null;
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.TicketAttachment)arguments[1]);
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getFileAsStream((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue());
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2]);
		}

		if (_methodName298.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes298, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsZipFile(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName299.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes299, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.replicateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName300.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes300, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateDeleteDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2]);
		}

		if (_methodName301.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes301, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.updateExtractedText((com.liferay.osb.model.TicketAttachment)arguments[0]);

			return null;
		}

		if (_methodName302.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes302, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.updateStatus((com.liferay.portal.kernel.model.User)arguments[0],
				(java.util.List<com.liferay.osb.model.TicketAttachment>)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[4]);

			return null;
		}

		if (_methodName303.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes303, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName304.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes304, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName305.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes305, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachments((java.util.List<java.lang.Long>)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3]);
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
	private String _methodName292;
	private String[] _methodParameterTypes292;
	private String _methodName293;
	private String[] _methodParameterTypes293;
	private String _methodName294;
	private String[] _methodParameterTypes294;
	private String _methodName295;
	private String[] _methodParameterTypes295;
	private String _methodName296;
	private String[] _methodParameterTypes296;
	private String _methodName297;
	private String[] _methodParameterTypes297;
	private String _methodName298;
	private String[] _methodParameterTypes298;
	private String _methodName299;
	private String[] _methodParameterTypes299;
	private String _methodName300;
	private String[] _methodParameterTypes300;
	private String _methodName301;
	private String[] _methodParameterTypes301;
	private String _methodName302;
	private String[] _methodParameterTypes302;
	private String _methodName303;
	private String[] _methodParameterTypes303;
	private String _methodName304;
	private String[] _methodParameterTypes304;
	private String _methodName305;
	private String[] _methodParameterTypes305;
}