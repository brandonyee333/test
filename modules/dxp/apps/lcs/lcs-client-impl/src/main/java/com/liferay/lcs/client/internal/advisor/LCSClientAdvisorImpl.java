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

package com.liferay.lcs.client.internal.advisor;

import com.liferay.lcs.client.advisor.LCSClientAdvisor;
import com.liferay.lcs.client.advisor.LCSClusterEntryTokenAdvisor;
import com.liferay.lcs.client.configuration.LCSConfiguration;
import com.liferay.lcs.client.configuration.LCSConfigurationProvider;
import com.liferay.lcs.client.platform.exception.LCSException;
import com.liferay.lcs.client.platform.portal.LCSClusterEntry;
import com.liferay.lcs.client.platform.portal.LCSClusterEntryClient;
import com.liferay.lcs.client.platform.portal.LCSClusterNode;
import com.liferay.lcs.client.platform.portal.LCSClusterNodeClient;
import com.liferay.lcs.client.platform.portal.LCSProject;
import com.liferay.lcs.client.platform.portal.LCSProjectClient;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.PortletQName;
import com.liferay.portal.kernel.util.Http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(immediate = true, service = LCSClientAdvisor.class)
public class LCSClientAdvisorImpl implements LCSClientAdvisor {

	public String getLCSClusterEntryLayoutURL() throws LCSException {
		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeClient.fetchLCSClusterNode(_lcsKeyAdvisor.getKey());

		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryClient.getLCSClusterEntry(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryId());

		LCSProject lcsProject = _lcsProjectClient.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

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

	@Override
	public String getLCSClusterEntryName() throws LCSException {
		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryClient.getLCSClusterEntry(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryId());

		return lcsClusterEntry.getName();
	}

	public String getLCSClusterNodeLayoutURL() throws LCSException {
		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeClient.fetchLCSClusterNode(_lcsKeyAdvisor.getKey());

		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryClient.getLCSClusterEntry(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryId());

		LCSProject lcsProject = _lcsProjectClient.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSClusterEntryId"),
			String.valueOf(lcsClusterEntry.getLcsClusterEntryId()));
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSClusterNodeId"),
			String.valueOf(lcsClusterNode.getLcsClusterNodeId()));
		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return _getLCSLayoutURL(
			_lcsPlatformPortalLayoutLCSClusterNode, publicRenderParameters);
	}

	@Override
	public String getLCSClusterNodeName() throws LCSException {
		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeClient.fetchLCSClusterNode(_lcsKeyAdvisor.getKey());

		return lcsClusterNode.getName();
	}

	public String getLCSProjectLayoutURL() throws LCSException {
		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryClient.getLCSClusterEntry(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryId());

		LCSProject lcsProject = _lcsProjectClient.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

		Map<String, String> publicRenderParameters = new HashMap<>();

		publicRenderParameters.put(
			_getPublicRenderParameterName("layoutLCSProjectId"),
			String.valueOf(lcsProject.getLcsProjectId()));

		return _getLCSLayoutURL(
			_lcsPlatformPortalLayoutLCSProject, publicRenderParameters);
	}

	@Override
	public String getLCSProjectName() throws LCSException {
		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryClient.getLCSClusterEntry(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryId());

		LCSProject lcsProject = _lcsProjectClient.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

		return lcsProject.getName();
	}

	public String getRegistrationLayoutURL() throws LCSException {
		LCSClusterNode lcsClusterNode =
			_lcsClusterNodeClient.fetchLCSClusterNode(_lcsKeyAdvisor.getKey());

		LCSClusterEntry lcsClusterEntry =
			_lcsClusterEntryClient.getLCSClusterEntry(
				_lcsClusterEntryTokenAdvisor.getLcsClusterEntryId());

		LCSProject lcsProject = _lcsProjectClient.getLCSProject(
			lcsClusterEntry.getLcsProjectId());

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

	@Activate
	protected void activate() {
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

		StringBundler sb = new StringBundler((4 * parms.size()) + 1);

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
	private LCSClusterEntryClient _lcsClusterEntryClient;

	@Reference
	private LCSClusterEntryTokenAdvisor _lcsClusterEntryTokenAdvisor;

	@Reference
	private LCSClusterNodeClient _lcsClusterNodeClient;

	@Reference
	private LCSConfigurationProvider _lcsConfigurationProvider;

	@Reference
	private LCSKeyAdvisor _lcsKeyAdvisor;

	private String _lcsPlatformPortalHostName;
	private int _lcsPlatformPortalHostPort;
	private String _lcsPlatformPortalLayoutLCSClusterEntry;
	private String _lcsPlatformPortalLayoutLCSClusterNode;
	private String _lcsPlatformPortalLayoutLCSProject;
	private String _lcsPlatformPortalProtocol;

	@Reference
	private LCSProjectClient _lcsProjectClient;

}