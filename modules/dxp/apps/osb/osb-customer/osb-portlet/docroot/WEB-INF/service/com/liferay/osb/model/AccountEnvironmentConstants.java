/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.model;

import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class AccountEnvironmentConstants {

	public static final long ENV_AS_GLASSFISH_2_1 = 27016;

	public static final long ENV_AS_GLASSFISH_2_X = 27000;

	public static final long ENV_AS_GLASSFISH_3 = 27017;

	public static final long ENV_AS_GLASSFISH_3_1 = 27018;

	public static final long ENV_AS_GLASSFISH_3_X = 27001;

	public static final long ENV_AS_GLASSFISH_4_0 = 27048;

	public static final long ENV_AS_JBOSS_4_2_X = 27002;

	public static final long ENV_AS_JBOSS_5_1 = 27019;

	public static final long ENV_AS_JBOSS_5_X = 27003;

	public static final long ENV_AS_JBOSS_7_X = 27012;

	public static final long ENV_AS_JBOSS_AS_7_1 = 27020;

	public static final long ENV_AS_JBOSS_EAP_5_1 = 27050;

	public static final long ENV_AS_JBOSS_EAP_5_2 = 27042;

	public static final long ENV_AS_JBOSS_EAP_6_0 = 27040;

	public static final long ENV_AS_JBOSS_EAP_6_1 = 27049;

	public static final long ENV_AS_JBOSS_EAP_6_2 = 27051;

	public static final long ENV_AS_JBOSS_EAP_6_3 = 27052;

	public static final long ENV_AS_JBOSS_EAP_6_4 = 27053;

	public static final long ENV_AS_JBOSS_EAP_7_0 = 27058;

	public static final long ENV_AS_JBOSS_EAP_7_1 = 27061;

	public static final long ENV_AS_JBOSS_TOMCAT_4_2 = 27021;

	public static final long ENV_AS_JBOSS_TOMCAT_5_0 = 27022;

	public static final long ENV_AS_JONAS_5_1 = 27023;

	public static final long ENV_AS_JONAS_5_2 = 27024;

	public static final long ENV_AS_JONAS_TOMCAT_4_10 = 27026;

	public static final long ENV_AS_ORACLEAS_10_1 = 27027;

	public static final long ENV_AS_OTHER = 27004;

	public static final long ENV_AS_RESIN_3_1 = 27028;

	public static final long ENV_AS_RESIN_4 = 27029;

	public static final long ENV_AS_TCAT = 27011;

	public static final long ENV_AS_TCAT_6_4 = 27014;

	public static final long ENV_AS_TCAT_7_0 = 27043;

	public static final long ENV_AS_TCSERVER_2_6 = 27041;

	public static final long ENV_AS_TCSERVER_2_9 = 27044;

	public static final long ENV_AS_TCSERVER_3_1 = 27054;

	public static final long ENV_AS_TCSERVER_3_2 = 27060;

	public static final long ENV_AS_TCSERVER_4_0 = 27062;

	public static final long ENV_AS_TOMCAT_5_5 = 27030;

	public static final long ENV_AS_TOMCAT_5_5_X = 27005;

	public static final long ENV_AS_TOMCAT_6_0 = 27031;

	public static final long ENV_AS_TOMCAT_6_X = 27006;

	public static final long ENV_AS_TOMCAT_7_0 = 27039;

	public static final long ENV_AS_TOMCAT_7_0_X = 27013;

	public static final long ENV_AS_TOMCAT_8_0 = 27055;

	public static final long ENV_AS_TOMCAT_8_5 = 27059;

	public static final long ENV_AS_TOMCAT_9_0 = 27063;

	public static final long ENV_AS_WEBLOGIC_9_2 = 27032;

	public static final long ENV_AS_WEBLOGIC_9_X = 27007;

	public static final long ENV_AS_WEBLOGIC_10_0 = 27033;

	public static final long ENV_AS_WEBLOGIC_10_3 = 27034;

	public static final long ENV_AS_WEBLOGIC_10_X = 27008;

	public static final long ENV_AS_WEBLOGIC_11 = 27035;

	public static final long ENV_AS_WEBLOGIC_11_G = 27045;

	public static final long ENV_AS_WEBLOGIC_11_X = 27015;

	public static final long ENV_AS_WEBLOGIC_12C_R1 = 27046;

	public static final long ENV_AS_WEBLOGIC_12C_R2 = 27056;

	public static final long ENV_AS_WEBSPHERE_6_1 = 27036;

	public static final long ENV_AS_WEBSPHERE_6_1_X = 27009;

	public static final long ENV_AS_WEBSPHERE_7_0 = 27037;

	public static final long ENV_AS_WEBSPHERE_7_X = 27010;

	public static final long ENV_AS_WEBSPHERE_8 = 27038;

	public static final long ENV_AS_WEBSPHERE_8_5 = 27047;

	public static final long ENV_AS_WEBSPHERE_9_0 = 27064;

	public static final long ENV_AS_WILDFILY_10_0 = 27057;

	public static final long ENV_AS_WILDFILY_11_0 = 27065;

	public static final long ENV_BROWSER_ANDROID_BROWSER_4_3_PLUS = 37010;

	public static final long ENV_BROWSER_CHROME = 37000;

	public static final long ENV_BROWSER_EDGE = 37013;

	public static final long ENV_BROWSER_FIREFOX = 37001;

	public static final long ENV_BROWSER_FIREFOX_ESR_45 = 37014;

	public static final long ENV_BROWSER_FIREFOX_ESR_52 = 37019;

	public static final long ENV_BROWSER_IE_6 = 37002;

	public static final long ENV_BROWSER_IE_7 = 37003;

	public static final long ENV_BROWSER_IE_8 = 37004;

	public static final long ENV_BROWSER_IE_9 = 37005;

	public static final long ENV_BROWSER_IE_10 = 37006;

	public static final long ENV_BROWSER_IE_11 = 37009;

	public static final long ENV_BROWSER_IOS_SAFARI = 37012;

	public static final long ENV_BROWSER_MOBILE_CHROME = 37017;

	public static final long ENV_BROWSER_OTHER = 37999;

	public static final long ENV_BROWSER_SAFARI_5 = 37007;

	public static final long ENV_BROWSER_SAFARI_6 = 37008;

	public static final long ENV_BROWSER_SAFARI_8 = 37016;

	public static final long ENV_BROWSER_SAFARI_9 = 37015;

	public static final long ENV_BROWSER_SAFARI_10 = 37018;

	public static final long ENV_BROWSER_SAFARI_11 = 37020;

	public static final long ENV_CS_AWS_AURORA = 39006;

	public static final long ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD = 39000;

	public static final long ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE = 39001;

	public static final long ENV_CS_AWS_S3 = 39002;

	public static final long ENV_CS_AZURE_FILES = 39003;

	public static final long ENV_CS_AZURE_SQL_DATABASES = 39004;

	public static final long ENV_CS_AZURE_VIRTUAL_MACHINES = 39005;

	public static final long ENV_DB_AMAZON_AURORA = 28041;

	public static final long ENV_DB_DB2_8_1 = 28016;

	public static final long ENV_DB_DB2_8_2 = 28000;

	public static final long ENV_DB_DB2_9_7 = 28013;

	public static final long ENV_DB_DB2_10_1 = 28024;

	public static final long ENV_DB_DB2_10_5 = 28038;

	public static final long ENV_DB_DB2_11_1 = 28042;

	public static final long ENV_DB_HYPERSONIC = 28002;

	public static final long ENV_DB_MARIADB_10 = 28033;

	public static final long ENV_DB_MARIADB_10_2 = 28043;

	public static final long ENV_DB_MYSQL_5_0 = 28003;

	public static final long ENV_DB_MYSQL_5_1 = 28004;

	public static final long ENV_DB_MYSQL_5_5 = 28014;

	public static final long ENV_DB_MYSQL_5_6 = 28025;

	public static final long ENV_DB_MYSQL_5_7 = 28036;

	public static final long ENV_DB_ORACLE_9I = 28005;

	public static final long ENV_DB_ORACLE_10G = 28006;

	public static final long ENV_DB_ORACLE_10G_RELEASE_2 = 28017;

	public static final long ENV_DB_ORACLE_11G = 28007;

	public static final long ENV_DB_ORACLE_11G_RELEASE_1 = 28018;

	public static final long ENV_DB_ORACLE_11G_RELEASE_2 = 28019;

	public static final long ENV_DB_ORACLE_12C_RELEASE_1 = 28030;

	public static final long ENV_DB_ORACLE_12C_RELEASE_2 = 28040;

	public static final long ENV_DB_OTHER = 28008;

	public static final long ENV_DB_POSTGRESQL = 28009;

	public static final long ENV_DB_POSTGRESQL_8_4 = 28020;

	public static final long ENV_DB_POSTGRESQL_9_0 = 28021;

	public static final long ENV_DB_POSTGRESQL_9_1 = 28026;

	public static final long ENV_DB_POSTGRESQL_9_2 = 28027;

	public static final long ENV_DB_POSTGRESQL_9_3 = 28031;

	public static final long ENV_DB_POSTGRESQL_9_4 = 28034;

	public static final long ENV_DB_POSTGRESQL_9_6 = 28039;

	public static final long ENV_DB_POSTGRESQL_10 = 28044;

	public static final long ENV_DB_SQL_SERVER_2000 = 28010;

	public static final long ENV_DB_SQL_SERVER_2005 = 28011;

	public static final long ENV_DB_SQL_SERVER_2008 = 28012;

	public static final long ENV_DB_SQL_SERVER_2008_R2 = 28022;

	public static final long ENV_DB_SQL_SERVER_2012 = 28028;

	public static final long ENV_DB_SQL_SERVER_2014 = 28032;

	public static final long ENV_DB_SQL_SERVER_2016 = 28037;

	public static final long ENV_DB_SYBASE_ASE_15_0 = 28023;

	public static final long ENV_DB_SYBASE_ASE_15_5 = 28015;

	public static final long ENV_DB_SYBASE_ASE_15_7 = 28029;

	public static final long ENV_DB_SYBASE_ASE_16 = 28035;

	public static final long ENV_JVM_IBM_JDK_6 = 29003;

	public static final long ENV_JVM_IBM_JDK_7 = 29004;

	public static final long ENV_JVM_IBM_JDK_8 = 29007;

	public static final long ENV_JVM_JROCKET_JDK_6 = 29005;

	public static final long ENV_JVM_OPENJDK_8 = 29008;

	public static final long ENV_JVM_ORACLE_SUN_JDK_5 = 29000;

	public static final long ENV_JVM_ORACLE_SUN_JDK_6 = 29001;

	public static final long ENV_JVM_ORACLE_SUN_JDK_7 = 29002;

	public static final long ENV_JVM_ORACLE_SUN_JDK_8 = 29006;

	public static final long ENV_OS_AIX = 30044;

	public static final long ENV_OS_AIX_6_1 = 30010;

	public static final long ENV_OS_AIX_7_1 = 30011;

	public static final long ENV_OS_CENTOS_4 = 30012;

	public static final long ENV_OS_CENTOS_5 = 30013;

	public static final long ENV_OS_CENTOS_6 = 30014;

	public static final long ENV_OS_CENTOS_7 = 30036;

	public static final long ENV_OS_DEBIAN_6_0 = 30034;

	public static final long ENV_OS_DEBIAN_7 = 30041;

	public static final long ENV_OS_DEBIAN_8 = 30037;

	public static final long ENV_OS_DEBIAN_9 = 30044;

	public static final long ENV_OS_HP_UX = 30015;

	public static final long ENV_OS_LINUX = 30000;

	public static final long ENV_OS_MAC_OS_X = 30001;

	public static final long ENV_OS_MAC_OS_X_10_5_PLUS = 30024;

	public static final long ENV_OS_OPENSUSE_13_1 = 30032;

	public static final long ENV_OS_ORACLE_LINUX = 30016;

	public static final long ENV_OS_ORACLE_LINUX_6 = 30017;

	public static final long ENV_OS_ORACLE_LINUX_7 = 30038;

	public static final long ENV_OS_OTHER = 30006;

	public static final long ENV_OS_OTHER_LINUX_DISTIRBUTIONS = 30018;

	public static final long ENV_OS_RED_HAT = 30007;

	public static final long ENV_OS_RED_HAT_ENTERPRISE_4 = 30019;

	public static final long ENV_OS_RED_HAT_ENTERPRISE_5 = 30020;

	public static final long ENV_OS_RED_HAT_ENTERPRISE_6 = 30021;

	public static final long ENV_OS_RED_HAT_ENTERPRISE_7 = 30035;

	public static final long ENV_OS_SOLARIS_10 = 30022;

	public static final long ENV_OS_SOLARIS_11 = 30031;

	public static final long ENV_OS_SOLARIS_EXPRESS_11 = 30025;

	public static final long ENV_OS_SUSE_ENTERPRISE_LINUX_11 = 30033;

	public static final long ENV_OS_SUSE_ENTERPRISE_LINUX_12 = 30039;

	public static final long ENV_OS_UBUNTU = 30023;

	public static final long ENV_OS_UBUNTU_10 = 30026;

	public static final long ENV_OS_UBUNTU_11 = 30027;

	public static final long ENV_OS_UBUNTU_12 = 30028;

	public static final long ENV_OS_UBUNTU_13 = 30029;

	public static final long ENV_OS_UBUNTU_LTS_14_04 = 30040;

	public static final long ENV_OS_UBUNTU_LTS_16_04 = 30042;

	public static final long ENV_OS_WINDOWS_7 = 30002;

	public static final long ENV_OS_WINDOWS_SERVER_2003 = 30004;

	public static final long ENV_OS_WINDOWS_SERVER_2008 = 30005;

	public static final long ENV_OS_WINDOWS_SERVER_2008_R2 = 30008;

	public static final long ENV_OS_WINDOWS_SERVER_2012 = 30030;

	public static final long ENV_OS_WINDOWS_SERVER_2016 = 30043;

	public static final long ENV_OS_WINDOWS_XP = 30003;

	public static final long ENV_SEARCH_ELASTICSEARCH_2_X = 40000;

	public static final long ENV_SEARCH_ELASTICSEARCH_6_X = 40006;

	public static final long ENV_SEARCH_KIBANA_4_X = 40001;

	public static final long ENV_SEARCH_KIBANA_6_X = 40007;

	public static final long ENV_SEARCH_MARVEL_2_X = 40002;

	public static final long ENV_SEARCH_SHIELD_2_X = 40003;

	public static final long ENV_SEARCH_SOLR = 40004;

	public static final long ENV_SEARCH_SOLR_5 = 40009;

	public static final long ENV_SEARCH_SOLRCLOUD = 40005;

	public static final long ENV_SEARCH_X_PACK_6_X = 40008;

	public static final String LIST_TYPE_ENV_AS =
		AccountEnvironment.class.getName() + ".envAS";

	public static final String LIST_TYPE_ENV_BROWSER =
		AccountEnvironment.class.getName() + ".envBrowser";

	public static final String LIST_TYPE_ENV_CS =
		AccountEnvironment.class.getName() + ".envCS";

	public static final String LIST_TYPE_ENV_DB =
		AccountEnvironment.class.getName() + ".envDB";

	public static final String LIST_TYPE_ENV_JVM =
		AccountEnvironment.class.getName() + ".envJVM";

	public static final String LIST_TYPE_ENV_OS =
		AccountEnvironment.class.getName() + ".envOS";

	public static final String LIST_TYPE_ENV_SEARCH =
		AccountEnvironment.class.getName() + ".envSearch";

	public static final String NOT_AVAILABLE = "N/A";

	public static String getEnvLabel(long envType) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(envType);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

	public static long[] getEnvListTypeIds(long envLFR, String envListType) {
		if (ProductEntryConstants.isPortalVersion5_2(envLFR)) {
			return _envPortalVersion52.get(envListType);
		}
		else if (ProductEntryConstants.isPortalVersion6_0(envLFR)) {
			return _envPortalVersion60.get(envListType);
		}
		else if (ProductEntryConstants.isPortalVersion6_1(envLFR)) {
			return _envPortalVersion61.get(envListType);
		}
		else if (ProductEntryConstants.isPortalVersion6_2(envLFR)) {
			return _envPortalVersion62.get(envListType);
		}
		else if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {
			return _envDigitalEnterpriseVersion70.get(envListType);
		}
		else if (ProductEntryConstants.isDigitalEnterpriseVersion7_1(envLFR)) {
			return _envDigitalEnterpriseVersion71.get(envListType);
		}
		else if ((envLFR >=
					 ProductEntryConstants.SOCIAL_OFFICE_VERSION_2_0_3) &&
				 (envLFR <=
					 ProductEntryConstants.SOCIAL_OFFICE_VERSION_2_1_0)) {

			return _envSocialOfficeVersion2.get(envListType);
		}
		else if ((envLFR >=
					 ProductEntryConstants.SOCIAL_OFFICE_VERSION_3_0_0) &&
				 (envLFR <=
					 ProductEntryConstants.SOCIAL_OFFICE_VERSION_3_1_1)) {

			return _envSocialOfficeVersion3.get(envListType);
		}
		else {
			return _envPortalVersionOther.get(envListType);
		}
	}

	private static final long[] _ENV_AS_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_AS_JBOSS_EAP_6_4, ENV_AS_JBOSS_EAP_7_0, ENV_AS_TCSERVER_3_1,
		ENV_AS_TCSERVER_3_2, ENV_AS_TOMCAT_8_0, ENV_AS_TOMCAT_8_5,
		ENV_AS_WEBLOGIC_12C_R1, ENV_AS_WEBLOGIC_12C_R2, ENV_AS_WEBSPHERE_8_5,
		ENV_AS_WILDFILY_10_0
	};

	private static final long[] _ENV_AS_DIGITAL_ENTERPRISE_VERSION_7_1 = {
		ENV_AS_JBOSS_EAP_7_1, ENV_AS_TCSERVER_4_0, ENV_AS_TOMCAT_9_0,
		ENV_AS_WEBLOGIC_12C_R2, ENV_AS_WEBSPHERE_8_5, ENV_AS_WEBSPHERE_9_0,
		ENV_AS_WILDFILY_10_0, ENV_AS_WILDFILY_11_0
	};

	private static final long[] _ENV_AS_PORTAL_VERSION_5_2 = {
		ENV_AS_GLASSFISH_2_1, ENV_AS_JBOSS_TOMCAT_4_2, ENV_AS_JBOSS_TOMCAT_5_0,
		ENV_AS_JONAS_TOMCAT_4_10, ENV_AS_ORACLEAS_10_1, ENV_AS_RESIN_3_1,
		ENV_AS_TOMCAT_5_5, ENV_AS_TOMCAT_6_0, ENV_AS_WEBLOGIC_9_2,
		ENV_AS_WEBLOGIC_10_0, ENV_AS_WEBLOGIC_10_3, ENV_AS_WEBSPHERE_6_1,
		ENV_AS_WEBSPHERE_7_0
	};

	private static final long[] _ENV_AS_PORTAL_VERSION_6_0 = {
		ENV_AS_GLASSFISH_3, ENV_AS_JBOSS_5_1, ENV_AS_JONAS_5_1,
		ENV_AS_RESIN_3_1, ENV_AS_TOMCAT_6_0, ENV_AS_WEBLOGIC_10_0,
		ENV_AS_WEBLOGIC_10_3, ENV_AS_WEBSPHERE_6_1, ENV_AS_WEBSPHERE_7_0
	};

	private static final long[] _ENV_AS_PORTAL_VERSION_6_1 = {
		ENV_AS_GLASSFISH_3_1, ENV_AS_JBOSS_AS_7_1, ENV_AS_JBOSS_EAP_5_1,
		ENV_AS_JBOSS_EAP_6_0, ENV_AS_JONAS_5_2, ENV_AS_RESIN_4, ENV_AS_TCAT_6_4,
		ENV_AS_TCSERVER_2_6, ENV_AS_TOMCAT_6_0, ENV_AS_TOMCAT_7_0,
		ENV_AS_TOMCAT_8_0, ENV_AS_WEBLOGIC_10_0, ENV_AS_WEBLOGIC_10_3,
		ENV_AS_WEBLOGIC_11, ENV_AS_WEBSPHERE_6_1, ENV_AS_WEBSPHERE_7_0,
		ENV_AS_WEBSPHERE_8, ENV_AS_WEBSPHERE_8_5
	};

	private static final long[] _ENV_AS_PORTAL_VERSION_6_2 = {
		ENV_AS_GLASSFISH_3_1, ENV_AS_GLASSFISH_4_0, ENV_AS_JBOSS_AS_7_1,
		ENV_AS_JBOSS_EAP_5_2, ENV_AS_JBOSS_EAP_6_0, ENV_AS_JBOSS_EAP_6_1,
		ENV_AS_JBOSS_EAP_6_2, ENV_AS_JBOSS_EAP_6_3, ENV_AS_JBOSS_EAP_6_4,
		ENV_AS_JBOSS_EAP_7_0, ENV_AS_RESIN_4, ENV_AS_TCAT_6_4, ENV_AS_TCAT_7_0,
		ENV_AS_TCSERVER_2_6, ENV_AS_TCSERVER_2_9, ENV_AS_TCSERVER_3_1,
		ENV_AS_TOMCAT_6_0, ENV_AS_TOMCAT_7_0, ENV_AS_TOMCAT_8_0,
		ENV_AS_TOMCAT_8_5, ENV_AS_WEBLOGIC_11_G, ENV_AS_WEBLOGIC_12C_R1,
		ENV_AS_WEBSPHERE_7_0, ENV_AS_WEBSPHERE_8, ENV_AS_WEBSPHERE_8_5
	};

	private static final long[] _ENV_AS_PORTAL_VERSION_OTHER = {
		ENV_AS_GLASSFISH_2_X, ENV_AS_GLASSFISH_3_X, ENV_AS_JBOSS_4_2_X,
		ENV_AS_JBOSS_5_X, ENV_AS_JBOSS_7_X, ENV_AS_JBOSS_EAP_6_0,
		ENV_AS_JBOSS_EAP_6_1, ENV_AS_JBOSS_EAP_6_2, ENV_AS_TCAT,
		ENV_AS_TCAT_6_4, ENV_AS_TOMCAT_5_5_X, ENV_AS_TOMCAT_6_X,
		ENV_AS_TOMCAT_7_0_X, ENV_AS_WEBLOGIC_10_X, ENV_AS_WEBLOGIC_11_X,
		ENV_AS_WEBLOGIC_9_X, ENV_AS_WEBSPHERE_6_1_X, ENV_AS_WEBSPHERE_7_X
	};

	private static final long[] _ENV_BROWSER_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_BROWSER_CHROME, ENV_BROWSER_EDGE, ENV_BROWSER_FIREFOX,
		ENV_BROWSER_FIREFOX_ESR_45, ENV_BROWSER_IE_9, ENV_BROWSER_IE_10,
		ENV_BROWSER_IE_11, ENV_BROWSER_IOS_SAFARI, ENV_BROWSER_MOBILE_CHROME,
		ENV_BROWSER_SAFARI_8, ENV_BROWSER_SAFARI_9, ENV_BROWSER_SAFARI_10
	};

	private static final long[] _ENV_BROWSER_DIGITAL_ENTERPRISE_VERSION_7_1 = {
		ENV_BROWSER_CHROME, ENV_BROWSER_EDGE, ENV_BROWSER_FIREFOX_ESR_52,
		ENV_BROWSER_IE_11, ENV_BROWSER_IOS_SAFARI, ENV_BROWSER_MOBILE_CHROME,
		ENV_BROWSER_SAFARI_11
	};

	private static final long[] _ENV_BROWSER_PORTAL_VERSION_6_2 = {
		ENV_BROWSER_ANDROID_BROWSER_4_3_PLUS, ENV_BROWSER_CHROME,
		ENV_BROWSER_EDGE, ENV_BROWSER_FIREFOX, ENV_BROWSER_IE_6,
		ENV_BROWSER_IE_7, ENV_BROWSER_IE_8, ENV_BROWSER_IE_9, ENV_BROWSER_IE_10,
		ENV_BROWSER_IE_11, ENV_BROWSER_IOS_SAFARI, ENV_BROWSER_OTHER,
		ENV_BROWSER_SAFARI_5, ENV_BROWSER_SAFARI_6, ENV_BROWSER_SAFARI_10
	};

	private static final long[] _ENV_BROWSER_PORTAL_VERSION_OTHER = {
		ENV_BROWSER_CHROME, ENV_BROWSER_FIREFOX, ENV_BROWSER_IE_6,
		ENV_BROWSER_IE_7, ENV_BROWSER_IE_8, ENV_BROWSER_IE_9, ENV_BROWSER_IE_10,
		ENV_BROWSER_OTHER, ENV_BROWSER_SAFARI_5, ENV_BROWSER_SAFARI_6
	};

	private static final long[] _ENV_CS_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_CS_AWS_AURORA, ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD,
		ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE, ENV_CS_AWS_S3,
		ENV_CS_AZURE_FILES, ENV_CS_AZURE_SQL_DATABASES,
		ENV_CS_AZURE_VIRTUAL_MACHINES
	};

	private static final long[] _ENV_CS_DIGITAL_ENTERPRISE_VERSION_7_1 = {
		ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD,
		ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE, ENV_CS_AWS_S3,
		ENV_CS_AZURE_FILES, ENV_CS_AZURE_SQL_DATABASES,
		ENV_CS_AZURE_VIRTUAL_MACHINES
	};

	private static final long[] _ENV_CS_PORTAL_VERSION_6_2 = {
		ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD,
		ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE, ENV_CS_AWS_S3,
		ENV_CS_AZURE_FILES, ENV_CS_AZURE_SQL_DATABASES,
		ENV_CS_AZURE_VIRTUAL_MACHINES
	};

	private static final long[] _ENV_DB_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_DB_DB2_9_7, ENV_DB_DB2_10_1, ENV_DB_DB2_10_5, ENV_DB_MARIADB_10,
		ENV_DB_MYSQL_5_6, ENV_DB_MYSQL_5_7, ENV_DB_ORACLE_11G_RELEASE_2,
		ENV_DB_ORACLE_12C_RELEASE_1, ENV_DB_ORACLE_12C_RELEASE_2,
		ENV_DB_POSTGRESQL_9_3, ENV_DB_POSTGRESQL_9_4, ENV_DB_POSTGRESQL_9_6,
		ENV_DB_POSTGRESQL_10, ENV_DB_SQL_SERVER_2008, ENV_DB_SQL_SERVER_2008_R2,
		ENV_DB_SQL_SERVER_2012, ENV_DB_SQL_SERVER_2014, ENV_DB_SQL_SERVER_2016,
		ENV_DB_SYBASE_ASE_15_7, ENV_DB_SYBASE_ASE_16
	};

	private static final long[] _ENV_DB_DIGITAL_ENTERPRISE_VERSION_7_1 = {
		ENV_DB_AMAZON_AURORA, ENV_DB_DB2_10_5, ENV_DB_DB2_11_1,
		ENV_DB_MARIADB_10_2, ENV_DB_MYSQL_5_7, ENV_DB_ORACLE_12C_RELEASE_2,
		ENV_DB_POSTGRESQL_9_6, ENV_DB_POSTGRESQL_10, ENV_DB_SQL_SERVER_2016,
		ENV_DB_SYBASE_ASE_16
	};

	private static final long[] _ENV_DB_PORTAL_VERSION_5_2 = {
		ENV_DB_DB2_8_1, ENV_DB_DB2_9_7, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_POSTGRESQL_8_4,
		ENV_DB_SQL_SERVER_2000, ENV_DB_SQL_SERVER_2005, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SYBASE_ASE_15_0
	};

	private static final long[] _ENV_DB_PORTAL_VERSION_6_0 = {
		ENV_DB_DB2_8_1, ENV_DB_DB2_9_7, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_POSTGRESQL_8_4,
		ENV_DB_SQL_SERVER_2000, ENV_DB_SQL_SERVER_2005, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SYBASE_ASE_15_0
	};

	private static final long[] _ENV_DB_PORTAL_VERSION_6_1 = {
		ENV_DB_DB2_9_7, ENV_DB_DB2_10_5, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_MYSQL_5_5, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_ORACLE_11G_RELEASE_2,
		ENV_DB_POSTGRESQL_8_4, ENV_DB_POSTGRESQL_9_0, ENV_DB_SQL_SERVER_2005,
		ENV_DB_SQL_SERVER_2008, ENV_DB_SQL_SERVER_2008_R2,
		ENV_DB_SYBASE_ASE_15_5
	};

	private static final long[] _ENV_DB_PORTAL_VERSION_6_2 = {
		ENV_DB_DB2_9_7, ENV_DB_DB2_10_1, ENV_DB_DB2_10_5, ENV_DB_MARIADB_10,
		ENV_DB_MYSQL_5_5, ENV_DB_MYSQL_5_6, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_ORACLE_11G_RELEASE_2,
		ENV_DB_ORACLE_12C_RELEASE_1, ENV_DB_POSTGRESQL_8_4,
		ENV_DB_POSTGRESQL_9_0, ENV_DB_POSTGRESQL_9_1, ENV_DB_POSTGRESQL_9_2,
		ENV_DB_POSTGRESQL_9_3, ENV_DB_POSTGRESQL_9_4, ENV_DB_SQL_SERVER_2005,
		ENV_DB_SQL_SERVER_2008, ENV_DB_SQL_SERVER_2008_R2,
		ENV_DB_SQL_SERVER_2012, ENV_DB_SQL_SERVER_2014, ENV_DB_SYBASE_ASE_15_5,
		ENV_DB_SYBASE_ASE_15_7
	};

	private static final long[] _ENV_DB_PORTAL_VERSION_OTHER = {
		ENV_DB_DB2_8_2, ENV_DB_DB2_9_7, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_MYSQL_5_5, ENV_DB_ORACLE_10G,
		ENV_DB_ORACLE_11G, ENV_DB_ORACLE_9I, ENV_DB_POSTGRESQL,
		ENV_DB_SQL_SERVER_2000, ENV_DB_SQL_SERVER_2005, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SYBASE_ASE_15_5
	};

	private static final long[] _ENV_JVM_DIGITAL_ENTERPRISE_VERSION_7_0 =
		{ENV_JVM_IBM_JDK_8, ENV_JVM_ORACLE_SUN_JDK_8, ENV_JVM_OPENJDK_8};

	private static final long[] _ENV_JVM_DIGITAL_ENTERPRISE_VERSION_7_1 =
		{ENV_JVM_IBM_JDK_8, ENV_JVM_ORACLE_SUN_JDK_8, ENV_JVM_OPENJDK_8};

	private static final long[] _ENV_JVM_PORTAL_VERSION_6_1 = {
		ENV_JVM_ORACLE_SUN_JDK_5, ENV_JVM_ORACLE_SUN_JDK_6,
		ENV_JVM_ORACLE_SUN_JDK_7, ENV_JVM_ORACLE_SUN_JDK_8
	};

	private static final long[] _ENV_JVM_PORTAL_VERSION_6_2 = {
		ENV_JVM_IBM_JDK_6, ENV_JVM_IBM_JDK_7, ENV_JVM_IBM_JDK_8,
		ENV_JVM_ORACLE_SUN_JDK_6, ENV_JVM_ORACLE_SUN_JDK_7,
		ENV_JVM_ORACLE_SUN_JDK_8, ENV_JVM_JROCKET_JDK_6
	};

	private static final long[] _ENV_JVM_PORTAL_VERSION_OTHER = {
		ENV_JVM_ORACLE_SUN_JDK_5, ENV_JVM_ORACLE_SUN_JDK_6,
		ENV_JVM_ORACLE_SUN_JDK_7
	};

	private static final long[] _ENV_OS_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_OS_AIX_7_1, ENV_OS_CENTOS_6, ENV_OS_CENTOS_7, ENV_OS_DEBIAN_7,
		ENV_OS_DEBIAN_8, ENV_OS_DEBIAN_9, ENV_OS_ORACLE_LINUX_6,
		ENV_OS_ORACLE_LINUX_7, ENV_OS_RED_HAT_ENTERPRISE_6,
		ENV_OS_RED_HAT_ENTERPRISE_7, ENV_OS_SOLARIS_10, ENV_OS_SOLARIS_11,
		ENV_OS_SUSE_ENTERPRISE_LINUX_11, ENV_OS_SUSE_ENTERPRISE_LINUX_12,
		ENV_OS_UBUNTU_LTS_14_04, ENV_OS_UBUNTU_LTS_16_04,
		ENV_OS_WINDOWS_SERVER_2012, ENV_OS_WINDOWS_SERVER_2016
	};

	private static final long[] _ENV_OS_DIGITAL_ENTERPRISE_VERSION_7_1 = {
		ENV_OS_AIX_7_1, ENV_OS_CENTOS_7, ENV_OS_DEBIAN_9, ENV_OS_ORACLE_LINUX_6,
		ENV_OS_ORACLE_LINUX_7, ENV_OS_RED_HAT_ENTERPRISE_7, ENV_OS_SOLARIS_11,
		ENV_OS_SUSE_ENTERPRISE_LINUX_12, ENV_OS_UBUNTU_LTS_16_04,
		ENV_OS_WINDOWS_SERVER_2016
	};

	private static final long[] _ENV_OS_PORTAL_VERSION_5_2 = {
		ENV_OS_AIX, ENV_OS_CENTOS_4, ENV_OS_CENTOS_5, ENV_OS_HP_UX,
		ENV_OS_ORACLE_LINUX, ENV_OS_OTHER, ENV_OS_OTHER_LINUX_DISTIRBUTIONS,
		ENV_OS_RED_HAT_ENTERPRISE_4, ENV_OS_RED_HAT_ENTERPRISE_5,
		ENV_OS_SOLARIS_10, ENV_OS_UBUNTU, ENV_OS_WINDOWS_7,
		ENV_OS_WINDOWS_SERVER_2003, ENV_OS_WINDOWS_SERVER_2008,
		ENV_OS_WINDOWS_XP
	};

	private static final long[] _ENV_OS_PORTAL_VERSION_6_0 = {
		ENV_OS_AIX, ENV_OS_CENTOS_4, ENV_OS_CENTOS_5, ENV_OS_HP_UX,
		ENV_OS_ORACLE_LINUX, ENV_OS_OTHER, ENV_OS_OTHER_LINUX_DISTIRBUTIONS,
		ENV_OS_RED_HAT_ENTERPRISE_4, ENV_OS_RED_HAT_ENTERPRISE_5,
		ENV_OS_SOLARIS_10, ENV_OS_UBUNTU, ENV_OS_WINDOWS_7,
		ENV_OS_WINDOWS_SERVER_2003, ENV_OS_WINDOWS_SERVER_2008,
		ENV_OS_WINDOWS_XP
	};

	private static final long[] _ENV_OS_PORTAL_VERSION_6_1 = {
		ENV_OS_AIX_6_1, ENV_OS_AIX_7_1, ENV_OS_CENTOS_5, ENV_OS_CENTOS_6,
		ENV_OS_HP_UX, ENV_OS_MAC_OS_X_10_5_PLUS, ENV_OS_ORACLE_LINUX_6,
		ENV_OS_OTHER, ENV_OS_OTHER_LINUX_DISTIRBUTIONS,
		ENV_OS_RED_HAT_ENTERPRISE_5, ENV_OS_RED_HAT_ENTERPRISE_6,
		ENV_OS_SOLARIS_10, ENV_OS_SOLARIS_11, ENV_OS_UBUNTU_10,
		ENV_OS_UBUNTU_11, ENV_OS_WINDOWS_7, ENV_OS_WINDOWS_SERVER_2008_R2,
		ENV_OS_WINDOWS_XP
	};

	private static final long[] _ENV_OS_PORTAL_VERSION_6_2 = {
		ENV_OS_AIX_6_1, ENV_OS_AIX_7_1, ENV_OS_CENTOS_5, ENV_OS_CENTOS_6,
		ENV_OS_CENTOS_7, ENV_OS_DEBIAN_6_0, ENV_OS_DEBIAN_8, ENV_OS_HP_UX,
		ENV_OS_OPENSUSE_13_1, ENV_OS_ORACLE_LINUX_6, ENV_OS_OTHER,
		ENV_OS_RED_HAT_ENTERPRISE_5, ENV_OS_RED_HAT_ENTERPRISE_6,
		ENV_OS_RED_HAT_ENTERPRISE_7, ENV_OS_SOLARIS_10, ENV_OS_SOLARIS_11,
		ENV_OS_SUSE_ENTERPRISE_LINUX_11, ENV_OS_UBUNTU_10, ENV_OS_UBUNTU_11,
		ENV_OS_UBUNTU_12, ENV_OS_UBUNTU_13, ENV_OS_UBUNTU_LTS_14_04,
		ENV_OS_WINDOWS_SERVER_2008, ENV_OS_WINDOWS_SERVER_2012
	};

	private static final long[] _ENV_OS_PORTAL_VERSION_OTHER = {
		ENV_OS_LINUX, ENV_OS_MAC_OS_X, ENV_OS_OTHER, ENV_OS_WINDOWS_7,
		ENV_OS_WINDOWS_XP, ENV_OS_WINDOWS_SERVER_2003,
		ENV_OS_WINDOWS_SERVER_2008, ENV_OS_RED_HAT,
		ENV_OS_WINDOWS_SERVER_2008_R2
	};

	private static final long[]
		_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_0_ENTERPRISE = {
			ENV_SEARCH_ELASTICSEARCH_2_X, ENV_SEARCH_ELASTICSEARCH_6_X,
			ENV_SEARCH_KIBANA_4_X, ENV_SEARCH_KIBANA_6_X, ENV_SEARCH_MARVEL_2_X,
			ENV_SEARCH_SHIELD_2_X, ENV_SEARCH_X_PACK_6_X
		};

	private static final long[]
		_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_0_STANDARD = {
			ENV_SEARCH_ELASTICSEARCH_2_X, ENV_SEARCH_ELASTICSEARCH_6_X,
			ENV_SEARCH_SOLR, ENV_SEARCH_SOLRCLOUD
		};

	private static final long[]
		_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_1_ENTERPRISE = {
			ENV_SEARCH_ELASTICSEARCH_6_X, ENV_SEARCH_KIBANA_6_X,
			ENV_SEARCH_X_PACK_6_X
		};

	private static final long[]
		_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_1_STANDARD =
			{ENV_SEARCH_ELASTICSEARCH_6_X, ENV_SEARCH_SOLR_5};

	private static final Map<String, long[]> _envDigitalEnterpriseVersion70 =
		new HashMap<>();
	private static final Map<String, long[]> _envDigitalEnterpriseVersion71 =
		new HashMap<>();
	private static final Map<String, long[]> _envPortalVersion52 =
		new HashMap<>();
	private static final Map<String, long[]> _envPortalVersion60 =
		new HashMap<>();
	private static final Map<String, long[]> _envPortalVersion61 =
		new HashMap<>();
	private static final Map<String, long[]> _envPortalVersion62 =
		new HashMap<>();
	private static final Map<String, long[]> _envPortalVersionOther =
		new HashMap<>();
	private static final Map<String, long[]> _envSocialOfficeVersion2 =
		new HashMap<>();
	private static final Map<String, long[]> _envSocialOfficeVersion3 =
		new HashMap<>();

	static {
		_envPortalVersion52.put(LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_5_2);
		_envPortalVersion52.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_envPortalVersion52.put(LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_5_2);
		_envPortalVersion52.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_envPortalVersion52.put(LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_5_2);

		_envPortalVersion60.put(LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_0);
		_envPortalVersion60.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_envPortalVersion60.put(LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_0);
		_envPortalVersion60.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_envPortalVersion60.put(LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_0);

		_envPortalVersion61.put(LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_1);
		_envPortalVersion61.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_envPortalVersion61.put(LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_1);
		_envPortalVersion61.put(LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_6_1);
		_envPortalVersion61.put(LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_1);

		_envPortalVersion62.put(LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_2);
		_envPortalVersion62.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_6_2);
		_envPortalVersion62.put(LIST_TYPE_ENV_CS, _ENV_CS_PORTAL_VERSION_6_2);
		_envPortalVersion62.put(LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_2);
		_envPortalVersion62.put(LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_6_2);
		_envPortalVersion62.put(LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_2);

		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_AS, _ENV_AS_DIGITAL_ENTERPRISE_VERSION_7_0);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_DIGITAL_ENTERPRISE_VERSION_7_0);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_CS, _ENV_CS_DIGITAL_ENTERPRISE_VERSION_7_0);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_DB, _ENV_DB_DIGITAL_ENTERPRISE_VERSION_7_0);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_DIGITAL_ENTERPRISE_VERSION_7_0);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_OS, _ENV_OS_DIGITAL_ENTERPRISE_VERSION_7_0);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_SEARCH + ".enterprise",
			_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_0_ENTERPRISE);
		_envDigitalEnterpriseVersion70.put(
			LIST_TYPE_ENV_SEARCH + ".standard",
			_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_0_STANDARD);

		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_AS, _ENV_AS_DIGITAL_ENTERPRISE_VERSION_7_1);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_DIGITAL_ENTERPRISE_VERSION_7_1);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_CS, _ENV_CS_DIGITAL_ENTERPRISE_VERSION_7_1);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_DB, _ENV_DB_DIGITAL_ENTERPRISE_VERSION_7_1);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_DIGITAL_ENTERPRISE_VERSION_7_1);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_OS, _ENV_OS_DIGITAL_ENTERPRISE_VERSION_7_1);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_SEARCH + ".enterprise",
			_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_1_ENTERPRISE);
		_envDigitalEnterpriseVersion71.put(
			LIST_TYPE_ENV_SEARCH + ".standard",
			_ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_1_STANDARD);

		_envPortalVersionOther.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_OTHER);
		_envPortalVersionOther.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_envPortalVersionOther.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_OTHER);
		_envPortalVersionOther.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_envPortalVersionOther.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_OTHER);

		_envSocialOfficeVersion2.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_1);
		_envSocialOfficeVersion2.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_envSocialOfficeVersion2.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_1);
		_envSocialOfficeVersion2.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_envSocialOfficeVersion2.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_1);

		_envSocialOfficeVersion3.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_2);
		_envSocialOfficeVersion3.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_envSocialOfficeVersion3.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_2);
		_envSocialOfficeVersion3.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_envSocialOfficeVersion3.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_2);
	}

}