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

package com.liferay.osb.asah.dataflow.ingestion.event.ip.geocoder;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Inácio Nery
 */
public class IPGeocoder {

	public static IPInfo getIPInfo(String ipAddress) {
		if (StringUtils.isBlank(ipAddress)) {
			return null;
		}

		Location location = _ipGeocoder._lookupService.getLocation(ipAddress);

		if (location != null) {
			return new IPInfo(location);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Unable to get IP info for " + ipAddress);
		}

		try {
			InetAddress inetAddress = InetAddress.getByName(ipAddress);

			if (inetAddress.isLoopbackAddress() ||
				inetAddress.isSiteLocalAddress()) {

				return IPInfo.LOCAL_NETWORK;
			}
		}
		catch (UnknownHostException unknownHostException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					unknownHostException.getMessage(), unknownHostException);
			}
		}

		return null;
	}

	private IPGeocoder() {
		try {
			_lookupService = new LookupService(
				_getIPGeocoderFile(), LookupService.GEOIP_MEMORY_CACHE);
		}
		catch (IOException ioException) {
			_log.error("Unable to load MaxMind Geo IP data", ioException);

			throw new IllegalStateException(ioException);
		}

		if (_log.isInfoEnabled()) {
			_log.info("IPGeocoder loaded successfully");
		}
	}

	private InputStream _getDatasetInputStream() {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		return classLoader.getResourceAsStream(
			"META-INF/com.maxmind.geoip.dat.gz");
	}

	private File _getIPGeocoderFile() throws IOException {
		Path geoip = Files.createTempFile("IPGeocoder", ".dat");

		File file = geoip.toFile();

		synchronized (this) {
			_write(file, new GZIPInputStream(_getDatasetInputStream()));
		}

		if (_log.isDebugEnabled()) {
			_log.debug("IPGeocoder database file successfully created");
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
		catch (SecurityException securityException) {
			throw new IOException("Unable to create path", securityException);
		}

		try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
				inputStream);
			BufferedOutputStream bufferedOutputStream =
				new BufferedOutputStream(new FileOutputStream(file))) {

			int i = 0;

			while ((i = bufferedInputStream.read()) != -1) {
				bufferedOutputStream.write(i);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(IPGeocoder.class);

	private static final IPGeocoder _ipGeocoder = new IPGeocoder();

	private volatile LookupService _lookupService;

}