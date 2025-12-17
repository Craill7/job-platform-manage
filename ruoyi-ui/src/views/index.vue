<template>
  <div class="app-container home">
    <!-- 顶部 Hero 区 -->
    <div class="home-hero">
      <div class="hero-left">
        <div class="hero-badge">中级实训 · 校企合作</div>
        <h1 class="hero-title">软件工程学院招聘与实践平台</h1>
        <p class="hero-subtitle">
          面向学院、学生与企业，提供统一的招聘发布与实训活动管理，帮助学生获取优质实践机会，也为企业发现并选拔优秀人才搭建高效渠道。
        </p>
        <p class="hero-subtitle">
          通过集中管理招聘信息与投递数据，促进
          <b>学院</b>、<b>学生</b> 与 <b>企业</b> 的紧密联动。
        </p>

        <div class="hero-tags">
          <el-tag effect="dark" type="info">学院发布招聘</el-tag>
          <el-tag effect="dark" type="info">学生在线投递</el-tag>
          <el-tag effect="dark" type="info">企业对接人才</el-tag>
        </div>
        <div class="hero-actions">
          <el-button type="primary" plain @click="goJobAudit">
            待审核岗位
            <span class="audit-count">{{ pendingJobCount }}</span>
          </el-button>

          <el-button type="primary" plain @click="goAccountAudit">
            待审核账号
            <span class="audit-count">{{ pendingAccountCount }}</span>
          </el-button>

          <el-button type="primary" plain @click="goJobPublish">
            快速发布招聘
          </el-button>
        </div>

        <div class="hero-version">
          当前版本：<span>v{{ version }}</span>
        </div>
      </div>

      <div class="hero-right">
        <div class="hero-graphic">
          <div class="bubble bubble-1"></div>
          <div class="bubble bubble-2"></div>
          <div class="bubble bubble-3"></div>
          <div class="hero-graphic-center">
            <div class="circle-text">招聘</div>
            <div class="circle-text">实训</div>
            <div class="circle-text">管理</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 数据概览卡片（3 个） -->
    <el-row :gutter="16" class="home-stat-row">
      <el-col :xs="24" :sm="8" v-for="item in statCards" :key="item.label">
        <div class="stat-card">
          <div class="stat-label">{{ item.label }}</div>
          <div class="stat-value">{{ item.value }}</div>
          <div class="stat-desc">{{ item.desc }}</div>
        </div>
      </el-col>
    </el-row>

    <!-- 下方：左侧时间线 + 右侧功能说明 -->
    <el-row :gutter="16" class="home-bottom-row">
      <!-- 左：招聘 / 活动时间线 -->
      <el-col :xs="24" :md="14">
        <el-card shadow="never" class="home-card">
          <template #header>
            <div class="card-header">
              <span>活动动态</span>
            </div>
          </template>
          <el-timeline>
            <el-timeline-item
              v-for="(item, index) in timeline"
              :key="index"
              :timestamp="item.time"
              :type="item.type"
              placement="top"
            >
              <p class="timeline-title">{{ item.title }}</p>
              <p class="timeline-desc">{{ item.desc }}</p>
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>

      <!-- 右：角色功能一览 -->
      <el-col :xs="24" :md="10">
        <!-- 角色功能一览 -->
        <el-card shadow="never" class="home-card">
          <template #header>
            <div class="card-header">
              <span>角色功能一览</span>
            </div>
          </template>
          <ul class="role-list">
            <li v-for="(role, idx) in roles" :key="idx" class="role-item">
              <div class="role-title">
                <span class="role-dot" :class="'role-dot-' + role.key"></span>
                {{ role.name }}
              </div>
              <ul class="role-desc-list">
                <li v-for="(line, i) in role.desc" :key="i">{{ line }}</li>
              </ul>
            </li>
          </ul>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import request from '@/utils/request'

const router = useRouter()
const version = ref('1.0.0')

/** 待审核数量 */
const pendingJobCount = ref(0)
const pendingAccountCount = ref(0)

