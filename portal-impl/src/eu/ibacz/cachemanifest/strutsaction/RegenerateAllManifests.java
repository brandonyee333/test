/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.strutsaction;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import eu.ibacz.cachemanifest.manifest.Util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Struts Action fo regenerating all manifests.
 *
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class RegenerateAllManifests extends BaseStrutsAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Util.initializeAllManifests();

        return null;
    }
}
