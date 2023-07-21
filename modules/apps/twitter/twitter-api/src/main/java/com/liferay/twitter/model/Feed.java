/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.twitter.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Feed service. Represents a row in the &quot;Twitter_Feed&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FeedModel
 * @generated
 */
@ImplementationClassName("com.liferay.twitter.model.impl.FeedImpl")
@ProviderType
public interface Feed extends FeedModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.twitter.model.impl.FeedImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Feed, Long> FEED_ID_ACCESSOR =
		new Accessor<Feed, Long>() {

			@Override
			public Long get(Feed feed) {
				return feed.getFeedId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Feed> getTypeClass() {
				return Feed.class;
			}

		};

}