import xml.etree.ElementTree
import json

e = xml.etree.ElementTree.parse('raw_data/senators_cfm.xml').getroot()

memberArray = []

for member in e.findall('member'):
    memberMap = dict()
    memberMap['name'] = member.find('member_full').text
    memberMap['firstName'] = member.find('first_name').text
    memberMap['lastName'] = member.find('last_name').text
    memberMap['party'] = member.find('party').text
    memberMap['website'] = member.find('website').text
    memberMap['bioguideId'] = member.find('bioguide_id').text
    memberMap['state'] = member.find('state').text
    memberMap['emailContactForm'] = member.find('email').text
    memberMap['dcPhone'] = member.find('phone').text
    memberMap['dcAddress'] = member.find('address').text
    memberMap['type'] = "senator"
    memberArray.append(memberMap)

with open('senators.json','w') as outfile:
    json.dump(memberArray,outfile)
print memberArray

