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

package com.liferay.osb.asah.common.constants;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Brian Wing Shun Chan
 */
public class ServiceConstants {

	public static final String LCP_ENGINE_ELASTICSEARCH_SERVER_IP;

	/**
	 * @deprecated As of 2.10.0, replaced by {@link ProjectThreadLocal#getProjectId()}}
	 */
	@Deprecated
	public static final String LCP_PROJECT_ID = System.getenv("LCP_PROJECT_ID");

	public static final boolean OSB_ASAH_MULTITENANCY_ENABLED =
		Boolean.parseBoolean(System.getenv("OSB_ASAH_MULTITENANCY_ENABLED"));

	public static final String POSTGRESQL_SERVER_IP;

	public static final String TRINO_SERVER_IP;

	public static final String URL_BACKEND;

	public static final String URL_BACKEND_INTERNAL;

	public static final String URL_BATCH_CURATOR;

	public static final String URL_EXTRACTOR;

	public static final String URL_FRONTEND;

	public static final String URL_PUBLISHER;

	public static final String URL_PUBSUB_EMULATOR;

	public static final String URL_REDIS;

	public static final String URL_SALESFORCE_EXTRACTOR;

	public static final String URL_STREAM_CURATOR;

	public static boolean isInternalServiceURL(String url) {
		return _internalServiceURLs.contains(url);
	}

	private static String _doGetURL(
		String serviceName, String port, boolean external) {

		serviceName = serviceName.replace("_", "");
		serviceName = serviceName.toLowerCase();

		String internalServiceURL = _setInternalURL(serviceName, port);

		if (external && (LCP_PROJECT_ID != null) &&
			(_LCP_SERVICE_DOMAIN != null)) {

			return "https://osbasah" + serviceName + "-" + LCP_PROJECT_ID +
				"." + _LCP_SERVICE_DOMAIN;
		}

		if (StringUtils.isNotEmpty(_DOMAIN_SUFFIX)) {
			return "https://osbasah" + serviceName + "-" + _DOMAIN_SUFFIX;
		}

		return internalServiceURL;
	}

	private static String _getElasticsearchClusterURL() {
		String lcpEngineElasticsearchServerIP = System.getenv(
			"LCP_ENGINE_ELASTICSEARCH_SERVER_IP");

		if (StringUtils.isNotEmpty(lcpEngineElasticsearchServerIP)) {
			return lcpEngineElasticsearchServerIP;
		}

		String lcpProjectCluster = System.getenv("LCP_PROJECT_CLUSTER");

		if (StringUtils.isEmpty(lcpProjectCluster)) {
			return _LOCALHOST_IP;
		}

		StringBuilder sb = new StringBuilder();

		sb.append("esmaster--cluster.");

		if (lcpProjectCluster.equals("europe-west2-c1")) {
			sb.append("qbcxohfftcjtgoiczl");
		}
		else if (lcpProjectCluster.equals("europe-west3-c1")) {
			sb.append("qiwvfjzwojvfzdelon");
		}
		else if (lcpProjectCluster.equals("southamerica-east1-c1")) {
			sb.append("djjykaalhvzcovrqam");
		}
		else if (lcpProjectCluster.equals("us-west1-c1")) {
			sb.append("oweobqtbjflwouqzpe");
		}
		else {
			throw new RuntimeException("Invalid LCP project cluster");
		}

		sb.append(".svc.cluster.local");

		return sb.toString();
	}

	private static String _getPostgreSQLClusterURL() {
		String postgresServerIp = System.getenv("POSTGRESQL_SERVER_IP");

		if (StringUtils.isNotEmpty(postgresServerIp)) {
			return postgresServerIp;
		}

		return _LOCALHOST_IP;
	}

	private static String _getTrinoClusterURL() {
		String trinoServerIp = System.getenv("TRINO_SERVER_IP");

		if (StringUtils.isNotEmpty(trinoServerIp)) {
			return trinoServerIp;
		}

		return _LOCALHOST_IP;
	}

	private static String _getURL(
		String serviceName, String port, boolean external) {

		String url = null;

		if (_MONOLITH_ENABLED) {
			url = _doGetURL("MONOLITH", "8080", true);
		}
		else {
			url = _doGetURL(serviceName, port, external);
		}

		String osbAsahURLKey = "OSB_ASAH_" + serviceName + "_URL";

		String overrideURL = System.getenv(osbAsahURLKey);

		if (overrideURL != null) {
			url = overrideURL;
		}

		if (_log.isInfoEnabled()) {
			_log.info(osbAsahURLKey + ": " + url);
		}

		return url;
	}

	private static String _setInternalURL(String serviceName, String port) {
		String internalServiceURL = null;

		if (_MONOLITH_ENABLED) {
			internalServiceURL = "http://localhost:8080";
		}
		else {
			serviceName = serviceName.replace("_", "");
			serviceName = serviceName.toLowerCase();

			internalServiceURL = "http://osbasah" + serviceName + ":" + port;
		}

		_internalServiceURLs.add(internalServiceURL);

		return internalServiceURL;
	}

	private static final String _DOMAIN_SUFFIX = System.getenv(
		"OSB_ASAH_LCP_DOMAIN_SUFFIX");

	private static final String _LCP_SERVICE_DOMAIN = System.getenv(
		"LCP_SERVICE_DOMAIN");

	@SuppressWarnings("PMD.AvoidUsingHardCodedIP")
	private static final String _LOCALHOST_IP = "127.0.0.1";

	private static final boolean _MONOLITH_ENABLED = Boolean.parseBoolean(
		System.getenv("OSB_ASAH_MONOLITH_ENABLED"));

	private static final Log _log = LogFactory.getLog(ServiceConstants.class);

	private static final Set<String> _internalServiceURLs;

	static {
		LCP_ENGINE_ELASTICSEARCH_SERVER_IP = _getElasticsearchClusterURL();

		_internalServiceURLs = new HashSet<>();

		if (_log.isInfoEnabled()) {
			_log.info(
				"LCP_ENGINE_ELASTICSEARCH_SERVER_IP: " +
					LCP_ENGINE_ELASTICSEARCH_SERVER_IP);

			_log.info("LCP_PROJECT_ID: " + LCP_PROJECT_ID);
		}

		if (_log.isInfoEnabled()) {
			_log.info("OSB_ASAH_LCP_DOMAIN_SUFFIX: " + _DOMAIN_SUFFIX);

			if (_MONOLITH_ENABLED) {
				_log.info(
					"OSB_ASAH_MONOLITH_URL: " +
						_doGetURL("MONOLITH", "8080", true));
			}
		}

		POSTGRESQL_SERVER_IP = _getPostgreSQLClusterURL();
		TRINO_SERVER_IP = _getTrinoClusterURL();

		URL_BACKEND = _getURL("BACKEND", "8080", true);
		URL_BACKEND_INTERNAL = _setInternalURL("BACKEND", "8080");
		URL_BATCH_CURATOR = _getURL("BATCH_CURATOR", "8080", false);
		URL_EXTRACTOR = _getURL("EXTRACTOR", "8080", false);
		URL_FRONTEND = System.getenv("OSB_FARO_FRONTEND_URL");
		URL_PUBLISHER = _getURL("PUBLISHER", "8080", true);
		URL_PUBSUB_EMULATOR = _getURL("PUBSUB_EMULATOR", "8095", false);
		URL_REDIS = _getURL("REDIS", "6379", false);
		URL_SALESFORCE_EXTRACTOR = _getURL(
			"SALESFORCE_EXTRACTOR", "8080", false);
		URL_STREAM_CURATOR = _getURL("STREAM_CURATOR", "8080", false);
	}

}