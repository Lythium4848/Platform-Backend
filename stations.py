import xml.etree.ElementTree as ET

def parse_xml(xml_file):
	tree = ET.parse(xml_file)
	root = tree.getroot()

	crs_names = ""

	for station in root.findall('Station'):
		crs = station.find('CRS').text
		name = station.find('Name').text
		darwin = station.find('DarwinEnabled').text # Check if the station is actually a station or something

		if crs and darwin == "true":
			crs_names += f"Station(\"{crs}\", \"{name}\"),\n"

	return crs_names

# Use the function
xml_file = '/home/max/Downloads/StationsRefData_v1.2.xml'  # replace with your actual XML file path
result = parse_xml(xml_file)
file = open("stations.txt", "w")
file.write(result)
file.close()
