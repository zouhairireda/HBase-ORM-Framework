package sgcib.eliot.datalake.HBernate.orm.services;

import java.io.IOException;

import net.sf.json.JSON;
import sgcib.eliot.datalake.HBernate.orm.exceptions.ParsingException;
import sgcib.eliot.datalake.HBernate.orm.models.Properties;

public interface Parsing {

	/**
	 *
	 * @param fileConfig
	 *            - path of Apache Phoenix AND Apache HBase configuration (by default, it's embedded config.xml)
	 * @return properties bean - contains information of configuration in object format
	 * @throws ParsingException
	 * @throws IOException
	 */
	public static Properties getProperties(String fileConfig) throws ParsingException, IOException {
		JSON jsonObject = XmlToJSON.xmlToJSON(fileConfig);
		return Deserializer.deserialize(jsonObject);
	}

}
