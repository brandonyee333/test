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
 * The extended model interface for the ZendeskCategory service. Represents a row in the &quot;OSBCustomer_ZendeskCategory&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategoryModel
 * @see com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryImpl
 * @see com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryImpl")
@ProviderType
public interface ZendeskCategory extends ZendeskCategoryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.osb.customer.zendesk.documentation.sync.model.impl.ZendeskCategoryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<ZendeskCategory, Long> ZENDESK_CATEGORY_ID_ACCESSOR =
		new Accessor<ZendeskCategory, Long>() {
			@Override
			public Long get(ZendeskCategory zendeskCategory) {
				return zendeskCategory.getZendeskCategoryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<ZendeskCategory> getTypeClass() {
				return ZendeskCategory.class;
			}
		};

	public java.lang.String[] getRemoteLabelNames();
}