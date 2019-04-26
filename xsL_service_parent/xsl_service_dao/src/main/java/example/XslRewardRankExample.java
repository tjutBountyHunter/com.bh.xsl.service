package example;

import java.util.ArrayList;
import java.util.List;

public class XslRewardRankExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslRewardRankExample() {
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

        public Criteria andRewardRankIdIsNull() {
            addCriterion("reward_rank_id is null");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdIsNotNull() {
            addCriterion("reward_rank_id is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdEqualTo(String value) {
            addCriterion("reward_rank_id =", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdNotEqualTo(String value) {
            addCriterion("reward_rank_id <>", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdGreaterThan(String value) {
            addCriterion("reward_rank_id >", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdGreaterThanOrEqualTo(String value) {
            addCriterion("reward_rank_id >=", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdLessThan(String value) {
            addCriterion("reward_rank_id <", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdLessThanOrEqualTo(String value) {
            addCriterion("reward_rank_id <=", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdLike(String value) {
            addCriterion("reward_rank_id like", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdNotLike(String value) {
            addCriterion("reward_rank_id not like", value, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdIn(List<String> values) {
            addCriterion("reward_rank_id in", values, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdNotIn(List<String> values) {
            addCriterion("reward_rank_id not in", values, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdBetween(String value1, String value2) {
            addCriterion("reward_rank_id between", value1, value2, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankIdNotBetween(String value1, String value2) {
            addCriterion("reward_rank_id not between", value1, value2, "rewardRankId");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameIsNull() {
            addCriterion("reward_rank_name is null");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameIsNotNull() {
            addCriterion("reward_rank_name is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameEqualTo(String value) {
            addCriterion("reward_rank_name =", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameNotEqualTo(String value) {
            addCriterion("reward_rank_name <>", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameGreaterThan(String value) {
            addCriterion("reward_rank_name >", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameGreaterThanOrEqualTo(String value) {
            addCriterion("reward_rank_name >=", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameLessThan(String value) {
            addCriterion("reward_rank_name <", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameLessThanOrEqualTo(String value) {
            addCriterion("reward_rank_name <=", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameLike(String value) {
            addCriterion("reward_rank_name like", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameNotLike(String value) {
            addCriterion("reward_rank_name not like", value, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameIn(List<String> values) {
            addCriterion("reward_rank_name in", values, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameNotIn(List<String> values) {
            addCriterion("reward_rank_name not in", values, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameBetween(String value1, String value2) {
            addCriterion("reward_rank_name between", value1, value2, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankNameNotBetween(String value1, String value2) {
            addCriterion("reward_rank_name not between", value1, value2, "rewardRankName");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoIsNull() {
            addCriterion("reward_rank_info is null");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoIsNotNull() {
            addCriterion("reward_rank_info is not null");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoEqualTo(String value) {
            addCriterion("reward_rank_info =", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoNotEqualTo(String value) {
            addCriterion("reward_rank_info <>", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoGreaterThan(String value) {
            addCriterion("reward_rank_info >", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoGreaterThanOrEqualTo(String value) {
            addCriterion("reward_rank_info >=", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoLessThan(String value) {
            addCriterion("reward_rank_info <", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoLessThanOrEqualTo(String value) {
            addCriterion("reward_rank_info <=", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoLike(String value) {
            addCriterion("reward_rank_info like", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoNotLike(String value) {
            addCriterion("reward_rank_info not like", value, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoIn(List<String> values) {
            addCriterion("reward_rank_info in", values, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoNotIn(List<String> values) {
            addCriterion("reward_rank_info not in", values, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoBetween(String value1, String value2) {
            addCriterion("reward_rank_info between", value1, value2, "rewardRankInfo");
            return (Criteria) this;
        }

        public Criteria andRewardRankInfoNotBetween(String value1, String value2) {
            addCriterion("reward_rank_info not between", value1, value2, "rewardRankInfo");
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