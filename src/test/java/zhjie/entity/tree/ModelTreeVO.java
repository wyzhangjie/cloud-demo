/*
 * Copyright © 2015~2019 深圳前海大道金融服务有限公司 粤ICP备15110793号
 */

package zhjie.entity.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 *
 * @author cuiyuqiang
 * @date 2019/10/16
 */
public class ModelTreeVO  {
    private Integer id;
    private String classFullName;
    private String className;
    private String classDisplayName;
    private String remark;
    private String parentIds;
    private Integer classLevel;
    private Long objectId;
    private String objectUid;
    private Integer indexNo;
    private String policyUid;
    private String policyGroupUid;

    /**
     * 父模型
     */
    private List<ModelTreeVO> parentModels;
    /**
     * 字段
     */
    private List<ModelFieldTreeVO> modelFieldVOList;

    /**
     * 子模型
     */
    private List<ModelTreeVO> childModels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassFullName() {
        return classFullName;
    }

    public void setClassFullName(String classFullName) {
        this.classFullName = classFullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDisplayName() {
        return classDisplayName;
    }

    public void setClassDisplayName(String classDisplayName) {
        this.classDisplayName = classDisplayName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(Integer classLevel) {
        this.classLevel = classLevel;
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

    public List<ModelTreeVO> getParentModels() {
        return parentModels;
    }

    public void setParentModels(List<ModelTreeVO> parentModels) {
        this.parentModels = parentModels;
    }

    public List<ModelFieldTreeVO> getModelFieldVOList() {
        return modelFieldVOList;
    }

    public void setModelFieldVOList(List<ModelFieldTreeVO> modelFieldVOList) {
        this.modelFieldVOList = modelFieldVOList;
    }

    public List<ModelTreeVO> getChildModels() {
        return childModels;
    }

    public void setChildModels(List<ModelTreeVO> childModels) {
        this.childModels = childModels;
    }

    public void addParentModel(ModelTreeVO modelTreeVO) {
        if (this.parentModels == null) {
            this.parentModels = new ArrayList<>();
        }
        this.parentModels.add(modelTreeVO);
    }

    public void addModelFieldTreeVO(ModelFieldTreeVO modelFieldVO) {
        if (this.modelFieldVOList == null) {
            this.modelFieldVOList = new ArrayList<>();
        }
        this.modelFieldVOList.add(modelFieldVO);
    }

    public void addChildModel(ModelTreeVO modelTreeVO) {
        if (this.childModels == null) {
            this.childModels = new ArrayList<>();
        }
        this.childModels.add(modelTreeVO);
    }

    public ModelTreeVO getParentModel() {
        ModelTreeVO root = this;
        List<ModelTreeVO> parentModels = this.getParentModels();
        if (parentModels != null && !parentModels.isEmpty()) {
            root = parentModels.get(0);
        }
        return root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ModelTreeVO that = (ModelTreeVO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "ModelTreeVO{" +
            "id=" + id +
            '}';
    }
}
