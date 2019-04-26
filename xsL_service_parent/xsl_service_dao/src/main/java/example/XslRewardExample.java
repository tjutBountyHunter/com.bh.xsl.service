package example;

import java.util.ArrayList;
import java.util.List;

public class XslRewardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslRewardExample() {
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

        public Criteria andRewardIdIsNull() {
            addCriterion("reward_id is null");
            return (Criteria) this;
        }

        public Criteria andRewardIdIsNotNull() {
            addCriterion("reward_id is not null");
            return (Criteria) this;
        }

        public Criteria andRewardIdEqualTo(String value) {
            addCriterion("reward_id =", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotEqualTo(String value) {
            addCriterion("reward_id <>", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdGreaterThan(String value) {
            addCriterion("reward_id >", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdGreaterThanOrEqualTo(String value) {
            addCriterion("reward_id >=", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdLessThan(String value) {
            addCriterion("reward_id <", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdLessThanOrEqualTo(String value) {
            addCriterion("reward_id <=", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdLike(String value) {
            addCriterion("reward_id like", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotLike(String value) {
            addCriterion("reward_id not like", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdIn(List<String> values) {
            addCriterion("reward_id in", values, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotIn(List<String> values) {
            addCriterion("reward_id not in", values, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdBetween(String value1, String value2) {
            addCriterion("reward_id between", value1, value2, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotBetween(String value1, String value2) {
            addCriterion("reward_id not between", value1, value2, "rewardId");
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

        public Criteria andRewardContentIsNull() {
            addCriterion("reward_content is null");
            return (Criteria) this;
        }

        public Criteria andRewardContentIsNotNull() {
            addCriterion("reward_content is not null");
            return (Criteria) this;
        }

        public Criteria andRewardContentEqualTo(String value) {
            addCriterion("reward_content =", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentNotEqualTo(String value) {
            addCriterion("reward_content <>", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentGreaterThan(String value) {
            addCriterion("reward_content >", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentGreaterThanOrEqualTo(String value) {
            addCriterion("reward_content >=", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentLessThan(String value) {
            addCriterion("reward_content <", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentLessThanOrEqualTo(String value) {
            addCriterion("reward_content <=", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentLike(String value) {
            addCriterion("reward_content like", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentNotLike(String value) {
            addCriterion("reward_content not like", value, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentIn(List<String> values) {
            addCriterion("reward_content in", values, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentNotIn(List<String> values) {
            addCriterion("reward_content not in", values, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentBetween(String value1, String value2) {
            addCriterion("reward_content between", value1, value2, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andRewardContentNotBetween(String value1, String value2) {
            addCriterion("reward_content not between", value1, value2, "rewardContent");
            return (Criteria) this;
        }

        public Criteria andContentQuotaIsNull() {
            addCriterion("content_quota is null");
            return (Criteria) this;
        }

        public Criteria andContentQuotaIsNotNull() {
            addCriterion("content_quota is not null");
            return (Criteria) this;
        }

        public Criteria andContentQuotaEqualTo(Integer value) {
            addCriterion("content_quota =", value, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaNotEqualTo(Integer value) {
            addCriterion("content_quota <>", value, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaGreaterThan(Integer value) {
            addCriterion("content_quota >", value, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaGreaterThanOrEqualTo(Integer value) {
            addCriterion("content_quota >=", value, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaLessThan(Integer value) {
            addCriterion("content_quota <", value, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaLessThanOrEqualTo(Integer value) {
            addCriterion("content_quota <=", value, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaIn(List<Integer> values) {
            addCriterion("content_quota in", values, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaNotIn(List<Integer> values) {
            addCriterion("content_quota not in", values, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaBetween(Integer value1, Integer value2) {
            addCriterion("content_quota between", value1, value2, "contentQuota");
            return (Criteria) this;
        }

        public Criteria andContentQuotaNotBetween(Integer value1, Integer value2) {
            addCriterion("content_quota not between", value1, value2, "contentQuota");
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