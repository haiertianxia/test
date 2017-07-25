## 账号支付ui自动化脚本

### 设置环境变量
- 运行前需要设置环境变量  SUITE,匹配 mvn build 执行文件通配符号，见pom.xml 变量 ${env.SUITE}
- 默认可设置AllFeatureTest.java 全部运行生成一个json报告
- 设置AllTest.java 全部运行每个FetureTest类生成一个json报告
- 也可以指定运行某一FeatureTest 如：YeYouFeatureTest.java
- 在windows 可以使用 set SUITE=YeYouFeatureTest.java 设定环境变量，只本次有效
- pom.xml配置环境变量，运行某个test