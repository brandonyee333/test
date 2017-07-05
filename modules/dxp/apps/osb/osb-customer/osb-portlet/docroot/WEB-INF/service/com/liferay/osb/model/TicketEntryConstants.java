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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kyle Bischof
 */
public class TicketEntryConstants {

	public static final int CLUSTER_SERVER_COMMUNICATION_TYPE_MULTICAST = 1;

	public static final int CLUSTER_SERVER_COMMUNICATION_TYPE_UNICAST = 2;

	public static final int[] CLUSTER_SERVER_COMMUNICATION_TYPES = {
		CLUSTER_SERVER_COMMUNICATION_TYPE_MULTICAST,
		CLUSTER_SERVER_COMMUNICATION_TYPE_UNICAST
	};

	public static final int COMPONENT_ACTIVATION_KEY_PRODUCT_ADMINISTRATION =
		26063;

	public static final int COMPONENT_ALERTS_ANNOUNCEMENTS = 26052;

	public static final int COMPONENT_APPLICATION_DISPLAY_TEMPLATES = 26042;

	public static final int COMPONENT_ASSET_PUBLISHER_FRAMEWORK = 26043;

	public static final int COMPONENT_AUDIENCE_TARGETING = 26044;

	public static final int COMPONENT_AUTHENTICATION = 26007;

	public static final int COMPONENT_BLADE = 26070;

	public static final int COMPONENT_BLOGS = 26053;

	public static final int COMPONENT_CACHING_CLUSTERING = 26031;

	public static final int COMPONENT_CALENDAR = 26023;

	public static final int COMPONENT_CATEGORIES_TAGS_VOCABULARIES = 26045;

	public static final int COMPONENT_CLUSTERING = 26000;

	public static final int COMPONENT_COLLABORATION_SUITE = 26002;

	public static final int COMPONENT_CUSTOM_DEVELOPMENT = 26003;

	public static final int COMPONENT_DEPLOYMENT_ENVIRONMENTS = 26032;

	public static final int COMPONENT_DOCUMENT_LIBRARY = 26004;

	public static final int COMPONENT_DOCUMENT_MANAGEMENT = 26054;

	public static final int COMPONENT_DYNAMIC_DATA_MAPPING_DATA_LISTS = 26059;

	public static final int COMPONENT_ENTERPRISE_SEARCH = 26038;

	public static final int COMPONENT_FORM_VALIDATION = 26060;

	public static final String COMPONENT_GROUP_DE_COLLABORATION =
		"collaboration";

	public static final String COMPONENT_GROUP_DE_FORMS_WORKFLOW =
		"forms-workflow";

	public static final String COMPONENT_GROUP_DE_FOUNDATION = "foundation";

	public static final String COMPONENT_GROUP_DE_PROJECT_MANAGEMENT =
		"project-management";

	public static final String COMPONENT_GROUP_DE_WEB_EXPERIENCE =
		"web-experience";

	public static final String[] COMPONENT_GROUPS_DE = {
		COMPONENT_GROUP_DE_PROJECT_MANAGEMENT, COMPONENT_GROUP_DE_FOUNDATION,
		COMPONENT_GROUP_DE_WEB_EXPERIENCE, COMPONENT_GROUP_DE_COLLABORATION,
		COMPONENT_GROUP_DE_FORMS_WORKFLOW
	};

	public static final int COMPONENT_LAR_STAGING = 26006;

	public static final int COMPONENT_LCS_CLIENT = 26067;

	public static final int COMPONENT_LICENSE = 26026;

	public static final int COMPONENT_LICENSE_ACCOUNT_SETUP = 26008;

	public static final int COMPONENT_LIFERAY_API = 26020;

	public static final int COMPONENT_LIFERAY_CONNECTED_SERVICES = 26030;

	public static final int COMPONENT_LIFERAY_DEVELOPER_STUDIO = 26017;

	public static final int COMPONENT_LIFERAY_FACES = 26021;

	public static final int COMPONENT_LIFERAY_MOBILE_SDK = 26024;

	public static final int COMPONENT_LIFERAY_PUSH = 26064;

	public static final int COMPONENT_LIFERAY_SCREENS = 26065;

	public static final int COMPONENT_LIFERAY_SCREENS_CONNECTOR = 26066;

	public static final int COMPONENT_LIFERAY_SYNC = 26022;

	public static final int COMPONENT_LIFERAY_WORKSPACE = 26069;

	public static final int COMPONENT_LOCALIZATION = 26072;

	public static final int COMPONENT_MAVEN = 26040;

	public static final int COMPONENT_MESSAGE_BOARDS = 26055;

	public static final int COMPONENT_MOBILE_DEVICE_DETECTION = 26039;

	public static final int COMPONENT_NAVIGATION_BREADCRUMBS = 26046;

	public static final int COMPONENT_OTHER = 26010;

	public static final int COMPONENT_PATCH_MANAGEMENT = 26027;

	public static final int COMPONENT_PERFORMANCE = 26033;

	public static final int COMPONENT_PORTAL_ADMINISTRATION = 26019;

	public static final int COMPONENT_PORTAL_CONFIGURATION = 26034;

	public static final int COMPONENT_PORTAL_DEPLOYMENT = 26005;

	public static final int COMPONENT_PORTAL_SERVICES = 26035;

	public static final int COMPONENT_PROJECT_ADMINISTRATION = 26025;

	public static final int COMPONENT_RECYCLE_BIN = 26047;

	public static final int COMPONENT_REPORTING = 26056;

	public static final int COMPONENT_SEARCH_ENGINE_OPTIMIZATIONS_ANALYTICS =
		26048;

	public static final int COMPONENT_SEARCH_INDEXING = 26018;

	public static final int COMPONENT_SECURITY = 26012;

	public static final int COMPONENT_SITE_ADMINISTRATION_PAGE_MANAGEMENT =
		26049;

	public static final int COMPONENT_SOCIAL_NETWORKING = 26057;

	public static final int COMPONENT_SOCIAL_OFFICE = 26029;

	public static final int COMPONENT_STAGING_EXPORT_IMPORT = 26050;

	public static final int COMPONENT_SYNC_CONNECTOR = 26068;

	public static final int COMPONENT_SYNC_DESKTOP = 26041;

	public static final int COMPONENT_SYNC_MOBILE = 26071;

	public static final int COMPONENT_UI = 26015;

	public static final int COMPONENT_UI_INFRASTRUCTURE_ACCESSIBILITY = 26036;

	public static final int COMPONENT_UPGRADE = 26016;

	public static final int COMPONENT_USER_MEMBERSHIP_ROLE_MANAGEMENT = 26037;

	public static final int COMPONENT_WEB_CONTENT_ADMINISTRATION = 26051;

	public static final int COMPONENT_WEB_CONTENT_MANAGEMENT = 26001;

	public static final int COMPONENT_WEB_FORMS = 26061;

	public static final int COMPONENT_WIKI = 26058;

	public static final int COMPONENT_WORKFLOW_WORKFLOW_FORMS = 26062;

	public static final int COMPONENT_WORKFLOWS_FORMS = 26028;

	public static final int[] COMPONENTS_DE_COLLABORATION = {
		COMPONENT_ALERTS_ANNOUNCEMENTS, COMPONENT_BLOGS,
		COMPONENT_DOCUMENT_MANAGEMENT, COMPONENT_MESSAGE_BOARDS,
		COMPONENT_REPORTING, COMPONENT_SOCIAL_NETWORKING, COMPONENT_WIKI
	};

	public static final int[] COMPONENTS_DE_FORMS_WORKFLOW = {
		COMPONENT_CALENDAR, COMPONENT_DYNAMIC_DATA_MAPPING_DATA_LISTS,
		COMPONENT_FORM_VALIDATION, COMPONENT_WEB_FORMS,
		COMPONENT_WORKFLOW_WORKFLOW_FORMS
	};

	public static final int[] COMPONENTS_DE_FOUNDATION = {
		COMPONENT_AUTHENTICATION, COMPONENT_CACHING_CLUSTERING,
		COMPONENT_DEPLOYMENT_ENVIRONMENTS, COMPONENT_LOCALIZATION,
		COMPONENT_PERFORMANCE, COMPONENT_PORTAL_CONFIGURATION,
		COMPONENT_PORTAL_SERVICES, COMPONENT_SEARCH_INDEXING,
		COMPONENT_SECURITY, COMPONENT_UI_INFRASTRUCTURE_ACCESSIBILITY,
		COMPONENT_UPGRADE, COMPONENT_USER_MEMBERSHIP_ROLE_MANAGEMENT,
	};

	public static final int[] COMPONENTS_DE_PROJECT_MANAGEMENT = {
		COMPONENT_ACTIVATION_KEY_PRODUCT_ADMINISTRATION,
		COMPONENT_PATCH_MANAGEMENT
	};

	public static final int[] COMPONENTS_DE_WEB_EXPERIENCE = {
		COMPONENT_APPLICATION_DISPLAY_TEMPLATES,
		COMPONENT_ASSET_PUBLISHER_FRAMEWORK, COMPONENT_AUDIENCE_TARGETING,
		COMPONENT_CATEGORIES_TAGS_VOCABULARIES,
		COMPONENT_NAVIGATION_BREADCRUMBS, COMPONENT_RECYCLE_BIN,
		COMPONENT_SEARCH_ENGINE_OPTIMIZATIONS_ANALYTICS,
		COMPONENT_SITE_ADMINISTRATION_PAGE_MANAGEMENT,
		COMPONENT_STAGING_EXPORT_IMPORT, COMPONENT_WEB_CONTENT_ADMINISTRATION
	};

	public static final int[] COMPONENTS_DEPRECATED = {
		COMPONENT_CUSTOM_DEVELOPMENT, COMPONENT_LICENSE_ACCOUNT_SETUP,
		COMPONENT_OTHER
	};

	public static final int[] COMPONENTS_DEVELOPER_TOOLS = {
		COMPONENT_BLADE, COMPONENT_LIFERAY_DEVELOPER_STUDIO,
		COMPONENT_LIFERAY_FACES, COMPONENT_LIFERAY_WORKSPACE, COMPONENT_MAVEN
	};

