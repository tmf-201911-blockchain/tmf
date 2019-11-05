package com.whalecloud.domain.re;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuotationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuotationExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdEqualTo(String value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotEqualTo(String value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThan(String value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThan(String value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThanOrEqualTo(String value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLike(String value) {
            addCriterion("resource_id like", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotLike(String value) {
            addCriterion("resource_id not like", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIn(List<String> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotIn(List<String> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdBetween(String value1, String value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotBetween(String value1, String value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andOfferorIsNull() {
            addCriterion("offeror is null");
            return (Criteria) this;
        }

        public Criteria andOfferorIsNotNull() {
            addCriterion("offeror is not null");
            return (Criteria) this;
        }

        public Criteria andOfferorEqualTo(String value) {
            addCriterion("offeror =", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorNotEqualTo(String value) {
            addCriterion("offeror <>", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorGreaterThan(String value) {
            addCriterion("offeror >", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorGreaterThanOrEqualTo(String value) {
            addCriterion("offeror >=", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorLessThan(String value) {
            addCriterion("offeror <", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorLessThanOrEqualTo(String value) {
            addCriterion("offeror <=", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorLike(String value) {
            addCriterion("offeror like", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorNotLike(String value) {
            addCriterion("offeror not like", value, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorIn(List<String> values) {
            addCriterion("offeror in", values, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorNotIn(List<String> values) {
            addCriterion("offeror not in", values, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorBetween(String value1, String value2) {
            addCriterion("offeror between", value1, value2, "offeror");
            return (Criteria) this;
        }

        public Criteria andOfferorNotBetween(String value1, String value2) {
            addCriterion("offeror not between", value1, value2, "offeror");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountIsNull() {
            addCriterion("quotation_amount is null");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountIsNotNull() {
            addCriterion("quotation_amount is not null");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountEqualTo(BigDecimal value) {
            addCriterion("quotation_amount =", value, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountNotEqualTo(BigDecimal value) {
            addCriterion("quotation_amount <>", value, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountGreaterThan(BigDecimal value) {
            addCriterion("quotation_amount >", value, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("quotation_amount >=", value, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountLessThan(BigDecimal value) {
            addCriterion("quotation_amount <", value, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("quotation_amount <=", value, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountIn(List<BigDecimal> values) {
            addCriterion("quotation_amount in", values, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountNotIn(List<BigDecimal> values) {
            addCriterion("quotation_amount not in", values, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quotation_amount between", value1, value2, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("quotation_amount not between", value1, value2, "quotationAmount");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeIsNull() {
            addCriterion("quotation_time is null");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeIsNotNull() {
            addCriterion("quotation_time is not null");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeEqualTo(Date value) {
            addCriterion("quotation_time =", value, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeNotEqualTo(Date value) {
            addCriterion("quotation_time <>", value, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeGreaterThan(Date value) {
            addCriterion("quotation_time >", value, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("quotation_time >=", value, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeLessThan(Date value) {
            addCriterion("quotation_time <", value, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeLessThanOrEqualTo(Date value) {
            addCriterion("quotation_time <=", value, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeIn(List<Date> values) {
            addCriterion("quotation_time in", values, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeNotIn(List<Date> values) {
            addCriterion("quotation_time not in", values, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeBetween(Date value1, Date value2) {
            addCriterion("quotation_time between", value1, value2, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andQuotationTimeNotBetween(Date value1, Date value2) {
            addCriterion("quotation_time not between", value1, value2, "quotationTime");
            return (Criteria) this;
        }

        public Criteria andIdBidIsNull() {
            addCriterion("id_bid is null");
            return (Criteria) this;
        }

        public Criteria andIdBidIsNotNull() {
            addCriterion("id_bid is not null");
            return (Criteria) this;
        }

        public Criteria andIdBidEqualTo(Integer value) {
            addCriterion("id_bid =", value, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidNotEqualTo(Integer value) {
            addCriterion("id_bid <>", value, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidGreaterThan(Integer value) {
            addCriterion("id_bid >", value, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidGreaterThanOrEqualTo(Integer value) {
            addCriterion("id_bid >=", value, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidLessThan(Integer value) {
            addCriterion("id_bid <", value, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidLessThanOrEqualTo(Integer value) {
            addCriterion("id_bid <=", value, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidIn(List<Integer> values) {
            addCriterion("id_bid in", values, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidNotIn(List<Integer> values) {
            addCriterion("id_bid not in", values, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidBetween(Integer value1, Integer value2) {
            addCriterion("id_bid between", value1, value2, "idBid");
            return (Criteria) this;
        }

        public Criteria andIdBidNotBetween(Integer value1, Integer value2) {
            addCriterion("id_bid not between", value1, value2, "idBid");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andYnIsNull() {
            addCriterion("yn is null");
            return (Criteria) this;
        }

        public Criteria andYnIsNotNull() {
            addCriterion("yn is not null");
            return (Criteria) this;
        }

        public Criteria andYnEqualTo(Integer value) {
            addCriterion("yn =", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnNotEqualTo(Integer value) {
            addCriterion("yn <>", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnGreaterThan(Integer value) {
            addCriterion("yn >", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnGreaterThanOrEqualTo(Integer value) {
            addCriterion("yn >=", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnLessThan(Integer value) {
            addCriterion("yn <", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnLessThanOrEqualTo(Integer value) {
            addCriterion("yn <=", value, "yn");
            return (Criteria) this;
        }

        public Criteria andYnIn(List<Integer> values) {
            addCriterion("yn in", values, "yn");
            return (Criteria) this;
        }

        public Criteria andYnNotIn(List<Integer> values) {
            addCriterion("yn not in", values, "yn");
            return (Criteria) this;
        }

        public Criteria andYnBetween(Integer value1, Integer value2) {
            addCriterion("yn between", value1, value2, "yn");
            return (Criteria) this;
        }

        public Criteria andYnNotBetween(Integer value1, Integer value2) {
            addCriterion("yn not between", value1, value2, "yn");
            return (Criteria) this;
        }

        public Criteria andResourceIdLikeInsensitive(String value) {
            addCriterion("upper(resource_id) like", value.toUpperCase(), "resourceId");
            return (Criteria) this;
        }

        public Criteria andOfferorLikeInsensitive(String value) {
            addCriterion("upper(offeror) like", value.toUpperCase(), "offeror");
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