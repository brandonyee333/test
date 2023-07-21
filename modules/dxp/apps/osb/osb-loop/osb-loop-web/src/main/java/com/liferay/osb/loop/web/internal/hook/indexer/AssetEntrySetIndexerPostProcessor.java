/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.hook.indexer;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.web.internal.notifications.LoopUserNotificationEventUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.BaseIndexerPostProcessor;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexerPostProcessor;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Portal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Kong
 * @author Timothy Bell
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet",
	service = IndexerPostProcessor.class
)
public class AssetEntrySetIndexerPostProcessor
	extends BaseIndexerPostProcessor {

	@Override
	public void postProcessDocument(Document document, Object obj)
		throws Exception {

		BaseModel<?> baseModel = (BaseModel<?>)obj;

		long assetEntrySetId = (Long)baseModel.getPrimaryKeyObj();

		String message = LoopUserNotificationEventUtil.getMessage(
			_portal.getClassNameId(AssetEntrySet.class), assetEntrySetId);

		document.addText(Field.DESCRIPTION, HtmlUtil.extractText(message));
	}

	@Reference
	private Portal _portal;

}