	public static final int[] COMPONENTS_MANAGEMENT_TOOLS = {
		COMPONENT_LCS_CLIENT, COMPONENT_LIFERAY_CONNECTED_SERVICES
	};

	public static final int[] COMPONENTS_MOBILE_EXPERIENCE = {
		COMPONENT_LIFERAY_MOBILE_SDK, COMPONENT_LIFERAY_PUSH,
		COMPONENT_LIFERAY_SCREENS, COMPONENT_LIFERAY_SCREENS_CONNECTOR
	};

	public static final int[] COMPONENTS_PORTAL = {
		COMPONENT_AUTHENTICATION, COMPONENT_CALENDAR, COMPONENT_CLUSTERING,
		COMPONENT_COLLABORATION_SUITE, COMPONENT_DOCUMENT_LIBRARY,
		COMPONENT_LAR_STAGING, COMPONENT_LICENSE, COMPONENT_LIFERAY_API,
		COMPONENT_LIFERAY_DEVELOPER_STUDIO, COMPONENT_LIFERAY_FACES,
		COMPONENT_LIFERAY_MOBILE_SDK, COMPONENT_LIFERAY_SYNC,
		COMPONENT_LOCALIZATION, COMPONENT_PATCH_MANAGEMENT,
		COMPONENT_PORTAL_ADMINISTRATION, COMPONENT_PORTAL_DEPLOYMENT,
		COMPONENT_PROJECT_ADMINISTRATION, COMPONENT_SEARCH_INDEXING,
		COMPONENT_SECURITY, COMPONENT_SOCIAL_OFFICE, COMPONENT_UI,
		COMPONENT_UPGRADE, COMPONENT_WEB_CONTENT_MANAGEMENT,
		COMPONENT_WORKFLOWS_FORMS
	};

	public static final int[] COMPONENTS_PRODUCTIVITY_TOOLS = {
		COMPONENT_SYNC_CONNECTOR, COMPONENT_SYNC_DESKTOP, COMPONENT_SYNC_MOBILE
	};

	public static final int DOC_LIB_PERSISTENCE_ADVANCED_FILE_SYSTEM_STORE = 1;

	public static final int DOC_LIB_PERSISTENCE_CMIS_STORE = 2;

	public static final int DOC_LIB_PERSISTENCE_DB_STORE = 3;

	public static final int DOC_LIB_PERSISTENCE_FILE_SYSTEM_STORE = 4;

	public static final int DOC_LIB_PERSISTENCE_JCR_STORE = 5;

	public static final int DOC_LIB_PERSISTENCE_S3_STORE = 6;

	public static final int[] DOC_LIB_PERSISTENCES = {
		DOC_LIB_PERSISTENCE_ADVANCED_FILE_SYSTEM_STORE,
		DOC_LIB_PERSISTENCE_CMIS_STORE, DOC_LIB_PERSISTENCE_DB_STORE,
		DOC_LIB_PERSISTENCE_FILE_SYSTEM_STORE, DOC_LIB_PERSISTENCE_JCR_STORE,
		DOC_LIB_PERSISTENCE_S3_STORE
	};

	public static final int ENV_AS_GLASSFISH_2_1 = 27016;

	public static final int ENV_AS_GLASSFISH_2_X = 27000;

	public static final int ENV_AS_GLASSFISH_3 = 27017;

	public static final int ENV_AS_GLASSFISH_3_1 = 27018;

	public static final int ENV_AS_GLASSFISH_3_X = 27001;

	public static final int ENV_AS_GLASSFISH_4_0 = 27048;

	public static final int ENV_AS_JBOSS_4_2_X = 27002;

	public static final int ENV_AS_JBOSS_5_1 = 27019;

	public static final int ENV_AS_JBOSS_5_X = 27003;

	public static final int ENV_AS_JBOSS_7_X = 27012;

	public static final int ENV_AS_JBOSS_AS_7_1 = 27020;

	public static final int ENV_AS_JBOSS_EAP_5_1 = 27050;

	public static final int ENV_AS_JBOSS_EAP_5_2 = 27042;

	public static final int ENV_AS_JBOSS_EAP_6_0 = 27040;

	public static final int ENV_AS_JBOSS_EAP_6_1 = 27049;

	public static final int ENV_AS_JBOSS_EAP_6_2 = 27051;

	public static final int ENV_AS_JBOSS_EAP_6_3 = 27052;

	public static final int ENV_AS_JBOSS_EAP_6_4 = 27053;

	public static final int ENV_AS_JBOSS_EAP_7_0 = 27058;

	public static final int ENV_AS_JBOSS_TOMCAT_4_2 = 27021;

	public static final int ENV_AS_JBOSS_TOMCAT_5_0 = 27022;

	public static final int ENV_AS_JONAS_5_1 = 27023;

	public static final int ENV_AS_JONAS_5_2 = 27024;

	public static final int ENV_AS_JONAS_TOMCAT_4_10 = 27026;

	public static final int ENV_AS_ORACLEAS_10_1 = 27027;

	public static final int ENV_AS_OTHER = 27004;

	public static final int ENV_AS_RESIN_3_1 = 27028;

	public static final int ENV_AS_RESIN_4 = 27029;

	public static final int ENV_AS_TCAT = 27011;

	public static final int ENV_AS_TCAT_6_4 = 27014;

	public static final int ENV_AS_TCAT_7_0 = 27043;

	public static final int ENV_AS_TCSERVER_2_6 = 27041;

	public static final int ENV_AS_TCSERVER_2_9 = 27044;

	public static final int ENV_AS_TCSERVER_3_1 = 27054;

	public static final int ENV_AS_TCSERVER_3_2 = 27060;

	public static final int ENV_AS_TOMCAT_5_5 = 27030;

	public static final int ENV_AS_TOMCAT_5_5_X = 27005;

	public static final int ENV_AS_TOMCAT_6_0 = 27031;

	public static final int ENV_AS_TOMCAT_6_X = 27006;

	public static final int ENV_AS_TOMCAT_7_0 = 27039;

	public static final int ENV_AS_TOMCAT_7_0_X = 27013;

	public static final int ENV_AS_TOMCAT_8_0 = 27055;

	public static final int ENV_AS_TOMCAT_8_5 = 27059;

	public static final int ENV_AS_WEBLOGIC_9_2 = 27032;

	public static final int ENV_AS_WEBLOGIC_9_X = 27007;

	public static final int ENV_AS_WEBLOGIC_10_0 = 27033;

	public static final int ENV_AS_WEBLOGIC_10_3 = 27034;

	public static final int ENV_AS_WEBLOGIC_10_X = 27008;

	public static final int ENV_AS_WEBLOGIC_11 = 27035;

	public static final int ENV_AS_WEBLOGIC_11_G = 27045;

	public static final int ENV_AS_WEBLOGIC_11_X = 27015;

	public static final int ENV_AS_WEBLOGIC_12C_R1 = 27046;

	public static final int ENV_AS_WEBLOGIC_12C_R2 = 27056;

	public static final int ENV_AS_WEBSPHERE_6_1 = 27036;

	public static final int ENV_AS_WEBSPHERE_6_1_X = 27009;

	public static final int ENV_AS_WEBSPHERE_7_0 = 27037;

	public static final int ENV_AS_WEBSPHERE_7_X = 27010;

	public static final int ENV_AS_WEBSPHERE_8 = 27038;

	public static final int ENV_AS_WEBSPHERE_8_5 = 27047;

	public static final int ENV_AS_WILDFILY_10_0 = 27057;

	public static final int ENV_BROWSER_ANDROID_BROWSER_4_3_PLUS = 37010;

	public static final int ENV_BROWSER_CHROME = 37000;

	public static final int ENV_BROWSER_EDGE = 37013;

	public static final int ENV_BROWSER_FIREFOX = 37001;

	public static final int ENV_BROWSER_FIREFOX_ESR_45 = 37014;

	public static final int ENV_BROWSER_IE_6 = 37002;

	public static final int ENV_BROWSER_IE_7 = 37003;

	public static final int ENV_BROWSER_IE_8 = 37004;

	public static final int ENV_BROWSER_IE_9 = 37005;

	public static final int ENV_BROWSER_IE_10 = 37006;

	public static final int ENV_BROWSER_IE_11 = 37009;

	public static final int ENV_BROWSER_IOS_SAFARI = 37012;

	public static final int ENV_BROWSER_MOBILE_CHROME = 37017;

	public static final int ENV_BROWSER_OTHER = 37999;

	public static final int ENV_BROWSER_SAFARI_5 = 37007;

	public static final int ENV_BROWSER_SAFARI_6 = 37008;

	public static final int ENV_BROWSER_SAFARI_8 = 37016;

	public static final int ENV_BROWSER_SAFARI_9 = 37015;

	public static final int ENV_BROWSER_SAFARI_10 = 37018;

	public static final int ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD = 39000;

	public static final int ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE = 39001;

	public static final int ENV_CS_AWS_S3 = 39002;

	public static final int ENV_CS_AZURE_FILES = 39003;

	public static final int ENV_CS_AZURE_SQL_DATABASES = 39004;

	public static final int ENV_CS_AZURE_VIRTUAL_MACHINES = 39005;

	public static final int ENV_DB_DB2_8_1 = 28016;

	public static final int ENV_DB_DB2_8_2 = 28000;

	public static final int ENV_DB_DB2_9_7 = 28013;

	public static final int ENV_DB_DB2_10_1 = 28024;

	public static final int ENV_DB_DB2_10_5 = 28038;

	public static final int ENV_DB_HYPERSONIC = 28002;

	public static final int ENV_DB_MARIADB_10 = 28033;

	public static final int ENV_DB_MYSQL_5_0 = 28003;

	public static final int ENV_DB_MYSQL_5_1 = 28004;

	public static final int ENV_DB_MYSQL_5_5 = 28014;

	public static final int ENV_DB_MYSQL_5_6 = 28025;

	public static final int ENV_DB_MYSQL_5_7 = 28036;

	public static final int ENV_DB_ORACLE_9I = 28005;

	public static final int ENV_DB_ORACLE_10G = 28006;

	public static final int ENV_DB_ORACLE_10G_RELEASE_2 = 28017;

	public static final int ENV_DB_ORACLE_11G = 28007;

	public static final int ENV_DB_ORACLE_11G_RELEASE_1 = 28018;

