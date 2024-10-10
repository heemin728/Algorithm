#include <iostream>
#include <limits.h>
#include <algorithm>
#define MAX 1001
using namespace std;

int C, N;
int main()
{
    cin >> C >> N;

    int dp[MAX] = {
        0,
    };

    for (int i = 0; i < N; i++)
    {
        int cost, customer;
        cin >> cost >> customer;

        for (int j = cost; j < MAX; j++)
        {
            dp[j] = max(dp[j], dp[j - cost] + customer);
        }
    }

    for (int j = 1; j <= MAX; j++)
    {
        if (dp[j] >= C)
        {
            cout << j << "\n";
            break;
        }
    }
}