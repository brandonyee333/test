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

import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingCertificateTemplateLocalServiceClpInvoker {
	public TrainingCertificateTemplateLocalServiceClpInvoker() {
		_methodName0 = "addTrainingCertificateTemplate";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TrainingCertificateTemplate"
			};

		_methodName1 = "createTrainingCertificateTemplate";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTrainingCertificateTemplate";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTrainingCertificateTemplate";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TrainingCertificateTemplate"
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

		_methodName9 = "fetchTrainingCertificateTemplate";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTrainingCertificateTemplate";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTrainingCertificateTemplates";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTrainingCertificateTemplatesCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTrainingCertificateTemplate";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TrainingCertificateTemplate"
			};

		_methodName15 = "updateTrainingCertificateTemplate";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TrainingCertificateTemplate", "boolean"
			};

		_methodName680 = "getBeanIdentifier";

		_methodParameterTypes680 = new String[] {  };

		_methodName681 = "setBeanIdentifier";

		_methodParameterTypes681 = new String[] { "java.lang.String" };

		_methodName686 = "addTrainingCertificateTemplate";

		_methodParameterTypes686 = new String[] {
				"long", "java.lang.String", "java.lang.String", "int",
				"java.lang.String", "java.io.InputStream", "java.lang.String",
				"java.io.InputStream"
			};

		_methodName687 = "deleteTrainingCertificateTemplate";

		_methodParameterTypes687 = new String[] { "long" };

		_methodName688 = "getTrainingCertificateTemplateAsStream";

		_methodParameterTypes688 = new String[] { "long" };

		_methodName689 = "getTrainingCertificateTemplates";

		_methodParameterTypes689 = new String[] { "int" };

		_methodName690 = "getTrainingCertificateTemplates";

		_methodParameterTypes690 = new String[] {
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName691 = "hasCompletedTrainingCertificateTemplate";

		_methodParameterTypes691 = new String[] { "long", "long", "long" };

		_methodName692 = "updateTrainingCertificateTemplate";

		_methodParameterTypes692 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String", "int",
				"java.lang.String", "java.io.InputStream", "java.lang.String",
				"java.io.InputStream"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.addTrainingCertificateTemplate((com.liferay.osb.model.TrainingCertificateTemplate)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.createTrainingCertificateTemplate(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.deleteTrainingCertificateTemplate(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.deleteTrainingCertificateTemplate((com.liferay.osb.model.TrainingCertificateTemplate)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.fetchTrainingCertificateTemplate(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplate(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplates(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplatesCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.updateTrainingCertificateTemplate((com.liferay.osb.model.TrainingCertificateTemplate)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.updateTrainingCertificateTemplate((com.liferay.osb.model.TrainingCertificateTemplate)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName680.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes680, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName681.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes681, parameterTypes)) {
			TrainingCertificateTemplateLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName686.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes686, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.addTrainingCertificateTemplate(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				(java.io.InputStream)arguments[5],
				(java.lang.String)arguments[6],
				(java.io.InputStream)arguments[7]);
		}

		if (_methodName687.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes687, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.deleteTrainingCertificateTemplate(((Long)arguments[0]).longValue());
		}

		if (_methodName688.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes688, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplateAsStream(((Long)arguments[0]).longValue());
		}

		if (_methodName689.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes689, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplates(((Integer)arguments[0]).intValue());
		}

		if (_methodName690.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes690, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.getTrainingCertificateTemplates(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[2]);
		}

		if (_methodName691.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes691, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.hasCompletedTrainingCertificateTemplate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName692.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes692, parameterTypes)) {
			return TrainingCertificateTemplateLocalServiceUtil.updateTrainingCertificateTemplate(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				((Integer)arguments[4]).intValue(),
				(java.lang.String)arguments[5],
				(java.io.InputStream)arguments[6],
				(java.lang.String)arguments[7],
				(java.io.InputStream)arguments[8]);
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
}