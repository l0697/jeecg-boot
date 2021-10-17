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
              <j-date placeholder="请选择创建日期" v-model="model.createTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateBy">
              <a-input v-model="model.updateBy" placeholder="请输入更新人"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateTime">
              <j-date placeholder="请选择更新日期" v-model="model.updateTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sysOrgCode">
              <a-input v-model="model.sysOrgCode" placeholder="请输入所属部门"></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="松装密度（g/mL）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bulkDensity">
              <a-input-number v-model="model.bulkDensity" placeholder="请输入松装密度（g/mL）" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="振实密度（g/mL）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tapDensity">
              <a-input-number v-model="model.tapDensity" placeholder="请输入振实密度（g/mL）" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="比表面积(m2/g)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specSurfaceArea">
              <a-input-number v-model="model.specSurfaceArea" placeholder="请输入比表面积(m2/g)" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="烧损（110℃）(%)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="burnLoss110">
              <a-input-number v-model="model.burnLoss110" placeholder="请输入烧损（110℃）(%)" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="烧损（538℃）(%)" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="burnLoss538">
              <a-input-number v-model="model.burnLoss538" placeholder="请输入烧损（538℃）(%)" style="width: 100%"/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="纯度（%）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="purity">
              <a-input-number v-model="model.purity" placeholder="请输入纯度（%）" style="width: 100%"/>
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
    name: 'PerformanceParamNormalForm',
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
        console.log("PerformanceParamNormalForm-edit",this.model);
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
            this.$emit("validateError","性能参数表单校验未通过");
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