	public static final int ENV_DB_ORACLE_11G_RELEASE_2 = 28019;

	public static final int ENV_DB_ORACLE_12C_RELEASE_1 = 28030;

	public static final int ENV_DB_OTHER = 28008;

	public static final int ENV_DB_POSTGRESQL = 28009;

	public static final int ENV_DB_POSTGRESQL_8_4 = 28020;

	public static final int ENV_DB_POSTGRESQL_9_0 = 28021;

	public static final int ENV_DB_POSTGRESQL_9_1 = 28026;

	public static final int ENV_DB_POSTGRESQL_9_2 = 28027;

	public static final int ENV_DB_POSTGRESQL_9_3 = 28031;

	public static final int ENV_DB_POSTGRESQL_9_4 = 28034;

	public static final int ENV_DB_SQL_SERVER_2000 = 28010;

	public static final int ENV_DB_SQL_SERVER_2005 = 28011;

	public static final int ENV_DB_SQL_SERVER_2008 = 28012;

	public static final int ENV_DB_SQL_SERVER_2008_R2 = 28022;

	public static final int ENV_DB_SQL_SERVER_2012 = 28028;

	public static final int ENV_DB_SQL_SERVER_2014 = 28032;

	public static final int ENV_DB_SQL_SERVER_2016 = 28037;

	public static final int ENV_DB_SYBASE_ASE_15_0 = 28023;

	public static final int ENV_DB_SYBASE_ASE_15_5 = 28015;

	public static final int ENV_DB_SYBASE_ASE_15_7 = 28029;

	public static final int ENV_DB_SYBASE_ASE_16 = 28035;

	public static final int ENV_JVM_IBM_JDK_6 = 29003;

	public static final int ENV_JVM_IBM_JDK_7 = 29004;

	public static final int ENV_JVM_IBM_JDK_8 = 29007;

	public static final int ENV_JVM_JAVA_5 = 29000;

	public static final int ENV_JVM_JAVA_6 = 29001;

	public static final int ENV_JVM_JAVA_7 = 29002;

	public static final int ENV_JVM_JAVA_8 = 29006;

	public static final int ENV_JVM_JROCKET_JDK_6 = 29005;

	public static final int ENV_OS_AIX = 30009;

	public static final int ENV_OS_AIX_6_1 = 30010;

	public static final int ENV_OS_AIX_7_1 = 30011;

	public static final int ENV_OS_CENTOS_4 = 30012;

	public static final int ENV_OS_CENTOS_5 = 30013;

	public static final int ENV_OS_CENTOS_6 = 30014;

	public static final int ENV_OS_CENTOS_7 = 30036;

	public static final int ENV_OS_DEBIAN_6_0 = 30034;

	public static final int ENV_OS_DEBIAN_7 = 30041;

	public static final int ENV_OS_DEBIAN_8 = 30037;

	public static final int ENV_OS_HP_UX = 30015;

	public static final int ENV_OS_LINUX = 30000;

	public static final int ENV_OS_MAC_OS_X = 30001;

	public static final int ENV_OS_MAC_OS_X_10_5_PLUS = 30024;

	public static final int ENV_OS_OPENSUSE_13_1 = 30032;

	public static final int ENV_OS_ORACLE_LINUX = 30016;

	public static final int ENV_OS_ORACLE_LINUX_6 = 30017;

	public static final int ENV_OS_ORACLE_LINUX_7 = 30038;

	public static final int ENV_OS_OTHER = 30006;

	public static final int ENV_OS_OTHER_LINUX_DISTIRBUTIONS = 30018;

	public static final int ENV_OS_RED_HAT = 30007;

	public static final int ENV_OS_RED_HAT_ENTERPRISE_4 = 30019;

	public static final int ENV_OS_RED_HAT_ENTERPRISE_5 = 30020;

	public static final int ENV_OS_RED_HAT_ENTERPRISE_6 = 30021;

	public static final int ENV_OS_RED_HAT_ENTERPRISE_7 = 30035;

	public static final int ENV_OS_SOLARIS_10 = 30022;

	public static final int ENV_OS_SOLARIS_11 = 30031;

	public static final int ENV_OS_SOLARIS_EXPRESS_11 = 30025;

	public static final int ENV_OS_SUSE_ENTERPRISE_LINUX_11 = 30033;

	public static final int ENV_OS_SUSE_ENTERPRISE_LINUX_12 = 30039;

	public static final int ENV_OS_UBUNTU = 30023;

	public static final int ENV_OS_UBUNTU_10 = 30026;

	public static final int ENV_OS_UBUNTU_11 = 30027;

	public static final int ENV_OS_UBUNTU_12 = 30028;

	public static final int ENV_OS_UBUNTU_13 = 30029;

	public static final int ENV_OS_UBUNTU_LTS_14_04 = 30040;

	public static final int ENV_OS_WINDOWS_7 = 30002;

	public static final int ENV_OS_WINDOWS_SERVER_2003 = 30004;

	public static final int ENV_OS_WINDOWS_SERVER_2008 = 30005;

	public static final int ENV_OS_WINDOWS_SERVER_2008_R2 = 30008;

	public static final int ENV_OS_WINDOWS_SERVER_2012 = 30030;

	public static final int ENV_OS_WINDOWS_XP = 30003;

	public static final int ENV_SEARCH_ELASTICSEARCH = 40000;

	public static final int ENV_SEARCH_KIBANA_4_4 = 40001;

	public static final int ENV_SEARCH_MARVEL_2_2 = 40002;

	public static final int ENV_SEARCH_SHIELD_2_2 = 40003;

	public static final int ENV_SEARCH_SOLR = 40004;

	public static final int ENV_SEARCH_SOLRCLOUD = 40005;

	public static final int ESCALATION_LEVEL_1 = 31001;

	public static final int ESCALATION_LEVEL_2 = 31002;

	public static final int ESCALATION_LEVEL_P1 = 31000;

	public static final int LICENSE_PURPOSE_ELASTIC_MONTHLY_KEY = 2;

	public static final int LICENSE_PURPOSE_PERMANENT_KEY = 1;

	public static final int LICENSE_PURPOSE_TEMPORARY_KEY = 3;

	public static final int LICENSE_TYPE_CLUSTER = 2;

	public static final int LICENSE_TYPE_DEVELOPER = 3;

	public static final int LICENSE_TYPE_STANDALONE = 1;

	public static final String LIST_TYPE_COMPONENT =
		TicketEntry.class.getName() + ".component";

	public static final String LIST_TYPE_ENV_AS =
		TicketEntry.class.getName() + ".envAS";

	public static final String LIST_TYPE_ENV_BROWSER =
		TicketEntry.class.getName() + ".envBrowser";

	public static final String LIST_TYPE_ENV_CS =
		TicketEntry.class.getName() + ".envCS";

	public static final String LIST_TYPE_ENV_DB =
		TicketEntry.class.getName() + ".envDB";

	public static final String LIST_TYPE_ENV_JVM =
		TicketEntry.class.getName() + ".envJVM";

	public static final String LIST_TYPE_ENV_OS =
		TicketEntry.class.getName() + ".envOS";

	public static final String LIST_TYPE_ENV_SEARCH =
		TicketEntry.class.getName() + ".envSearch";

	public static final String LIST_TYPE_ESCALATION_LEVEL =
		TicketEntry.class.getName() + ".escalationLevel";

	public static final String LIST_TYPE_RESOLUTION =
		TicketEntry.class.getName() + ".resolution";

	public static final String LIST_TYPE_STATUS =
		TicketEntry.class.getName() + ".status";

	public static final String NOT_AVAILABLE = "N/A";

	public static final int RESOLUTION_COMPLETED = 32000;

	public static final int RESOLUTION_DUPLICATE = 32001;

	public static final int RESOLUTION_FUTURE_RELEASE = 32005;

	public static final int RESOLUTION_PENDING_CUSTOMER = 32003;

	public static final int RESOLUTION_REDIRECTED = 32004;

	public static final int STATUS_BUILDING_PATCH = 33010;

	public static final int STATUS_CLOSED = 33000;

	public static final int STATUS_ENGINEER_ANALYZING = 33014;

	public static final int STATUS_INACTIVE = 33001;

	public static final int STATUS_INCIDENT_REPORTED = 33002;

	public static final int STATUS_INVESTIGATING = 33003;

	public static final int STATUS_ON_HOLD = 33005;

	public static final int STATUS_OPEN = -1;

	public static final int STATUS_PENDING_WORKER = 33006;

	public static final int STATUS_REOPENED = 33007;

	public static final int STATUS_REPRODUCED = 33008;

	public static final int STATUS_RESOLVED = 33016;

	public static final int STATUS_RESOLVED_IN_PRODUCTION = 33017;

	public static final int STATUS_SOLUTION_DELIVERED = 33018;

	public static final int STATUS_SOLUTION_PROPOSED = 33009;

	public static final int[] STATUSES_ACTIVE = {
		STATUS_INCIDENT_REPORTED, STATUS_INVESTIGATING, STATUS_REOPENED
	};

	public static final int[] STATUSES_CLOSED = {
		STATUS_CLOSED, STATUS_RESOLVED, STATUS_RESOLVED_IN_PRODUCTION,
		STATUS_SOLUTION_DELIVERED
	};

	public static final int[] STATUSES_IN_PROGRESS = {
		STATUS_BUILDING_PATCH, STATUS_ENGINEER_ANALYZING, STATUS_PENDING_WORKER,
		STATUS_REPRODUCED
	};

	public static final int[] STATUSES_INACTIVE = {
		STATUS_CLOSED, STATUS_INACTIVE, STATUS_RESOLVED,
		STATUS_RESOLVED_IN_PRODUCTION, STATUS_SOLUTION_DELIVERED
	};

	public static final int[] STATUSES_ON_HOLD = {
		STATUS_ON_HOLD, STATUS_SOLUTION_PROPOSED
	};

	public static final int[] STATUSES_OPEN = {
		STATUS_BUILDING_PATCH, STATUS_ENGINEER_ANALYZING, STATUS_INACTIVE,
		STATUS_INCIDENT_REPORTED, STATUS_INVESTIGATING, STATUS_ON_HOLD,
		STATUS_PENDING_WORKER, STATUS_REOPENED, STATUS_REPRODUCED,
		STATUS_RESOLVED, STATUS_SOLUTION_DELIVERED, STATUS_SOLUTION_PROPOSED
	};

