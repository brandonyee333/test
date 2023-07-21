/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.item.selector;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.file.criterion.FileItemSelectorCriterion;
import com.liferay.item.selector.criteria.image.criterion.ImageItemSelectorCriterion;
import com.liferay.journal.constants.JournalWebKeys;
import com.liferay.journal.item.selector.criterion.JournalItemSelectorCriterion;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolder;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayRenderResponse;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Roberto Díaz
 */
public class JournalItemSelectorHelper {

	public JournalItemSelectorHelper(
		JournalArticle article, JournalFolder folder,
		RenderRequest renderRequest, RenderResponse renderResponse) {

		_article = article;
		_folder = folder;
		_renderRequest = renderRequest;
		_renderResponse = renderResponse;

		_itemSelector = (ItemSelector)renderRequest.getAttribute(
			JournalWebKeys.ITEM_SELECTOR);
	}

	public PortletURL getDocumentLibrarySelectorURL() {
		ItemSelectorCriterion fileItemSelectorCriterion =
			new FileItemSelectorCriterion();

		List<ItemSelectorReturnType>
			fileItemSelectorCriterionDesiredItemSelectorReturnTypes =
				new ArrayList<>();

		fileItemSelectorCriterionDesiredItemSelectorReturnTypes.add(
			new FileEntryItemSelectorReturnType());

		fileItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			fileItemSelectorCriterionDesiredItemSelectorReturnTypes);

		LiferayPortletRequest liferayPortletRequest =
			PortalUtil.getLiferayPortletRequest(_renderRequest);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(liferayPortletRequest);

		LiferayRenderResponse liferayRenderResponse =
			(LiferayRenderResponse)PortalUtil.getLiferayPortletResponse(
				_renderResponse);

		return _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory,
			liferayRenderResponse.getNamespace() + "selectDocumentLibrary",
			fileItemSelectorCriterion);
	}

	public PortletURL getImageSelectorURL() {
		JournalItemSelectorCriterion journalItemSelectorCriterion =
			new JournalItemSelectorCriterion();

		if (_article != null) {
			journalItemSelectorCriterion.setResourcePrimKey(
				_article.getResourcePrimKey());

			journalItemSelectorCriterion.setFolderId(_article.getFolderId());
		}
		else if (_folder != null) {
			journalItemSelectorCriterion.setFolderId(_folder.getFolderId());
		}

		ItemSelectorCriterion fileItemSelectorCriterion =
			new ImageItemSelectorCriterion();

		List<ItemSelectorReturnType>
			itemSelectorCriterionDesiredItemSelectorReturnTypes =
				new ArrayList<>();

		itemSelectorCriterionDesiredItemSelectorReturnTypes.add(
			new FileEntryItemSelectorReturnType());

		journalItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			itemSelectorCriterionDesiredItemSelectorReturnTypes);

		fileItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
			itemSelectorCriterionDesiredItemSelectorReturnTypes);

		LiferayPortletRequest liferayPortletRequest =
			PortalUtil.getLiferayPortletRequest(_renderRequest);

		RequestBackedPortletURLFactory requestBackedPortletURLFactory =
			RequestBackedPortletURLFactoryUtil.create(liferayPortletRequest);

		LiferayRenderResponse liferayRenderResponse =
			(LiferayRenderResponse)PortalUtil.getLiferayPortletResponse(
				_renderResponse);

		return _itemSelector.getItemSelectorURL(
			requestBackedPortletURLFactory,
			liferayRenderResponse.getNamespace() + "selectDocumentLibrary",
			journalItemSelectorCriterion, fileItemSelectorCriterion);
	}

	private final JournalArticle _article;
	private final JournalFolder _folder;
	private final ItemSelector _itemSelector;
	private final RenderRequest _renderRequest;
	private final RenderResponse _renderResponse;

}