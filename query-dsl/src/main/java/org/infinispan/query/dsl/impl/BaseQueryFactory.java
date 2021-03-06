package org.infinispan.query.dsl.impl;

import org.infinispan.query.dsl.FilterConditionBeginContext;
import org.infinispan.query.dsl.FilterConditionContext;
import org.infinispan.query.dsl.FilterConditionEndContext;
import org.infinispan.query.dsl.Query;
import org.infinispan.query.dsl.QueryFactory;

/**
 * @author anistor@redhat.com
 * @since 6.0
 */
public abstract class BaseQueryFactory<T extends Query> implements QueryFactory<T> {

   @Override
   public FilterConditionEndContext having(String attributePath) {
      return new AttributeCondition(attributePath);
   }

   @Override
   public FilterConditionBeginContext not() {
      return new IncompleteCondition().not();
   }

   @Override
   public FilterConditionContext not(FilterConditionContext fcc) {
      //todo [anistor] check fcc does not already belong to a builder
      BaseCondition baseCondition = (BaseCondition) fcc;
      NotCondition notCondition = new NotCondition(baseCondition);
      baseCondition.setParent(notCondition);
      return notCondition;
   }
}
