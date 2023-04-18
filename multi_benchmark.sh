cd agent
javac -h . -cp './disruptor-4.0.0.RC1.jar:./javassist.jar' org/mybyteman/*.java
# javac  -h . org/mybyteman/MoodyCamelBuffer.java
g++ -c -fPIC -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux org_mybyteman_MoodyCamelBuffer.cpp
g++ -shared -fPIC -o libnative.so org_mybyteman_MoodyCamelBuffer.o -lc
jar cfm myAgent.jar ./MANIFEST.MF  org/mybyteman/*.class
# java -cp './disruptor-4.0.0.RC1.jar' org.mybyteman.TestDisruptor

cd -
cd target
javac org/my/*.java
# # sleep 1
# java -Xmx200m -javaagent:../agent/myAgent.jar org.my.SimpleProgram 20 10000000
# java -Djava.library.path=../agent/ -Xmx200m -javaagent:../agent/myAgent.jar org.my.SimpleProgram 10 10000000
for i in `seq 1 64`
do
java -Djava.library.path=../agent/ -Xmx200m -javaagent:../agent/myAgent.jar org.my.SimpleProgram2 $i 10 10000000
done > result.txt
# java -Xmx200m -javaagent:../agent/myAgent.jar org.my.SimpleProgram2 3 10 10000000
# # java  org.my.SimpleProgram 20 10000000
