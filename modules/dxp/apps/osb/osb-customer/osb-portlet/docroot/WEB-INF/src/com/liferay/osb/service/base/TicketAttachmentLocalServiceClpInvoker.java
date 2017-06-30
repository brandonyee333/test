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

import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
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

		_methodName9 = "fetchTicketAttachment";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTicketAttachment";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTicketAttachments";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTicketAttachmentsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTicketAttachment";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName15 = "updateTicketAttachment";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TicketAttachment", "boolean"
			};

		_methodName686 = "getBeanIdentifier";

		_methodParameterTypes686 = new String[] {  };

		_methodName687 = "setBeanIdentifier";

		_methodParameterTypes687 = new String[] { "java.lang.String" };

		_methodName692 = "addTicketAttachment";

		_methodParameterTypes692 = new String[] {
				"long", "long", "long", "java.lang.String", "long", "int", "int",
				"java.lang.String", "int"
			};

		_methodName693 = "addTicketAttachments";

		_methodParameterTypes693 = new String[] {
				"long", "long", "long", "java.util.List", "java.util.List",
				"int", "int", "com.liferay.portal.service.ServiceContext"
			};

		_methodName694 = "checkAvailability";

		_methodParameterTypes694 = new String[] { "long", "java.lang.String" };

		_methodName695 = "cleanTicketAttachments";

		_methodParameterTypes695 = new String[] {  };

		_methodName696 = "deleteTicketAttachment";

		_methodParameterTypes696 = new String[] { "long", "long" };

		_methodName697 = "deleteTicketAttachment";

		_methodParameterTypes697 = new String[] { "long", "long", "int" };

		_methodName698 = "deleteTicketAttachment";

		_methodParameterTypes698 = new String[] {
				"long", "com.liferay.osb.model.TicketAttachment"
			};

		_methodName699 = "fetchTicketAttachment";

		_methodParameterTypes699 = new String[] { "long", "int" };

		_methodName700 = "fetchTicketAttachment";

		_methodParameterTypes700 = new String[] {
				"long", "java.lang.String", "int", "int"
			};

		_methodName701 = "getFileAsStream";

		_methodParameterTypes701 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName702 = "getTicketAttachments";

		_methodParameterTypes702 = new String[] { "java.util.Date", "int" };

		_methodName703 = "getTicketAttachments";

		_methodParameterTypes703 = new String[] { "int[][]" };

		_methodName704 = "getTicketAttachments";

		_methodParameterTypes704 = new String[] { "long" };

		_methodName705 = "getTicketAttachments";

		_methodParameterTypes705 = new String[] { "long", "int[][]", "int" };

		_methodName706 = "getTicketAttachments";

		_methodParameterTypes706 = new String[] { "long", "int[][]", "int[][]" };

		_methodName707 = "getTicketAttachments";

		_methodParameterTypes707 = new String[] {
				"long", "int[][]", "int[][]", "int"
			};

		_methodName708 = "getTicketAttachments";

		_methodParameterTypes708 = new String[] { "long", "long" };

		_methodName709 = "getTicketAttachments";

		_methodParameterTypes709 = new String[] { "long", "long", "int", "int" };

		_methodName710 = "getTicketAttachmentsCount";

		_methodParameterTypes710 = new String[] { "long", "int[][]" };

		_methodName711 = "getTicketAttachmentsCount";

		_methodParameterTypes711 = new String[] { "long", "int[][]", "int[][]" };

		_methodName712 = "getTicketAttachmentsZipFile";

		_methodParameterTypes712 = new String[] { "long", "int[][]" };

		_methodName713 = "replicateTicketAttachment";

		_methodParameterTypes713 = new String[] { "long", "long" };

		_methodName714 = "updateDeleteDate";

		_methodParameterTypes714 = new String[] { "long", "long", "java.util.Date" };

		_methodName715 = "updateExtractedText";

		_methodParameterTypes715 = new String[] {
				"com.liferay.osb.model.TicketAttachment"
			};

		_methodName716 = "updateStatus";

		_methodParameterTypes716 = new String[] {
				"com.liferay.portal.model.User", "java.util.List", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName717 = "updateTicketAttachment";

		_methodParameterTypes717 = new String[] { "long", "long", "int", "int" };

		_methodName718 = "updateTicketAttachment";

		_methodParameterTypes718 = new String[] {
				"long", "long", "long", "int", "int"
			};

		_methodName719 = "updateTicketAttachments";

		_methodParameterTypes719 = new String[] {
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
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachment(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment((com.liferay.osb.model.TicketAttachment)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
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

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.addTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>>)arguments[3],
				(java.util.List<java.lang.Integer>)arguments[4],
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[7]);
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.checkAvailability(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.cleanTicketAttachments();

			return null;
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());

			return null;
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.deleteTicketAttachment(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.TicketAttachment)arguments[1]);
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.fetchTicketAttachment(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getFileAsStream((com.liferay.osb.model.TicketAttachment)arguments[0]);
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments((java.util.Date)arguments[0],
				((Integer)arguments[1]).intValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments((int[])arguments[0]);
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2]);
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2],
				((Integer)arguments[3]).intValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachments(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsCount(((Long)arguments[0]).longValue(),
				(int[])arguments[1], (int[])arguments[2]);
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.getTicketAttachmentsZipFile(((Long)arguments[0]).longValue(),
				(int[])arguments[1]);
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.replicateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateDeleteDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (java.util.Date)arguments[2]);
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.updateExtractedText((com.liferay.osb.model.TicketAttachment)arguments[0]);

			return null;
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			TicketAttachmentLocalServiceUtil.updateStatus((com.liferay.portal.model.User)arguments[0],
				(java.util.List<com.liferay.osb.model.TicketAttachment>)arguments[1],
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);

			return null;
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return TicketAttachmentLocalServiceUtil.updateTicketAttachment(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue());
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
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
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName686;
	private String[] _methodParameterTypes686;
	private String _methodName687;
	private String[] _methodParameterTypes687;
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
	private String _methodName715;
	private String[] _methodParameterTypes715;
	private String _methodName716;
	private String[] _methodParameterTypes716;
	private String _methodName717;
	private String[] _methodParameterTypes717;
	private String _methodName718;
	private String[] _methodParameterTypes718;
	private String _methodName719;
	private String[] _methodParameterTypes719;
}