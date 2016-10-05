package info.san.books.app.query;

import info.san.books.app.Persistence;
import info.san.books.app.query.Ordering.OrderingItem;
import info.san.books.app.query.Ordering.OrderingItem.OrderWay;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
 * The abstract basic query repository implements basic function to query the database.
 *
 * @author ANGELLOZ-NICOUD Sébastien
 *
 * @param <E> the JPA type the repository query.
 */
public abstract class AbstractBasicQueryRepository<E> implements QueryRepository<E> {

    protected EntityManager em = Persistence.getInstance().createEntityManager();

    private Class<E> clazz;

    /**
     * Default contructor.
     *
     * @param clazz the type the repository will query.
     */
    public AbstractBasicQueryRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll(Ordering ordering, Page page, Filter filters) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaQuery<E> cq = cb.createQuery(this.clazz);
        Root<E> root = cq.from(this.clazz);

        if (ordering != null && !ordering.getOrderingItems().isEmpty()) {
            List<Order> orderBys = new ArrayList<Order>();
            for (OrderingItem oi : ordering.getOrderingItems()) {
                if (oi.getOrderWay().equals(OrderWay.Asc)) {
                    orderBys.add(cb.asc(root.get(oi.getField())));
                } else {
                    orderBys.add(cb.desc(root.get(oi.getField())));
                }
            }

            cq = cq.orderBy(orderBys);
        }

        if (filters != null && !filters.getFilters().isEmpty()) {
            Predicate[] predicates = new Predicate[filters.getFilters().size()];

            int i = 0;

            for (Map.Entry<String, String> filterEntry : filters.getFilters().entrySet()) {
                predicates[i] = cb.equal(root.get(filterEntry.getKey()), filterEntry.getValue());
                i++;
            }

            cq = cq.where(predicates);
        }

        Query q = this.em.createQuery(cq.select(root));

        if (page != null) {
            if (page.getLimit() > 0) {
                q.setMaxResults(page.getLimit());
            }

            q.setFirstResult(page.getPage() * page.getLimit());
        }

        return q.getResultList();
    }

    @Override
    public E find(Object id) {
        return this.em.find(this.clazz, id);
    }

    @Override
    public int count() {
        return ((Number) this.em.createNamedQuery(this.clazz.getSimpleName() + ".count").getSingleResult()).intValue();
    }

}
