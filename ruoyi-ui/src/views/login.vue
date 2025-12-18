<template>
  <div class="login">
    <el-form ref="loginRef" :model="loginForm" :rules="loginRules" class="login-form">
      <h3 class="title">{{ title }}</h3>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          type="text"
          size="large"
          auto-complete="off"
          placeholder="账号"
        >
          <template #prefix><svg-icon icon-class="user" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          size="large"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter="handleLogin"
        >
          <template #prefix><svg-icon icon-class="password" class="el-input__icon input-icon" /></template>
        </el-input>
      </el-form-item>
      <el-form-item prop="code" v-if="captchaEnabled">
        <el-input
          v-model="loginForm.code"
          size="large"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter="handleLogin"
        >
          <template #prefix><svg-icon icon-class="validCode" class="el-input__icon input-icon" /></template>
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode" class="login-code-img"/>
        </div>
      </el-form-item>
      <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button
          :loading="loading"
          size="large"
          type="primary"
          style="width:100%;"
          @click.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
        <div style="float: right;" v-if="register">
          <router-link class="link-type" :to="'/register'">立即注册</router-link>
        </div>
      </el-form-item>
    </el-form>
    <!--  底部  -->
    <div class="el-login-footer">
      <span></span>
    </div>
  </div>
</template>

<script setup>
import { getCodeImg } from "@/api/login"
import Cookies from "js-cookie"
import { encrypt, decrypt } from "@/utils/jsencrypt"
import useUserStore from '@/store/modules/user'

const title = import.meta.env.VITE_APP_TITLE
const userStore = useUserStore()
const route = useRoute()
const router = useRouter()
const { proxy } = getCurrentInstance()

const loginForm = ref({
  username: "admin",
  password: "admin123",
  rememberMe: false,
  code: "",
  uuid: ""
})

const loginRules = {
  username: [{ required: true, trigger: "blur", message: "请输入您的账号" }],
  password: [{ required: true, trigger: "blur", message: "请输入您的密码" }],
  code: [{ required: true, trigger: "change", message: "请输入验证码" }]
}

const codeUrl = ref("")
const loading = ref(false)
// 验证码开关
const captchaEnabled = ref(true)
// 注册开关
const register = ref(false)
const redirect = ref(undefined)

watch(route, (newRoute) => {
    redirect.value = newRoute.query && newRoute.query.redirect
}, { immediate: true })

function handleLogin() {
  proxy.$refs.loginRef.validate(valid => {
    if (valid) {
      loading.value = true
      // 勾选了需要记住密码设置在 cookie 中设置记住用户名和密码
      if (loginForm.value.rememberMe) {
        Cookies.set("username", loginForm.value.username, { expires: 30 })
        Cookies.set("password", encrypt(loginForm.value.password), { expires: 30 })
        Cookies.set("rememberMe", loginForm.value.rememberMe, { expires: 30 })
      } else {
        // 否则移除
        Cookies.remove("username")
        Cookies.remove("password")
        Cookies.remove("rememberMe")
      }
      // 调用action的登录方法
      userStore.login(loginForm.value).then(() => {
        const query = route.query
        const otherQueryParams = Object.keys(query).reduce((acc, cur) => {
          if (cur !== "redirect") {
            acc[cur] = query[cur]
          }
          return acc
        }, {})
        router.push({ path: redirect.value || "/", query: otherQueryParams })
      }).catch(() => {
        loading.value = false
        // 重新获取验证码
        if (captchaEnabled.value) {
          getCode()
        }
      })
    }
  })
}

function getCode() {
  getCodeImg().then(res => {
    captchaEnabled.value = res.captchaEnabled === undefined ? true : res.captchaEnabled
    if (captchaEnabled.value) {
      codeUrl.value = "data:image/gif;base64," + res.img
      loginForm.value.uuid = res.uuid
    }
  })
}

function getCookie() {
  const username = Cookies.get("username")
  const password = Cookies.get("password")
  const rememberMe = Cookies.get("rememberMe")
  loginForm.value = {
    username: username === undefined ? loginForm.value.username : username,
    password: password === undefined ? loginForm.value.password : decrypt(password),
    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
  }
}

