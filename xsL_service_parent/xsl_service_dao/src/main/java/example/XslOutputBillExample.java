package example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XslOutputBillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslOutputBillExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOutputidIsNull() {
            addCriterion("outputId is null");
            return (Criteria) this;
        }

        public Criteria andOutputidIsNotNull() {
            addCriterion("outputId is not null");
            return (Criteria) this;
        }

        public Criteria andOutputidEqualTo(String value) {
            addCriterion("outputId =", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidNotEqualTo(String value) {
            addCriterion("outputId <>", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidGreaterThan(String value) {
            addCriterion("outputId >", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidGreaterThanOrEqualTo(String value) {
            addCriterion("outputId >=", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidLessThan(String value) {
            addCriterion("outputId <", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidLessThanOrEqualTo(String value) {
            addCriterion("outputId <=", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidLike(String value) {
            addCriterion("outputId like", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidNotLike(String value) {
            addCriterion("outputId not like", value, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidIn(List<String> values) {
            addCriterion("outputId in", values, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidNotIn(List<String> values) {
            addCriterion("outputId not in", values, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidBetween(String value1, String value2) {
            addCriterion("outputId between", value1, value2, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputidNotBetween(String value1, String value2) {
            addCriterion("outputId not between", value1, value2, "outputid");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyIsNull() {
            addCriterion("output_money is null");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyIsNotNull() {
            addCriterion("output_money is not null");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyEqualTo(Double value) {
            addCriterion("output_money =", value, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyNotEqualTo(Double value) {
            addCriterion("output_money <>", value, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyGreaterThan(Double value) {
            addCriterion("output_money >", value, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("output_money >=", value, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyLessThan(Double value) {
            addCriterion("output_money <", value, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyLessThanOrEqualTo(Double value) {
            addCriterion("output_money <=", value, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyIn(List<Double> values) {
            addCriterion("output_money in", values, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyNotIn(List<Double> values) {
            addCriterion("output_money not in", values, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyBetween(Double value1, Double value2) {
            addCriterion("output_money between", value1, value2, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andOutputMoneyNotBetween(Double value1, Double value2) {
            addCriterion("output_money not between", value1, value2, "outputMoney");
            return (Criteria) this;
        }

        public Criteria andTradetimeIsNull() {
            addCriterion("tradeTime is null");
            return (Criteria) this;
        }

        public Criteria andTradetimeIsNotNull() {
            addCriterion("tradeTime is not null");
            return (Criteria) this;
        }

        public Criteria andTradetimeEqualTo(Date value) {
            addCriterion("tradeTime =", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotEqualTo(Date value) {
            addCriterion("tradeTime <>", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeGreaterThan(Date value) {
            addCriterion("tradeTime >", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradeTime >=", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeLessThan(Date value) {
            addCriterion("tradeTime <", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeLessThanOrEqualTo(Date value) {
            addCriterion("tradeTime <=", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeIn(List<Date> values) {
            addCriterion("tradeTime in", values, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotIn(List<Date> values) {
            addCriterion("tradeTime not in", values, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeBetween(Date value1, Date value2) {
            addCriterion("tradeTime between", value1, value2, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotBetween(Date value1, Date value2) {
            addCriterion("tradeTime not between", value1, value2, "tradetime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}