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

		_methodName282 = "getOSGiServiceIdentifier";

		_methodParameterTypes282 = new String[] {  };

		_methodName287 = "addTicketAttachment";

		_methodParameterTypes287 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "int", "int",
				"java.lang.String", "int"
			};

		_methodName288 = "addTicketAttachments";

		_methodParameterTypes288 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName289 = "checkAvailability";

		_methodParameterTypes289 = new String[] { "long", "java.lang.String" };

		_methodName290 = "cleanTicketAttachments";

		_methodParameterTypes290 = new String[] {  };

		_methodName291 = "deleteTicketAttachment";

		_methodParameterTypes291 = new String[] { "long", "long" };

		_methodName292 = "deleteTicketAttachment";

		_methodParameterTypes292 = new String[] { "long", "long", "int" };

		_methodName293 = "deleteTicketAttachment";

		_methodParameterTypes293 = new String[] {
				"long", "com.liferay.osb.model.TicketAttachment"
			};

		_methodName294 = "fetchTicketAttachment";

		_methodParameterTypes294 = new String[] { "long", "int" };

		_methodName295 = "fetchTicketAttachment";

		_methodParameterTypes295 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName296 = "getFileAsStream";

		_methodParameterTypes296 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName297 = "getTicketAttachments";

		_methodParameterTypes297 = new String[] { "java.util.Date", "int" };

		_methodName298 = "getTicketAttachments";

		_methodParameterTypes298 = new String[] { "int[][]" };

		_methodName299 = "getTicketAttachments";

		_methodParameterTypes299 = new String[] { "long" };

		_methodName300 = "getTicketAttachments";

		_methodParameterTypes300 = new String[] { "long", "int[][]", "int" };

		_methodName301 = "getTicketAttachments";

		_methodParameterTypes301 = new String[] { "long", "int[][]", "int[][]" };

		_methodName302 = "getTicketAttachments";

		_methodParameterTypes302 = new String[] {
				"long", "int[][]", "int[][]", "int"
			};

		_methodName303 = "getTicketAttachments";

		_methodParameterTypes303 = new String[] { "long", "long" };

		_methodName304 = "getTicketAttachments";

		_methodParameterTypes304 = new String[] { "long", "long", "int", "int" };

		_methodName305 = "getTicketAttachmentsCount";

		_methodParameterTypes305 = new String[] { "long", "int[][]" };

		_methodName306 = "getTicketAttachmentsCount";

		_methodParameterTypes306 = new String[] { "long", "int[][]", "int[][]" };

		_methodName307 = "getTicketAttachmentsZipFile";

		_methodParameterTypes307 = new String[] { "long", "int[][]" };

		_methodName308 = "replicateTicketAttachment";

		_methodParameterTypes308 = new String[] { "long", "long" };

		_methodName309 = "updateDeleteDate";

		_methodParameterTypes309 = new String[] { "long", "long", "java.util.Date" };

		_methodName310 = "updateExtractedText";

		_methodParameterTypes310 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName311 = "updateStatus";

		_methodParameterTypes311 = new String[] {
				"com.liferay.portal.kernel.model.User", "java.util.List", "long",
				"int", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName312 = "updateTicketAttachment";

		_methodParameterTypes312 = new String[] { "long", "long", "int", "int" };

		_methodName313 = "updateTicketAttachment";

		_methodParameterTypes313 = new String[] {
				"long", "long", "long", "int", "int"
			};

		_methodName314 = "updateTicketAttachments";

		_methodParameterTypes314 = new String[] {
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

		if (_methodName282.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes282, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName287.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes287, parameterTypes)) {
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

		if (_methodName288.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes288, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName289.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes289, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.checkAvailability(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName290.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes290, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.cleanTicketAttachments();

			return null;
		}

		if (_methodName291.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes291, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName292.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes292, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());

			return null;
		}

		if (_methodName293.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes293, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.TicketAttachment)arguments[1]);
		}

		if (_methodName294.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes294, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName295.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes295, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName296.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes296, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getFileAsStream((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName297.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes297, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments((java.util.Date)arguments[0],
				((Integer)arguments[1]).intValue());
		}

		if (_methodName298.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes298, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments((int[])arguments[0]);
		}

		if (_methodName299.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes299, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue());
		}

		if (_methodName300.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes300, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName301.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes301, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2]);
		}

		if (_methodName302.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes302, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName303.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes303, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName304.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes304, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName305.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes305, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName306.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes306, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2]);
		}

		if (_methodName307.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes307, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsZipFile(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName308.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes308, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.replicateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName309.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes309, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateDeleteDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2]);
		}

		if (_methodName310.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes310, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.updateExtractedText((com.liferay.osb.model.TicketAttachment)arguments[0]);

			return null;
		}

		if (_methodName311.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes311, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.updateStatus((com.liferay.portal.kernel.model.User)arguments[0],
				(java.util.List<com.liferay.osb.model.TicketAttachment>)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[4]);

			return null;
		}

		if (_methodName312.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes312, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName313.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes313, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName314.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes314, parameterTypes)) {
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
	private String _methodName282;
	private String[] _methodParameterTypes282;
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
	private String _methodName306;
	private String[] _methodParameterTypes306;
	private String _methodName307;
	private String[] _methodParameterTypes307;
	private String _methodName308;
	private String[] _methodParameterTypes308;
	private String _methodName309;
	private String[] _methodParameterTypes309;
	private String _methodName310;
	private String[] _methodParameterTypes310;
	private String _methodName311;
	private String[] _methodParameterTypes311;
	private String _methodName312;
	private String[] _methodParameterTypes312;
	private String _methodName313;
	private String[] _methodParameterTypes313;
	private String _methodName314;
	private String[] _methodParameterTypes314;
}