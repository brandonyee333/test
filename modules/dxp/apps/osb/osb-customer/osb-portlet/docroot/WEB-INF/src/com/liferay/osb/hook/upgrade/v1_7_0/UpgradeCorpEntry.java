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

package com.liferay.osb.hook.upgrade.v1_7_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.document.library.kernel.store.DLStoreUtil;

import java.awt.image.RenderedImage;

import java.io.File;

import java.util.List;

*/

/**
 * @author Douglas Wong
 */
public class UpgradeCorpEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	protected void doUpgrade() throws Exception {
		updateCorpEntries();
	}

	protected void updateCorpEntries() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CorpEntry.class);

		List<CorpEntry> corpEntries =
			(List<CorpEntry>)CorpEntryLocalServiceUtil.dynamicQuery(
				dynamicQuery);

		for (CorpEntry corpEntry : corpEntries) {
			try {
				updateCorpEntry(corpEntry);
			}
			catch (Exception e) {
			}
		}
	}

	protected void updateCorpEntry(CorpEntry corpEntry) throws Exception {
		AssetAttachment assetAttachment =
			AssetAttachmentLocalServiceUtil.fetchAssetAttachment(
				corpEntry.getLogoId());

		if (assetAttachment == null) {
			return;
		}

		File logoFile = DLStoreUtil.getFile(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM,
			assetAttachment.getPath());

		byte[] logoBytes = FileUtil.getBytes(logoFile);

		ImageBag imageBag = ImageToolUtil.read(logoBytes);

		RenderedImage renderedImage = imageBag.getRenderedImage();

		int maxHeight = PortletPropsValues.CORP_ENTRY_LOGO_MAX_HEIGHT;
		int maxWidth = PortletPropsValues.CORP_ENTRY_LOGO_MAX_WIDTH;

		if ((renderedImage.getHeight() <= maxHeight) &&
			(renderedImage.getWidth() <= maxWidth)) {

			return;
		}

		renderedImage = ImageToolUtil.scale(renderedImage, maxHeight, maxWidth);

		logoBytes = ImageToolUtil.getBytes(renderedImage, imageBag.getType());

		AssetAttachment newAssetAttachment =
			AssetAttachmentLocalServiceUtil.addAssetAttachment(
				assetAttachment.getUserId(), CorpEntry.class.getName(),
				corpEntry.getCorpEntryId(), logoFile.getName(),
				AssetAttachmentConstants.TYPE_MEDIA, 0, logoBytes);

		corpEntry.setLogoId(newAssetAttachment.getAssetAttachmentId());

		CorpEntryLocalServiceUtil.updateCorpEntry(corpEntry);
	}

}

*/

}