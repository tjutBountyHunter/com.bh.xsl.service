package example;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XslMatchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslMatchExample() {
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

        public Criteria andMatchIdIsNull() {
            addCriterion("match_id is null");
            return (Criteria) this;
        }

        public Criteria andMatchIdIsNotNull() {
            addCriterion("match_id is not null");
            return (Criteria) this;
        }

        public Criteria andMatchIdEqualTo(String value) {
            addCriterion("match_id =", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotEqualTo(String value) {
            addCriterion("match_id <>", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdGreaterThan(String value) {
            addCriterion("match_id >", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdGreaterThanOrEqualTo(String value) {
            addCriterion("match_id >=", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLessThan(String value) {
            addCriterion("match_id <", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLessThanOrEqualTo(String value) {
            addCriterion("match_id <=", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdLike(String value) {
            addCriterion("match_id like", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotLike(String value) {
            addCriterion("match_id not like", value, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdIn(List<String> values) {
            addCriterion("match_id in", values, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotIn(List<String> values) {
            addCriterion("match_id not in", values, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdBetween(String value1, String value2) {
            addCriterion("match_id between", value1, value2, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchIdNotBetween(String value1, String value2) {
            addCriterion("match_id not between", value1, value2, "matchId");
            return (Criteria) this;
        }

        public Criteria andMatchNameIsNull() {
            addCriterion("match_name is null");
            return (Criteria) this;
        }

        public Criteria andMatchNameIsNotNull() {
            addCriterion("match_name is not null");
            return (Criteria) this;
        }

        public Criteria andMatchNameEqualTo(String value) {
            addCriterion("match_name =", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotEqualTo(String value) {
            addCriterion("match_name <>", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameGreaterThan(String value) {
            addCriterion("match_name >", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("match_name >=", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameLessThan(String value) {
            addCriterion("match_name <", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameLessThanOrEqualTo(String value) {
            addCriterion("match_name <=", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameLike(String value) {
            addCriterion("match_name like", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotLike(String value) {
            addCriterion("match_name not like", value, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameIn(List<String> values) {
            addCriterion("match_name in", values, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotIn(List<String> values) {
            addCriterion("match_name not in", values, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameBetween(String value1, String value2) {
            addCriterion("match_name between", value1, value2, "matchName");
            return (Criteria) this;
        }

        public Criteria andMatchNameNotBetween(String value1, String value2) {
            addCriterion("match_name not between", value1, value2, "matchName");
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

        public Criteria andMatchWebsiteIsNull() {
            addCriterion("match_website is null");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteIsNotNull() {
            addCriterion("match_website is not null");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteEqualTo(String value) {
            addCriterion("match_website =", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteNotEqualTo(String value) {
            addCriterion("match_website <>", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteGreaterThan(String value) {
            addCriterion("match_website >", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("match_website >=", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteLessThan(String value) {
            addCriterion("match_website <", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteLessThanOrEqualTo(String value) {
            addCriterion("match_website <=", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteLike(String value) {
            addCriterion("match_website like", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteNotLike(String value) {
            addCriterion("match_website not like", value, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteIn(List<String> values) {
            addCriterion("match_website in", values, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteNotIn(List<String> values) {
            addCriterion("match_website not in", values, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteBetween(String value1, String value2) {
            addCriterion("match_website between", value1, value2, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchWebsiteNotBetween(String value1, String value2) {
            addCriterion("match_website not between", value1, value2, "matchWebsite");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdIsNull() {
            addCriterion("match_oriented_id is null");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdIsNotNull() {
            addCriterion("match_oriented_id is not null");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdEqualTo(String value) {
            addCriterion("match_oriented_id =", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdNotEqualTo(String value) {
            addCriterion("match_oriented_id <>", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdGreaterThan(String value) {
            addCriterion("match_oriented_id >", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdGreaterThanOrEqualTo(String value) {
            addCriterion("match_oriented_id >=", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdLessThan(String value) {
            addCriterion("match_oriented_id <", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdLessThanOrEqualTo(String value) {
            addCriterion("match_oriented_id <=", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdLike(String value) {
            addCriterion("match_oriented_id like", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdNotLike(String value) {
            addCriterion("match_oriented_id not like", value, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdIn(List<String> values) {
            addCriterion("match_oriented_id in", values, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdNotIn(List<String> values) {
            addCriterion("match_oriented_id not in", values, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdBetween(String value1, String value2) {
            addCriterion("match_oriented_id between", value1, value2, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchOrientedIdNotBetween(String value1, String value2) {
            addCriterion("match_oriented_id not between", value1, value2, "matchOrientedId");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeIsNull() {
            addCriterion("match_startTime is null");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeIsNotNull() {
            addCriterion("match_startTime is not null");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeEqualTo(Date value) {
            addCriterionForJDBCDate("match_startTime =", value, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("match_startTime <>", value, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeGreaterThan(Date value) {
            addCriterionForJDBCDate("match_startTime >", value, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("match_startTime >=", value, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeLessThan(Date value) {
            addCriterionForJDBCDate("match_startTime <", value, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("match_startTime <=", value, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeIn(List<Date> values) {
            addCriterionForJDBCDate("match_startTime in", values, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("match_startTime not in", values, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("match_startTime between", value1, value2, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchStarttimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("match_startTime not between", value1, value2, "matchStarttime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeIsNull() {
            addCriterion("match_endTime is null");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeIsNotNull() {
            addCriterion("match_endTime is not null");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeEqualTo(Date value) {
            addCriterionForJDBCDate("match_endTime =", value, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("match_endTime <>", value, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("match_endTime >", value, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("match_endTime >=", value, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeLessThan(Date value) {
            addCriterionForJDBCDate("match_endTime <", value, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("match_endTime <=", value, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeIn(List<Date> values) {
            addCriterionForJDBCDate("match_endTime in", values, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("match_endTime not in", values, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("match_endTime between", value1, value2, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchEndtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("match_endTime not between", value1, value2, "matchEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchStateIsNull() {
            addCriterion("match_state is null");
            return (Criteria) this;
        }

        public Criteria andMatchStateIsNotNull() {
            addCriterion("match_state is not null");
            return (Criteria) this;
        }

        public Criteria andMatchStateEqualTo(Integer value) {
            addCriterion("match_state =", value, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateNotEqualTo(Integer value) {
            addCriterion("match_state <>", value, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateGreaterThan(Integer value) {
            addCriterion("match_state >", value, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_state >=", value, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateLessThan(Integer value) {
            addCriterion("match_state <", value, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateLessThanOrEqualTo(Integer value) {
            addCriterion("match_state <=", value, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateIn(List<Integer> values) {
            addCriterion("match_state in", values, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateNotIn(List<Integer> values) {
            addCriterion("match_state not in", values, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateBetween(Integer value1, Integer value2) {
            addCriterion("match_state between", value1, value2, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchStateNotBetween(Integer value1, Integer value2) {
            addCriterion("match_state not between", value1, value2, "matchState");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeIsNull() {
            addCriterion("match_signUp_endTime is null");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeIsNotNull() {
            addCriterion("match_signUp_endTime is not null");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeEqualTo(Date value) {
            addCriterion("match_signUp_endTime =", value, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeNotEqualTo(Date value) {
            addCriterion("match_signUp_endTime <>", value, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeGreaterThan(Date value) {
            addCriterion("match_signUp_endTime >", value, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("match_signUp_endTime >=", value, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeLessThan(Date value) {
            addCriterion("match_signUp_endTime <", value, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("match_signUp_endTime <=", value, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeIn(List<Date> values) {
            addCriterion("match_signUp_endTime in", values, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeNotIn(List<Date> values) {
            addCriterion("match_signUp_endTime not in", values, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeBetween(Date value1, Date value2) {
            addCriterion("match_signUp_endTime between", value1, value2, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchSignupEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("match_signUp_endTime not between", value1, value2, "matchSignupEndtime");
            return (Criteria) this;
        }

        public Criteria andMatchAddressIsNull() {
            addCriterion("match_address is null");
            return (Criteria) this;
        }

        public Criteria andMatchAddressIsNotNull() {
            addCriterion("match_address is not null");
            return (Criteria) this;
        }

        public Criteria andMatchAddressEqualTo(String value) {
            addCriterion("match_address =", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotEqualTo(String value) {
            addCriterion("match_address <>", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressGreaterThan(String value) {
            addCriterion("match_address >", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressGreaterThanOrEqualTo(String value) {
            addCriterion("match_address >=", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressLessThan(String value) {
            addCriterion("match_address <", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressLessThanOrEqualTo(String value) {
            addCriterion("match_address <=", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressLike(String value) {
            addCriterion("match_address like", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotLike(String value) {
            addCriterion("match_address not like", value, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressIn(List<String> values) {
            addCriterion("match_address in", values, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotIn(List<String> values) {
            addCriterion("match_address not in", values, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressBetween(String value1, String value2) {
            addCriterion("match_address between", value1, value2, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchAddressNotBetween(String value1, String value2) {
            addCriterion("match_address not between", value1, value2, "matchAddress");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumIsNull() {
            addCriterion("match_signUp_maxNum is null");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumIsNotNull() {
            addCriterion("match_signUp_maxNum is not null");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumEqualTo(Integer value) {
            addCriterion("match_signUp_maxNum =", value, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumNotEqualTo(Integer value) {
            addCriterion("match_signUp_maxNum <>", value, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumGreaterThan(Integer value) {
            addCriterion("match_signUp_maxNum >", value, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_signUp_maxNum >=", value, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumLessThan(Integer value) {
            addCriterion("match_signUp_maxNum <", value, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumLessThanOrEqualTo(Integer value) {
            addCriterion("match_signUp_maxNum <=", value, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumIn(List<Integer> values) {
            addCriterion("match_signUp_maxNum in", values, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumNotIn(List<Integer> values) {
            addCriterion("match_signUp_maxNum not in", values, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumBetween(Integer value1, Integer value2) {
            addCriterion("match_signUp_maxNum between", value1, value2, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchSignupMaxnumNotBetween(Integer value1, Integer value2) {
            addCriterion("match_signUp_maxNum not between", value1, value2, "matchSignupMaxnum");
            return (Criteria) this;
        }

        public Criteria andMatchFormIsNull() {
            addCriterion("match_form is null");
            return (Criteria) this;
        }

        public Criteria andMatchFormIsNotNull() {
            addCriterion("match_form is not null");
            return (Criteria) this;
        }

        public Criteria andMatchFormEqualTo(Integer value) {
            addCriterion("match_form =", value, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormNotEqualTo(Integer value) {
            addCriterion("match_form <>", value, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormGreaterThan(Integer value) {
            addCriterion("match_form >", value, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_form >=", value, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormLessThan(Integer value) {
            addCriterion("match_form <", value, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormLessThanOrEqualTo(Integer value) {
            addCriterion("match_form <=", value, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormIn(List<Integer> values) {
            addCriterion("match_form in", values, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormNotIn(List<Integer> values) {
            addCriterion("match_form not in", values, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormBetween(Integer value1, Integer value2) {
            addCriterion("match_form between", value1, value2, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchFormNotBetween(Integer value1, Integer value2) {
            addCriterion("match_form not between", value1, value2, "matchForm");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumIsNull() {
            addCriterion("match_teamNum is null");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumIsNotNull() {
            addCriterion("match_teamNum is not null");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumEqualTo(Integer value) {
            addCriterion("match_teamNum =", value, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumNotEqualTo(Integer value) {
            addCriterion("match_teamNum <>", value, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumGreaterThan(Integer value) {
            addCriterion("match_teamNum >", value, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_teamNum >=", value, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumLessThan(Integer value) {
            addCriterion("match_teamNum <", value, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumLessThanOrEqualTo(Integer value) {
            addCriterion("match_teamNum <=", value, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumIn(List<Integer> values) {
            addCriterion("match_teamNum in", values, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumNotIn(List<Integer> values) {
            addCriterion("match_teamNum not in", values, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumBetween(Integer value1, Integer value2) {
            addCriterion("match_teamNum between", value1, value2, "matchTeamnum");
            return (Criteria) this;
        }

        public Criteria andMatchTeamnumNotBetween(Integer value1, Integer value2) {
            addCriterion("match_teamNum not between", value1, value2, "matchTeamnum");
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