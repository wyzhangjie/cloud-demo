package zhjie.entity.tree;


import java.util.Date;

/**
 * @author hongzhipeng
 */
public class EnumConvertVO {

    private static final long serialVersionUID = -1491379107202235468L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 枚举码
     */
    private String enumCode;

    /**
     * 枚举值
     */
    private String enumValue;

    /**
     * 所属类型
     */
    private String type;

    /**
     * 描述信息
     */
    private String remark;

    /**
     * 字典状态(0关闭，1开启)
     */
    private Integer state;


    private String enumDescribe;

    private String userId;

    private String userName;

    private Date createTime;

    private Date updateTime;


    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取枚举码
     *
     * @return enum_code - 枚举码
     */
    public String getEnumCode() {
        return enumCode;
    }

    /**
     * 设置枚举码
     *
     * @param enumCode 枚举码
     */
    public void setEnumCode(String enumCode) {
        this.enumCode = enumCode;
    }

    /**
     * 获取枚举值
     *
     * @return enum_value - 枚举值
     */
    public String getEnumValue() {
        return enumValue;
    }

    /**
     * 设置枚举值
     *
     * @param enumValue 枚举值
     */
    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

    /**
     * 获取所属类型
     *
     * @return type - 所属类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置所属类型
     *
     * @param type 所属类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取描述信息
     *
     * @return remark - 描述信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述信息
     *
     * @param remark 描述信息
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取字典状态(0关闭，1开启)
     *
     * @return state - 字典状态(0关闭，1开启)
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置字典状态(0关闭，1开启)
     *
     * @param state 字典状态(0关闭，1开启)
     */
    public void setState(Integer state) {
        this.state = state;
    }

    public String getEnumDescribe() {
        return enumDescribe;
    }

    public void setEnumDescribe(String enumDescribe) {
        this.enumDescribe = enumDescribe;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String toString() {
		return "EnumConvertVO [id=" + id + ", enumCode=" + enumCode + ", enumValue=" + enumValue + ", type=" + type
				+ ", remark=" + remark + ", state=" + state + ", enumDescribe=" + enumDescribe + ", userId=" + userId
				+ ", userName=" + userName + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
    
}
