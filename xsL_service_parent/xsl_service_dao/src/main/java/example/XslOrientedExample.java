package example;

import java.util.ArrayList;
import java.util.List;

public class XslOrientedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslOrientedExample() {
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
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrientedIdIsNull() {
            addCriterion("oriented_id is null");
            return (Criteria) this;
        }

        public Criteria andOrientedIdIsNotNull() {
            addCriterion("oriented_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrientedIdEqualTo(String value) {
            addCriterion("oriented_id =", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdNotEqualTo(String value) {
            addCriterion("oriented_id <>", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdGreaterThan(String value) {
            addCriterion("oriented_id >", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdGreaterThanOrEqualTo(String value) {
            addCriterion("oriented_id >=", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdLessThan(String value) {
            addCriterion("oriented_id <", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdLessThanOrEqualTo(String value) {
            addCriterion("oriented_id <=", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdLike(String value) {
            addCriterion("oriented_id like", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdNotLike(String value) {
            addCriterion("oriented_id not like", value, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdIn(List<String> values) {
            addCriterion("oriented_id in", values, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdNotIn(List<String> values) {
            addCriterion("oriented_id not in", values, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdBetween(String value1, String value2) {
            addCriterion("oriented_id between", value1, value2, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedIdNotBetween(String value1, String value2) {
            addCriterion("oriented_id not between", value1, value2, "orientedId");
            return (Criteria) this;
        }

        public Criteria andOrientedNameIsNull() {
            addCriterion("oriented_name is null");
            return (Criteria) this;
        }

        public Criteria andOrientedNameIsNotNull() {
            addCriterion("oriented_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrientedNameEqualTo(String value) {
            addCriterion("oriented_name =", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameNotEqualTo(String value) {
            addCriterion("oriented_name <>", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameGreaterThan(String value) {
            addCriterion("oriented_name >", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameGreaterThanOrEqualTo(String value) {
            addCriterion("oriented_name >=", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameLessThan(String value) {
            addCriterion("oriented_name <", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameLessThanOrEqualTo(String value) {
            addCriterion("oriented_name <=", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameLike(String value) {
            addCriterion("oriented_name like", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameNotLike(String value) {
            addCriterion("oriented_name not like", value, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameIn(List<String> values) {
            addCriterion("oriented_name in", values, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameNotIn(List<String> values) {
            addCriterion("oriented_name not in", values, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameBetween(String value1, String value2) {
            addCriterion("oriented_name between", value1, value2, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedNameNotBetween(String value1, String value2) {
            addCriterion("oriented_name not between", value1, value2, "orientedName");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoIsNull() {
            addCriterion("oriented_info is null");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoIsNotNull() {
            addCriterion("oriented_info is not null");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoEqualTo(String value) {
            addCriterion("oriented_info =", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoNotEqualTo(String value) {
            addCriterion("oriented_info <>", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoGreaterThan(String value) {
            addCriterion("oriented_info >", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoGreaterThanOrEqualTo(String value) {
            addCriterion("oriented_info >=", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoLessThan(String value) {
            addCriterion("oriented_info <", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoLessThanOrEqualTo(String value) {
            addCriterion("oriented_info <=", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoLike(String value) {
            addCriterion("oriented_info like", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoNotLike(String value) {
            addCriterion("oriented_info not like", value, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoIn(List<String> values) {
            addCriterion("oriented_info in", values, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoNotIn(List<String> values) {
            addCriterion("oriented_info not in", values, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoBetween(String value1, String value2) {
            addCriterion("oriented_info between", value1, value2, "orientedInfo");
            return (Criteria) this;
        }

        public Criteria andOrientedInfoNotBetween(String value1, String value2) {
            addCriterion("oriented_info not between", value1, value2, "orientedInfo");
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