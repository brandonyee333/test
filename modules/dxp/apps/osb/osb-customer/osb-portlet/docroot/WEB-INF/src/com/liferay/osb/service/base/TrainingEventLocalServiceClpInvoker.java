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

import com.liferay.osb.service.TrainingEventLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingEventLocalServiceClpInvoker {
	public TrainingEventLocalServiceClpInvoker() {
		_methodName0 = "addTrainingEvent";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
			};

		_methodName1 = "createTrainingEvent";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTrainingEvent";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTrainingEvent";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
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

		_methodName9 = "fetchTrainingEvent";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTrainingEvent";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTrainingEvents";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTrainingEventsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTrainingEvent";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
			};

		_methodName15 = "updateTrainingEvent";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TrainingEvent", "boolean"
			};

		_methodName692 = "getBeanIdentifier";

		_methodParameterTypes692 = new String[] {  };

		_methodName693 = "setBeanIdentifier";

		_methodParameterTypes693 = new String[] { "java.lang.String" };

		_methodName699 = "addTrainingEvent";

		_methodParameterTypes699 = new String[] {
				"long", "long", "long", "long", "long", "long",
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "boolean", "java.lang.String", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName700 = "checkTrainingEventSurveys";

		_methodParameterTypes700 = new String[] { "int" };

		_methodName701 = "deleteTrainingEvent";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "deleteTrainingEvent";

		_methodParameterTypes702 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
			};

		_methodName703 = "fetchTrainingEventByDDLRecordSetId";

		_methodParameterTypes703 = new String[] { "long" };

		_methodName704 = "getTrainingEventByDDLRecordSetId";

		_methodParameterTypes704 = new String[] { "long" };

		_methodName705 = "getTrainingEvents";

		_methodParameterTypes705 = new String[] {
				"int", "java.util.Date", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName706 = "getTrainingEvents";

		_methodParameterTypes706 = new String[] {
				"java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName707 = "getTrainingEvents";

		_methodParameterTypes707 = new String[] {
				"long", "int", "java.util.Date", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName708 = "getTrainingEvents";

		_methodParameterTypes708 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName709 = "getTrainingEvents";

		_methodParameterTypes709 = new String[] {
				"long[][]", "java.util.Date", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName710 = "getTrainingEventsCount";

		_methodParameterTypes710 = new String[] { "long" };

		_methodName711 = "getTrainingEventsCount";

		_methodParameterTypes711 = new String[] { "long[][]", "java.util.Date" };

		_methodName712 = "search";

		_methodParameterTypes712 = new String[] {
				"java.lang.Integer", "java.lang.String", "java.lang.String",
				"java.lang.Integer", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String", "int",
				"int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName713 = "search";

		_methodParameterTypes713 = new String[] {
				"java.lang.String", "java.lang.Long", "java.lang.Long", "int",
				"int", "int", "int", "int", "int", "java.util.LinkedHashMap",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName714 = "searchCount";

		_methodParameterTypes714 = new String[] {
				"java.lang.Integer", "java.lang.String", "java.lang.String",
				"java.lang.Integer", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String", "int",
				"int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean"
			};

		_methodName715 = "searchCount";

		_methodParameterTypes715 = new String[] {
				"java.lang.String", "java.lang.Long", "java.lang.Long", "int",
				"int", "int", "int", "int", "int", "java.util.LinkedHashMap"
			};

		_methodName716 = "sendTrainingSurvey";

		_methodParameterTypes716 = new String[] { "long", "java.lang.String" };

		_methodName717 = "updateTrainingEvent";

		_methodParameterTypes717 = new String[] {
				"long", "long", "long", "long", "long", "long", "long",
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "boolean", "java.lang.String", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TrainingEventLocalServiceUtil.addTrainingEvent((com.liferay.osb.model.TrainingEvent)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TrainingEventLocalServiceUtil.createTrainingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TrainingEventLocalServiceUtil.deleteTrainingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TrainingEventLocalServiceUtil.deleteTrainingEvent((com.liferay.osb.model.TrainingEvent)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TrainingEventLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TrainingEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TrainingEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TrainingEventLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TrainingEventLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TrainingEventLocalServiceUtil.fetchTrainingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvents(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEventsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TrainingEventLocalServiceUtil.updateTrainingEvent((com.liferay.osb.model.TrainingEvent)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TrainingEventLocalServiceUtil.updateTrainingEvent((com.liferay.osb.model.TrainingEvent)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			TrainingEventLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return TrainingEventLocalServiceUtil.addTrainingEvent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				(java.lang.String)arguments[6], (java.lang.String)arguments[7],
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(java.lang.String)arguments[10],
				((Boolean)arguments[11]).booleanValue(),
				(java.lang.String)arguments[12],
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Integer)arguments[22]).intValue(),
				(java.lang.String)arguments[23],
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25],
				(java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				((Long)arguments[28]).longValue(),
				((Long)arguments[29]).longValue(),
				((Integer)arguments[30]).intValue(),
				(java.lang.String)arguments[31],
				(com.liferay.portal.service.ServiceContext)arguments[32]);
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			TrainingEventLocalServiceUtil.checkTrainingEventSurveys(((Integer)arguments[0]).intValue());

			return null;
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return TrainingEventLocalServiceUtil.deleteTrainingEvent(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return TrainingEventLocalServiceUtil.deleteTrainingEvent((com.liferay.osb.model.TrainingEvent)arguments[0]);
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return TrainingEventLocalServiceUtil.fetchTrainingEventByDDLRecordSetId(((Long)arguments[0]).longValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEventByDDLRecordSetId(((Long)arguments[0]).longValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvents(((Integer)arguments[0]).intValue(),
				(java.util.Date)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvents((java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvents(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				(java.util.Date)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[5]);
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvents(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEvents((long[])arguments[0],
				(java.util.Date)arguments[1],
				((Integer)arguments[2]).intValue(),
				((Integer)arguments[3]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[4]);
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEventsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return TrainingEventLocalServiceUtil.getTrainingEventsCount((long[])arguments[0],
				(java.util.Date)arguments[1]);
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return TrainingEventLocalServiceUtil.search((java.lang.Integer)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.Integer)arguments[3],
				(java.lang.String)arguments[4], (java.lang.Long)arguments[5],
				(java.lang.Long)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[18],
				((Boolean)arguments[19]).booleanValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[22]);
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			return TrainingEventLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.Long)arguments[1], (java.lang.Long)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[9],
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[12]);
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return TrainingEventLocalServiceUtil.searchCount((java.lang.Integer)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.Integer)arguments[3],
				(java.lang.String)arguments[4], (java.lang.Long)arguments[5],
				(java.lang.Long)arguments[6], (java.lang.String)arguments[7],
				(java.lang.String)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				(java.lang.String)arguments[15],
				(java.lang.String)arguments[16],
				(java.lang.String)arguments[17],
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[18],
				((Boolean)arguments[19]).booleanValue());
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			return TrainingEventLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.Long)arguments[1], (java.lang.Long)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				(java.util.LinkedHashMap<java.lang.String, java.lang.Object>)arguments[9]);
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			TrainingEventLocalServiceUtil.sendTrainingSurvey(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return TrainingEventLocalServiceUtil.updateTrainingEvent(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue(),
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				(java.lang.String)arguments[11],
				((Boolean)arguments[12]).booleanValue(),
				(java.lang.String)arguments[13],
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue(),
				((Integer)arguments[17]).intValue(),
				((Integer)arguments[18]).intValue(),
				((Integer)arguments[19]).intValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Integer)arguments[22]).intValue(),
				((Integer)arguments[23]).intValue(),
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25],
				(java.lang.String)arguments[26],
				(java.lang.String)arguments[27],
				(java.lang.String)arguments[28],
				((Long)arguments[29]).longValue(),
				((Long)arguments[30]).longValue(),
				((Integer)arguments[31]).intValue(),
				(java.lang.String)arguments[32],
				(com.liferay.portal.service.ServiceContext)arguments[33]);
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
	private String _methodName692;
	private String[] _methodParameterTypes692;
	private String _methodName693;
	private String[] _methodParameterTypes693;
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
}