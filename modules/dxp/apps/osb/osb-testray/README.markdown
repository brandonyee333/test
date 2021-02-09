# Databases

## MySQL

1. Start MySQL.

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

1. Connect to MySQL via the MySQL client.

	1. ```docker exec -it testray-mysql mysql -utest -ptest***```

	1. ```use lportal;```

	1. ```***show tables;```

		No tables exist because the database is empty.

## Liferay

1. Start Liferay.

	```
	docker run \
		--link testray-mysql \
		-e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASER_EQUIREMENT="1" \
		-e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASET_ASK="1" \
		-e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASEB_UILD="1" \
		-e LIFERAY_COUNTER_PERIOD_INCREMENT_PERIOD_COM_PERIOD_OSB_PERIOD_TESTRAY_PERIOD_MODEL_PERIOD_UPPERCASET_ESTRAY_UPPERCASEP_ROJECT="1" \
		-e LIFERAY_JAVASCRIPT_PERIOD_SINGLE_PERIOD_PAGE_PERIOD_APPLICATION_PERIOD_ENABLED="false" \
		-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME="com.mysql.jdbc.Driver" \
		-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD="test" \
		-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL="jdbc:mysql://testray-mysql/lportal?characterEncoding=UTF-8&useFastDateParsing=false&useUnicode=true" \
		-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME="test" \
		-e LIFERAY_MINIFIER_PERIOD_ENABLE="false" \
		-it \
		-p 8080:8080 \
		-p 11311:11311 \
		--rm \
		liferay/dxp:7.0.10-de-96
	```

1. Verify databases were created. Go to your MySQL client.

	1. ```***show tables;```

		Many tables exist because the database was populated by Liferay.

## Deploy Testray