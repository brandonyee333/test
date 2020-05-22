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

package com.liferay.osb.customer.restful.servlet.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
public class UploadServletRequest extends HttpServletRequestWrapper {

	public UploadServletRequest(HttpServletRequest httpServletRequest)
		throws IOException {

		super(httpServletRequest);

		_fileParams = new HashMap<>();
		_regularParams = new HashMap<>();

		try {
			ServletFileUpload servletFileUpload = new ServletFileUpload(
				new DiskFileItemFactory());

			List<FileItem> fileItems = servletFileUpload.parseRequest(
				httpServletRequest);

			for (FileItem fileItem : fileItems) {
				String fieldName = fileItem.getFieldName();

				if (fileItem.isFormField()) {
					List<String> values = _regularParams.get(fieldName);

					if (values == null) {
						values = new ArrayList<>();

						_regularParams.put(fieldName, values);
					}

					String value = fileItem.getString();

					values.add(value);
				}
				else {
					List<FileItem> values = _fileParams.get(fieldName);

					if (values == null) {
						values = new ArrayList<>();

						_fileParams.put(fieldName, values);
					}

					values.add(fileItem);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		_tempFolder = FileUtil.createTempFolder();
	}

	public void cleanUp() {
		FileUtil.deltree(_tempFolder);
	}

	public File[] getFiles(String name) {
		List<FileItem> fileItems = _fileParams.get(name);

		if ((fileItems == null) || fileItems.isEmpty()) {
			return null;
		}

		List<File> files = new ArrayList<>(fileItems.size());

		for (FileItem fileItem : fileItems) {
			String fileName = FilenameUtils.getName(fileItem.getName());

			File file = new File(_tempFolder, fileName);

			try {
				FileUtil.write(file, fileItem.getInputStream());
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);

				continue;
			}

			files.add(file);
		}

		return files.toArray(new File[0]);
	}

	@Override
	public String getParameter(String name) {
		List<String> values = _regularParams.get(name);

		if ((values != null) && !values.isEmpty()) {
			return values.get(0);
		}

		return super.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new HashMap<>();

		Enumeration<String> enu = getParameterNames();

		while (enu.hasMoreElements()) {
			String name = enu.nextElement();

			map.put(name, getParameterValues(name));
		}

		return map;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> parameterNames = new LinkedHashSet<>();

		Enumeration<String> enu = super.getParameterNames();

		while (enu.hasMoreElements()) {
			parameterNames.add(enu.nextElement());
		}

		parameterNames.addAll(_regularParams.keySet());
		parameterNames.addAll(_fileParams.keySet());

		return Collections.enumeration(parameterNames);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = null;

		List<String> values = _regularParams.get(name);

		if (values != null) {
			parameterValues = values.toArray(new String[0]);
		}

		String[] parentParameterValues = super.getParameterValues(name);

		if (parameterValues == null) {
			return parentParameterValues;
		}
		else if (parentParameterValues == null) {
			return parameterValues;
		}

		return ArrayUtil.append(parameterValues, parentParameterValues);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UploadServletRequest.class);

	private final Map<String, List<FileItem>> _fileParams;
	private final Map<String, List<String>> _regularParams;
	private final File _tempFolder;

}