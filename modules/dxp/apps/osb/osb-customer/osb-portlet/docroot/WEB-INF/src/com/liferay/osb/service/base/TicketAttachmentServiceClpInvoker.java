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
		_methodName372 = "getOSGiServiceIdentifier";

		_methodParameterTypes372 = new String[] {  };

		_methodName377 = "addTicketAttachment";

		_methodParameterTypes377 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "int", "int",
				"java.lang.String", "int"
			};

		_methodName378 = "addTicketAttachments";

		_methodParameterTypes378 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "int[][]",
				"com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName379 = "addTicketAttachments";

		_methodParameterTypes379 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName380 = "checkAvailability";

		_methodParameterTypes380 = new String[] { "long", "java.lang.String" };

		_methodName381 = "deleteTicketAttachment";

		_methodParameterTypes381 = new String[] { "long" };

		_methodName382 = "getTicketAttachment";

		_methodParameterTypes382 = new String[] { "long" };

		_methodName383 = "getUploadToken";

		_methodParameterTypes383 = new String[] { "long", "java.lang.String" };

		_methodName384 = "replicateTicketAttachment";

		_methodParameterTypes384 = new String[] { "long" };

		_methodName385 = "updateDeleteDate";

		_methodParameterTypes385 = new String[] { "long", "java.util.Date" };

		_methodName386 = "updateTicketAttachment";

		_methodParameterTypes386 = new String[] {
				"long", "long", "int", "int", "int[][]"
			};

		_methodName387 = "updateTicketAttachments";

		_methodParameterTypes387 = new String[] {
				"java.util.List", "long", "java.util.List", "java.util.List",
				"int[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName372.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes372, parameterTypes)) {
			return TicketAttachmentServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName377.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes377, parameterTypes)) {
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

		if (_methodName378.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes378, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(), (int[])arguments[7],
				(com.liferay.portal.kernel.service.ServiceContext)arguments[8]);
		}

		if (_methodName379.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes379, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[7]);
		}

		if (_methodName380.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes380, parameterTypes)) {
			return TicketAttachmentServiceUtil.checkAvailability(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName381.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes381, parameterTypes)) {
			return TicketAttachmentServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName382.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes382, parameterTypes)) {
			return TicketAttachmentServiceUtil.getTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName383.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes383, parameterTypes)) {
			return TicketAttachmentServiceUtil.getUploadToken(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName384.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes384, parameterTypes)) {
			return TicketAttachmentServiceUtil.replicateTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName385.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes385, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateDeleteDate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName386.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes386, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(), (int[])arguments[4]);
		}

		if (_methodName387.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes387, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateTicketAttachments((java.util.List<java.lang.Long>)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3],
				(int[])arguments[4]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName372;
	private String[] _methodParameterTypes372;
	private String _methodName377;
	private String[] _methodParameterTypes377;
	private String _methodName378;
	private String[] _methodParameterTypes378;
	private String _methodName379;
	private String[] _methodParameterTypes379;
	private String _methodName380;
	private String[] _methodParameterTypes380;
	private String _methodName381;
	private String[] _methodParameterTypes381;
	private String _methodName382;
	private String[] _methodParameterTypes382;
	private String _methodName383;
	private String[] _methodParameterTypes383;
	private String _methodName384;
	private String[] _methodParameterTypes384;
	private String _methodName385;
	private String[] _methodParameterTypes385;
	private String _methodName386;
	private String[] _methodParameterTypes386;
	private String _methodName387;
	private String[] _methodParameterTypes387;
}