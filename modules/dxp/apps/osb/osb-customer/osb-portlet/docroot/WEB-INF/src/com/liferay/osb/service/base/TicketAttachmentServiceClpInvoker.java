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

import com.liferay.osb.service.TicketAttachmentServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TicketAttachmentServiceClpInvoker {
	public TicketAttachmentServiceClpInvoker() {
		_methodName670 = "getBeanIdentifier";

		_methodParameterTypes670 = new String[] {  };

		_methodName671 = "setBeanIdentifier";

		_methodParameterTypes671 = new String[] { "java.lang.String" };

		_methodName676 = "addTicketAttachment";

		_methodParameterTypes676 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "int", "int",
				"java.lang.String", "int"
			};

		_methodName677 = "addTicketAttachments";

		_methodParameterTypes677 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "int[][]",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName678 = "addTicketAttachments";

		_methodParameterTypes678 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "com.liferay.portal.service.ServiceContext"
			};

		_methodName679 = "checkAvailability";

		_methodParameterTypes679 = new String[] { "long", "java.lang.String" };

		_methodName680 = "deleteTicketAttachment";

		_methodParameterTypes680 = new String[] { "long" };

		_methodName681 = "getTicketAttachment";

		_methodParameterTypes681 = new String[] { "long" };

		_methodName682 = "getUploadToken";

		_methodParameterTypes682 = new String[] { "long", "java.lang.String" };

		_methodName683 = "replicateTicketAttachment";

		_methodParameterTypes683 = new String[] { "long" };

		_methodName684 = "updateDeleteDate";

		_methodParameterTypes684 = new String[] { "long", "java.util.Date" };

		_methodName685 = "updateTicketAttachment";

		_methodParameterTypes685 = new String[] {
				"long", "long", "int", "int", "int[][]"
			};

		_methodName686 = "updateTicketAttachments";

		_methodParameterTypes686 = new String[] {
				"java.util.List", "long", "java.util.List", "java.util.List",
				"int[][]"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName670.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes670, parameterTypes)) {
			return TicketAttachmentServiceUtil.getBeanIdentifier();
		}

		if (_methodName671.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes671, parameterTypes)) {
			TicketAttachmentServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName676.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes676, parameterTypes)) {
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

		if (_methodName677.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes677, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(), (int[])arguments[7],
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName678.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes678, parameterTypes)) {
			return TicketAttachmentServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName679.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes679, parameterTypes)) {
			return TicketAttachmentServiceUtil.checkAvailability(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return TicketAttachmentServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			return TicketAttachmentServiceUtil.getTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName682.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes682, parameterTypes)) {
			return TicketAttachmentServiceUtil.getUploadToken(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName683.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes683, parameterTypes)) {
			return TicketAttachmentServiceUtil.replicateTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName684.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateDeleteDate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName685.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(), (int[])arguments[4]);
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return TicketAttachmentServiceUtil.updateTicketAttachments((java.util.List<java.lang.Long>)arguments[0],
				((Long)arguments[1]).longValue(),
				(java.util.List<java.lang.Integer>)arguments[2],
				(java.util.List<java.lang.Integer>)arguments[3],
				(int[])arguments[4]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName670;
	private String[] _methodParameterTypes670;
	private String _methodName671;
	private String[] _methodParameterTypes671;
	private String _methodName676;
	private String[] _methodParameterTypes676;
	private String _methodName677;
	private String[] _methodParameterTypes677;
	private String _methodName678;
	private String[] _methodParameterTypes678;
	private String _methodName679;
	private String[] _methodParameterTypes679;
	private String _methodName680;
	private String[] _methodParameterTypes680;
	private String _methodName681;
	private String[] _methodParameterTypes681;
	private String _methodName682;
	private String[] _methodParameterTypes682;
	private String _methodName683;
	private String[] _methodParameterTypes683;
	private String _methodName684;
	private String[] _methodParameterTypes684;
	private String _methodName685;
	private String[] _methodParameterTypes685;
	private String _methodName686;
	private String[] _methodParameterTypes686;
}