/*
 * IBA CZ Confidential
 * 
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 * 
 */
package eu.ibacz.cachemanifest.servlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import eu.ibacz.cachemanifest.manifest.Manifest;
import eu.ibacz.cachemanifest.manifest.ManifestHolder;
import eu.ibacz.cachemanifest.manifest.ManifestInitializer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Returns Cache Manifest with timestamp of last regeneration.
 *
 * @author Tomáš Král <tomas.kral@ibacz.eu>
 */
public class ManifestServletFilter implements Filter {
    private static final Log _log = LogFactoryUtil.getLog(ManifestServletFilter.class);


    public void init(FilterConfig filterConfig) throws ServletException {
        _log.debug("Initializing Application Cache Manifest Hook...");

        try {
            ManifestInitializer.getInstance().initializeAllManifests();
        } catch (SystemException ex) {
            throw new RuntimeException(ex);
        } catch (PortalException ex) {
            throw new RuntimeException(ex);
        }
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        String manifestName = getManifestName(servletRequest.getServletPath());
        Manifest manifest = ManifestHolder.getManifest(manifestName);

        if (manifest != null) {
            byte[] manifestBytes = manifest.getManifestBytes();

            response.setContentType("text/cache-manifest;charset=utf-8");

            String etag = manifest.getMd5Hex();
            servletResponse.addHeader("ETag", etag);
            servletResponse.setDateHeader("Last-Modified", manifest.getDateModifiedInMillis());

            String previousToken = servletRequest.getHeader("If-None-Match");
            if (previousToken != null && previousToken.equals(etag)) {
                servletResponse.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            } else {
                OutputStream os = response.getOutputStream();
                os.write(manifestBytes);
                os.flush();
            }
        } else {
            servletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    public void destroy() {
    }


    private String getManifestName(String servletPath) {
        return servletPath.substring(1, servletPath.indexOf('.'));
    }
}
