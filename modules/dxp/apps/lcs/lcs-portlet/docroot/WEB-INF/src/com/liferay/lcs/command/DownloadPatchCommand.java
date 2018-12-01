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

package com.liferay.lcs.command;

import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.messaging.CommandMessage;
import com.liferay.lcs.messaging.DownloadPatchCommandMessage;
import com.liferay.lcs.messaging.DownloadPatchResponseMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayClient;
import com.liferay.lcs.util.LCSConstants;
import com.liferay.lcs.util.LCSPatcherUtil;
import com.liferay.lcs.util.PatchUtil;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class DownloadPatchCommand
	implements Command<DownloadPatchCommandMessage> {

	public DownloadPatchCommand(LCSGatewayClient lcsGatewayClient) {
		_lcsGatewayClient = lcsGatewayClient;
	}

	@Override
	public void execute(
		DownloadPatchCommandMessage downloadPatchCommandMessage) {

		if (!LCSPatcherUtil.isConfigured() ||
			(LCSPatcherUtil.getPatchDirectory() == null)) {

			_log.error(
				"Aborting patch download. The patching tool is not " +
					"configured.");

			return;
		}

		try {
			_downloadPatch(downloadPatchCommandMessage);
		}
		catch (Exception e) {
			_log.error("Unable to complete patch download", e);
		}
	}

	private void _checkMD5Sum(String fileName, String md5Sum)
		throws IOException {

		if (Validator.isNull(md5Sum)) {
			throw new IOException("Unable to check MD5Sum which is null");
		}

		File downloadedFile = new File(
			LCSPatcherUtil.getPatchDirectory(), fileName);

		byte[] bytes = FileUtil.getBytes(downloadedFile);

		String actualMD5Sum = PatchUtil.getPatchMD5Sum(bytes);

		if (actualMD5Sum == null) {
			throw new IOException("Unable to check MD5Sum which is null");
		}

		if (!md5Sum.equals(actualMD5Sum)) {
			_log.error("MD5Sum check failed for file " + fileName);

			throw new IOException("MD5Sum check failed");
		}
	}

	private void _checkZipFile(File file) throws IOException {
		ZipFile zipFile = null;
		ZipInputStream zipInputStream = null;

		try {
			zipFile = new ZipFile(file);

			FileInputStream fileInputStream = new FileInputStream(file);

			zipInputStream = new ZipInputStream(fileInputStream);

			ZipEntry zipEntry = zipInputStream.getNextEntry();

			if (zipEntry == null) {
				throw new ZipException(
					"Expected ZIP entry instance but encounted null");
			}

			while (zipEntry != null) {
				zipFile.getInputStream(zipEntry);

				zipEntry.getCompressedSize();
				zipEntry.getCrc();
				zipEntry.getName();

				zipEntry = zipInputStream.getNextEntry();
			}
		}
		finally {
			try {
				if (zipFile != null) {
					zipFile.close();
				}

				if (zipInputStream != null) {
					zipInputStream.close();
				}
			}
			catch (IOException ioe) {
				_log.error("Unable to close validated ZIP file");
			}
		}
	}

	private void _downloadPatch(
			DownloadPatchCommandMessage downloadPatchCommandMessage)
		throws CompressionException, JSONWebServiceException {

		if (_log.isInfoEnabled()) {
			_log.info("Executing download patches command");
		}

		String patchFileName = downloadPatchCommandMessage.getPatchFileName();

		_lcsGatewayClient.sendMessage(
			_getDownloadPatchResponseMessage(
				downloadPatchCommandMessage, patchFileName,
				LCSConstants.PATCHES_DOWNLOADING));

		File localFile = new File(
			LCSPatcherUtil.getPatchDirectory(), patchFileName);

		String patchURL = downloadPatchCommandMessage.getPatchURL();

		if (_log.isInfoEnabled()) {
			_log.info("Started patch download from URL " + patchURL);
		}

		DownloadPatchResponseMessage downloadPatchResponseMessage =
			_getDownloadPatchResponseMessage(
				downloadPatchCommandMessage, patchFileName,
				LCSConstants.PATCHES_ERROR);

		try {
			while (!_transferBytes(patchURL, localFile));

			_checkMD5Sum(
				patchFileName, downloadPatchCommandMessage.getMd5Sum());

			_checkZipFile(localFile);

			if (_log.isInfoEnabled()) {
				_log.info("Completed patch download");
			}

			downloadPatchResponseMessage = _getDownloadPatchResponseMessage(
				downloadPatchCommandMessage, patchFileName,
				LCSConstants.PATCHES_DOWNLOADED);
		}
		catch (IOException ioe) {
			_log.error("Unable to read downloaded patch file", ioe);
		}

		_lcsGatewayClient.sendMessage(downloadPatchResponseMessage);
	}

	private DownloadPatchResponseMessage _getDownloadPatchResponseMessage(
		CommandMessage commandMessage, String patchFileName, int status) {

		DownloadPatchResponseMessage downloadPatchResponseMessage =
			new DownloadPatchResponseMessage();

		downloadPatchResponseMessage.setCreateTime(System.currentTimeMillis());
		downloadPatchResponseMessage.setKey(commandMessage.getKey());
		downloadPatchResponseMessage.setPatchFileName(patchFileName);
		downloadPatchResponseMessage.setStatus(status);

		return downloadPatchResponseMessage;
	}

	private boolean _transferBytes(String remoteFileURL, File localFile)
		throws IOException {

		long totalTransferred = localFile.length();

		URL url = new URL(remoteFileURL);

		URLConnection urlConnection = url.openConnection();

		urlConnection.setRequestProperty(
			"Range", "bytes=" + totalTransferred + "-");

		ReadableByteChannel readableByteChannel = Channels.newChannel(
			urlConnection.getInputStream());

		long remaining = urlConnection.getContentLength();

		long transferred = 0;

		long bufferSize = 65536;

		if (_log.isDebugEnabled()) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Remaining size: ");
			sb.append(remaining);
			sb.append(", transferred size: ");
			sb.append(totalTransferred);

			_log.debug(sb.toString());
		}

		FileOutputStream fileOutputStream = new FileOutputStream(
			localFile, true);

		while (remaining > 0) {
			try {
				FileChannel fileChannel = fileOutputStream.getChannel();

				long currentTransferred = fileChannel.transferFrom(
					readableByteChannel, totalTransferred, bufferSize);

				totalTransferred += currentTransferred;
				transferred += currentTransferred;

				if (currentTransferred == 0) {
					break;
				}
			}
			catch (IOException ioe) {
				_log.error("Unable to download file", ioe);

				break;
			}
		}

		if (transferred == remaining) {
			fileOutputStream.close();
			readableByteChannel.close();

			return true;
		}

		fileOutputStream.close();

		if (_log.isInfoEnabled()) {
			_log.info("Download was interrupted. Trying to resume download.");
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DownloadPatchCommand.class);

	private final LCSGatewayClient _lcsGatewayClient;

}