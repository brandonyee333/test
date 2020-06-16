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

package com.liferay.osb.customer.zendesk.documentation.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the ZendeskArticle service. Represents a row in the &quot;OSBCustomer_ZendeskArticle&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleModel
 * @see com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleImpl
 * @see com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleImpl")
@ProviderType
public interface ZendeskArticle extends ZendeskArticleModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskArticleImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ZendeskArticle, Long> ZENDESK_ARTICLE_ID_ACCESSOR =
		new Accessor<ZendeskArticle, Long>() {
			@Override
			public Long get(ZendeskArticle zendeskArticle) {
				return zendeskArticle.getZendeskArticleId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ZendeskArticle> getTypeClass() {
				return ZendeskArticle.class;
			}
		};

	public java.lang.String getRemoteHtmlURL(java.lang.String zendeskLocale);
}