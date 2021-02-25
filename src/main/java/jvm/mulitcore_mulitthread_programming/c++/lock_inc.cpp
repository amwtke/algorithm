#include <iostream>
#include <string>
#include <thread>

const int TC = 4;
int count = 0;
bool flag = true;
void inc_count()
{
    asm("movl	$1, %eax");
    asm("LOCK addl %eax,count(%rip)");
}
void testRun()
{
    for(int i=0; i<10000;i++)
    {
        inc_count();
    }
}

int main()
{
    std::thread threads[TC]; // 默认构造线程
    for (int i = 0; i < TC; ++i)
    {
        threads[i] = std::thread(testRun); // move-assign threads
    }

    std::this_thread::sleep_for(std::chrono::seconds(1));
    std::cout << "main thread id:" << std::this_thread::get_id() << std::endl;
    flag = false;
    for (auto &thread : threads)
    {
        thread.join();
    }

    std::cout << "All threads joined!"
              << " count:" << count<<std::endl;
}
