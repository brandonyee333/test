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

import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingCertificateLocalServiceClpInvoker {
	public TrainingCertificateLocalServiceClpInvoker() {
		_methodName0 = "addTrainingCertificate";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TrainingCertificate"
			};

		_methodName1 = "createTrainingCertificate";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTrainingCertificate";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTrainingCertificate";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TrainingCertificate"
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

		_methodName9 = "fetchTrainingCertificate";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTrainingCertificate";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTrainingCertificates";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTrainingCertificatesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTrainingCertificate";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TrainingCertificate"
			};

		_methodName15 = "updateTrainingCertificate";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TrainingCertificate", "boolean"
			};

		_methodName684 = "getBeanIdentifier";

		_methodParameterTypes684 = new String[] {  };

		_methodName685 = "setBeanIdentifier";

		_methodParameterTypes685 = new String[] { "java.lang.String" };

		_methodName690 = "addTrainingCertificate";

		_methodParameterTypes690 = new String[] {
				"long", "long", "java.lang.String", "int", "long"
			};

		_methodName691 = "addTrainingCertificates";

		_methodParameterTypes691 = new String[] { "long", "long[][]" };

		_methodName692 = "deleteTrainingCustomerTrainingCertificate";

		_methodParameterTypes692 = new String[] { "long" };

		_methodName693 = "fetchTrainingCertificateByTrainingCustomerId";

		_methodParameterTypes693 = new String[] { "long" };

		_methodName694 = "getTrainingCertificate";

		_methodParameterTypes694 = new String[] {
				"javax.portlet.ResourceResponse", "long"
			};

		_methodName695 = "getTrainingCertificate";

		_methodParameterTypes695 = new String[] { "java.lang.String" };

		_methodName696 = "getTrainingCertificate";

		_methodParameterTypes696 = new String[] {
				"java.lang.String", "java.lang.String", "long"
			};

		_methodName697 = "getTrainingCertificateByTrainingCustomerId";

		_methodParameterTypes697 = new String[] { "long" };

		_methodName698 = "hasTrainingCertificate";

		_methodParameterTypes698 = new String[] { "long" };

		_methodName699 = "hasTrainingCertificateCertifiedDate";

		_methodParameterTypes699 = new String[] { "long" };

		_methodName700 = "sendTrainingCertificate";

		_methodParameterTypes700 = new String[] { "long" };

		_methodName701 = "updateCertifiedDate";

		_methodParameterTypes701 = new String[] { "long", "java.util.Date" };

		_methodName702 = "updateCertifiedDate";

		_methodParameterTypes702 = new String[] { "long", "long" };

		_methodName703 = "updateTrainingCertificate";

		_methodParameterTypes703 = new String[] { "long", "java.util.Date", "int" };

		_methodName704 = "updateTrainingCertificate";

		_methodParameterTypes704 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "int"
			};

		_methodName705 = "updateUserProfileHistoryId";

		_methodParameterTypes705 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.addTrainingCertificate((com.liferay.osb.model.TrainingCertificate)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.createTrainingCertificate(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.deleteTrainingCertificate(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.deleteTrainingCertificate((com.liferay.osb.model.TrainingCertificate)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.fetchTrainingCertificate(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getTrainingCertificate(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getTrainingCertificates(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getTrainingCertificatesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateTrainingCertificate((com.liferay.osb.model.TrainingCertificate)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateTrainingCertificate((com.liferay.osb.model.TrainingCertificate)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName684.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes684, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName685.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes685, parameterTypes)) {
			TrainingCertificateLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.addTrainingCertificate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				((Long)arguments[4]).longValue());
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			TrainingCertificateLocalServiceUtil.addTrainingCertificates(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			TrainingCertificateLocalServiceUtil.deleteTrainingCustomerTrainingCertificate(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName693.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes693, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.fetchTrainingCertificateByTrainingCustomerId(((Long)arguments[0]).longValue());
		}

		if (_methodName694.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes694, parameterTypes)) {
			TrainingCertificateLocalServiceUtil.getTrainingCertificate((javax.portlet.ResourceResponse)arguments[0],
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName695.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes695, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getTrainingCertificate((java.lang.String)arguments[0]);
		}

		if (_methodName696.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes696, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getTrainingCertificate((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], ((Long)arguments[2]).longValue());
		}

		if (_methodName697.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes697, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.getTrainingCertificateByTrainingCustomerId(((Long)arguments[0]).longValue());
		}

		if (_methodName698.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes698, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.hasTrainingCertificate(((Long)arguments[0]).longValue());
		}

		if (_methodName699.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes699, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.hasTrainingCertificateCertifiedDate(((Long)arguments[0]).longValue());
		}

		if (_methodName700.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes700, parameterTypes)) {
			TrainingCertificateLocalServiceUtil.sendTrainingCertificate(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName701.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes701, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateCertifiedDate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1]);
		}

		if (_methodName702.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes702, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateCertifiedDate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName703.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes703, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateTrainingCertificate(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], ((Integer)arguments[2]).intValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateTrainingCertificate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue());
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			return TrainingCertificateLocalServiceUtil.updateUserProfileHistoryId(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4], (java.lang.String)arguments[5]);
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
	private String _methodName684;
	private String[] _methodParameterTypes684;
	private String _methodName685;
	private String[] _methodParameterTypes685;
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
}