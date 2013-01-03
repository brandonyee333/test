/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package com.liferay.portal.cachemanifest.listener;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.cachemanifest.manifest.Util;

/**
 * Listener that regenerate MANIFEST after Layout Set change
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class LayoutSetListener extends BaseModelListener<LayoutSet> {

    @Override
    public void onAfterUpdate(LayoutSet model) throws ModelListenerException {
        _log.debug("Layout Set update event triggered.");

        Util.initializeManifest(model);
    }

    private static Log _log = LogFactoryUtil.getLog(LayoutSetListener.class);
}
