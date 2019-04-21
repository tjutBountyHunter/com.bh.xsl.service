package example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XslHunterExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslHunterExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Short value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Short value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Short value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Short value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Short value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Short> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Short> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Short value1, Short value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Short value1, Short value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andEmpiricalIsNull() {
            addCriterion("empirical is null");
            return (Criteria) this;
        }

        public Criteria andEmpiricalIsNotNull() {
            addCriterion("empirical is not null");
            return (Criteria) this;
        }

        public Criteria andEmpiricalEqualTo(Integer value) {
            addCriterion("empirical =", value, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalNotEqualTo(Integer value) {
            addCriterion("empirical <>", value, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalGreaterThan(Integer value) {
            addCriterion("empirical >", value, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalGreaterThanOrEqualTo(Integer value) {
            addCriterion("empirical >=", value, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalLessThan(Integer value) {
            addCriterion("empirical <", value, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalLessThanOrEqualTo(Integer value) {
            addCriterion("empirical <=", value, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalIn(List<Integer> values) {
            addCriterion("empirical in", values, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalNotIn(List<Integer> values) {
            addCriterion("empirical not in", values, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalBetween(Integer value1, Integer value2) {
            addCriterion("empirical between", value1, value2, "empirical");
            return (Criteria) this;
        }

        public Criteria andEmpiricalNotBetween(Integer value1, Integer value2) {
            addCriterion("empirical not between", value1, value2, "empirical");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumIsNull() {
            addCriterion("taskAccNum is null");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumIsNotNull() {
            addCriterion("taskAccNum is not null");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumEqualTo(Integer value) {
            addCriterion("taskAccNum =", value, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumNotEqualTo(Integer value) {
            addCriterion("taskAccNum <>", value, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumGreaterThan(Integer value) {
            addCriterion("taskAccNum >", value, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskAccNum >=", value, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumLessThan(Integer value) {
            addCriterion("taskAccNum <", value, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumLessThanOrEqualTo(Integer value) {
            addCriterion("taskAccNum <=", value, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumIn(List<Integer> values) {
            addCriterion("taskAccNum in", values, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumNotIn(List<Integer> values) {
            addCriterion("taskAccNum not in", values, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumBetween(Integer value1, Integer value2) {
            addCriterion("taskAccNum between", value1, value2, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskaccnumNotBetween(Integer value1, Integer value2) {
            addCriterion("taskAccNum not between", value1, value2, "taskaccnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumIsNull() {
            addCriterion("taskFailNum is null");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumIsNotNull() {
            addCriterion("taskFailNum is not null");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumEqualTo(Integer value) {
            addCriterion("taskFailNum =", value, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumNotEqualTo(Integer value) {
            addCriterion("taskFailNum <>", value, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumGreaterThan(Integer value) {
            addCriterion("taskFailNum >", value, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskFailNum >=", value, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumLessThan(Integer value) {
            addCriterion("taskFailNum <", value, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumLessThanOrEqualTo(Integer value) {
            addCriterion("taskFailNum <=", value, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumIn(List<Integer> values) {
            addCriterion("taskFailNum in", values, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumNotIn(List<Integer> values) {
            addCriterion("taskFailNum not in", values, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumBetween(Integer value1, Integer value2) {
            addCriterion("taskFailNum between", value1, value2, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andTaskfailnumNotBetween(Integer value1, Integer value2) {
            addCriterion("taskFailNum not between", value1, value2, "taskfailnum");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Short value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Short value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Short value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Short value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Short value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Short value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Short> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Short> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Short value1, Short value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Short value1, Short value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andDescrIsNull() {
            addCriterion("descr is null");
            return (Criteria) this;
        }

        public Criteria andDescrIsNotNull() {
            addCriterion("descr is not null");
            return (Criteria) this;
        }

        public Criteria andDescrEqualTo(String value) {
            addCriterion("descr =", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotEqualTo(String value) {
            addCriterion("descr <>", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThan(String value) {
            addCriterion("descr >", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThanOrEqualTo(String value) {
            addCriterion("descr >=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThan(String value) {
            addCriterion("descr <", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThanOrEqualTo(String value) {
            addCriterion("descr <=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLike(String value) {
            addCriterion("descr like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotLike(String value) {
            addCriterion("descr not like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrIn(List<String> values) {
            addCriterion("descr in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotIn(List<String> values) {
            addCriterion("descr not in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrBetween(String value1, String value2) {
            addCriterion("descr between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotBetween(String value1, String value2) {
            addCriterion("descr not between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andLastaccdateIsNull() {
            addCriterion("lastAccDate is null");
            return (Criteria) this;
        }

        public Criteria andLastaccdateIsNotNull() {
            addCriterion("lastAccDate is not null");
            return (Criteria) this;
        }

        public Criteria andLastaccdateEqualTo(Date value) {
            addCriterionForJDBCDate("lastAccDate =", value, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateNotEqualTo(Date value) {
            addCriterionForJDBCDate("lastAccDate <>", value, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateGreaterThan(Date value) {
            addCriterionForJDBCDate("lastAccDate >", value, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastAccDate >=", value, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateLessThan(Date value) {
            addCriterionForJDBCDate("lastAccDate <", value, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("lastAccDate <=", value, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateIn(List<Date> values) {
            addCriterionForJDBCDate("lastAccDate in", values, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateNotIn(List<Date> values) {
            addCriterionForJDBCDate("lastAccDate not in", values, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastAccDate between", value1, value2, "lastaccdate");
            return (Criteria) this;
        }

        public Criteria andLastaccdateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("lastAccDate not between", value1, value2, "lastaccdate");
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