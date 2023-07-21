/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.item.selector.web.internal;

import com.liferay.blogs.item.selector.constants.BlogsItemSelectorViewConstants;
import com.liferay.blogs.item.selector.criterion.BlogsItemSelectorCriterion;
import com.liferay.blogs.item.selector.web.internal.display.context.BlogsItemSelectorViewDisplayContext;
import com.liferay.document.library.display.context.DLMimeTypeDisplayContext;
import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.item.selector.ItemSelectorReturnTypeResolverHandler;
import com.liferay.item.selector.ItemSelectorView;
import com.liferay.item.selector.criteria.FileEntryItemSelectorReturnType;
import com.liferay.item.selector.criteria.URLItemSelectorReturnType;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;

import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Roberto Díaz
 */
@Component(
	property = "item.selector.view.key=" + BlogsItemSelectorViewConstants.ITEM_SELECTOR_VIEW_KEY
)
public class BlogsItemSelectorView
	implements ItemSelectorView<BlogsItemSelectorCriterion> {

	public static final String BLOGS_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT =
		"BLOGS_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT";

	public static final String DL_MIME_TYPE_DISPLAY_CONTEXT =
		"DL_MIME_TYPE_DISPLAY_CONTEXT";

	@Override
	public Class<BlogsItemSelectorCriterion> getItemSelectorCriterionClass() {
		return BlogsItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	@Override
	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	@Override
	public String getTitle(Locale locale) {
		return LanguageUtil.get(locale, "blog-images");
	}

	@Override
	public boolean isShowSearch() {
		return true;
	}

	@Override
	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	@Override
	public void renderHTML(
			ServletRequest request, ServletResponse response,
			BlogsItemSelectorCriterion blogsItemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		BlogsItemSelectorViewDisplayContext
			blogsItemSelectorViewDisplayContext =
				new BlogsItemSelectorViewDisplayContext(
					blogsItemSelectorCriterion, this,
					_itemSelectorReturnTypeResolverHandler,
					itemSelectedEventName, search, portletURL);

		request.setAttribute(
			BLOGS_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT,
			blogsItemSelectorViewDisplayContext);

		request.setAttribute(
			DL_MIME_TYPE_DISPLAY_CONTEXT, _dlMimeTypeDisplayContext);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/blogs_attachments.jsp");

		requestDispatcher.include(request, response);
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	public void setDLMimeTypeDisplayContext(
		DLMimeTypeDisplayContext dlMimeTypeDisplayContext) {

		_dlMimeTypeDisplayContext = dlMimeTypeDisplayContext;
	}

	@Reference(unbind = "-")
	public void setItemSelectorReturnTypeResolverHandler(
		ItemSelectorReturnTypeResolverHandler
			itemSelectorReturnTypeResolverHandler) {

		_itemSelectorReturnTypeResolverHandler =
			itemSelectorReturnTypeResolverHandler;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.blogs.item.selector.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	public void unsetDLMimeTypeDisplayContext(
		DLMimeTypeDisplayContext dlMimeTypeDisplayContext) {

		_dlMimeTypeDisplayContext = null;
	}

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(
				new ItemSelectorReturnType[] {
					new FileEntryItemSelectorReturnType(),
					new URLItemSelectorReturnType()
				}));

	private DLMimeTypeDisplayContext _dlMimeTypeDisplayContext;
	private ItemSelectorReturnTypeResolverHandler
		_itemSelectorReturnTypeResolverHandler;
	private ServletContext _servletContext;

}