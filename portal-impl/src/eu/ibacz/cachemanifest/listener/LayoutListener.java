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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Layout;
import eu.ibacz.cachemanifest.manifest.Util;

/**
 * Listener that regenerate MANIFEST after change in layout structure.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class LayoutListener extends BaseModelListener<Layout> {
    private static Log _log = LogFactoryUtil.getLog(LayoutListener.class);

    @Override
    public void onAfterCreate(Layout model) throws ModelListenerException {
        _log.debug("Layout create event triggered.");

        try {
            Util.initializeManifest(model.getLayoutSet());
        } catch (PortalException e) {
            throw new ModelListenerException(e);
        } catch (SystemException e) {
            throw new ModelListenerException(e);
        }
    }

    @Override
    public void onAfterRemove(Layout model) throws ModelListenerException {
        _log.debug("Layout remove event triggered.");

        try {
            Util.initializeManifest(model.getLayoutSet());
        } catch (PortalException e) {
            throw new ModelListenerException(e);
        } catch (SystemException e) {
            throw new ModelListenerException(e);
        }
    }

    @Override
    public void onAfterUpdate(Layout model) throws ModelListenerException {
        _log.debug("Layout update event triggered.");

        try {
            Util.initializeManifest(model.getLayoutSet());
        } catch (PortalException e) {
            throw new ModelListenerException(e);
        } catch (SystemException e) {
            throw new ModelListenerException(e);
        }
    }
}
