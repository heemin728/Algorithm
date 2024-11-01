#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 100001
#define INF 1000000000001
using namespace std;

int n,m;
int points[MAX];
vector<pair<int,int>> graph[MAX];
long long d[MAX];

void dijkstra(int node){

    priority_queue<pair<long long,int>> pq;
    pq.push({0,node});
    d[node]=0;

    while(!pq.empty()){
        long long dist = -pq.top().first;
        int now = pq.top().second;
        pq.pop();

        if(d[now]<dist){
            continue;
        }

        for(int i=0;i<graph[now].size();i++){
            int next=graph[now][i].first;
            long long cost = dist+graph[now][i].second;

            if(next!=n-1 && points[next]==1){
                continue;
            }

            if(cost < d[next]){
                d[next]=cost;
                pq.push({-cost, next});
            }
        }
    }
}

int main(){
    cin >> n >> m;
    for(int i=0;i<n;i++){
        int num;
        cin >> num;
        points[i]=num;
    }

    for(int i=0;i<n;i++){
        d[i]=INF;
    }

    for(int i=0;i<m;i++){
        int a,b,t;
        cin >> a >> b >> t;

        graph[a].push_back({b,t});   
        graph[b].push_back({a,t});
    }

    dijkstra(0);

    if(d[n-1]==INF){
        cout << "-1";
    }
    else{
        cout << d[n-1] << "\n";
    }

    // for(int i=0;i<n;i++){
    //     cout << d[i] << "   ";
    // }
    // cout << "\n";
}