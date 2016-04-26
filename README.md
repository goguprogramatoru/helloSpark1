# Hello Spark: Project 1
Spark training project 1

What you will learn
- Create a first app with Apache Spark
- Use Spark as a lib to your Scala application
- Basic RDD operations 

About this project: 
Does a word count on a story
- downloads a story to a txt file
- use spark to split the file in words & count the occurances of each word

How to do a similar project from scratch: 
- Use InteliJ IDEA (with Scala plugin installed)
- New project -> Scala -> SBT
- Files: 
    - build.sbt: you can put here the library dependencies (similar to maven)
    - src/main/scala: put your scala code here
    - src/main/resources/log4j.properties: custom logger to reduce the logging output of spark to WARN,ERROR
    - src/main/scala/Main: the app logic (download + do the spark task)

That's all!!
