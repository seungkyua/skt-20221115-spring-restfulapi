### git 설치
  ```
yum install -y git
  ```
### java 설치
  ```
$ yum install java-1.8.0-openjdk
$ yum install java-1.8.0-openjdk-devel
$ readlink -f /usr/bin/java
  /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.262.b10-0.1.al7.x86_64/jre/bin/java
$ vi /etc/profile 수정
  ```
  ```
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.242.b08-0.el7_7.x86_64
PATH=$PATH:$JAVA_HOME/bin
CLASSPATH=$JAVA_HOME/jre/lib:$JAVA_HOME/lib/tools.jar

export JAVA_HOME PATH CLASSPATH
  ```
3. maven 설치
  ```
$ wget http://www-eu.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
$ sudo tar xzf apache-maven-3.6.3-bin.tar.gz
$ sudo ln -s apache-maven-3.6.3  maven
$ vi /etc/profile.d/maven.sh
  ```
  ```
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.242.b08-0.el7_7.x86_64
export MAVEN_HOME=/root/maven
export PATH=${MAVEN_HOME}/bin:${PATH}
  
  ```
