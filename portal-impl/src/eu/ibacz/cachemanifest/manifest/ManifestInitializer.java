/* ===========================================================================
 * IBA CZ Confidential
 *
 * © Copyright IBA CZ 2012 ALL RIGHTS RESERVED
 * The source code for this program is not published or otherwise
 * divested of its trade secrets.
 *
 * ===========================================================================*/
package eu.ibacz.cachemanifest.manifest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.*;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Singleton used for initialisation of one or all manifests.
 *
 * @author Jan Andrýsek <jan.andrysek at ibacz.eu>
 * @author Tomáš Král (tomas.kral@ibacz.eu)
 */
public class ManifestInitializer {
    private static final Log _log = LogFactoryUtil.getLog(ManifestInitializer.class);
    private static final Pattern linkPattern = Pattern.compile("(<dynamic-element[^>]+type=\"(document_library|image)\"[^>]+>\\s+<dynamic-content[^>]*>(<!\\[CDATA\\[)?([^\\]<]+)(\\]\\]>)?\\s*</dynamic-content>)|(src=\"(/[^\"]+)\")", Pattern.MULTILINE);

    private static final ManifestInitializer instance = new ManifestInitializer();


    private ManifestInitializer() {
    }

    /**
     * Returns singleton instance of this class.
     *
     * @return singleton instance
     */
    public static ManifestInitializer getInstance() {
        return instance;
    }

    /**
     * Generates all manifests and stores them for later use.
     *
     * @throws SystemException
     * @throws PortalException
     */
    public void initializeAllManifests() throws SystemException, PortalException {
        _log.debug("Regenerating all manifests.");

        List<LayoutSet> layoutSets = LayoutSetLocalServiceUtil.getLayoutSets(0, -1);

        List<Manifest> manifests = new ArrayList<Manifest>(layoutSets.size());

        for (LayoutSet layoutSet : layoutSets) {
            UnicodeProperties properties = layoutSet.getSettingsProperties();
            boolean allow = "true".equals(properties.getProperty(ManifestConstants.PROPERTIES_ALLOW));

            if (allow) {
                String name = Util.generateManifestName(layoutSet);

                manifests.add(initializeManifest(name, layoutSet));
            }
        }

        ManifestHolder.setManifests(manifests);
    }

    /**
     * Generates manifest for given Layout Set and stores it for later use.
     *
     * @param layoutSet
     * @throws SystemException
     * @throws PortalException
     */
    public void initializeManifest(LayoutSet layoutSet) throws SystemException, PortalException {
        UnicodeProperties properties = layoutSet.getSettingsProperties();
        boolean allow = "true".equals(properties.getProperty(ManifestConstants.PROPERTIES_ALLOW));

        String name = Util.generateManifestName(layoutSet);

        if (allow) {
            ManifestHolder.addManifest(initializeManifest(name, layoutSet));
        } else {
            ManifestHolder.removeManifest(name);
        }
    }


