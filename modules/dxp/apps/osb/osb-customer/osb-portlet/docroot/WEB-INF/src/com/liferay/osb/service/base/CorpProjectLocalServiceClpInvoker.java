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

import com.liferay.osb.service.CorpProjectLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CorpProjectLocalServiceClpInvoker {
	public CorpProjectLocalServiceClpInvoker() {
		_methodName0 = "addCorpProject";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.CorpProject"
			};

		_methodName1 = "createCorpProject";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteCorpProject";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteCorpProject";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.CorpProject"
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

		_methodName9 = "fetchCorpProject";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getCorpProject";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getCorpProjects";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getCorpProjectsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateCorpProject";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.CorpProject"
			};

		_methodName15 = "updateCorpProject";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.CorpProject", "boolean"
			};

		_methodName704 = "getBeanIdentifier";

		_methodParameterTypes704 = new String[] {  };

		_methodName705 = "setBeanIdentifier";

		_methodParameterTypes705 = new String[] { "java.lang.String" };

		_methodName710 = "addCorpProject";

		_methodParameterTypes710 = new String[] {
				"long", "long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName711 = "addCorpProjectUsers";

		_methodParameterTypes711 = new String[] { "long", "long[][]" };

		_methodName712 = "addUserCorpProjectRoles";

		_methodParameterTypes712 = new String[] {
				"long", "long", "long[][]", "long"
			};

		_methodName713 = "deleteCorpProject";

		_methodParameterTypes713 = new String[] {
				"com.liferay.osb.model.CorpProject"
			};

		_methodName714 = "deleteCorpProject";

		_methodParameterTypes714 = new String[] { "long" };

		_methodName715 = "deleteUserCorpProjectRoles";

		_methodParameterTypes715 = new String[] {
				"long", "long", "long[][]", "long"
			};

		_methodName716 = "fetchCorpProject";

		_methodParameterTypes716 = new String[] { "java.lang.String" };

		_methodName717 = "getCorpProjects";

		_methodParameterTypes717 = new String[] {
				"java.lang.String", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName718 = "getCorpProjectsCount";

		_methodParameterTypes718 = new String[] { "java.lang.String" };

		_methodName719 = "getOrganizationCorpProject";

		_methodParameterTypes719 = new String[] { "long" };

		_methodName720 = "getUserCorpProjects";

		_methodParameterTypes720 = new String[] { "long" };

		_methodName721 = "getUserCorpProjects";

		_methodParameterTypes721 = new String[] { "long", "long" };

		_methodName722 = "getUserCorpProjects";

		_methodParameterTypes722 = new String[] { "long", "java.lang.String" };

		_methodName723 = "hasUserCorpProject";

		_methodParameterTypes723 = new String[] { "long", "long" };

		_methodName724 = "hasUserCorpProjectRole";

		_methodParameterTypes724 = new String[] { "long", "long", "long" };

		_methodName725 = "unsetCorpProjectUsers";

		_methodParameterTypes725 = new String[] { "long", "long[][]" };

		_methodName726 = "updateCorpProject";

		_methodParameterTypes726 = new String[] { "long", "java.lang.String" };

		_methodName727 = "updateCorpProject";

		_methodParameterTypes727 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String", "long"
			};

		_methodName728 = "validateMembership";

		_methodParameterTypes728 = new String[] { "long", "long", "long" };

		_methodName729 = "validateSalesforceProjectKey";

		_methodParameterTypes729 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return CorpProjectLocalServiceUtil.addCorpProject((com.liferay.osb.model.CorpProject)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return CorpProjectLocalServiceUtil.createCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return CorpProjectLocalServiceUtil.deleteCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return CorpProjectLocalServiceUtil.deleteCorpProject((com.liferay.osb.model.CorpProject)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return CorpProjectLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return CorpProjectLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return CorpProjectLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return CorpProjectLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return CorpProjectLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return CorpProjectLocalServiceUtil.fetchCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getCorpProjects(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getCorpProjectsCount();
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return CorpProjectLocalServiceUtil.updateCorpProject((com.liferay.osb.model.CorpProject)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return CorpProjectLocalServiceUtil.updateCorpProject((com.liferay.osb.model.CorpProject)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName704.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes704, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName705.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes705, parameterTypes)) {
			CorpProjectLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName710.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes710, parameterTypes)) {
			return CorpProjectLocalServiceUtil.addCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.lang.String)arguments[3],
				(java.lang.String)arguments[4]);
		}

		if (_methodName711.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes711, parameterTypes)) {
			CorpProjectLocalServiceUtil.addCorpProjectUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName712.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes712, parameterTypes)) {
			CorpProjectLocalServiceUtil.addUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				((Long)arguments[3]).longValue());

			return null;
		}

		if (_methodName713.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes713, parameterTypes)) {
			return CorpProjectLocalServiceUtil.deleteCorpProject((com.liferay.osb.model.CorpProject)arguments[0]);
		}

		if (_methodName714.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes714, parameterTypes)) {
			return CorpProjectLocalServiceUtil.deleteCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName715.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes715, parameterTypes)) {
			CorpProjectLocalServiceUtil.deleteUserCorpProjectRoles(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				((Long)arguments[3]).longValue());

			return null;
		}

		if (_methodName716.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes716, parameterTypes)) {
			return CorpProjectLocalServiceUtil.fetchCorpProject((java.lang.String)arguments[0]);
		}

		if (_methodName717.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes717, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getCorpProjects((java.lang.String)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName718.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes718, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getCorpProjectsCount((java.lang.String)arguments[0]);
		}

		if (_methodName719.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes719, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getOrganizationCorpProject(((Long)arguments[0]).longValue());
		}

		if (_methodName720.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes720, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getUserCorpProjects(((Long)arguments[0]).longValue());
		}

		if (_methodName721.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes721, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getUserCorpProjects(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName722.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes722, parameterTypes)) {
			return CorpProjectLocalServiceUtil.getUserCorpProjects(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName723.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes723, parameterTypes)) {
			return CorpProjectLocalServiceUtil.hasUserCorpProject(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName724.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes724, parameterTypes)) {
			return CorpProjectLocalServiceUtil.hasUserCorpProjectRole(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName725.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes725, parameterTypes)) {
			CorpProjectLocalServiceUtil.unsetCorpProjectUsers(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName726.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes726, parameterTypes)) {
			return CorpProjectLocalServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1]);
		}

		if (_methodName727.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes727, parameterTypes)) {
			return CorpProjectLocalServiceUtil.updateCorpProject(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3], ((Long)arguments[4]).longValue());
		}

		if (_methodName728.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes728, parameterTypes)) {
			CorpProjectLocalServiceUtil.validateMembership(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());

			return null;
		}

		if (_methodName729.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes729, parameterTypes)) {
			CorpProjectLocalServiceUtil.validateSalesforceProjectKey(((Long)arguments[0]).longValue());

			return null;
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
	private String _methodName704;
	private String[] _methodParameterTypes704;
	private String _methodName705;
	private String[] _methodParameterTypes705;
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
	private String _methodName720;
	private String[] _methodParameterTypes720;
	private String _methodName721;
	private String[] _methodParameterTypes721;
	private String _methodName722;
	private String[] _methodParameterTypes722;
	private String _methodName723;
	private String[] _methodParameterTypes723;
	private String _methodName724;
	private String[] _methodParameterTypes724;
	private String _methodName725;
	private String[] _methodParameterTypes725;
	private String _methodName726;
	private String[] _methodParameterTypes726;
	private String _methodName727;
	private String[] _methodParameterTypes727;
	private String _methodName728;
	private String[] _methodParameterTypes728;
	private String _methodName729;
	private String[] _methodParameterTypes729;
}