<template>
  <j-form-container :disabled="disabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createBy">
              <a-input v-model="model.createBy" placeholder="请输入创建人"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createTime">
              <a-input v-model="model.createTime" placeholder="请输入创建日期"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateBy">
              <a-input v-model="model.updateBy" placeholder="请输入更新人"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateTime">
              <a-input v-model="model.updateTime" placeholder="请输入更新日期"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sysOrgCode">
              <a-input v-model="model.sysOrgCode" placeholder="请输入所属部门"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="形貌" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="appearance">
              <j-multi-select-tag type="list_multi" v-model="model.appearance" dictCode="appearance" placeholder="请选择形貌"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="粒度分布" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="particleSizeDis">
              <a-input v-model="model.particleSizeDis" placeholder="请输入粒度分布"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="晶型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="crystalForm">
              <j-dict-select-tag type="list" v-model="model.crystalForm" dictCode="crystal_form" placeholder="请选择晶型"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="实验号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mainId">
              <a-input v-model="model.mainId" placeholder="请输入实验号"></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
  </j-form-container>
</template>
<script>
  import { getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'
 import { VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
  export default {
    name: 'OrgStrucParamNormalForm',
    components: {
    },
    props:{
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
        },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 6 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        labelCol2: {
          xs: { span: 24 },
          sm: { span: 3 },
        },
        wrapperCol2: {
          xs: { span: 24 },
          sm: { span: 20 },
        },
        validatorRules: {
           mainId: [
              { required: true, message: '请输入实验号!'},
           ],
        },
        confirmLoading: false,
      }
    },
     created () {
      //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods:{
      initFormData(url,id){
        this.clearFormData()
        if(!id){
          this.edit(this.modelDefault)
        }else{
          getAction(url,{id:id}).then(res=>{
            if(res.success){
              let records = res.result
              if(records && records.length>0){
                this.edit(records[0])
              }
            }
          })
        }
      },
      edit(record){
        this.model = Object.assign({}, record)
        console.log("OrgStrucParamNormalForm-edit",this.model);
      },
      getFormData(){
        let formdata_arr = []
        this.$refs.form.validate(valid => {
          if (valid) {
            let isNullObj = true
            Object.keys(this.model).forEach(key=>{
              if(this.model[key]){
                isNullObj = false
              }
            })
            if(!isNullObj){
              formdata_arr.push(this.model)
            }
          }else{
            this.$emit("validateError","组织结构参数表单校验未通过");
          }
        })
        return formdata_arr;
      },
      validate(index){
          return new Promise((resolve, reject) => {
            // 验证主表表单
           this.$refs.form.validate(valid => {
              !valid ? reject({ error: VALIDATE_NO_PASSED ,index}) : resolve()
            })
          }).then(values => {
            return Promise.resolve()
          }).catch(error => {
            return Promise.reject(error)
          })
        },
      clearFormData(){
        this.$refs.form.clearValidate()
      },
    }
  }
</script>
