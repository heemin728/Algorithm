#include<iostream>
#include<algorithm>
#include<vector>
#define MAX 16
using namespace std;

int N,L,R,X;
vector<int> v;
int cnt;

void dfs(int idx, int sum, int min_index ,int max_index){
    
    if(idx==N-1 && sum >= L && sum <= R && v[max_index]-v[min_index] >=X){
        cnt++;
        return;
    }
    if(sum > R || idx == N-1){
        return;
    }
    dfs(idx+1,sum+v[idx+1], min_index, idx+1);
    dfs(idx+1,sum, min_index, max_index);
}

int main(){
    cin >> N >> L >> R >> X;

    for(int i=0;i<N;i++){
        int a;
        cin >> a;
        v.push_back(a);
    }

    sort(v.begin(),v.end());
    
    for(int i=0;i<N;i++){
        dfs(i,v[i],i,i);
    }
    cout << cnt << "\n";
}