	public static final int[] STATUSES_SUPPORT_WORKER_ASSIGNED = {
		STATUS_BUILDING_PATCH, STATUS_ENGINEER_ANALYZING,
		STATUS_INCIDENT_REPORTED, STATUS_INVESTIGATING, STATUS_ON_HOLD,
		STATUS_PENDING_WORKER, STATUS_REOPENED, STATUS_REPRODUCED,
		STATUS_SOLUTION_PROPOSED
	};

	public static final int[] STATUSES_WORKER_OPEN = {
		STATUS_BUILDING_PATCH, STATUS_ENGINEER_ANALYZING, STATUS_INACTIVE,
		STATUS_INCIDENT_REPORTED, STATUS_INVESTIGATING, STATUS_ON_HOLD,
		STATUS_PENDING_WORKER, STATUS_REOPENED, STATUS_REPRODUCED,
		STATUS_SOLUTION_PROPOSED
	};

	public static final int[] STATUSES_WORKFLOW_ORDER = {
		STATUS_BUILDING_PATCH, STATUS_CLOSED, STATUS_ENGINEER_ANALYZING,
		STATUS_INCIDENT_REPORTED, STATUS_INACTIVE, STATUS_INVESTIGATING,
		STATUS_ON_HOLD, STATUS_PENDING_WORKER, STATUS_REOPENED,
		STATUS_REPRODUCED, STATUS_RESOLVED_IN_PRODUCTION, STATUS_RESOLVED,
		STATUS_SOLUTION_DELIVERED, STATUS_SOLUTION_PROPOSED
	};

	public static final int SUBCOMPONENT_ACCESSIBILITY_COMPLIANCE = 1;

	public static final int SUBCOMPONENT_ALLOY_UI = 2;

	public static final int SUBCOMPONENT_ANNOUNCEMENTS = 3;

	public static final int SUBCOMPONENT_BLOGS = 4;

	public static final int SUBCOMPONENT_BROWSER_SUPPORT = 5;

	public static final int SUBCOMPONENT_CACHE_REPLICATION_CLUSTER_LINK = 6;

	public static final int SUBCOMPONENT_CK_EDITOR = 7;

	public static final int SUBCOMPONENT_CONNECTING_TO_LCS = 52;

	public static final int SUBCOMPONENT_COOKIES = 8;

	public static final int SUBCOMPONENT_DEPENDENCY_JARS_UPGRADE = 9;

	public static final int SUBCOMPONENT_DESKTOP = 10;

	public static final int SUBCOMPONENT_ENVIRONMENT_SERVER_MANAGEMENT = 53;

	public static final int SUBCOMPONENT_FACEBOOK = 11;

	public static final int SUBCOMPONENT_GENERAL_IMPLEMENTATION = 12;

	public static final int SUBCOMPONENT_JGROUPS = 13;

	public static final int SUBCOMPONENT_LAR_EXPORT = 14;

	public static final int SUBCOMPONENT_LAR_IMPORT = 15;

	public static final int SUBCOMPONENT_LDAP = 16;

	public static final int SUBCOMPONENT_LICENSE = 17;

	public static final int SUBCOMPONENT_LIFERAY_AUTHENTICATION = 18;

	public static final int SUBCOMPONENT_LIFERAY_INSTALLATION = 19;

	public static final int SUBCOMPONENT_LOCAL_STAGING = 20;

	public static final int SUBCOMPONENT_LSVS = 21;

	public static final int SUBCOMPONENT_LUCENE = 22;

	public static final int SUBCOMPONENT_MESSAGE_BOARDS = 23;

	public static final int SUBCOMPONENT_MOBILE = 24;

	public static final int SUBCOMPONENT_MULTICAST = 25;

	public static final int SUBCOMPONENT_NOTIFICATIONS = 54;

	public static final int SUBCOMPONENT_OPENID = 26;

	public static final int SUBCOMPONENT_OTHER = 27;

	public static final int SUBCOMPONENT_PERMISSIONS = 28;

	public static final int SUBCOMPONENT_PERMISSIONS_ALGORITHM_MIGRATION = 29;

	public static final int SUBCOMPONENT_PLUGINS = 30;

	public static final int SUBCOMPONENT_PLUGINS_CUSTOM = 31;

	public static final int SUBCOMPONENT_PORTAL_SETTINGS = 32;

	public static final int SUBCOMPONENT_PORTAL_UPGRADE = 33;

	public static final int SUBCOMPONENT_REMOTE_STAGING = 34;

	public static final int SUBCOMPONENT_ROLES_PERMISSIONS = 35;

	public static final int SUBCOMPONENT_SAML = 36;

	public static final int SUBCOMPONENT_SITE_PAGE_TEMPLATES = 37;

	public static final int SUBCOMPONENT_SITES_ORGANIZATIONS = 38;

	public static final int SUBCOMPONENT_SO_APPS = 39;

	public static final int SUBCOMPONENT_SO_HOOKS = 40;

	public static final int SUBCOMPONENT_SOLR = 41;

	public static final int SUBCOMPONENT_SSO = 42;

	public static final int SUBCOMPONENT_STRUCTURES = 43;

	public static final int SUBCOMPONENT_SUBSCRIPTION_MANAGEMENT = 51;

	public static final int SUBCOMPONENT_TEMPLATES = 44;

	public static final int SUBCOMPONENT_THEMES = 45;

	public static final int SUBCOMPONENT_UNICAST = 46;

	public static final int SUBCOMPONENT_USER_MANAGEMENT_PERMISSIONS = 50;

	public static final int SUBCOMPONENT_WEB_SERVICES = 47;

	public static final int SUBCOMPONENT_WIKIS = 48;

	public static final int SUBCOMPONENT_XSS = 49;

	public static final int[] SUBCOMPONENTS_AUTHENTICATION = {
		SUBCOMPONENT_FACEBOOK, SUBCOMPONENT_LDAP,
		SUBCOMPONENT_LIFERAY_AUTHENTICATION, SUBCOMPONENT_OPENID,
		SUBCOMPONENT_SAML, SUBCOMPONENT_SSO
	};

	public static final int[] SUBCOMPONENTS_CLUSTERING = {
		SUBCOMPONENT_CACHE_REPLICATION_CLUSTER_LINK, SUBCOMPONENT_JGROUPS,
		SUBCOMPONENT_MULTICAST, SUBCOMPONENT_UNICAST
	};

	public static final int[] SUBCOMPONENTS_COLLABORATION_SUITE = {
		SUBCOMPONENT_ANNOUNCEMENTS, SUBCOMPONENT_BLOGS,
		SUBCOMPONENT_MESSAGE_BOARDS, SUBCOMPONENT_WIKIS
	};

	public static final int[] SUBCOMPONENTS_LAR_STAGING = {
		SUBCOMPONENT_LAR_EXPORT, SUBCOMPONENT_LAR_IMPORT,
		SUBCOMPONENT_LOCAL_STAGING, SUBCOMPONENT_REMOTE_STAGING
	};

	public static final int[] SUBCOMPONENTS_LICENSE = {
		SUBCOMPONENT_LICENSE
	};

	public static final int[] SUBCOMPONENTS_LIFERAY_API = {
		SUBCOMPONENT_GENERAL_IMPLEMENTATION, SUBCOMPONENT_WEB_SERVICES
	};

	public static final int[] SUBCOMPONENTS_LIFERAY_CONNECTED_SERVICES = {
		SUBCOMPONENT_USER_MANAGEMENT_PERMISSIONS,
		SUBCOMPONENT_SUBSCRIPTION_MANAGEMENT, SUBCOMPONENT_CONNECTING_TO_LCS,
		SUBCOMPONENT_ENVIRONMENT_SERVER_MANAGEMENT, SUBCOMPONENT_NOTIFICATIONS
	};

	public static final int[] SUBCOMPONENTS_LIFERAY_SYNC = {
		SUBCOMPONENT_DESKTOP, SUBCOMPONENT_MOBILE
	};

	public static final int[] SUBCOMPONENTS_PORTAL_ADMINISTRATION = {
		SUBCOMPONENT_PORTAL_SETTINGS, SUBCOMPONENT_ROLES_PERMISSIONS,
		SUBCOMPONENT_SITE_PAGE_TEMPLATES, SUBCOMPONENT_SITES_ORGANIZATIONS
	};

	public static final int[] SUBCOMPONENTS_PORTAL_DEPLOYMENT = {
		SUBCOMPONENT_LIFERAY_INSTALLATION, SUBCOMPONENT_PLUGINS_CUSTOM
	};

	public static final int[] SUBCOMPONENTS_SEARCH_INDEXING = {
		SUBCOMPONENT_LUCENE, SUBCOMPONENT_SOLR
	};

	public static final int[] SUBCOMPONENTS_SECURITY = {
		SUBCOMPONENT_COOKIES, SUBCOMPONENT_LSVS, SUBCOMPONENT_PERMISSIONS,
		SUBCOMPONENT_XSS
	};

	public static final int[] SUBCOMPONENTS_SOCIAL_OFFICE = {
		SUBCOMPONENT_SO_APPS, SUBCOMPONENT_SO_HOOKS
	};

	public static final int[] SUBCOMPONENTS_UI = {
		SUBCOMPONENT_ACCESSIBILITY_COMPLIANCE, SUBCOMPONENT_ALLOY_UI,
		SUBCOMPONENT_BROWSER_SUPPORT, SUBCOMPONENT_PLUGINS, SUBCOMPONENT_THEMES
	};

	public static final int[] SUBCOMPONENTS_UPGRADE = {
		SUBCOMPONENT_DEPENDENCY_JARS_UPGRADE,
		SUBCOMPONENT_PERMISSIONS_ALGORITHM_MIGRATION,
		SUBCOMPONENT_PORTAL_UPGRADE
	};

	public static final int[] SUBCOMPONENTS_WEB_CONTENT_MANAGEMENT = {
		SUBCOMPONENT_CK_EDITOR, SUBCOMPONENT_STRUCTURES, SUBCOMPONENT_TEMPLATES
	};

	public static final int SYSTEM_STATUS_INOPERABLE = 1;

