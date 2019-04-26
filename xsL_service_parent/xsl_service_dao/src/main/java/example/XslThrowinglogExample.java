package example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XslThrowinglogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslThrowinglogExample() {
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

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andThrowingIsNull() {
            addCriterion("throwing is null");
            return (Criteria) this;
        }

        public Criteria andThrowingIsNotNull() {
            addCriterion("throwing is not null");
            return (Criteria) this;
        }

        public Criteria andThrowingEqualTo(String value) {
            addCriterion("throwing =", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingNotEqualTo(String value) {
            addCriterion("throwing <>", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingGreaterThan(String value) {
            addCriterion("throwing >", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingGreaterThanOrEqualTo(String value) {
            addCriterion("throwing >=", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingLessThan(String value) {
            addCriterion("throwing <", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingLessThanOrEqualTo(String value) {
            addCriterion("throwing <=", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingLike(String value) {
            addCriterion("throwing like", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingNotLike(String value) {
            addCriterion("throwing not like", value, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingIn(List<String> values) {
            addCriterion("throwing in", values, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingNotIn(List<String> values) {
            addCriterion("throwing not in", values, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingBetween(String value1, String value2) {
            addCriterion("throwing between", value1, value2, "throwing");
            return (Criteria) this;
        }

        public Criteria andThrowingNotBetween(String value1, String value2) {
            addCriterion("throwing not between", value1, value2, "throwing");
            return (Criteria) this;
        }

        public Criteria andOperationerIsNull() {
            addCriterion("operationer is null");
            return (Criteria) this;
        }

        public Criteria andOperationerIsNotNull() {
            addCriterion("operationer is not null");
            return (Criteria) this;
        }

        public Criteria andOperationerEqualTo(String value) {
            addCriterion("operationer =", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerNotEqualTo(String value) {
            addCriterion("operationer <>", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerGreaterThan(String value) {
            addCriterion("operationer >", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerGreaterThanOrEqualTo(String value) {
            addCriterion("operationer >=", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerLessThan(String value) {
            addCriterion("operationer <", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerLessThanOrEqualTo(String value) {
            addCriterion("operationer <=", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerLike(String value) {
            addCriterion("operationer like", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerNotLike(String value) {
            addCriterion("operationer not like", value, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerIn(List<String> values) {
            addCriterion("operationer in", values, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerNotIn(List<String> values) {
            addCriterion("operationer not in", values, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerBetween(String value1, String value2) {
            addCriterion("operationer between", value1, value2, "operationer");
            return (Criteria) this;
        }

        public Criteria andOperationerNotBetween(String value1, String value2) {
            addCriterion("operationer not between", value1, value2, "operationer");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeIsNull() {
            addCriterion("throwingtime is null");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeIsNotNull() {
            addCriterion("throwingtime is not null");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeEqualTo(Date value) {
            addCriterion("throwingtime =", value, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeNotEqualTo(Date value) {
            addCriterion("throwingtime <>", value, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeGreaterThan(Date value) {
            addCriterion("throwingtime >", value, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("throwingtime >=", value, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeLessThan(Date value) {
            addCriterion("throwingtime <", value, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeLessThanOrEqualTo(Date value) {
            addCriterion("throwingtime <=", value, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeIn(List<Date> values) {
            addCriterion("throwingtime in", values, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeNotIn(List<Date> values) {
            addCriterion("throwingtime not in", values, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeBetween(Date value1, Date value2) {
            addCriterion("throwingtime between", value1, value2, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andThrowingtimeNotBetween(Date value1, Date value2) {
            addCriterion("throwingtime not between", value1, value2, "throwingtime");
            return (Criteria) this;
        }

        public Criteria andMethodnameIsNull() {
            addCriterion("methodName is null");
            return (Criteria) this;
        }

        public Criteria andMethodnameIsNotNull() {
            addCriterion("methodName is not null");
            return (Criteria) this;
        }

        public Criteria andMethodnameEqualTo(String value) {
            addCriterion("methodName =", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameNotEqualTo(String value) {
            addCriterion("methodName <>", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameGreaterThan(String value) {
            addCriterion("methodName >", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameGreaterThanOrEqualTo(String value) {
            addCriterion("methodName >=", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameLessThan(String value) {
            addCriterion("methodName <", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameLessThanOrEqualTo(String value) {
            addCriterion("methodName <=", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameLike(String value) {
            addCriterion("methodName like", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameNotLike(String value) {
            addCriterion("methodName not like", value, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameIn(List<String> values) {
            addCriterion("methodName in", values, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameNotIn(List<String> values) {
            addCriterion("methodName not in", values, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameBetween(String value1, String value2) {
            addCriterion("methodName between", value1, value2, "methodname");
            return (Criteria) this;
        }

        public Criteria andMethodnameNotBetween(String value1, String value2) {
            addCriterion("methodName not between", value1, value2, "methodname");
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