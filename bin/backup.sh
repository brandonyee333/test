:
cd ../..
zip -rX portal/bin/liferay-idea.zip $(find portal -name '*.iml' -print) .idea plugins/plugins.iml atlassian-ide-plugin.xml
cd -
