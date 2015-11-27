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
 - 初始化侧滑菜单
   - 继承 SlidingFragmentActivity
   - 设置菜单显示的基本属性
   - 使用Fragment填充菜单和主页面

### 主页面
  - 布局homeFragment界面
    - 上面自定义viewpager
    - 下面radioGroup
   - 定义一个BasePager,都继承它
     - 将所有的子pager放在一个list集合中
   - 给viewPager添加适配器
      - PagerAdapter
      - 返回所有的pager
  - radioGroup给按钮设置监听
    - setOnCheckedChangeListener
    - 根据当前选中的按钮跳转到对应的viewpager
      - viewpager.setCurrentItem(0);
### 完善界面显示
  - 初始化标题
    - 给basepager定义一个初始化标题的方法
    - 单独将标题抽取出来
    - 所有的内容使用include导入标题
### viewPager优化
  - 屏蔽掉滑动事件
    - onInterceptTouchEvent (false)
    - onTouchEvent (false)
  - 去掉预加载
    - DEFAULT_OFFSCREEN_PAGES =0
### 获取新闻中心数据
  - 导入xutil库
    - 如果出现最低版本错误的话,
    - 到build.gradle修改minSdkVersion 7