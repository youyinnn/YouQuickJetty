### YouQuickJetty
##### 这是一个自用的嵌入式JettyWeb应用快速启动工具包
- - -

### 索引
- [介绍](#介绍)
- [架构](#)
  - [依赖](#)
  - [架构以及惯例说明](#)
- [示例](#)

- - -

<span id="介绍"/>
#### 介绍

使用嵌入式Jetty来快速启动一个Web项目，并且快速部署，在生产环境中的任何地方一键启动项目。

- - -

<span id="架构"/>
#### 架构
##### 依赖
```
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

##### 架构以及惯例说明

未完待续
