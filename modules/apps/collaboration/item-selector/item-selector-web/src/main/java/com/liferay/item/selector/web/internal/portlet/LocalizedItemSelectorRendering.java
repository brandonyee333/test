/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.item.selector.web.internal.portlet;

import com.liferay.item.selector.ItemSelectorRendering;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.ItemSelectorViewRenderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;

/**
 * @author Iván Zaera
 */
public class LocalizedItemSelectorRendering {

	public static LocalizedItemSelectorRendering get(
		PortletRequest portletRequest) {

		return (LocalizedItemSelectorRendering)portletRequest.getAttribute(
			LocalizedItemSelectorRendering.class.getName());
	}

	public LocalizedItemSelectorRendering(
		Locale locale, ItemSelectorRendering itemSelectorRendering) {

		_locale = locale;
		_itemSelectorRendering = itemSelectorRendering;

		for (ItemSelectorViewRenderer itemSelectorViewRenderer :
				itemSelectorRendering.getItemSelectorViewRenderers()) {

			add(itemSelectorViewRenderer);
		}
	}

	public void add(ItemSelectorViewRenderer itemSelectorViewRenderer) {
		ItemSelectorView<?> itemSelectorView =
			itemSelectorViewRenderer.getItemSelectorView();

		String title = itemSelectorView.getTitle(_locale);

		_itemSelectorViewRenderers.put(title, itemSelectorViewRenderer);
		_titles.add(title);
	}

	public String getItemSelectedEventName() {
		return _itemSelectorRendering.getItemSelectedEventName();
	}

	public ItemSelectorViewRenderer getItemSelectorViewRenderer(String title) {
		return _itemSelectorViewRenderers.get(title);
	}

	public String getSelectedTab() {
		return _itemSelectorRendering.getSelectedTab();
	}

	public List<String> getTitles() {
		return _titles;
	}

	public void store(PortletRequest portletRequest) {
		portletRequest.setAttribute(
			LocalizedItemSelectorRendering.class.getName(), this);
	}

	private final ItemSelectorRendering _itemSelectorRendering;
	private final Map<String, ItemSelectorViewRenderer>
		_itemSelectorViewRenderers = new HashMap<>();
	private final Locale _locale;
	private final List<String> _titles = new ArrayList<>();

}