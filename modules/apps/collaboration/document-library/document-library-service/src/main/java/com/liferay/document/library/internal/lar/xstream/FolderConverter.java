/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.internal.lar.xstream;

import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.exportimport.kernel.xstream.BaseXStreamConverter;
import com.liferay.exportimport.kernel.xstream.XStreamHierarchicalStreamReader;
import com.liferay.exportimport.kernel.xstream.XStreamUnmarshallingContext;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.repository.proxy.FolderProxyBean;
import com.liferay.portal.repository.liferayrepository.model.LiferayFolder;
import com.liferay.portlet.documentlibrary.model.impl.DLFolderImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Akos Thurzo
 */
public class FolderConverter extends BaseXStreamConverter {

	@Override
	public boolean canConvert(Class<?> clazz) {
		return clazz.equals(FolderProxyBean.class);
	}

	@Override
	public Object unmarshal(
			XStreamHierarchicalStreamReader xStreamHierarchicalStreamReader,
			XStreamUnmarshallingContext xStreamUnmarshallingContext)
		throws Exception {

		DLFolder dlFolder = new DLFolderImpl();
		boolean escapedModel = false;

		while (xStreamHierarchicalStreamReader.hasMoreChildren()) {
			xStreamHierarchicalStreamReader.moveDown();

			String nodeName = xStreamHierarchicalStreamReader.getNodeName();

			Class<?> clazz = BeanPropertiesUtil.getObjectType(
				dlFolder, nodeName);

			Object convertedValue = xStreamUnmarshallingContext.convertAnother(
				xStreamHierarchicalStreamReader.getValue(), clazz);

			if (fields.contains(nodeName)) {
				if (nodeName.equals(FieldConstants.ESCAPED_MODEL)) {
					escapedModel = (Boolean)convertedValue;
				}
				else {
					BeanPropertiesUtil.setProperty(
						dlFolder, nodeName, convertedValue);
				}
			}

			xStreamHierarchicalStreamReader.moveUp();
		}

		return new LiferayFolder(dlFolder, escapedModel);
	}

	@Override
	protected List<String> getFields() {
		return fields;
	}

	protected static List<String> fields = new LinkedList<>();

	static {
		fields.add(FieldConstants.COMPANY_ID);
		fields.add(FieldConstants.CREATE_DATE);
		fields.add(FieldConstants.DESCRIPTION);
		fields.add(FieldConstants.ESCAPED_MODEL);
		fields.add(FieldConstants.FOLDER_ID);
		fields.add(FieldConstants.GROUP_ID);
		fields.add(FieldConstants.LAST_POST_DATE);
		fields.add(FieldConstants.MODIFIED_DATE);
		fields.add(FieldConstants.MOUNT_POINT);
		fields.add(FieldConstants.NAME);
		fields.add(FieldConstants.PARENT_FOLDER_ID);
		fields.add(FieldConstants.REPOSITORY_ID);
		fields.add(FieldConstants.USER_ID);
		fields.add(FieldConstants.USER_NAME);
		fields.add(FieldConstants.USER_UUID);
		fields.add(FieldConstants.UUID);
	}

}