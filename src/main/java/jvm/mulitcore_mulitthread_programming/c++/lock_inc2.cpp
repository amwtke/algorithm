#include <iostream>

#include <thread>

const int TC = 10;
int count = 0;
int inc = 1;
void inc_count()
{
    asm("movl	inc(%rip), %eax");
    asm("lock addl	%eax, count(%rip)");
    asm("popq	%rbp");
    asm("retq");

}
void testRun()
{
    for (int j = 0; j < 10000; j++)
    {
        inc_count();
    }
    std::cout << "terminated\n";
}

int main()
{
    std::thread threads[TC]; // 默认构造线程
    for (int i = 0; i < TC; ++i)
    {
        threads[i] = std::thread(testRun); // move-assign threads
    }

    for (auto &thread : threads)
    {
        thread.join();
    }

    std::cout << "All threads joined!\n"
              << " count:" << count << "\n";
}
