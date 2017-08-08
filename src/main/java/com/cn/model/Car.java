package com.cn.model;

import java.util.Date;

public class Car {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car.Id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car.plate_number
     *
     * @mbggenerated
     */
    private String plateNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car.type
     *
     * @mbggenerated
     */
    private String type;

    private String typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    public Car() {
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car
     *
     * @mbggenerated
     */

    public Car(String id, String plateNumber, String type,String typeId, String status, String remark, Date createTime) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.type = type;
        this.typeId=typeId;

        this.status = status;
        this.remark = remark;
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car.Id
     *
     * @return the value of t_car.Id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car.plate_number
     *
     * @return the value of t_car.plate_number
     *
     * @mbggenerated
     */
    public String getPlateNumber() {
        return plateNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car.type
     *
     * @return the value of t_car.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car.status
     *
     * @return the value of t_car.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car.remark
     *
     * @return the value of t_car.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car.create_time
     *
     * @return the value of t_car.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}