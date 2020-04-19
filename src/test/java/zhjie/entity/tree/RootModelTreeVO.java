/*
 * Copyright © 2015~2019 深圳前海大道金融服务有限公司 粤ICP备15110793号
 */

package zhjie.entity.tree;



import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author cuiyuqiang
 * @date 2019/10/16
 */
public class RootModelTreeVO  {
    /**
     * 动态模型-根模型
     */
    private ModelTreeVO rootModelTreeVO;
    /**
     * 模型树
     */
    private Map<Integer, ModelTreeVO> modelTreeVOMap;
    /**
     * 模型字段树
     */
    private Map<Integer, ModelFieldTreeVO> modelFieldTreeVOMap;

    /**
     * 对应枚举值
     */
    private Map<String, List<EnumConvertVO>> enumConvertVOMap;

    /**
     * 模型全名
     */
    private Map<String, ModelTreeVO> modelNameModelMap;

    /**
     * class_id对应字段列表
     */
    private Map<Integer, Map<String, ModelFieldTreeVO>> classIdFieldNameFiledMap;

    /**
     * 索引对应模型
     */
    private Map<Integer, ModelTreeVO> indexModelMap;

    /**
     * 索引对应模型
     */
    private Map<Integer, ModelFieldTreeVO> indexModelFieldMap;

    public ModelTreeVO getRootModelTreeVO() {
        return rootModelTreeVO;
    }

    public void setRootModelTreeVO(ModelTreeVO rootModelTreeVO) {
        this.rootModelTreeVO = rootModelTreeVO;
    }

    public Map<Integer, ModelTreeVO> getModelTreeVOMap() {
        return modelTreeVOMap;
    }

    public void setModelTreeVOMap(Map<Integer, ModelTreeVO> modelTreeVOMap) {
        this.modelTreeVOMap = modelTreeVOMap;
    }

    public Map<Integer, ModelFieldTreeVO> getModelFieldTreeVOMap() {
        return modelFieldTreeVOMap;
    }

    public void setModelFieldTreeVOMap(Map<Integer, ModelFieldTreeVO> modelFieldTreeVOMap) {
        this.modelFieldTreeVOMap = modelFieldTreeVOMap;
    }

    public Map<String, List<EnumConvertVO>> getEnumConvertVOMap() {
        return enumConvertVOMap;
    }

    public void setEnumConvertVOMap(Map<String, List<EnumConvertVO>> enumConvertVOMap) {
        this.enumConvertVOMap = enumConvertVOMap;
    }

    public Map<String, ModelTreeVO> getModelNameModelMap() {
        return modelNameModelMap;
    }

    public void setModelNameModelMap(Map<String, ModelTreeVO> modelNameModelMap) {
        this.modelNameModelMap = modelNameModelMap;
    }

    public Map<Integer, Map<String, ModelFieldTreeVO>> getClassIdFieldNameFiledMap() {
        return classIdFieldNameFiledMap;
    }

    public void setClassIdFieldNameFiledMap(Map<Integer, Map<String, ModelFieldTreeVO>> classIdFieldNameFiledMap) {
        this.classIdFieldNameFiledMap = classIdFieldNameFiledMap;
    }

    public Map<Integer, ModelTreeVO> getIndexModelMap() {
        return indexModelMap;
    }

    public void setIndexModelMap(Map<Integer, ModelTreeVO> indexModelMap) {
        this.indexModelMap = indexModelMap;
    }

    public Map<Integer, ModelFieldTreeVO> getIndexModelFieldMap() {
        return indexModelFieldMap;
    }

    public void setIndexModelFieldMap(Map<Integer, ModelFieldTreeVO> indexModelFieldMap) {
        this.indexModelFieldMap = indexModelFieldMap;
    }

    public String getFullPath(Integer filedId) {
        ModelFieldTreeVO modelFieldTreeVO = modelFieldTreeVOMap.get(filedId);
        StringBuilder sb = new StringBuilder();
        sb.append(modelFieldTreeVO.getFieldName()).append('.');
        ModelTreeVO belongToModel = modelFieldTreeVO.getBelongToModel();
        List<ModelTreeVO> parentModels;
        while (belongToModel != null && (parentModels = belongToModel.getParentModels()) != null) {
            modelFieldTreeVO = indexModelFieldMap.get(belongToModel.getIndexNo());
            sb.append(modelFieldTreeVO.getFieldName()).append('.');
            belongToModel = parentModels.get(0);
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    @Override
    public String toString() {
        return "RootModelTreeVO{}";
    }
}
