package com.team.common.constants;

//import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 工单信息枚举
 * @author QianT
 * @date 2022/7/29$
 */
public class WorkConstants {

    public enum Status{
        NEW(0,"新建"),
        WAIT(1,"待执行"),
        EXECUTE(2,"执行中"),
        FINISH(3,"已完成"),
        ARCHIVE(4,"已归档"),
        CANCEL(5,"已取消"),
        ;
        private Integer code;
        private String msg;
        Status(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Status value : Status.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }
    public enum Source{
        HAND_WORK(0,"手工工单"),
        INSPECTION_WORK(1,"运检工单"),
        MODE_WORK(2,"模板工单"),
        SUPERIOR_WORK(3,"上级工单"),;
        private Integer code;
        private String msg;
        Source(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Source value : Source.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }
    public enum Type{
        TEAM_BUILD(0,"班组建设"),
        MEMBER_TRAIN(1,"员工培训"),
        SAFE_EVENT(2,"安全活动"),
        SCENE_PRODUCE(3,"现场生产"),
        CAPITAL_CHECK(4,"基建验收"),
        BASICS_MANAGE(5,"基础管理"),
        SCIENCE_INNOVATE(6,"科技创新"),
        SYNTHESIZE_AFFAIR(7,"综合事务"),
        TEMPORARY_WORK(8,"临时任务"),
        OTHER_WORK(9,"其他"),
        ;
        private Integer code;
        private String msg;
        Type(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Type value : Type.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }

    public enum Nature{
        ATTENDANCE(0,"出勤"),
        BUSINESS_TRIP(1,"出差"),
        TRAIN(2,"培训"),
        ON_LOAN(3,"借调"),
        ABSENCE(4,"缺勤"),
        ;
        private Integer code;
        private String msg;
        Nature(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Nature value : Nature.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }

    public enum Level{
        GENERAL(0,"一般任务"),
        URGENCY(1,"紧急任务"),
        IMPORTANT(2,"重要任务"),
        ;
        private Integer code;
        private String msg;
        Level(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Level value : Level.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }

    public enum Evaluate{
        REACH(0,"达标"),
        NOT_REACH(1,"未达标"),
        MORE_THAN_EXPECTED(2,"超预期"),
        ;
        private Integer code;
        private String msg;
        Evaluate(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Evaluate value : Evaluate.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }

    public enum Leave_Type{
        //0旷工、1事假、2病假、3婚假、4丧假、5产假、6探亲假、7年休假、8护理假、9疗休养
        ABSENT(0,"旷工"),
        PERSONAL_LEAVE(1,"事假"),
        SICK_LEAVE(2,"病假"),
        MARRIAGE(3,"婚假"),
        FUNERAL(4,"丧假"),
        MATERNITY(5,"产假"),
        HOME(6,"探亲假"),
        YEAR(7,"年休假"),
        NURSING(8,"护理假"),
        CONVALESCENT(9,"疗休养"),
        ;
        private Integer code;
        private String msg;
        Leave_Type(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }
        public Integer getCode() {
            return code;
        }
        public void setCode(Integer code) {
            this.code = code;
        }
        public String getMsg() {
            return msg;
        }
        public void setMsg(String msg) {
            this.msg = msg;
        }
        public static String getMsgByCode(Integer code){
            if(null==code){
                return null;
            }
            for (Leave_Type value : Leave_Type.values()) {
                if(value.code.equals(code)){
                    return value.msg;
                }
            }
            return null;
        }
    }

    public static class Delete{
        public static final Integer NORMAL = 0;

        public static final Integer DELETE = 1;
    }
    public static class Record_Type{
        /**
         * 0新建
         */
        public static final Integer NEW = 0;
        /**
         * 1删除
         */
        public static final Integer DELETE = 1;
        /**
         * 2修改
         */
        public static final Integer UPDATE = 2;
        /**
         * 4取消
         */
        public static final Integer CANCEL = 4;
        /**
         * 5发布
         */
        public static final Integer PUSH = 5;
        /**
         * 6人员调整
         */
        public static final Integer CHANGE = 6;
        /**
         * 7评价调整
         */
        public static final Integer EVALUATE = 7;
        /**
         * 8归档
         */
        public static final Integer ARCHIVE = 8;

    }
}
