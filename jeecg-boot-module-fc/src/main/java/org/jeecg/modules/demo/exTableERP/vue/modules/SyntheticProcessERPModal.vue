<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form-model ref="form" :model="model" :rules="validatorRules">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createBy">
              <a-input v-model="model.createBy"placeholder="请输入创建人" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createTime">
              <j-date placeholder="请选择创建日期" v-model="model.createTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateBy">
              <a-input v-model="model.updateBy"placeholder="请输入更新人" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateTime">
              <j-date placeholder="请选择更新日期" v-model="model.updateTime" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%" disabled/>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sysOrgCode">
              <a-input v-model="model.sysOrgCode"placeholder="请输入所属部门" disabled></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="反应溶剂及其体积" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reactantVolume">
              <a-input v-model="model.reactantVolume"placeholder="请输入反应溶剂及其体积" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="还原剂及其浓度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="redagentCon">
              <a-input v-model="model.redagentCon"placeholder="请输入还原剂及其浓度" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="前驱体及其浓度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="precursorCon">
              <a-input v-model="model.precursorCon"placeholder="请输入前驱体及其浓度" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="保护剂及其浓度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="proagentCon">
              <a-input v-model="model.proagentCon"placeholder="请输入保护剂及其浓度" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="助剂及其浓度" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="addContra">
              <a-input v-model="model.addContra"placeholder="请输入助剂及其浓度" ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="反应温度（℃）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reflexTem">
              <a-input-number v-model="model.reflexTem"placeholder="请输入反应温度（℃）" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="反应时间（分钟）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reflexTime">
              <a-input-number v-model="model.reflexTime"placeholder="请输入反应时间（分钟）" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="搅拌条件（转/分钟）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="stirMethod">
              <a-input-number v-model="model.stirMethod"placeholder="请输入搅拌条件（转/分钟）" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="反应溶液滴加速度（升/分钟）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reactantAddSpeed">
              <a-input-number v-model="model.reactantAddSpeed"placeholder="请输入反应溶液滴加速度（升/分钟）" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="反应溶液 pH 值" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="reactantPh">
              <a-input-number v-model="model.reactantPh"placeholder="请输入反应溶液 pH 值" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="实验号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="mainId">
              <a-input v-model="model.mainId"placeholder="请输入实验号" disabled></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: "SyntheticProcessERPModal",
    components: {
    },
    props:{
      mainId:{
        type:String,
        required:false,
        default:''
      }
    },
    data () {
      return {
        title:"操作",
        width:800,
        visible: false,
        model:{
        },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        validatorRules: {
           mainId: [
              { required: true, message: '请输入实验号!'},
           ],
        },
        url: {
          add: "/exTableERP/experimentMainERP/addSyntheticProcessERP",
          edit: "/exTableERP/experimentMainERP/editSyntheticProcessERP",
        }

      }
    },
    created () {
    //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.$refs.form.clearValidate();
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            this.model['mainId'] = this.mainId
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }else{
             return false
          }
        })
      },
      handleCancel () {
        this.close()
      },


    }
  }
</script>
