<template>
    <div>
      <br>
      <x-header class="header" :left-options="{showBack: false}" >工作日程</x-header>
      <br>
      <group>
        <datetime
          v-model="time"
          :title="$t('请选择时间')"
          @on-confirm="onConfirmTime">
        </datetime>
      </group>
      <br>
      <div v-for="(data , i) in respData" :key="i" style="padding:0 5px;" @click="seeDetail(respData[i].id)" >
        <x-table :cell-bordered="false" full-bordered>
        <tbody>
        <tr>
          <td width="25%" style="font-weight: 600">预约时间</td>
          <td width="25%">{{data.apptime | formatDate(data.apptime)}}</td>
          <td width="25%" style="font-weight: 600">维修人员</td>
          <td width="25%">{{data.staffname}}</td>
        </tr>
        <tr>
          <td>企业名称</td>
          <td>{{data.name.substr(0, 3)}}</td>
          <td>所属园区</td>
          <td>{{data.park}}</td>
        </tr>
        </tbody>
          <divider></divider>
      </x-table>
      </div>
    </div>
</template>

<script>import { XHeader, Group, Datetime, XTable, Divider } from 'vux'
    export default {
      name: "workOutline",
      components: {
        XHeader,
        Group,
        Datetime,
        XTable,
        Divider
      },
      data: function(){
        return {
          time: null,
          // 返回的数据
          respData: []
        }
      },
      mounted: function(){
        this.loadData()
      },
      /**
       * 过滤时间戳为正常显示的时间
       */
      filters:{
        formatDate: function (value) {
          let date = new Date(value);
          let y = date.getFullYear();
          let MM = date.getMonth() + 1;
          MM = MM < 10 ? ('0' + MM) : MM;
          let d = date.getDate();
          d = d < 10 ? ('0' + d) : d;
          let h = date.getHours();
          h = h < 10 ? ('0' + h) : h;
          let m = date.getMinutes();
          m = m < 10 ? ('0' + m) : m;
          let s = date.getSeconds();
          s = s < 10 ? ('0' + s) : s;
          return y + '-' + MM + '-' + d ;
        }
      },
      methods:{
        /**
         * 拿着login传来的手机号
         * 发送请求去获得这个员工的工作
         */
        loadData(){
          var _this = this
          var param = {
            'time': this.time,
            'mobile': this.$route.params.mobile
          }
          console.log('接受的参数：', param)
          this.$axios.post('/repairStaff/workOutline', param).then(resp =>{
            if(resp.status === 200){// 返回数据是一个列表 因为维修工可能有多条工作条目
              _this.respData = resp.data
              for(let i = 0; i < resp.data.length; i++){
                if(resp.data[i].apptime === undefined){
                  resp.data[i].apptime = null
                }
                // var date = new Date(resp.data[i].apptime)
                // console.log('print the date:', date)
                // console.log('print the time:', resp.data[i].apptime)
              }
              // resp.data.time = this.timestampToTime(resp.data.time)
              console.log('print the return data:', resp.data)
            }
          })
        },
        /**
         * 时间选择确定触发
         */
        onConfirmTime(){

        },
        /**
         * 查看工作详情
         * 拿着id进行页面跳转去工作详情页面
         */
        seeDetail(id){
          this.$router.push({
            name: 'work',
            params:{
              id: id
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
