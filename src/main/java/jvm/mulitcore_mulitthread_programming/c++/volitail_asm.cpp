#include <iostream>
#include <thread>
#include <mutex>
std::mutex g_display_mutex;

const int TC = 300;
int flag=1;
long count;
long inc=1;

void inc_count()
{
    asm("movl   inc(%rip), %eax");
    asm("lock addl      %eax, count(%rip)");
    asm("popq   %rbp");
    asm("retq");
}

void sub_flag()
{
    asm("movl   inc(%rip), %eax");
    asm("lock sub      %eax, flag(%rip)");
    asm("popq   %rbp");
    asm("retq");
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
    sub_flag();
    for (auto &thread : threads)
    {
        thread.join();
    }

    std::cout << "All threads joined!\n"
              << " count:" << count
              << "\n";
}
