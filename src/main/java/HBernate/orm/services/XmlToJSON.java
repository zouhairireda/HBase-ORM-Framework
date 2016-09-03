package sgcib.eliot.datalake.HBernate.orm.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public interface XmlToJSON {

	/**
	 *
	 * @param fileConfig
	 *            - path of Apache Phoenix AND Apache HBase configuration (by default, it's embedded config.xml)
	 * @return object json - serialization of xml config
	 * @throws IOException
	 */
	public static JSON xmlToJSON(String fileConfig) throws IOException {

		URL url = Parsing.class.getClassLoader().getResource(fileConfig);
		InputStream is = null;

		if (url == null) {
			throw new FileNotFoundException("File " + fileConfig + " not found");
		}
		is = url.openStream();
		String xml = IOUtils.toString(is);
		JSON objetJson = new XMLSerializer().read(xml);
		is.close();
		return objetJson;
	}

}
