package info.san.books.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

	private static final Properties PROPERTIES = new Properties();

	private static class Holder {
		private static final AppProperties INSTANCE = new AppProperties();
	}

	private AppProperties() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("books.app.config");
		if (is != null) {
			try {
				AppProperties.PROPERTIES.load(is);
			} catch (IOException io) {
				new RuntimeException(io);
			}
		} else {
			throw new RuntimeException("Cannot load properties file 'books.app.config'.");
		}
	}

	public static AppProperties getInstance() {
		return Holder.INSTANCE;
	}

	public String getEventStorePath() {
		return AppProperties.PROPERTIES.getProperty("event.store.location");
	}

}
