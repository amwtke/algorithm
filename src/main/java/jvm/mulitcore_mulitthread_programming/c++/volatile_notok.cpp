#include <iostream>
#include <thread>
#include <mutex>
std::mutex g_display_mutex;

const int TC = 4;
volatile bool flag=true;
volatile long count;

void inc_count()
{
    count++;
}
void testRun(bool flag)
{
    while(flag){
       inc_count();
    }

   g_display_mutex.lock();
   std::cout << "terminated!"<<"count:"<<count<<std::endl;
   std::cout << "flag:"<<flag<<std::endl;
   g_display_mutex.unlock();
}

int main()
{
    std::thread threads[TC]; // 默认构造线程
    for (int i = 0; i < TC; ++i)
    {
        threads[i] = std::thread(testRun,flag); // move-assign threads
    }
    std::this_thread::sleep_for(std::chrono::seconds(1));
    flag = false;
    for (auto &thread : threads)
    {
        thread.join();
    }

    std::cout << "All threads joined!\n"
              << " count:" << count
              << "\n";
}
