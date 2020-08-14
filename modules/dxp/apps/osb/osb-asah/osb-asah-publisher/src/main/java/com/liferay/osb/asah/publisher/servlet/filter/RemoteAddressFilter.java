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

package com.liferay.osb.asah.publisher.servlet.filter;

import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.common.util.SetUtil;

import java.io.IOException;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnProperty("osb.asah.remote.address.filter.enabled")
@MonolithExclude
public class RemoteAddressFilter extends OncePerRequestFilter {

	@Override
	public void doFilterInternal(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws IOException, ServletException {

		if (!_isIPAddressAuthorized(httpServletRequest)) {
			_logUnauthorizedIPAddress(httpServletRequest);

			httpServletResponse.sendError(
				HttpServletResponse.SC_UNAUTHORIZED, "Invalid IP Address");

			return;
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private String _getRemoteAddress(HttpServletRequest httpServletRequest) {
		String xForwardedForHeader = httpServletRequest.getHeader(
			"X-Forwarded-For");

		if (StringUtils.isBlank(xForwardedForHeader)) {
			return httpServletRequest.getRemoteAddr();
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Retrieving remote address from X-Forwarded-For header " +
					xForwardedForHeader);
		}

		String[] xForwardedForHeaderArray = StringUtils.split(
			xForwardedForHeader, ",");

		return xForwardedForHeaderArray[0];
	}

	@PostConstruct
	private void _init() {
		try {
			for (String hostAllowed : SetUtil.of(_hostsAllowed)) {
				if (_log.isInfoEnabled()) {
					_log.info(
						String.format(
							"Adding %s to the list of allowed hosts",
							hostAllowed));
				}

				_ipV4AddressValidators.add(
					new IPV4AddressValidator(hostAllowed));
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private boolean _isIPAddressAuthorized(
		HttpServletRequest httpServletRequest) {

		for (IPV4AddressValidator ipV4AddressValidator :
				_ipV4AddressValidators) {

			if (ipV4AddressValidator.isValid(
					_getRemoteAddress(httpServletRequest))) {

				return true;
			}
		}

		return false;
	}

	private void _logUnauthorizedIPAddress(
		HttpServletRequest httpServletRequest) {

		if (_log.isWarnEnabled()) {
			_log.warn(
				String.format(
					"%s attempted to access %s with an invalid IP address ",
					_getRemoteAddress(httpServletRequest),
					httpServletRequest.getRequestURI()));
		}
	}

	private static final Log _log = LogFactory.getLog(
		RemoteAddressFilter.class);

	@Value("${osb.asah.remote.address.filter.hosts.allowed:127.0.0.1}")
	private String[] _hostsAllowed;

	private final List<IPV4AddressValidator> _ipV4AddressValidators =
		new ArrayList<>();

	private static class IPV4AddressValidator {

		public IPV4AddressValidator(String hostAllowed) throws Exception {
			String[] ipAddressAndNetmask = StringUtils.split(hostAllowed, "/");

			_allowedIpAddress = InetAddress.getByName(ipAddressAndNetmask[0]);

			_allowedIpAddressBytes = _allowedIpAddress.getAddress();

			if (_hasNetmask(ipAddressAndNetmask)) {
				_netmask = _getNetmask(ipAddressAndNetmask[1]);
			}
		}

		public boolean isValid(String ipAddress) {
			InetAddress inetAddress = null;

			try {
				inetAddress = InetAddress.getByName(ipAddress);
			}
			catch (UnknownHostException uhe) {
				return false;
			}

			byte[] inetAddressBytes = inetAddress.getAddress();

			if (!_isSameProtocol(inetAddressBytes)) {
				return false;
			}

			if (_netmask == null) {
				return _allowedIpAddress.equals(inetAddress);
			}

			for (int i = 0; i < _netmask.length; i++) {
				if ((inetAddressBytes[i] & _netmask[i]) !=
						(_allowedIpAddressBytes[i] & _netmask[i])) {

					return false;
				}
			}

			return true;
		}

		private byte[] _getNetmask(String netmask) {
			int cidr = Integer.parseInt(netmask);

			int netmaskBytes = cidr / 8;

			byte[] bytesNetmask = new byte[4];

			for (int i = 0; i < netmaskBytes; i++) {
				bytesNetmask[i] = (byte)_BYTE[8];
			}

			if (netmaskBytes < bytesNetmask.length) {
				int byteOffset = cidr % 8;

				bytesNetmask[netmaskBytes] = (byte)_BYTE[byteOffset];
			}

			return bytesNetmask;
		}

		private boolean _hasNetmask(String[] ipAddressAndNetmask) {
			if (ipAddressAndNetmask.length > 1) {
				return true;
			}

			return false;
		}

		private boolean _isSameProtocol(byte[] ipAddressBytes) {
			if (_allowedIpAddressBytes.length == ipAddressBytes.length) {
				return true;
			}

			return false;
		}

		private static final int[] _BYTE = {
			0b00000000, 0b10000000, 0b11000000, 0b11100000, 0b11110000,
			0b11111000, 0b11111100, 0b11111110, 0b11111111
		};

		private final InetAddress _allowedIpAddress;
		private final byte[] _allowedIpAddressBytes;
		private byte[] _netmask;

	}

}