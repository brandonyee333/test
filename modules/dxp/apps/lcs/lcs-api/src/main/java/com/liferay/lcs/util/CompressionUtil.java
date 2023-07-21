/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides Gzip based compression and uncompression methods.
 *
 * @author  Ivica Cardic
 * @version 2.1.1
 * @since   LCS 0.1
 */
public class CompressionUtil {

	/**
	 * Returns the Gzip compressed value of the uncompressed string.
	 *
	 * @param  uncompressedString the uncompressed string
	 * @return the Gzip compressed value of the uncompressed string
	 * @throws IOException if a Gzip input stream could not be initialized or if
	 *         an IO exception occurred
	 * @since  LCS 0.1
	 */
	public static String compress(String uncompressedString)
		throws IOException {

		if ((uncompressedString == null) ||
			(uncompressedString.length() == 0)) {

			return uncompressedString;
		}

		if (_log.isTraceEnabled()) {
			_log.trace(
				"Uncompressed string length " + uncompressedString.length());
		}

		ByteArrayOutputStream byteArrayOutputStream =
			new ByteArrayOutputStream();

		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(
			byteArrayOutputStream);

		gzipOutputStream.write(uncompressedString.getBytes());

		gzipOutputStream.close();

		String compressedString = byteArrayOutputStream.toString(
			LCSConstants.CHARSET_ISO_8859_1);

		if (_log.isTraceEnabled()) {
			_log.trace("Compressed string length " + compressedString.length());
		}

		return compressedString;
	}

	/**
	 * Returns the original uncompressed string.
	 *
	 * @param  compressedString the compressed string
	 * @return the original uncompressed string
	 * @throws IOException if a Gzip input stream could not be initialized or if
	 *         an IO exception occurred
	 * @since  LCS 0.1
	 */
	public static String uncompress(String compressedString)
		throws IOException {

		if ((compressedString == null) || (compressedString.length() == 0)) {
			return compressedString;
		}

		GZIPInputStream gzipOutputStream = new GZIPInputStream(
			new ByteArrayInputStream(
				compressedString.getBytes(LCSConstants.CHARSET_ISO_8859_1)));

		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(
				gzipOutputStream, LCSConstants.CHARSET_ISO_8859_1));

		StringBuilder sb = new StringBuilder();

		String line = null;

		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}

	private static final Logger _log = LoggerFactory.getLogger(
		CompressionUtil.class);

}