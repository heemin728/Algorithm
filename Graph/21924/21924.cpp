#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
#define MAX 100001
using namespace std;

int n,m;
vector <pair<int, pair<int,int>>> v;
int parent[MAX];

void init(){
    for(int i=1;i<=n;i++){
        parent[i]=i;
    }
}

int find(long long x){
    if(x==parent[x]){
        return x;
    }
   
    return parent[x]=find(parent[x]);
}

void uni(long long x, long long y){
    int px=find(x);
    int py=find(y);
    
    if(px < py){
        parent[py]=px;
    }
    else{
        parent[px]=py;
    }
}
int main(){
    cin >> n >> m;
    
    init();
    long long total = 0;
    for(int i=0;i<m;i++){
        int a,b,c;
        cin >> a >> b >>c;
        v.push_back({c,{a,b}});
        total+=c;
    }
    sort(v.begin(),v.end());
    
    long long cnt=0;
    
    int sz=v.size();
    for(int i=0;i<m;i++){
        int cost=v[i].first;
        int a=v[i].second.first;
        int b=v[i].second.second;
        
        if(find(a)!=find(b)){
            uni(a,b);
            cnt+=cost;
        }
    }
    
    for(int i=1;i<=n;i++){
        if(find(1)!=find(i)){
            cout << -1 << endl;
            return 0;
        }
    }
    long long ans=total-cnt;
    cout <<  ans<< endl;
 }
