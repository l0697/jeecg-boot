<template>
  <a-card :bordered="false" :class="'cust-erp-sub-tab'">
    <!-- 操作按钮区域 -->
    <div class="table-operator" v-if="mainId">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('合成工艺')">导出</a-button>
      <a-upload
        name="file"
        :showUploadList="false"
        :multiple="false"
        :headers="tokenHeader"
        :action="importExcelUrl"
        @change="handleImportExcel">
          <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :scroll="{x:true}"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>
          <a-divider type="vertical" />
          <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
            <a>删除</a>
          </a-popconfirm>
        </span>

      </a-table>
    </div>

    <syntheticProcessERP-modal ref="modalForm" @ok="modalFormOk" :mainId="mainId"></syntheticProcessERP-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import SyntheticProcessERPModal from './modules/SyntheticProcessERPModal'

  export default {
    name: "SyntheticProcessERPList",
    mixins:[JeecgListMixin],
    components: { SyntheticProcessERPModal },
    props:{
      mainId:{
        type:String,
        default:'',
        required:false
      }
    },
    watch:{
      mainId:{
        immediate: true,
        handler(val) {
          if(!this.mainId){
            this.clearList()
          }else{
            this.queryParam['mainId'] = val
            this.loadData(1);
          }
        }
      }
    },
    data () {
      return {
        description: '实验数据主表管理页面',
        disableMixinCreated:true,
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'创建人',
            align:"center",
            sorter: true,
            dataIndex: 'createBy'
          },
          {
            title:'创建日期',
            align:"center",
            sorter: true,
            dataIndex: 'createTime'
          },
          {
            title:'更新人',
            align:"center",
            sorter: true,
            dataIndex: 'updateBy'
          },
          {
            title:'更新日期',
            align:"center",
            sorter: true,
            dataIndex: 'updateTime'
          },
          {
            title:'所属部门',
            align:"center",
            sorter: true,
            dataIndex: 'sysOrgCode'
          },
          {
            title:'反应溶剂及其体积',
            align:"center",
            sorter: true,
            dataIndex: 'reactantVolume'
          },
          {
            title:'还原剂及其浓度',
            align:"center",
            sorter: true,
            dataIndex: 'redagentCon'
          },
          {
            title:'前驱体及其浓度',
            align:"center",
            sorter: true,
            dataIndex: 'precursorCon'
          },
          {
            title:'保护剂及其浓度',
            align:"center",
            sorter: true,
            dataIndex: 'proagentCon'
          },
          {
            title:'助剂及其浓度',
            align:"center",
            sorter: true,
            dataIndex: 'addContra'
          },
          {
            title:'反应温度（℃）',
            align:"center",
            sorter: true,
            dataIndex: 'reflexTem'
          },
          {
            title:'反应时间（分钟）',
            align:"center",
            sorter: true,
            dataIndex: 'reflexTime'
          },
          {
            title:'搅拌条件（转/分钟）',
            align:"center",
            sorter: true,
            dataIndex: 'stirMethod'
          },
          {
            title:'反应溶液滴加速度（升/分钟）',
            align:"center",
            sorter: true,
            dataIndex: 'reactantAddSpeed'
          },
          {
            title:'反应溶液 pH 值',
            align:"center",
            sorter: true,
            dataIndex: 'reactantPh'
          },
          {
            title:'实验号',
            align:"center",
            sorter: true,
            dataIndex: 'mainId'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' },
          }
        ],
        url: {
          list: "/exTableERP/experimentMainERP/listSyntheticProcessERPByMainId",
          delete: "/exTableERP/experimentMainERP/deleteSyntheticProcessERP",
          deleteBatch: "/exTableERP/experimentMainERP/deleteBatchSyntheticProcessERP",
          exportXlsUrl: "/exTableERP/experimentMainERP/exportSyntheticProcessERP",
          importUrl: "/exTableERP/experimentMainERP/importSyntheticProcessERP",
        },
        dictOptions:{
        },
        superFieldList:[],
      }
    },
    created() {
      this.getSuperFieldList();
    },
    computed: {
      importExcelUrl(){
        return `${window._CONFIG['domianURL']}/${this.url.importUrl}/${this.mainId}`;
      }
    },
    methods: {
      clearList(){
        this.dataSource=[]
        this.selectedRowKeys=[]
        this.ipagination.current = 1
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'createBy',text:'创建人',dictCode:''})
        fieldList.push({type:'datetime',value:'createTime',text:'创建日期'})
        fieldList.push({type:'string',value:'updateBy',text:'更新人',dictCode:''})
        fieldList.push({type:'datetime',value:'updateTime',text:'更新日期'})
        fieldList.push({type:'string',value:'sysOrgCode',text:'所属部门',dictCode:''})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>
