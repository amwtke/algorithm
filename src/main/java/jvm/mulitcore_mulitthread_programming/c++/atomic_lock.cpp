#include <iostream>
#include <mutex>
#include <thread>

const int TC = 100;
long count = 0;
std::mutex mutex;
void inc_count()
{
    //long占用8个字节，在x86下的load不是原子的，所以这里要加incq？
    __asm__ volatile("lock incq  count(%rip)");
}
void testRun()
{
    for (int j = 0; j < 100000000; j++)
    {
        inc_count();
    //asm volatile ("lock incl  count(%rip)");
    }
//    std::cout << "terminated!"<< " count:"<<count<<std::endl;
}

int main()
{
std::time_t b = std::time(0);
    std::thread threads[TC]; // 默认构造线程
    for (int i = 0; i < TC; ++i)
    {
        threads[i] = std::thread(testRun); // move-assign threads
    }

    for (auto &thread : threads)
    {
        thread.join();
    }

std::time_t e = std::time(0);
    std::cout << "All threads joined!\n"
              << " count:" << count << "\n";
   std::cout<<"time b:"<<b << " time e:"<<e<<std::endl;
}
