#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int m,n;
vector<long long> tks;
long long sum=0;
bool solution(long long time){

    long long cnt=0;

    for(int i=0;i<tks.size();i++){
        cnt+= (time/tks[i]);
        if(cnt >= m){
            return true;
        }
    }
    return false;
}

int main(){
    cin >> n >> m;
    long long max_size=0;

    for(int i=0;i<n;i++){
        long long a;
        cin >> a;
        tks.push_back(a);
        max_size=max(max_size,a);
        sum+=a;
    }

    sort(tks.begin(),tks.end());
    long long start=0;
    long long end=tks[0]*m;
    long long mid=0;
    long long answer=0;

    while(start <= end){
        mid=(start+end)/2;

        if(solution(mid)){
            answer=mid;
            end=mid-1;
        }
        else{
            start=mid+1;
        }
    }

    cout << answer << "\n";
}