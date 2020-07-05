<template>
  <div>
    <br>
    <x-header class="header" :left-options="{showBack: false}" >工作详情</x-header>
    <br>
    <x-table>
      <tbody>
      <tr>
        <td width="50%">类型</td>
        <td></td>
      </tr>
      <tr>
        <td>预约时间</td>
        <td>{{respData.apptime}}</td>
      </tr>
      <tr>
        <td>维修人员</td>
        <td>{{respData.staffname}}</td>
      </tr>
      <tr>
        <td>企业名称</td>
        <td></td>
      </tr>
      <tr>
        <td>联系方式</td>
        <td></td>
      </tr>
      <tr>
        <td>所属园区</td>
        <td></td>
      </tr>
      <tr>
        <td>维修房屋</td>
        <td></td>
      </tr>
      <tr>
        <td>维修内容</td>
        <td></td>
      </tr>
      </tbody>
    </x-table>
  </div>
</template>

<script>import { XHeader, XTable} from 'vux'
    export default {
      name: "work",
      components: {
        XHeader,
        XTable
      },
      data: function(){
        return {
          respData: null
        }
      },
      /**
       * 初始化加载数据
       */
      mounted: function () {
        this.loadData()
      },
      methods: {
        /**
         * 拿着维修人员数据条目的id去请求他的工作详情
         */
        loadData(){
          var _this = this
          var param = {
            'id': this.$route.params.id
          }
          this.$axios.post('/repairStaff/workDetail', param).then(resp =>{
            if(resp.status === 200){
              _this.respData = resp.data
              console.log('print the return data:', resp.data)
            }
          })
        }
      }
}
</script>

<style scoped>
  .header{
    margin-top: -4.8em;
  }
</style>
