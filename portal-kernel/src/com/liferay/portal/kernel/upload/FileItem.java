/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public interface FileItem {

	public void delete();

	public String getContentType();

	public String getEncodedString();

	public String getFieldName();

	public String getFileName();

	public String getFileNameExtension();

	public String getFullFileName();

	public InputStream getInputStream() throws IOException;

	public long getSize();

	public int getSizeThreshold();

	public File getStoreLocation();

	public String getString();

	public File getTempFile();

	public boolean isFormField();

	public boolean isInMemory();

	public void setString(String encode);

}