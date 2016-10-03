package info.san.books.app;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * MIT License
 *
 * Copyright (c) 2016 sangellozn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/**
 * Global properties and quick access to it.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 */
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
                throw new RuntimeException(io);
            }
        } else {
            throw new RuntimeException("Cannot load properties file 'books.app.config'.");
        }
    }

    /**
     * Get the {@link AppProperties} instance.
     *
     * @return the {@link AppProperties} instance.
     */
    public static AppProperties getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Get the event store path property ({@code event.store.location}).
     *
     * @return the configured event store path.
     */
    public String getEventStorePath() {
        return AppProperties.PROPERTIES.getProperty("event.store.location");
    }

}
