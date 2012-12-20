/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.listener;

import com.liferay.portal.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.LayoutSet;
import eu.ibacz.cachemanifest.manifest.Util;

/**
 * Listener that regenerate MANIFEST after Layout Set change
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class LayoutSetListener extends BaseModelListener<LayoutSet> {
    private static Log _log = LogFactoryUtil.getLog(LayoutSetListener.class);

    @Override
    public void onAfterUpdate(LayoutSet model) throws ModelListenerException {
        _log.debug("Layout Set update event triggered.");

        Util.initializeManifest(model);
    }
}
