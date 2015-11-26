# 智慧北京(COPY)

## day01

### splash界面(SplashActivity.java)
- ImageView + ShareUtil
  - splash界面读取shared文件,判断是否第一次访问

### 导航页面(GuideActivity.java)
- 使用viewPager 进行导航界面切换
- 如果到最后一张图片就显示按钮,否则隐藏
  - addOnPageChangeListener
    - onPageSelected,当前选中的页面

### 侧滑菜单
- 导入第三方库
  - 报错解决方法
    - 找到menu_library文件build.gradle文件
    - 修改dependencies 和 buildToolsVersion
    - 改和app目录下的一致
 - 将menu_library库引入当前项目
   - project structure 找到APP
   - dependencies (module dependencies)
