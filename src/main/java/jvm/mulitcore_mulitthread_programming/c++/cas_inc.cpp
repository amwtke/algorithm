#include <iostream>
#include <string>
#include <thread>
#include <mutex>
#include <atomic>
std::mutex g_display_mutex;

const int TC = 4;
bool flag = true;
std::atomic<int> count{0};
void inc_count() {
    count.fetch_add(1, std::memory_order_seq_cst);
}

void testRun()
{
    for(int i=0; i<10000;i++)
    {
        inc_count();
    }
    g_display_mutex.lock();
    std::cout << "child thread id:" << std::this_thread::get_id() <<" count is:" <<count<<std::endl;
    std::cout << "terminated\n";
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
    std::cout << "main thread id:" << std::this_thread::get_id() << std::endl;
    flag = false;
    for (auto &thread : threads)
    {
        thread.join();
    }

    std::cout << "All threads joined!"
              << " count:" << count<<std::endl;
}
