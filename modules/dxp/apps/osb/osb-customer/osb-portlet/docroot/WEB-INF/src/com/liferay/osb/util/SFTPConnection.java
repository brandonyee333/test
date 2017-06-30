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

package com.liferay.osb.util;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.FileOutputStream;

import java.util.List;
import java.util.Properties;

/**
 * @author Wesley Gong
 * @author Calvin Keum
 */
public class SFTPConnection {

	public void cd(String path) throws Exception {
		channelSftp.cd(path);
	}

	public boolean connect(
		String host, String userName, String password, int maxAttempts) {

		Properties properties = new Properties();

		properties.put("StrictHostKeyChecking", "no");

		for (int i = 0; i < maxAttempts; i++) {
			try {
				JSch jsch = new JSch();

				JSch.setConfig(properties);

				session = jsch.getSession(userName, host);

				session.setPassword(password);
				session.connect();

				channelSftp = (ChannelSftp)session.openChannel("sftp");

				channelSftp.connect();

				return true;
			}
			catch (Exception e) {
				disconnect();
			}
		}

		return false;
	}

	public void disconnect() {
		if (session != null) {
			session.disconnect();
		}

		if (channelSftp != null) {
			channelSftp.disconnect();
		}
	}

	public void get(String fileName, FileOutputStream fileOutputStream)
		throws Exception {

		channelSftp.get(fileName, fileOutputStream);
	}

	public List<LsEntry> ls(String path) throws Exception {
		return channelSftp.ls(path);
	}

	protected ChannelSftp channelSftp;
	protected Session session;

}