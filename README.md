# Computer_Architecture_Labs
SE-346 计算机体系结构的实验

**实验目的**：

利用Sprak云平台和开源图计算平台GraphX实现SSSP（单源最短路径）和PageRank

**版权声明**：

实验用到的数据集来自SNAP networks，放置在`sourcecode/data`目录下，分别为

* [Wikipedia_vote_network.txt](http://snap.stanford.edu/data/wiki-Vote.html)
* [Google_web_graph.txt](http://snap.stanford.edu/data/web-Google.html)

**目录树**：

```
├──sourcecode
│  ├──data
│  │  ├──Google_web_graph.txt        # Google数据集
│  │  ├──Graph.txt                   # 自定义测试数据集
│  │  └──Wikipedia_vote_network.txt  # Wikipedia数据集
│  ├──project
│  │  └──build.properties
│  ├──src
│  │  └──main
│  │     ├──resources
│  │     │  └──log4j.properties      # 自定义Spark配置文件
│  │     └──scala
│  │        ├──PageRank.scala        # PageRank源码
│  │        ├──SSSP.scala            # SSSP源码
│  │        └──WordCount.scala       # 环境测试源码
│  ├──.gitignore                     # 忽略临时文件和构建文件
│  └──build.sbt                      # SBT工程文件
├──README.md                         # 此说明
├──guidebook.pdf                     # 实验指导手册
└──report.pdf                        # 实验报告
```

