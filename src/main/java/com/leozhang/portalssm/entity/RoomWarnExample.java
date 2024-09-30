package com.leozhang.portalssm.entity;

import java.util.ArrayList;
import java.util.List;

public class RoomWarnExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RoomWarnExample() {
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

        public Criteria andMaxDiskIsNull() {
            addCriterion("max_disk is null");
            return (Criteria) this;
        }

        public Criteria andMaxDiskIsNotNull() {
            addCriterion("max_disk is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDiskEqualTo(Integer value) {
            addCriterion("max_disk =", value, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskNotEqualTo(Integer value) {
            addCriterion("max_disk <>", value, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskGreaterThan(Integer value) {
            addCriterion("max_disk >", value, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_disk >=", value, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskLessThan(Integer value) {
            addCriterion("max_disk <", value, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskLessThanOrEqualTo(Integer value) {
            addCriterion("max_disk <=", value, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskIn(List<Integer> values) {
            addCriterion("max_disk in", values, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskNotIn(List<Integer> values) {
            addCriterion("max_disk not in", values, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskBetween(Integer value1, Integer value2) {
            addCriterion("max_disk between", value1, value2, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxDiskNotBetween(Integer value1, Integer value2) {
            addCriterion("max_disk not between", value1, value2, "maxDisk");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureIsNull() {
            addCriterion("max_temperature is null");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureIsNotNull() {
            addCriterion("max_temperature is not null");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureEqualTo(Integer value) {
            addCriterion("max_temperature =", value, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureNotEqualTo(Integer value) {
            addCriterion("max_temperature <>", value, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureGreaterThan(Integer value) {
            addCriterion("max_temperature >", value, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_temperature >=", value, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureLessThan(Integer value) {
            addCriterion("max_temperature <", value, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureLessThanOrEqualTo(Integer value) {
            addCriterion("max_temperature <=", value, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureIn(List<Integer> values) {
            addCriterion("max_temperature in", values, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureNotIn(List<Integer> values) {
            addCriterion("max_temperature not in", values, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureBetween(Integer value1, Integer value2) {
            addCriterion("max_temperature between", value1, value2, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxTemperatureNotBetween(Integer value1, Integer value2) {
            addCriterion("max_temperature not between", value1, value2, "maxTemperature");
            return (Criteria) this;
        }

        public Criteria andMaxRequestIsNull() {
            addCriterion("max_request is null");
            return (Criteria) this;
        }

        public Criteria andMaxRequestIsNotNull() {
            addCriterion("max_request is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRequestEqualTo(Integer value) {
            addCriterion("max_request =", value, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestNotEqualTo(Integer value) {
            addCriterion("max_request <>", value, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestGreaterThan(Integer value) {
            addCriterion("max_request >", value, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_request >=", value, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestLessThan(Integer value) {
            addCriterion("max_request <", value, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestLessThanOrEqualTo(Integer value) {
            addCriterion("max_request <=", value, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestIn(List<Integer> values) {
            addCriterion("max_request in", values, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestNotIn(List<Integer> values) {
            addCriterion("max_request not in", values, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestBetween(Integer value1, Integer value2) {
            addCriterion("max_request between", value1, value2, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxRequestNotBetween(Integer value1, Integer value2) {
            addCriterion("max_request not between", value1, value2, "maxRequest");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseIsNull() {
            addCriterion("max_cpu_use is null");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseIsNotNull() {
            addCriterion("max_cpu_use is not null");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseEqualTo(Integer value) {
            addCriterion("max_cpu_use =", value, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseNotEqualTo(Integer value) {
            addCriterion("max_cpu_use <>", value, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseGreaterThan(Integer value) {
            addCriterion("max_cpu_use >", value, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_cpu_use >=", value, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseLessThan(Integer value) {
            addCriterion("max_cpu_use <", value, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseLessThanOrEqualTo(Integer value) {
            addCriterion("max_cpu_use <=", value, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseIn(List<Integer> values) {
            addCriterion("max_cpu_use in", values, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseNotIn(List<Integer> values) {
            addCriterion("max_cpu_use not in", values, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseBetween(Integer value1, Integer value2) {
            addCriterion("max_cpu_use between", value1, value2, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andMaxCpuUseNotBetween(Integer value1, Integer value2) {
            addCriterion("max_cpu_use not between", value1, value2, "maxCpuUse");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNull() {
            addCriterion("room_id is null");
            return (Criteria) this;
        }

        public Criteria andRoomIdIsNotNull() {
            addCriterion("room_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoomIdEqualTo(Long value) {
            addCriterion("room_id =", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotEqualTo(Long value) {
            addCriterion("room_id <>", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThan(Long value) {
            addCriterion("room_id >", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdGreaterThanOrEqualTo(Long value) {
            addCriterion("room_id >=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThan(Long value) {
            addCriterion("room_id <", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdLessThanOrEqualTo(Long value) {
            addCriterion("room_id <=", value, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdIn(List<Long> values) {
            addCriterion("room_id in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotIn(List<Long> values) {
            addCriterion("room_id not in", values, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdBetween(Long value1, Long value2) {
            addCriterion("room_id between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andRoomIdNotBetween(Long value1, Long value2) {
            addCriterion("room_id not between", value1, value2, "roomId");
            return (Criteria) this;
        }

        public Criteria andLowPowerIsNull() {
            addCriterion("low_power is null");
            return (Criteria) this;
        }

        public Criteria andLowPowerIsNotNull() {
            addCriterion("low_power is not null");
            return (Criteria) this;
        }

        public Criteria andLowPowerEqualTo(Integer value) {
            addCriterion("low_power =", value, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerNotEqualTo(Integer value) {
            addCriterion("low_power <>", value, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerGreaterThan(Integer value) {
            addCriterion("low_power >", value, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerGreaterThanOrEqualTo(Integer value) {
            addCriterion("low_power >=", value, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerLessThan(Integer value) {
            addCriterion("low_power <", value, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerLessThanOrEqualTo(Integer value) {
            addCriterion("low_power <=", value, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerIn(List<Integer> values) {
            addCriterion("low_power in", values, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerNotIn(List<Integer> values) {
            addCriterion("low_power not in", values, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerBetween(Integer value1, Integer value2) {
            addCriterion("low_power between", value1, value2, "lowPower");
            return (Criteria) this;
        }

        public Criteria andLowPowerNotBetween(Integer value1, Integer value2) {
            addCriterion("low_power not between", value1, value2, "lowPower");
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