    private Manifest initializeManifest(String manifestName, LayoutSet layoutSet) throws SystemException, PortalException {
        _log.debug("Generating manifest: " + manifestName);

        String cacheSection = layoutSet.getSettingsProperties().getProperty(ManifestConstants.PROPERTIES_CACHE_SECTION, "");
        String networkSection = layoutSet.getSettingsProperties().getProperty(ManifestConstants.PROPERTIES_NETWORK_SECTION, "");
        String fallbackSection = layoutSet.getSettingsProperties().getProperty(ManifestConstants.PROPERTIES_FALLBACK_SECTION, "");
        String folders = layoutSet.getSettingsProperties().getProperty(ManifestConstants.PROPERTIES_FOLDERS, "");


        ManifestSectionDataDto data = new ManifestSectionDataDto(
                cacheSection.isEmpty() ? "" : cacheSection + "\n",
                networkSection.isEmpty() ? "" : networkSection + "\n",
                fallbackSection.isEmpty() ? "" : fallbackSection + "\n");

        List<Layout> layouts = LayoutLocalServiceUtil.getLayouts(layoutSet.getGroupId(), layoutSet.isPrivateLayout());

        data = addLayouts(data, layouts);

        if (!folders.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();

            Map<String, String> foldersMapSettings;

            try {
                foldersMapSettings = mapper.readValue(
                        folders,
                        new TypeReference<Map<String, String>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            Map<String, File> foldersMap = new HashMap<String, File>();

            for (Map.Entry<String, String> set : foldersMapSettings.entrySet()) {
                File file = new File(set.getValue());

                if (file.exists()) {
                    foldersMap.put(set.getKey(), file);
                }
            }

            data = addFolderResources(data, foldersMap);
        }

        return new Manifest(manifestName,
                data.getCache(),
                data.getNetwork(),
                data.getFallback());
    }

    private ManifestSectionDataDto addLayouts(ManifestSectionDataDto manifest, List<Layout> layouts) throws SystemException, PortalException {
        StringBuilder cacheStringBuilder = new StringBuilder(manifest.getCache());

        Set<String> resources = new HashSet<String>();

        cacheStringBuilder.append("\n");

        for (Layout layout : layouts) {
            if (!"true".equals(layout.getTypeSettingsProperties().getProperty(ManifestConstants.PROPERTIES_DISABLE))) {
                cacheStringBuilder.append(getLayoutURL(layout)).append("\n");

                resources.addAll(getLayoutResources(layout));
            }
        }

        cacheStringBuilder.append("\n");

        for (String resource : resources) {
            cacheStringBuilder.append(resource).append("\n");
        }

        return new ManifestSectionDataDto(cacheStringBuilder.toString(), manifest.getNetwork(), manifest.getFallback());
    }

    private Set<String> getLayoutResources(Layout layout) throws SystemException, PortalException {
        Set<String> result = new HashSet<String>();

        if (layout.isTypePortlet()) {
            LayoutTypePortlet portletLayout = (LayoutTypePortlet) layout.getLayoutType();

            List<Portlet> portlets = portletLayout.getAllPortlets();
            for (Portlet portlet : portlets) {
                if (portlet.getPortletName().equals(PortletKeys.JOURNAL_CONTENT)) {
                    javax.portlet.PortletPreferences prefs = PortletPreferencesFactoryUtil.getLayoutPortletSetup(layout, portlet.getPortletId());
                    String aid = prefs.getValue("articleId", null);
                    String groupId = prefs.getValue("groupId", null);

                    if (aid != null && groupId != null) {
                        JournalArticle article = JournalArticleLocalServiceUtil.getArticle(Long.valueOf(groupId), aid);

                        Matcher m = linkPattern.matcher(article.toXmlString());
                        while (m.find()) {
                            String resource = m.group(7) != null ? m.group(7) : m.group(4);

                            result.add(resource.replace("&amp;", "&"));
                        }
                    }

                }
            }
        }

        return result;
    }

    private ManifestSectionDataDto addFolderResources(ManifestSectionDataDto manifest, Map<String, File> folders) {
        StringBuilder cacheStringBuilder = new StringBuilder(manifest.getCache());
        StringBuilder fallbackStringBuilder = new StringBuilder(manifest.getFallback());

        for (Map.Entry<String, File> set : folders.entrySet()) {
            List<File> files = getAllFiles(set.getValue());

            int folderPathLength = set.getValue().getAbsolutePath().length();

            String resourcePrefix = set.getKey().endsWith("/") ? set.getKey().substring(0, set.getKey().length() - 1) : set.getKey();

            for (File file : files) {
                String resource = resourcePrefix + file.getAbsolutePath().substring(folderPathLength);

                cacheStringBuilder.append(resource).append("\n");
                fallbackStringBuilder.append(resource).append("\t").append(resource).append("\n");
            }
        }

        return new ManifestSectionDataDto(cacheStringBuilder.toString(), manifest.getNetwork(), fallbackStringBuilder.toString());
    }

    private List<File> getAllFiles(File folder) {
        List<File> files = new ArrayList<File>();

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(getAllFiles(file));
            } else {
                files.add(file);
            }
        }

        return files;
    }

    private String getLayoutURL(Layout layout) throws SystemException, PortalException {
        Group group = layout.getGroup();

        if (layout.getLayoutSet().getVirtualHostname() != null && !layout.getLayoutSet().getVirtualHostname().isEmpty()) {
            return layout.getFriendlyURL();
        } else {
            if (layout.isPrivateLayout()) {
                if (group.isUser()) {
                    return PortalUtil.getPathFriendlyURLPrivateUser() + group.getFriendlyURL() + layout.getFriendlyURL();
                } else {
                    return PortalUtil.getPathFriendlyURLPrivateGroup() + group.getFriendlyURL() + layout.getFriendlyURL();
                }
            } else {
                return PortalUtil.getPathFriendlyURLPublic() + group.getFriendlyURL() + layout.getFriendlyURL();
            }
        }
    }
}
