#include <iostream>
#include<algorithm>
#include<vector>
#define MAX 501
#define INF 99999999
using namespace std;

int N,M;
vector<pair<pair<int,int>,int>> v;
long long dist[MAX];

void bellman_ford(){
    
    dist[1]=0;
    
    for(int i=1;i<N;i++){
        for(int j=0;j<M;j++){
            int from=v[j].first.first;
            int to=v[j].first.second;
            int cost=v[j].second;
            
            if(dist[from]==INF){
                continue;
            }
            
            if(dist[to] > dist[from]+cost) {
                dist[to]=dist[from]+cost;
            }
            
        }
    }
    
        for(int j=0;j<M;j++){
            int from=v[j].first.first;
            int to=v[j].first.second;
            int cost=v[j].second;
            
            if(dist[from]==INF){
                continue;
            }
            
            if(dist[to] > dist[from]+cost) {
                cout << "-1\n";
                return;
            }
            
        }
        
    for(int i=2;i<=N;i++){
        if(dist[i]==INF){
            cout << -1 << "\n";
        }
        else{
            cout << dist[i] << "\n";
        }
    }    
}

int main()
{
    cin >> N >> M;
    
    for(int i=0;i<M;i++){
        int a,b,c;
        cin >> a >> b >> c;
        v.push_back({{a,b},c});
    }
    
    for(int i=0;i<=N;i++){
        dist[i]=INF;
    }
    bellman_ford();
    

    return 0;
}
