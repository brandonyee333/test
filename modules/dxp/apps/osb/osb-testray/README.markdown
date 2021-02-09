# Setup

## Start MySQL

```
docker run \
    --name testray-mysql \
    --rm \
    -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
    -e MYSQL_DATABASE=lportal \
    -e MYSQL_PASSWORD=test \
    -e MYSQL_USER=test \
    -it \
    -p 3306:3306 \
    mysql:5.7 \
    --character-set-server=utf8mb4 \
    --collation-server=utf8mb4_unicode_ci
```

## Start Liferay

```
docker run \
    --link testray-mysql \
    -e LIFERAY_AUTO_PERIOD_LOGIN_PERIOD_HOOKS=com.liferay.portal.security.auto.login.basic.auth.header.BasicAuthHeaderAutoLogin \
    -e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASER_EQUIREMENT=1 \
    -e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASET_ASK=1 \
    -e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASEB_UILD=1 \
    -e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASEP_ROJECT=1 \
    -e LIFERAY_JAVASCRIPT_PERIOD_SINGLE_PERIOD_PAGE_PERIOD_APPLICATION_PERIOD_ENABLED=false \
    -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=com.mysql.jdbc.Driver \
    -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=test \
    -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL='jdbc:mysql://testray-mysql/lportal?characterEncoding=UTF-8&useFastDateParsing=false&useUnicode=true' \
    -e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=test \
    -it \
    -p 8080:8080 \
    -p 11311:11311 \
    --rm \
    liferay/dxp:7.0.10-de-96
```

Note that, in addition to the MySQL connection information, the above docker command specifies the following necessary portal properties:

```
javascript.single.page.application.enabled=false
auto.login.hooks=com.liferay.portal.security.auto.login.basic.auth.header.BasicAuthHeaderAutoLogin
counter.increment.com.liferay.osb.testray.model.TestrayBuild=1
counter.increment.com.liferay.osb.testray.model.TestrayProject=1
counter.increment.com.liferay.osb.testray.model.TestrayRequirement=1
counter.increment.com.liferay.osb.testray.model.TestrayTask=1
minifier.enabled=false
```

## Deploy Testray

0. If your portal repo isn't already set up, run the following command from the repo base directory:

   ```
   ant setup-profile-dxp setup-sdk compile install-portal-snapshots
   ```

1. Run `gw deploy` in the current directory.

   Or, if you're running Liferay in a Docker image:
   ```
   gw deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

## Setting Up Testray on Liferay

### Get Liferay Ready

1. Log in
1. Remove Hello World Portlet
   1. Hello World kabab menu > Remove
1. Add Testray to the page
   1. (+) button in the top right side of the page
   1. Open Applications section
   1. Click Tools > Testray > Add
1. Apply Testray Theme
   1. Left menu > Liferay DXP > Navigation > Public Pages kabab menu > Configure
   1. Change Current Theme to _OSB Testray_
   1. Make sure to Save before navigating away
1. Enable Basic Authentication
   1. Click Manage (bottom left) > Manage Server (which takes you to the Control Panel)
   1. Navigate to Left Menu > Configuration > System Settings > Foundation
   1. Go to _Auto Login Basic Authentication Header_, enable it, and save
   1. Go to _Basic Auth Header Verifiers_, add a new entry (plus sign in the bottom right), enter `*/testray/*` for the field `URLs Includes`, and save
1. Create a Dashboard User (while possibly not strictly necessary, this will help avoid exceptions)
   1. Click Manage (bottom left) > Manage Server (which takes you to the Control Panel)
   1. Navigate to Left Menu > Users > Users and Organizations
   1. Add a new User (plus sign in the bottom right)
   1. Enter the following values into the form:
      Screen Name: `dashboard.user`
      Email Address: `dashboard.user@liferay.com`
      First Name: Dashboard
      Last Name: User
   1. Save
1. Testray is now set up to work on your Liferay instance

### Get Testray Ready

1. In the upper right of the Project Directory click the blue button labeled `+ New Project`
1. Enter `Liferay Portal 7.1` as the name, and click Add
1. Click on `Liferay Portal 7.1` in the search container
1. In the page that opens up, click the blue button `+ Add Routine`
1. Enter `Hotfix Tester` as the name, and click Add
1. Import the test file located in this directory via curl command:
   ```
   curl -sS -X POST \
       --data-urlencode "results@TESTS-test-1-1_test-portal-hotfix-release_13003_functional-bundle-tomcat-mysql57-jdk8_3_9.xml" \
       -u test@liferay.com:test \
       "http://127.0.0.1:8080/web/guest/home/-/testray/case_results/importResults.json?type=poshi"
   ```