getCode()
getCookie()
</script>

<style lang="scss" scoped>
/* ===============================
   登录页整体背景
   =============================== */
.login {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;

  background: url("../assets/images/login-background.png") no-repeat center center;
  background-size: cover;
  padding: 20px;
}

/* ===============================
   登录卡片（毛玻璃 + 米白）
   =============================== */
.login-form {
  width: 420px;
  max-width: calc(100vw - 40px);
  padding: 38px 30px 28px;
  border-radius: 16px;

  background: rgba(255, 255, 255, 0.82);
  backdrop-filter: blur(10px);
  -webkit-backdrop-filter: blur(10px);

  box-shadow: 0 8px 30px rgba(0, 0, 0, 0.12);
  border: 1px solid rgba(255, 255, 255, 0.35);
  z-index: 1;

  /* 输入框高度统一 */
  :deep(.el-input) {
    height: 44px;

    input {
      height: 44px;
      font-size: 15px;
    }
  }
}

/* ===============================
   标题
   =============================== */
.title {
  margin: 0 auto 26px;
  text-align: center;
  font-size: 30px;
  font-weight: 800;
  letter-spacing: 1px;
  color: #2A5E23; /* 深绿 */
}

/* ===============================
   输入框前缀图标
   =============================== */
.input-icon {
  width: 16px;
  height: 16px;
  opacity: 0.75;
}

/* ===============================
   Element 输入框风格优化
   =============================== */
.login-form :deep(.el-input__wrapper) {
  border-radius: 10px;
  background: rgba(255, 255, 255, 0.9);
  border: 1px solid #E5E7EB;
  box-shadow: none;
  transition: all 0.25s ease;
}

.login-form :deep(.el-input__wrapper.is-focus) {
  border-color: rgba(42, 94, 35, 0.55);
  box-shadow: 0 0 0 3px rgba(42, 94, 35, 0.15);
}

/* ===============================
   验证码区域
   =============================== */
.login-code {
  width: 33%;
  height: 44px;
  float: right;
  display: flex;
  align-items: center;
  justify-content: flex-end;

  img {
    cursor: pointer;
    height: 44px;
    border-radius: 10px;
    padding: 0 10px;
    background: rgba(255, 255, 255, 0.9);
    border: 1px solid #E5E7EB;
  }
}

.login-code-img {
  height: 44px;
  padding-left: 10px;
}

/* ===============================
   记住密码
   =============================== */
.login-form :deep(.el-checkbox) {
  margin: 8px 0 20px 0 !important;
  --el-checkbox-checked-text-color: #2A5E23;
}

/* ===============================
   登录按钮（深绿）
   =============================== */
.login-form :deep(.el-button--primary) {
  height: 46px;
  border-radius: 10px;
  background: #2A5E23 !important;
  border-color: #2A5E23 !important;

  font-size: 16px;
  font-weight: 800;
  letter-spacing: 2px;
  transition: all 0.25s ease;
}

.login-form :deep(.el-button--primary:hover) {
  background: #1E4A1A !important;
  border-color: #1E4A1A !important;
  box-shadow: 0 10px 22px rgba(42, 94, 35, 0.2);
  transform: translateY(-1px);
}

.login-form :deep(.el-button--primary:active) {
  transform: translateY(0);
  box-shadow: none;
}

/* ===============================
   注册链接
   =============================== */
.login-form :deep(.link-type) {
  color: #2A5E23;
  font-weight: 600;
  text-decoration: none;
}

.login-form :deep(.link-type:hover) {
  text-decoration: underline;
}

/* ===============================
   底部（保持低调）
   =============================== */
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  font-size: 12px;
  color: rgba(55, 65, 81, 0.6);
  letter-spacing: 1px;
}

/* ===============================
   响应式
   =============================== */
@media (max-width: 768px) {
  .login-form {
    padding: 30px 20px 22px;
    border-radius: 14px;
  }

  .title {
    font-size: 26px;
  }
}
</style>