/** 最新招聘和活动数据 */
const timeline = ref([])

/**
 * 获取接口数据
 */
 const fetchOverviewData = async () => {
  try {
    const res = await request({
      url: '/system/home/overview', // 接口地址
      method: 'get'
    })
    const data = res.data || {}
    
    // 更新待审核数量
    pendingJobCount.value = data.currentRecruitCount ?? 0
    pendingAccountCount.value = data.companyCount ?? 0

    // 更新最新动态（招聘活动）
    // 对时间进行排序，按日期从最近到最远
    const sortedTimeline = data.latestTimeline.sort((a, b) => {
      return new Date(b.time) - new Date(a.time); // 按时间排序，最新的排前面
    });

    // 只取最近的 5 条记录
    timeline.value = sortedTimeline.slice(0, 5);
    
    // 更新统计卡片
    statCards.value = [
      { label: '当前招聘', value: data.currentRecruitCount, desc: '正在进行的岗位数量' },
      { label: '学生投递', value: data.studentDeliveryCount, desc: '学生已投递简历数量' },
      { label: '合作企业', value: data.companyCount, desc: '已合作的企业数量' }
    ]
  } catch (e) {
    console.error('获取数据失败', e)
  }
}


/** 跳转到岗位审核页面 */
const goJobAudit = () => {
  router.push('/judge/job-review')
}

/** 跳转到账号审核页面 */
const goAccountAudit = () => {
  router.push('/judge/account-review')
}

/** 跳转到快速发布招聘页面 */
const goJobPublish = () => {
  router.push('/recruit/jobs')
}

onMounted(() => {
  fetchOverviewData()
})

/** 静态示例统计卡片 */
const statCards = ref([
  { label: '当前招聘', value: 12, desc: '正在进行的岗位数量（示例数据）' },
  { label: '学生投递', value: 86, desc: '系统统计的简历投递次数' },
  { label: '合作企业', value: 18, desc: '已入驻并发布岗位的企业' }
])

const roles = [
  {
    key: 'school',
    name: '学院端',
    desc: [
      '发布招聘信息与实训活动通知',
      '审核企业资质与岗位信息',
      '查看整体招聘与投递统计数据'
    ]
  },
  {
    key: 'student',
    name: '学生端',
    desc: [
      '浏览学院发布的招聘与活动',
      '在线投递简历，查看投递进度',
      '关注个人实践记录与活动参与情况'
    ]
  },
  {
    key: 'company',
    name: '企业端',
    desc: [
      '提交岗位与实训项目需求',
      '筛选学生简历，反馈录用结果',
      '与学院保持长期合作与招聘对接'
    ]
  }
]
</script>

<style scoped lang="scss">
@use "sass:color";

/* 主色 */
$primary-dark-green: #2a5e23;
$primary-gradient: linear-gradient(135deg, #2a5e23, #4a7c3a);

/* ---------- 顶部 Hero ---------- */
.home-hero {
  display: flex;
  gap: 24px;
  padding: 24px 26px;
  margin-bottom: 12px;
  border-radius: 14px;
  background: $primary-gradient;
  color: #fff;
  overflow: hidden;
}

.hero-left {
  flex: 1.4;
  animation: fadeInUp 0.6s ease-out;
}

.hero-right {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  animation: fadeInUp 0.8s ease-out;
}

.hero-badge {
  display: inline-block;
  padding: 4px 12px;
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.16);
  font-size: 13px;
  margin-bottom: 8px;
}

.hero-title {
  margin: 0 0 8px;
  font-size: 30px;
  font-weight: 600;
}

.hero-subtitle {
  margin: 4px 0;
  line-height: 1.9;
  font-size: 15px;
  opacity: 0.96;
  b { font-weight: 600; }
}

/* 深绿色 tag */
.hero-tags .el-tag--info {
  background: rgba(42, 94, 35, 0.85);
  border-color: rgba(42, 94, 35, 0.85);
  color: #fff;
}

.hero-actions {
  margin-top: 18px;
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
  align-items: center;
}

