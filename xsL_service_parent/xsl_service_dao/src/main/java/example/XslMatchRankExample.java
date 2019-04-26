package example;

import java.util.ArrayList;
import java.util.List;

public class XslMatchRankExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslMatchRankExample() {
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

        public Criteria andMatchRankIdIsNull() {
            addCriterion("match_rank_id is null");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdIsNotNull() {
            addCriterion("match_rank_id is not null");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdEqualTo(String value) {
            addCriterion("match_rank_id =", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdNotEqualTo(String value) {
            addCriterion("match_rank_id <>", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdGreaterThan(String value) {
            addCriterion("match_rank_id >", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdGreaterThanOrEqualTo(String value) {
            addCriterion("match_rank_id >=", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdLessThan(String value) {
            addCriterion("match_rank_id <", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdLessThanOrEqualTo(String value) {
            addCriterion("match_rank_id <=", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdLike(String value) {
            addCriterion("match_rank_id like", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdNotLike(String value) {
            addCriterion("match_rank_id not like", value, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdIn(List<String> values) {
            addCriterion("match_rank_id in", values, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdNotIn(List<String> values) {
            addCriterion("match_rank_id not in", values, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdBetween(String value1, String value2) {
            addCriterion("match_rank_id between", value1, value2, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMatchRankIdNotBetween(String value1, String value2) {
            addCriterion("match_rank_id not between", value1, value2, "matchRankId");
            return (Criteria) this;
        }

        public Criteria andMankNameIsNull() {
            addCriterion("mank_name is null");
            return (Criteria) this;
        }

        public Criteria andMankNameIsNotNull() {
            addCriterion("mank_name is not null");
            return (Criteria) this;
        }

        public Criteria andMankNameEqualTo(String value) {
            addCriterion("mank_name =", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameNotEqualTo(String value) {
            addCriterion("mank_name <>", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameGreaterThan(String value) {
            addCriterion("mank_name >", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameGreaterThanOrEqualTo(String value) {
            addCriterion("mank_name >=", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameLessThan(String value) {
            addCriterion("mank_name <", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameLessThanOrEqualTo(String value) {
            addCriterion("mank_name <=", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameLike(String value) {
            addCriterion("mank_name like", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameNotLike(String value) {
            addCriterion("mank_name not like", value, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameIn(List<String> values) {
            addCriterion("mank_name in", values, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameNotIn(List<String> values) {
            addCriterion("mank_name not in", values, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameBetween(String value1, String value2) {
            addCriterion("mank_name between", value1, value2, "mankName");
            return (Criteria) this;
        }

        public Criteria andMankNameNotBetween(String value1, String value2) {
            addCriterion("mank_name not between", value1, value2, "mankName");
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