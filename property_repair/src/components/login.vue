<template>
    <div>
      <br>
      <x-header class="header" :left-options="{showBack: false}" >登录</x-header>
      <br>
      <group>
        <x-input title="手机号" name="mobile" v-model="phoneNumber" placeholder="请输入手机号码" is-type="china-mobile" :show-clear="false" :max="11" keyboard="number"></x-input>
        <x-input title="验证码"  v-model="inputCode" placeholder="请输入验证码" class="weui-vcode">
          <x-button slot="right" type="primary" @click.native="toGetCode" placeholder="请输入验证码" mini v-show="!computedTime">获取验证码</x-button>
          <x-button slot="right" type="plain" mini v-show="computedTime">({{computedTime}}s)</x-button>
        </x-input>
      </group>
      <div style="padding:15px;">
        <x-button @click.native='loginVerify' type="primary"> 登录</x-button>
      </div>
    </div>
</template>

<script>import { XHeader, XInput, XButton, Group} from 'vux'
    export default {
        name: "login",
        components: {
          XHeader,
          XInput,
          XButton,
          Group
        },
      data: function () {
        return {
          // 短信验证码发送接口
          messageURL: 'http://dropdbandescape.parkwing.cn/codiv/sms/code',
          // 手机号码
          phoneNumber: null,
          // 倒数计时
          computedTime: 0,
          // 本地存储数据库返回的正确验证码
          // 以及这条验证码的createtime 用于判断十分钟的有效期
          verifyCode: null,
          verifyCodeTime: null,
          // 用户输入的验证码
          inputCode: null
        }
      },
      mounted: function(){
        this.timeVerify()
      },
      methods:{
        async toGetCode(){
          // 倒数计时器
          if (this.phoneNumber!== null && this.phoneNumber.length === 11 ) {
            this.computedTime = 5;
            this.timer = setInterval(() => {
              this.computedTime --;
              if (this.computedTime === 0) {
                clearInterval(this.timer)
              }
            }, 1000)
          }else{
            alert('please input the correct phone number!')
          }
          // 向短信接口发送请求
          var param = {
            'mobile': this.phoneNumber,
            'type': '2'
          }
          this.$axios.post(this.messageURL, param).then(resp => {
            if(resp.status === 200){
              alert('发送成功！')
            }
            else{
              alert('发送失败！')
            }
          })
          // 确定返回了数据后再进行接下来的操作
          // await this.afterGetCode()
        },
        /**
         * 根据登录用户的手机号和姓名
         * 发送请求去数据库获得正确的验证码
         * 用于接下来的匹配
         */
        afterGetCode(){
          var _this = this
          var param = {
            'mobile': this.phoneNumber
          }
          this.$axios.post('/repairStaff/login', param).then(resp => {
            if(resp.status === 200){  // 将请求返回的正确验证码以及验证码的创建时间存到本地
              _this.verifyCode = resp.data.code
              _this.verifyCodeTime = resp.data.createtime
              console.log('print the resp data:', resp.data)
              console.log('print the code:', _this.code)
              console.log('print the resp.data.code:', resp.data.code)
              console.log( 'print the time:', _this.verifyCodeTime)
            }else{
              alert('返回状态错误！')
            }
          })
        },
        /**
         * 验证码的createtime和当前时间进行判断
         * 大于十分钟就判断为无效
         */
        timeVerify(){
          var d = new Date()
          console.log('print the current time1:', d)
          d.setTime(d.getTime() - 60 * 10 * 1000) // 当前时间减去十分钟
          console.log('print the current time2:', d)
          var tenMinAfterCurrent = d.getTime() + 60 * 10 * 1000 // 当前时间加十分钟的时间戳
          var returnCreatetime = this.verifyCodeTime // 验证码的创建时间戳 未修改格式
          // console.log('print the time1:', tenMinAfterCurrent.setTime(tenMinAfterCurrent))
          // console.log('print the time2:', returnCreatetime.setTime(returnCreatetime))
          if(returnCreatetime > tenMinAfterCurrent){
            // alert('超出时间限制！验证码无效！')
            return false
          }else{
            return true
          }
        },
        /**
         * 验证码是否匹配
         */
        codeVerify(){
          console.log('print the this.verifyCode:', this.verifyCode)
          console.log('print the this.inputCode:', this.inputCode)
          if(this.verifyCode === this.inputCode){
            return true
          } else{
            return false
          }
        },
        /**
         * 点击登录 触发检验验证码的正确性
         */
        async loginVerify(){
          await this.afterGetCode()
          if(this.timeVerify() && this.codeVerify()){// 验证码符合要求进行 带手机号码的 页面跳转到work
            console.log('验证码正确！')
            this.$router.push({
              name: 'workOutline',
              params: {
                mobile: this.phoneNumber
              }
            })
          }else if(!this.timeVerify() && this.codeVerify()){
            alert('验证码过期！')
          }else if(this.timeVerify() &&  !this.codeVerify()){
            alert('验证码错误！')
          }else{
            alert('error!')
          }
        }
      }
    }
</script>

<style scoped>
  .header{
    margin-top: -4.8em;
  }
</style>
