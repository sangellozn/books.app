package info.san.books.app.query;

import info.san.books.app.query.Ordering.OrderingItem.OrderWay;

import java.util.ArrayList;
import java.util.Collection;

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
