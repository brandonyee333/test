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

package com.liferay.lcs.client.internal.util;

import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.internal.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.platform.portal.LCSClusterNode;
import com.liferay.lcs.client.platform.portal.LCSProject;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 */
@Component(immediate = true, service = LCSLayoutBuilder.class)
public class LCSLayoutBuilder {

	@Activate
	public void activate() {
		LCSConfiguration lcsConfiguration =
			_lcsConfigurationProvider.getLCSConfiguration();

		_lcsPlatformPortalHostName =
			lcsConfiguration.lcsPlatformPortalHostName();
		_lcsPlatformPortalHostPort =
			lcsConfiguration.lcsPlatformPortalHostPort();
		_lcsPlatformPortalLayoutLCSClusterEntry =
			lcsConfiguration.lcsPlatformPortalLayoutLCSClusterEntry();
		_lcsPlatformPortalLayoutLCSClusterNode =
			lcsConfiguration.lcsPlatformPortalLayoutLCSClusterNode();
		_lcsPlatformPortalLayoutLCSProject =
			lcsConfiguration.lcsPlatformPortalLayoutLCSProject();
		_lcsPlatformPortalProtocol =
			lcsConfiguration.lcsPlatformPortalProtocol();
	}

	public String getLCSClusterEntryLayoutURL(
		LCSProject lcsProject, LCSClusterNode lcsClusterNode) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterNode.getLcsClusterEntryId()));
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return _getLCSLayoutURL(
			_lcsPlatformPortalLayoutLCSClusterEntry, publicRenderParameters);
	}

	public String getLCSClusterNodeLayoutURL(
		LCSProject lcsProject, LCSClusterNode lcsClusterNode) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterNode.getLcsClusterEntryId()));
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSClusterNodeId"),
			String.valueOf(lcsClusterNode.getLcsClusterNodeId()));
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return _getLCSLayoutURL(
			_lcsPlatformPortalLayoutLCSClusterNode, publicRenderParameters);
	}

	public String getLCSProjectLayoutURL(LCSProject lcsProject) {
		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return _getLCSLayoutURL(
			_lcsPlatformPortalLayoutLCSProject, publicRenderParameters);
	}

	public String getRegistrationLayoutURL(
		LCSProject lcsProject, LCSClusterNode lcsClusterNode) {

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			_getPublicRenderParameterName("environmentPage"), "registration");
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterNode.getLcsClusterEntryId()));
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return _getLCSLayoutURL(
			_lcsPlatformPortalLayoutLCSClusterEntry, publicRenderParameters);
	}

	private static String _getPublicRenderParameterName(String parameterName) {
		StringBundler sb = new StringBundler(2);

		sb.append(PortletQName.PUBLIC_RENDER_PARAMETER_NAMESPACE);
		sb.append(parameterName);

		return sb.toString();
	}

	private String _getLCSLayoutURL(
		String friendlyURL, Map<String, String> parms) {

		String layoutFullURL = _getLCSPortalURL() + friendlyURL;

		if (parms.isEmpty()) {
			return layoutFullURL;
		}

		StringBundler sb = new StringBundler(4 * parms.size() + 1);

		sb.append(layoutFullURL);
		sb.append(StringPool.QUESTION);

		Set<String> keys = parms.keySet();

		Iterator<String> iterator = keys.iterator();

		while (iterator.hasNext()) {
			String parm = iterator.next();

			sb.append(parm);

			sb.append(StringPool.EQUAL);
			sb.append(parms.get(parm));

			if (iterator.hasNext()) {
				sb.append(StringPool.AMPERSAND);
			}
		}

		return sb.toString();
	}

	private String _getLCSPortalURL() {
		StringBundler sb = new StringBundler(5);

		sb.append(_lcsPlatformPortalProtocol);
		sb.append(Http.PROTOCOL_DELIMITER);
		sb.append(_lcsPlatformPortalHostName);

		if ((_lcsPlatformPortalHostPort == Http.HTTP_PORT) ||
			(_lcsPlatformPortalHostPort == Http.HTTPS_PORT)) {

			return sb.toString();
		}

		sb.append(StringPool.COLON);
		sb.append(_lcsPlatformPortalHostPort);

		return sb.toString();
	}

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	private String _lcsPlatformPortalHostName;
	private int _lcsPlatformPortalHostPort;
	private String _lcsPlatformPortalLayoutLCSClusterEntry;
	private String _lcsPlatformPortalLayoutLCSClusterNode;
	private String _lcsPlatformPortalLayoutLCSProject;
	private String _lcsPlatformPortalProtocol;

}