.el-button--primary.is-plain {
  border-color: $primary-dark-green;
  color: $primary-dark-green;
  &:hover {
    border-color: color.adjust($primary-dark-green, $lightness: 8%);
    color:      color.adjust($primary-dark-green, $lightness: 8%);
  }
}

.audit-count { margin-left: 4px; font-weight: 600; }

.hero-version {
  margin-top: 14px;
  font-size: 14px;
  opacity: 0.9;
  span { font-weight: 600; }
}

/* 右侧装饰圆 */
.hero-graphic {
  position: relative;
  width: 210px;
  height: 210px;
  border-radius: 50%;
  background: radial-gradient(circle at 30% 30%, #fff, #e6efe4);
  box-shadow: 0 14px 36px rgba(42,94,35,.22);
  display: flex;
  align-items: center;
  justify-content: center;
}

.hero-graphic-center {
  width: 135px;
  height: 135px;
  border-radius: 50%;
  background: $primary-gradient;
  color: #fff;
  box-shadow: 0 10px 24px rgba(42,94,35,.45);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 4px;
  font-size: 14px;
  letter-spacing: 2px;
}

.bubble {
  position: absolute;
  border-radius: 50%;
  opacity: 0.75;
  animation: float 6s ease-in-out infinite;
}
.bubble-1 { width: 40px; height: 40px; background: rgba(255,255,255,.75); top: 10px; right: -6px; }
.bubble-2 { width: 30px; height: 30px; background: rgba(255,255,255,.6);  bottom: 26px; left: 4px; animation-delay: .8s; }
.bubble-3 { width: 18px; height: 18px; background: rgba(255,255,255,.65); top: 42px; left: 26px; animation-delay: 1.4s; }

/* ---------- 统计卡片 ---------- */
.home-stat-row { margin: 4px 0 10px; }
.stat-card {
  background: #fff;
  border-radius: 10px;
  padding: 16px 16px 13px;
  box-shadow: 0 4px 14px rgba(0,0,0,.05);
  transition: transform .2s, box-shadow .2s;
  &:hover {
    transform: translateY(-3px);
    box-shadow: 0 10px 22px rgba(42,94,35,.18);
  }
}
.stat-label { font-size: 14px; color: #909399; margin-bottom: 4px; }
.stat-value { font-size: 24px; font-weight: 600; margin-bottom: 4px; }
.stat-desc  { font-size: 13px; color: #a0a3ad; }

/* ---------- 底部卡片 ---------- */
.home-bottom-row { margin-top: 6px; }
.home-card { border-radius: 10px; }
.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-weight: 500;
  font-size: 15px;
}
.timeline-title { font-weight: 500; font-size: 15px; margin: 0 0 3px; }
.timeline-desc  { font-size: 13px; color: #909399; margin: 0; }

/* 角色列表 */
.role-list { margin: 0; padding: 0; list-style: none; }
.role-item {
  padding: 8px 0;
  border-bottom: 1px solid #f2f3f5;
  &:last-child { border: none; }
}
.role-title {
  display: flex;
  align-items: center;
  font-size: 15px;
  font-weight: 500;
  margin-bottom: 4px;
}
.role-dot {
  width: 8px; height: 8px; border-radius: 50%; margin-right: 8px;
  &-school   { background: $primary-dark-green; }
  &-student  { background: #67c23a; }
  &-company  { background: #e6a23c; }
}
.role-desc-list {
  margin: 0; padding-left: 16px; list-style: none;
  font-size: 13px; color: #606266; line-height: 1.7;
}

/* ---------- 动画 ---------- */
@keyframes fadeInUp {
  from { opacity: 0; transform: translateY(10px); }
  to   { opacity: 1; transform: translateY(0); }
}
@keyframes float {
  0%,100% { transform: translateY(0); }
  50%     { transform: translateY(-8px); }
}

/* ---------- 响应式 ---------- */
@media (max-width: 992px) {
  .home-hero { flex-direction: column; }
  .hero-right { justify-content: flex-start; }
}
</style>