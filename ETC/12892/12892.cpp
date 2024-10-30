
#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int n, d;
vector<pair<int,int>> gifts;

int main(){
    cin >> n >> d;

    for(int i=0;i<n;i++){
        int p,v;
        cin >> p >> v;
        gifts.push_back({p,v});
    }

    sort(gifts.begin(),gifts.end());

    int left=0;
    int right=0;

    long long diff=0;

    long long sum=0;
    long long answer=0;

    while(true){
        diff = gifts[right].first - gifts[left].first;

        if(right >= n || left >=n ){
            break;
        }

        if(diff < d){
            sum += gifts[right].second;
            right++;
            answer=max(answer,sum);
        }
        else{
            sum -= gifts[left].second;
            left++;
        }
    }

    cout << answer << "\n";

}