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