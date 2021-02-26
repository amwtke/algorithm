#include <iostream>
#include <thread>
#include <mutex>
std::mutex g_display_mutex;

const int TC = 10;
volatile bool flag=true;
volatile long count;

void inc_count()
{
    count++;
}
void testRun()
{
    while(flag){
       inc_count();
    }
   std::time_t t = std::time(0);  // t is an integer type

   g_display_mutex.lock();
   std::cout << "happen time:"<< t <<std::endl;
   std::cout << "thread id:"<< std::this_thread::get_id() <<std::endl;
   std::cout << "terminated!"<<"count:"<<count<<std::endl;
   std::cout << "flag:"<<flag<<std::endl;
   g_display_mutex.unlock();
}

int main()
{
    std::thread threads[TC]; // 默认构造线程
    for (int i = 0; i < TC; ++i)
    {
        threads[i] = std::thread(testRun); // move-assign threads
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
