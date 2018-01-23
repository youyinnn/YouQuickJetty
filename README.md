## YouQuickJetty
[![Travis](https://img.shields.io/badge/version-1.0.5.1-green.svg)]()
[![Travis](https://img.shields.io/badge/jetty-9.4.6.v20170531-brightgreen.svg)]()
##### 这是一个“自用”的嵌入式JettyWeb应用快速启动工具包

- - -

TODO:

- <del>干脆把基本的Jetty依赖jar包集成到项目里得了</del>（这个点子太蠢了）
- 热加载（略难）
- 启动前后方法区提供

- - -

### 索引
- [介绍](#介绍)
- [架构](#架构)
  - [依赖](#依赖)
  - [架构、惯例](#架构惯例)
- [示例](#示例)

- - -

<span id="介绍"/>

#### 介绍
使用嵌入式Jetty来快速启动一个Web项目，并且快速部署，在生产环境中的任何地方一键启动项目，并且会陆续集成多种Web开发中常用的功能。

需要特别声明的是，因为这个工具类是自用的工具类，所以使用了本人所习惯的惯例来减少配置量，所以惯例部分一定要看仔细。

- 功能：
    - 快速嵌入Jetty到你的项目中
    - 提供一个BaseHttpFilter 基本就是把请求和响应传为Http的
    - 提供一个配置文件的加载工具类

- - -

<span id="架构"/>

#### 架构

<span id="依赖"/>

##### 依赖
``` xml
        <!-- jetty-server部分 -->
        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-server</artifactId>
            <version>${jetty-version}</version>
        </dependency>

        <!-- jetty-servlet部分 -->
        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-servlet</artifactId>
            <version>${jetty-version}</version>
        </dependency>

        <!-- jetty-webapp部分 -->
        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-webapp</artifactId>
            <version>${jetty-version}</version>
        </dependency>

        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-plus</artifactId>
            <version>${jetty-version}</version>
        </dependency>

        <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-annotations</artifactId>
            <version>${jetty-version}</version>
        </dependency>
```

- - -

<span id="架构惯例"/>

##### 架构、惯例

首先来说架构：

架构总览：

- `package` cn.youyinnn.youquickjetty
  - `package` utils
    - `class` ArgsAnalysis
      ```
      这是一个对args参数进行简单的存储和分析的工具类
      ```
    - `class` BaseHttpFilter
      ```
      这是基本的Filter 里面把ServletRequest和ServletResponse转为HttpServletRequest和HttpServletResponse
      ```
    - `class` YouProUtils
      ```
      这是Properties对象以及系统prop的工具类
      ```
  - `class` YouJetty
     ```
     这是快速启动嵌入式Jetty的核心类
     ```

核心类说明：

YouJetty:
```
    //代码很简单 没啥说明的 直接看使用案例
```

<br>

惯例：

1、项目架构必须为maven的标准架构。

2、webapp在项目下的相对路径为`src/main/webapp`。

3、可以接收的参数只有
  - p : 指定端口号
  - cn : 指定contextName
  - wpth : 指定war包的路径

4、pom文件中用到的一些properties：
``` xml
    <properties>
        <jetty-version>9.4.6.v20170531</jetty-version>
        <compileVersion>1.8</compileVersion>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
```
- - -

<span id="示例"/>

##### 示例

1、首先配置好pom文件

主要是使用插件让maven可以为我们做这些事：

- 把编译在war中的classes目录复制到war外边（maven-compiler-plugin）
- 把集成在war中的lib目录复制到war外边（maven-dependency-plugin）
- 让maven在打包的时候不打包上面复制的两个目录进war包中（maven-war-plugin）
- 把我们写的启动嵌入式Jetty的main函数所在的类作为war的启动主函数（maven-war-plugin）
- 把外边的classes目录加入到classpath中（maven-war-plugin）
- 把“lib”作为前缀加入到依赖的引用当中（maven-war-plugin）

这个时候，我们打包得出的war应该是一个"裸环境"的war包，包内没有class文件以及jar依赖，这些文件我们都复制到外边了。

pom配置：
``` xml
<build>

    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.6.0</version>
            <configuration>
                <encoding>${project.build.sourceEncoding}</encoding>
                <target>${compileVersion}</target>
                <source>${compileVersion}</source>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-resources-plugin</artifactId>
            <version>3.0.1</version>

            <executions>
                <execution>
                    <id>copy-command</id>
                    <phase>package</phase>
                    <goals>
                        <goal>copy-resources</goal>
                    </goals>
                    <configuration>
                        <encoding>${project.build.sourceEncoding}</encoding>
                        <outputDirectory>
                            ${project.build.directory}
                        </outputDirectory>
                        <resources>
                            <resource>
                                <directory>src/main/resources/bin/</directory>
                                <filtering>true</filtering>
                            </resource>
                        </resources>
                    </configuration>
                </execution>
            </executions>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <configuration>
                <warName>${project.artifactId}</warName>
                <!--
                              这两个配置可以在打包的时候把Jar依赖和conf文件中的内容排除掉
                              配合着把配置文件打包在外面
                          -->
                <packagingExcludes>**/*.class,**/conf/*.*,**/*.jar</packagingExcludes>

                <archive>
                    <manifest>
                        <!-- 这里写入口主函数的全类名 -->
                        <mainClass>Start2</mainClass>
                        <addClasspath>true</addClasspath>
                        <!-- 在所有依赖引用之前加上lib前缀 这样就会引用war包外的lib目录-->
                        <classpathPrefix>lib/</classpathPrefix>
                    </manifest>
                    <manifestEntries>
                        <!-- 在Class-Path下添加外部classes文件路径 -->
                        <Class-Path>classes/</Class-Path>
                    </manifestEntries>
                </archive>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <configuration>
                <outputDirectory>${project.build.directory}/lib</outputDirectory>
                <excludeTransitive>false</excludeTransitive>
                <!-- 表示是否不包含间接依赖的包 -->
                <stripVersion>false</stripVersion>
                <!-- 去除版本信息 -->
            </configuration>
            <executions>
                <execution>
                    <id>copy-dependencies</id>
                    <phase>package</phase>
                    <goals>
                        <goal>copy-dependencies</goal>
                    </goals>
                    <configuration>
                        <!-- 拷贝项目依赖包到lib/目录下 -->
                        <outputDirectory>${project.build.directory}/lib</outputDirectory>
                        <excludeTransitive>false</excludeTransitive>
                        <stripVersion>false</stripVersion>
                    </configuration>
                </execution>
            </executions>
        </plugin>

    </plugins>

</build>
```

然后这是主函数：
``` java
public class Start2 {

    public static void main(String[] args) {

        YouJetty youJetty = YouJetty.initServer(args);

        YouProUtils.load("/conf/root.properties");
        System.out.println(YouProUtils.get("root"));

        youJetty.startAndJoin();
    }

}
```

先看看我们在idea下的开发目录结构：

![](https://github.com/youyinnn/YouQuickJetty/raw/master/screenshots/1.png)

然后运行`mvn clean package`，我们可以得到target目录如图：

![](https://github.com/youyinnn/YouQuickJetty/raw/master/screenshots/2.png)

这里面我们真正有用的文件只有：
- classes
- lib
- start.bat
- YouQuickJetty.war

其中start.bat的命令：
```
java -jar .\YouQuickJetty.war -p 8080 -cn YouQuickJetty -wpth ./YouQuickJetty.war
```

我们实际上可以在任何环境中部署以下文件，即可一键启动web应用！

![](https://github.com/youyinnn/YouQuickJetty/raw/master/screenshots/3.png)

![](https://github.com/youyinnn/YouQuickJetty/raw/master/screenshots/4.png)

![](https://github.com/youyinnn/YouQuickJetty/raw/master/screenshots/5.png)

特别看看我们的war包里面，里面的lib和classes都是空的：
![](https://github.com/youyinnn/YouQuickJetty/raw/master/screenshots/6.png)
