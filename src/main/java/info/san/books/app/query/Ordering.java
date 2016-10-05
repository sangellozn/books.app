package info.san.books.app.query;

import info.san.books.app.query.Ordering.OrderingItem.OrderWay;

import java.util.ArrayList;
import java.util.Collection;

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
 * Class for request ordering fields.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 */
public class Ordering {

    private Collection<OrderingItem> orderingItems;

    public static final class OrderingItem {

        public enum OrderWay {
            Asc,
            Desc
        }

        private String field;

        private OrderWay orderWay;

        public OrderingItem(String field, OrderWay orderWay) {
            this.field    = field;
            this.orderWay = orderWay;
        }

        public String getField() {
            return this.field;
        }

        public OrderWay getOrderWay() {
            return this.orderWay;
        }

    }

    public Ordering(String rawOrdering) {
        this.orderingItems = new ArrayList<Ordering.OrderingItem>();

        if (rawOrdering != null && !rawOrdering.trim().isEmpty()) {
            String[] orderParts = rawOrdering.split("[,]");

            for (String orderPart : orderParts) {
                if (!orderPart.trim().isEmpty()) {
                    if (orderPart.startsWith("-")) {
                        if (orderPart.length() > 1) {
                            this.orderingItems.add(new OrderingItem(orderPart.substring(1), OrderWay.Desc));
                        }
                    } else {
                        this.orderingItems.add(new OrderingItem(orderPart, OrderWay.Asc));
                    }
                }
            }
        }

    }

    public Collection<OrderingItem> getOrderingItems() {
        return this.orderingItems;
    }

}
