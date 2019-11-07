import datetime
import json
import os
import requests
from requests.auth import HTTPBasicAuth
import sys

lcs_host = os.environ['LCS_PORTAL_HOST']
lcs_port = os.environ['LCS_PORTAL_PORT']
lcs_email = os.environ['LCS_PORTAL_EMAIL']
lcs_pwd = os.environ['LCS_PORTAL_PWD']

lcs_base_url = "http://" + lcs_host + ":" + lcs_port + "/api/jsonws/osb-lcs-portlet.lcsclusterentry/export-lcs-cluster-entry-token"
lcs_environment_name = datetime.datetime.now().strftime("%Y%m%d%H%M%S")
lcs_token_path = "/liferay/data/lcs-aatf-" + lcs_environment_name + ".aatf"

url = lcs_base_url + '/lcs-project-id/1/lcs-cluster-entry-name/' + lcs_environment_name + '/subscription-type/UNDEFINED/type/1'

print("Getting LCS token from url: " + url)
print("Environment name: " + lcs_environment_name)
print("User: " + lcs_email)

r = requests.post(
    url,
    auth=HTTPBasicAuth(lcs_email, lcs_pwd),
    stream=True)

response = r.content.decode('utf-8')

print("Decoded response from LCS: " + response)

if "exception " in response:
    println("Exception occurred")
    sys.exit(1)

data = json.loads(response)
data = [x for x in map(lambda x: x % 256, data)]

with open(lcs_token_path, 'wb') as out_f:
    out_f.write(bytearray(data))
    out_f.close()