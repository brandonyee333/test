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
import com.liferay.portlet.journal.model.JournalArticle;
import eu.ibacz.cachemanifest.manifest.Util;

/**
 * Listener that regenerate MANIFEST after Web Content change.
 *
 * @author Tomáš Král <tomas.kral@ibacz.eu>
 */
public class JournalArticleListener extends BaseModelListener<JournalArticle> {
    private static Log _log = LogFactoryUtil.getLog(JournalArticleListener.class);

    @Override
    public void onAfterRemove(JournalArticle model) throws ModelListenerException {
        _log.debug("Article removed event triggered.");

        Util.initializeAllManifests();
    }

    @Override
    public void onAfterUpdate(JournalArticle model) throws ModelListenerException {
        _log.debug("Article update event triggered.");

        Util.initializeAllManifests();
    }
}
