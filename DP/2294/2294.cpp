#include<iostream>
#include<vector>
#include<algorithm>
#define MAX 10001
#define INF 98765432
using namespace std;

int main(){
    int N,K;

    cin >> N >> K;

    vector<int> coins(N+1);
    int dp[MAX];

    for(int i=1;i<=N;i++){
        cin >> coins[i];
    }

    for(int i=1;i<=K;i++){
        dp[i]=INF;
    }

    for(int i=1;i<=N;i++){
        for(int j=coins[i];j<=K;j++){
            dp[j]=min(dp[j],dp[j-coins[i]]+1);
        }
    }

    if(dp[K]==INF) cout << "-1\n";
    else cout << dp[K];
}