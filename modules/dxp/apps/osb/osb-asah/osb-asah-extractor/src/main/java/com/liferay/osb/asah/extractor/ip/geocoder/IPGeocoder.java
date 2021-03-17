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

package com.liferay.osb.asah.extractor.ip.geocoder;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.zip.GZIPInputStream;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class IPGeocoder {

	public IPInfo getIPInfo(String ipAddress) {
		Location location = _lookupService.getLocation(ipAddress);

		if (location != null) {
			return new IPInfo(location);
		}

		if (_logger.isDebugEnabled()) {
			_logger.debug("Unable to get IP info for " + ipAddress);
		}

		try {
			InetAddress inetAddress = InetAddress.getByName(ipAddress);

			if (inetAddress.isLoopbackAddress() ||
				inetAddress.isSiteLocalAddress()) {

				return IPInfo.LOCAL_NETWORK;
			}
		}
		catch (UnknownHostException unknownHostException) {
			if (_logger.isDebugEnabled()) {
				_logger.debug(
					unknownHostException.getMessage(), unknownHostException);
			}
		}

		return null;
	}

	@PreDestroy
	protected void destroy() {
		_lookupService = null;
	}

	@PostConstruct
	protected void init() {
		try {
			File ipGeocoderFile = _getIPGeocoderFile();

			_lookupService = new LookupService(
				ipGeocoderFile, LookupService.GEOIP_MEMORY_CACHE);
		}
		catch (IOException ioe) {
			_logger.error("Unable to load MaxMind Geo IP data", ioe);

			throw new IllegalStateException(ioe);
		}
	}

	private InputStream _getDatasetInputStream() throws IOException {
		if (_resource.length > 0) {
			return _resource[0].getInputStream();
		}

		String classpath = System.getProperty("java.class.path");

		for (String path : classpath.split(File.pathSeparator)) {
			if (path.matches(".*?(geoip.*gz)$")) {
				return new FileInputStream(path);
			}
		}

		throw new RuntimeException("Unable to load GeoIP dataset");
	}

	private File _getIPGeocoderFile() throws IOException {
		File file = new File(
			System.getProperty("java.io.tmpdir") +
				"/liferay/geoip/GeoIPCity.dat");

		if (file.exists()) {
			return file;
		}

		synchronized (this) {
			_write(file, new GZIPInputStream(_getDatasetInputStream()));
		}

		return file;
	}

	private void _write(File file, InputStream inputStream) throws IOException {
		File parentFile = file.getParentFile();

		if (parentFile == null) {
			return;
		}

		try {
			if (!parentFile.exists() && !parentFile.mkdirs()) {
				throw new IOException("Unable to create path");
			}
		}
		catch (SecurityException se) {
			throw new IOException("Unable to create path", se);
		}

		BufferedInputStream bufferedInputStream = new BufferedInputStream(
			inputStream);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
			new FileOutputStream(file));

		try {
			int i = 0;

			while ((i = bufferedInputStream.read()) != -1) {
				bufferedOutputStream.write(i);
			}
		}
		finally {
			bufferedOutputStream.flush();

			bufferedInputStream.close();

			bufferedOutputStream.close();
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		IPGeocoder.class);

	private volatile LookupService _lookupService;

	@Value("classpath*:META-INF/com.maxmind.geoip.dat-*.gz")
	private Resource[] _resource;

}