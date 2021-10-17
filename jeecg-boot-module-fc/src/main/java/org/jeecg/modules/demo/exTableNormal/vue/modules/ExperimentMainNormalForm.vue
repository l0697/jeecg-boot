<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <!-- 主表单区域 -->
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24" >
            <a-form-model-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createBy">
              <a-input v-model="model.createBy" placeholder="请输入创建人" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createTime">
              <j-date placeholder="请选择创建日期" v-model="model.createTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateBy">
              <a-input v-model="model.updateBy" placeholder="请输入更新人" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateTime">
              <j-date placeholder="请选择更新日期" v-model="model.updateTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24" >
            <a-form-model-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sysOrgCode">
              <a-input v-model="model.sysOrgCode" placeholder="请输入所属部门" disabled></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
      <!-- 子表单区域 -->
    <a-tabs v-model="activeKey" @change="handleChangeTabs">
      <a-tab-pane tab="组织结构参数" :key="refKeys[0]" :forceRender="true">
        <org-struc-param-normal-form ref="orgStrucParamNormalForm" @validateError="validateError" :disabled="formDisabled"></org-struc-param-normal-form>
      </a-tab-pane>
      
      <a-tab-pane tab="合成工艺" :key="refKeys[1]" :forceRender="true">
        <synthetic-process-normal-form ref="syntheticProcessNormalForm" @validateError="validateError" :disabled="formDisabled"></synthetic-process-normal-form>
      </a-tab-pane>
      
      <a-tab-pane tab="性能参数" :key="refKeys[2]" :forceRender="true">
        <performance-param-normal-form ref="performanceParamNormalForm" @validateError="validateError" :disabled="formDisabled"></performance-param-normal-form>
      </a-tab-pane>
      
    </a-tabs>
  </a-spin>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { FormTypes,getRefPromise,VALIDATE_NO_PASSED } from '@/utils/JEditableTableUtil'
  import { JEditableTableModelMixin } from '@/mixins/JEditableTableModelMixin'
  import { validateDuplicateValue } from '@/utils/util'
  import OrgStrucParamNormalForm from './OrgStrucParamNormalForm.vue'
  import SyntheticProcessNormalForm from './SyntheticProcessNormalForm.vue'
  import PerformanceParamNormalForm from './PerformanceParamNormalForm.vue'

  export default {
    name: 'ExperimentMainNormalForm',
    mixins: [JEditableTableModelMixin],
    components: {
      OrgStrucParamNormalForm,
      SyntheticProcessNormalForm,
      PerformanceParamNormalForm,
    },
    data() {
      return {
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
        model:{
        },
        // 新增时子表默认添加几行空数据
        addDefaultRowNum: 1,
        validatorRules: {
        },
        refKeys: ['orgStrucParamNormal', 'syntheticProcessNormal', 'performanceParamNormal', ],
        tableKeys:[],
        activeKey: 'orgStrucParamNormal',
        // 组织结构参数
        orgStrucParamNormalTable: {
          loading: false,
          dataSource: [],
          columns: [
          ]
        },
        // 合成工艺
        syntheticProcessNormalTable: {
          loading: false,
          dataSource: [],
          columns: [
          ]
        },
        // 性能参数
        performanceParamNormalTable: {
          loading: false,
          dataSource: [],
          columns: [
          ]
        },
        url: {
          add: "/exTableNormal/experimentMainNormal/add",
          edit: "/exTableNormal/experimentMainNormal/edit",
          queryById: "/exTableNormal/experimentMainNormal/queryById",
          orgStrucParamNormal: {
            list: '/exTableNormal/experimentMainNormal/queryOrgStrucParamNormalByMainId'
          },
          syntheticProcessNormal: {
            list: '/exTableNormal/experimentMainNormal/querySyntheticProcessNormalByMainId'
          },
          performanceParamNormal: {
            list: '/exTableNormal/experimentMainNormal/queryPerformanceParamNormalByMainId'
          },
        }
      }
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
    },
    methods: {
      addBefore(){
        this.$refs.orgStrucParamNormalForm.clearFormData()
        this.$refs.syntheticProcessNormalForm.clearFormData()
        this.$refs.performanceParamNormalForm.clearFormData()
      },
      getAllTable() {
        return new Promise(resolve => {
          resolve([]);
        })
      },
      /** 调用完edit()方法之后会自动调用此方法 */
      editAfter() {
        this.$nextTick(() => {
          this.$refs.orgStrucParamNormalForm.initFormData(this.url.orgStrucParamNormal.list,this.model.id)
          this.$refs.syntheticProcessNormalForm.initFormData(this.url.syntheticProcessNormal.list,this.model.id)
          this.$refs.performanceParamNormalForm.initFormData(this.url.performanceParamNormal.list,this.model.id)
        })
        // 加载子表数据
        if (this.model.id) {
          let params = { id: this.model.id }
        }
      },
      //校验所有一对一子表表单
      validateSubForm(allValues){
          return new Promise((resolve,reject)=>{
            Promise.all([
                this.$refs.orgStrucParamNormalForm.validate(0),
                this.$refs.syntheticProcessNormalForm.validate(1),
                this.$refs.performanceParamNormalForm.validate(2),
            ]).then(() => {
              resolve(allValues)
            }).catch(e => {
              if (e.error === VALIDATE_NO_PASSED) {
                // 如果有未通过表单验证的子表，就自动跳转到它所在的tab
                this.activeKey = e.index == null ? this.activeKey : this.refKeys[e.index]
              } else {
                console.error(e)
              }
            })
          })
      },
      /** 整理成formData */
      classifyIntoFormData(allValues) {
        let main = Object.assign(this.model, allValues.formValue)
        return {
          ...main, // 展开
          orgStrucParamNormalList: this.$refs.orgStrucParamNormalForm.getFormData(),
          syntheticProcessNormalList: this.$refs.syntheticProcessNormalForm.getFormData(),
          performanceParamNormalList: this.$refs.performanceParamNormalForm.getFormData(),
        }
      },
      validateError(msg){
        this.$message.error(msg)
      },

    }
  }
</script>

<style scoped>
</style>