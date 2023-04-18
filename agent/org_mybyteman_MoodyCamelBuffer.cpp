#include <iostream>
#include "org_mybyteman_MoodyCamelBuffer.h"
#include "concurrentqueue.h"

static int i = 0;
static moodycamel::ConcurrentQueue<int> q;

JNIEXPORT void JNICALL Java_org_mybyteman_MoodyCamelBuffer_put
  (JNIEnv *, jclass, jint item)  {

  q.enqueue(item);
}

JNIEXPORT void JNICALL Java_org_mybyteman_MoodyCamelBuffer_printAll
  (JNIEnv *, jclass) {
    std::cout << q.size_approx() << std::endl;
  }

