/*
 * Copyright © 2015~2019 深圳前海大道金融服务有限公司 粤ICP备15110793号
 */

package zhjie.entity.tree;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 *
 * @author cuiyuqiang
 * @date 2019/10/16
 */
public class ModelFieldTreeVO  {

    private Integer id;
    private Integer classId;
    private String fieldName;
    private String fieldDisplayName;
    private Integer typeId;
    private Integer fieldDataType;
    private String remark;
    private Integer listData;
    private String listDataGroupKey;
    private String xPath;
    private String parentIds;
    private Integer dataTypeClassId;
    private Long objectId;
    private String objectUid;
    private Integer indexNo;
    private String policyUid;
    private String policyGroupUid;

    /**
     * 所属模型
     */
    private ModelTreeVO belongToModel;
    /**
     * 父字段
     */
    private List<ModelFieldTreeVO> parentModelFields;
    /**
     * 数据类型模型
     */
    private ModelTreeVO dataTypeClassIdVO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDisplayName() {
        return fieldDisplayName;
    }

    public void setFieldDisplayName(String fieldDisplayName) {
        this.fieldDisplayName = fieldDisplayName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getFieldDataType() {
        return fieldDataType;
    }

    public void setFieldDataType(Integer fieldDataType) {
        this.fieldDataType = fieldDataType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getListData() {
        return listData;
    }

    public void setListData(Integer listData) {
        this.listData = listData;
    }

    public String getListDataGroupKey() {
        return listDataGroupKey;
    }

    public void setListDataGroupKey(String listDataGroupKey) {
        this.listDataGroupKey = listDataGroupKey;
    }

    public String getxPath() {
        return xPath;
    }

    public void setxPath(String xPath) {
        this.xPath = xPath;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getDataTypeClassId() {
        return dataTypeClassId;
    }

    public void setDataTypeClassId(Integer dataTypeClassId) {
        this.dataTypeClassId = dataTypeClassId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public String getObjectUid() {
        return objectUid;
    }

    public void setObjectUid(String objectUid) {
        this.objectUid = objectUid;
    }

    public Integer getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Integer indexNo) {
        this.indexNo = indexNo;
    }

    public String getPolicyUid() {
        return policyUid;
    }

    public void setPolicyUid(String policyUid) {
        this.policyUid = policyUid;
    }

    public String getPolicyGroupUid() {
        return policyGroupUid;
    }

    public void setPolicyGroupUid(String policyGroupUid) {
        this.policyGroupUid = policyGroupUid;
    }

    public ModelTreeVO getBelongToModel() {
        return belongToModel;
    }

    public void setBelongToModel(ModelTreeVO belongToModel) {
        this.belongToModel = belongToModel;
    }

    public List<ModelFieldTreeVO> getParentModelFields() {
        return parentModelFields;
    }

    public void setParentModelFields(List<ModelFieldTreeVO> parentModelFields) {
        this.parentModelFields = parentModelFields;
    }

    public ModelTreeVO getDataTypeClassIdVO() {
        return dataTypeClassIdVO;
    }

    public void setDataTypeClassIdVO(ModelTreeVO dataTypeClassIdVO) {
        this.dataTypeClassIdVO = dataTypeClassIdVO;
    }

    public void addParentModelField(ModelFieldTreeVO parentModelField) {
        if (this.parentModelFields == null) {
            this.parentModelFields = new ArrayList<>();
        }
        this.parentModelFields.add(parentModelField);
    }

    /**
     * 获取当前字段对应二级模型
     * @return
     */
    public ModelTreeVO getRootModel() {
        ModelTreeVO root = belongToModel;
        List<ModelTreeVO> parentModels;
        while (root != null && (parentModels = root.getParentModels()) != null) {

            root = parentModels.get(0);
        }
        return root;
    }

    @Override
    public String toString() {
        return "ModelFieldTreeVO{" +
            "id=" + id +
            '}';
    }
}
