package example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IbmCommercialExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IbmCommercialExample() {
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

        public Criteria andUseridEqualTo(Integer value) {
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

        public Criteria andCommercialcodeIsNull() {
            addCriterion("commercialCode is null");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeIsNotNull() {
            addCriterion("commercialCode is not null");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeEqualTo(String value) {
            addCriterion("commercialCode =", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeNotEqualTo(String value) {
            addCriterion("commercialCode <>", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeGreaterThan(String value) {
            addCriterion("commercialCode >", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeGreaterThanOrEqualTo(String value) {
            addCriterion("commercialCode >=", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeLessThan(String value) {
            addCriterion("commercialCode <", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeLessThanOrEqualTo(String value) {
            addCriterion("commercialCode <=", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeLike(String value) {
            addCriterion("commercialCode like", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeNotLike(String value) {
            addCriterion("commercialCode not like", value, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeIn(List<String> values) {
            addCriterion("commercialCode in", values, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeNotIn(List<String> values) {
            addCriterion("commercialCode not in", values, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeBetween(String value1, String value2) {
            addCriterion("commercialCode between", value1, value2, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andCommercialcodeNotBetween(String value1, String value2) {
            addCriterion("commercialCode not between", value1, value2, "commercialcode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeIsNull() {
            addCriterion("efileCode is null");
            return (Criteria) this;
        }

        public Criteria andEfilecodeIsNotNull() {
            addCriterion("efileCode is not null");
            return (Criteria) this;
        }

        public Criteria andEfilecodeEqualTo(String value) {
            addCriterion("efileCode =", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeNotEqualTo(String value) {
            addCriterion("efileCode <>", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeGreaterThan(String value) {
            addCriterion("efileCode >", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeGreaterThanOrEqualTo(String value) {
            addCriterion("efileCode >=", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeLessThan(String value) {
            addCriterion("efileCode <", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeLessThanOrEqualTo(String value) {
            addCriterion("efileCode <=", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeLike(String value) {
            addCriterion("efileCode like", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeNotLike(String value) {
            addCriterion("efileCode not like", value, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeIn(List<String> values) {
            addCriterion("efileCode in", values, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeNotIn(List<String> values) {
            addCriterion("efileCode not in", values, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeBetween(String value1, String value2) {
            addCriterion("efileCode between", value1, value2, "efilecode");
            return (Criteria) this;
        }

        public Criteria andEfilecodeNotBetween(String value1, String value2) {
            addCriterion("efileCode not between", value1, value2, "efilecode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIsNull() {
            addCriterion("orderCode is null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIsNotNull() {
            addCriterion("orderCode is not null");
            return (Criteria) this;
        }

        public Criteria andOrdercodeEqualTo(String value) {
            addCriterion("orderCode =", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotEqualTo(String value) {
            addCriterion("orderCode <>", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThan(String value) {
            addCriterion("orderCode >", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeGreaterThanOrEqualTo(String value) {
            addCriterion("orderCode >=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThan(String value) {
            addCriterion("orderCode <", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLessThanOrEqualTo(String value) {
            addCriterion("orderCode <=", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeLike(String value) {
            addCriterion("orderCode like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotLike(String value) {
            addCriterion("orderCode not like", value, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeIn(List<String> values) {
            addCriterion("orderCode in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotIn(List<String> values) {
            addCriterion("orderCode not in", values, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeBetween(String value1, String value2) {
            addCriterion("orderCode between", value1, value2, "ordercode");
            return (Criteria) this;
        }

        public Criteria andOrdercodeNotBetween(String value1, String value2) {
            addCriterion("orderCode not between", value1, value2, "ordercode");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(String value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(String value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(String value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(String value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(String value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLike(String value) {
            addCriterion("money like", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotLike(String value) {
            addCriterion("money not like", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<String> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<String> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(String value1, String value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(String value1, String value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andRobotipIsNull() {
            addCriterion("robotIp is null");
            return (Criteria) this;
        }

        public Criteria andRobotipIsNotNull() {
            addCriterion("robotIp is not null");
            return (Criteria) this;
        }

        public Criteria andRobotipEqualTo(String value) {
            addCriterion("robotIp =", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipNotEqualTo(String value) {
            addCriterion("robotIp <>", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipGreaterThan(String value) {
            addCriterion("robotIp >", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipGreaterThanOrEqualTo(String value) {
            addCriterion("robotIp >=", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipLessThan(String value) {
            addCriterion("robotIp <", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipLessThanOrEqualTo(String value) {
            addCriterion("robotIp <=", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipLike(String value) {
            addCriterion("robotIp like", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipNotLike(String value) {
            addCriterion("robotIp not like", value, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipIn(List<String> values) {
            addCriterion("robotIp in", values, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipNotIn(List<String> values) {
            addCriterion("robotIp not in", values, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipBetween(String value1, String value2) {
            addCriterion("robotIp between", value1, value2, "robotip");
            return (Criteria) this;
        }

        public Criteria andRobotipNotBetween(String value1, String value2) {
            addCriterion("robotIp not between", value1, value2, "robotip");
            return (Criteria) this;
        }

        public Criteria andInformantIsNull() {
            addCriterion("informant is null");
            return (Criteria) this;
        }

        public Criteria andInformantIsNotNull() {
            addCriterion("informant is not null");
            return (Criteria) this;
        }

        public Criteria andInformantEqualTo(String value) {
            addCriterion("informant =", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantNotEqualTo(String value) {
            addCriterion("informant <>", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantGreaterThan(String value) {
            addCriterion("informant >", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantGreaterThanOrEqualTo(String value) {
            addCriterion("informant >=", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantLessThan(String value) {
            addCriterion("informant <", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantLessThanOrEqualTo(String value) {
            addCriterion("informant <=", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantLike(String value) {
            addCriterion("informant like", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantNotLike(String value) {
            addCriterion("informant not like", value, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantIn(List<String> values) {
            addCriterion("informant in", values, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantNotIn(List<String> values) {
            addCriterion("informant not in", values, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantBetween(String value1, String value2) {
            addCriterion("informant between", value1, value2, "informant");
            return (Criteria) this;
        }

        public Criteria andInformantNotBetween(String value1, String value2) {
            addCriterion("informant not between", value1, value2, "informant");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNull() {
            addCriterion("createDate is null");
            return (Criteria) this;
        }

        public Criteria andCreatedateIsNotNull() {
            addCriterion("createDate is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedateEqualTo(Date value) {
            addCriterionForJDBCDate("createDate =", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("createDate <>", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("createDate >", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createDate >=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThan(Date value) {
            addCriterionForJDBCDate("createDate <", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("createDate <=", value, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateIn(List<Date> values) {
            addCriterionForJDBCDate("createDate in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("createDate not in", values, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createDate between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andCreatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("createDate not between", value1, value2, "createdate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNull() {
            addCriterion("updateDate is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIsNotNull() {
            addCriterion("updateDate is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedateEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate =", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate <>", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThan(Date value) {
            addCriterionForJDBCDate("updateDate >", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate >=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThan(Date value) {
            addCriterionForJDBCDate("updateDate <", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("updateDate <=", value, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateIn(List<Date> values) {
            addCriterionForJDBCDate("updateDate in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotIn(List<Date> values) {
            addCriterionForJDBCDate("updateDate not in", values, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateDate between", value1, value2, "updatedate");
            return (Criteria) this;
        }

        public Criteria andUpdatedateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("updateDate not between", value1, value2, "updatedate");
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