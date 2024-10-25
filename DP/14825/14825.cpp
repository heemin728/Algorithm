#include<iostream>
#include<vector>
#include<algorithm>
#define MAX 1000001
#define DIVIDE 1000000007
using namespace std;

int N;
long long dp[MAX];
long long sum[MAX]; 

int main(){
    cin >> N;
    
    dp[0]=1;
    dp[1]=2;
    dp[2]=7;
    sum[0]=1;
    sum[1]=3;
    sum[2]=10;

    for(int i=3;i<=N;i++){
        dp[i]=(dp[i-1]*2+dp[i-2]*3 + 2*sum[i-3])%DIVIDE;
        sum[i]=sum[i-1]+dp[i];
    }
    cout << dp[N] << "\n";
}