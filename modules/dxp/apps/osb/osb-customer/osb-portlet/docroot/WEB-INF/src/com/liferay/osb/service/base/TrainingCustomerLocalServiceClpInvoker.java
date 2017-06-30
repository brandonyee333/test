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

import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingCustomerLocalServiceClpInvoker {
	public TrainingCustomerLocalServiceClpInvoker() {
		_methodName0 = "addTrainingCustomer";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TrainingCustomer"
			};

		_methodName1 = "createTrainingCustomer";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTrainingCustomer";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTrainingCustomer";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TrainingCustomer"
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

		_methodName9 = "fetchTrainingCustomer";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTrainingCustomer";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTrainingCustomers";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTrainingCustomersCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTrainingCustomer";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TrainingCustomer"
			};

		_methodName15 = "updateTrainingCustomer";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TrainingCustomer", "boolean"
			};

		_methodName680 = "getBeanIdentifier";

		_methodParameterTypes680 = new String[] {  };

		_methodName681 = "setBeanIdentifier";

		_methodParameterTypes681 = new String[] { "java.lang.String" };

		_methodName686 = "addTrainingCustomer";

		_methodParameterTypes686 = new String[] { "long", "long", "long", "long" };

		_methodName687 = "addTrainingCustomers";

		_methodParameterTypes687 = new String[] {
				"long[][]", "long", "long", "java.util.Map"
			};

		_methodName688 = "deleteTrainingCustomer";

		_methodParameterTypes688 = new String[] { "long" };

		_methodName689 = "deleteTrainingCustomer";

		_methodParameterTypes689 = new String[] {
				"com.liferay.osb.model.TrainingCustomer"
			};

		_methodName690 = "deleteTrainingCustomers";

		_methodParameterTypes690 = new String[] { "long" };

		_methodName691 = "deleteTrainingCustomers";

		_methodParameterTypes691 = new String[] { "long", "long" };

		_methodName692 = "deleteTrainingCustomers";

		_methodParameterTypes692 = new String[] { "long[][]", "long", "long" };

		_methodName693 = "fetchTrainingCustomer";

		_methodParameterTypes693 = new String[] { "long", "long", "long" };

		_methodName694 = "fetchTrainingCustomer";

		_methodParameterTypes694 = new String[] {
				"long", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName695 = "getClassTrainingCustomers";

		_methodParameterTypes695 = new String[] { "long", "long" };

		_methodName696 = "getTrainingCustomer";

		_methodParameterTypes696 = new String[] { "long", "long", "long" };

		_methodName697 = "getTrainingCustomerBadgeNames";

		_methodParameterTypes697 = new String[] { "long", "long" };

		_methodName698 = "getTrainingCustomers";

		_methodParameterTypes698 = new String[] { "long", "long", "int[][]" };

		_methodName699 = "getTrainingCustomersCount";

		_methodParameterTypes699 = new String[] { "long", "long", "int[][]" };

		_methodName700 = "getUserTrainingCustomers";

		_methodParameterTypes700 = new String[] { "long", "long" };

		_methodName701 = "getUserTrainingCustomersCount";

		_methodParameterTypes701 = new String[] { "long" };

		_methodName702 = "hasTrainingCustomer";

		_methodParameterTypes702 = new String[] { "long", "long", "long" };

		_methodName703 = "hasTrainingCustomerBadgeNames";

		_methodParameterTypes703 = new String[] { "long" };

		_methodName704 = "search";

		_methodParameterTypes704 = new String[] { "java.lang.String", "int", "int" };

		_methodName705 = "search";

		_methodParameterTypes705 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean", "int", "int"
			};

		_methodName706 = "searchCount";

		_methodParameterTypes706 = new String[] { "java.lang.String" };

		_methodName707 = "searchCount";

		_methodParameterTypes707 = new String[] {
				"java.lang.String", "java.lang.String", "java.lang.String",
				"boolean"
			};

		_methodName708 = "updateComments";

		_methodParameterTypes708 = new String[] { "long", "java.lang.String" };

		_methodName709 = "updateStatus";

		_methodParameterTypes709 = new String[] { "long", "int" };

		_methodName710 = "updateStatuses";

		_methodParameterTypes710 = new String[] { "long[][]", "long", "int" };

		_methodName711 = "updateTrainingCustomer";

		_methodParameterTypes711 = new String[] {
				"long", "long", "long", "long", "long"
			};

		_methodName712 = "updateTrainingCustomer";

		_methodParameterTypes712 = new String[] {
				"long", "long", "long", "long", "java.lang.String",
				"java.lang.String", "java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.addTrainingCustomer((com.liferay.osb.model.TrainingCustomer)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.createTrainingCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.deleteTrainingCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.deleteTrainingCustomer((com.liferay.osb.model.TrainingCustomer)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomers(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomersCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.updateTrainingCustomer((com.liferay.osb.model.TrainingCustomer)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.updateTrainingCustomer((com.liferay.osb.model.TrainingCustomer)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			TrainingCustomerLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.addTrainingCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue());
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			TrainingCustomerLocalServiceUtil.addTrainingCustomers((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Map<java.lang.Long, java.lang.Long>)arguments[3]);

			return null;
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.deleteTrainingCustomer(((Long)arguments[0]).longValue());
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.deleteTrainingCustomer((com.liferay.osb.model.TrainingCustomer)arguments[0]);
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			TrainingCustomerLocalServiceUtil.deleteTrainingCustomers(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			TrainingCustomerLocalServiceUtil.deleteTrainingCustomers(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			TrainingCustomerLocalServiceUtil.deleteTrainingCustomers((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.fetchTrainingCustomer(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[1]);
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getClassTrainingCustomers(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomerBadgeNames(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomers(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (int[])arguments[2]);
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getTrainingCustomersCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (int[])arguments[2]);
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getUserTrainingCustomers(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.getUserTrainingCustomersCount(((Long)arguments[0]).longValue());
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.hasTrainingCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.hasTrainingCustomerBadgeNames(((Long)arguments[0]).longValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.search((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.search((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				((Integer)arguments[4]).intValue(),
				((Integer)arguments[5]).intValue());
		}

		if (_methodName706.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes706, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.searchCount((java.lang.String)arguments[0]);
		}

		if (_methodName707.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes707, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.searchCount((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Boolean)arguments[3]).booleanValue());
		}

		if (_methodName708.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes708, parameterTypes)) {
			TrainingCustomerLocalServiceUtil.updateComments(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);

			return null;
		}

		if (_methodName709.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes709, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.updateStatuses((long[])arguments[0],
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.updateTrainingCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			return TrainingCustomerLocalServiceUtil.updateTrainingCustomer(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Long)arguments[3]).longValue(),
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				(java.lang.String)arguments[6], (java.lang.String)arguments[7]);
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
}