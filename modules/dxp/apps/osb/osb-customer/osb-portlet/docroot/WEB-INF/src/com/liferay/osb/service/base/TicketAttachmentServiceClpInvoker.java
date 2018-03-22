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

import com.liferay.osb.service.TicketAttachmentServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class TicketAttachmentServiceClpInvoker {
	public TicketAttachmentServiceClpInvoker() {
		_methodName364 = "getOSGiServiceIdentifier";

		_methodParameterTypes364 = new String[] {  };

		_methodName369 = "addTicketAttachment";

		_methodParameterTypes369 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "int", "int",
				"java.lang.String", "int"
			};

		_methodName370 = "addTicketAttachments";

		_methodParameterTypes370 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "int[][]",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName371 = "addTicketAttachments";

		_methodParameterTypes371 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName372 = "checkAvailability";

		_methodParameterTypes372 = new String[] { "long", "java.lang.String" };

		_methodName373 = "deleteTicketAttachment";

		_methodParameterTypes373 = new String[] { "long" };

		_methodName374 = "getTicketAttachment";

		_methodParameterTypes374 = new String[] { "long" };

		_methodName375 = "getUploadToken";

		_methodParameterTypes375 = new String[] { "long", "java.lang.String" };

		_methodName376 = "replicateTicketAttachment";

		_methodParameterTypes376 = new String[] { "long" };

		_methodName377 = "updateDeleteDate";

		_methodParameterTypes377 = new String[] { "long", "java.util.Date" };

		_methodName378 = "updateTicketAttachment";

		_methodParameterTypes378 = new String[] {
				"long", "long", "int", "int", "int[][]"
			};

		_methodName379 = "updateTicketAttachments";

		_methodParameterTypes379 = new String[] {
				"java.util.List", "long", "java.util.List", "java.util.List",
				"int[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName364.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes364, parameterTypes)) {
			return TicketAttachmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName369.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes369, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3],
				((Long)arguments[4]).longValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(java.lang.String)arguments[7],
				((Integer)arguments[8]).intValue());
		}

		if (_methodName370.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes370, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(), (int[])arguments[7],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[8]);
		}

		if (_methodName371.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes371, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return TicketAttachmentServiceUtil.checkAvailability(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName373.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes373, parameterTypes)) {
			return TicketAttachmentServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName374.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes374, parameterTypes)) {
			return TicketAttachmentServiceUtil.getTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName375.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes375, parameterTypes)) {
			return TicketAttachmentServiceUtil.getUploadToken(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName376.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes376, parameterTypes)) {
			return TicketAttachmentServiceUtil.replicateTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateDeleteDate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(), (int[])arguments[4]);
		}

		if (_methodName379.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes379, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateTicketAttachments((java.util.List<java.lang.Long>)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3],
				(int[])arguments[4]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName364;
	private String[] _methodParameterTypes364;
	private String _methodName369;
	private String[] _methodParameterTypes369;
	private String _methodName370;
	private String[] _methodParameterTypes370;
	private String _methodName371;
	private String[] _methodParameterTypes371;
	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName373;
	private String[] _methodParameterTypes373;
	private String _methodName374;
	private String[] _methodParameterTypes374;
	private String _methodName375;
	private String[] _methodParameterTypes375;
	private String _methodName376;
	private String[] _methodParameterTypes376;
	private String _methodName377;
	private String[] _methodParameterTypes377;
	private String _methodName378;
	private String[] _methodParameterTypes378;
	private String _methodName379;
	private String[] _methodParameterTypes379;
}