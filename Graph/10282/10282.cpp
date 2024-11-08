#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX 10001
#define INF 987654321
using namespace std;

int n,d,c;
int cnt;
int dist[MAX];
bool infection[MAX];

vector<pair<int,int>> v[MAX];

void dijkstra(int start){
    priority_queue<pair<int,int>> pq;

    pq.push({0,start});
    dist[start]=0;

    while(!pq.empty()){
        int cost = -pq.top().first;
        int node = pq.top().second;
        pq.pop();

        if(dist[node] < cost){
            continue;
        }

        for(int i=0;i<v[node].size();i++){
            int ncost= cost + v[node][i].second;
            int next = v[node][i].first;
        
            infection[next]=true;

            if( ncost < dist[next] ){
                dist[next]=ncost;
                pq.push({-ncost,next});
            }
        }
    }
}

int main(){
    int t;
    cin >> t;

    for(int T=0;T<t;T++){
        cin >> n >> d >> c;

        cnt=0;
        for(int j=1;j<=n;j++){
            dist[j]=INF;
            v[j].clear();
            infection[j]=false;
        }

        for(int i=0;i<d;i++){
            int a,b,s;

            cin >> a >> b >> s;

            v[b].push_back({a,s});
        }

        infection[c]=true;
        dijkstra(c);

        int total=0;
        for(int i=1;i<=n;i++){
            if(infection[i]){
                total++;
                cnt=max(cnt,dist[i]);
            }
        }
        cout << total << " " << cnt << "\n";
    }
}