	public static final int SYSTEM_STATUS_INVALID = 6;

	public static final int SYSTEM_STATUS_LIMITED = 3;

	public static final int SYSTEM_STATUS_MINOR = 5;

	public static final int SYSTEM_STATUS_NEW_ACTIVATION = 7;

	public static final int SYSTEM_STATUS_PERIODIC = 4;

	public static final int SYSTEM_STATUS_UPGRADING = 8;

	public static final int UPLOAD_METHOD_FTP = 1;

	public static final int UPLOAD_METHOD_HERE = 2;

	public static final int[] UPLOAD_METHODS = {
		UPLOAD_METHOD_FTP, UPLOAD_METHOD_HERE
	};

	public static final int WEIGHT_HEAVY = 2;

	public static final int WEIGHT_LIGHT = 3;

	public static final int WEIGHT_NORMAL = 1;

	public static final int[] WEIGHTS = {
		WEIGHT_LIGHT, WEIGHT_NORMAL, WEIGHT_HEAVY
	};

	public static String getClusterServerCommunicationTypeLabel(
		int serverCommuncationType) {

		if (serverCommuncationType ==
				CLUSTER_SERVER_COMMUNICATION_TYPE_MULTICAST) {

			return "multicast";
		}
		else if (serverCommuncationType ==
					CLUSTER_SERVER_COMMUNICATION_TYPE_UNICAST) {

			return "unicast";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getComponentLabel(int component) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(component);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

	public static String getDocLibPersistenceLabel(int docLibPersistence) {
		if (docLibPersistence ==
				DOC_LIB_PERSISTENCE_ADVANCED_FILE_SYSTEM_STORE) {

			return "advanced-file-system-store";
		}
		else if (docLibPersistence == DOC_LIB_PERSISTENCE_CMIS_STORE) {
			return "cmis-store";
		}
		else if (docLibPersistence == DOC_LIB_PERSISTENCE_DB_STORE) {
			return "db-store";
		}
		else if (docLibPersistence == DOC_LIB_PERSISTENCE_FILE_SYSTEM_STORE) {
			return "file-system-store";
		}
		else if (docLibPersistence == DOC_LIB_PERSISTENCE_JCR_STORE) {
			return "jcr-store";
		}
		else if (docLibPersistence == DOC_LIB_PERSISTENCE_S3_STORE) {
			return "s3-store";
		}

		return NOT_AVAILABLE;
	}

	public static String getEnvLabel(int envType) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(envType);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

	public static int[] getEnvListTypeIds(int envLFR, String envListType) {
		if (ProductEntryConstants.isPortalVersion5_2(envLFR)) {
			return _ENV_PORTAL_VERSION_5_2.get(envListType);
		}
		else if (ProductEntryConstants.isPortalVersion6_0(envLFR)) {
			return _ENV_PORTAL_VERSION_6_0.get(envListType);
		}
		else if (ProductEntryConstants.isPortalVersion6_1(envLFR)) {
			return _ENV_PORTAL_VERSION_6_1.get(envListType);
		}
		else if (ProductEntryConstants.isPortalVersion6_2(envLFR)) {
			return _ENV_PORTAL_VERSION_6_2.get(envListType);
		}
		else if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {
			return _ENV_DIGITAL_ENTERPRISE_VERSION_7_0.get(envListType);
		}
		else if ((envLFR >=
					ProductEntryConstants.SOCIAL_OFFICE_VERSION_2_0_3) &&
				 (envLFR <=
				 	ProductEntryConstants.SOCIAL_OFFICE_VERSION_2_1_0)) {

			return _ENV_SOCIAL_OFFICE_VERSION_2.get(envListType);
		}
		else if ((envLFR >=
					ProductEntryConstants.SOCIAL_OFFICE_VERSION_3_0_0) &&
				 (envLFR <=
				 	ProductEntryConstants.SOCIAL_OFFICE_VERSION_3_1_1)) {

			return _ENV_SOCIAL_OFFICE_VERSION_3.get(envListType);
		}
		else {
			return _ENV_PORTAL_VERSION_OTHER.get(envListType);
		}
	}

	public static String getEscalationLevelLabel(int escalationLevel) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(
				escalationLevel);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

	public static int[] getGroupComponents(String componentGroup) {
		if (componentGroup.equals(COMPONENT_GROUP_DE_COLLABORATION)) {
			return COMPONENTS_DE_COLLABORATION;
		}
		else if (componentGroup.equals(COMPONENT_GROUP_DE_FORMS_WORKFLOW)) {
			return COMPONENTS_DE_FORMS_WORKFLOW;
		}
		else if (componentGroup.equals(COMPONENT_GROUP_DE_FOUNDATION)) {
			return COMPONENTS_DE_FOUNDATION;
		}
		else if (componentGroup.equals(COMPONENT_GROUP_DE_PROJECT_MANAGEMENT)) {
			return COMPONENTS_DE_PROJECT_MANAGEMENT;
		}
		else if (componentGroup.equals(COMPONENT_GROUP_DE_WEB_EXPERIENCE)) {
			return COMPONENTS_DE_WEB_EXPERIENCE;
		}
		else {
			return new int[0];
		}
	}

	public static String getLicensePurposeLabel(int purpose) {
		if (purpose == LICENSE_PURPOSE_ELASTIC_MONTHLY_KEY) {
			return "elastic-monthly-key";
		}
		else if (purpose == LICENSE_PURPOSE_PERMANENT_KEY) {
			return "permanent-key";
		}
		else if (purpose == LICENSE_PURPOSE_TEMPORARY_KEY) {
			return "temporary-key";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static final int[] getLicensePurposes() {
		return _LICENSE_PURPOSES;
	}

	public static String getLicenseTypeLabel(int type) {
		if (type == LICENSE_TYPE_CLUSTER) {
			return "cluster";
		}
		else if (type == LICENSE_TYPE_DEVELOPER) {
			return "developer";
		}
		else if (type == LICENSE_TYPE_STANDALONE) {
			return "standalone";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static int[] getLicenseTypes() {
		return _LICENSE_TYPES;
	}

	public static int[] getProductComponents(ProductEntry productEntry) {
		if (productEntry.isDeveloperTools()) {
			return COMPONENTS_DEVELOPER_TOOLS;
		}
		else if (productEntry.isManagementTools()) {
			return COMPONENTS_MANAGEMENT_TOOLS;
		}
		else if (productEntry.isMobileExperience()) {
			return COMPONENTS_MOBILE_EXPERIENCE;
		}
		else if (productEntry.isPortal()) {
			return COMPONENTS_PORTAL;
		}
		else if (productEntry.isProductivityTools()) {
			return COMPONENTS_PRODUCTIVITY_TOOLS;
		}
		else {
			return new int[0];
		}
	}

	public static int[] getProductDisplayNameComponents(
		String productDisplayName) {

		if (productDisplayName.equals(
				ProductEntryConstants.DISPLAY_NAME_DEVELOPER_TOOLS)) {

			return COMPONENTS_DEVELOPER_TOOLS;
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.
						DISPLAY_NAME_MOBILE_DEVICE_DETECTION)) {

			return new int[] {COMPONENT_MOBILE_DEVICE_DETECTION};
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.DISPLAY_NAME_DIGITAL_ENTERPRISE)) {

			return ArrayUtil.append(
				COMPONENTS_DE_COLLABORATION, COMPONENTS_DE_FORMS_WORKFLOW,
				COMPONENTS_DE_FOUNDATION, COMPONENTS_DE_PROJECT_MANAGEMENT,
				COMPONENTS_DE_WEB_EXPERIENCE);
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.
						DISPLAY_NAME_ENTERPRISE_SEARCH_PREMIUM) ||
				 productDisplayName.equals(
					ProductEntryConstants.
						DISPLAY_NAME_ENTERPRISE_SEARCH_STANDARD)) {

			return new int[] {COMPONENT_ENTERPRISE_SEARCH};
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.DISPLAY_NAME_MANAGEMENT_TOOLS)) {

			return COMPONENTS_MANAGEMENT_TOOLS;
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.DISPLAY_NAME_MOBILE_EXPERIENCE)) {

			return COMPONENTS_MOBILE_EXPERIENCE;
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.DISPLAY_NAME_PORTAL)) {

			return COMPONENTS_PORTAL;
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.DISPLAY_NAME_PRODUCTIVITY_TOOLS)) {

			return COMPONENTS_PRODUCTIVITY_TOOLS;
		}
		else if (productDisplayName.equals(
					ProductEntryConstants.DISPLAY_NAME_SOCIAL_OFFICE)) {

			return new int[] {COMPONENT_SOCIAL_OFFICE};
		}
		else {
			return new int[0];
		}
	}

	public static String getResolutionLabel(int resolution) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(resolution);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

	public static String getSeverityLabel(int severity) {
		if (severity == SupportResponseConstants.SEVERITY_CRITICAL) {
			return "critical";
		}
		else if (severity == SupportResponseConstants.SEVERITY_MAJOR) {
			return "major";
		}
		else if (severity == SupportResponseConstants.SEVERITY_MINOR) {
			return "minor";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getStatusLabel(int status) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(status);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

	public static String getSubcomponentLabel(int subComponent) {
		if (subComponent == SUBCOMPONENT_ACCESSIBILITY_COMPLIANCE) {
			return "accessibility-compliance";
		}
		else if (subComponent == SUBCOMPONENT_ALLOY_UI) {
			return "alloy-ui";
		}
		else if (subComponent == SUBCOMPONENT_ANNOUNCEMENTS) {
			return "announcements";
		}
		else if (subComponent == SUBCOMPONENT_BLOGS) {
			return "blogs";
		}
		else if (subComponent == SUBCOMPONENT_BROWSER_SUPPORT) {
			return "browser-support";
		}
		else if (subComponent == SUBCOMPONENT_CACHE_REPLICATION_CLUSTER_LINK) {
			return "cache-replication-cluster-link";
		}
		else if (subComponent == SUBCOMPONENT_CK_EDITOR) {
			return "ck-editor";
		}
		else if (subComponent == SUBCOMPONENT_CONNECTING_TO_LCS) {
			return "connecting-to-lcs";
		}
		else if (subComponent == SUBCOMPONENT_COOKIES) {
			return "cookies";
		}
		else if (subComponent == SUBCOMPONENT_DEPENDENCY_JARS_UPGRADE) {
			return "dependency-jars-upgrade";
		}
		else if (subComponent == SUBCOMPONENT_DESKTOP) {
			return "desktop";
		}
		else if (subComponent == SUBCOMPONENT_ENVIRONMENT_SERVER_MANAGEMENT) {
			return "environment-server-management";
		}
		else if (subComponent == SUBCOMPONENT_FACEBOOK) {
			return "facebook";
		}
		else if (subComponent == SUBCOMPONENT_GENERAL_IMPLEMENTATION) {
			return "general-implementation";
		}
		else if (subComponent == SUBCOMPONENT_JGROUPS) {
			return "jgroups";
		}
		else if (subComponent == SUBCOMPONENT_LAR_EXPORT) {
			return "lar-export";
		}
		else if (subComponent == SUBCOMPONENT_LAR_IMPORT) {
			return "lar-import";
		}
		else if (subComponent == SUBCOMPONENT_LDAP) {
			return "ldap";
		}
		else if (subComponent == SUBCOMPONENT_LICENSE) {
			return "license";
		}
		else if (subComponent == SUBCOMPONENT_LIFERAY_AUTHENTICATION) {
			return "liferay-authentication";
		}
		else if (subComponent == SUBCOMPONENT_LIFERAY_INSTALLATION) {
			return "liferay-installation";
		}
		else if (subComponent == SUBCOMPONENT_LOCAL_STAGING) {
			return "local-staging";
		}
		else if (subComponent == SUBCOMPONENT_LSVS) {
			return "lsvs";
		}
		else if (subComponent == SUBCOMPONENT_LUCENE) {
			return "lucene";
		}
		else if (subComponent == SUBCOMPONENT_MESSAGE_BOARDS) {
			return "message-boards";
		}
		else if (subComponent == SUBCOMPONENT_MOBILE) {
			return "mobile";
		}
		else if (subComponent == SUBCOMPONENT_MULTICAST) {
			return "multicast";
		}
		else if (subComponent == SUBCOMPONENT_NOTIFICATIONS) {
			return "notifications";
		}
		else if (subComponent == SUBCOMPONENT_OPENID) {
			return "openid";
		}
		else if (subComponent == SUBCOMPONENT_OTHER) {
			return "other";
		}
		else if (subComponent == SUBCOMPONENT_PERMISSIONS) {
			return "permissions";
		}
		else if (subComponent == SUBCOMPONENT_PERMISSIONS_ALGORITHM_MIGRATION) {
			return "permissions-algorithm-migration";
		}
		else if (subComponent == SUBCOMPONENT_PLUGINS) {
			return "plugins";
		}
		else if (subComponent == SUBCOMPONENT_PLUGINS_CUSTOM) {
			return "plugins-custom";
		}
		else if (subComponent == SUBCOMPONENT_PORTAL_SETTINGS) {
			return "portal-settings";
		}
		else if (subComponent == SUBCOMPONENT_PORTAL_UPGRADE) {
			return "portal-upgrade";
		}
		else if (subComponent == SUBCOMPONENT_REMOTE_STAGING) {
			return "remote-staging";
		}
		else if (subComponent == SUBCOMPONENT_ROLES_PERMISSIONS) {
			return "roles-permissions";
		}
		else if (subComponent == SUBCOMPONENT_SAML) {
			return "saml";
		}
		else if (subComponent == SUBCOMPONENT_SITE_PAGE_TEMPLATES) {
			return "site-page-templates";
		}
		else if (subComponent == SUBCOMPONENT_SITES_ORGANIZATIONS) {
			return "sites-organizations";
		}
		else if (subComponent == SUBCOMPONENT_SO_APPS) {
			return "so-apps";
		}
		else if (subComponent == SUBCOMPONENT_SO_HOOKS) {
			return "so-hooks";
		}
		else if (subComponent == SUBCOMPONENT_SOLR) {
			return "solr";
		}
		else if (subComponent == SUBCOMPONENT_SSO) {
			return "sso";
		}
		else if (subComponent == SUBCOMPONENT_STRUCTURES) {
			return "structures";
		}
		else if (subComponent == SUBCOMPONENT_SUBSCRIPTION_MANAGEMENT) {
			return "subscription-management";
		}
		else if (subComponent == SUBCOMPONENT_TEMPLATES) {
			return "templates";
		}
		else if (subComponent == SUBCOMPONENT_THEMES) {
			return "themes";
		}
		else if (subComponent == SUBCOMPONENT_UNICAST) {
			return "unicast";
		}
		else if (subComponent == SUBCOMPONENT_USER_MANAGEMENT_PERMISSIONS) {
			return "user-management-permissions";
		}
		else if (subComponent == SUBCOMPONENT_WEB_SERVICES) {
			return "web-services";
		}
		else if (subComponent == SUBCOMPONENT_WIKIS) {
			return "wikis";
		}
		else if (subComponent == SUBCOMPONENT_XSS) {
			return "xss";
		}

		return NOT_AVAILABLE;
	}

	public static int[] getSubcomponents(int component) {
		if (component == COMPONENT_AUTHENTICATION) {
			return SUBCOMPONENTS_AUTHENTICATION;
		}
		else if (component == COMPONENT_CLUSTERING) {
			return SUBCOMPONENTS_CLUSTERING;
		}
		else if (component == COMPONENT_COLLABORATION_SUITE) {
			return SUBCOMPONENTS_COLLABORATION_SUITE;
		}
		else if (component == COMPONENT_LAR_STAGING) {
			return SUBCOMPONENTS_LAR_STAGING;
		}
		else if (component == COMPONENT_LICENSE) {
			return SUBCOMPONENTS_LICENSE;
		}
		else if (component == COMPONENT_LIFERAY_API) {
			return SUBCOMPONENTS_LIFERAY_API;
		}
		else if (component == COMPONENT_LIFERAY_CONNECTED_SERVICES) {
			return SUBCOMPONENTS_LIFERAY_CONNECTED_SERVICES;
		}
		else if (component == COMPONENT_LIFERAY_SYNC) {
			return SUBCOMPONENTS_LIFERAY_SYNC;
		}
		else if (component == COMPONENT_PORTAL_ADMINISTRATION) {
			return SUBCOMPONENTS_PORTAL_ADMINISTRATION;
		}
		else if (component == COMPONENT_PORTAL_DEPLOYMENT) {
			return SUBCOMPONENTS_PORTAL_DEPLOYMENT;
		}
		else if (component == COMPONENT_SEARCH_INDEXING) {
			return SUBCOMPONENTS_SEARCH_INDEXING;
		}
		else if (component == COMPONENT_SECURITY) {
			return SUBCOMPONENTS_SECURITY;
		}
		else if (component == COMPONENT_UI) {
			return SUBCOMPONENTS_UI;
		}
		else if (component == COMPONENT_UPGRADE) {
			return SUBCOMPONENTS_UPGRADE;
		}
		else if (component == COMPONENT_WEB_CONTENT_MANAGEMENT) {
			return SUBCOMPONENTS_WEB_CONTENT_MANAGEMENT;
		}
		else {
			return new int[0];
		}
	}

	public static int[] getSystemStatuses(int component) {
		if (component == TicketEntryConstants.COMPONENT_LICENSE) {
			return _SYSTEM_STATUS_LICENSE;
		}
		else {
			return _SYSTEM_STATUS_GENERIC;
		}
	}

	public static String getSystemStatusLabel(int systemStatus) {
		if (systemStatus == SYSTEM_STATUS_INOPERABLE) {
			return "completely-inoperable-shutdown";
		}
		else if (systemStatus == SYSTEM_STATUS_INVALID) {
			return "invalid-error";
		}
		else if (systemStatus == SYSTEM_STATUS_LIMITED) {
			return "functioning-with-limited-capabilities";
		}
		else if (systemStatus == SYSTEM_STATUS_MINOR) {
			return "fully-functional-but-errors-exist";
		}
		else if (systemStatus == SYSTEM_STATUS_NEW_ACTIVATION) {
			return "new-activation-key";
		}
		else if (systemStatus == SYSTEM_STATUS_PERIODIC) {
			return "unstable-with-periodic-interruptions";
		}
		else if (systemStatus == SYSTEM_STATUS_UPGRADING) {
			return "upgrading-moving-servers-testing";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getUploadMethodLabel(int uploadMethod) {
		if (uploadMethod == UPLOAD_METHOD_FTP) {
			return "use-ftp-large-files";
		}
		else if (uploadMethod == UPLOAD_METHOD_HERE) {
			return "upload-here-small-files";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	public static String getWeightLabel(int weight) {
		if (weight == WEIGHT_HEAVY) {
			return "heavy";
		}
		else if (weight == WEIGHT_LIGHT) {
			return "light";
		}
		else if (weight == WEIGHT_NORMAL) {
			return "normal";
		}
		else {
			return NOT_AVAILABLE;
		}
	}

	private static final int[] _ENV_AS_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_AS_JBOSS_EAP_6_4, ENV_AS_JBOSS_EAP_7_0, ENV_AS_TCSERVER_3_1,
		ENV_AS_TCSERVER_3_2, ENV_AS_TOMCAT_8_0, ENV_AS_TOMCAT_8_5,
		ENV_AS_WEBLOGIC_12C_R1, ENV_AS_WEBLOGIC_12C_R2, ENV_AS_WEBSPHERE_8_5,
		ENV_AS_WILDFILY_10_0
	};

	private static final int[] _ENV_AS_PORTAL_VERSION_5_2 = {
		ENV_AS_GLASSFISH_2_1, ENV_AS_JBOSS_TOMCAT_4_2, ENV_AS_JBOSS_TOMCAT_5_0,
		ENV_AS_JONAS_TOMCAT_4_10, ENV_AS_ORACLEAS_10_1, ENV_AS_RESIN_3_1,
		ENV_AS_TOMCAT_5_5, ENV_AS_TOMCAT_6_0, ENV_AS_WEBLOGIC_9_2,
		ENV_AS_WEBLOGIC_10_0, ENV_AS_WEBLOGIC_10_3, ENV_AS_WEBSPHERE_6_1,
		ENV_AS_WEBSPHERE_7_0
	};

	private static final int[] _ENV_AS_PORTAL_VERSION_6_0 = {
		ENV_AS_GLASSFISH_3, ENV_AS_JBOSS_5_1, ENV_AS_JONAS_5_1,
		ENV_AS_RESIN_3_1, ENV_AS_TOMCAT_6_0, ENV_AS_WEBLOGIC_10_0,
		ENV_AS_WEBLOGIC_10_3, ENV_AS_WEBSPHERE_6_1, ENV_AS_WEBSPHERE_7_0
	};

	private static final int[] _ENV_AS_PORTAL_VERSION_6_1 = {
		ENV_AS_GLASSFISH_3_1, ENV_AS_JBOSS_AS_7_1, ENV_AS_JBOSS_EAP_5_1,
		ENV_AS_JBOSS_EAP_6_0, ENV_AS_JONAS_5_2, ENV_AS_RESIN_4, ENV_AS_TCAT_6_4,
		ENV_AS_TCSERVER_2_6, ENV_AS_TOMCAT_6_0, ENV_AS_TOMCAT_7_0,
		ENV_AS_TOMCAT_8_0, ENV_AS_WEBLOGIC_10_0, ENV_AS_WEBLOGIC_10_3,
		ENV_AS_WEBLOGIC_11, ENV_AS_WEBSPHERE_6_1, ENV_AS_WEBSPHERE_7_0,
		ENV_AS_WEBSPHERE_8, ENV_AS_WEBSPHERE_8_5
	};

	private static final int[] _ENV_AS_PORTAL_VERSION_6_2 = {
		ENV_AS_GLASSFISH_3_1, ENV_AS_GLASSFISH_4_0, ENV_AS_JBOSS_AS_7_1,
		ENV_AS_JBOSS_EAP_5_2, ENV_AS_JBOSS_EAP_6_0, ENV_AS_JBOSS_EAP_6_1,
		ENV_AS_JBOSS_EAP_6_2, ENV_AS_JBOSS_EAP_6_3, ENV_AS_JBOSS_EAP_6_4,
		ENV_AS_JBOSS_EAP_7_0, ENV_AS_RESIN_4, ENV_AS_TCAT_6_4, ENV_AS_TCAT_7_0,
		ENV_AS_TCSERVER_2_6, ENV_AS_TCSERVER_2_9, ENV_AS_TCSERVER_3_1,
		ENV_AS_TOMCAT_6_0, ENV_AS_TOMCAT_7_0, ENV_AS_TOMCAT_8_0,
		ENV_AS_WEBLOGIC_11_G, ENV_AS_WEBLOGIC_12C_R1, ENV_AS_WEBSPHERE_7_0,
		ENV_AS_WEBSPHERE_8, ENV_AS_WEBSPHERE_8_5
	};

	private static final int[] _ENV_AS_PORTAL_VERSION_OTHER = {
		ENV_AS_GLASSFISH_2_X, ENV_AS_GLASSFISH_3_X, ENV_AS_JBOSS_4_2_X,
		ENV_AS_JBOSS_5_X, ENV_AS_JBOSS_7_X, ENV_AS_JBOSS_EAP_6_0,
		ENV_AS_JBOSS_EAP_6_1, ENV_AS_JBOSS_EAP_6_2, ENV_AS_TCAT,
		ENV_AS_TCAT_6_4, ENV_AS_TOMCAT_5_5_X, ENV_AS_TOMCAT_6_X,
		ENV_AS_TOMCAT_7_0_X, ENV_AS_WEBLOGIC_10_X, ENV_AS_WEBLOGIC_11_X,
		ENV_AS_WEBLOGIC_9_X, ENV_AS_WEBSPHERE_6_1_X, ENV_AS_WEBSPHERE_7_X
	};

	private static final int[] _ENV_BROWSER_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_BROWSER_CHROME, ENV_BROWSER_EDGE, ENV_BROWSER_FIREFOX,
		ENV_BROWSER_FIREFOX_ESR_45, ENV_BROWSER_IE_9, ENV_BROWSER_IE_10,
		ENV_BROWSER_IE_11, ENV_BROWSER_IOS_SAFARI, ENV_BROWSER_MOBILE_CHROME,
		ENV_BROWSER_SAFARI_8, ENV_BROWSER_SAFARI_9, ENV_BROWSER_SAFARI_10
	};

	private static final int[] _ENV_BROWSER_PORTAL_VERSION_6_2 = {
		ENV_BROWSER_ANDROID_BROWSER_4_3_PLUS, ENV_BROWSER_CHROME,
		ENV_BROWSER_EDGE, ENV_BROWSER_FIREFOX, ENV_BROWSER_IE_6,
		ENV_BROWSER_IE_7, ENV_BROWSER_IE_8, ENV_BROWSER_IE_9, ENV_BROWSER_IE_10,
		ENV_BROWSER_IE_11, ENV_BROWSER_IOS_SAFARI, ENV_BROWSER_OTHER,
		ENV_BROWSER_SAFARI_5, ENV_BROWSER_SAFARI_6, ENV_BROWSER_SAFARI_10
	};

	private static final int[] _ENV_BROWSER_PORTAL_VERSION_OTHER = {
		ENV_BROWSER_CHROME, ENV_BROWSER_FIREFOX, ENV_BROWSER_IE_6,
		ENV_BROWSER_IE_7, ENV_BROWSER_IE_8, ENV_BROWSER_IE_9, ENV_BROWSER_IE_10,
		ENV_BROWSER_OTHER, ENV_BROWSER_SAFARI_5, ENV_BROWSER_SAFARI_6
	};

	private static final int[] _ENV_CS_PORTAL_VERSION_ANY = {
		ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD,
		ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE, ENV_CS_AWS_S3,
		ENV_CS_AZURE_FILES, ENV_CS_AZURE_SQL_DATABASES,
		ENV_CS_AZURE_VIRTUAL_MACHINES
	};

	private static final int[] _ENV_DB_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_DB_DB2_9_7, ENV_DB_DB2_10_1, ENV_DB_DB2_10_5, ENV_DB_MARIADB_10,
		ENV_DB_MYSQL_5_6, ENV_DB_MYSQL_5_7, ENV_DB_ORACLE_11G_RELEASE_2,
		ENV_DB_ORACLE_12C_RELEASE_1, ENV_DB_POSTGRESQL_9_3,
		ENV_DB_POSTGRESQL_9_4, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SQL_SERVER_2008_R2, ENV_DB_SQL_SERVER_2012,
		ENV_DB_SQL_SERVER_2014, ENV_DB_SQL_SERVER_2016, ENV_DB_SYBASE_ASE_15_7,
		ENV_DB_SYBASE_ASE_16
	};

	private static final int[] _ENV_DB_PORTAL_VERSION_5_2 = {
		ENV_DB_DB2_8_1, ENV_DB_DB2_9_7, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_POSTGRESQL_8_4,
		ENV_DB_SQL_SERVER_2000, ENV_DB_SQL_SERVER_2005, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SYBASE_ASE_15_0
	};

	private static final int[] _ENV_DB_PORTAL_VERSION_6_0 = {
		ENV_DB_DB2_8_1, ENV_DB_DB2_9_7, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_POSTGRESQL_8_4,
		ENV_DB_SQL_SERVER_2000, ENV_DB_SQL_SERVER_2005, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SYBASE_ASE_15_0
	};

	private static final int[] _ENV_DB_PORTAL_VERSION_6_1 = {
		ENV_DB_DB2_9_7, ENV_DB_DB2_10_5, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_MYSQL_5_5, ENV_DB_ORACLE_10G_RELEASE_2,
		ENV_DB_ORACLE_11G_RELEASE_1, ENV_DB_ORACLE_11G_RELEASE_2,
		ENV_DB_POSTGRESQL_8_4, ENV_DB_POSTGRESQL_9_0, ENV_DB_SQL_SERVER_2005,
		ENV_DB_SQL_SERVER_2008, ENV_DB_SQL_SERVER_2008_R2,
		ENV_DB_SYBASE_ASE_15_5
	};

	private static final int[] _ENV_DB_PORTAL_VERSION_6_2 = {
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

	private static final int[] _ENV_DB_PORTAL_VERSION_OTHER = {
		ENV_DB_DB2_8_2, ENV_DB_DB2_9_7, ENV_DB_HYPERSONIC, ENV_DB_MYSQL_5_0,
		ENV_DB_MYSQL_5_1, ENV_DB_MYSQL_5_5, ENV_DB_ORACLE_10G,
		ENV_DB_ORACLE_11G, ENV_DB_ORACLE_9I, ENV_DB_POSTGRESQL,
		ENV_DB_SQL_SERVER_2000, ENV_DB_SQL_SERVER_2005, ENV_DB_SQL_SERVER_2008,
		ENV_DB_SYBASE_ASE_15_5
	};

	private static final Map<String, int[]>
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0 = new HashMap<String, int[]>();

	private static final int[] _ENV_JVM_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_JVM_IBM_JDK_8, ENV_JVM_JAVA_8
	};

	private static final int[] _ENV_JVM_PORTAL_VERSION_6_1 = {
		ENV_JVM_JAVA_5, ENV_JVM_JAVA_6, ENV_JVM_JAVA_7, ENV_JVM_JAVA_8
	};

	private static final int[] _ENV_JVM_PORTAL_VERSION_6_2 = {
		ENV_JVM_IBM_JDK_6, ENV_JVM_IBM_JDK_7, ENV_JVM_IBM_JDK_8, ENV_JVM_JAVA_6,
		ENV_JVM_JAVA_7, ENV_JVM_JAVA_8, ENV_JVM_JROCKET_JDK_6
	};

	private static final int[] _ENV_JVM_PORTAL_VERSION_OTHER = {
		ENV_JVM_JAVA_5, ENV_JVM_JAVA_6, ENV_JVM_JAVA_7
	};

	private static final int[] _ENV_OS_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_OS_AIX_7_1, ENV_OS_CENTOS_6, ENV_OS_CENTOS_7, ENV_OS_DEBIAN_7,
		ENV_OS_DEBIAN_8, ENV_OS_ORACLE_LINUX_6, ENV_OS_ORACLE_LINUX_7,
		ENV_OS_RED_HAT_ENTERPRISE_6, ENV_OS_RED_HAT_ENTERPRISE_7,
		ENV_OS_SOLARIS_10, ENV_OS_SOLARIS_11, ENV_OS_SUSE_ENTERPRISE_LINUX_11,
		ENV_OS_SUSE_ENTERPRISE_LINUX_12, ENV_OS_UBUNTU_LTS_14_04,
		ENV_OS_WINDOWS_SERVER_2012
	};

	private static final int[] _ENV_OS_PORTAL_VERSION_5_2 = {
		ENV_OS_AIX, ENV_OS_CENTOS_4, ENV_OS_CENTOS_5, ENV_OS_HP_UX,
		ENV_OS_ORACLE_LINUX, ENV_OS_OTHER, ENV_OS_OTHER_LINUX_DISTIRBUTIONS,
		ENV_OS_RED_HAT_ENTERPRISE_4, ENV_OS_RED_HAT_ENTERPRISE_5,
		ENV_OS_SOLARIS_10, ENV_OS_UBUNTU, ENV_OS_WINDOWS_7,
		ENV_OS_WINDOWS_SERVER_2003, ENV_OS_WINDOWS_SERVER_2008,
		ENV_OS_WINDOWS_XP
	};

	private static final int[] _ENV_OS_PORTAL_VERSION_6_0 = {
		ENV_OS_AIX, ENV_OS_CENTOS_4, ENV_OS_CENTOS_5, ENV_OS_HP_UX,
		ENV_OS_ORACLE_LINUX, ENV_OS_OTHER, ENV_OS_OTHER_LINUX_DISTIRBUTIONS,
		ENV_OS_RED_HAT_ENTERPRISE_4, ENV_OS_RED_HAT_ENTERPRISE_5,
		ENV_OS_SOLARIS_10, ENV_OS_UBUNTU, ENV_OS_WINDOWS_7,
		ENV_OS_WINDOWS_SERVER_2003, ENV_OS_WINDOWS_SERVER_2008,
		ENV_OS_WINDOWS_XP
	};

	private static final int[] _ENV_OS_PORTAL_VERSION_6_1 = {
		ENV_OS_AIX_6_1, ENV_OS_AIX_7_1, ENV_OS_CENTOS_5, ENV_OS_CENTOS_6,
		ENV_OS_HP_UX, ENV_OS_MAC_OS_X_10_5_PLUS, ENV_OS_ORACLE_LINUX_6,
		ENV_OS_OTHER, ENV_OS_OTHER_LINUX_DISTIRBUTIONS,
		ENV_OS_RED_HAT_ENTERPRISE_5, ENV_OS_RED_HAT_ENTERPRISE_6,
		ENV_OS_SOLARIS_10, ENV_OS_SOLARIS_11, ENV_OS_UBUNTU_10,
		ENV_OS_UBUNTU_11, ENV_OS_WINDOWS_7, ENV_OS_WINDOWS_SERVER_2008_R2,
		ENV_OS_WINDOWS_XP
	};

	private static final int[] _ENV_OS_PORTAL_VERSION_6_2 = {
		ENV_OS_AIX_6_1, ENV_OS_AIX_7_1, ENV_OS_CENTOS_5, ENV_OS_CENTOS_6,
		ENV_OS_CENTOS_7, ENV_OS_DEBIAN_6_0, ENV_OS_DEBIAN_8, ENV_OS_HP_UX,
		ENV_OS_OPENSUSE_13_1, ENV_OS_ORACLE_LINUX_6, ENV_OS_OTHER,
		ENV_OS_RED_HAT_ENTERPRISE_5, ENV_OS_RED_HAT_ENTERPRISE_6,
		ENV_OS_RED_HAT_ENTERPRISE_7, ENV_OS_SOLARIS_10, ENV_OS_SOLARIS_11,
		ENV_OS_SUSE_ENTERPRISE_LINUX_11, ENV_OS_UBUNTU_10, ENV_OS_UBUNTU_11,
		ENV_OS_UBUNTU_12, ENV_OS_UBUNTU_13, ENV_OS_UBUNTU_LTS_14_04,
		ENV_OS_WINDOWS_SERVER_2008, ENV_OS_WINDOWS_SERVER_2012
	};

	private static final int[] _ENV_OS_PORTAL_VERSION_OTHER = {
		ENV_OS_LINUX, ENV_OS_MAC_OS_X, ENV_OS_OTHER, ENV_OS_WINDOWS_7,
		ENV_OS_WINDOWS_XP, ENV_OS_WINDOWS_SERVER_2003,
		ENV_OS_WINDOWS_SERVER_2008, ENV_OS_RED_HAT,
		ENV_OS_WINDOWS_SERVER_2008_R2
	};

	private static final Map<String, int[]> _ENV_PORTAL_VERSION_5_2 =
		new HashMap<String, int[]>();

	private static final Map<String, int[]> _ENV_PORTAL_VERSION_6_0 =
		new HashMap<String, int[]>();

	private static final Map<String, int[]> _ENV_PORTAL_VERSION_6_1 =
		new HashMap<String, int[]>();

	private static final Map<String, int[]> _ENV_PORTAL_VERSION_6_2 =
		new HashMap<String, int[]>();

	private static final Map<String, int[]> _ENV_PORTAL_VERSION_OTHER =
		new HashMap<String, int[]>();

	private static final int[] _ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_0 = {
		ENV_SEARCH_ELASTICSEARCH, ENV_SEARCH_KIBANA_4_4, ENV_SEARCH_MARVEL_2_2,
		ENV_SEARCH_SHIELD_2_2, ENV_SEARCH_SOLR, ENV_SEARCH_SOLRCLOUD
	};

	private static final Map<String, int[]> _ENV_SOCIAL_OFFICE_VERSION_2 =
		new HashMap<String, int[]>();

	private static final Map<String, int[]> _ENV_SOCIAL_OFFICE_VERSION_3 =
		new HashMap<String, int[]>();

	private static final int[] _LICENSE_PURPOSES = {
		LICENSE_PURPOSE_PERMANENT_KEY, LICENSE_PURPOSE_ELASTIC_MONTHLY_KEY,
		LICENSE_PURPOSE_TEMPORARY_KEY
	};

	private static final int[] _LICENSE_TYPES = {
		LICENSE_TYPE_STANDALONE, LICENSE_TYPE_CLUSTER, LICENSE_TYPE_DEVELOPER
	};

	private static final int[] _SYSTEM_STATUS_GENERIC = {
		SYSTEM_STATUS_INOPERABLE, SYSTEM_STATUS_LIMITED, SYSTEM_STATUS_MINOR,
		SYSTEM_STATUS_PERIODIC
	};

	private static final int[] _SYSTEM_STATUS_LICENSE = {
		SYSTEM_STATUS_INOPERABLE, SYSTEM_STATUS_INVALID,
		SYSTEM_STATUS_NEW_ACTIVATION, SYSTEM_STATUS_UPGRADING
	};

	static {
		_ENV_PORTAL_VERSION_5_2.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_5_2);
		_ENV_PORTAL_VERSION_5_2.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_5_2.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_5_2);
		_ENV_PORTAL_VERSION_5_2.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_5_2.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_5_2);

		_ENV_PORTAL_VERSION_6_0.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_0);
		_ENV_PORTAL_VERSION_6_0.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_6_0.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_0);
		_ENV_PORTAL_VERSION_6_0.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_6_0.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_0);

		_ENV_PORTAL_VERSION_6_1.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_1);
		_ENV_PORTAL_VERSION_6_1.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_6_1.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_1);
		_ENV_PORTAL_VERSION_6_1.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_6_1);
		_ENV_PORTAL_VERSION_6_1.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_1);

		_ENV_PORTAL_VERSION_6_2.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_2);
		_ENV_PORTAL_VERSION_6_2.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_6_2);
		_ENV_PORTAL_VERSION_6_2.put(
			LIST_TYPE_ENV_CS, _ENV_CS_PORTAL_VERSION_ANY);
		_ENV_PORTAL_VERSION_6_2.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_2);
		_ENV_PORTAL_VERSION_6_2.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_6_2);
		_ENV_PORTAL_VERSION_6_2.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_2);

		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_AS, _ENV_AS_DIGITAL_ENTERPRISE_VERSION_7_0);
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_DIGITAL_ENTERPRISE_VERSION_7_0);
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_CS, _ENV_CS_PORTAL_VERSION_ANY);
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_DB, _ENV_DB_DIGITAL_ENTERPRISE_VERSION_7_0);
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_DIGITAL_ENTERPRISE_VERSION_7_0);
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_OS, _ENV_OS_DIGITAL_ENTERPRISE_VERSION_7_0);
		_ENV_DIGITAL_ENTERPRISE_VERSION_7_0.put(
			LIST_TYPE_ENV_SEARCH, _ENV_SEARCH_DIGITAL_ENTERPRISE_VERSION_7_0);

		_ENV_PORTAL_VERSION_OTHER.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_OTHER.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_OTHER.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_OTHER.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_ENV_PORTAL_VERSION_OTHER.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_OTHER);

		_ENV_SOCIAL_OFFICE_VERSION_2.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_1);
		_ENV_SOCIAL_OFFICE_VERSION_2.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_ENV_SOCIAL_OFFICE_VERSION_2.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_1);
		_ENV_SOCIAL_OFFICE_VERSION_2.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_ENV_SOCIAL_OFFICE_VERSION_2.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_1);

		_ENV_SOCIAL_OFFICE_VERSION_3.put(
			LIST_TYPE_ENV_AS, _ENV_AS_PORTAL_VERSION_6_2);
		_ENV_SOCIAL_OFFICE_VERSION_3.put(
			LIST_TYPE_ENV_BROWSER, _ENV_BROWSER_PORTAL_VERSION_OTHER);
		_ENV_SOCIAL_OFFICE_VERSION_3.put(
			LIST_TYPE_ENV_DB, _ENV_DB_PORTAL_VERSION_6_2);
		_ENV_SOCIAL_OFFICE_VERSION_3.put(
			LIST_TYPE_ENV_JVM, _ENV_JVM_PORTAL_VERSION_OTHER);
		_ENV_SOCIAL_OFFICE_VERSION_3.put(
			LIST_TYPE_ENV_OS, _ENV_OS_PORTAL_VERSION_6_2);
	}

}