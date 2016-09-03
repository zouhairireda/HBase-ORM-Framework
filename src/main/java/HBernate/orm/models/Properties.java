package sgcib.eliot.datalake.HBernate.orm.models;

import java.io.IOException;
import java.util.Map;

import sgcib.eliot.datalake.HBernate.orm.exceptions.ParsingException;
import sgcib.eliot.datalake.HBernate.orm.services.Parsing;

public class Properties {

	/**
	 * the driver configuration for Apache Phoenix
	 */
	private String driver;

	/**
	 * the path of HBase cluster
	 */
	private String cluster;

	public Properties() {
		super();
	}

	public Properties(String driver, String cluster) {
		super();
		this.driver = driver;
		this.cluster = cluster;
	}

	public Properties(String fileConfig) throws IOException, ParsingException {
		Properties p = Parsing.getProperties(fileConfig);
		this.driver = p.driver;
		this.cluster = p.cluster;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String toString() {
		return "Driver: " + this.driver + " || Cluster: " + cluster;
	}

	public boolean equals(Object params) {
		if (params instanceof Properties) {
			Properties p = null;
			try {
				p = (Properties) ((Properties) params).clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}

			if (p != null) {
				if (this.driver == p.driver && this.cluster == p.cluster) {
					return true;
				}
			}
		}

		return false;
	}

	public static Properties mapToProperties(Map<String, String> map) {
		Properties properties = new Properties();
		properties.driver = map.get("driver");
		properties.cluster = map.get("cluster");

		return properties;
	}
}
