#
# Copyright (c) 2000-present Liferay, Inc. All rights reserved.
#
# The contents of this file are subject to the terms of the Liferay Enterprise
# Subscription License ("License"). You may not use this file except in
# compliance with the License. You can obtain a copy of the License by
# contacting Liferay, Inc. See the License for the specific language governing
# permissions and limitations under the License, including but not limited to
# distribution rights of the Software.
#

import argparse
import importlib
import sys

argument_parser = argparse.ArgumentParser()

argument_parser.add_argument('application')

args = argument_parser.parse_args(sys.argv[1:2])

try:
	application = args.application

	module_name, class_name = application.rsplit('.', 1)

	application_module = importlib.import_module(module_name)

	application_class = getattr(application_module, class_name)

	application_instance = application_class()

	application_instance.start()

except Exception as e:
	argument_parser.print_usage()

	raise ValueError('Startup error: {}'.format(e))