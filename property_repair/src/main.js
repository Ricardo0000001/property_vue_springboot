// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import Vuex from 'vuex' // 引入插件
import vuexI18n from 'vuex-i18n'

Vue.config.productionTip = false
Vue.prototype.$axios = axios //全局注册，使用方法为:this.$axios
axios.defaults.baseURL = 'http://localhost:6501'
// axios.defaults.baseURL = 'https://dropdbandescape.parkwing.cn/codiv'


// Vue.use(VueResource);
Vue.use(Vuex);
const store = new Vuex.Store({
  modules: {
    i18n: vuexI18n.store
  }
});
Vue.use(vuexI18n.plugin, store);
const translationsEn = {
  "content": "This is some {type} content"
};

// translations can be kept in separate files for each language
// i.e. resources/i18n/de.json.
// add translations directly to the application
Vue.i18n.add('en', translationsEn);

// set the start locale to use
Vue.i18n.set('en')


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
