#include <iostream>

#include <thread>

const int TC = 4;
int count = 0;
int inc = 1;
void inc_count()
{
    count += inc;
}
void testRun()
{
    while (inc == 1)
    {
        inc_count();
    }
    std::cout << "child thread id:" << std::this_thread::get_id() << std::endl;
    std::cout << "terminated\n";
}

int main()
{
    std::thread threads[TC]; // 默认构造线程
    for (int i = 0; i < TC; ++i)
    {
        threads[i] = std::thread(testRun); // move-assign threads
    }

    std::this_thread::sleep_for(std::chrono::microseconds(1000));
    std::cout << "main thread id:" << std::this_thread::get_id() << std::endl;
    inc = 0;
    for (auto &thread : threads)
    {
        thread.join();
    }

    std::cout << "All threads joined!\n"
              << " count:" << count << "\n";
}
