package sgcib.eliot.datalake.HBernate.orm.services;

import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import sgcib.eliot.datalake.HBernate.orm.models.Properties;

public interface Deserializer {

	/**
	 *
	 * @param jsonObject
	 *            - configuration in json format
	 * @return bean Properties who represent a deserialization of json object
	 */
	@SuppressWarnings("unchecked")
	public static Properties deserialize(JSON jsonObject) {
		JSONObject obj = JSONObject.fromObject(jsonObject);
		Map<String, String> map = obj;
		Properties properties = Properties.mapToProperties(map);
		return properties;
	}
}
