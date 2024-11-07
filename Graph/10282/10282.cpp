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

int dijkstra(int start){
    priority_queue<pair<int,int>> pq;

    pq.push({0,start});
    dist[start]=0;

    while(!pq.empty()){
        int cost = -pq.top().first;
        int node = pq.top().second;
        pq.pop();

        //cout << "\nnode = " << node << ", cost = " << cost << "\n";

        if(dist[node] < cost){
            continue;
        }

        for(int i=0;i<v[node].size();i++){
            int ncost= cost + v[node][i].second;
            int next = v[node][i].first;

            //cout << "-next= " << next << "-> ncost = " << ncost << ", dist[next] = " << dist[next] << "\n";
        
            infection[next]=true;

            if( ncost < dist[next] ){
                dist[next]=ncost;
              //  cout << "next =" << next << ", dist = " << dist[next] << "\n";
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
                //cnt+=dist[i];
                cnt=max(cnt,dist[i]);
            }
        }

        // for(int i=1;i<=n;i++){
        //     cout << dist[i] << " ";
        // }
        // cout << "\n";

        cout << total << " " << cnt << "\n";
    }
}