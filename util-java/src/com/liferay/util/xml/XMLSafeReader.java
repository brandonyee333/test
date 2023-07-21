/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.util.xml;

import com.liferay.petra.xml.XMLUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;

/**
 * @author Brian Wing Shun Chan
 */
public class XMLSafeReader extends UnsyncStringReader {

	public XMLSafeReader(String xml) {
		super(XMLUtil.fixProlog(xml));
	}

}