#include <iostream>
#include <thread>

const int TC = 10;
bool flag=true;
long count;

void inc_count()
{
    count++;
}
void testRun()
{
   std::cout << "flag address:" << &flag<<std::endl;
    while(flag){
       inc_count();
    }
   std::cout << "terminated!"<<"count:"<<count<<std::endl;
   std::cout << "flag:"<<flag<<std::endl;
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
