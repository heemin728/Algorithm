#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 21
using namespace std;

int N;
vector<int> inputs;
int main(){
    int T;
    cin >> T;

    for(int i=0;i<T;i++){
        cin >> N;
        inputs.push_back(N);
    }

    long long dp[MAX];

    dp[1]=0;
    dp[2]=1;

    for(int i=3;i<MAX;i++){
        dp[i]=(i-1)*(dp[i-1]+dp[i-2]);
    }

    for(int i=0;i<T;i++){
        cout << dp[inputs[i]] << "\n";
    }
}