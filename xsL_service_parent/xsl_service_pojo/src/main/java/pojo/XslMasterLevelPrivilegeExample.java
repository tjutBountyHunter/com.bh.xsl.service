package pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XslMasterLevelPrivilegeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public XslMasterLevelPrivilegeExample() {
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

        public Criteria andHunterlevelidIsNull() {
            addCriterion("hunterLevelId is null");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidIsNotNull() {
            addCriterion("hunterLevelId is not null");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidEqualTo(String value) {
            addCriterion("hunterLevelId =", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidNotEqualTo(String value) {
            addCriterion("hunterLevelId <>", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidGreaterThan(String value) {
            addCriterion("hunterLevelId >", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidGreaterThanOrEqualTo(String value) {
            addCriterion("hunterLevelId >=", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidLessThan(String value) {
            addCriterion("hunterLevelId <", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidLessThanOrEqualTo(String value) {
            addCriterion("hunterLevelId <=", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidLike(String value) {
            addCriterion("hunterLevelId like", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidNotLike(String value) {
            addCriterion("hunterLevelId not like", value, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidIn(List<String> values) {
            addCriterion("hunterLevelId in", values, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidNotIn(List<String> values) {
            addCriterion("hunterLevelId not in", values, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidBetween(String value1, String value2) {
            addCriterion("hunterLevelId between", value1, value2, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andHunterlevelidNotBetween(String value1, String value2) {
            addCriterion("hunterLevelId not between", value1, value2, "hunterlevelid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidIsNull() {
            addCriterion("privilegeId is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidIsNotNull() {
            addCriterion("privilegeId is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidEqualTo(String value) {
            addCriterion("privilegeId =", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidNotEqualTo(String value) {
            addCriterion("privilegeId <>", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidGreaterThan(String value) {
            addCriterion("privilegeId >", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidGreaterThanOrEqualTo(String value) {
            addCriterion("privilegeId >=", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidLessThan(String value) {
            addCriterion("privilegeId <", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidLessThanOrEqualTo(String value) {
            addCriterion("privilegeId <=", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidLike(String value) {
            addCriterion("privilegeId like", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidNotLike(String value) {
            addCriterion("privilegeId not like", value, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidIn(List<String> values) {
            addCriterion("privilegeId in", values, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidNotIn(List<String> values) {
            addCriterion("privilegeId not in", values, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidBetween(String value1, String value2) {
            addCriterion("privilegeId between", value1, value2, "privilegeid");
            return (Criteria) this;
        }

        public Criteria andPrivilegeidNotBetween(String value1, String value2) {
            addCriterion("privilegeId not between", value1, value2, "privilegeid");
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