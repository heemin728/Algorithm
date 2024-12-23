#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 500009
using namespace std;

int N,M;
vector<int> friends;
vector<pair<int,int>> v[MAX];
int dist[3][100009];

void dijikstra(int idx){

    int start=friends[idx];
    dist[idx][start]=0;

    priority_queue<pair<int,int>> pq;

    pq.push({0,start});

    while(!pq.empty()){
        int d = -pq.top().first;
        int node=pq.top().second;
        pq.pop();

        if(dist[idx][node] < d){
            continue;
        }

        for(int i=0;i<v[node].size();i++){
            int cost = d + v[node][i].second;
            
            if(cost < dist[idx][v[node][i].first]){
                dist[idx][v[node][i].first] = cost;
                pq.push({-cost, v[node][i].first});
            }
        }
    }
}

int main(){
    cin >> N;

    for(int i=0;i<3;i++){
        int a;
        cin >> a;
        friends.push_back(a);
    }

    cin >> M;

    for(int i=0;i<M;i++){
        int a,b,d;
        cin >> a >> b >> d;

        v[a].push_back({b,d});
        v[b].push_back({a,d});

    }

    for(int j=0;j<3;j++){
        for(int i=1;i<=N;i++){
            dist[j][i]=987654321;
        }
    }

    dijikstra(0);
    dijikstra(1);
    dijikstra(2);

    int max_value=0;
    int answer=0;

    for(int i=1;i<=N;i++){
        int tmp_min=min(min(dist[0][i],dist[1][i]),dist[2][i]);
        if(tmp_min > max_value){
            max_value=tmp_min;
            answer=i;
        }
    }
    cout << answer << "\n";
}