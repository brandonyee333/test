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

import com.liferay.osb.service.SupportRegionLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SupportRegionLocalServiceClpInvoker {
	public SupportRegionLocalServiceClpInvoker() {
		_methodName0 = "addSupportRegion";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.SupportRegion"
			};

		_methodName1 = "createSupportRegion";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteSupportRegion";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteSupportRegion";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.SupportRegion"
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

		_methodName10 = "fetchSupportRegion";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getSupportRegion";

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

		_methodName17 = "getSupportRegions";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getSupportRegionsCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateSupportRegion";

		_methodParameterTypes19 = new String[] {
				"com.liferay.osb.model.SupportRegion"
			};

		_methodName20 = "addAccountEntrySupportRegion";

		_methodParameterTypes20 = new String[] { "long", "long" };

		_methodName21 = "addAccountEntrySupportRegion";

		_methodParameterTypes21 = new String[] {
				"long", "com.liferay.osb.model.SupportRegion"
			};

		_methodName22 = "addAccountEntrySupportRegions";

		_methodParameterTypes22 = new String[] { "long", "long[][]" };

		_methodName23 = "addAccountEntrySupportRegions";

		_methodParameterTypes23 = new String[] { "long", "java.util.List" };

		_methodName24 = "clearAccountEntrySupportRegions";

		_methodParameterTypes24 = new String[] { "long" };

		_methodName25 = "deleteAccountEntrySupportRegion";

		_methodParameterTypes25 = new String[] { "long", "long" };

		_methodName26 = "deleteAccountEntrySupportRegion";

		_methodParameterTypes26 = new String[] {
				"long", "com.liferay.osb.model.SupportRegion"
			};

		_methodName27 = "deleteAccountEntrySupportRegions";

		_methodParameterTypes27 = new String[] { "long", "long[][]" };

		_methodName28 = "deleteAccountEntrySupportRegions";

		_methodParameterTypes28 = new String[] { "long", "java.util.List" };

		_methodName29 = "getAccountEntryPrimaryKeys";

		_methodParameterTypes29 = new String[] { "long" };

		_methodName30 = "getAccountEntrySupportRegions";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "getAccountEntrySupportRegions";

		_methodParameterTypes31 = new String[] { "long", "int", "int" };

		_methodName32 = "getAccountEntrySupportRegions";

		_methodParameterTypes32 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName33 = "getAccountEntrySupportRegionsCount";

		_methodParameterTypes33 = new String[] { "long" };

		_methodName34 = "hasAccountEntrySupportRegion";

		_methodParameterTypes34 = new String[] { "long", "long" };

		_methodName35 = "hasAccountEntrySupportRegions";

		_methodParameterTypes35 = new String[] { "long" };

		_methodName36 = "setAccountEntrySupportRegions";

		_methodParameterTypes36 = new String[] { "long", "long[][]" };

		_methodName37 = "addPartnerEntrySupportRegion";

		_methodParameterTypes37 = new String[] { "long", "long" };

		_methodName38 = "addPartnerEntrySupportRegion";

		_methodParameterTypes38 = new String[] {
				"long", "com.liferay.osb.model.SupportRegion"
			};

		_methodName39 = "addPartnerEntrySupportRegions";

		_methodParameterTypes39 = new String[] { "long", "long[][]" };

		_methodName40 = "addPartnerEntrySupportRegions";

		_methodParameterTypes40 = new String[] { "long", "java.util.List" };

		_methodName41 = "clearPartnerEntrySupportRegions";

		_methodParameterTypes41 = new String[] { "long" };

		_methodName42 = "deletePartnerEntrySupportRegion";

		_methodParameterTypes42 = new String[] { "long", "long" };

		_methodName43 = "deletePartnerEntrySupportRegion";

		_methodParameterTypes43 = new String[] {
				"long", "com.liferay.osb.model.SupportRegion"
			};

		_methodName44 = "deletePartnerEntrySupportRegions";

		_methodParameterTypes44 = new String[] { "long", "long[][]" };

		_methodName45 = "deletePartnerEntrySupportRegions";

		_methodParameterTypes45 = new String[] { "long", "java.util.List" };

		_methodName46 = "getPartnerEntryPrimaryKeys";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "getPartnerEntrySupportRegions";

		_methodParameterTypes47 = new String[] { "long" };

		_methodName48 = "getPartnerEntrySupportRegions";

		_methodParameterTypes48 = new String[] { "long", "int", "int" };

		_methodName49 = "getPartnerEntrySupportRegions";

		_methodParameterTypes49 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName50 = "getPartnerEntrySupportRegionsCount";

		_methodParameterTypes50 = new String[] { "long" };

		_methodName51 = "hasPartnerEntrySupportRegion";

		_methodParameterTypes51 = new String[] { "long", "long" };

		_methodName52 = "hasPartnerEntrySupportRegions";

		_methodParameterTypes52 = new String[] { "long" };

		_methodName53 = "setPartnerEntrySupportRegions";

		_methodParameterTypes53 = new String[] { "long", "long[][]" };

		_methodName198 = "getOSGiServiceIdentifier";

		_methodParameterTypes198 = new String[] {  };

		_methodName203 = "addSupportRegion";

		_methodParameterTypes203 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};

		_methodName204 = "deleteSupportRegion";

		_methodParameterTypes204 = new String[] { "long" };

		_methodName205 = "fetchSupportRegionByName";

		_methodParameterTypes205 = new String[] { "java.lang.String" };

		_methodName206 = "getAccountEntrySupportRegions";

		_methodParameterTypes206 = new String[] { "long" };

		_methodName207 = "updateSupportRegion";

		_methodParameterTypes207 = new String[] {
				"long", "java.lang.String", "java.lang.String",
				"java.lang.String"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return SupportRegionLocalServiceUtil.addSupportRegion((com.liferay.osb.model.SupportRegion)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return SupportRegionLocalServiceUtil.createSupportRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return SupportRegionLocalServiceUtil.deleteSupportRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return SupportRegionLocalServiceUtil.deleteSupportRegion((com.liferay.osb.model.SupportRegion)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return SupportRegionLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return SupportRegionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return SupportRegionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return SupportRegionLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<?>)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return SupportRegionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return SupportRegionLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return SupportRegionLocalServiceUtil.fetchSupportRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getSupportRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getActionableDynamicQuery();
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getIndexableActionableDynamicQuery();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return SupportRegionLocalServiceUtil.deletePersistedModel((com.liferay.portal.kernel.model.PersistedModel)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getSupportRegions(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getSupportRegionsCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return SupportRegionLocalServiceUtil.updateSupportRegion((com.liferay.osb.model.SupportRegion)arguments[0]);
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			SupportRegionLocalServiceUtil.addAccountEntrySupportRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			SupportRegionLocalServiceUtil.addAccountEntrySupportRegion(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportRegion)arguments[1]);

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			SupportRegionLocalServiceUtil.addAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			SupportRegionLocalServiceUtil.addAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportRegion>)arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			SupportRegionLocalServiceUtil.clearAccountEntrySupportRegions(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			SupportRegionLocalServiceUtil.deleteAccountEntrySupportRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			SupportRegionLocalServiceUtil.deleteAccountEntrySupportRegion(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportRegion)arguments[1]);

			return null;
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			SupportRegionLocalServiceUtil.deleteAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			SupportRegionLocalServiceUtil.deleteAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportRegion>)arguments[1]);

			return null;
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getAccountEntryPrimaryKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(((Long)arguments[0]).longValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion>)arguments[3]);
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getAccountEntrySupportRegionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return SupportRegionLocalServiceUtil.hasAccountEntrySupportRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SupportRegionLocalServiceUtil.hasAccountEntrySupportRegions(((Long)arguments[0]).longValue());
		}

		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			SupportRegionLocalServiceUtil.setAccountEntrySupportRegions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			SupportRegionLocalServiceUtil.addPartnerEntrySupportRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			SupportRegionLocalServiceUtil.addPartnerEntrySupportRegion(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportRegion)arguments[1]);

			return null;
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			SupportRegionLocalServiceUtil.addPartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			SupportRegionLocalServiceUtil.addPartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportRegion>)arguments[1]);

			return null;
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			SupportRegionLocalServiceUtil.clearPartnerEntrySupportRegions(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName42.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes42, parameterTypes)) {
			SupportRegionLocalServiceUtil.deletePartnerEntrySupportRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName43.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes43, parameterTypes)) {
			SupportRegionLocalServiceUtil.deletePartnerEntrySupportRegion(((Long)arguments[0]).longValue(),
				(com.liferay.osb.model.SupportRegion)arguments[1]);

			return null;
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			SupportRegionLocalServiceUtil.deletePartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			SupportRegionLocalServiceUtil.deletePartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				(java.util.List<com.liferay.osb.model.SupportRegion>)arguments[1]);

			return null;
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getPartnerEntryPrimaryKeys(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getPartnerEntrySupportRegions(((Long)arguments[0]).longValue());
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getPartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getPartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion>)arguments[3]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getPartnerEntrySupportRegionsCount(((Long)arguments[0]).longValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return SupportRegionLocalServiceUtil.hasPartnerEntrySupportRegion(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return SupportRegionLocalServiceUtil.hasPartnerEntrySupportRegions(((Long)arguments[0]).longValue());
		}

		if (_methodName53.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes53, parameterTypes)) {
			SupportRegionLocalServiceUtil.setPartnerEntrySupportRegions(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName198.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes198, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName203.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes203, parameterTypes)) {
			return SupportRegionLocalServiceUtil.addSupportRegion(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
		}

		if (_methodName204.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes204, parameterTypes)) {
			return SupportRegionLocalServiceUtil.deleteSupportRegion(((Long)arguments[0]).longValue());
		}

		if (_methodName205.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes205, parameterTypes)) {
			return SupportRegionLocalServiceUtil.fetchSupportRegionByName((java.lang.String)arguments[0]);
		}

		if (_methodName206.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes206, parameterTypes)) {
			return SupportRegionLocalServiceUtil.getAccountEntrySupportRegions(((Long)arguments[0]).longValue());
		}

		if (_methodName207.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes207, parameterTypes)) {
			return SupportRegionLocalServiceUtil.updateSupportRegion(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.lang.String)arguments[3]);
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
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
	private String _methodName42;
	private String[] _methodParameterTypes42;
	private String _methodName43;
	private String[] _methodParameterTypes43;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
	private String _methodName53;
	private String[] _methodParameterTypes53;
	private String _methodName198;
	private String[] _methodParameterTypes198;
	private String _methodName203;
	private String[] _methodParameterTypes203;
	private String _methodName204;
	private String[] _methodParameterTypes204;
	private String _methodName205;
	private String[] _methodParameterTypes205;
	private String _methodName206;
	private String[] _methodParameterTypes206;
	private String _methodName207;
	private String[] _methodParameterTypes207;
}