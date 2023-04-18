cd target
javac -g  org/my/*.java
sleep 1
# java -javaagent:${BYTEMAN_HOME}/lib/byteman.jar=script:../increment.btm \
#     org.my.SimpleProgram 20 10000000

java -javaagent:${BYTEMAN_HOME}/lib/byteman.jar=script:../increment.btm \
    org.my.SimpleProgram 20 10000000
 

# java -javaagent:${BYTEMAN_HOME}/lib/byteman.jar=script:increment.btm \
#     -XX:+UnlockDiagnosticVMOptions -Xbatch -XX:-TieredCompilation -XX:+PrintCompilation \
#     -XX:+PrintAssembly org.my.SimpleProgram 10 10000000
# mypid=$!

# bminstall.sh $mypid
# bmsubmit.sh -l increment.btm
# bmsubmit.sh
# echo "done"

# wait $mypid
